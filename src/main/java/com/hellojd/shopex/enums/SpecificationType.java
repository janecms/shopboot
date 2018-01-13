package com.hellojd.shopex.enums;

import com.baomidou.mybatisplus.enums.IEnum;

import java.io.Serializable;

public enum  SpecificationType implements IEnum {
    text(0),  image(1);
    ;

    SpecificationType(int code) {
        this.code = code;
    }

    private int code;
    @Override
    public Serializable getValue() {
        return this.code;
    }
}
