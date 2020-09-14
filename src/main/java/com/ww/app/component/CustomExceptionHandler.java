package com.ww.app.component;

import com.ww.app.exception.CustomException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class CustomExceptionHandler {

    @ExceptionHandler(CustomException.class)
    public String handlerException(Exception e, HttpServletRequest request) {
        // 设置自己的状态码
        request.setAttribute("javax.servlet.error.status_code", 500);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("code", "500");
        map.put("message", "系统出错啦！");
        request.setAttribute("ext", map);
        return "forward:/error";
    }

}
