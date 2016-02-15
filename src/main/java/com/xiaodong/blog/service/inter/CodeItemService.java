package com.xiaodong.blog.service.inter;

import com.xiaodong.blog.model.AreaCode;
import com.xiaodong.blog.model.CodeItem;
import com.xiaodong.blog.model.CodeSet;

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

    List<CodeSet> getAllCodeSet();

    String saveCodeSet(CodeSet codeSet);

    void deleteCodeSet(long id);

    CodeSet get(long id);

    List<CodeItem> getCodeItemByCodeSet(String codeSetValue);

    String saveCodeItem(CodeItem codeItem);

    void deleteCodeItem(long id);

    Map<String,String> codeItemMap(String codeSetValue);

}
