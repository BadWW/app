package com.ww.app.controller;

import com.ww.app.service.IAuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@Controller
public class MenuController {

    @Autowired
    IAuthService menuService;

    @PostMapping("/menus")
    @ResponseBody
    public String menus(HttpServletRequest request) {
        return menuService.getMenus(request);
    }

    @GetMapping("/menu/*")
    public String menu(HttpServletRequest request) {
        String servletPath = request.getServletPath();
        return servletPath.split("/")[servletPath.split("/").length-1];
    }

}
