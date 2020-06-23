package com.ibm.convertcurrency.client;

import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient("Currency-Factor")
@RibbonClient("Currency-Factor")
public interface RestClient {

	@GetMapping("/conversionFactor/{countryCode}")
	public Double getConversionUnit(@PathVariable(value="countryCode") String countryCode);
}
