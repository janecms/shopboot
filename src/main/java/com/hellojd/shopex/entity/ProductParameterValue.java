package com.hellojd.shopex.entity;

import com.baomidou.mybatisplus.annotations.TableName;
import lombok.Data;
/**
 * 商品参数
 * @author Administrator
 */
@TableName("product_parameter_value")
@Data
public class ProductParameterValue  {
    Long productId;
    Long parameterValueKey;
    String parameterValue;
}
