package com.hellojd.shopex.repository;

import com.hellojd.shopex.domain.Category;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CategoryRepository {
    List<Category> getCategoryList();

    Category getCategory(String categoryId);
}
