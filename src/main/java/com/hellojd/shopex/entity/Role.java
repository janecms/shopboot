package com.hellojd.shopex.entity;

import lombok.Data;

import java.util.List;
import java.util.Set;
@Data
public class Role extends BaseEntity {
    String name;
    Boolean isSystem;
    String description;
    List<String> authorities;
    Set<Admin> admins;
}
