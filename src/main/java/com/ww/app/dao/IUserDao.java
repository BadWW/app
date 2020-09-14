package com.ww.app.dao;

import java.util.List;
import java.util.Map;

public interface IUserDao {

    public Map<String, Object> queryUserByName(String username);

}
