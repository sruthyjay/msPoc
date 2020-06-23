package com.ibm.currencyfactor.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ibm.currencyfactor.dto.CurrencyFactorDTO;
import com.ibm.currencyfactor.service.CurrencyFactorService;

@RequestMapping("/conversionFactor")
@RestController
public class CurrencyFactorController {
	
	@Autowired
	CurrencyFactorService currecyService;

	@GetMapping("/message")
	public String getMessage() {
		return "hello";
	}
	
	@PostMapping("/add")
	public ResponseEntity<CurrencyFactorDTO> addConversionFactor(@RequestBody CurrencyFactorDTO currencyFactorDTO) {
		
		CurrencyFactorDTO dto = currecyService.addConversionFactor(currencyFactorDTO);
		return new ResponseEntity<CurrencyFactorDTO>(dto,HttpStatus.CREATED);		
	}
	
	
	 @GetMapping("/{countryCode}")
	 public Double searchByCountryCode(@PathVariable(value="countryCode") String countryCode){
		 CurrencyFactorDTO dto =currecyService.getCurrencyFactorByCountryCode(countryCode);
		 return dto.getConversionUnit();
	}
	 
	 
	 @PutMapping("/{countryCode}/{conversionUnit}")
	 public  ResponseEntity<Object> updateConversionFactor(@PathVariable(value="countryCode") String countryCode ,
			 @PathVariable(value="conversionUnit") Double conversionUnit){
		 
			return 	currecyService.updateConversionFactor(countryCode,conversionUnit);
		 
	 }

}
