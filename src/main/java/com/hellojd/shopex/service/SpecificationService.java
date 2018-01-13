package com.hellojd.shopex.service;

import com.hellojd.shopex.bean.SpecificationBean;
import com.hellojd.shopex.entity.Specification;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

public interface SpecificationService {

    void save(SpecificationBean specification);

    void update(SpecificationBean specification);
}
