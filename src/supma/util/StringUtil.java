/**********************************************************
Copyright (C),2003-10-12, Beijing  USTB.
All rights reserved.
Filename: DealString.java
Author: ���б�
Version 1.0
Date:2003-10-12
Description:���ļ�Ϊ�ַ����Ĵ�����
Other:
Variable List:
Function List:
1.public DealString()//���캯��
2.public String toString(String str)//��nullת��Ϊ"",trim()
3.public String toGBK(String str)//ת������
  public String toUtf8String(String src)
4.public String[] splitStr(String str,char c)//�ָ��ַ���
5.public String getDateTime()//ȡ��ϵͳʱ��
6.public String Replace(String source, String oldString, String newString)//�滻�ַ���
7.public long getDaysInterval(Date d1,Date d2)//ȡ���������ڵ�����֮��
8.public String toLengthStr(String instr,int len)//���ַ�����ʽ��Ϊ�̶�����
9.public String toLengthStrRight(String instr,int len)//���ַ�����ʽ��Ϊ�̶�����(�ұ߲��ո�)
//9.public String AsciiToChineseString(String s)//ASCIIת��Ϊ�ַ���
//10.public String ChineseStringToAscii(String s)//�ַ���ת��ΪASCII
11.public String UnicodetoGB(String s)//Unicodeת����GB��Դ��
12.public int toASCII(char c)//�ַ�ת��ΪASCII
public String toASCII(String s)
13.public int byteLength(String)//ȡ���ַ��ֽڳ���
14.public String strByteCopy(String str,int nEnd)//ȡ���ַ�����ͷ��ʼָ�������Ӵ�
15.public boolean strMatch(String motherStr,String childStr)//ȡ���ַ�����ͷ��ʼָ�������Ӵ�  new ��
16.public String strDistinct(String str)//��str���ظ���ȥ��    new ����
17 	public Vector simplify(String[] str) //�������� 0 �������е�
18 	public Vector simplify(Vector vstr) //�������� 0 �������е�


History:
***********************************************************/
package supma.util;

import java.util.*;
import java.text.*;
import java.lang.*;
import sun.io.*;
import supma.common.CommonWenZi;

public class StringUtil{
	
	private static final long serialVersionUID = 13L;
	
	/**���캯��*/
	public StringUtil()
	{
	}

	public static String nulltoblank(String str) {
		if(str==null){
			return "";
		}else{
			return str;
		}
	}
	//����1000��.����1.0ǧ��
	public static String toKG(String str) {
		if (str==null || "".equals(str)){
			return "";
		}else{
			str=str.trim();
		}
		int a = Integer.parseInt(str);
		if(a<1000){
			return str+CommonWenZi.wight_ke;
		}else{
			System.out.println("a/1000:"+a/1000);
			return ""+(a/1000)+CommonWenZi.wight_keKG;
		}
	}
	
	//���ӣ�dbBean.cutStringAddChar(dbBean.fillInRight(sName2,' ',14),'.',14);
	public static String cutStringAddChar(String data, char fill, int len) {
		if (len == 0)
			return data;
		if (data == null)
			data = "";
		if(data.length()<= len){
			return data;
		}else{
			String temp = data.substring(0,len-1)+fill+fill;
			return temp;
		}
	}
	
	/**��nullת��Ϊ""*/
	public String toString(String str)
	{
		if(str==null)str = "";
		if(str.equals("null"))str = "";
		str = str.trim();
		return str;
	}

	/**ת������*/
	public String toGBK(String str)
	{
		try
		{
			str=new String(str.getBytes("ISO-8859-1"),"GBK");
		}catch (Exception e) {}
		return str;
	}

	/**UTF8*/
	public String toUtf8String(String src)
	{
		byte[] b = src.getBytes();
		char[] c = new char[b.length];
		for(int i=0;i<b.length;i++)
		{
			c[i] = (char)(b[i]&0x00FF);
		}
		return new String(c);
	}

	public String toASCII(String str)
	{
		try
		{
			str=new String(str.getBytes("GBK"),"ISO-8859-1");
		}catch (Exception e) {}
		return str;
	}
	/**�ָ��ַ���*/
	public String[] splitStr(String str,char c)
	{
		str+=c;
		int n=0;
		for(int i=0;i<str.length();i++)
		{
			if(str.charAt(i)==c)n++;
		}

		String out[] = new String[n];

		for(int i=0;i<n;i++)
		{
			int index = str.indexOf(c);
			out[i] = str.substring(0,index);
			str = str.substring(index+1,str.length());
		}
		return out;
	}

	/**ȡ��ϵͳʱ��*/
	public String getDateTime()
	{
		java.text.SimpleDateFormat f = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String time = f.format(new java.util.Date());
		return time;
	}

	/**�滻�ַ���*/
	public String Replace(String source, String oldString, String newString)
	{
       StringBuffer output = new StringBuffer();

       int lengthOfSource = source.length();   // Դ�ַ�������
       int lengthOfOld = oldString.length();   // ���ַ�������
       int posStart = 0;   // ��ʼ����λ��
       int pos;            // ���������ַ�����λ��

       while ((pos = source.indexOf(oldString, posStart)) >= 0) {
           output.append(source.substring(posStart, pos));
           output.append(newString);
           posStart = pos + lengthOfOld;
       }
       if (posStart < lengthOfSource) {
           output.append(source.substring(posStart));
       }
       return output.toString();
	}

