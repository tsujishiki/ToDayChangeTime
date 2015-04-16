package org.soya.tdct.service;

import org.soya.tdct.model.Country;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by FunkySoya on 2015/3/29.
 */
@Service
public interface CountrySer {
    Country selectCountryByCode(String code);

    List<Country> queryAllCountry();
}
