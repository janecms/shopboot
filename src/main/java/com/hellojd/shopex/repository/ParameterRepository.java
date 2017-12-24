package com.hellojd.shopex.repository;

import com.hellojd.shopex.entity.Parameter;
import com.hellojd.shopex.entity.ParameterGroup;

import java.util.List;
import java.util.Set;

public interface ParameterRepository {
    public abstract List<Parameter> findList(ParameterGroup paramParameterGroup, Set<Parameter> paramSet);
}
