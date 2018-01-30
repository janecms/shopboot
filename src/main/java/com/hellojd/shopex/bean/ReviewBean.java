package com.hellojd.shopex.bean;

import com.hellojd.shopex.entity.Product;
import com.hellojd.shopex.entity.Review;
import lombok.Data;

@Data
public class ReviewBean extends Review {
    private Product product;
}
