package com.ibm.convertcurrency.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ibm.convertcurrency.service.ConvertCurrencyService;

@RequestMapping("/currencyConversion")
@RestController
public class ConvertCurrencyController {
	
	@Autowired
	ConvertCurrencyService convertService;

	@GetMapping("/{countryCode}/{amount}")
	public ResponseEntity<Double> convertCurrency(@PathVariable(value = "countryCode") String countryCode,
			@PathVariable(value = "amount") Double amount) {
		
		Double convertedAmount = convertService.convertCurrency(countryCode, amount);
		return ResponseEntity.ok().body(convertedAmount);

	}
	 

}
