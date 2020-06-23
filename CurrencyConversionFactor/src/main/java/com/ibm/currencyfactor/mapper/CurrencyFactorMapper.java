package com.ibm.currencyfactor.mapper;

import com.ibm.currencyfactor.domain.CurrencyFactor;
import com.ibm.currencyfactor.dto.CurrencyFactorDTO;

public class CurrencyFactorMapper {
	
public CurrencyFactorDTO convertCurrencyFactorDAOToDTO(CurrencyFactor dao) {
		
		CurrencyFactorDTO cfdto = new CurrencyFactorDTO();
		cfdto.setId(dao.getId());
		cfdto.setCountryCode(dao.getCountryCode());
		cfdto.setConversionUnit(dao.getConversionUnit());
		
		return cfdto;
		
	}
	
	
	public CurrencyFactor convertCurrencyFactorDTOToDAO(CurrencyFactorDTO dto) {
		
		CurrencyFactor cfdao = new CurrencyFactor();
		cfdao.setId(dto.getId());
		cfdao.setCountryCode(dto.getCountryCode());
		cfdao.setConversionUnit(dto.getConversionUnit());

		return cfdao;
		
	}

}
