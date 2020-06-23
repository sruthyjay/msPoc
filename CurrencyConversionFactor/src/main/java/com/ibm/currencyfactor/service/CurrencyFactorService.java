package com.ibm.currencyfactor.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.ibm.currencyfactor.domain.CurrencyFactor;
import com.ibm.currencyfactor.dto.CurrencyFactorDTO;
import com.ibm.currencyfactor.mapper.CurrencyFactorMapper;
import com.ibm.currencyfactor.repo.CurrencyFactorRepo;

@Service
public class CurrencyFactorService {
	
	public static Logger logger = LoggerFactory.getLogger(CurrencyFactorService.class);
	
	@Autowired
	CurrencyFactorRepo currencyFactorRepo;
	
	public CurrencyFactorDTO addConversionFactor(CurrencyFactorDTO dto) {
		
		CurrencyFactorMapper mapper = new CurrencyFactorMapper();
		CurrencyFactor cfdao = mapper.convertCurrencyFactorDTOToDAO(dto);
		CurrencyFactor dao = currencyFactorRepo.save(cfdao);	
		return mapper.convertCurrencyFactorDAOToDTO(dao);
		
	}

	public ResponseEntity<Object> updateConversionFactor(String countryCode , Double conversionUnit) {
		
		CurrencyFactor currencyFact = currencyFactorRepo.findByCountryCode(countryCode);
		if(currencyFact != null)
			currencyFact.setConversionUnit(conversionUnit);
		currencyFactorRepo.save(currencyFact);
		
		return ResponseEntity.noContent().build();
		
	}
	
	public CurrencyFactorDTO getCurrencyFactorByCountryCode(String countryCode) {
		logger.info("Inside getCurrencyFactory for CountryCode :"+countryCode);
		CurrencyFactor currencyFact = currencyFactorRepo.findByCountryCode(countryCode);
		CurrencyFactorDTO dto = null;
		if(currencyFact != null) {
			CurrencyFactorMapper mapper =  new CurrencyFactorMapper();
			dto = mapper.convertCurrencyFactorDAOToDTO(currencyFact);		
		}
		logger.info("Conversion Unit for CountryCode :"+countryCode +"is :" +dto.getConversionUnit() );
		return dto;
	}

}
