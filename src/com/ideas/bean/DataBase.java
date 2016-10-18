/**********************************************************
 Copyright (C),2003-10-12, Beijing  USTB.
 All rights reserved.
 Filename: Datebase.java
 Author: 刘中兵
 Version 1.0
 Date:2003-10-12
 Description:此文件为数据库的基本操作处理类
 Other:
 Variable List:
 1.Connection conn = null;//数据库连接对象
 Function List:
 //基本操作
 1.public DataBase()//构造函数
 2.public void getConnPool()//取得连接池
 3.public void createConn(String url,String usr,String pwd)//生成Oracle SQLServer等的连接
 4.public void createConn(String url)//生成Access连接
 5.public void releaseConn()//释放数据库连接
 6.public ResultSet QuerySQL(String sql)//查询记录
 7.public int ExecuteSQL(String sql)//执行增删改的语句
 //辅助使用函数
 8.public int makeID(String table,String field1,String field2,String value1,boolean type1)//产生唯一编号,type1为false时表示条件字段为字符串类型
  public int makeID(String table,String field1,String field2,String field3,String value1,String value2,boolean type1,boolean type2)
  public int makeID_Add1(String table,String field1,String field2,String value1,boolean type1)//产生唯一编号,type1为false时表示条件字段为字符串类型
  public int makeID_Add1(String table,String field1,String field2,String field3,String value1,String value2,boolean type1,boolean type2)
 9.public int toID(String table,String field1,String field2,String value1)//将名称转换为编号
 10.public String toName(String table,String field1,String field2,String value)//将编号转换为名称
 11.public Vector (String sql,int page,int records)//分页时取得一页的数据量
 12.public int setSort(String table,String field1,String field2,String wherestr,String orderstr,boolean b)//为某一个字段进行重新排序
 //查询与更新LOB类型
 13.public String QueryCLOB(String table,String wherestr,String clobfield)//查询CLOB类型值
 14.public int UpdateCLOB(String table,String wherestr,String blobfield,String blobvalue)//修改CLOB类型值
 15.public String QueryBLOB(String table,String wherestr,String blobfield)//查询BLOB类型值
 16.public int UpdateBLOB(String table,String wherestr,String blobfield,String blobvalue)//修改BLOB类型值
 17.public String QueryBLOB_JNDI(String table,String wherestr,String blobfield)//查询BLOB类型值
 178.public int UpdateBLOB_JNDI(String table,String wherestr,String blobfield,String blobvalue)//修改BLOB类型值
 //LOB类型基本操作（到文件）
 19.public int clobInsert(String sql,String table,String wherestr,String clobfield,String infile)//往数据库中插入一个新的CLOB对象
 20.public int clobModify(String table,String wherestr,String clobfield,String infile)//修改CLOB对象（是在原CLOB对象基础上进行覆盖式的修改）
 21.public int clobReplace(String table,String wherestr,String clobfield,String infile)//替换CLOB对象（将原CLOB对象清除，换成一个全新的CLOB对象）
 22.public int blobInsert(String sql,String table,String wherestr,String blobfield,String infile)//往数据库中插入一个新的BLOB对象
 23.public int blobModify(String table,String wherestr,String blobfield,String infile)//修改BLOB对象（是在原BLOB对象基础上进行覆盖式的修改）
 24.public int blobReplace(String table,String wherestr,String blobfield,String infile)//替换BLOB对象（将原BLOB对象清除，换成一个全新的BLOB对象）
 //数据库结构信息
 25.public Hashtable getDataBaseInfo()//数据库信息
 26;public Vector getTableList()//数据表列表
 27.public Vector getTableStruct(String table)//数据表的结构
 28.public Vector getResultSetData(ResultSet rs)//取得数据集内容
 //对sql语句的分布处理
 29.public void prepareStatement(String sql)//创建申明对象
 30.public void executeQuery()//执行查询
 31.public boolean next()//转向下一条
 32.public String getObject(String field,String sqlType)//取得数据并根据数据类型转化为字符串
 33.public void setObject(int index,String value,String sqlType)//根据数据类型保存到数据库
 34.public void executeUpdate()//执行更新
 35.public void closePstm()//关闭申明对象
 36.public void closeRs()//关闭游标
 History:
 date:2003-11-21
 name:liumei
 action:修改getOnePage(String sql,int page,int records)//分页时取得一页的数据量
 每页的记录显示条数从字典中取,即去掉records的作用
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
 * 此文件为数据库的基本操作处理类
 * @author 刘中兵
 * @version 1.0-hg
 */
