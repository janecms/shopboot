package com.hellojd.shopex.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

import com.hellojd.shopex.service.ParameterGroupService;

/**
 *
 * @author zhaoguoyu
 * @date 2018/1/19
 */
@RequestMapping("/parameter_group")
public class ParameterGroupController extends BaseController {
  @Autowired
  ParameterGroupService parameterGroupService;
}
