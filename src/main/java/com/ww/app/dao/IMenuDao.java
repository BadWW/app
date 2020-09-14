package com.ww.app.dao;

import java.util.List;
import java.util.Map;

public interface IMenuDao {

    public List<Map<String, Object>> getMenusByParentId(int parentId);

    public List<Integer> queryUserAuth(int userId);

}
