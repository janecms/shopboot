package com.hellojd.shopex.service;

import com.hellojd.shopex.domain.Category;
import com.hellojd.shopex.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class CatalogService {
    @Autowired
    CategoryRepository categoryRepository;

    public List<Category> getCategoryList() {
        return categoryRepository.getCategoryList();
    }

    public Category getCategory(String categoryId) {
        return categoryRepository.getCategory(categoryId);
    }
}
