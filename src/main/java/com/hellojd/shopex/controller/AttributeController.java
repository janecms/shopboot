package com.hellojd.shopex.controller;

import com.baomidou.mybatisplus.plugins.Page;
import com.hellojd.shopex.bean.AttributeBean;
import com.hellojd.shopex.bean.PageWrapper;
import com.hellojd.shopex.bean.treeview.TreeViewBean;
import com.hellojd.shopex.common.Message;
import com.hellojd.shopex.entity.Attribute;
import com.hellojd.shopex.entity.BaseEntity;
import com.hellojd.shopex.service.AttributeService;
import com.hellojd.shopex.service.ProductCategoryService;
import com.hellojd.shopex.util.JsonUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Iterator;
import java.util.List;

/**
 *
 * @author zhaoguoyu
 * @date 2018/1/19
 */
@Controller
@RequestMapping("/attribute")
public class AttributeController  extends BaseController {
    private AttributeService attributeService;
    @Autowired
    ProductCategoryService productCategoryService;
    @RequestMapping(value={"/add"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
    public String add(ModelMap model)
    {
        model.addAttribute("attributeValuePropertyCount", Integer.valueOf(20));
        return "/admin/attribute/add";
    }

    @ModelAttribute
    public void common(ModelMap model){
        final List<TreeViewBean> productCategoryTreeView = this.productCategoryService.buildCategoryTree(null);
        final String productCategoryTreeViewJson= JsonUtils.toJson(productCategoryTreeView);
        model.addAttribute("productCategoryTreeViewJson", productCategoryTreeViewJson);
        model.addAttribute("attributeValuePropertyCount", Integer.valueOf(20));
    }


    @RequestMapping(value={"/save"}, method={org.springframework.web.bind.annotation.RequestMethod.POST})
    public String save(AttributeBean attribute, Long productCategoryId, RedirectAttributes redirectAttributes)
    {
        Iterator options = attribute.getOptions().iterator();
        while (options.hasNext())
        {
            String option = (String)options.next();
            if (!StringUtils.isEmpty(option)) {
                continue;
            }
            options.remove();
        }
        attribute.setProductCategory(this.productCategoryService.getProductCategoryById(productCategoryId));
        if (!validate(attribute, new Class[] { BaseEntity.Save.class })) {
            return "/admin/common/error";
        }
        if (attribute.getProductCategory().getAttributes().size() >= 20)
        {
            addAttribute(redirectAttributes, Message.error("admin.attribute.addCountNotAllowed", new Object[] { Integer.valueOf(20) }));
        }
        else
        {
            attribute.setPropertyIndex(null);
            this.attributeService.save(attribute);
            addAttribute(redirectAttributes, SUCCESS);
        }
        return "redirect:/attribute/";
    }

    @RequestMapping(value={"/{id}"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
    public String edit(@PathVariable("id") Long id, ModelMap model)
    {
        model.addAttribute("attribute", this.attributeService.getAttribute(id));
        return "/admin/attribute/edit";
    }

    @RequestMapping(value={"/update"}, method={org.springframework.web.bind.annotation.RequestMethod.POST})
    public String update(AttributeBean attribute, RedirectAttributes redirectAttributes)
    {
        Iterator<String> iterator = attribute.getOptions().iterator();
        while (iterator.hasNext())
        {
            String str = iterator.next();
            if (!StringUtils.isEmpty(str)) {
                continue;
            }
            iterator.remove();
        }
        if (!validate(attribute, new Class[0])) {
            return "/admin/common/error";
        }
        this.attributeService.update(attribute);
        addAttribute(redirectAttributes, SUCCESS);
        return "redirect:/attribute/";
    }

    @RequestMapping(value={"/"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
    public String list(Page<Attribute> page, ModelMap model)
    {
        final Page<Attribute> attributePage = this.attributeService.selectPage(page);
        model.addAttribute("page", PageWrapper.wrapper(attributePage));
        return "/admin/attribute/list";
    }
}
