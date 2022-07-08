package com.example.myssm.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class StringUtil {

    //判断字符串是否为null或者""
    public static boolean isEmpty(String str) {
        return str == null || "".equals(str);
    }

    public static boolean isNotEmpty(String str) {
        return !isEmpty(str);
    }

    public static String trimDateSymbol(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateStr = sdf.format(date);
        String[] arrDate = dateStr.split(" ");

        String[] arrDay = arrDate[0].split("-");
        String[] arrTime = arrDate[1].split(":");
        StringBuilder sb = new StringBuilder();
        for (String str : arrDay) {
            sb.append(str);
        }
        for (String str : arrTime) {
            sb.append(str);
        }
        return sb.toString();
    }
}
