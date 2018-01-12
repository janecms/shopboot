package com.hellojd.shopex.controller;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/")
public class IndexController {

    @RequestMapping("/")
    public String index(){
        return "index";
    }
    @RequestMapping("/html")
    public String  path(@RequestParam("filepath") String filepath){
        return filepath;
    }
}
