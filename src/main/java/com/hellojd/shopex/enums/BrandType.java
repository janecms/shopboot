package com.hellojd.shopex.enums;

import com.baomidou.mybatisplus.enums.IEnum;

import java.io.Serializable;

/**
 * @author Administrator
 */

public enum  BrandType implements IEnum {
    text(0,"text"), image(1,"image");
    int code;
    String name;

    BrandType(int code, String name) {
        this.code = code;
        this.name = name;
    }

    @Override
    public Serializable getValue() {
        return this.code;
    }

    public int getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

}
