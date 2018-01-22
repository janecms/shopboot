package com.hellojd.shopex.bean;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.baomidou.mybatisplus.plugins.Page;

/**
 *
 * @author zhaoguoyu
 * @date 2018/1/19
 */
public class PageBean<T> extends Page<T> {
  public List<Integer> getSegments() {
    Integer pageNumber = this.getCurrent();//当前页
    Integer totalPages = this.getPages();
    Integer segmentCount = 5;
    /*
    boolean hasPrevious = this.hasPrevious();
    boolean hasNext = pageNumber.intValue() < totalPages.intValue();
    boolean isFirst = pageNumber.intValue() == 1;
    boolean isLast = pageNumber.equals(totalPages);
    int previousPageNumber = pageNumber.intValue() - 1;
    int nextPageNumber = pageNumber.intValue() + 1;
    int firstPageNumber = 1;
    int lastPageNumber = totalPages.intValue();
    */
    int begin = pageNumber.intValue() - (int) Math.floor((segmentCount.intValue() - 1) / 2.0D);
    int end = pageNumber.intValue() + (int) Math.ceil((segmentCount.intValue() - 1) / 2.0D);
    if (begin < 1) {
      begin = 1;
    }
    if (end > totalPages) {
      end = totalPages.intValue();
    }
    List<Integer> segments = new ArrayList<Integer>();
    for (int i = begin; i <= end; i++) {
      segments.add(Integer.valueOf(i));
    }
    return segments;
  }
}
