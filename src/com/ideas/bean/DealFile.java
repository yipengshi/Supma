/**********************************************************
Copyright (C),2003-10-12, Beijing  USTB.
All rights reserved.
Filename: DealFile.java
Author: ���б�   
Version 1.0
Date:2003-10-12
Description:�ļ�����ش������
Other:
Variable List:
1.String infile = "";
2.String outfile = "";
3.FileInputStream fis = null;//�����ļ���
4.FileOutputStream fos = null;//����ļ���

Function List:
//��ʼ��������Ϣ
1.public DealFile()//���캯��
2.public void connFIS(String i)//���������ļ���
3.public void connFOS(String o)//��������ļ���
4.public void closeFIS()//�ر������ļ���
5.public void closeFOS()//�ر�����ļ���
6.public FileInputStream getFIS()//ȡ��������
7.public FileOutputStream getFOS()//ȡ�������

//�ļ�����
8.public void deleteFile(String df)//ɾ���ļ�
9.public void movefile_FileStream()//�����ļ�����fis->fos
10.public void movefile_BufferedByteStream()//�����ļ�����fis->fos
11.public void movefile_BufferedCharStream()//�����ļ�����infile->outfile

//�ַ���������������
12.public String readCHStr()//���ļ��������ַ���infile->
13.public String readCHStr(InputStream is)//�����������ַ���
14.public FileInputStream toInputStream(String str)//���ַ���ת��Ϊ������
15.public void writeCHStr(String str)//д�����ַ������ļ�->outfile
16.public void writeCHStr(OutputStream os,String str)//д�����ַ������ļ�->outfile
17.public void appendCHStr(String outfile,String str)//׷�ӵ��ļ���β

//��λ��ָ�����
18.public long seekStrPos(long cur,String str)//��λ��cur��ʼ������һ��str��λ��
19.public long seekStrPos(String str)//��λĳһ���ַ������ļ��е�λ��(����)
20.public long seekStrPos(String str1,String str2)//��λ���ַ���str1��ʼ��һ���ַ���str2��λ��

//ȡ�Ӵ�����
21.public String substring(long pos,int len)//��posλ�ÿ�ʼȡ����Ϊlen���ַ���
22.public String substring(String str1,String str2,int len)//���ַ���str1��ʼ����str2��ĳ���Ϊlen���ַ���

History:
*/
package com.ideas.bean;

import java.io.*;

public class DealFile
{
	/**�����ļ�*/
	String infile = "";
	/**����ļ�*/
	String outfile = "";
	/**�����ļ���*/
	FileInputStream fis = null;
	/**����ļ���*/
	FileOutputStream fos = null;

	public DealFile()
	{
	}

    /**���������ļ���*/
	public void connFIS(String i)
	{
		try
		{
			infile = i;
			fis = new FileInputStream(infile);
		}catch(IOException ioe){System.out.println("����DealFile.connFIS()��������:\r\n"+ioe);}
	}

	//��������ļ���*/
	public void connFOS(String o)
	{
		try
		{
			outfile = o;
			fos = new FileOutputStream(outfile);
		}catch(IOException ioe){System.out.println("����DealFile.connFOS()��������:\r\n"+ioe);}
	}

	/**�ر������ļ���*/
	public void closeFIS()
	{
		try
		{
			if(fis!=null)fis.close();
		}catch(IOException ioe){System.out.println("����DealFile.closeFIS()��������:\r\n"+ioe);}
	}

	/**�ر�����ļ���*/
	public void closeFOS()
	{
		try
		{
			if(fos!=null)fos.close();
		}catch(IOException ioe){System.out.println("����DealFile.closeFOS()��������:\r\n"+ioe);}
	}

	
	/**ȡ��������*/
	public FileInputStream getFIS()
	{
		return fis;
	}

	/**ȡ�������*/
	public FileOutputStream getFOS()
	{
		return fos;
	}

	/**ɾ���ļ�*/
	public void deleteFile(String df)
	{
		File file = new File(df);
		file.delete();		
	}

	/**�����ļ�����fis->fos*/
	public void movefile_FileStream()
	{
		try
		{
			File f = new File(infile);
			byte b[]=new byte[(int)(f.length())];
			fis.read(b);
			fos.write(b);
		}catch(IOException ioe){System.out.println("����DealFile.movefile_FileStream()��������:\r\n"+ioe);}
	}

	/**�����ļ�����fis->fos*/
	public void movefile_BufferedByteStream()
	{
		try
		{
			BufferedInputStream in = new BufferedInputStream(fis);
			BufferedOutputStream out = new BufferedOutputStream(fos);
			int c;
			while((c=in.read())!=-1)
			{
				out.write(c);
			}
			in.close();
			out.close();
		}catch(IOException ioe){System.out.println("����DealFile.movefile_BufferedByteStream()��������:\r\n"+ioe);}
	}

	/**�����ļ�����infile->outfile*/
	public void movefile_BufferedCharStream()
	{
		try
		{
			BufferedReader in = new BufferedReader(new FileReader(infile));
			BufferedWriter out = new BufferedWriter(new FileWriter(outfile));
			int c;
			while((c=in.read())!=-1)
			{
				out.write(c);
			}
			in.close();
			out.close();
		}catch(IOException ioe){System.out.println("����DealFile.movefile_BufferedCharStream()��������:\r\n"+ioe);}
	}

	/**�������ַ���infile->*/
	public String readCHStr()
	{
		return readCHStr(fis);
	}

