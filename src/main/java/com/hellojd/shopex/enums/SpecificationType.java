package com.hellojd.shopex.enums;

import com.baomidou.mybatisplus.enums.IEnum;

import java.io.Serializable;

public enum  SpecificationType implements IEnum {
    text(0,"文本"),  image(1,"图片");
    ;

    SpecificationType(int code,String name) {
        this.name=name;
        this.code = code;
    }

    private int code;
    String name;
    @Override
    public Serializable getValue() {
        return this.code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
