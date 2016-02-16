package com.xiaodong.blog.dao;

import com.xiaodong.blog.model.CodeItem;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by xiaodong on 2016/2/3.
 */
public interface CodeItemDAO extends CrudRepository<CodeItem,Long> {

    @Query(value = "SELECT * FROM code_item where code_set=?",nativeQuery = true)
    List<CodeItem> getByCodeSetValue(String codeSetValue);

    @Query(value = "SELECT * FROM code_item where ''=?",nativeQuery = true)
    List<CodeItem> getByCodeSets(String codeSetValue);

}
