package org.soya.mcore.service;

import org.soya.mcore.model.User;

/**
 * Created by Administrator on 2015/4/17.
 */
public interface UserSer {

    public User selectByName(String userName);

}
