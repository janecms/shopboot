package com.hellojd.shopex.bean;

import java.util.List;
import java.util.Set;

public interface CategoryBean<T> {

    public Set<T> getChildren();
}
