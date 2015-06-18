package org.soya.tdct.service;

import org.soya.tdct.model.Business;

/**
 * Created by FunkySoya on 2015/6/18.
 */
public interface BusinessSer {

    Boolean add(Business business);

    Boolean update(Business business);

    Boolean del(String businessId);
}
