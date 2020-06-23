package com.ibm.currencyfactor.repo;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ibm.currencyfactor.domain.CurrencyFactor;

@Repository
public interface CurrencyFactorRepo extends JpaRepository<CurrencyFactor, Long>{
 
	 CurrencyFactor findByCountryCode(String countryCode);

}
