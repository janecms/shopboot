package com.hellojd.shopex.common;

import lombok.Data;

import java.io.Serializable;

@Data
public class LogConfig  implements Serializable {
    String urlPattern;
    String operation;
}
