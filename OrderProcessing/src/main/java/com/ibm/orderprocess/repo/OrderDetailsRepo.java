package com.ibm.orderprocess.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ibm.orderprocess.entity.OrderDetails;

public interface OrderDetailsRepo extends JpaRepository<OrderDetails, Integer>{

}
