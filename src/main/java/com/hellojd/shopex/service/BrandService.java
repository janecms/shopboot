package com.hellojd.shopex.service;

import com.baomidou.mybatisplus.service.IService;
import com.hellojd.shopex.bean.BrandBean;
import com.hellojd.shopex.entity.Brand;

import java.util.List;

public interface BrandService extends IService<Brand> {
    public void save(BrandBean brand);
    public void update(BrandBean brand);
    public List<Brand> findAll();
}
