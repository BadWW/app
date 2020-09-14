package com.ww.app.config;

import com.ww.app.component.LoginHandlerInterceptor;
import com.ww.app.component.MyLocaleResolver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.Arrays;
import java.util.List;

/**
 * 扩展SpringMVC
 */
@Configuration
public class MvcExtendConfig implements WebMvcConfigurer {

    public static List<String> EXCLUDE_PATH =
            Arrays.asList("/login.html","/login","/druid/**","/static/**","/webjars/**");

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/ww").setViewName("login");
    }

    // 所有的WebMvcConfigurer组件都会一起起作用
    @Bean // 将组件注入容器
    public WebMvcConfigurer webMvcConfigurer() {
        WebMvcConfigurer webMvcConfigurer = new WebMvcConfigurer() {
            @Override
            public void addViewControllers(ViewControllerRegistry registry) {
                registry.addViewController("/login.html").setViewName("login");
                registry.addViewController("/index.html").setViewName("index");
            }

            // 注册拦截器
            @Override
            public void addInterceptors(InterceptorRegistry registry) {
                registry.addInterceptor(new LoginHandlerInterceptor())
                        .addPathPatterns("/**").excludePathPatterns(EXCLUDE_PATH);
            }

            @Override
            public void addResourceHandlers(ResourceHandlerRegistry registry) {
                registry.addResourceHandler("/static/**").addResourceLocations("classpath:/static/");
            }

        };
        return webMvcConfigurer;
    }

    @Bean
    public LocaleResolver localeResolver() {
        return new MyLocaleResolver();
    }
}