public class DataBase extends Object
{
	Connection conn = null;
	PreparedStatement pstm = null;
	ResultSet rs = null;


	/**构造函数*/
	public DataBase()
	{
	}

	/**取得连接池*/
	public void getConnPool()
	{
		try
		{
			Context initCtx = new InitialContext();
			Context ctx = (Context)initCtx.lookup("java:comp/env");

			/**获取连接池对象*/
			Object obj = (Object)ctx.lookup("jdbc/OracleDB");

			/**类型转换*/
			javax.sql.DataSource ds = (javax.sql.DataSource)obj;
			conn = ds.getConnection();

			/**测试连接计数*/
			//	ParentBean.curnum++;
			//	ParentBean.sumnum++;
			ParentBean.showConnNUM(true);
		}
		catch(NamingException e)
		{
			System.out.println("从数据池取得数据库连接时出错;\r\n错误为:" + e);
		}
		catch(SQLException e)
		{
			System.out.println("从数据池取得数据库连接时出错;\r\n错误为:" + e);
		}
	}

	/**创建我的连接池*/
	public boolean getMyConnPool()
	{
		return true;
	}

	/**释放我的连接池*/
	public boolean releaseMyConnPool()
	{
		
		return true;
	}



	 /**生成Oracle SQLServer等的连接*/
	 public void createConn(String drv, String url, String usr, String pwd)
	 {
		 try
		 {
			 Class.forName(drv).newInstance();

			 conn = DriverManager.getConnection(url, usr, pwd);


			 /**测试连接计数*/
			 ParentBean.showConnNUM(true);
		 }
		 catch(ClassNotFoundException ec)
		 {
			 System.out.println("从自身建立数据库连接时出错;\r\n错误为:" + ec);
		 }
		 catch(SQLException e)
		 {
			 System.out.println("从自身建立数据库连接时出错;\r\n错误为:" + e);
		 }
		 catch(Exception et)
		 {
			 System.out.println("从自身建立数据库连接时出错;\r\n错误为:" + et);
		 }
	 }

	/**生成Access连接*/
	public void createConn(String drv, String url)
	{
		try
		{
			Class.forName(drv).newInstance();
			conn = DriverManager.getConnection(url);

			/**测试连接计数*/
			ParentBean.showConnNUM(true);
		}
		catch(ClassNotFoundException ec)
		{
			System.out.println("从自身建立数据库连接时出错;\r\n错误为:" + ec);
		}
		catch(SQLException e)
		{
			System.out.println("从自身建立数据库连接时出错;\r\n错误为:" + e);
		}
		catch(Exception et)
		{
			System.out.println("从自身建立数据库连接时出错;\r\n错误为:" + et);
		}
	}

	/**释放数据库连接*/
	public void releaseConn()
	{
		try
		{
			if(conn != null)
			{
				conn.close();

				/**测试连接计数*/
				ParentBean.showConnNUM(false);
			}
		}
		catch(SQLException e)
		{
			System.out.println("关闭数据库连接时出错;\r\n错误为:" + e);
		}
	}

