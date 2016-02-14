package com.xiaodong.blog.controller;

import com.xiaodong.blog.model.CodeItem;
import com.xiaodong.blog.model.CodeSet;
import com.xiaodong.blog.service.inter.CodeItemService;
import com.xiaodong.blog.utils.JsonResponseUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

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
            List<CodeSet> codeSets = codeItemService.getAllCodeSet();
            LOG.info("codeSets = {}",codeSets);
            request.setAttribute("codeSets",codeSets);
            return "codeSet";
        } catch (Exception e){
            LOG.error("获取codeSet失败，",e);
            request.setAttribute("errMsg",e.getMessage());
            return "error";
        }
    }

    @ResponseBody
    @RequestMapping("saveCodeSet")
    public String saveCodeSet(CodeSet codeSet){
        try {
            LOG.info("save_code_set:{}",codeSet);
            String errMsg = codeItemService.saveCodeSet(codeSet);
            if (errMsg==null) {
                return JsonResponseUtils.ok();
            }
            return JsonResponseUtils.badResult(errMsg);
        } catch (Exception e){
            LOG.error("保存codeSet失败，",e);
            return JsonResponseUtils.badResult(e.getMessage());
        }
    }

    @ResponseBody
    @RequestMapping("deleteCodeSet")
    public String deleteCodeSet(@Param("id") Long id){
        try {
            if (id==null || id.longValue()<1){
                return JsonResponseUtils.badResult("id无效");
            }
            codeItemService.deleteCodeSet(id.longValue());
            return JsonResponseUtils.ok();
        } catch (Exception e){
            LOG.error("删除codeSet失败，",e);
            return JsonResponseUtils.badResult(e.getMessage());
        }
    }

    @RequestMapping("codeItem")
    public String codeItem(@Param("id")Long id,HttpServletRequest request){
        try {
            if (id == null){
                return JsonResponseUtils.badResult("字符集无效");
            }
            CodeSet codeSet = codeItemService.get(id);
            List<CodeItem> codeItems = codeItemService.getCodeItemByCodeSet(codeSet.getCodeSetValue());
            request.setAttribute("codeItems",codeItems);
            request.setAttribute("codeSet",codeSet);
            return "codeItem";
        } catch (Exception e){
            LOG.error("获取codeItem失败",e);
            request.setAttribute("errMsg",e.getMessage());
            return "error";
        }
    }

    @ResponseBody
    @RequestMapping("saveCodeItem")
    public String saveCodeItem(CodeItem codeItem){
        try {
            LOG.info("codeItem = {}",codeItem);
            String errMsg = codeItemService.saveCodeItem(codeItem);
            if (errMsg != null){
                return JsonResponseUtils.badResult(errMsg);
            }
            return JsonResponseUtils.ok();
        } catch (Exception e) {
            LOG.error("存储codeItem失败",e);
            return JsonResponseUtils.badResult(e.getMessage());
        }
    }

    @ResponseBody
    @RequestMapping("deleteCodeItem")
    public String deleteCodeItem(@Param("id")Long id){
        try {
            if (id == null){
                return JsonResponseUtils.badResult("获取信息失败，请刷新页面");
            }
            codeItemService.deleteCodeItem(id);
            return JsonResponseUtils.ok();
        } catch (Exception e) {
            LOG.error("删除codeItem失败，",e);
            return JsonResponseUtils.badResult(e.getMessage());
        }
    }
}
