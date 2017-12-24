package com.hellojd.shopex.controller;
import com.hellojd.shopex.entity.Product;
import com.hellojd.shopex.entity.ProductCategory;
import com.hellojd.shopex.bean.dt.DataTables;
import com.hellojd.shopex.service.ProductCategoryService;
import com.hellojd.shopex.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/category")
public class CategoryController {
    @Autowired
    ProductService productService;
    @Autowired
    ProductCategoryService productCategoryService;

    @GetMapping("/{productId}")
    public Product getProduct(@PathVariable("productId") String productId) {

        return productService.selectById(productId);
    }

    @GetMapping("/")
    public String categorylist(ModelMap modelMap){
        List<ProductCategory> categoryList = this.productCategoryService.getRootProductCategoryList();
        modelMap.put("categoryList",categoryList);
        return "/product/categorys";
    }

    @GetMapping("/{categoryId}/products")
    @ResponseBody
    public DataTables<Product> getProductListByCategory(@PathVariable("categoryId") Long categoryId,DataTables dt){
        ProductCategory productCategory = productCategoryService.selectById(categoryId);
        List<Product> productList = this.productService.getProductList(productCategory);
        DataTables<Product> productDataTables = new DataTables<>(productList);
        productDataTables.setDraw(dt.getDraw());
        return productDataTables;
    }
}
