package com.hellojd.shopex.service.impl;

import java.util.List;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.hellojd.shopex.bean.BrandBean;
import com.hellojd.shopex.entity.Brand;
import com.hellojd.shopex.repository.BrandRepository;
import com.hellojd.shopex.service.BrandService;
import com.hellojd.shopex.util.BeanUtils;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Administrator
 */
@Service
public class BrandServiceImpl extends ServiceImpl<BrandRepository,Brand> implements BrandService {
    public List<Brand> findList(List<Long> brandIds) {
        return this.baseMapper.selectBatchIds(brandIds);
    }
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void save(BrandBean brand) {
        this.baseMapper.insert(brand);
    }
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void update(BrandBean brandBean) {
        final Brand brandPo = this.baseMapper.selectById(brandBean.getId());
        BeanUtils.copyProperties(brandBean, brandPo,new String[] { "products", "productCategories", "promotions" });
        this.baseMapper.updateById(brandPo);
    }

    @Override
    public List<Brand> findAll() {
        return baseMapper.selectList(new EntityWrapper<>(new Brand()));
    }

}
