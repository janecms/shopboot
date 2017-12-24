package com.hellojd.shopex.service;

import com.hellojd.shopex.domain.Product;
import com.hellojd.shopex.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;
    public Product getProduct(String productId) {
        return productRepository.getProduct(productId);
    }


    public List<Product> getProductListByCategory(String categoryId) {
        return productRepository.getProductListByCategory(categoryId);
    }

    public List<Product> searchProductList(String keyword) {
        return productRepository.searchProductList(keyword);
    }
}
