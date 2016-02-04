package com.xiaodong.blog.service.impl;

import com.xiaodong.blog.dao.AreaCodeDAO;
import com.xiaodong.blog.dao.CodeItemDAO;
import com.xiaodong.blog.dao.CodeSetDAO;
import com.xiaodong.blog.model.AreaCode;
import com.xiaodong.blog.model.CodeItem;
import com.xiaodong.blog.model.CodeSet;
import com.xiaodong.blog.service.inter.CodeItemService;
import com.xiaodong.blog.utils.CommonsUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Created by xiaodong on 2016/2/2.
 */
@Service
public class CodeItemServiceImpl implements CodeItemService {

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
        String cityCodeStart = getAreaCodeStartStr(cityCode,4);
        String zxsCityCodeStart = getAreaCodeStartStr(cityCode,2);
        List<AreaCode> areaCodes = areaCodeDAO.getCountyCode(cityCodeStart,zxsCityCodeStart);
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
    public void saveCodeSet(CodeSet codeSet) {
        codeSetDAO.save(codeSet);
    }

    @Override
    public void updateCodeSet(CodeSet codeSet) {
        codeSetDAO.update(codeSet);
    }

    @Override
    public List<CodeItem> getCodeItemByCodeSet(String codeSetValue) {
        return codeItemDAO.getByCodeSetValue(codeSetValue);
    }
}
