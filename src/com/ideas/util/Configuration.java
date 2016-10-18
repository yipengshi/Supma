package com.ideas.util;

import java.awt.*;

public class Configuration {

  public static String ConfigFilePath;

  public static String ServerBindAddress;
  public static int ServerPort;
  public static int PageRefreshRate; //页面刷新频率
  public static int TimerDelay; //定时器间隔期
  public static int WarningDelayInterval; //警告窗口停留的间隔期
  public static int DataManageInterval; //清理历史数据的间隔期

  public static String LoginName;
  public static String LoginPassword; //定时器间隔期
  /**
   * 电话报警器配置
   * TelephoneTellerFlag:是否报警
   * TelephoneTellerListenerAddress:报警器ip
   * TelephoneTellerListenerPort:报警器port
   *
   */
  public static boolean TelephoneTellerFlag = true;
  public static String TelephoneTellerListenerAddress = "192.168.2.118";
  public static int TelephoneTellerListenerPort = 8000;

  /**
   * 多个unix主机，对应的所有配置(ip,os,cics,sna,sybase)按数组下标排列，一一对应。
   */

  public static String[] UnixClientHostname;
  public static String[] UnixClientIP;
  public static String[] os;
  public static String[] cicsclient;
  public static String[] snaclient;
  public static String[][] sybaseclient; //有多台unix,每台unix上有多个sybase client

  /**
   * email报警器设置
   */
  public static boolean MAILFlag;
  public static String MAILServer;
  public static String MAILAddr;
  public static String MAILUser;
  public static String MAILPasswd;
  public static String MAILAuth;
  public static String[] MAILRcptAddress;
  /**
   * sms手机短信报警设置
   */
  public static boolean SMSFlag;
  public static String SMSGatewayIP;
  public static int SMSGatewayPort;
  public static String[] SMSPhoneNumber;
  /**
   *连接池配置
   */
  public static String ConnectionPoolName = "idb";
  public static String DB_URL; //= "jdbc:mysql://192.168.0.1:3306/book?useUnicode=true&characterEncoding=gb2312";
  public static String DB_JDBCDRIVER; //= "org.gjt.mm.mysql.Driver";
  public static String DB_USERNAME; //= "admin";
  public static String DB_PASSWORD; //= "admin";
  public static String DB_MAXCONNNUM; //= "10";
  public static String DB_LOGFILE; //= "D:\\log.txt";
  /**
   * 预设50个柱状图的颜色
   */
  public static Color[] BandColor = new Color[50];
  /**
   * 每页显示记录数（分页用）
   */
  public static int Number_shown;

}
