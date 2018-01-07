package com.hellojd.shopex.controller;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.hellojd.shopex.bean.treeview.TreeViewBean;
import com.hellojd.shopex.entity.Brand;
import com.hellojd.shopex.entity.Product;
import com.hellojd.shopex.entity.ProductCategory;
import com.hellojd.shopex.bean.dt.DataTables;
import com.hellojd.shopex.service.BrandService;
import com.hellojd.shopex.service.ProductCategoryService;
import com.hellojd.shopex.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Controller
@RequestMapping("/category")
public class CategoryController {
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
        Set<ProductCategory> grid = this.productCategoryService.getRootProductCategoryList();
        modelMap.put("grid",grid);
        return "product/categorys_grid";
    }

    @GetMapping("/{categoryId}/edit")
    public String edit(@PathVariable("categoryId") Long categoryId,ModelMap model) throws Exception {
        ProductCategory productCategory = this.productCategoryService.getProductCategoryById(categoryId);
        assert productCategory !=null;
        List<Brand> allBrands = this.brandService.selectList(null);
        model.addAttribute("brands", allBrands);
        model.addAttribute("productCategory", productCategory);
        final List<TreeViewBean> productCategoryTreeView = this.productCategoryService.buildCategoryTree(productCategory.getParent());
        objectMapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
        final String productCategoryTreeViewJson = objectMapper.writeValueAsString(productCategoryTreeView);
        model.addAttribute("productCategoryTreeViewJson", productCategoryTreeViewJson);
        return "product/category_edit";
    }
    @RequestMapping(value={"/update"}, method={org.springframework.web.bind.annotation.RequestMethod.POST})
    public String update(ProductCategory productCategory, Long parentId, List<Long> brandIds, RedirectAttributes redirectAttributes){
        productCategory.setBrands(new HashSet(this.brandService.selectBatchIds(brandIds)));
        return null;
    }
    @ResponseBody
    @RequestMapping("/treeview/{selectId}")
    public List<TreeViewBean> buildCategoryTree(@PathVariable("selectId") Long selectId){
        ProductCategory productCategory = this.productCategoryService.getProductCategoryById(selectId);
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
