package org.soya.tdct.controller;

import org.soya.tdct.model.Country;
import org.soya.tdct.service.CountrySer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * Created by FunkySoya on 2015/3/29.
 */
@RestController
@RequestMapping("/country")
public class CountryController {

    @Autowired
    CountrySer countrySer;

    @RequestMapping(value = { "/{code}" }, method = { org.springframework.web.bind.annotation.RequestMethod.GET })
    public ModelAndView getCountry(@PathVariable("code")String code, ModelMap modelMap) throws Exception {
        Country country = countrySer.selectCountryByCode(code);
        modelMap.put("country", country);
        return new ModelAndView("countryviewer", modelMap);
    }


    @RequestMapping(value = {"/getAjax"}, method = { org.springframework.web.bind.annotation.RequestMethod.GET })
    @ResponseBody
    public Country getAjax(){
        Country country = new Country();
        country.setName("中国");
        return country;
    }

    @RequestMapping(value = { "/all" }, method = { org.springframework.web.bind.annotation.RequestMethod.GET })
    @ResponseBody
    public List<Country> getAllCountry() {
        return countrySer.queryAllCountry();
    }
}
