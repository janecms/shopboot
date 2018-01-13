package com.hellojd.shopex.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.hellojd.shopex.bean.RefBean;
import com.hellojd.shopex.bean.SpecificationBean;
import com.hellojd.shopex.entity.Specification;
import com.hellojd.shopex.entity.SpecificationValue;
import com.hellojd.shopex.repository.SpecificationRepository;
import com.hellojd.shopex.repository.SpecificationValueRepository;
import com.hellojd.shopex.service.SpecificationService;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

/**
 * @author Administrator
 */
@Service
@Slf4j
public class SpecificationServiceImpl extends ShopBaseServiceImpl<SpecificationRepository, Specification> implements SpecificationService {
    @Autowired
    SpecificationValueRepository specificationValueRepository;

    @Override
    public void save(SpecificationBean specification) {
        this.baseMapper.insert(specification);
        final Set<SpecificationValue> specificationValues = specification.getSpecificationValues();
        final Long id = specification.getId();
        if (CollectionUtils.isNotEmpty(specificationValues)) {
            for (SpecificationValue sv : specificationValues) {
                sv.setSpecification(id);
                this.specificationValueRepository.insert(sv);
            }
        } else {
            log.warn("没有为规格设置子项列表 {}", id);
        }
    }

    @Override
    public void update(SpecificationBean specification) {
        final SpecificationBean po = this.baseMapper.getSpecification(specification.getId());
        this.baseMapper.updateById(specification);
        final Set<SpecificationValue> requests = specification.getSpecificationValues();
        Set<SpecificationValue> specificationValues = po.getSpecificationValues();
        this.updateRefResult(requests,specificationValues,new RefResultUpdater<SpecificationValue>(){
            @Override
            public void execute(RefResult<SpecificationValue> refResult) {
                refResult.savingSet.forEach(item ->{
                    specificationValueRepository.insert(item);
                });
                refResult.deletingSet.forEach(item ->{
                    specificationValueRepository.delete(new EntityWrapper<>(item));
                });
            }
        });
    }
}