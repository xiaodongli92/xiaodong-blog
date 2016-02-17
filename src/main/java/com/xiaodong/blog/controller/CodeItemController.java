package com.xiaodong.blog.controller;

import com.alibaba.fastjson.JSONObject;
import com.xiaodong.blog.model.CodeItem;
import com.xiaodong.blog.model.CodeSet;
import com.xiaodong.blog.service.ExportService;
import com.xiaodong.blog.service.inter.CodeItemService;
import com.xiaodong.blog.utils.CommonsUtils;
import com.xiaodong.blog.utils.JsonResponseUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.ws.Response;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by xiaodong on 2016/2/2.
 */
@Controller
@RequestMapping("bs")
public class CodeItemController extends AbstractController {

    private static final Logger LOG = LoggerFactory.getLogger(CodeItemController.class);

    @Autowired
    private CodeItemService codeItemService;

    @Autowired
    private ExportService exportService;

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
            codeItemService.deleteCodeSet(id);
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

    @ResponseBody
    @RequestMapping("codeItemMap")
    public String parentCodeMap(@Param("codeSetValue")String codeSetValue){
        try {
            if (StringUtils.isBlank(codeSetValue)){
                return JsonResponseUtils.badResult("获取信息失败，请刷新页面");
            }
            Map<String,String> map = codeItemService.codeItemMap(codeSetValue);
            return JsonResponseUtils.ok(map);
        } catch (Exception e) {
            LOG.error("获取parentCodeMap失败",e);
            return JsonResponseUtils.badResult(e.getMessage());
        }
    }

    @ResponseBody
    @RequestMapping("exportCodeItem")
    public String exportCodeItem(@Param("codeSets")String codeSets,HttpServletRequest request){
        try {
            String fileName = "codeSet.txt";
            Set<String> set = CommonsUtils.getSetFromString(codeSets);
            List<CodeSet> codeSetList = codeItemService.getCodeSet(set);
            List<CodeItem> codeItemList = codeItemService.getCodeItem(set);
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("codeItemList",codeItemList);
            jsonObject.put("codeSetList",codeSetList);
            LOG.info("开始生成文件fileName={}",fileName);
            LOG.info("内容={}",jsonObject.toJSONString());
            exportService.exportCodeItem(getDownloadFile(request,fileName),jsonObject.toJSONString());
            return JsonResponseUtils.ok("fileName",fileName);
        } catch (Exception e){
            LOG.error("生成文件失败，",e);
            return JsonResponseUtils.badResult(e.getMessage());
        }
    }

    @ResponseBody
    @RequestMapping("checkFile")
    public String checkFile(@Param("fileName")String fileName,HttpServletRequest request){
        try {
            return checkFile(getDownloadFile(request, fileName));
        } catch (Exception e){
            LOG.error("检查文件失败",e);
            return JsonResponseUtils.badResult(e.getMessage());
        }
    }

    @ResponseBody
    @RequestMapping("download")
    public String download(@Param("fileName")String fileName, HttpServletRequest request, HttpServletResponse response){
        try {
            byte[] bytes = exportService.getFileDownload(getDownloadFile(request,fileName));
            response.setHeader("Content-Length", String.valueOf(bytes.length));
            response.setContentType("application/txt;charset=utf-8");
            response.addHeader("Content-Disposition", "attachment;filename=" + fileName);
            response.getOutputStream().write(bytes);
            response.flushBuffer();
        } catch (Exception e){
            LOG.error("下载文件失败，",e);
        }
        return null;
    }

    @RequestMapping("importCodeItem")
    public String importCodeItem(MultipartFile file,HttpServletRequest request){
        try {
            LOG.info("file = {}",file);
            String errMsg = codeItemService.importCodeItem(file);
            if (errMsg == null){
                return "redirect:codeSet.do";
            }
            request.setAttribute("errMsg",errMsg);
            return "error";
        } catch (Exception e){
            LOG.error("导入CodeItem失败，",e);
            request.setAttribute("errMsg",e.getMessage());
            return "error";
        }
    }
}
