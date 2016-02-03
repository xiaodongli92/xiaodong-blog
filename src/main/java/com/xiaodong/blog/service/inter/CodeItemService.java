package com.xiaodong.blog.service.inter;

import com.xiaodong.blog.model.AreaCode;

import java.util.List;
import java.util.Map;

/**
 * Created by xiaodong on 2016/2/2.
 */
public interface CodeItemService {

    Map<String,String> getProvinceMap();

    Map<String,String> getCityMap(String provinceCode);

    Map<String,String> getCountyMap(String CityCode);

    List<AreaCode> getAreaList();

    Map<String,String> getAreaMap();
}
