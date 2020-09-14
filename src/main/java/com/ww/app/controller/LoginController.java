package com.ww.app.controller;

import com.ww.app.exception.CustomException;
import com.ww.app.service.ILoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

@Controller
public class LoginController {

    @Autowired
    public ILoginService loginService;

    @PostMapping("/login")
    public String login(@RequestParam("username") String username,
                        @RequestParam("password") String password,
                        Map<String, Object> map,
                        HttpSession session) throws Exception{
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("username", username);
        param.put("password", password);
        Map<String, Object> user = loginService.login(param);
        if (user != null) {
            session.setAttribute("user",user);
            // 登录成功，防止表单重复提交，重定向到主页
            return "redirect:/index.html";
        } else {
            map.put("msg","用户名和密码错误！");
            return "login";
        }
    }

}
