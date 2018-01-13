package com.hellojd.shopex.controller;

import com.hellojd.shopex.service.LogService;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.Resource;
import java.awt.print.Pageable;

@Controller
@RequestMapping({"/admin/log"})
public class LogController {
    @Resource
    private LogService logService;
}
