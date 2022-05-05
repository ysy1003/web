package com.ysy.pagedemo.config;

import org.springframework.web.servlet.LocaleResolver;
import org.thymeleaf.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Locale;

public class MyLocaleResolver implements LocaleResolver {

    //处理请求，来判断语言
    @Override
    public Locale resolveLocale(HttpServletRequest request) {

        String language = request.getParameter("lang");
        //默认
        Locale locale = Locale.getDefault();

        if (!StringUtils.isEmpty(language)){
            //zh_CN
            String[] split = language.split("_");
            locale = new Locale(split[0],split[1]);
        }

        return locale;
    }

    @Override
    public void setLocale(HttpServletRequest request, HttpServletResponse response, Locale locale) {

    }
}
