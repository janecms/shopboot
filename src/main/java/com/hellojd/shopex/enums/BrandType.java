package com.hellojd.shopex.enums;

import com.baomidou.mybatisplus.enums.IEnum;

import java.io.Serializable;

/**
 * @author Administrator
 */

public enum  BrandType implements IEnum {
    text, image;
    @Override
    public Serializable getValue() {
        return this.name();
    }
}
