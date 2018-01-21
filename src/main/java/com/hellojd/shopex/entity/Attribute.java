package com.hellojd.shopex.entity;
import com.baomidou.mybatisplus.annotations.TableName;
import lombok.Data;
import java.util.List;

/**
 * @author shopex
 */
@TableName("d_attribute")
@Data
public class Attribute extends OrderAbleEntity{
    String name;
    Integer propertyIndex;
    Integer productCategoryId;
}
