package com.hellojd.shopex.controller;

import com.baomidou.mybatisplus.plugins.Page;
import com.hellojd.shopex.bean.PageWrapper;
import com.hellojd.shopex.entity.BaseEntity;
import com.hellojd.shopex.entity.Tag;
import com.hellojd.shopex.enums.TagType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.hellojd.shopex.service.TagService;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 *
 * @author zhaoguoyu
 * @date 2018/1/19
 */
@Controller
@RequestMapping("/tag")
public class TagController extends BaseController {
  @Autowired
  TagService tagService;

  @RequestMapping(value={"/add"}, method={RequestMethod.GET})
  public String add(ModelMap model)
  {
    model.addAttribute("types", TagType.values());
    return "/admin/tag/add";
  }

  @RequestMapping(value={"/save"}, method={RequestMethod.POST})
  public String save(Tag tag, RedirectAttributes redirectAttributes)
  {
    if (!validate(tag, new Class[] { BaseEntity.Save.class })) {
      return "/admin/common/error";
    }
    this.tagService.save(tag);
    addAttribute(redirectAttributes, SUCCESS);
    return "redirect:/tag/";
  }

  @RequestMapping(value={"/{id}"}, method={RequestMethod.GET})
  public String edit(@PathVariable("id") Long id, ModelMap model)
  {
    model.addAttribute("types", TagType.values());
    model.addAttribute("tag", this.tagService.selectById(id));
    return "/admin/tag/edit";
  }

  @RequestMapping(value={"/update"}, method={org.springframework.web.bind.annotation.RequestMethod.POST})
  public String update(Tag tag, RedirectAttributes redirectAttributes)
  {
    if (!validate(tag, new Class[0])) {
      return "/admin/common/error";
    }
    this.tagService.update(tag);
    addAttribute(redirectAttributes, SUCCESS);
    return "redirect:/tag/";
  }

  @RequestMapping(value={"/"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
  public String list(Page<Tag> pageable, ModelMap model)
  {
    model.addAttribute("page", PageWrapper.wrapper(this.tagService.selectPage(pageable)));
    return "/admin/tag/grid";
  }
}
