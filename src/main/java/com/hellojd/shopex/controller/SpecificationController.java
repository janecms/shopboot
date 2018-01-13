package com.hellojd.shopex.controller;

import com.hellojd.shopex.bean.SpecificationBean;
import com.hellojd.shopex.entity.Specification;
import com.hellojd.shopex.entity.SpecificationValue;
import com.hellojd.shopex.enums.SpecificationType;
import com.hellojd.shopex.service.SpecificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Iterator;

/**
 * @author Administrator
 */
@Controller
@RequestMapping("/specification")
public class SpecificationController extends BaseController {
    @Autowired
    private SpecificationService specificationService;

    @RequestMapping(value = {"/add"}, method = {RequestMethod.GET})
    public String add(ModelMap model) {
        model.addAttribute("types", SpecificationType.values());
        return "product/specification_edit";
    }

    @RequestMapping(value = {"/save"}, method = {RequestMethod.POST})
    public String save(SpecificationBean specification, RedirectAttributes redirectAttributes) {
        Iterator values = specification.getSpecificationValues().iterator();
        while (values.hasNext()) {
            SpecificationValue localSpecificationValue = (SpecificationValue) values.next();
            if ((localSpecificationValue == null) || (localSpecificationValue.getName() == null)) {
                values.remove();
            } else {
                if (specification.getType() == SpecificationType.text) {
                    localSpecificationValue.setImage(null);
                }
            }
        }
        if (!validate(specification, new Class[0])) {
            return ADMIN_COMMON_ERROR_PAGE;
        }
        specification.setProducts(null);
        this.specificationService.save(specification);
        addAttribute(redirectAttributes, SUCCESS);
        return "redirect:product/specification_grid";
    }

    @RequestMapping(value = {"/update"}, method = {RequestMethod.POST})
    public String update(SpecificationBean specification, RedirectAttributes redirectAttributes) {
        Iterator iterator = specification.getSpecificationValues().iterator();
        while (iterator.hasNext()) {
            SpecificationValue specificationValue = (SpecificationValue) iterator.next();
            if ((specificationValue == null) || (specificationValue.getName() == null)) {
                iterator.remove();
            } else {
                if (specification.getType() == SpecificationType.text) {
                    specificationValue.setImage(null);
                }
            }
        }
        if (!validate(specification, new Class[0])) {
            return ADMIN_COMMON_ERROR_PAGE;
        }
        this.specificationService.update(specification);
        addAttribute(redirectAttributes, SUCCESS);
        return "redirect:product/specification_grid";
    }
}