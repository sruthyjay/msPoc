package com.ibm.convertcurrency.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ibm.convertcurrency.client.RestClient;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

@Service
public class ConvertCurrencyService {
	
	public static Logger logger = LoggerFactory.getLogger(ConvertCurrencyService.class);

	final RestClient restClient;

	@Autowired
	public ConvertCurrencyService(RestClient restClient) {
		this.restClient = restClient;
	}

	@HystrixCommand(fallbackMethod="convertCurrencyFallBack")
	public Double convertCurrency(String countryCode, Double amount) {
		logger.info("Calling ms1 to get the conversion Unit for country code : "+countryCode);
		Double conversionUnit = restClient.getConversionUnit(countryCode);
		logger.info(" conversion Unit for country code : "+countryCode +"is :"+conversionUnit);
		return conversionUnit * amount;

	}
	
	public Double convertCurrencyFallBack(String countryCode , Double amount) {
		
		return 10d;
		
	}

}
