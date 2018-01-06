package com.hellojd.shopex.repository;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.hellojd.shopex.bean.Pager;
import com.hellojd.shopex.entity.Product;
import com.hellojd.shopex.entity.ProductCategory;
import org.apache.ibatis.annotations.Mapper;

import java.util.Date;
import java.util.List;
@Mapper
public interface ProductRepository extends BaseMapper<Product> {

}
