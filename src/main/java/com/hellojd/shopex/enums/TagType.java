package com.hellojd.shopex.enums;

import java.io.Serializable;

import com.baomidou.mybatisplus.enums.IEnum;

/**
 *
 * @author zhaoguoyu
 * @date 2017/12/27
 */
public enum TagType implements IEnum {
  article(1,"article"),  product(0,"product")
  ;
  private int code;
  private String name;

  TagType(int code, String name) {
    this.code = code;
    this.name = name;
  }
  public int getCode() {
    return code;
  }

  public String getName() {
    return name;
  }

  @Override
  public Serializable getValue() {
    return code;
  }
}
