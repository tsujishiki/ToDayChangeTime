package org.soya.tdct.mapper;

import org.apache.ibatis.annotations.Param;
import org.soya.tdct.model.Business;
import org.springframework.stereotype.Repository;

/**
 * Created by FunkySoya on 2015/6/18.
 */
@Repository
public interface BusinessMapper {

    Boolean add(Business business);

    Boolean update(Business business);

    Boolean del(@Param("businessId")String businessId);

}
