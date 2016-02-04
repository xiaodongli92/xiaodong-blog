package com.xiaodong.blog.dao;

import com.xiaodong.blog.model.CodeSet;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by xiaodong on 2016/2/3.
 */
public interface CodeSetDAO extends CrudRepository<CodeSet,String> {

    @Query(value = "SELECT * FROM code_set",nativeQuery = true)
    List<CodeSet> getAll();

    @Query(value = "update code_set set" +
            " code_set_name=?.codeSetName," +
            " code_set_value=?.codeSetValue," +
            " remark=?.remark," +
            " status=?.status," +
            " seq=?.seq where id=?.seq",nativeQuery = true)
    void update(CodeSet codeSet);
}
