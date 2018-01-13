package com.hellojd.shopex.entity;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;
/**
 * @author Administrator
 */
@Data
public class Promotion extends OrderAbleEntity{
    String name;
    String title;
    Date beginDate;
    Date endDate;
    BigDecimal startPrice;
    BigDecimal endPrice;
}
