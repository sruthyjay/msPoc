package com.ibm.currencyfactor.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name= "currencyFactor")
@Entity
public class CurrencyFactor {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column(name="countrycode")
	private String countryCode;
	
	@Column(name="conversionunit")
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
