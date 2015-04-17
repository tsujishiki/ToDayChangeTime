package org.soya.mcore.mapper;

import org.soya.mcore.model.User;
import org.springframework.stereotype.Repository;

/**
 * Created by Administrator on 2015/4/17.
 */
@Repository
public interface UserMapper {
    User selectByName(String userName);
}
