package com.hellojd.shopex.entity;

import lombok.Data;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Data
public class Review extends BaseEntity {
    @NotNull
    @Min(1L)
    @Max(5L)
    private Integer score;
    @NotEmpty
    @Length(max=200)
    private String content;
    private Boolean isShow;
    private String ip;
    private Member member;
    private Product product;
}
