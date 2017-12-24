package com.hellojd.shopex.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;
@Data
public class SafeKey implements Serializable{
    String value;
    Date expire;
}
