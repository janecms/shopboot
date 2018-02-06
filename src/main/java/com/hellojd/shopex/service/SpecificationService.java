package com.hellojd.shopex.service;

import com.baomidou.mybatisplus.service.IService;
import com.hellojd.shopex.bean.SpecificationBean;
import com.hellojd.shopex.entity.Specification;
import com.hellojd.shopex.entity.SpecificationValue;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

/**
 * @author Administrator
 */
public interface SpecificationService extends IService<Specification> {
    SpecificationBean getSpecification(Long specificationId);
    void save(SpecificationBean specification);

    void update(SpecificationBean specification);

    List<SpecificationBean> findAll();
}
