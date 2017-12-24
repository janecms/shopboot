package com.hellojd.shopex.entity;

import com.hellojd.shopex.interceptor.MemberInterceptor;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;
@Data
public class Member {
    public static final String PRINCIPAL_ATTRIBUTE_NAME = MemberInterceptor.class.getName() + ".PRINCIPAL";
    public static final String USERNAME_COOKIE_NAME = "username";
    public static final int ATTRIBUTE_VALUE_PROPERTY_COUNT = 10;
    public static final String ATTRIBUTE_VALUE_PROPERTY_NAME_PREFIX = "attributeValue";
    public static final Integer MAX_FAVORITE_COUNT = Integer.valueOf(10);
    public enum Gender
    {
        male,  female;
    }
    String username;
    String password;
    String email;
    Long point;
    BigDecimal amount;
    BigDecimal balance;
    Boolean isEnabled;
    Boolean isLocked;
    Integer loginFailureCount;
    Date lockedDate;
    String registerIp;
    String loginIp;
    Date loginDate;
    String name;
    Gender gender;
    Date birth;
    String address;
    String zipCode;
    String phone;
    String mobile;
    SafeKey safeKey;
    MemberRank memberRank;
}
