package com.xiaodong.blog.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.xiaodong.blog.dao.AreaCodeDAO;
import com.xiaodong.blog.dao.CodeItemDAO;
import com.xiaodong.blog.dao.CodeSetDAO;
import com.xiaodong.blog.model.AreaCode;
import com.xiaodong.blog.model.CodeItem;
import com.xiaodong.blog.model.CodeSet;
import com.xiaodong.blog.service.inter.CodeItemService;
import com.xiaodong.blog.utils.CommonsUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * Created by xiaodong on 2016/2/2.
 */
@Service
public class CodeItemServiceImpl implements CodeItemService {

    private static final Logger LOG = LoggerFactory.getLogger(CodeItemServiceImpl.class);

    @Autowired
    private AreaCodeDAO areaCodeDAO;

    @Autowired
    private CodeSetDAO codeSetDAO;

    @Autowired
    private CodeItemDAO codeItemDAO;

    @Override
    public Map<String, String> getProvinceMap() {
        List<AreaCode> areaCodes = areaCodeDAO.getProvinceAreaCode();
        return CommonsUtils.getMapFromAreaList(areaCodes);
    }

    @Override
    public Map<String, String> getCityMap(String provinceCode) {
        String provinceCodeStart = getAreaCodeStartStr(provinceCode,2);
        List<AreaCode> areaCodes = areaCodeDAO.getCityAreaCode(provinceCodeStart);
        return CommonsUtils.getMapFromAreaList(areaCodes);
    }

    @Override
    public Map<String, String> getCountyMap(String cityCode) {
        boolean checkCity = "00".equals(StringUtils.substring(cityCode,2,4));
        String cityCodeStart = getAreaCodeStartStr(cityCode,checkCity?2:4);
        List<AreaCode> areaCodes = areaCodeDAO.getCountyCode(cityCodeStart);
        return CommonsUtils.getMapFromAreaList(areaCodes);
    }

    @Override
    public List<AreaCode> getAreaList() {
        return areaCodeDAO.getAreaList();
    }

    @Override
    public Map<String, String> getAreaMap() {
        List<AreaCode> areaCodes = areaCodeDAO.getAreaList();
        return CommonsUtils.getMapFromAreaList(areaCodes);
    }

    private static String getAreaCodeStartStr(String code,int length) {
        return code.substring(0,length)+"%";
    }

    @Override
    public List<CodeSet> getAllCodeSet() {
        return codeSetDAO.getAll();
    }

    @Override
    public String saveCodeSet(CodeSet codeSet) {
        String errMsg = validateCodeSet(codeSet);
        if (errMsg != null){
            return errMsg;
        }
        codeSetDAO.save(codeSet);
        return null;
    }

    @Override
    public void deleteCodeSet(long id) {
        List<CodeItem> codeItems = codeItemDAO.getByCodeSetValue(codeSetDAO.get(id).getCodeSetValue());
        deleteCodeItems(codeItems);
        codeSetDAO.delete(id);
    }

    private void deleteCodeItems(List<CodeItem> codeItems){
        if (codeItems!=null){
            for (CodeItem codeItem:codeItems){
                codeItemDAO.delete(codeItem);
            }
        }
    }

    @Override
    public CodeSet get(long id) {
        return codeSetDAO.get(id);
    }

    @Override
    public List<CodeItem> getCodeItemByCodeSet(String codeSetValue) {
        return codeItemDAO.getByCodeSetValue(codeSetValue);
    }

    private static String validateCodeSet(CodeSet codeSet){
        if (StringUtils.isBlank(codeSet.getCodeSetName())){
            return "字典名称不能为空";
        }
        if (StringUtils.isBlank(codeSet.getCodeSetValue())){
            return "字典值不能为空";
        }
        if (codeSet.getSeq()==null){
            return "请选择字典序号";
        }
        return null;
    }

    @Override
    public String saveCodeItem(CodeItem codeItem) {
        String errMsg = validateCodeItem(codeItem);
        if (errMsg != null) {
            return errMsg;
        }
        codeItemDAO.save(codeItem);
        return null;
    }

