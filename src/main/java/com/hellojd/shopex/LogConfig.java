package com.hellojd.shopex;

import lombok.Data;

import java.io.Serializable;

@Data
public class LogConfig  implements Serializable {
    String urlPattern;
    String operation;
}
