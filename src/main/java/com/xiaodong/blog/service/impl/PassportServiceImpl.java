package com.xiaodong.blog.service.impl;

import com.xiaodong.blog.commons.AppConstants;
import com.xiaodong.blog.dao.PassportDAO;
import com.xiaodong.blog.dao.UserInfoDAO;
import com.xiaodong.blog.model.User;
import com.xiaodong.blog.model.UserInfo;
import com.xiaodong.blog.service.inter.PassportService;
import com.xiaodong.blog.utils.ValidateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by xiaodong on 2015/11/16.
 */
@Service
public class PassportServiceImpl implements PassportService {

    @Autowired
    private PassportDAO passportDAO;

    @Autowired
    private UserInfoDAO userInfoDAO;

    @Override
    public String signUp(User user) {
        String validateMsg = ValidateUtils.signUpValidate(user);
        if (validateMsg != null){
            return validateMsg;
        }
        if (passportDAO.getUserByEmail(user.getEmail())!=null){
            return AppConstants.ALREADY_SIGN_UP;
        }
        passportDAO.save(user);
        return null;
    }

    @Override
    public String signIn(User user) {
        String validateMsg = ValidateUtils.signInValidate(user);
        if (validateMsg!=null){
            return validateMsg;
        }
        if (passportDAO.getUserByEmail(user.getEmail())==null){
            return AppConstants.NOT_SIGN_UP;
        }
        if (passportDAO.signInValidate(user.getEmail(), user.getPassword())==null){
            return AppConstants.ERR_EMIAL_PASSWORD;
        }
        return null;
    }

    @Override
    public User getUserByEmail(String email) {
        return passportDAO.getUserByEmail(email);
    }

    @Override
    public User update(User user) {
        return passportDAO.save(user);
    }

    @Override
    public User getUserById(long userId) {
        return passportDAO.getUserById(userId);
    }

    @Override
    public UserInfo getUserInfoByUserId(long userId) {
        return userInfoDAO.getUserInfoByUserId(userId);
    }

    @Override
    public UserInfo updateUserInfo(UserInfo userInfo) {
        return userInfoDAO.save(userInfo);
    }
}
