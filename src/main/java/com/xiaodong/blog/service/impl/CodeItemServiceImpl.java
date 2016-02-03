package com.xiaodong.blog.service.impl;

import com.xiaodong.blog.dao.AreaCodeDAO;
import com.xiaodong.blog.model.AreaCode;
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

    @Override
    public Map<String, String> getProvinceMap() {
        List<AreaCode> areaCodes = areaCodeDAO.getProvinceAreaCode();
        return CommonsUtils.getMapFromAreaList(areaCodes);
    }

    @Override
    public Map<String, String> getCityMap(String provinceCode) {
        String provinceCodeStart = getAreaCodeStartStr(provinceCode);
        List<AreaCode> areaCodes = areaCodeDAO.getCityAreaCode(provinceCodeStart);
        return CommonsUtils.getMapFromAreaList(areaCodes);
    }

    @Override
    public Map<String, String> getCountyMap(String cityCode) {
        String cityCodeStart = getAreaCodeStartStr(cityCode);
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

    private static String getAreaCodeStartStr(String code) {
        return code.substring(0,2)+"%";
    }
}
