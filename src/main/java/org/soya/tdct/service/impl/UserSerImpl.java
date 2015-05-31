package org.soya.tdct.service.impl;

import org.soya.mcore.mapper.UserMapper;
import org.soya.mcore.model.User;
import org.soya.tdct.service.UserSer;
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

    @Override
    public int updateToken(String userId,String token) {
        return mapper.updateToken(userId, token);
    }

    @Override
    public User selectByNickName(String nickName) {
        return mapper.selectByNickName(nickName);
    }

    public int addUser(User user){
        return mapper.insertUser(user);
    }
}
