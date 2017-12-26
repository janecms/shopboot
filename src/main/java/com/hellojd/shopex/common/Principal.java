package com.hellojd.shopex.common;

import lombok.Data;

import java.io.Serializable;
@Data
public class Principal  implements Serializable {
    Long id;
    String username;
}
