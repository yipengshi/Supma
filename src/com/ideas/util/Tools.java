package com.ideas.util;

import java.util.Date;
import java.text.*;
import java.util.regex.*;

public class Tools {
  public static boolean isNULL(String s) {
    if ( (s == null) || (s.equals(""))) {
      return true;
    }
    else {
      return false;
    }
  }

  public static String getFormatTime() {
    String time = "";
    time = DateFormat.getDateTimeInstance().format(new Date());
    return time;
  }
  /**
   * 从任意字符串中寻找一个数字
   * 如：afasdfas%$##1234&^%$#a":,sdfasfd 中的 1234
   * @param src
   * @return
   */
  public static String findNumberInString(String src){
    String number="0";


   Pattern p = null;
   Matcher m = null;
   //正则表达式
   p = Pattern.compile("[\\d]+");
   m = p.matcher(src);
   if (m.find()) {
     number = m.group();
   }
    return number;
  }

  public static void main(String args[]) {
    System.out.println(Tools.getFormatTime());
        System.out.println(Tools.findNumberInString("afasdfas%$1234&^%$#a\":,sdfasfd" ));
  }
}