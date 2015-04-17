package org.soya.tdct.service.impl;

import org.soya.tdct.mapper.CountryMapper;
import org.soya.tdct.model.Country;
import org.soya.tdct.service.CountrySer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by FunkySoya on 2015/3/29.
 */
@Service
public class CountrySerImpl implements CountrySer {

    @Autowired
    private CountryMapper countryMapper;

    @Override
    public Country selectCountryByCode(String code) {
        return countryMapper.selectCountryByCode(code);
    }

    @Override
    public List<Country> queryAllCountry() {
        return countryMapper.queryAllCountry();
    }

}
