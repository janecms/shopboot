package com.hellojd.shopex.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.hellojd.shopex.bean.AttributeBean;
import com.hellojd.shopex.entity.Attribute;
import com.hellojd.shopex.entity.AttributeOption;
import com.hellojd.shopex.entity.Brand;
import com.hellojd.shopex.entity.SpecificationValue;
import com.hellojd.shopex.repository.AttributeOptionRepository;
import com.hellojd.shopex.repository.AttributeRepository;
import com.hellojd.shopex.service.AttributeService;
import com.hellojd.shopex.service.ProductCategoryService;
import com.hellojd.shopex.util.BeanUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.List;

/**
 * 商品属性
 * @author Administrator
 */
@Service
@Slf4j
public class AttributeServiceImpl extends ShopBaseServiceImpl<AttributeRepository,Attribute> implements AttributeService{
    @Autowired
    private AttributeOptionRepository attributeOptionRepository;
    @Autowired
    private ProductCategoryService productCategoryService;

    @Override
    public AttributeBean getAttribute(Long attributeId) {
        AttributeBean attribute = this.baseMapper.getAttribute(attributeId);
        Long productCategoryId = attribute.getProductCategoryId();
        attribute.setProductCategory(productCategoryService.getProductCategoryById(productCategoryId));
        return attribute;
    }
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void save(AttributeBean attribute) {
        this.baseMapper.insert(attribute);
        final List<AttributeOption> options = attribute.getOptions();
        final Long id = attribute.getId();
        if (CollectionUtils.isNotEmpty(options)) {
            for (AttributeOption option : options) {
                this.attributeOptionRepository.insert(option);
            }
        } else {
            log.warn("没有为规格设置子项列表 {}", id);
        }
    }
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void update(AttributeBean attribute) {
        final AttributeBean attributePO = this.baseMapper.getAttribute(attribute.getId());
        BeanUtils.copyProperties(attribute, attributePO,new String[] { "propertyIndex", "productCategory","options" });
        this.baseMapper.updateById(attributePO);
        final List<AttributeOption> requests = attribute.getOptions();
        List<AttributeOption> options = attributePO.getOptions();
        this.updateRefResult(requests,options,new RefResultUpdater<AttributeOption>(){
            @Override
            public void execute(RefResult<AttributeOption> refResult) {
                refResult.deletingSet.forEach(option ->{
                    attributeOptionRepository.delete(new EntityWrapper<>(option));
                });
                refResult.savingSet.forEach(option ->{
                    attributeOptionRepository.insert(option);
                });
            }
        });
    }

    @Override
    public Page<AttributeBean> selectBeanPage(Page<AttributeBean> page, AttributeBean attribute) {
        final List<AttributeBean> records = this.baseMapper.selectPage(page, attribute);
        page.setRecords(records);
        return page;
    }
}
