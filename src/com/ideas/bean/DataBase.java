/**********************************************************
 Copyright (C),2003-10-12, Beijing  USTB.
 All rights reserved.
 Filename: Datebase.java
 Author: ���б�
 Version 1.0
 Date:2003-10-12
 Description:���ļ�Ϊ���ݿ�Ļ�������������
 Other:
 Variable List:
 1.Connection conn = null;//���ݿ����Ӷ���
 Function List:
 //��������
 1.public DataBase()//���캯��
 2.public void getConnPool()//ȡ�����ӳ�
 3.public void createConn(String url,String usr,String pwd)//����Oracle SQLServer�ȵ�����
 4.public void createConn(String url)//����Access����
 5.public void releaseConn()//�ͷ����ݿ�����
 6.public ResultSet QuerySQL(String sql)//��ѯ��¼
 7.public int ExecuteSQL(String sql)//ִ����ɾ�ĵ����
 //����ʹ�ú���
 8.public int makeID(String table,String field1,String field2,String value1,boolean type1)//����Ψһ���,type1Ϊfalseʱ��ʾ�����ֶ�Ϊ�ַ�������
  public int makeID(String table,String field1,String field2,String field3,String value1,String value2,boolean type1,boolean type2)
  public int makeID_Add1(String table,String field1,String field2,String value1,boolean type1)//����Ψһ���,type1Ϊfalseʱ��ʾ�����ֶ�Ϊ�ַ�������
  public int makeID_Add1(String table,String field1,String field2,String field3,String value1,String value2,boolean type1,boolean type2)
 9.public int toID(String table,String field1,String field2,String value1)//������ת��Ϊ���
 10.public String toName(String table,String field1,String field2,String value)//�����ת��Ϊ����
 11.public Vector (String sql,int page,int records)//��ҳʱȡ��һҳ��������
 12.public int setSort(String table,String field1,String field2,String wherestr,String orderstr,boolean b)//Ϊĳһ���ֶν�����������
 //��ѯ�����LOB����
 13.public String QueryCLOB(String table,String wherestr,String clobfield)//��ѯCLOB����ֵ
 14.public int UpdateCLOB(String table,String wherestr,String blobfield,String blobvalue)//�޸�CLOB����ֵ
 15.public String QueryBLOB(String table,String wherestr,String blobfield)//��ѯBLOB����ֵ
 16.public int UpdateBLOB(String table,String wherestr,String blobfield,String blobvalue)//�޸�BLOB����ֵ
 17.public String QueryBLOB_JNDI(String table,String wherestr,String blobfield)//��ѯBLOB����ֵ
 178.public int UpdateBLOB_JNDI(String table,String wherestr,String blobfield,String blobvalue)//�޸�BLOB����ֵ
 //LOB���ͻ������������ļ���
 19.public int clobInsert(String sql,String table,String wherestr,String clobfield,String infile)//�����ݿ��в���һ���µ�CLOB����
 20.public int clobModify(String table,String wherestr,String clobfield,String infile)//�޸�CLOB��������ԭCLOB��������Ͻ��и���ʽ���޸ģ�
 21.public int clobReplace(String table,String wherestr,String clobfield,String infile)//�滻CLOB���󣨽�ԭCLOB�������������һ��ȫ�µ�CLOB����
 22.public int blobInsert(String sql,String table,String wherestr,String blobfield,String infile)//�����ݿ��в���һ���µ�BLOB����
 23.public int blobModify(String table,String wherestr,String blobfield,String infile)//�޸�BLOB��������ԭBLOB��������Ͻ��и���ʽ���޸ģ�
 24.public int blobReplace(String table,String wherestr,String blobfield,String infile)//�滻BLOB���󣨽�ԭBLOB�������������һ��ȫ�µ�BLOB����
 //���ݿ�ṹ��Ϣ
 25.public Hashtable getDataBaseInfo()//���ݿ���Ϣ
 26;public Vector getTableList()//���ݱ��б�
 27.public Vector getTableStruct(String table)//���ݱ�Ľṹ
 28.public Vector getResultSetData(ResultSet rs)//ȡ�����ݼ�����
 //��sql���ķֲ�����
 29.public void prepareStatement(String sql)//������������
 30.public void executeQuery()//ִ�в�ѯ
 31.public boolean next()//ת����һ��
 32.public String getObject(String field,String sqlType)//ȡ�����ݲ�������������ת��Ϊ�ַ���
 33.public void setObject(int index,String value,String sqlType)//�����������ͱ��浽���ݿ�
 34.public void executeUpdate()//ִ�и���
 35.public void closePstm()//�ر���������
 36.public void closeRs()//�ر��α�
 History:
 date:2003-11-21
 name:liumei
 action:�޸�getOnePage(String sql,int page,int records)//��ҳʱȡ��һҳ��������
 ÿҳ�ļ�¼��ʾ�������ֵ���ȡ,��ȥ��records������
 ***********************************************************/
package com.ideas.bean;

import java.util.*;
import java.text.*;
import java.sql.*;
import java.io.*;
import java.lang.*;
import javax.naming.*;
//import oracle.jdbc.driver.OracleResultSet;
//import oracle.sql.*;
import com.ideas.util.*;

/**
 * ���ļ�Ϊ���ݿ�Ļ�������������
 * @author ���б�
 * @version 1.0-hg
 */
public class DataBase extends Object
{
	Connection conn = null;
	PreparedStatement pstm = null;
	ResultSet rs = null;


	/**���캯��*/
	public DataBase()
	{
	}

	/**ȡ�����ӳ�*/
	public void getConnPool()
	{
		try
		{
			Context initCtx = new InitialContext();
			Context ctx = (Context)initCtx.lookup("java:comp/env");

			/**��ȡ���ӳض���*/
			Object obj = (Object)ctx.lookup("jdbc/OracleDB");

			/**����ת��*/
			javax.sql.DataSource ds = (javax.sql.DataSource)obj;
			conn = ds.getConnection();

			/**�������Ӽ���*/
			//	ParentBean.curnum++;
			//	ParentBean.sumnum++;
			ParentBean.showConnNUM(true);
		}
		catch(NamingException e)
		{
			System.out.println("�����ݳ�ȡ�����ݿ�����ʱ����;\r\n����Ϊ:" + e);
		}
		catch(SQLException e)
		{
			System.out.println("�����ݳ�ȡ�����ݿ�����ʱ����;\r\n����Ϊ:" + e);
		}
	}

