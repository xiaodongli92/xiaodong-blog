package com.xiaodong.blog.dao;

import com.xiaodong.blog.model.AreaCode;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.RepositoryDefinition;

import java.util.List;

/**
 * Created by xiaodong on 2016/2/2.
 */
@RepositoryDefinition(domainClass = AreaCode.class,idClass = Long.class)
public interface AreaCodeDAO{
    @Query(value = "SELECT * FROM area_code WHERE code%10000=0",nativeQuery = true)
    List<AreaCode> getProvinceAreaCode();

    @Query(value = "SELECT * FROM area_code WHERE code like ?1 and (code%10000!=0 or is_zxs=1) and code%100=0",nativeQuery = true)
    List<AreaCode> getCityAreaCode(String provinceCode);

    @Query(value = "SELECT * FROM area_code WHERE code like ?1 and code%100!=0",nativeQuery = true)
    List<AreaCode> getCountyCode(String cityCode);

    @Query(value = "SELECT * FROM area_code",nativeQuery = true)
    List<AreaCode> getAreaList();
}
