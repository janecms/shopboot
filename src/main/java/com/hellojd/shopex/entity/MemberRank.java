package com.hellojd.shopex.entity;

import com.baomidou.mybatisplus.annotations.TableName;
import lombok.Data;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

import java.math.BigDecimal;
@TableName("d_member_rank")
@Data
public class MemberRank  extends BaseEntity{
    @NotEmpty
    @Length(max=200)
    String name;
    double scale;
    BigDecimal amount;
    Boolean isDefault;
    Boolean isSpecial;

}