    private static String validateCodeItem(CodeItem codeItem){
        if (StringUtils.isBlank(codeItem.getCodeName())){
            return "代码名称不能为空";
        }
        if (StringUtils.isBlank(codeItem.getCodeValue())){
            return "代码值不能为空";
        }
        if (codeItem.getSeq()==null){
            return "请选择序号";
        }
        return null;
    }

    @Override
    public void deleteCodeItem(long id) {
        codeItemDAO.delete(id);
    }

    @Override
    public Map<String, String> codeItemMap(String codeSetValue) {
        List<CodeItem> codeItems = codeItemDAO.getByCodeSetValue(codeSetValue);
        return getParentCodeMap(codeItems);
    }

    private static Map<String,String> getParentCodeMap(List<CodeItem> codeItems){
        Map<String,String> map = new HashMap<String, String>();
        for (CodeItem codeItem:codeItems){
            map.put(codeItem.getCodeValue(),codeItem.getCodeName());
        }
        return map;
    }

    @Override
    public List<CodeItem> getCodeItem(Set<String> codeSets) {
        return codeItemDAO.getByCodeSets(codeSets);
    }

    @Override
    public List<CodeSet> getCodeSet(Set<String> codeSets) {
        return codeSetDAO.getByCodeSets(codeSets);
    }

    @Override
    public String importCodeItem(MultipartFile file) {
        StringBuilder sb = new StringBuilder();
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new InputStreamReader(file.getInputStream()));
            String line;
            while ((line = reader.readLine()) != null){
                sb.append(line);
            }
        } catch (IOException e) {
            LOG.error("读取导入文件失败",e);
            return e.getMessage();
        } finally {
            try {
                reader.close();
            } catch (IOException e){
                LOG.error("关闭 reader 失败",e);
                return e.getMessage();
            }
        }
        JSONObject jsonObject = JSON.parseObject(sb.toString());
        List<CodeSet> codeSets = getCodeSetsFromJson(jsonObject.getJSONArray("codeSetList"));
        List<CodeItem> codeItems = getCodeItemsFromJson(jsonObject.getJSONArray("codeItemList"));
        codeItemDAO.save(codeItems);
        codeSetDAO.save(codeSets);
        return null;
    }

    private static List<CodeSet> getCodeSetsFromJson(JSONArray codeSetJsonArray){
        List<CodeSet> codeSets = new ArrayList<>();
        for (int i=0;i<codeSetJsonArray.size();i++){
            JSONObject jsonObject = codeSetJsonArray.getJSONObject(i);
            CodeSet codeSet = new CodeSet();
            codeSet.setCodeSetName(jsonObject.getString("codeSetName"));
            codeSet.setCodeSetValue(jsonObject.getString("codeSetValue"));
            codeSet.setRemark(jsonObject.getString("remark"));
            codeSet.setSeq(jsonObject.getInteger("seq"));
            codeSet.setStatus(jsonObject.getInteger("status"));
            codeSets.add(codeSet);
        }
        return codeSets;
    }

    private static List<CodeItem> getCodeItemsFromJson(JSONArray codeItemJsonArray){
        List<CodeItem> codeItems = new ArrayList<>();
        for (int i=0;i<codeItemJsonArray.size();i++){
            JSONObject jsonObject = codeItemJsonArray.getJSONObject(i);
            CodeItem codeItem = new CodeItem();
            codeItem.setCodeSet(jsonObject.getString("codeSet"));
            codeItem.setParentCode(jsonObject.getString("parentCode"));
            codeItem.setCodeName(jsonObject.getString("codeName"));
            codeItem.setCodeName2(jsonObject.getString("codeName2"));
            codeItem.setCodeValue(jsonObject.getString("codeValue"));
            codeItem.setSeq(jsonObject.getInteger("seq"));
            codeItem.setRemark(jsonObject.getString("remark"));
            codeItem.setStatus(jsonObject.getInteger("status"));
            codeItems.add(codeItem);
        }
        return codeItems;
    }
}