	/**�������ַ���infile->*/
	public String readCHStr(InputStream is)
	{
		String str = "";
		try
		{
			//����Unicode�ַ���
			InputStreamReader isw = new InputStreamReader(is);
			BufferedReader br = new BufferedReader(isw);

			//��Unicode�ַ���
			String s = "";
			while((s=br.readLine())!=null)
			{
				if(!str.equals(""))str = str + "\r\n" + s;
				else str = str + s;
			}

			br.close();
			isw.close();
		}catch(IOException ioe){System.out.println("����DealFile.readCHStr()��������:\r\n"+ioe);}
		return str;
	}

	/**���ַ���ת��Ϊ������*/
	public FileInputStream toInputStream(String str)
	{
		FileInputStream fis_t = null;
		try
		{
			//���ַ���д����ʱ�ļ����ٴ��ļ�����������
			FileOutputStream fos_t = new FileOutputStream("tmp.txt");
			writeCHStr(fos_t,str);
			fis_t = new FileInputStream("tmp.txt");
		}catch(IOException ioe){System.out.println("����DealFile.toInputStream()��������:\r\n"+ioe);}
		return fis_t;
	}

	/**д�����ַ������ļ�->outfile*/
	public void writeCHStr(String str)
	{
		writeCHStr(fos,str);
	}

	/**д�����ַ������ļ�->outfile*/
	public void writeCHStr(OutputStream os,String str)
	{
		try
		{
			//����Unicode�ַ���
			OutputStreamWriter osw = new OutputStreamWriter(os);
			BufferedWriter bw = new BufferedWriter(osw);

			//дUnicode�ַ���
			bw.write(str,0,str.length());
			bw.newLine();
			bw.close();
			osw.close();
		}catch(IOException ioe){System.out.println("����DealFile.writeCHStr()��������:\r\n"+ioe);}
	}
	/**׷�ӵ��ļ���β*/
	public void appendCHStr(String outfile,String str)
	{
		try
		{
			RandomAccessFile rf = new RandomAccessFile(outfile,"rw");
			rf.seek(rf.length());
			rf.writeBytes(str);
			rf.close();
		}catch(IOException ioe){System.out.println("����DealFile.appendCHStr()��������:\r\n"+ioe);}
	}

	/**��λ��cur��ʼ������һ��str��λ��*/
	public long seekStrPos(long cur,String str)
	{
		long fcur = cur;
		try
		{
			RandomAccessFile file = new RandomAccessFile(new File(infile),"r");			
			long flen = file.length();
			int slen = str.length();
			byte []b = new byte[slen];
			
			for(;fcur<flen;fcur++)
			{
				file.seek(fcur);

				//�ļ�βʣ�೤�Ȳ��ٹ�ʱ
				if((flen-fcur)<slen)
				{
					fcur = -1;
					break;
				}

				//�жϵ�ǰλ���Ƿ��ǣ��������������
				file.read(b,0,slen);
				String bstr = new String(b);
				if(str.equals(bstr))
					break;
			}
			file.close();
		}catch(IOException ioe){System.out.println("����DealFile.seekStrPos()��������:\r\n"+ioe);}
		return fcur;
	}
	/**��λĳһ���ַ������ļ��е�λ��(����)*/
	public long seekStrPos(String str)
	{
		return seekStrPos(0,str);
	}

	/**��λ���ַ���str1��ʼ��һ���ַ���str2��λ��*/
	public long seekStrPos(String str1,String str2)
	{
		long cur = seekStrPos(0,str1);
		return seekStrPos(cur,str2);
	}	

	/**��posλ�ÿ�ʼȥ����Ϊlen���ַ���*/
	public String substring(long pos,int len)
	{
		String str = "";
		try
		{
			RandomAccessFile file = new RandomAccessFile(new File(infile),"r");
			long flen = file.length();

			//�����ܷ���ʱ���ؿ�ֵ
			if(pos<0||(flen-pos)<len)return "";
			
			file.seek(pos);
			byte []b = new byte[len];
			file.read(b,0,len);
			str = new String(b);
			file.close();
		}catch(IOException ioe){System.out.println("����DealFile.substring()��������:\r\n"+ioe);}
		return str;
	}

	/**���ַ���str1��ʼ����str2��ĳ���Ϊlen���ַ���*/
	public String substring(String str1,String str2,int len)
	{
		long pos = seekStrPos(str1,str2);
		return substring(pos+str2.length(),len).trim();
	}

	public static void main(String args[]) throws IOException,ArrayIndexOutOfBoundsException
	{
		
		DealFile df = new DealFile();
		
		//*׷���ļ�����(һ����Ϊ����ļ����򿪣��������)
		//df.connFIS("out.txt");
		//String str = df.readCHStr()+"����׷��WWWWWWWWTTTTW�ҵĻ�";
		
		//df.connFOS("out.txt");
		//df.writeCHStr(str);
		//df.closeFIS();
		//df.closeFOS();

		//�����ļ�
		df.connFIS("out.txt");
		df.connFOS("in.txt");
		df.movefile_FileStream();
		//df.movefile_BufferedByteStream();
		//df.movefile_BufferedCharStream();
		df.closeFIS();
		df.closeFOS();

		//df.connFIS("out.txt");
		//System.out.println(df.substring("flyline","background-color:",7).toUpperCase());
		//df.closeFIS();
	}

	
};