package com.hellojd.shopex.controller;

import com.baomidou.mybatisplus.plugins.Page;
import com.hellojd.shopex.bean.*;
import com.hellojd.shopex.bean.treeview.TreeViewBean;
import com.hellojd.shopex.common.Setting;
import com.hellojd.shopex.common.ShopxxSettings;
import com.hellojd.shopex.entity.*;
import com.hellojd.shopex.enums.TagType;
import com.hellojd.shopex.service.*;
import com.hellojd.shopex.util.JsonUtils;
import com.hellojd.shopex.util.SettingUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.util.*;

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
    public String parameterGroups(Long id,ModelMap model) {
        ProductCategoryBean productCategory = this.productCategoryService.getProductCategoryById(id);
        final Set<ParameterGroupBean> parameterGroups = productCategory.getParameterGroups();
        model.addAttribute("parameterGroups",parameterGroups);
        return "/admin/product/parameter_panel";
    }

    @RequestMapping(value = {"/attributes"}, method = {RequestMethod.GET})
    public String attributes(Long id,ModelMap model) {
        ProductCategoryBean productCategory = this.productCategoryService.getProductCategoryById(id);
        final Set<AttributeBean> attributes = productCategory.getAttributes();
        model.addAttribute("attributes",attributes);
        return "/admin/product/attribute_panel";
    }

    @RequestMapping(value = {"/add"}, method = {RequestMethod.GET})
    public String add(ModelMap model) {
        final List<TreeViewBean> productCategoryTreeView = this.productCategoryService.buildCategoryTree(null);
        final String productCategoryTreeViewJson = JsonUtils.toJson(productCategoryTreeView);
        model.addAttribute("productCategoryTreeViewJson", productCategoryTreeViewJson);
        model.addAttribute("brands", this.brandService.findAll());
        model.addAttribute("tags", this.tagService.findList(TagType.product));
        model.addAttribute("memberRanks", this.memberRankService.findAll());
        return "/admin/product/add";
    }

    @RequestMapping(value = {"/save"}, method = {org.springframework.web.bind.annotation.RequestMethod.POST})
    public String save(ProductBean product, Long productCategoryId, Long brandId, Long[] tagIds, HttpServletRequest request, RedirectAttributes redirectAttributes) {
        if (this.validate(product, new Class[0])) {
            return this.ADMIN_COMMON_ERROR_PAGE;
        }
        if ((StringUtils.isNotEmpty(product.getSn())) && (this.productService.snExists(product.getSn()))) {
            return this.ADMIN_COMMON_ERROR_PAGE;
        }
        if (product.getMarketPrice() == null) {
            product.setMarketPrice(product.getPrice());
        }
        if (product.getPoint() == null) {
            long point = transforPoint(product.getPrice());
            product.setPoint(Long.valueOf(point));
        }
        product.setProductCategory(this.productCategoryService.getProductCategoryById(productCategoryId));
        product.setBrand(this.brandService.selectById(brandId));
        product.setFullName(null);
        product.setAllocatedStock(Integer.valueOf(0));
        product.setScore(Float.valueOf(0.0F));
        product.setTotalScore(Long.valueOf(0L));
        product.setScoreCount(Long.valueOf(0L));
        product.setHits(Long.valueOf(0L));
        product.setWeekHits(Long.valueOf(0L));
        product.setMonthHits(Long.valueOf(0L));
        product.setSales(Long.valueOf(0L));
        product.setWeekSales(Long.valueOf(0L));
        product.setMonthSales(Long.valueOf(0L));
        product.setWeekHitsDate(new Date());
        product.setMonthHitsDate(new Date());
        product.setWeekSalesDate(new Date());
        product.setMonthSalesDate(new Date());

        Iterator<ParameterGroupBean> parameterGroupIter = product.getProductCategory().getParameterGroups().iterator();
        while (parameterGroupIter.hasNext()) {
            ParameterGroupBean parameterGroup = parameterGroupIter.next();
            Iterator<ParameterBean> parameterIter = parameterGroup.getParameters().iterator();
            while (parameterIter.hasNext()) {
                ParameterBean parameterBean = parameterIter.next();
                String parameterValue = request.getParameter("parameter_" + parameterBean.getId());
                if (StringUtils.isNotEmpty((String) parameterValue)) {
                    product.getParameterValue().put(parameterBean, parameterValue);
                } else {
                    product.getParameterValue().remove(parameterBean);
                }
            }
        }
        Iterator<AttributeBean> attributeBeanIterator = product.getProductCategory().getAttributes().iterator();
        while (attributeBeanIterator.hasNext()) {
            final AttributeBean attributeBean = attributeBeanIterator.next();
            String attributeValue = request.getParameter("attribute_" + attributeBean.getId());
            if (StringUtils.isNotEmpty((String) attributeValue)) {
                product.getAttributeValueMap().put(attributeBean, attributeValue);
            } else {
                product.getAttributeValueMap().put(attributeBean, null);
            }
        }
        return  "redirect:/product/";
}

    @RequestMapping(value = {"/{productId}"}, method = {RequestMethod.GET})
    public String edit(@PathVariable("productId") Long id, ModelMap model) {
        final ProductBean product = this.productService.getProduct(id);
        final List<TreeViewBean> productCategoryTreeView = this.productCategoryService.buildCategoryTree(product.getProductCategory());
        final String productCategoryTreeViewJson = JsonUtils.toJson(productCategoryTreeView);
        model.addAttribute("productCategoryTreeViewJson", productCategoryTreeViewJson);
        model.addAttribute("brands", this.brandService.findAll());
        model.addAttribute("tags", this.tagService.findList(TagType.product));
        model.addAttribute("memberRanks", this.memberRankService.findAll());
        model.addAttribute("product", product);
        return "/admin/product/edit";
    }

    @RequestMapping(value = {"/"}, method = {RequestMethod.GET})
    public String list(ProductBean probe, Page<ProductBean> page, ModelMap model) {
        Page<ProductBean> productPage = this.productService.selectPage(page, probe);
        model.addAttribute("page", PageWrapper.wrapper(productPage));
        return "/admin/product/grid";
    }

    private long transforPoint(BigDecimal decimal) {
        if (decimal==null){return 0L;}
        ShopxxSettings settings = SettingUtils.get();
        Double defaultPointScale = settings.getDefaultPointScale();
        return decimal.multiply(new BigDecimal(defaultPointScale.toString())).longValue();
    }
}
