package com.hellojd.shopex.bean;

import org.springframework.beans.BeanUtils;

import com.baomidou.mybatisplus.plugins.Page;

/**
 *
 * @author zhaoguoyu
 * @date 2018/1/19
 */
public class PageWrapper {
  public static <T> PageBean<T> wrapper(Page<T> page){
    PageBean pageBean = new PageBean();
    BeanUtils.copyProperties(page,pageBean);
    return pageBean;
  }
}
