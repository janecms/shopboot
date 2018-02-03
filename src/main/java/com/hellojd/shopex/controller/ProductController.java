package com.hellojd.shopex.controller;

import com.baomidou.mybatisplus.plugins.Page;
import com.hellojd.shopex.bean.*;
import com.hellojd.shopex.bean.treeview.TreeViewBean;
import com.hellojd.shopex.entity.*;
import com.hellojd.shopex.enums.TagType;
import com.hellojd.shopex.service.*;
import com.hellojd.shopex.util.JsonUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Arrays;
import java.util.List;
import java.util.Set;
@Controller
@RequestMapping({"/product"})
public class ProductController extends BaseController {
    @Autowired
    private ProductService productService;
    @Autowired
    private ProductCategoryService productCategoryService;
    @Autowired
    private BrandService brandService;
    @Autowired
    private TagService tagService;
    @Autowired
    private MemberRankService memberRankService;
    @Autowired
    private SpecificationService specificationService;
    @Autowired
    private FileService fileService;

    @RequestMapping(value = {"/check_sn"}, method = {RequestMethod.GET})
    @ResponseBody
    public boolean checkSn(String previousSn, String sn) {
        if (StringUtils.isEmpty(sn)) {
            return false;
        }
        return this.productService.snUnique(previousSn, sn);
    }

    @RequestMapping(value = {"/parameter_groups"}, method = {RequestMethod.GET})
    @ResponseBody
    public Set<ParameterGroupBean> parameterGroups(Long id) {
        ProductCategoryBean productCategory = this.productCategoryService.getProductCategoryById(id);
        return productCategory.getParameterGroups();
    }

    @RequestMapping(value = {"/attributes"}, method = {RequestMethod.GET})
    @ResponseBody
    public Set<AttributeBean> attributes(Long id) {
        ProductCategoryBean productCategory = this.productCategoryService.getProductCategoryById(id);
        return productCategory.getAttributes();
    }

    @RequestMapping(value = {"/add"}, method = {RequestMethod.GET})
    public String add(ModelMap model) {
        final List<TreeViewBean> productCategoryTreeView = this.productCategoryService.buildCategoryTree(null);
        final String productCategoryTreeViewJson = JsonUtils.toJson(productCategoryTreeView);
        model.addAttribute("productCategoryTreeViewJson", productCategoryTreeViewJson);
        model.addAttribute("brands", this.brandService.findAll());
        model.addAttribute("tags", this.tagService.findList(TagType.product));
        model.addAttribute("memberRanks", this.memberRankService.findAll());
        model.addAttribute("specifications", this.specificationService.findAll());
        return "/admin/product/add";
    }

    @RequestMapping(value={"/{productId}"}, method={RequestMethod.GET})
    public String edit(@PathVariable("productId") Long id, ModelMap model)
    {
        final ProductBean product = this.productService.getProduct(id);
        final List<TreeViewBean> productCategoryTreeView = this.productCategoryService.buildCategoryTree(product.getProductCategory());
        final String productCategoryTreeViewJson = JsonUtils.toJson(productCategoryTreeView);
        model.addAttribute("productCategoryTreeViewJson", productCategoryTreeViewJson);
        model.addAttribute("brands", this.brandService.findAll());
        model.addAttribute("tags", this.tagService.findList(TagType.product));
        model.addAttribute("memberRanks", this.memberRankService.findAll());
        model.addAttribute("memberRanks", this.memberRankService.findAll());
        model.addAttribute("product", product);
        return "/admin/product/edit";
    }

    @RequestMapping(value={"/"}, method={RequestMethod.GET})
    public String list(ProductBean probe,Page<ProductBean> page, ModelMap model)
    {
        Page<ProductBean> productPage=this.productService.selectPage(page,probe);
        model.addAttribute("page", PageWrapper.wrapper(productPage));
        return "/admin/product/grid";
    }
}