	/**�����ҵ����ӳ�*/
	public boolean getMyConnPool()
	{
		return true;
	}

	/**�ͷ��ҵ����ӳ�*/
	public boolean releaseMyConnPool()
	{
		
		return true;
	}



	 /**����Oracle SQLServer�ȵ�����*/
	 public void createConn(String drv, String url, String usr, String pwd)
	 {
		 try
		 {
			 Class.forName(drv).newInstance();

			 conn = DriverManager.getConnection(url, usr, pwd);


			 /**�������Ӽ���*/
			 ParentBean.showConnNUM(true);
		 }
		 catch(ClassNotFoundException ec)
		 {
			 System.out.println("�����������ݿ�����ʱ����;\r\n����Ϊ:" + ec);
		 }
		 catch(SQLException e)
		 {
			 System.out.println("�����������ݿ�����ʱ����;\r\n����Ϊ:" + e);
		 }
		 catch(Exception et)
		 {
			 System.out.println("�����������ݿ�����ʱ����;\r\n����Ϊ:" + et);
		 }
	 }

	/**����Access����*/
	public void createConn(String drv, String url)
	{
		try
		{
			Class.forName(drv).newInstance();
			conn = DriverManager.getConnection(url);

			/**�������Ӽ���*/
			ParentBean.showConnNUM(true);
		}
		catch(ClassNotFoundException ec)
		{
			System.out.println("�����������ݿ�����ʱ����;\r\n����Ϊ:" + ec);
		}
		catch(SQLException e)
		{
			System.out.println("�����������ݿ�����ʱ����;\r\n����Ϊ:" + e);
		}
		catch(Exception et)
		{
			System.out.println("�����������ݿ�����ʱ����;\r\n����Ϊ:" + et);
		}
	}

	/**�ͷ����ݿ�����*/
	public void releaseConn()
	{
		try
		{
			if(conn != null)
			{
				conn.close();

				/**�������Ӽ���*/
				ParentBean.showConnNUM(false);
			}
		}
		catch(SQLException e)
		{
			System.out.println("�ر����ݿ�����ʱ����;\r\n����Ϊ:" + e);
		}
	}

	/**��ѯ��¼*/
	public ResultSet QuerySQL(String sql)
	{
		ResultSet rs = null;
		try
		{
			pstm = conn.prepareStatement(sql);
			rs = pstm.executeQuery();
		}
		catch(SQLException sqle)
		{
			System.out.println("����SQL��� " + sql + " ʱ����;\r\n����Ϊ:" + sqle);
		}
		return rs;
	}

	/**ִ����ɾ�ĵ����*/
	public int ExecuteSQL(String sql)
	{
		try
		{
            boolean defaultCommit = conn.getAutoCommit();
			conn.setAutoCommit(false);
			pstm = conn.prepareStatement(sql);
			pstm.executeUpdate();
			conn.commit();
            conn.setAutoCommit(defaultCommit);
			pstm.close();
		}
		catch(SQLException sqle)
		{
			System.out.println("����SQL��� " + sql + " ʱ����;\r\n����Ϊ:" + sqle);
			return sqle.getErrorCode();
		}
		return 0;
	}

	/**����Ψһ���*/
	public int makeID(String table, String field1, String field2, String value1,
					  boolean type1)
	{
		int out = -1;
		String sql = "";
		try
		{
			//ֻ��Ψһ����field1
			sql = "select " + field1 + " as ID from " + table + " order by " +
				field1;
			//����������field1��field2
			if(!value1.equals(""))
			{ //����һ���ֶβ���ʱ����Ϊ������ѯ�ڶ����ֶ�
				sql = "select " + field2 + " as ID from " + table + " where " +
					field1 +
					"=" + value1 + " order by " + field2;
				if(!type1)
				{ //���ַ���ʱ ��type1��Ϊfalse
					sql = "select " + field2 + " as ID from " + table +
						" where " +
						field1 + "='" + value1 + "' order by " + field2;
				}
			}
			pstm = conn.prepareStatement(sql);
			rs = pstm.executeQuery();
			int t1 = 1;
			int t2 = 2;
			if(rs.next())
			{ //�м�¼
				t1 = rs.getInt("ID");
				out = t1;
				boolean bool = false;
				while(rs.next())
				{ //��ֹһ����¼
					bool = true;
					t2 = rs.getInt("ID");
					if((t2 - t1) > 1)
					{
						break; //���t2��t1������1,������ȥ,�±��Ϊt1++��������**��
					}
					t1 = t2; //����t2����t1
				}
				if(!bool)
				{ //���ֻ��һ����¼
					if(t1 > 1)
					{
						t1 = 1; //������м�¼��ID�Ŵ���1�����±����Ϊ1
					}
					else
					{
						t1++;
					}
				}
				else
				{
					t1++; //**
				}
			}
			if(out > 1)
			{
				out = 1;
			}
			else
			{
				out = t1;

			}
			rs.close();
			pstm.close();
		}
		catch(SQLException sqle)
		{
			System.out.println("����SQL��� " + sql + " ʱ����;\r\n����Ϊ:" + sqle);
		}
		return out;
	}

	/**����Ψһ���*/
	public int makeID_Add1(String table, String field1, String field2,
						   String value1, boolean type1)
	{
		int out = -1;
		String sql = "";
		try
		{
			//ֻ��Ψһ����field1
			sql = "select max(" + field1 + ")+1 as ID from " + table +
				" order by " +
				field1;
			//����������field1��field2
			if(!value1.equals(""))
			{ //����һ���ֶβ���ʱ����Ϊ������ѯ�ڶ����ֶ�
				sql = "select (" + field2 + ")+1 as ID from " + table +
					" where " +
					field1 + "=" + value1 + " order by " + field2;
				if(!type1)
				{ //���ַ���ʱ ��type1��Ϊfalse
					sql = "select (" + field2 + ")+1 as ID from " + table +
						" where " +
						field1 + "='" + value1 + "' order by " + field2;
				}
			}
			pstm = conn.prepareStatement(sql);
			rs = pstm.executeQuery();
			if(rs.next())
			{ //�м�¼
				out = rs.getInt(1);
			}

			rs.close();
			pstm.close();
		}
		catch(SQLException sqle)
		{
			System.out.println("����SQL��� " + sql + " ʱ����;\r\n����Ϊ:" + sqle);
		}
		return out;
	}

