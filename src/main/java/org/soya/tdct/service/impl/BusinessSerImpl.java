package org.soya.tdct.service.impl;

import org.soya.tdct.mapper.BusinessMapper;
import org.soya.tdct.model.Business;
import org.soya.tdct.service.BusinessSer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by FunkySoya on 2015/6/18.
 */
@Service
public class BusinessSerImpl implements BusinessSer {

    @Autowired
    BusinessMapper businessMapper;

    @Override
    public Boolean add(Business business) {
        return businessMapper.add(business);
    }

    @Override
    public Boolean update(Business business) {
        return businessMapper.update(business);
    }

    @Override
    public Boolean del(String businessId) {
        return businessMapper.del(businessId);
    }
}
