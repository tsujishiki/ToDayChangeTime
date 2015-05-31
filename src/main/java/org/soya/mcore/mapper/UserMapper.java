package org.soya.mcore.mapper;

import org.apache.ibatis.annotations.Param;
import org.soya.mcore.model.User;
import org.springframework.stereotype.Repository;

/**
 * �û�Mapper
 * Created by Administrator on 2015/4/17.
 */
@Repository
public interface UserMapper {

    /**
     * �����û�����ȡ�û�
     * @param userName
     * @return User
     */
    User selectByName(String userName);

    /**
     * �����ǳƻ�ȡ�û�
     * @param nickName
     * @return User
     */
    User selectByNickName(String nickName);

    /**
     * �����û�
     * @param user
     * @return int
     */
    int insertUser(User user);

    /**
     * �û���¼������û�token
     * @param userId
     * @param token
     * @return
     */
    int updateToken(@Param("userId")String userId,@Param("token")String token);
}
