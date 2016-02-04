package com.xiaodong.blog.controller;

import com.xiaodong.blog.model.CodeSet;
import com.xiaodong.blog.service.inter.CodeItemService;
import com.xiaodong.blog.utils.JsonResponseUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by xiaodong on 2016/2/2.
 */
@Controller
@RequestMapping("bs")
public class CodeItemController {

    private static final Logger LOG = LoggerFactory.getLogger(CodeItemController.class);

    @Autowired
    private CodeItemService codeItemService;

    @ResponseBody
    @RequestMapping("getProvinceMap")
    public String getProvinceMap(){
        try {
            return JsonResponseUtils.ok(codeItemService.getProvinceMap());
        } catch (Exception e) {
            LOG.error("获取省份编码失败，",e);
            return JsonResponseUtils.badResult(e.getMessage());
        }
    }

    @ResponseBody
    @RequestMapping("getCityMap")
    public String getCityCode(@Param("provinceCode") String provinceCode){
        try {
            return JsonResponseUtils.ok(codeItemService.getCityMap(provinceCode));
        } catch (Exception e){
            LOG.error("获取城市编码失败，provinceCode={}",provinceCode,e);
            return JsonResponseUtils.badResult(e.getMessage());
        }
    }

    @ResponseBody
    @RequestMapping("getCountyMap")
    public String getCountyCode(@Param("cityCode") String cityCode){
        try {
            return JsonResponseUtils.ok(codeItemService.getCountyMap(cityCode));
        } catch (Exception e) {
            LOG.error("获取区县编码失败，cityCode={}",cityCode,e);
            return JsonResponseUtils.badResult(e.getMessage());
        }
    }

    @ResponseBody
    @RequestMapping("getAreaList")
    public String getAreaList(){
        try {
            return JsonResponseUtils.ok(codeItemService.getAreaList());
        } catch (Exception e){
            LOG.error("获取地区list失败，",e);
            return JsonResponseUtils.badResult(e.getMessage());
        }
    }

    @ResponseBody
    @RequestMapping("getAreaMap")
    public String getAreaMap(){
        try {
            return JsonResponseUtils.ok(codeItemService.getAreaMap());
        } catch (Exception e){
            LOG.error("获取地区map失败，",e);
            return JsonResponseUtils.badResult(e.getMessage());
        }
    }

    @RequestMapping("codeSet")
    public String codeSet(HttpServletRequest request){
        try {
            request.setAttribute("codeSet",codeItemService.getAllCodeSet());
            return "codeSet";
        } catch (Exception e){
            LOG.error("获取codeSet失败，",e);
            return JsonResponseUtils.badResult(e.getMessage());
        }
    }

    @RequestMapping("saveCodeSet")
    public String saveCodeSet(CodeSet codeSet){
        try {
            codeItemService.saveCodeSet(codeSet);
            return "redirect:bs/codeSet.do";
        } catch (Exception e){
            LOG.error("保存codeSet失败，",e);
            return JsonResponseUtils.badResult(e.getMessage());
        }
    }

    @RequestMapping("updateCodeSet")
    public String updateCodeSet(CodeSet codeSet){
        try {
            codeItemService.saveCodeSet(codeSet);
            return "redirect:bs/codeSet.do";
        } catch (Exception e){
            LOG.error("保存codeSet失败，",e);
            return JsonResponseUtils.badResult(e.getMessage());
        }
    }

}