	public int makeID(String table, String field1, String field2, String field3,
					  String value1, String value2, boolean type1,
					  boolean type2)
	{
		int out = -1;
		String sql = "";
		try
		{
			//ֻ��Ψһ����field1
			sql = "select " + field1 + " as ID from " + table + " order by " +
				field1;
			//����������field1��field2
			if(!value1.equals(""))
			{ //����һ���ֶβ���ʱ����Ϊ������ѯ�ڶ����ֶ�
				sql = "select " + field2 + " as ID from " + table + " where " +
					field1 +
					"=" + value1 + " order by " + field2;
				if(!type1)
				{ //���ַ���ʱ ��type1��Ϊfalse
					sql = "select " + field2 + " as ID from " + table +
						" where " +
						field1 + "='" + value1 + "' order by " + field2;
				}
			}
			if(!value2.equals(""))
			{ //���ڶ����ֶβ���ʱ����Ϊ������ѯ�������ֶ�
				sql = "select " + field3 + " as ID from " + table + " where " +
					field1 +
					"=" + value1 + " and " + field2 + "=" + value2 +
					" order by " +
					field3;
				if(!type2)
				{ //���ַ���ʱ ��type1��Ϊfalse
					sql = "select " + field3 + " as ID from " + table +
						" where " +
						field1 + "='" + value1 + "' and " + field2 + "='" +
						value2 +
						"' order by " + field3;
				}
			}
			pstm = conn.prepareStatement(sql);
			rs = pstm.executeQuery();
			int t1 = 1;
			int t2 = 2;
			if(rs.next())
			{ //�м�¼
				t1 = rs.getInt("ID");
				out = t1;
				boolean bool = false;
				while(rs.next())
				{ //��ֹһ����¼
					bool = true;
					t2 = rs.getInt("ID");
					if((t2 - t1) > 1)
					{
						break; //���t2��t1������1,������ȥ,�±��Ϊt1++��������**��
					}
					t1 = t2; //����t2����t1
				}
				if(!bool)
				{ //���ֻ��һ����¼
					if(t1 > 1)
					{
						t1 = 1; //������м�¼��ID�Ŵ���1�����±����Ϊ1
					}
					else
					{
						t1++;
					}
				}
				else
				{
					t1++; //**
				}
			}
			if(out > 1)
			{
				out = 1;
			}
			else
			{
				out = t1;

			}
			rs.close();
			pstm.close();
		}
		catch(SQLException sqle)
		{
			System.out.println("����SQL��� " + sql + " ʱ����;\r\n����Ϊ:" + sqle);
		}
		return out;
	}

	public int makeID_Add1(String table, String field1, String field2,
						   String field3, String value1, String value2,
						   boolean type1, boolean type2)
	{
		int out = -1;
		String sql = "";
		try
		{
			//ֻ��Ψһ����field1
			sql = "select max(" + field1 + ") as ID from " + table +
				" order by " +
				field1;
			//����������field1��field2
			if(!value1.equals(""))
			{ //����һ���ֶβ���ʱ����Ϊ������ѯ�ڶ����ֶ�
				sql = "select max(" + field2 + ") as ID from " + table +
					" where " +
					field1 + "=" + value1 + " order by " + field2;
				if(!type1)
				{ //���ַ���ʱ ��type1��Ϊfalse
					sql = "select max(" + field2 + ") as ID from " + table +
						" where " +
						field1 + "='" + value1 + "' order by " + field2;
				}
			}
			if(!value2.equals(""))
			{ //���ڶ����ֶβ���ʱ����Ϊ������ѯ�������ֶ�
				sql = "select max(" + field3 + ") as ID from " + table +
					" where " +
					field1 + "=" + value1 + " and " + field2 + "=" + value2 +
					" order by " + field3;
				if(!type2)
				{ //���ַ���ʱ ��type1��Ϊfalse
					sql = "select max(" + field3 + ") as ID from " + table +
						" where " +
						field1 + "='" + value1 + "' and " + field2 + "='" +
						value2 +
						"' order by " + field3;
				}
			}
			pstm = conn.prepareStatement(sql);
			rs = pstm.executeQuery();
			if(rs.next())
			{ //�м�¼
				out = rs.getInt("ID");
			}

			rs.close();
			pstm.close();
		}
		catch(SQLException sqle)
		{
			System.out.println("����SQL��� " + sql + " ʱ����;\r\n����Ϊ:" + sqle);
		}
		return out;
	}

	/**������ת��Ϊ���*/
	public int toID(String table, String field1, String field2, String value1)
	{
		int out = -1;
		String sql = "";
		try
		{
			sql = "select " + field2 + " from " + table + " where " + field1 +
				"='" +
				value1 + "'";
			pstm = conn.prepareStatement(sql);
			rs = pstm.executeQuery();
			if(rs.next())
			{
				out = rs.getInt(field2);
			}
			rs.close();
			pstm.close();
		}
		catch(SQLException sqle)
		{
			System.out.println("����SQL��� " + sql + " ʱ����;\r\n����Ϊ:" + sqle);
		}
		return out;
	}

	/**�����ת��Ϊ����*/
	public String toName(String table, String field1, String field2,
						 String value1)
	{
		String out = "";
		String sql = "";
		try
		{
			sql = "select " + field2 + " from " + table + " where " + field1 +
				"='" +
				value1 + "'";
			pstm = conn.prepareStatement(sql);
			rs = pstm.executeQuery();
			if(rs.next())
			{
				out = (new DealString()).toString(rs.getString(field2));
			}
			rs.close();
			pstm.close();
		}
		catch(SQLException sqle)
		{
			System.out.println("����SQL��� " + sql + " ʱ����;\r\n����Ϊ:" + sqle);
		}
		return out;
	}

