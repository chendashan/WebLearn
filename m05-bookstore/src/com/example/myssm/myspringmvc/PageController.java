package com.example.myssm.myspringmvc;

import com.example.myssm.util.StringUtil;

public class PageController {
    public String page(String page) {

        if (StringUtil.isEmpty(page)) {
            //没有page参数时默认打开的页面
            return "user/login";
        }
        return page;
    }
}
