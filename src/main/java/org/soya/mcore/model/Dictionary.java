package org.soya.mcore.model;

import java.io.Serializable;

/**
 * 字典
 * Created by Administrator on 2015/5/28.
 */
public class Dictionary implements Serializable,Comparable<Dictionary> {

    private String code;
    private String name;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public int compareTo(Dictionary dict) {
        int flag = -1 ;
        if("OTHER".equals(this.getCode())){
            flag = 1;
        }else if("OTHER".equals(dict.getCode())){
            flag = -1;
        }else{
            if(this.getCode().compareTo(dict.getCode()) > 0)
                flag = 1;
        }

        return flag;
    }
}
