package com.hellojd.shopex.controller;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.hellojd.shopex.bean.PageWrapper;
import com.hellojd.shopex.bean.SpecificationBean;
import com.hellojd.shopex.entity.Brand;
import com.hellojd.shopex.entity.Specification;
import com.hellojd.shopex.entity.SpecificationValue;
import com.hellojd.shopex.enums.SpecificationType;
import com.hellojd.shopex.service.SpecificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

/**
 * 规格
 * @author Administrator
 */
@Controller
@RequestMapping("/specification")
public class SpecificationController extends BaseController {
    @Autowired
    private SpecificationService specificationService;

    @RequestMapping("/")
    public String list(Page<Specification> page, Specification queryObj, ModelMap model) {
        Wrapper wrapper = new EntityWrapper(queryObj);
        final Page<Brand> specificationPage = this.specificationService.selectPage(page, wrapper);
        model.addAttribute("page", PageWrapper.wrapper(specificationPage));
        return "admin/specification/grid";
    }

    @RequestMapping(value = {"/add"}, method = {RequestMethod.GET})
    public String add(ModelMap model) {
        model.addAttribute("types", SpecificationType.values());
        return "admin/specification/edit";
    }

    @RequestMapping(value = {"/{id}"}, method = {RequestMethod.GET})
    public String edit(@PathVariable Long id, ModelMap model) {
        final SpecificationBean specification = this.specificationService.getSpecification(id);
        model.addAttribute("types", SpecificationType.values());
        model.put("specification", specification);
        return "admin/specification/edit";
    }

    @RequestMapping(value = {"/save"}, method = {RequestMethod.POST})
    public String save(SpecificationBean specification, RedirectAttributes redirectAttributes) {
        Iterator<SpecificationValue> values = specification.getSpecificationValues().iterator();
        while (values.hasNext()) {
            SpecificationValue value = values.next();
            if ((value == null) || (value.getName() == null)) {
                values.remove();
            } else {
                if (specification.getType() == SpecificationType.text) {
                    value.setImage(null);
                }
            }
        }
        if (!validate(specification, new Class[0])) {
            return ADMIN_COMMON_ERROR_PAGE;
        }
        if (specification.getId() != null) {
            this.specificationService.update(specification);
        } else {
            this.specificationService.save(specification);
        }
        addAttribute(redirectAttributes, SUCCESS);
        return "redirect:/specification/";
    }

}

