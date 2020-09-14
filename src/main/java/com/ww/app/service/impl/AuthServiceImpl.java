package com.ww.app.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.ww.app.dao.IMenuDao;
import com.ww.app.service.IAuthService;
import com.ww.app.utils.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

@Service
public class AuthServiceImpl implements IAuthService {

    @Autowired
    IMenuDao menuDao;
    @Autowired
    IMenuDao userDao;

    @Override
    public String getUserAuth(HttpServletRequest request) {
        Map<String, Object> user = (Map<String, Object>) request.getSession().getAttribute("user");
        List<Integer> menus = menuDao.queryUserAuth((Integer) user.get("userId"));
        List<Map<String, Object>> menuList = getMenusByParentId(0, menus);
        ResultUtil result = new ResultUtil();
        result.setCode(0);
        result.setMsg("success");
        result.setCount((long) menuList.size());
        result.setData(menuList);
        return JSONArray.toJSON(result).toString();
    }

    public List<Map<String, Object>> getMenusByParentId(int parentId, List<Integer> menus) {
        List<Map<String, Object>> menuList = menuDao.getMenusByParentId(parentId);
        for (Map<String, Object> menu : menuList) {
            if (menus.contains(menu.get("id"))) {
                if ("N".equals((String) menu.get("isLeaf"))) {
                    List<Map<String, Object>> childs = getMenusByParentId((Integer) menu.get("id"), menus);
                    if (childs != null && childs.size() > 0) {
                        for (Map<String, Object> child : childs) {
                            if (!menus.contains(child.get("id"))) {
                                childs.remove(child);
                            }
                        }
                        menu.put("menus", childs);
                    }
                }
            } else {
                menuList.remove(menu);
            }
        }
        return menuList;
    }

}
