package com.hellojd.shopex.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.hellojd.shopex.bean.BrandBean;
import com.hellojd.shopex.entity.Brand;
import com.hellojd.shopex.repository.BrandRepository;
import com.hellojd.shopex.service.BrandService;
import com.hellojd.shopex.util.BeanUtils;

/**
 * @author Administrator
 */
@Service
public class BrandServiceImpl extends ServiceImpl<BrandRepository,Brand> implements BrandService {
    public List<Brand> findList(List<Long> brandIds) {
        return this.baseMapper.selectBatchIds(brandIds);
    }

    @Override
    public void save(BrandBean brand) {
        this.baseMapper.insert(brand);
    }

    @Override
    public void update(BrandBean brandBean) {
        final Brand brandPo = this.baseMapper.selectById(brandBean.getId());
        BeanUtils.copyProperties(brandBean, brandPo,new String[] { "products", "productCategories", "promotions" });
        this.baseMapper.updateById(brandPo);
    }

}
