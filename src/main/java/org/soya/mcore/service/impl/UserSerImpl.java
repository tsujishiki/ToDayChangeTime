package org.soya.mcore.service.impl;

import org.soya.mcore.mapper.UserMapper;
import org.soya.mcore.model.User;
import org.soya.mcore.service.UserSer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Administrator on 2015/4/17.
 */
@Service
public class UserSerImpl implements UserSer {

    @Autowired
    UserMapper mapper;

    @Override
    public User selectByName(String userName) {
        return mapper.selectByName(userName);
    }

    public int updateToken(String userId,String token) {
        return mapper.updateToken(userId, token);
    }
}
