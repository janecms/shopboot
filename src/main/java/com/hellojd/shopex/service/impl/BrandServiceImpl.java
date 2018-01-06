package com.hellojd.shopex.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.hellojd.shopex.entity.Brand;
import com.hellojd.shopex.repository.BrandRepository;
import com.hellojd.shopex.service.BrandService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Administrator
 */
@Service
public class BrandServiceImpl extends ServiceImpl<BrandRepository,Brand> implements BrandService {
    public List<Brand> findList(List<Long> brandIds) {
        return this.baseMapper.selectBatchIds(brandIds);
    }
}
