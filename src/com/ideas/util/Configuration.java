package com.ideas.util;

import java.awt.*;

public class Configuration {

  public static String ConfigFilePath;

  public static String ServerBindAddress;
  public static int ServerPort;
  public static int PageRefreshRate; //ҳ��ˢ��Ƶ��
  public static int TimerDelay; //��ʱ�������
  public static int WarningDelayInterval; //���洰��ͣ���ļ����
  public static int DataManageInterval; //������ʷ���ݵļ����

  public static String LoginName;
  public static String LoginPassword; //��ʱ�������
  /**
   * �绰����������
   * TelephoneTellerFlag:�Ƿ񱨾�
   * TelephoneTellerListenerAddress:������ip
   * TelephoneTellerListenerPort:������port
   *
   */
  public static boolean TelephoneTellerFlag = true;
  public static String TelephoneTellerListenerAddress = "192.168.2.118";
  public static int TelephoneTellerListenerPort = 8000;

  /**
   * ���unix��������Ӧ����������(ip,os,cics,sna,sybase)�������±����У�һһ��Ӧ��
   */

  public static String[] UnixClientHostname;
  public static String[] UnixClientIP;
  public static String[] os;
  public static String[] cicsclient;
  public static String[] snaclient;
  public static String[][] sybaseclient; //�ж�̨unix,ÿ̨unix���ж��sybase client

  /**
   * email����������
   */
  public static boolean MAILFlag;
  public static String MAILServer;
  public static String MAILAddr;
  public static String MAILUser;
  public static String MAILPasswd;
  public static String MAILAuth;
  public static String[] MAILRcptAddress;
  /**
   * sms�ֻ����ű�������
   */
  public static boolean SMSFlag;
  public static String SMSGatewayIP;
  public static int SMSGatewayPort;
  public static String[] SMSPhoneNumber;
  /**
   *���ӳ�����
   */
  public static String ConnectionPoolName = "idb";
  public static String DB_URL; //= "jdbc:mysql://192.168.0.1:3306/book?useUnicode=true&characterEncoding=gb2312";
  public static String DB_JDBCDRIVER; //= "org.gjt.mm.mysql.Driver";
  public static String DB_USERNAME; //= "admin";
  public static String DB_PASSWORD; //= "admin";
  public static String DB_MAXCONNNUM; //= "10";
  public static String DB_LOGFILE; //= "D:\\log.txt";
  /**
   * Ԥ��50����״ͼ����ɫ
   */
  public static Color[] BandColor = new Color[50];
  /**
   * ÿҳ��ʾ��¼������ҳ�ã�
   */
  public static int Number_shown;

}