	/**ȡ���������ڵ�����֮��*/
	public long getDaysInterval(Date d1,Date d2)
	{
		return (d2.getTime()-d1.getTime())/86400000;
	}

	/**���ַ�����ʽ��Ϊ�̶�����*/
	public String toLengthStr(String instr,int len)
	{
		int n = instr.length();
		for(int i=0;i<(len-n);i++)
		{
			instr = " "+instr;
		}
		return instr;
	}
	/**���ַ�����ʽ��Ϊ�̶�����(�ұ߲��ո�)*/
	public String toLengthStrRight(String instr,int len)
	{
		int n = instr.length();
		for(int i=0;i<(len-n);i++)
		{
			instr = instr+" ";
		}
		return instr;
	}
	/*ASCIIת��Ϊ�ַ���*/
//	public String AsciiToChineseString(String s)
//	{
//		char[] orig = s.toCharArray();
//		byte[] dest = new byte[orig.length];
//		for (int i=0;i<orig.length;i++)
//		dest[i] = (byte)(orig[i]&0xFF);
//		try
//		{
//			ByteToCharConverter toChar = ByteToCharConverter.getConverter("gb2312");
//			return new String(toChar.convertAll(dest));
//		}
//		catch (Exception e)
//		{
//			System.out.println(e);
//			return s;
//		}
//	}

	/**�ַ���ת��ΪASCII*/
//	public String ChineseStringToAscii(String s)
//	{
//		try
//		{
//			CharToByteConverter toByte = CharToByteConverter.getConverter("gb2312");
//			byte[] orig = toByte.convertAll(s.toCharArray());
//			char[] dest = new char[orig.length];
//			for (int i=0;i<orig.length;i++)
//				dest[i] = (char)(orig[i] & 0xFF);
//			return new String(dest);
//		}
//		catch (Exception e)
//		{
//			System.out.println(e);
//			return s;
//		}
//	}

	/**Unicodeת����GB��Դ��*/
	public String UnicodetoGB(String s)
	{
		StringBuffer  sb  =  new  StringBuffer();
		boolean  escape  =  false;
		for(int  i=0;  i<s.length();  i++)
		{
			char  c  =  s.charAt(i);
			switch  (c)
			{
				case  '\\': escape = true; break;
				case  'u':
				case  'U':
				if(escape)
				{
					try
					{
						 sb.append((char)Integer.parseInt(s.substring(i+1,i+5),16));
						 escape  =  false;
					}
					catch(NumberFormatException e)
					{
						 throw new IllegalArgumentException();
					}
					i += 4;
				}
				else
				{
					sb.append(c);
				}
				break;
				default:sb.append(c); break;
			}
		}
		return  sb.toString();
	}
	/*��str���ظ���ȥ��*/
	public String strDistinct(String str)
	{
		String[] strArr=str.split(",");
		String   strAim = ",";
		for(int i=0;i<strArr.length;i++)
		{
			if(strArr[i].equals(""))
				continue;
			if (strAim.indexOf(","+strArr[i]+",")==-1)
			{
				strAim = strAim + strArr[i] + ",";
			}
		}
		if(!strAim.equals(","))
		strAim = strAim.substring(1,strAim.length()-1);
		else strAim = "";
		return strAim;
	}

	/**�ַ�ת��ΪASCII*/
	public int toASCII(char c)
	{
		int i = c;
		return i;
	}

	/**ȡ���ַ��ֽڳ���*/
	public int byteLength(String str)
	{
		return ((str.getBytes()).length);
	}

