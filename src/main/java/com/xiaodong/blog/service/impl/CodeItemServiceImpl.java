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
    public Map<String, String> getCityMap() {
        return null;
    }

    @Override
    public Map<String, String> getCountyMap() {
        return null;
    }

    @Override
    public List<AreaCode> getAreaList() {
        return null;
    }
}
