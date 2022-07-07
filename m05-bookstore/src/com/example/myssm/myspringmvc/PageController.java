package com.example.myssm.myspringmvc;

import com.example.myssm.util.StringUtil;

public class PageController {
    public String page(String page) {
        if (StringUtil.isEmpty(page)) {
            return "user/login";
        }
        return page;
    }
}
