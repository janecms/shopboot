package com.hellojd.shopex.controller;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.hellojd.shopex.bean.ProductCategoryBean;
import com.hellojd.shopex.bean.treeview.TreeViewBean;
import com.hellojd.shopex.common.Message;
import com.hellojd.shopex.entity.Brand;
import com.hellojd.shopex.entity.Product;
import com.hellojd.shopex.entity.ProductCategory;
import com.hellojd.shopex.bean.dt.DataTables;
import com.hellojd.shopex.service.BrandService;
import com.hellojd.shopex.service.ProductCategoryService;
import com.hellojd.shopex.service.ProductService;
import com.hellojd.shopex.util.JsonUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.*;

@Controller
@RequestMapping("/category")
@Slf4j
public class CategoryController extends BaseController{
    @Autowired
    ProductService productService;
    @Autowired
    ProductCategoryService productCategoryService;
    @Autowired
    private BrandService brandService;

    ObjectMapper objectMapper = new ObjectMapper();
    @GetMapping("/{productId}")
    public Product getProduct(@PathVariable("productId") String productId) {
        return null;
//        return productService.selectById(productId);
    }

    @GetMapping("/")
    public String treeGrid(ModelMap modelMap){
        Set<ProductCategoryBean> grid = this.productCategoryService.getRootProductCategoryList();
        modelMap.put("grid",grid);
        return "admin/category/grid";
    }

    @GetMapping("/{categoryId}/edit")
    public String edit(@PathVariable("categoryId") Long categoryId,ModelMap model) throws Exception {
        ProductCategoryBean productCategory = this.productCategoryService.getProductCategoryById(categoryId);
        assert productCategory !=null;
        List<Brand> allBrands = this.brandService.selectList(null);
        model.addAttribute("brands", allBrands);
        model.addAttribute("productCategory", productCategory);
        final List<TreeViewBean> productCategoryTreeView = this.productCategoryService.buildCategoryTree(productCategory.getParent());
        final String productCategoryTreeViewJson=JsonUtils.toJson(productCategoryTreeView);
        model.addAttribute("productCategoryTreeViewJson", productCategoryTreeViewJson);
        return "admin/category/edit";
    }

    @RequestMapping(value={"/update"}, method={org.springframework.web.bind.annotation.RequestMethod.POST})
    public String update(ProductCategoryBean productCategory, Long parentId, Long[] brandIds, RedirectAttributes
        redirectAttributes){
        productCategory.setParent(this.productCategoryService.getProductCategoryById(parentId));
         List<Long> brandIdList =null;
        if (brandIds==null){
           log.info("brandIds is null");
            brandIdList=Arrays.asList(brandIds);
        }
        if (!validate(productCategory, new Class[0])) {
            return this.ADMIN_COMMON_ERROR_PAGE;
        }
        if (productCategory.getParent() != null)
        {
            ProductCategoryBean localProductCategory = productCategory.getParent();
            if (localProductCategory.equals(productCategory)) {
                return ADMIN_COMMON_ERROR_PAGE;
            }
            Set localList =localProductCategory.getChildren();
            if ((localList != null) && (localList.contains(localProductCategory))) {
                return ADMIN_COMMON_ERROR_PAGE;
            }
        }
        this.productCategoryService.update(productCategory,brandIdList);
        this.addAttribute(redirectAttributes, SUCCESS);
        return "redirect:/category/";
    }
    @ResponseBody
    @RequestMapping("/treeview/{selectId}")
    public List<TreeViewBean> buildCategoryTree(@PathVariable("selectId") Long selectId){
        ProductCategoryBean productCategory = this.productCategoryService.getProductCategoryById(selectId);
        final List<TreeViewBean> treeView = this.productCategoryService.buildCategoryTree(productCategory);
        return treeView;
    }

    @GetMapping("/{categoryId}/products")
    @ResponseBody
    public DataTables<Product> getProductListByCategory(@PathVariable("categoryId") Long categoryId,DataTables dt){
//        ProductCategory productCategory = productCategoryService.selectById(categoryId);
//        List<Product> productList = this.productService.getProductList(productCategory);
//        DataTables<Product> productDataTables = new DataTables<>(productList);
//        productDataTables.setDraw(dt.getDraw());
//        return productDataTables;
        return null;
    }
}
