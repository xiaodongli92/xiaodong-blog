package com.xiaodong.blog.controller;

import com.xiaodong.blog.service.inter.CodeItemService;
import com.xiaodong.blog.utils.JsonResponseUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by xiaodong on 2016/2/2.
 */
@Controller
@RequestMapping("")
public class CodeItemController {

    private static final Logger LOG = LoggerFactory.getLogger(CodeItemController.class);

    @Autowired
    private CodeItemService codeItemService;

    @ResponseBody
    @RequestMapping("getProvinceMap")
    public String getProvinceMap(){
        return JsonResponseUtils.ok(codeItemService.getProvinceMap());
    }
}
