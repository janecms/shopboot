package com.hellojd.shopex.repository;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.hellojd.shopex.bean.Pager;
import com.hellojd.shopex.bean.ProductBean;
import com.hellojd.shopex.entity.Product;
import com.hellojd.shopex.entity.ProductCategory;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
@Repository
public interface ProductRepository extends BaseMapper<Product> {
    ProductBean getProduct(Long id);
    public List<ProductBean> selectPage(RowBounds rowBounds,ProductBean probe);
}
