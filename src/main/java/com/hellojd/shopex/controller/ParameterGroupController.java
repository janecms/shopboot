package com.hellojd.shopex.controller;

import com.baomidou.mybatisplus.plugins.Page;
import com.hellojd.shopex.bean.PageBean;
import com.hellojd.shopex.bean.PageWrapper;
import com.hellojd.shopex.bean.ParameterBean;
import com.hellojd.shopex.bean.ParameterGroupBean;
import com.hellojd.shopex.bean.treeview.TreeViewBean;
import com.hellojd.shopex.entity.ParameterGroup;
import com.hellojd.shopex.entity.ProductCategory;
import com.hellojd.shopex.service.ParameterGroupService;
import com.hellojd.shopex.service.ProductCategoryService;
import com.hellojd.shopex.util.JsonUtils;
import org.apache.commons.lang.math.NumberUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Iterator;
import java.util.List;

/**
 * 商品参数
 *
 * @author zhaoguoyu
 * @date 2018/1/19
 */
@Controller
@RequestMapping("/parameter_group")
public class ParameterGroupController extends BaseController {
    @Autowired
    ParameterGroupService parameterGroupService;
    @Autowired
    private ProductCategoryService productCategoryService;

    private void populateCategoryTree(ProductCategory productCategory, ModelMap model) {
        final List<TreeViewBean> productCategoryTreeView = this.productCategoryService.buildCategoryTree(productCategory);
        final String productCategoryTreeViewJson = JsonUtils.toJson(productCategoryTreeView);
        model.addAttribute("productCategoryTreeViewJson", productCategoryTreeViewJson);
    }

    @RequestMapping(value = {"/add"}, method = {RequestMethod.GET})
    public String add(ModelMap model) {
        populateCategoryTree(null,model);
        return "/admin/parameter_group/add";
    }

    @RequestMapping(value = {"/save"}, method = {RequestMethod.POST})
    public String save(ParameterGroupBean parameterGroup, Long productCategoryId, RedirectAttributes redirectAttributes) {
        this.prev(parameterGroup);
        parameterGroup.setProductCategory(this.productCategoryService.getProductCategoryById(productCategoryId));
        if (!validate(parameterGroup, new Class[0])) {
            return "/admin/common/error";
        }
        this.parameterGroupService.save(parameterGroup);
        addAttribute(redirectAttributes, SUCCESS);
        return "redirect:/parameter_group/";
    }

    @RequestMapping(value = {"/{id}"}, method = {RequestMethod.GET})
    public String edit(@PathVariable("id") Long id, ModelMap model) {
        final ParameterGroupBean parameterGroup = this.parameterGroupService.getParameterGroup(id);
        model.addAttribute("parameterGroup", parameterGroup);
        populateCategoryTree(parameterGroup.getProductCategory(),model);
        return "/admin/parameter_group/edit";
    }

    @RequestMapping(value = {"/update"}, method = {RequestMethod.POST})
    public String update(ParameterGroupBean parameterGroup, Long productCategoryId, RedirectAttributes redirectAttributes) {
        this.prev(parameterGroup);
        parameterGroup.setProductCategory(this.productCategoryService.getProductCategoryById(productCategoryId));
        if (!validate(parameterGroup, new Class[0])) {
            return "/admin/common/error";
        }
        this.parameterGroupService.update(parameterGroup);
        addAttribute(redirectAttributes, SUCCESS);
        return "redirect:/parameter_group/";
    }

    /**
     * 保存，修改之前预处理
     * @param parameterGroup
     */
    private void prev(ParameterGroupBean parameterGroup) {
        Iterator<ParameterBean> iterator = parameterGroup.getParameters().iterator();
        while (iterator.hasNext()) {
            ParameterBean parameter = iterator.next();
            if ((parameter == null) || (parameter.getName() == null)) {
                iterator.remove();
            } else {
                parameter.setParameterGroup(parameterGroup);
            }
        }
    }

    @RequestMapping(value={"/"}, method={RequestMethod.GET})
    public String list(Page<ParameterGroupBean> page, ModelMap model)
    {
        final Page<ParameterGroupBean> attributePage = this.parameterGroupService.selectBeanPage(page,new ParameterGroupBean());
        model.addAttribute("page", PageWrapper.wrapper(attributePage));
        return "/admin/parameter_group/grid";
    }
}
