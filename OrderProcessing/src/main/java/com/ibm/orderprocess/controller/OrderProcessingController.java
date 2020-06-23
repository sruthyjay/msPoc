package com.ibm.orderprocess.controller;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.Date;

import javax.sql.rowset.serial.SerialException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.ibm.orderprocess.entity.AuditLog;
import com.ibm.orderprocess.entity.OrderDetails;
import com.ibm.orderprocess.jwt.JWTUtility;
import com.ibm.orderprocess.repo.AuditRepo;
import com.ibm.orderprocess.repo.OrderDetailsRepo;


@RestController
@CrossOrigin(origins = "*")

public class OrderProcessingController {
	
	@Autowired
	JWTUtility jwtUtility;
	
	@Autowired
	OrderDetailsRepo orderRepo;
	
	@Autowired
	AuditRepo auditRepo;
	
	@GetMapping("/message")
	public String getMessage() {
		return "Hello";
	}
	
	@GetMapping(path="/orderprocessing/add/{itemName}/{qty}/{usrToken}/{txnToken}", produces = "application/json")
	public String placeOrder(@PathVariable String itemName, @PathVariable Double qty, 
			@PathVariable String usrToken, @PathVariable String txnToken) {
		SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss");
		Date tokenDate;
		if(usrToken != null) {
			String[] strArr = usrToken.split(":TTL:");
			String tokenCreatedDate = strArr[1];
			if(tokenCreatedDate != null) {
				try {
					tokenDate = format.parse(tokenCreatedDate);
					Date currentDate = format.parse(LocalTime.now().toString());
					long diff = currentDate.getTime() - tokenDate.getTime();
					if(diff > 60000) {
						return "Session Expired. <button onClick=\"logout()\">Click here for Login Page</button>";
					}
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		OrderDetails order = new OrderDetails();
		order.setItemName(itemName);
		order.setQty(qty);
		order = orderRepo.save(order);
		
		String svcToken = jwtUtility.generateToken("");
		AuditLog auditLog = new AuditLog();
		try {
			auditLog.setUsrToken(new javax.sql.rowset.serial.SerialClob(usrToken.toCharArray()));
		} catch (SerialException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		auditLog.setTxnToken(txnToken);
		auditLog.setServiceToken(svcToken);
		auditLog.setOrderId(order.getId());
		auditLog = auditRepo.save(auditLog);
		System.out.println("auditLog Id="+auditLog.getId());
		
		return "Thank you for shopping with us !!! Your Order Id is :" + order.getId();
	}

}