	/**ȡ���ַ�����ͷ��ʼָ�������Ӵ�*/
	public String strByteCopy(String str,int nEnd)
	{
		if(nEnd==0)
			return "";
		byte[] byteStr=str.getBytes();
		String strSub=new String(byteStr,0,nEnd);
		if (strSub.length()==0) strSub=new String(byteStr,0,nEnd-1);
		return strSub;
	}
        public boolean strMatch(String motherStr,String childStr)
        {
          boolean matched=false;
          int mLength=motherStr.length();
          int cLength=childStr.length();
          int starWith;
          if(mLength>=cLength){
            starWith=mLength-cLength;
            for(int i=0;i<=starWith;i++){
              matched=motherStr.startsWith(childStr,i);
              if(matched)break;
            }
          }
          return matched;
        }
/*

	//���ַ���ת��Ϊ�ԣգԣƣ�����
	public static String toUtf8String(String s) {
                 StringBuffer sb = new StringBuffer();
                 for (int i=0;i<s.length();i++) {
                   char c = s.charAt(i);
                   if (c >= 0 && c <= 255) {
                     sb.append(c);
                   }
                   else {
                     byte[] b;
                     try {
                       b = Character.toString(c).getBytes("utf-8");
                     }
                     catch (Exception ex) {
                       System.out.println(ex);
                       b = new byte[0];
                     }
                     for (int j = 0; j < b.length; j++) {
                       int k = b[j];
                       if (k < 0)
                         k += 256;
                       sb.append("%" + Integer.toHexString(k).
                                 toUpperCase());
                     }
                   }
                 }
                 return sb.toString();
       }
*/
	public Vector simplify(String[] str){
		Vector vect = new Vector();
		for(int i=0;i<str.length;i++)
			vect.add(str[i]);
		for(int i=0;i<vect.size();i++){
			String[] s1 = ((String)vect.get(i)).split("_");
			for(int j=i+1;j<vect.size();){
				boolean out = true;
				String[] s2 = ((String)vect.get(j)).split("_");
				for(int k=0;k<s1.length;k++){
					if(!s1[k].equals(s2[k])&&!s1[k].equals("0")){
						out = false;
						break;
					}
				}
				if(out)
					vect.remove(j);
				else
					j++;
			}
		}
		return vect;
	}
	public Vector simplify(Vector vstr){
		Vector vect = new Vector();
		for(int i=0;i<vstr.size();i++)
			vect.add(vstr.get(i));
		for(int i=0;i<vect.size();i++){
			String[] s1 = ((String)vect.get(i)).split("_");
			for(int j=i+1;j<vect.size();){
				boolean out = true;
				String[] s2 = ((String)vect.get(j)).split("_");
				for(int k=0;k<s1.length;k++){
					if(!s1[k].equals(s2[k])&&!s1[k].equals("0")){
						out = false;
						break;
					}
				}
				if(out)
					vect.remove(j);
				else
					j++;
			}
		}
		return vect;
	}
	//0 to empty
	public static String zeroToEmpty(String data) {
		if("0".equals(data)){
			return "";
		}
		return data;
	}
	
	//empty to 0
	public static String emptyToZero(String data) {
		if(data==null || "".equals(data)){
			return "0";
		}
		return data;
	}
	
	public static String fillInRight(String data, char fill, int len) {
		if (len == 0)
			return data;
		if (data == null)
			data = "";
		if (data.getBytes().length > len)
			return data;
		int diffLen = len - data.getBytes().length;
		StringBuffer fmtStr = new StringBuffer(data);
		for (int i = 0; i < diffLen; i++)
			fmtStr.append(fill);
		return fmtStr.toString();
	}
	
	public static String fillInLeft(String data, char fill, int length) {

		StringBuffer fmtData = new StringBuffer();
		int zeroSu;

		if (data == null) {

			data = "";
			zeroSu = length;

		} else if (data.getBytes().length > length) {

			zeroSu = 0;

		} else {

			zeroSu = length - data.getBytes().length;

		}

		for (int i = 0; i < zeroSu; i++)
			fmtData.append(fill);

		fmtData.append(data);
		return fmtData.toString();

	}

	/**
	 * �ж�������У�������ȷ��
	 *	�������ʽ��12λ��+1λУ���룻��9771671216014
	 *	�жϷ������������������ż�����λ�ϵ��������ͣ�
	 *	������ĺͳ�3���ٰ�������������ϵ��������ͣ�
	 *	������ĺͼ��ϸղ�ż������ϵ�����Ȼ��ó��͡�
	 *	����10��ȥ����͵ĸ�λ�����͵ó�У���룬
	 *	�����10��ΪУ����Ϊ0
	 * @author ----
	 *
	 */
	public static boolean isBarCode(String code){
		boolean results = true;
		if(code==null || "".equals(code)){
			return false;
		}
		if(!code.matches("^[0-9]+$") ){//��ȫΪ����
			return false;
		}
		if(code.length()!=13){//λ����13
			return false;
		}
		//żλ���ĺ�*3
		int m = 0;
		for(int  i= 1;i<code.length()-1;i+=2){
			m += (code.charAt(i)-'0'); 
		}
		m=m*3;
		//��λ���ĺ�
		int n = 0;
		for(int  i= 0;i<code.length()-1;i+=2){
			n += (code.charAt(i)-'0'); 
		}
		//У��������ֵ
		int c = 0;
		if(((m+n)%10)==0){
			c = 0;
		}else{
			c = 10-((m+n)%10);
		}
		if(c==(code.charAt(code.length()-1)-'0')){
			results=true;
		}else{
			results=false;
		}
			
		return results;
	}
	
	public static void main(String args[]) throws Exception
	{
		StringUtil mb = new StringUtil();
		System.out.println("begin\r\n\r\n");
	/*
		System.out.println("Please  input  string  to  be  translated");
        String  oldStr  =  null;
		java.io.BufferedReader in  = new java.io.BufferedReader(new java.io.InputStreamReader(System.in));
		oldStr  =  in.readLine();
		while(oldStr!=null)
		{
			System.out.println(mb.UnicodetoGB(oldStr));
			oldStr  =  in.readLine();
		}
                */
		System.out.print("fdsafd");
		System.out.print("\rggggggggggggggg");
		System.out.println("\rend                 ");
	}
};