package org.soya.mcore.service.impl;

import org.soya.mcore.model.Dictionary;
import org.soya.mcore.service.BaseDataSer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.BoundHashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 基本数据
 * 数据从redis中获取
 * Created by FunkySoya on 2015/5/31.
 */
@Service
public class BaseDataSerImpl implements BaseDataSer {

    @Autowired
    RedisTemplate redis;

    /**
     * 根据字典类型获取字典
     * @param Type
     * @return List<Dictionary>
     */
    @Override
    public List<Dictionary> getListByCode(String Type) {
        List<Dictionary> dictList = new ArrayList<>();
        Dictionary dict = null;
        BoundHashOperations boundHashOperations = redis.boundHashOps(Type);

        Map<String,String> map = boundHashOperations.entries();

        for (Map.Entry<String, String> entry : map.entrySet()) {
            dict = new Dictionary();
            dict.setCode(entry.getKey());
            dict.setName(entry.getValue());
            dictList.add(dict);
        }
        return dictList;
    }

    /**
     * 根据字典类型获取字典
     * @param Type
     * @return Map
     */
    @Override
    public Map getMapByCode(String Type) {
        BoundHashOperations boundHashOperations = redis.boundHashOps(Type);

        Map<String,String> map = boundHashOperations.entries();

        return map;
    }
}
