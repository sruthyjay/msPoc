package com.ibm.currencyfactor.dto;

public class CurrencyFactorDTO {
	
	private Long id;
	
	private String countryCode;
	
	private Double conversionUnit;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCountryCode() {
		return countryCode;
	}

	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}

	public Double getConversionUnit() {
		return conversionUnit;
	}

	public void setConversionUnit(Double conversionUnit) {
		this.conversionUnit = conversionUnit;
	}

}
