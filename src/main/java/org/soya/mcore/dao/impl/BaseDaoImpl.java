package org.soya.mcore.dao.impl;


import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.soya.mcore.dao.BaseDao;

/**
 * Created by FunkySoya on 2015/3/29.
 */

public class BaseDaoImpl implements BaseDao {

    @Autowired(required = true)
    protected SqlSession sqlSessionTemplate;


}
