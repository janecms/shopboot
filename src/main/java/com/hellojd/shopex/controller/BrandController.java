package com.hellojd.shopex.controller;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.hellojd.shopex.bean.BrandBean;
import com.hellojd.shopex.common.Message;
import com.hellojd.shopex.entity.Brand;
import com.hellojd.shopex.enums.BrandType;
import com.hellojd.shopex.service.BrandService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Arrays;

/**
 * @author Administrator
 */
@Controller
@RequestMapping("/brand")
public class BrandController  extends BaseController {
    @Autowired
    private BrandService brandService;
    @RequestMapping("/")
    public String list(Page<Brand> page,Brand queryObj,ModelMap model){
        Wrapper wrapper = new EntityWrapper(queryObj);
        final Page<Brand> brandPage = this.brandService.selectPage(page,wrapper);
        model.addAttribute("page", brandPage);
        return "product/brand_grid";
    }

    @RequestMapping(value={"/add"}, method={RequestMethod.GET})
    public String add(ModelMap model)
    {
        model.addAttribute("types", BrandType.values());
        return "product/brand_edit";
    }
    @RequestMapping(value={"/{id}"}, method={RequestMethod.GET})
    public String edit(@PathVariable Long id, ModelMap model)
    {
        model.addAttribute("types", BrandType.values());
        model.addAttribute("brand", this.brandService.selectById(id));
        return "product/brand_edit";
    }
    @RequestMapping(value={"/save"}, method={RequestMethod.POST})
    public String save(BrandBean brand, RedirectAttributes redirectAttributes)
    {
        if (!validate(brand, new Class[0])) {
            return ADMIN_COMMON_ERROR_PAGE;
        }
        if (brand.getType() == BrandType.text) {
            brand.setLogo(null);
        } else if (StringUtils.isEmpty(brand.getLogo())) {
            return ADMIN_COMMON_ERROR_PAGE;
        }
        brand.setProducts(null);
        brand.setProductCategories(null);
        brand.setPromotions(null);
        this.brandService.save(brand);
        addAttribute(redirectAttributes, SUCCESS);
        return "redirect:/brand/";
    }

    @RequestMapping(value={"/update"}, method={RequestMethod.POST})
    public String update(BrandBean brand, RedirectAttributes redirectAttributes)
    {
        if (!validate(brand, new Class[0])) {
            return this.ADMIN_COMMON_ERROR_PAGE;
        }
        if (brand.getType() == BrandType.text) {
            brand.setLogo(null);
        } else if (StringUtils.isEmpty(brand.getLogo())) {
            return ADMIN_COMMON_ERROR_PAGE;
        }
        this.brandService.update(brand);
        addAttribute(redirectAttributes, SUCCESS);
        return "redirect:list.jhtml";
    }
    @RequestMapping(value={"/"}, method={RequestMethod.DELETE})
    @ResponseBody
    public Message delete(Long[] ids)
    {
        this.brandService.deleteBatchIds(Arrays.asList(ids));
        return SUCCESS;
    }
}
