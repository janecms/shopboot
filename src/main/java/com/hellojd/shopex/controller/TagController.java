package com.hellojd.shopex.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

import com.hellojd.shopex.service.TagService;

/**
 *
 * @author zhaoguoyu
 * @date 2018/1/19
 */
@RequestMapping("/tag")
public class TagController extends BaseController {
  @Autowired
  TagService tagService;
}