	/**查询记录*/
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
			System.out.println("调用SQL语句 " + sql + " 时出错;\r\n错误为:" + sqle);
		}
		return rs;
	}

	/**执行增删改的语句*/
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
			System.out.println("调用SQL语句 " + sql + " 时出错;\r\n错误为:" + sqle);
			return sqle.getErrorCode();
		}
		return 0;
	}

	/**产生唯一编号*/
	public int makeID(String table, String field1, String field2, String value1,
					  boolean type1)
	{
		int out = -1;
		String sql = "";
		try
		{
			//只有唯一主键field1
			sql = "select " + field1 + " as ID from " + table + " order by " +
				field1;
			//有两个主键field1、field2
			if(!value1.equals(""))
			{ //当第一个字段不空时，作为条件查询第二个字段
				sql = "select " + field2 + " as ID from " + table + " where " +
					field1 +
					"=" + value1 + " order by " + field2;
				if(!type1)
				{ //是字符串时 将type1设为false
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
			{ //有记录
				t1 = rs.getInt("ID");
				out = t1;
				boolean bool = false;
				while(rs.next())
				{ //不止一条纪录
					bool = true;
					t2 = rs.getInt("ID");
					if((t2 - t1) > 1)
					{
						break; //如果t2与t1相差大于1,则跳出去,新编号为t1++（见后面**）
					}
					t1 = t2; //否则将t2赋给t1
				}
				if(!bool)
				{ //如果只有一条纪录
					if(t1 > 1)
					{
						t1 = 1; //如果已有纪录的ID号大于1，则新编号设为1
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
			System.out.println("调用SQL语句 " + sql + " 时出错;\r\n错误为:" + sqle);
		}
		return out;
	}

	/**产生唯一编号*/
	public int makeID_Add1(String table, String field1, String field2,
						   String value1, boolean type1)
	{
		int out = -1;
		String sql = "";
		try
		{
			//只有唯一主键field1
			sql = "select max(" + field1 + ")+1 as ID from " + table +
				" order by " +
				field1;
			//有两个主键field1、field2
			if(!value1.equals(""))
			{ //当第一个字段不空时，作为条件查询第二个字段
				sql = "select (" + field2 + ")+1 as ID from " + table +
					" where " +
					field1 + "=" + value1 + " order by " + field2;
				if(!type1)
				{ //是字符串时 将type1设为false
					sql = "select (" + field2 + ")+1 as ID from " + table +
						" where " +
						field1 + "='" + value1 + "' order by " + field2;
				}
			}
			pstm = conn.prepareStatement(sql);
			rs = pstm.executeQuery();
			if(rs.next())
			{ //有记录
				out = rs.getInt(1);
			}

			rs.close();
			pstm.close();
		}
		catch(SQLException sqle)
		{
			System.out.println("调用SQL语句 " + sql + " 时出错;\r\n错误为:" + sqle);
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
			//只有唯一主键field1
			sql = "select " + field1 + " as ID from " + table + " order by " +
				field1;
			//有两个主键field1、field2
			if(!value1.equals(""))
			{ //当第一个字段不空时，作为条件查询第二个字段
				sql = "select " + field2 + " as ID from " + table + " where " +
					field1 +
					"=" + value1 + " order by " + field2;
				if(!type1)
				{ //是字符串时 将type1设为false
					sql = "select " + field2 + " as ID from " + table +
						" where " +
						field1 + "='" + value1 + "' order by " + field2;
				}
			}
			if(!value2.equals(""))
			{ //当第二个字段不空时，作为条件查询第三个字段
				sql = "select " + field3 + " as ID from " + table + " where " +
					field1 +
					"=" + value1 + " and " + field2 + "=" + value2 +
					" order by " +
					field3;
				if(!type2)
				{ //是字符串时 将type1设为false
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
			{ //有记录
				t1 = rs.getInt("ID");
				out = t1;
				boolean bool = false;
				while(rs.next())
				{ //不止一条纪录
					bool = true;
					t2 = rs.getInt("ID");
					if((t2 - t1) > 1)
					{
						break; //如果t2与t1相差大于1,则跳出去,新编号为t1++（见后面**）
					}
					t1 = t2; //否则将t2赋给t1
				}
				if(!bool)
				{ //如果只有一条纪录
					if(t1 > 1)
					{
						t1 = 1; //如果已有纪录的ID号大于1，则新编号设为1
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
			System.out.println("调用SQL语句 " + sql + " 时出错;\r\n错误为:" + sqle);
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
			//只有唯一主键field1
			sql = "select max(" + field1 + ") as ID from " + table +
				" order by " +
				field1;
			//有两个主键field1、field2
			if(!value1.equals(""))
			{ //当第一个字段不空时，作为条件查询第二个字段
				sql = "select max(" + field2 + ") as ID from " + table +
					" where " +
					field1 + "=" + value1 + " order by " + field2;
				if(!type1)
				{ //是字符串时 将type1设为false
					sql = "select max(" + field2 + ") as ID from " + table +
						" where " +
						field1 + "='" + value1 + "' order by " + field2;
				}
			}
			if(!value2.equals(""))
			{ //当第二个字段不空时，作为条件查询第三个字段
				sql = "select max(" + field3 + ") as ID from " + table +
					" where " +
					field1 + "=" + value1 + " and " + field2 + "=" + value2 +
					" order by " + field3;
				if(!type2)
				{ //是字符串时 将type1设为false
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
			{ //有记录
				out = rs.getInt("ID");
			}

			rs.close();
			pstm.close();
		}
		catch(SQLException sqle)
		{
			System.out.println("调用SQL语句 " + sql + " 时出错;\r\n错误为:" + sqle);
		}
		return out;
	}

	/**将名称转换为编号*/
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
			System.out.println("调用SQL语句 " + sql + " 时出错;\r\n错误为:" + sqle);
		}
		return out;
	}

	/**将编号转换为名称*/
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
			System.out.println("调用SQL语句 " + sql + " 时出错;\r\n错误为:" + sqle);
		}
		return out;
	}

	/**分页时取得一页的数据量*/
	public Vector getOnePage(String sql, int page, int records)
	{
		//第一个为总页数*/
	   //第二...个为Hashtable*/
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

			//移到当前行
			pstm.close();
			rs.close();

			pstm = conn.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE,
										 ResultSet.CONCUR_UPDATABLE);
			rs = pstm.executeQuery();
			rows = (page - 1) * records;
			rs.absolute(rows+1);

			DealString ds = new DealString();

			//查询当前页
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
			System.out.println("执行SQL语句 " + sql + " 分页至第 " + page +
							   " 页时出错;错误为:" +
							   sqle);
		}
		return vect;
	}

	/**为某一个字段进行重新排序*/
	public int setSort(String table, String field1, String field2,
					   String wherestr, String orderstr, boolean b)
	{
		//写入序列号,field2为唯一字段*/
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
			   { //为field2整型
				   sql = "update " + table + " set " + field1 + "=" + i +
					   " where " +
					   field2 + "=" + rs.getString(1);
			   }
			   else
			   { //为field2字符串
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
		   System.out.println("调用MyDataBase.setSort()函数错误:\r\n" + sqle);
	   }
		return 0;
	}

	/**查询CLOB类型值*/
	public String QueryCLOB(String table, String wherestr, String clobfield)
	{
		String out = "";
		
		return out;
	}

	/**修改CLOB类型值*/
	public int UpdateCLOB(String table, String wherestr, String clobfield,
						  String clobvalue)
	{
		
		return 0;
	}

	/**查询BLOB类型值*/
	public String QueryBLOB(String table, String wherestr, String blobfield)
	{
		String out = "";
		
		return out;
	}

	/**修改BLOB类型值*/
	public int UpdateBLOB(String table, String wherestr, String blobfield,
						  String blobvalue)
	{
		
		return 0;
	}

	/**查询BLOB类型值*/
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

	/**修改BLOB类型值*/
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

	/**往数据库中插入一个新的CLOB对象*/
	public int clobInsert(String sql, String table, String wherestr,
						  String clobfield, String infile)
	{
		int out = ExecuteSQL(sql); //Insert语句
		out = clobModify(table, wherestr, clobfield, infile);
		return out;
	}

	/**修改CLOB对象（是在原CLOB对象基础上进行覆盖式的修改）*/
	public int clobModify(String table, String wherestr, String clobfield,
						  String infile)
	{
		
		return 0;
	}

	/**替换CLOB对象（将原CLOB对象清除，换成一个全新的CLOB对象）*/
	public int clobReplace(String table, String wherestr, String clobfield,
						   String infile)
	{
		int out = 0;
		try
		{
			/* 设定不自动提交 */
			boolean defaultCommit = conn.getAutoCommit();
			conn.setAutoCommit(false);

			/* 清空原CLOB对象 */
			String sqlCommand = "update " + table + " set " + clobfield +
				"=EMPTY_CLOB()";
			if(!sqlCommand.equals(""))
			{
				sqlCommand += " where " + wherestr;
			}
			pstm = conn.prepareStatement(sqlCommand);
			pstm.executeUpdate();
			pstm.close();

			/* 正式提交 */
			conn.commit();

			/* 恢复原提交状态 */
			conn.setAutoCommit(defaultCommit);

			out = clobModify(table, wherestr, clobfield, infile);
		}
		catch(SQLException sqle)
		{
			System.out.println("调用DataBase.clobReplace()函数错误:\r\n" + sqle);
			return sqle.getErrorCode();
		}
		return out;
	}

	/**向数据库中插入一个新的BLOB对象*/
	public int blobInsert(String sql, String table, String wherestr,
						  String blobfield, String infile)
	{
		int out = ExecuteSQL(sql); //Insert语句
		out = clobModify(table, wherestr, blobfield, infile);
		return out;
	}

	/**修改BLOB对象（是在原BLOB对象基础上进行覆盖式的修改）*/
	public int blobModify(String table, String wherestr, String blobfield,
						  String infile)
	{
		
		return 0;
	}

	/**替换BLOB对象（将原BLOB对象清除，换成一个全新的CLOB对象）*/
	public int blobReplace(String table, String wherestr, String blobfield,
						   String infile)
	{
		int out = 0;
		try
		{
			/* 设定不自动提交 */
			boolean defaultCommit = conn.getAutoCommit();
			conn.setAutoCommit(false);

			/* 清空原CLOB对象 */
			String sqlCommand = "update " + table + " set " + blobfield +
				"=EMPTY_BLOB()";
			if(!sqlCommand.equals(""))
			{
				sqlCommand += " where " + wherestr;
			}
			pstm = conn.prepareStatement(sqlCommand);
			pstm.executeUpdate();
			pstm.close();

			/* 正式提交 */
			conn.commit();

			/* 恢复原提交状态 */
			conn.setAutoCommit(defaultCommit);

			out = blobModify(table, wherestr, blobfield, infile);
		}
		catch(SQLException sqle)
		{
			System.out.println("调用DataBase.blobReplace()函数错误:\r\n" + sqle);
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
		}catch(SQLException sqle){System.out.println("执行DataBase.getData(String)执行SQL语句 "+sql+" 时出错;错误为:"+sqle);}
		finally
		{
			closeRs();
			closePstm();
		}
		return vect;
	}

	/**数据库信息*/
	public Hashtable getDataBaseInfo()
	{
		Hashtable hash = new Hashtable();
		try
		{
			DatabaseMetaData dmd = conn.getMetaData();
			hash.put("1", dmd.getCatalogSeparator());
			hash.put("2", dmd.getCatalogTerm());
			hash.put("数据库类型名称", dmd.getDatabaseProductName());
			hash.put("数据库版本", dmd.getDatabaseProductVersion());
			hash.put("5", dmd.getDefaultTransactionIsolation() + "");
			hash.put("驱动版本(最大)", dmd.getDriverMajorVersion() + "");
			hash.put("驱动版本(最小)", dmd.getDriverMinorVersion() + "");
			hash.put("驱动名", dmd.getDriverName());
			hash.put("驱动版本", dmd.getDriverVersion());
			hash.put("10", dmd.getExtraNameCharacters());
			hash.put("11", dmd.getIdentifierQuoteString());
			hash.put("12", dmd.getMaxBinaryLiteralLength() + "");
			hash.put("最大行限定", dmd.getMaxRowSize() + "");
			hash.put("方案", dmd.getSchemaTerm());
			hash.put("日期函数", dmd.getTimeDateFunctions());
			hash.put("连接地址", dmd.getURL());
			hash.put("用户名", dmd.getUserName());
		}
		catch(SQLException sqle)
		{
			System.out.println("调用DataBase.getDataBaseInfo()函数错误:\r\n" + sqle);
		}
		catch(AbstractMethodError e)
		{
			System.out.println("调用DataBase.getDataBaseInfo()函数错误:\r\n" + e);
		}
		return hash;
	}

	/**数据表列表*/
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
			System.out.println("调用DataBase.getTableList()函数错误:\r\n" + sqle +
							   sqle.getErrorCode());
		}
		catch(AbstractMethodError e)
		{
			System.out.println("调用DataBase.getTableList()函数错误:\r\n" + e);
		}
		return vect;
	}

	/**数据表的结构*/
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
			 //hash.put("目录名",rmd.getCatalogName(i));
			 //hash.put("列返回值类型名",rmd.getColumnClassName(i));
			 hash.put("列定义大小",rmd.getColumnDisplaySize(i)+"");
			 //hash.put("列标签",rmd.getColumnLabel(i));
			 hash.put("字段名",rmd.getColumnName(i));
			 hash.put("列类型编号",rmd.getColumnType(i)+"");
			 hash.put("列标准类型名",rmd.getColumnTypeName(i));
			 hash.put("列精确度",rmd.getPrecision(i)+"");
			 //hash.put("10",rmd.getScale(i)+"");
			 //hash.put("11",rmd.getSchemaName(i));
			 //hash.put("表名",rmd.getTableName(i));
			 //hash.put("13",rmd.isAutoIncrement(i)+"");
			 //hash.put("大小写敏感",rmd.isCaseSensitive(i)+"");
			 //hash.put("是否为金额",rmd.isCurrency(i)+"");
			 //hash.put("是否可写",rmd.isDefinitelyWritable(i)+"");
			 hash.put("是否可为空",rmd.isNullable(i)+"");
			 //hash.put("是否只读",rmd.isReadOnly(i)+"");
			 //hash.put("是否可查询",rmd.isSearchable(i)+"");
			 hash.put("是否数字",rmd.isSigned(i)+"");
			 //hash.put("是否可写",rmd.isWritable(i)+"");
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
				hash.put("列定义大小", rs.getString("CHAR_OCTET_LENGTH") + "");
				String f = rs.getString("COLUMN_NAME");
				ResultSet r = QuerySQL("select " + f + " from " + table);
				ResultSetMetaData rm = r.getMetaData();

				hash.put("字段名", f + "");
				hash.put("列类型编号", rm.getColumnType(1) + "");
				hash.put("列标准类型名", rm.getColumnTypeName(1) + "");

				hash.put("是否可为空", rm.isNullable(1) + "");
				hash.put("是否数字", rm.isSigned(1) + "");
				hash.put("列定义大小", rm.getColumnDisplaySize(1) + "");
				hash.put("列精确度", rs.getString("NUM_PREC_RADIX") + "");

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
			System.out.println("调用DataBase.getTableStruct()函数错误:\r\n" + sqle);
		}
		catch(AbstractMethodError e)
		{
			System.out.println("调用DataBase.getTableStruct()函数错误:\r\n" + e);
		}
		return vect;
	}

	/**取得数据集内容*/
	public Vector getResultSetData(ResultSet rs)
	{
		Vector vect = new Vector();
		try
		{
			//取得列数和列名
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
			System.out.println("调用DataBase.getResultSetData()函数错误:\r\n" + sqle);
		}
		return vect;
	}

	/**创建申明对象*/
	public void prepareStatement(String sql)
	{
		try
		{
			pstm = conn.prepareStatement(sql);
		}
		catch(SQLException sqle)
		{
			System.out.println("调用DataBase.prepareStatement()函数错误:\r\n" + sqle);
		}
	}

	/**执行查询*/
	public void executeQuery()
	{
		try
		{
			rs = pstm.executeQuery();
		}
		catch(SQLException sqle)
		{
			System.out.println("调用DataBase.executeQuery()函数错误:\r\n" + sqle);
		}
	}

	/**转向下一条*/
	public boolean next()
	{
		try
		{
			return rs.next();
		}
		catch(SQLException sqle)
		{
			System.out.println("调用DataBase.next()函数错误:\r\n" + sqle);
		}
		return true;
	}

	/**取得数据并根据数据类型转化为字符串*/
	public String getObject(String field, String sqlType)
	{
		try
		{
			if(rs == null)
			{
				return "";
			}
			if(sqlType.equals("BINARY") || sqlType.equals("VARBINARY"))
			{ //字节型
				byte b[] = rs.getBytes(field);
				return new String(b);
			}
			else if(sqlType.equals("LONGVARBINARY") || sqlType.equals("BLOB"))
			{ //未编码大字节型
				InputStream is = rs.getBinaryStream(field);
				return(new DealFile()).readCHStr(is);
			}
			else if(sqlType.equals("LONGVARCHAR") || sqlType.equals("CLOB"))
			{ //编码大字节型
				InputStream is = rs.getAsciiStream(field);
				return(new DealFile()).readCHStr(is);
			}
			else
			{ //字符串型
				return rs.getString(field);
			}
		}
		catch(Exception sqle)
		{
			System.out.println("调用DataBase.getObject()函数错误:\r\n" + sqle);
		}
		return "";
	}

	/**根据数据类型保存到数据库*/
	public void setObject(int index, String value, String sqlType)
	{
		try
		{
			if(sqlType.equals("ARRAY"))
			{ //数组型?????
			}
			else if(sqlType.equals("BIGINT"))
			{ //64位的有符号整数
				Long l = new Long(value);
				pstm.setObject(index, l);
			}
			else if(sqlType.equals("BINARY") || sqlType.equals("VARBINARY"))
			{ //字节型
				byte b[] = value.getBytes();
				pstm.setObject(index, b);
			}
			else if(sqlType.equals("BIT"))
			{ //布尔型
				Boolean b = new Boolean("true");
				if(value.equals("0"))
				{
					b = new Boolean("false");
				}
				pstm.setObject(index, b);
			}
			else if(sqlType.equals("BLOB") || sqlType.equals("LONGVARBINARY"))
			{ //未编码流类型
				FileInputStream fis = (new DealFile()).toInputStream(value);
				pstm.setBinaryStream(index, fis,
									 (int)(new File("tmp.txt")).length());
			}
			else if(sqlType.equals("CLOB") || sqlType.equals("LONGVARCHAR"))
			{ //编码流类型
				FileInputStream fis = (new DealFile()).toInputStream(value);
				pstm.setAsciiStream(index, fis,
									(int)(new File("tmp.txt")).length());
			}
			else if(sqlType.equals("BOOLEAN"))
			{ //????
			}
			else if(sqlType.equals("CHAR") || sqlType.equals("VARCHAR"))
			{ //字符串型
				pstm.setObject(index, value);
			}
			else if(sqlType.equals("DATALINK"))
			{ //????
			}
			else if(sqlType.equals("DATE"))
			{ //日期型
				int year = Integer.parseInt(value.substring(0, 4));
				int month = Integer.parseInt(value.substring(5, 7));
				int day = Integer.parseInt(value.substring(8, 10));
				java.sql.Date d = new java.sql.Date(year, month, day);
				pstm.setObject(index, d);
			}
			else if(sqlType.equals("DECIMAL") || sqlType.equals("NUMERIC"))
			{ //固定精度十进制型
				java.math.BigDecimal b = new java.math.BigDecimal(value);
				pstm.setObject(index, b);
			}
			else if(sqlType.equals("DISTINCT"))
			{ //????
			}
			else if(sqlType.equals("DOUBLE") || sqlType.equals("FLOAT"))
			{ //双精度浮点型
				Double d = new Double(value);
				pstm.setObject(index, d);
			}
			else if(sqlType.equals("INTEGER"))
			{ //32位的有符号整数
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
			{ //单精度浮点型
				Float f = new Float(value);
				pstm.setObject(index, f);
			}
			else if(sqlType.equals("REF"))
			{ //????
			}
			else if(sqlType.equals("SMALLINT"))
			{ //16位的有符号整数
				Short s = new Short(value);
				pstm.setObject(index, s);
			}
			else if(sqlType.equals("STRUCT"))
			{ //????
			}
			else if(sqlType.equals("TIME"))
			{ //时间型
				int hour = Integer.parseInt(value.substring(0, 2));
				int minute = Integer.parseInt(value.substring(3, 5));
				int second = Integer.parseInt(value.substring(6, 8));
				java.sql.Time t = new java.sql.Time(hour, minute, second);
				pstm.setObject(index, t);
			}
			else if(sqlType.equals("TIMESTAMP"))
			{ //日期时间型
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
			{ //8位无符号整数
				Byte b = new Byte(value);
				pstm.setObject(index, b);
			}
		}
		catch(Exception sqle)
		{
			System.out.println("调用DataBase.setObject()函数错误:\r\n" + sqle);
		}
	}

	/**执行更新*/
	public void executeUpdate()
	{
		try
		{
			pstm.executeUpdate();
		}
		catch(SQLException sqle)
		{
			System.out.println("调用DataBase.executeUpdate()函数错误:\r\n" + sqle);
		}
	}

	/**关闭申明对象*/
	public void closePstm()
	{
		try
		{
			pstm.close();
		}
		catch(SQLException sqle)
		{
			System.out.println("调用DataBase.closePstm()函数错误:\r\n" + sqle);
		}
	}

	/**关闭游标*/
	public void closeRs()
	{
		try
		{
			rs.close();
		}
		catch(SQLException sqle)
		{
			System.out.println("调用DataBase.closeRs()函数错误:\r\n" + sqle);
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
//输出:getString()数字，字符串，日期,getBytes(),getBinaryStream()BLOB,getAsciiStream()CLOB
//均转化为字符串＊＊＊＊＊＊＊＊＊１
//对二进制，输出的均为转化后的字符串，在输入时反转换为二进制流，只要在输出时二进制流和输入时的二进制流一样就行
//检验Binary即可验证是否正确
//输入:setObject();构造相应的对象＊＊＊＊＊＊＊＊＊２

//输出：根据类型来确定使用那个函数(ParentBean)
			//正转：将流转换为字符串(DealFile)
//输入：根据类型来将字符串构造成不同的数据类型(ParentBean)
			//反转：将字符串转换为流(DealFile)

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
		/*必须执行的代码
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

		//System.out.println(db.UpdateCLOB("test","","content","ttew测试tewagdsagsad"));
		//System.out.println(db.QueryCLOB("test","","content"));

		//System.out.println(db.getDataBaseInfo());
		//System.out.println(db.getTableList());
		//System.out.println(db.getTableStruct("test"));
		//System.out.println(db.getResultSetData(db.QuerySQL("select * from test")));
		//System.out.println(db.getResultSetData(db.getTypeInfo()));

		/**?????
		 1.Access不能取得表列表
		 2.Oracle不能取得表结构:CLOB,BLOB
		 3.不能取得视图结构
		 4.clob,blob,time类型读出和写入时没有区分，都当作字符串处理了
		 5.字段精确度问题？如何区分和使用？
		 6.定义不同数据库的需求，
		 7.可视化的界面
		 8.字段映射列表，如何更合理？
		 １。字段类型，创建表时字段属性写法(长度，精确度，)，写入数据时所用的函数setObject
		 　　clob,blob,字符串（Ascii,unicode），数字（整数，浮点数），时间，布尔型，字节类型，数组
		 ２。如何取得access表列表
		 ３。数据库驱动,不同数据库，字段映射配置情况
		 ４。对不同的数据实现不同的配置选项，仿造SQLServer
		 */
		/*
		  //不用addBatch(),clearBatch()
		  //str长度<=file长度-2
		  //间接方式----------------(1)
		  db.prepareStatement("update test set dlxc=?");
		  db.setObject(1,"大类型测试","CLOB");
		  db.executeUpdate();
		  db.closePstm();
		  //----------------------
		  db.prepareStatement("select * from test");
		  db.executeQuery();
		  db.next();
		  System.out.println(db.getObject("dlxc","CLOB"));
		  db.closeRs();
		  db.closePstm();
		  //直接方式---------------------(2)
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
		 db.setObject(1,"大类型测试","BLOB");
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
//db.setObject(1,"大类型测试eee","BLOB");
//db.executeUpdate();
//db.closePstm();

//db.UpdateBLOB_JNDI("test1","bh=1","ziduan","大类型测试333");
//System.out.println(db.QueryBLOB_JNDI("article","id=60","content"));
//System.out.println(db.makeID_Add1("Article","ID","","",true));
		//db.releaseConn();

		//mysql测试
        db.createConn("org.gjt.mm.mysql.Driver","jdbc:mysql://localhost:3306/book","root","admin");
        System.out.println(db.getOnePage("select timestamp from pg where ipaddress='192.168.0.6' order by timestamp desc",1,100));
		//db.releaseMyConnPool();
		System.out.println("\r\n\r\nend");
	}
};