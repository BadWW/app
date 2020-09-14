package com.ww.app.service.impl;

import com.ww.app.dao.IUserDao;
import com.ww.app.service.ILoginService;
import com.ww.app.utils.AESUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class LoginServiceImpl implements ILoginService {

    @Autowired
    IUserDao userDao;

    @Override
    public Map<String, Object> login(Map<String, Object> map) throws Exception {
        Map<String, Object> userMap = userDao.queryUserByName((String) map.get("username"));
        String userPassword = (String) userMap.get("password");
        String password = (String) map.get("password");
        if (password.equals(AESUtil.decrypt(userPassword, "0000000000000000"))) {
            return userMap;
        } else {
            return null;
        }
    }
}
