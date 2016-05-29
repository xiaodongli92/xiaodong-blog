package com.xiaodong.blog.utils;

import com.xiaodong.blog.commons.AppConstants;
import com.xiaodong.blog.model.Article;
import com.xiaodong.blog.model.User;
import org.apache.commons.lang3.StringUtils;

/**
 * Created by xiaodong on 15/11/29.
 */
public class ValidateUtils {

    /**
     * 注册必填校验
     * @param user
     * @return
     */
    public static String signUpValidate(User user){
        if (StringUtils.isBlank(user.getName())){
            return AppConstants.NAME_NULL;
        }
        if (StringUtils.isBlank(user.getEmail())){
            return AppConstants.EMAIL_NULL;
        }
        if (StringUtils.isBlank(user.getPassword())){
            return AppConstants.PASSWORD_NULL;
        }
        return null;
    }

    /**
     * 登录校验
     * @param user
     * @return
     */
    public static String signInValidate(User user){
        if (StringUtils.isBlank(user.getEmail())){
            return AppConstants.EMAIL_NULL;
        }
        if (StringUtils.isBlank(user.getPassword())){
            return AppConstants.PASSWORD_NULL;
        }
        return null;
    }

    public static String articleValidate(Article article){
        if (article == null) {
            return "信息不能为空";
        }
        if (StringUtils.isBlank(article.getTitle())){
            return "标题不能为空";
        }
        if (StringUtils.isBlank(article.getContent())){
            return "内容不能为空";
        }
        if (StringUtils.isBlank(article.getTypeCode())){
            return "文章类型不能为空";
        }
        return null;
    }
}
