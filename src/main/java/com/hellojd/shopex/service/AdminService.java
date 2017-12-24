package com.hellojd.shopex.service;

import com.hellojd.shopex.entity.Admin;

import java.util.List;

public interface AdminService {
    public abstract boolean usernameExists(String paramString);

    public abstract Admin findByUsername(String paramString);

    public abstract List<String> findAuthorities(Long paramLong);

    public abstract boolean isAuthenticated();

    public abstract Admin getCurrent();

    public abstract String getCurrentUsername();
}
