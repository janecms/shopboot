package com.hellojd.shopex.controller;

import com.hellojd.shopex.service.*;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@RequestMapping({"/product"})
public class ProductController extends BaseController {
    @Autowired
    private ProductService productService;
    @Autowired
    private ProductCategoryService productCategoryService;
    @Autowired
    private BrandService brandService;
    @Autowired
    private TagService tagService;
    @Autowired
    private SpecificationService specificationService;
    @Autowired
    private FileService fileService;

    @ResponseBody
    public boolean checkSn(String previousSn, String sn)
    {
        if (StringUtils.isEmpty(sn))
            return false;
        return this.productService.snUnique(previousSn, sn);
    }
}
