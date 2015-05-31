package org.soya.mcore.mapper;

import org.apache.ibatis.annotations.Param;
import org.soya.mcore.model.User;
import org.springframework.stereotype.Repository;

/**
 * 用户Mapper
 * Created by Administrator on 2015/4/17.
 */
@Repository
public interface UserMapper {

    /**
     * 根据用户名获取用户
     * @param userName
     * @return User
     */
    User selectByName(String userName);

    /**
     * 根据昵称获取用户
     * @param nickName
     * @return User
     */
    User selectByNickName(String nickName);

    /**
     * 插入用户
     * @param user
     * @return int
     */
    int insertUser(User user);

    /**
     * 用户登录后更新用户token
     * @param userId
     * @param token
     * @return
     */
    int updateToken(@Param("userId")String userId,@Param("token")String token);
}
