package com.xiaodong.blog.dao;

import com.xiaodong.blog.model.CodeSet;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Set;

/**
 * Created by xiaodong on 2016/2/3.
 */
public interface CodeSetDAO extends CrudRepository<CodeSet,Long> {

    @Query(value = "SELECT * FROM code_set order by seq asc",nativeQuery = true)
    List<CodeSet> getAll();

    @Query(value = "SELECT * FROM code_set where id=?",nativeQuery = true)
    CodeSet get(long id);

    @Query(value = "SELECT * FROM code_set where code_set_value in (?1)",nativeQuery = true)
    List<CodeSet> getByCodeSets(Set<String> set);
}
