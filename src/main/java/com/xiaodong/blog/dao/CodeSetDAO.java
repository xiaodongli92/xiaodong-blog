package com.xiaodong.blog.dao;

import com.xiaodong.blog.model.CodeSet;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Created by xiaodong on 2016/2/3.
 */
public interface CodeSetDAO extends CrudRepository<CodeSet,String> {

    @Query(value = "SELECT * FROM code_set",nativeQuery = true)
    List<CodeSet> getAll();

    @Modifying(clearAutomatically = true)
    @Query(value = "update code_set set" +
            " code_set_name=:codeSet.codeSetName," +
            " code_set_value=:codeSet.codeSetValue," +
            " remark=:codeSet.remark," +
            " status=:codeSet.status," +
            " seq=?.seq where id=?.seq",nativeQuery = true)
    void update(@Param("codeSet") CodeSet codeSet);
}
