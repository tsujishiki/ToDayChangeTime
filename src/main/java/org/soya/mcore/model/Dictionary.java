package org.soya.mcore.model;

import java.io.Serializable;

/**
 * Created by Administrator on 2015/5/28.
 */
public class Dictionary implements Serializable {

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
}
