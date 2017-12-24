package com.hellojd.shopex.entity;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Pattern;
import java.util.Date;
import java.util.Set;
@Data
public class Admin  extends BaseEntity {
    Set<Role> roles;
    String username;
    @Pattern(regexp="^[^\\s&\"<>]+$")
    @Length(min=4, max=20)
    String password;

    String email;

    String name;

    String department;

    Boolean isEnabled;

    Boolean isLocked;

    Integer loginFailureCount;

    Date lockedDate;

    Date loginDate;

    String loginIp;
}
