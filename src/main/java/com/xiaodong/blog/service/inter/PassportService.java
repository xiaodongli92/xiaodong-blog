package com.xiaodong.blog.service.inter;


import com.xiaodong.blog.model.User;

/**
 * Created by xiaodong on 2015/11/16.
 */
public interface PassportService {

    /**
     * 用户注册
     * @param user
     * @return
     */
    String signUp(User user);

    /**
     * 用户登录
     * @param user
     * @return
     */
    String signIn(User user);

    /**
     * 根据邮箱得到user
     * @param email
     * @return
     */
    User getUserByEmail(String email);

    /**
     * 更新数据
     * @param user
     * @return
     */
    User update(User user);
}
