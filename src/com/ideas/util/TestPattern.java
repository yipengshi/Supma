package com.ideas.util;

import java.util.regex.*;

public class TestPattern {
  public static void main(String[] args) {
    Pattern p = null; //正则表达式
    Matcher m = null; //操作的字符串
    boolean b;
    String s = null;
    StringBuffer sb = null;
    int i = 0;
//字符串匹配，这是不符合的
    p = Pattern.compile("a*b");
    m = p.matcher("baaaaab");
    b = m.matches();
    System.out.println(b);
//字符串匹配，这是符合的
    p = Pattern.compile("a*b");
    m = p.matcher("aaaaab");
    b = m.matches();
    System.out.println(b);
//字符串替换
    p = Pattern.compile("ab");
    m = p.matcher("aaaaab");
    s = m.replaceAll("d");
    System.out.println(s);
    p = Pattern.compile("a*b");
    m = p.matcher("aaaaab");
    s = m.replaceAll("d");
    System.out.println(s);
    p = Pattern.compile("a*b");
    m = p.matcher("caaaaab");
    s = m.replaceAll("d");
    System.out.println(s);
//字符串查找
    p = Pattern.compile("cat");
    m = p.matcher("one cat two cats in the yard");
    sb = new StringBuffer();
    while (m.find()) {
      m.appendReplacement(sb, "dog");
      i++;
    }
    m.appendTail(sb);
    System.out.println(sb.toString());
    System.out.println(i);
    i = 0;
    p = Pattern.compile("cat");
    m = p.matcher("one cat two ca tsi nthe yard");
    sb = new StringBuffer();
    while (m.find()) {
      m.appendReplacement(sb, "dog");
      i++;
    }
    m.appendTail(sb);
    System.out.println(sb.toString());
    System.out.println(i);
    p = Pattern.compile("cat");
    m = p.matcher("one cat two cats in the yard");
    p = m.pattern();
    m = p.matcher("bacatab");
    b = m.matches();
    System.out.println(b);
    s = m.replaceAll("dog");
    System.out.println(s);
    i = 0;
    p = Pattern.compile("(fds){2,}");
    m = p.matcher("dsa da fdsfds aaafdsafds aaf");
    sb = new StringBuffer();
    while (m.find()) {
      m.appendReplacement(sb, "dog");
      i++;
    }

    m.appendTail(sb);
    System.out.println(sb.toString());
    System.out.println(i);

    p = Pattern.compile("cat");
    m = p.matcher("one cat two cats in the yard");
    sb = new StringBuffer();
    while (m.find()) {
      m.appendReplacement(sb, "<font color=\"red\"＞cat</font＞");
    }
    m.appendTail(sb);
    System.out.println(sb.toString());
    String aa = sb.toString();
    System.out.println(aa);
//字符串分割
    p = Pattern.compile("a+");
    String[] a = p.split("caaaaaat");
    for (i = 0; i < a.length; i++) {
      System.out.println(a[i]);
    }
    p = Pattern.compile("a+");
    a = p.split("c aa aaaa t", 0);
    for (i = 0; i < a.length; i++) {
      System.out.println(a[i]);
    }
    p = Pattern.compile(" +");
    a = p.split("c aa aaaa t", 0);
    for (i = 0; i < a.length; i++) {
      System.out.println(a[i]);
    }
    p = Pattern.compile("\\+");
    a = p.split("dsafasdfdsafsda+dsagfasdfa+sdafds");
    System.out.println(a.length);
    for (i = 0; i < a.length; i++) {
      System.out.println(a[i]);
    }

    //查找一个ip地址
    p = Pattern.compile("[0-9]{1,3}.[0-9]{1,3}.[0-9]{1,3}.[0-9]{1,3}");
    m = p.matcher("info{hostname=rs6000,ip=192.168.2.117}cpu{user=0 ,sys=2 ,wio=0 ,idle=98}memory{inuse= 7407900 K, free= 980552 K}pg{totalsize= 1966080 K,inuse= 257028 K}ps{name=hd6 ,size= 1920MB ,%used= 14}");
    if (m.find()) {
      String str = m.group();
      System.out.println(str);
    }
  }
}