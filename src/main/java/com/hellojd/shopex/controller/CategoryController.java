package com.hellojd.shopex.controller;

import com.hellojd.shopex.domain.DataTables;
import com.hellojd.shopex.domain.Category;
import com.hellojd.shopex.domain.Product;
import com.hellojd.shopex.service.CatalogService;
import com.hellojd.shopex.service.CatalogService;
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
    CatalogService catalogService;

    @GetMapping("/{productId}")
    public Product getProduct(@PathVariable("productId") String productId) {
        return productService.getProduct(productId);
    }

    @GetMapping("/")
    public String categorylist(ModelMap modelMap){
        List<Category> categoryList = this.catalogService.getCategoryList();
        modelMap.put("categoryList",categoryList);
        return "/product/categorys";
    }

    @GetMapping("/{categoryId}/products")
    @ResponseBody
    public DataTables<Product> getProductListByCategory(@PathVariable("categoryId") String categoryId,DataTables dt){
        List<Product> productList = this.productService.getProductListByCategory(categoryId);
        DataTables<Product> productDataTables = new DataTables<>(productList);
        productDataTables.setDraw(dt.getDraw());
        return productDataTables;
    }
}
