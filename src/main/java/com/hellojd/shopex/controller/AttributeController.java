package com.hellojd.shopex.controller;

import com.baomidou.mybatisplus.plugins.Page;
import com.hellojd.shopex.bean.AttributeBean;
import com.hellojd.shopex.bean.PageWrapper;
import com.hellojd.shopex.bean.treeview.TreeViewBean;
import com.hellojd.shopex.common.Message;
import com.hellojd.shopex.entity.Attribute;
import com.hellojd.shopex.entity.AttributeOption;
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
import java.util.Objects;

/**
 *
 * @author zhaoguoyu
 * @date 2018/1/19
 */
@Controller
@RequestMapping("/attribute")
public class AttributeController  extends BaseController {
    @Autowired
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
        this.prev(attribute);
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
            attribute.setPropertyIndex(0);
            this.attributeService.save(attribute);
            addAttribute(redirectAttributes, SUCCESS);
        }
        return "redirect:/attribute/";
    }

    @RequestMapping(value={"/{id}"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
    public String edit(@PathVariable("id") Long id, ModelMap model)
    {
        final AttributeBean attribute = this.attributeService.getAttribute(id);
        model.addAttribute("attribute", attribute);
        return "/admin/attribute/edit";
    }

    @RequestMapping(value={"/update"}, method={org.springframework.web.bind.annotation.RequestMethod.POST})
    public String update(AttributeBean attribute, RedirectAttributes redirectAttributes)
    {
        this.prev(attribute);
        if (!validate(attribute, new Class[0])) {
            return "/admin/common/error";
        }
        this.attributeService.update(attribute);
        addAttribute(redirectAttributes, SUCCESS);
        return "redirect:/attribute/";
    }

    /**
     * 预处理
     * @param attribute
     */
    private void prev(AttributeBean attribute) {
        Iterator<AttributeOption> iterator = attribute.getOptions().iterator();
        while (iterator.hasNext())
        {
            AttributeOption option = iterator.next();
            if (option==null ||option.getOptions()==null) {
                iterator.remove();
            }
        }
    }

    @RequestMapping(value={"/"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
    public String list(Page<AttributeBean> page, ModelMap model)
    {
        final Page<AttributeBean> attributePage = this.attributeService.selectBeanPage(page,new AttributeBean());
        model.addAttribute("page", PageWrapper.wrapper(attributePage));
        return "/admin/attribute/grid";
    }
}
