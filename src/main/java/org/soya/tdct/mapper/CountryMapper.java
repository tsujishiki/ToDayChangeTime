package org.soya.tdct.mapper;

import org.soya.tdct.model.Country;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CountryMapper {
	Country selectCountryByCode(String code);

	List<Country> queryAllCountry();
}
