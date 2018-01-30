package com.hellojd.shopex.repository;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.hellojd.shopex.bean.Pager;
import com.hellojd.shopex.entity.Product;
import com.hellojd.shopex.entity.ProductCategory;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
@Repository
public interface ProductRepository extends BaseMapper<Product> {
}
