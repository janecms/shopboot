package com.hellojd.shopex.service;

import com.hellojd.shopex.entity.Member;

public interface MemberService {
    public abstract boolean usernameExists(String paramString);

    public abstract boolean usernameDisabled(String paramString);

    public abstract boolean emailExists(String paramString);

    public abstract boolean emailUnique(String paramString1, String paramString2);

    public abstract boolean isAuthenticated();

    public abstract Member getCurrent();

    public abstract String getCurrentUsername();
}