	/**��ҳʱȡ��һҳ��������*/
	public Vector getOnePage(String sql, int page, int records)
	{
		//��һ��Ϊ��ҳ��*/
	   //�ڶ�...��ΪHashtable*/
	  Vector vect = new Vector();
		try
		{
			pstm = conn.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE,
										 ResultSet.CONCUR_UPDATABLE);
			rs = pstm.executeQuery();
			int rows = 0;
			while(rs.next())
			{
				rows++;
			}
			int sum = rows / records;
			if(rows % records != 0 || rows == 0)
			{
				sum++;
			}
			vect.add("" + rows);
			vect.add("" + sum);

			//�Ƶ���ǰ��
			pstm.close();
			rs.close();

			pstm = conn.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE,
										 ResultSet.CONCUR_UPDATABLE);
			rs = pstm.executeQuery();
			rows = (page - 1) * records;
			rs.absolute(rows+1);

			DealString ds = new DealString();

			//��ѯ��ǰҳ
			int j = 0;
			do
			{
				if(rs==null||j == records||rs.getRow()==0)
				{
					break;
				}
				j++;

				ResultSetMetaData rsmd = rs.getMetaData();
				int cols = rsmd.getColumnCount();
				Hashtable hash = new Hashtable();
				for(int i = 1; i <= cols; i++)
				{
					String field = ds.toString(rsmd.getColumnName(i));
					String value = ds.toString(rs.getString(i));
					hash.put(field, value);
				}
				vect.add(hash);
			}while(rs.next());
			rs.close();
			pstm.close();
		}
		catch(SQLException sqle)
		{
			System.out.println("ִ��SQL��� " + sql + " ��ҳ���� " + page +
							   " ҳʱ����;����Ϊ:" +
							   sqle);
		}
		return vect;
	}

	/**Ϊĳһ���ֶν�����������*/
	public int setSort(String table, String field1, String field2,
					   String wherestr, String orderstr, boolean b)
	{
		//д�����к�,field2ΪΨһ�ֶ�*/
	   try
	   {
		   String sql = "select " + field2 + " from " + table;
		   if(!wherestr.equals(""))
		   {
			   sql += " where " + wherestr;
		   }
		   sql += " " + orderstr;

		   pstm = conn.prepareStatement(sql);
		   rs = pstm.executeQuery();
		   PreparedStatement pstm_t = null;
		   int i = 1;
		   while(rs.next())
		   {
			   if(b)
			   { //Ϊfield2����
				   sql = "update " + table + " set " + field1 + "=" + i +
					   " where " +
					   field2 + "=" + rs.getString(1);
			   }
			   else
			   { //Ϊfield2�ַ���
				   sql = "update " + table + " set " + field1 + "=" + i +
					   " where " +
					   field2 + "='" + rs.getString(1) + "'";
			   }
			   pstm_t = conn.prepareStatement(sql);
			   pstm_t.executeUpdate();
			   i++;
		   }

		   pstm_t.close();
		   rs.close();
		   pstm.close();
	   }
	   catch(SQLException sqle)
	   {
		   System.out.println("����MyDataBase.setSort()��������:\r\n" + sqle);
	   }
		return 0;
	}

	/**��ѯCLOB����ֵ*/
	public String QueryCLOB(String table, String wherestr, String clobfield)
	{
		String out = "";
		
		return out;
	}

	/**�޸�CLOB����ֵ*/
	public int UpdateCLOB(String table, String wherestr, String clobfield,
						  String clobvalue)
	{
		
		return 0;
	}

	/**��ѯBLOB����ֵ*/
	public String QueryBLOB(String table, String wherestr, String blobfield)
	{
		String out = "";
		
		return out;
	}

	/**�޸�BLOB����ֵ*/
	public int UpdateBLOB(String table, String wherestr, String blobfield,
						  String blobvalue)
	{
		
		return 0;
	}

	/**��ѯBLOB����ֵ*/
	public String QueryBLOB_JNDI(String table, String wherestr,
								 String blobfield)
	{
		String sql = "select " + blobfield + " from " + table;
		if(!wherestr.equals(""))
		{
			sql += " where " + wherestr;
		}
		prepareStatement(sql);
		executeQuery();
		next();
		//InputStream is = rs.getBinaryStream("ziduan");
		//return (new DealFile()).readCHStr(is);
		String out = getObject(blobfield, "BLOB");
		closeRs();
		closePstm();
		return out;
	}

	/**�޸�BLOB����ֵ*/
	public int UpdateBLOB_JNDI(String table, String wherestr, String blobfield,
							   String blobvalue)
	{

		String sql = "UPDATE " + table + " set " + blobfield + "=?";
		if(!wherestr.equals(""))
		{
			sql += " where " + wherestr;
		}
		prepareStatement(sql);
		setObject(1, blobvalue, "BLOB");
		executeUpdate();
		closePstm();
		return 0;
	}

	/**�����ݿ��в���һ���µ�CLOB����*/
	public int clobInsert(String sql, String table, String wherestr,
						  String clobfield, String infile)
	{
		int out = ExecuteSQL(sql); //Insert���
		out = clobModify(table, wherestr, clobfield, infile);
		return out;
	}

	/**�޸�CLOB��������ԭCLOB��������Ͻ��и���ʽ���޸ģ�*/
	public int clobModify(String table, String wherestr, String clobfield,
						  String infile)
	{
		
		return 0;
	}

	/**�滻CLOB���󣨽�ԭCLOB�������������һ��ȫ�µ�CLOB����*/
	public int clobReplace(String table, String wherestr, String clobfield,
						   String infile)
	{
		int out = 0;
		try
		{
			/* �趨���Զ��ύ */
			boolean defaultCommit = conn.getAutoCommit();
			conn.setAutoCommit(false);

			/* ���ԭCLOB���� */
			String sqlCommand = "update " + table + " set " + clobfield +
				"=EMPTY_CLOB()";
			if(!sqlCommand.equals(""))
			{
				sqlCommand += " where " + wherestr;
			}
			pstm = conn.prepareStatement(sqlCommand);
			pstm.executeUpdate();
			pstm.close();

			/* ��ʽ�ύ */
			conn.commit();

			/* �ָ�ԭ�ύ״̬ */
			conn.setAutoCommit(defaultCommit);

			out = clobModify(table, wherestr, clobfield, infile);
		}
		catch(SQLException sqle)
		{
			System.out.println("����DataBase.clobReplace()��������:\r\n" + sqle);
			return sqle.getErrorCode();
		}
		return out;
	}

	/**�����ݿ��в���һ���µ�BLOB����*/
	public int blobInsert(String sql, String table, String wherestr,
						  String blobfield, String infile)
	{
		int out = ExecuteSQL(sql); //Insert���
		out = clobModify(table, wherestr, blobfield, infile);
		return out;
	}

	/**�޸�BLOB��������ԭBLOB��������Ͻ��и���ʽ���޸ģ�*/
	public int blobModify(String table, String wherestr, String blobfield,
						  String infile)
	{
		
		return 0;
	}

	/**�滻BLOB���󣨽�ԭBLOB�������������һ��ȫ�µ�CLOB����*/
	public int blobReplace(String table, String wherestr, String blobfield,
						   String infile)
	{
		int out = 0;
		try
		{
			/* �趨���Զ��ύ */
			boolean defaultCommit = conn.getAutoCommit();
			conn.setAutoCommit(false);

			/* ���ԭCLOB���� */
			String sqlCommand = "update " + table + " set " + blobfield +
				"=EMPTY_BLOB()";
			if(!sqlCommand.equals(""))
			{
				sqlCommand += " where " + wherestr;
			}
			pstm = conn.prepareStatement(sqlCommand);
			pstm.executeUpdate();
			pstm.close();

			/* ��ʽ�ύ */
			conn.commit();

			/* �ָ�ԭ�ύ״̬ */
			conn.setAutoCommit(defaultCommit);

			out = blobModify(table, wherestr, blobfield, infile);
		}
		catch(SQLException sqle)
		{
			System.out.println("����DataBase.blobReplace()��������:\r\n" + sqle);
			return sqle.getErrorCode();
		}
		return out;
	}

	public Vector getData(String sql)
	{
		Vector vect = new Vector();
		try
		{
			pstm = conn.prepareStatement(sql);
			rs = pstm.executeQuery();

			DealString ds = new DealString();
			ResultSetMetaData rsmd = rs.getMetaData();
			int cols = rsmd.getColumnCount();
			while(rs.next())
			{
				Hashtable hash = new Hashtable();
				for(int i=1;i<=cols;i++)
				{
					String field = ds.toString(rsmd.getColumnName(i));
					String value = ds.toString(rs.getString(i));
					hash.put(field,value);
				}
				vect.add(hash);
			}
		}catch(SQLException sqle){System.out.println("ִ��DataBase.getData(String)ִ��SQL��� "+sql+" ʱ����;����Ϊ:"+sqle);}
		finally
		{
			closeRs();
			closePstm();
		}
		return vect;
	}

	/**���ݿ���Ϣ*/
	public Hashtable getDataBaseInfo()
	{
		Hashtable hash = new Hashtable();
		try
		{
			DatabaseMetaData dmd = conn.getMetaData();
			hash.put("1", dmd.getCatalogSeparator());
			hash.put("2", dmd.getCatalogTerm());
			hash.put("���ݿ���������", dmd.getDatabaseProductName());
			hash.put("���ݿ�汾", dmd.getDatabaseProductVersion());
			hash.put("5", dmd.getDefaultTransactionIsolation() + "");
			hash.put("�����汾(���)", dmd.getDriverMajorVersion() + "");
			hash.put("�����汾(��С)", dmd.getDriverMinorVersion() + "");
			hash.put("������", dmd.getDriverName());
			hash.put("�����汾", dmd.getDriverVersion());
			hash.put("10", dmd.getExtraNameCharacters());
			hash.put("11", dmd.getIdentifierQuoteString());
			hash.put("12", dmd.getMaxBinaryLiteralLength() + "");
			hash.put("������޶�", dmd.getMaxRowSize() + "");
			hash.put("����", dmd.getSchemaTerm());
			hash.put("���ں���", dmd.getTimeDateFunctions());
			hash.put("���ӵ�ַ", dmd.getURL());
			hash.put("�û���", dmd.getUserName());
		}
		catch(SQLException sqle)
		{
			System.out.println("����DataBase.getDataBaseInfo()��������:\r\n" + sqle);
		}
		catch(AbstractMethodError e)
		{
			System.out.println("����DataBase.getDataBaseInfo()��������:\r\n" + e);
		}
		return hash;
	}

	/**���ݱ��б�*/
	public Vector getTableList()
	{
		Vector vect = new Vector();
		try
		{
			if(ParentBean.DBType.equals("Access"))
			{
				//ExecuteSQL("create table tab(name char)");
				//ExecuteSQL("insert into tab(name) select name from MsysObjects");
				rs = QuerySQL("select name as TABLE_NAME from tab");
				//rs = QuerySQL("SELECT MSysObjects.Name as TABLE_NAME FROM MsysObjects WHERE (Left$([Name],1)<>'~') AND (Left$([Name],4) <> 'Msys') AND (MSysObjects.Type)=1 ORDER BY MSysObjects.Name");
			}
			else
			{
				DatabaseMetaData dmd = conn.getMetaData();
				String[] types = new String[1];
				types[0] = "TABLE";
				//types[1] = "VIEW"
				rs = dmd.getTables(null, ParentBean.DBName.toUpperCase(), "%",
								   types);
			}
			System.out.println("000" + getResultSetData(rs));
			while(rs.next())
			{
				vect.add((new DealString()).toString(rs.getString("TABLE_NAME")));
			}
			rs.close();
		}
		catch(SQLException sqle)
		{
			System.out.println("����DataBase.getTableList()��������:\r\n" + sqle +
							   sqle.getErrorCode());
		}
		catch(AbstractMethodError e)
		{
			System.out.println("����DataBase.getTableList()��������:\r\n" + e);
		}
		return vect;
	}

	/**���ݱ�Ľṹ*/
	public Vector getTableStruct(String table)
	{
		Vector vect = new Vector();
		try
		{
			/*
			 rs = QuerySQL("select * from "+table);
			 ResultSetMetaData rmd = rs.getMetaData();
			 int cols = rmd.getColumnCount();
			 for(int i=1;i<=cols;i++)
			 {
			 Hashtable hash = new Hashtable();
			 //hash.put("Ŀ¼��",rmd.getCatalogName(i));
			 //hash.put("�з���ֵ������",rmd.getColumnClassName(i));
			 hash.put("�ж����С",rmd.getColumnDisplaySize(i)+"");
			 //hash.put("�б�ǩ",rmd.getColumnLabel(i));
			 hash.put("�ֶ���",rmd.getColumnName(i));
			 hash.put("�����ͱ��",rmd.getColumnType(i)+"");
			 hash.put("�б�׼������",rmd.getColumnTypeName(i));
			 hash.put("�о�ȷ��",rmd.getPrecision(i)+"");
			 //hash.put("10",rmd.getScale(i)+"");
			 //hash.put("11",rmd.getSchemaName(i));
			 //hash.put("����",rmd.getTableName(i));
			 //hash.put("13",rmd.isAutoIncrement(i)+"");
			 //hash.put("��Сд����",rmd.isCaseSensitive(i)+"");
			 //hash.put("�Ƿ�Ϊ���",rmd.isCurrency(i)+"");
			 //hash.put("�Ƿ��д",rmd.isDefinitelyWritable(i)+"");
			 hash.put("�Ƿ��Ϊ��",rmd.isNullable(i)+"");
			 //hash.put("�Ƿ�ֻ��",rmd.isReadOnly(i)+"");
			 //hash.put("�Ƿ�ɲ�ѯ",rmd.isSearchable(i)+"");
			 hash.put("�Ƿ�����",rmd.isSigned(i)+"");
			 //hash.put("�Ƿ��д",rmd.isWritable(i)+"");
			 vect.add(hash);
			 }
			 */

			DatabaseMetaData dmd = conn.getMetaData();
			rs = dmd.getColumns(null, ParentBean.DBName.toUpperCase(),
								table.toUpperCase(), null);
			rs = dmd.getColumns(null, "HG", "TEST", null);
			ResultSetMetaData rmd = rs.getMetaData();
			int cols = rmd.getColumnCount();
			System.out.println(cols + "gggHHH");
			System.out.println("resultSET" + getResultSetData(rs));
			while(rs.next())
			{
				System.out.println("TTTTT");
				Hashtable hash = new Hashtable();
				hash.put("�ж����С", rs.getString("CHAR_OCTET_LENGTH") + "");
				String f = rs.getString("COLUMN_NAME");
				ResultSet r = QuerySQL("select " + f + " from " + table);
				ResultSetMetaData rm = r.getMetaData();

				hash.put("�ֶ���", f + "");
				hash.put("�����ͱ��", rm.getColumnType(1) + "");
				hash.put("�б�׼������", rm.getColumnTypeName(1) + "");

				hash.put("�Ƿ��Ϊ��", rm.isNullable(1) + "");
				hash.put("�Ƿ�����", rm.isSigned(1) + "");
				hash.put("�ж����С", rm.getColumnDisplaySize(1) + "");
				hash.put("�о�ȷ��", rs.getString("NUM_PREC_RADIX") + "");

				r.close();
				Statement stst = r.getStatement();
				if(stst != null)
				{
					stst.close();
				}
				vect.add(hash);
			}
			rs.close();
			Statement stmt = rs.getStatement();
			if(stmt != null)
			{
				stmt.close();
			}
			System.out.println("____" + vect);
		}
		catch(SQLException sqle)
		{
			System.out.println("����DataBase.getTableStruct()��������:\r\n" + sqle);
		}
		catch(AbstractMethodError e)
		{
			System.out.println("����DataBase.getTableStruct()��������:\r\n" + e);
		}
		return vect;
	}

	/**ȡ�����ݼ�����*/
	public Vector getResultSetData(ResultSet rs)
	{
		Vector vect = new Vector();
		try
		{
			//ȡ������������
			ResultSetMetaData rsmd = rs.getMetaData();
			int cols = rsmd.getColumnCount();
			while(rs.next())
			{
				Hashtable hash = new Hashtable();
				for(int i = 1; i <= cols; i++)
				{
					DealString ds = new DealString();
					String field = ds.toString(rsmd.getColumnName(i));
					String value = ds.toString(rs.getString(i));
					hash.put(field, value);
				}
				vect.add(hash);
			}
		}
		catch(SQLException sqle)
		{
			System.out.println("����DataBase.getResultSetData()��������:\r\n" + sqle);
		}
		return vect;
	}

	/**������������*/
	public void prepareStatement(String sql)
	{
		try
		{
			pstm = conn.prepareStatement(sql);
		}
		catch(SQLException sqle)
		{
			System.out.println("����DataBase.prepareStatement()��������:\r\n" + sqle);
		}
	}

	/**ִ�в�ѯ*/
	public void executeQuery()
	{
		try
		{
			rs = pstm.executeQuery();
		}
		catch(SQLException sqle)
		{
			System.out.println("����DataBase.executeQuery()��������:\r\n" + sqle);
		}
	}

	/**ת����һ��*/
	public boolean next()
	{
		try
		{
			return rs.next();
		}
		catch(SQLException sqle)
		{
			System.out.println("����DataBase.next()��������:\r\n" + sqle);
		}
		return true;
	}

	/**ȡ�����ݲ�������������ת��Ϊ�ַ���*/
	public String getObject(String field, String sqlType)
	{
		try
		{
			if(rs == null)
			{
				return "";
			}
			if(sqlType.equals("BINARY") || sqlType.equals("VARBINARY"))
			{ //�ֽ���
				byte b[] = rs.getBytes(field);
				return new String(b);
			}
			else if(sqlType.equals("LONGVARBINARY") || sqlType.equals("BLOB"))
			{ //δ������ֽ���
				InputStream is = rs.getBinaryStream(field);
				return(new DealFile()).readCHStr(is);
			}
			else if(sqlType.equals("LONGVARCHAR") || sqlType.equals("CLOB"))
			{ //������ֽ���
				InputStream is = rs.getAsciiStream(field);
				return(new DealFile()).readCHStr(is);
			}
			else
			{ //�ַ�����
				return rs.getString(field);
			}
		}
		catch(Exception sqle)
		{
			System.out.println("����DataBase.getObject()��������:\r\n" + sqle);
		}
		return "";
	}

	/**�����������ͱ��浽���ݿ�*/
	public void setObject(int index, String value, String sqlType)
	{
		try
		{
			if(sqlType.equals("ARRAY"))
			{ //������?????
			}
			else if(sqlType.equals("BIGINT"))
			{ //64λ���з�������
				Long l = new Long(value);
				pstm.setObject(index, l);
			}
			else if(sqlType.equals("BINARY") || sqlType.equals("VARBINARY"))
			{ //�ֽ���
				byte b[] = value.getBytes();
				pstm.setObject(index, b);
			}
			else if(sqlType.equals("BIT"))
			{ //������
				Boolean b = new Boolean("true");
				if(value.equals("0"))
				{
					b = new Boolean("false");
				}
				pstm.setObject(index, b);
			}
			else if(sqlType.equals("BLOB") || sqlType.equals("LONGVARBINARY"))
			{ //δ����������
				FileInputStream fis = (new DealFile()).toInputStream(value);
				pstm.setBinaryStream(index, fis,
									 (int)(new File("tmp.txt")).length());
			}
			else if(sqlType.equals("CLOB") || sqlType.equals("LONGVARCHAR"))
			{ //����������
				FileInputStream fis = (new DealFile()).toInputStream(value);
				pstm.setAsciiStream(index, fis,
									(int)(new File("tmp.txt")).length());
			}
			else if(sqlType.equals("BOOLEAN"))
			{ //????
			}
			else if(sqlType.equals("CHAR") || sqlType.equals("VARCHAR"))
			{ //�ַ�����
				pstm.setObject(index, value);
			}
			else if(sqlType.equals("DATALINK"))
			{ //????
			}
			else if(sqlType.equals("DATE"))
			{ //������
				int year = Integer.parseInt(value.substring(0, 4));
				int month = Integer.parseInt(value.substring(5, 7));
				int day = Integer.parseInt(value.substring(8, 10));
				java.sql.Date d = new java.sql.Date(year, month, day);
				pstm.setObject(index, d);
			}
			else if(sqlType.equals("DECIMAL") || sqlType.equals("NUMERIC"))
			{ //�̶�����ʮ������
				java.math.BigDecimal b = new java.math.BigDecimal(value);
				pstm.setObject(index, b);
			}
			else if(sqlType.equals("DISTINCT"))
			{ //????
			}
			else if(sqlType.equals("DOUBLE") || sqlType.equals("FLOAT"))
			{ //˫���ȸ�����
				Double d = new Double(value);
				pstm.setObject(index, d);
			}
			else if(sqlType.equals("INTEGER"))
			{ //32λ���з�������
				Integer i = new Integer(value);
				pstm.setObject(index, i);
			}
			else if(sqlType.equals("JAVA_OBJECT"))
			{ //????
			}
			else if(sqlType.equals("NULL"))
			{ //????
			}
			else if(sqlType.equals("OTHER"))
			{ //????
			}
			else if(sqlType.equals("REAL"))
			{ //�����ȸ�����
				Float f = new Float(value);
				pstm.setObject(index, f);
			}
			else if(sqlType.equals("REF"))
			{ //????
			}
			else if(sqlType.equals("SMALLINT"))
			{ //16λ���з�������
				Short s = new Short(value);
				pstm.setObject(index, s);
			}
			else if(sqlType.equals("STRUCT"))
			{ //????
			}
			else if(sqlType.equals("TIME"))
			{ //ʱ����
				int hour = Integer.parseInt(value.substring(0, 2));
				int minute = Integer.parseInt(value.substring(3, 5));
				int second = Integer.parseInt(value.substring(6, 8));
				java.sql.Time t = new java.sql.Time(hour, minute, second);
				pstm.setObject(index, t);
			}
			else if(sqlType.equals("TIMESTAMP"))
			{ //����ʱ����
				int year = Integer.parseInt(value.substring(0, 4));
				int month = Integer.parseInt(value.substring(5, 7));
				int date = Integer.parseInt(value.substring(8, 10));
				int hour = Integer.parseInt(value.substring(11, 13));
				int minute = Integer.parseInt(value.substring(14, 16));
				int second = Integer.parseInt(value.substring(17, 19));
				int nano = Integer.parseInt(value.substring(20, 21));
				java.sql.Timestamp t = new java.sql.Timestamp(year, month, date,
					hour,
					minute, second, nano);
				pstm.setObject(index, t);
			}
			else if(sqlType.equals("TINYINT"))
			{ //8λ�޷�������
				Byte b = new Byte(value);
				pstm.setObject(index, b);
			}
		}
		catch(Exception sqle)
		{
			System.out.println("����DataBase.setObject()��������:\r\n" + sqle);
		}
	}

	/**ִ�и���*/
	public void executeUpdate()
	{
		try
		{
			pstm.executeUpdate();
		}
		catch(SQLException sqle)
		{
			System.out.println("����DataBase.executeUpdate()��������:\r\n" + sqle);
		}
	}

	/**�ر���������*/
	public void closePstm()
	{
		try
		{
			pstm.close();
		}
		catch(SQLException sqle)
		{
			System.out.println("����DataBase.closePstm()��������:\r\n" + sqle);
		}
	}

	/**�ر��α�*/
	public void closeRs()
	{
		try
		{
			rs.close();
		}
		catch(SQLException sqle)
		{
			System.out.println("����DataBase.closeRs()��������:\r\n" + sqle);
		}
	}

	public void test() throws Exception
	{
		PreparedStatement pstm = conn.prepareStatement(
			"update test set name=?,time=?,d=?,bb=?");
		java.sql.Time d = new java.sql.Time(20031011);
		pstm.setObject(1, "fdafafdsa");
		Boolean b = new Boolean(true);
		pstm.setObject(2, b);
		pstm.setObject(3, d);
		pstm.setObject(4, (new String("testlzb")).getBytes());
		pstm.executeUpdate();
		pstm.close();

		ResultSet rs = QuerySQL("select * from test");
		while(rs.next())
		{
			//System.out.println("1=="+new String(rs.getObject("bb")));
			//System.out.println("2=="+rs.getObject("bb").toString());
			System.out.println((String)rs.getObject("name"));
			Object o = rs.getObject("bb");
			String t = new String((byte[])o);
			byte bv[] = t.getBytes();
			System.out.println("____" + bv[0]);
			String s = new String(bv);
			System.out.println("t" + t);
			System.out.println("s" + s);
		}
		rs.close();
		Statement stst = rs.getStatement();
		if(stst != null)
		{
			stst.close();
//���:getString()���֣��ַ���������,getBytes(),getBinaryStream()BLOB,getAsciiStream()CLOB
//��ת��Ϊ�ַ�����������������������
//�Զ����ƣ�����ľ�Ϊת������ַ�����������ʱ��ת��Ϊ����������ֻҪ�����ʱ��������������ʱ�Ķ�������һ������
//����Binary������֤�Ƿ���ȷ
//����:setObject();������Ӧ�Ķ��󣪣�����������������

//���������������ȷ��ʹ���Ǹ�����(ParentBean)
			//��ת������ת��Ϊ�ַ���(DealFile)
//���룺�������������ַ�������ɲ�ͬ����������(ParentBean)
			//��ת�����ַ���ת��Ϊ��(DealFile)

		}

	}

	public void setBinaryStream(int index, InputStream is, int t) throws
		Exception
	{
		pstm.setBinaryStream(index, is, t);
	}

	public void setAsciiStream(int index, InputStream is, int t) throws
		Exception
	{
		pstm.setBinaryStream(index, is, t);
	}

	public String ttest() throws Exception
	{
		PreparedStatement pstm = conn.prepareStatement("select * from test1");
		ResultSet rs = pstm.executeQuery();
		rs.next();
		InputStream is = rs.getBinaryStream("ziduan");
		rs.close();
		Statement stmt = rs.getStatement();
		if(stmt != null)
		{
			stmt.close();
		}
		return(new DealFile()).readCHStr(is);
	}

	public static void main(String args[]) throws Exception
	{
		/*����ִ�еĴ���
		 *stm = rs.getStatement();
		 *rs.close();
		 *stm.close();
		 */
		System.out.println("begin\r\n\r\n");

		DataBase db = new DataBase();
		//db.createConn("oracle.jdbc.driver.OracleDriver","jdbc:oracle:thin:@10.0.0.94:1521:hg","oaadminuser","lancom4454");
		//db.createConn("sun.jdbc.odbc.JdbcOdbcDriver","jdbc:odbc:test");
		//db.createConn("com.microsoft.jdbc.sqlserver.SQLServerDriver","jdbc:microsoft:sqlserver://localhost:1433;DatabaseName=test","test","test");
		//ParentBean.DBName = "hg";

		//System.out.println(db.UpdateCLOB("test","","content","ttew����tewagdsagsad"));
		//System.out.println(db.QueryCLOB("test","","content"));

		//System.out.println(db.getDataBaseInfo());
		//System.out.println(db.getTableList());
		//System.out.println(db.getTableStruct("test"));
		//System.out.println(db.getResultSetData(db.QuerySQL("select * from test")));
		//System.out.println(db.getResultSetData(db.getTypeInfo()));

		/**?????
		 1.Access����ȡ�ñ��б�
		 2.Oracle����ȡ�ñ�ṹ:CLOB,BLOB
		 3.����ȡ����ͼ�ṹ
		 4.clob,blob,time���Ͷ�����д��ʱû�����֣��������ַ���������
		 5.�ֶξ�ȷ�����⣿������ֺ�ʹ�ã�
		 6.���岻ͬ���ݿ������
		 7.���ӻ��Ľ���
		 8.�ֶ�ӳ���б���θ�����
		 �����ֶ����ͣ�������ʱ�ֶ�����д��(���ȣ���ȷ�ȣ�)��д������ʱ���õĺ���setObject
		 ����clob,blob,�ַ�����Ascii,unicode�������֣�����������������ʱ�䣬�����ͣ��ֽ����ͣ�����
		 �������ȡ��access���б�
		 �������ݿ�����,��ͬ���ݿ⣬�ֶ�ӳ���������
		 �����Բ�ͬ������ʵ�ֲ�ͬ������ѡ�����SQLServer
		 */
		/*
		  //����addBatch(),clearBatch()
		  //str����<=file����-2
		  //��ӷ�ʽ----------------(1)
		  db.prepareStatement("update test set dlxc=?");
		  db.setObject(1,"�����Ͳ���","CLOB");
		  db.executeUpdate();
		  db.closePstm();
		  //----------------------
		  db.prepareStatement("select * from test");
		  db.executeQuery();
		  db.next();
		  System.out.println(db.getObject("dlxc","CLOB"));
		  db.closeRs();
		  db.closePstm();
		  //ֱ�ӷ�ʽ---------------------(2)
		  db.prepareStatement("update test set dlxc = ? ");
		  FileInputStream fis = new FileInputStream("out.txt");
		  db.setAsciiStream(1,fis,100);
		  db.executeUpdate();
		  //----------------------
		  System.out.println(db.QueryCLOB("test","","dlxc"));
		 */
//db.ExecuteSQL("create table test1 (bh integer,ziduan blob)");
//db.ExecuteSQL("insert into test1(bh) values(1)");
		/*
		 db.prepareStatement("update test1 set ziduan=?");
		 db.setObject(1,"�����Ͳ���","BLOB");
		 db.executeUpdate();
		 db.closePstm();
		 db.prepareStatement("select * from test1");
		 db.executeQuery();
		 db.next();
		 System.out.println(db.getObject("ziduan","BLOB"));
		 db.closeRs();
		 db.closePstm();
		 */
//System.out.println(db.ttest());

//db.prepareStatement("update article set content=?");
//db.setObject(1,"�����Ͳ���eee","BLOB");
//db.executeUpdate();
//db.closePstm();

//db.UpdateBLOB_JNDI("test1","bh=1","ziduan","�����Ͳ���333");
//System.out.println(db.QueryBLOB_JNDI("article","id=60","content"));
//System.out.println(db.makeID_Add1("Article","ID","","",true));
		//db.releaseConn();

		//mysql����
        db.createConn("org.gjt.mm.mysql.Driver","jdbc:mysql://localhost:3306/book","root","admin");
        System.out.println(db.getOnePage("select timestamp from pg where ipaddress='192.168.0.6' order by timestamp desc",1,100));
		//db.releaseMyConnPool();
		System.out.println("\r\n\r\nend");
	}
};