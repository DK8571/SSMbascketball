package com.baoshi.programmer.util;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtils {
    public static String datetoString(Date date, String patt){
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat(patt);
        String forDate=simpleDateFormat.format(date);
        return forDate;
    }
    public static Date stringToDate(String str,String patt) throws ParseException {
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat(patt);
        Date parse=simpleDateFormat.parse(str);
        return parse;
    }
}
