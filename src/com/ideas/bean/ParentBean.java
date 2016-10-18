/**********************************************************
Copyright (C),2003-10-14, Beijing  USTB.
All rights reserved.
Filename: ParentBean.java
Author: 刘中兵
Version 1.0
Date:2003-10-15
Description:封装与数据库类之上,Bean之下,用于选择连接,释放连接
Other:
Variable List:
1.DataBase db = new DataBase();
2.DealString ds = new DealString()//字符串处理时使用对象

Function List:
1.public ParentBean()//构造函数,初始化连接
2.public Connection getConn()//返回一个数据库连接
3.public void createConn(int type,String url,String usr,String pwd)//取得不同数据库的连接
4.public void closeConn()//释放连接
5.public static void showConnNUM(boolean bool)//测试连接计数

//基本函数
6.protected ResultSet selectRecord(String sql)//查询记录
7.protected int insertRecord(Vector vect)//新增记录
8.protected int updateRecord(Vector vect)//修改记录
9.protected void deleteRecord(String sql)//删除记录
10.protected int executeUpdate(String sql)//执行语句

//辅助使用函数
11.public int makeID(String table,String field1,String field2,String value1,boolean type1)//产生唯一编号
   public int makeID(String table,String field1,String field2,String field3,String value1,String value2,boolean type1,boolean type2)
   public int makeID_Add1(String table,String field1,String field2,String value1,boolean type1)//产生唯一编号
   public int makeID_Add1(String table,String field1,String field2,String field3,String value1,String value2,boolean type1,boolean type2)
12.public int toID(String table,String field1,String field2,String value1)//将名称转换为编号
13.public String toName(String table,String field1,String field2,String value1)//将编号转换为名称
14.protected Vector addVector(String field,String value,String type)//生成4、5的域值
15.public Vector getOnePage(String sql,int page,int records)//分页时取得一页的数据量
16.public int setSort(String table,String field1,String field2,String wherestr,String orderstr,boolean b)//为某一个字段进行重新排序

//查询与更新LOB类型
17.public String QueryCLOB(String table,String wherestr,String clobfield)//查询CLOB类型值
18.public int UpdateCLOB(String table,String wherestr,String blobfield,String blobvalue)//修改CLOB类型值
19.public String QueryBLOB(String table,String wherestr,String clobfield)//查询BLOB类型值
20.public int UpdateBLOB(String table,String wherestr,String blobfield,String blobvalue)//修改BLOB类型值
21.public String QueryBLOB_JNDI(String table,String wherestr,String blobfield)//查询BLOB类型值
22.public int UpdateBLOB_JNDI(String table,String wherestr,String blobfield,String blobvalue)//修改BLOB类型值

//数据库结构信息
23.public Hashtable getDataBaseInfo()//数据库信息
24.public Vector getTableList()//数据表列表
25.public Vector getTableStruct(String table)//数据表的结构
26.public Vector getResultSetData(ResultSet rs)//取得数据集内容

//对sql语句的分布处理
27.public void prepareStatement(String sql)//创建申明对象
28.public void executeQuery()//执行查询
29.public boolean next()//转向下一条
30.public String getObject(String field,String sqlType)//取得数据并根据数据类型转化为字符串
31.public void setObject(int index,String value,String sqlType)//根据数据类型保存到数据库
32.public void executeUpdate()//执行更新
33.public void closePstm()//关闭申明对象
34.public void closeRs()//关闭游标

History:
***********************************************************/
package com.ideas.bean;

import java.lang.*;
import java.util.*;
import java.sql.*;
import com.ideas.util.*;

public class ParentBean extends Object
{
	//数据库处理对象*/
	protected DataBase db = new DataBase();
	//字符串处理时使用对象*/
	protected static DealString ds = new DealString();

	//统计数据库当前实际连接数*/
	static int curnum = 0;
	//统计数据库接受请求连接数*/
	static long sumnum= 0;


	/**当前连接的数据库类型*/
	public static String DBType = "NULL";
	public static String DBName = "";
	public static String FilesPath = "";

	//构造函数,初始化连接*/
	public ParentBean()
	{
		//连接池取得连接
		//db.getConnPool();
		//System.out.println("从连接池创建连接成功!");

		//从类里取得连接
		//db.createConn("oracle.jdbc.driver.OracleDriver","jdbc:oracle:thin:@10.0.0.94:1521:hg","oaadminuser","lancom4454");
		//db.createConn("oracle.jdbc.driver.OracleDriver","jdbc:oracle:thin:@192.168.0.53:1521:jw","jw","jw");
		//db.createConn(oracle.jdbc.driver.OracleDriver","jdbc:oracle:thin:@10.2.4.253:1521:jw","jw","u2s0t0B3");
		//db.createConn("oracle.jdbc.driver.OracleDriver","jdbc:oracle:thin:@192.168.0.63:1521:hg","oa","oa");

		//从Access取得连接
		//db.createConn("sun.jdbc.odbc.JdbcOdbcDriver","jdbc:odbc:HANFENG","admin","");

		//System.out.println("通过自身创建连接成功!");

		DBType = "Oracle";
		DBName = "jw";
		//init();

//		System.out.print("\r当前实际连接数:["+ds.toLengthStr(curnum+"",5)+"]                接受请求连接数:["+ds.toLengthStr(sumnum+"",10)+"]");
	}



	public String getPara()
	{
		String tmp;
		tmp = Configuration.ConfigFilePath;
		tmp = tmp + Configuration.DB_URL + "|" + Configuration.DB_USERNAME + "|" + Configuration.DB_PASSWORD + "|";
		return tmp;
	}

	public void init()
	{
		try
		{
			ResultSet rs = selectRecord("select XMMC from CODE_ZDB where ZDMC='上传文件存放路径'");
			if(rs.next())
			{
				FilesPath = rs.getString(1);
			}
			Statement stm = rs.getStatement();
			rs.close();
			stm.close();
		}
		catch (SQLException e){}
		{
		}
	}

	public Vector getDataBySql(String sql) { return db.getData(sql); }

	/**返回一个数据库连接*/
	public Connection getConn()
	{
		DBType = "Oracle";
		DBName = "jw";
		return db.conn;
	}

	/**取得不同数据库的连接*/
	public void createConn(int type,String ip,String port,String dbsrv,String dbname,String usr,String pwd)
	{
		/**
		 *type: 数据库类型
		 *      1. Oracle		url示例:"jdbc:oracle:thin:@localhost:1521:orcl"
		 *      2. Access		url示例:"jdbc:odbc:HANFENG"
		 *      3. SQL Server	url示例:"jdbc:microsoft:sqlserver://localhost:1433;DatabaseName=pubs"
		 *      4. DB2			url示例:"jdbc:db2://localhost:5000/sample"
		 *      5. Informix		url示例:"jdbc:informix-sqli://123.45.67.89:1533/testDB:INFORMIXSERVER=myserver"
		 *      6. Sybase		url示例:"jdbc:sybase:Tds:localhost:5007/tsdata"
		 *		7. MySQL		url示例:"jdbc:mysql://localhost/softforum?user=soft&password=soft1234&useUnicode=true&characterEncoding=8859_1"
		 *		8. PostgreSQL	url示例:"jdbc:postgresql://localhost/soft"
		 *		9. Dbtools		url示例:
		 *		10.Foxpro		url示例:
		 *		11.Paradox		url示例:
		 *		12.Excel		url示例:
		 *		13.Text			url示例:
		 *		14.XBase		url示例:
		 *		15.dBase		url示例:
		 *		16.FoxBase		url示例:
		 *		17.SQLBase		url示例:
		 *		18.Approach		url示例:
		 *
		 *ip:		地址，如192.168.0.1,或localhost
		 *port:		端口号
		 *dbsrv:	数据库服务器(Informix专有)
		 *dbname:	数据库名,或数据源名
		 *
		 *参数用法:
		 *			1.type不同数据库使用不同序号；
		 *			2.Access只是用数据源dbname；
		 *			3.dbsrv只有使用Informix；
		 *			4.port对MySQL,PostgreSQL不用；
		 *			5.ip,port,dbnme除了以上2和4之外都要使用；
		*/
		this.DBName = dbname;

		if(type==1)//Oracle
		{
			db.createConn("oracle.jdbc.driver.OracleDriver","jdbc:oracle:thin:@"+ip+":"+port+":"+dbname,usr,pwd);
			DBType =  "Oralce";
		}
		else if(type==2)//Access
		{
			db.createConn("sun.jdbc.odbc.JdbcOdbcDriver","jdbc:odbc:"+dbname);
			DBType =  "Access";
		}
		else if(type==3)//SQL Server
		{
			db.createConn("com.microsoft.jdbc.sqlserver.SQLServerDriver","jdbc:microsoft:sqlserver://"+ip+":"+port+";DatabaseName="+dbname,usr,pwd);
			DBType =  "SQLServer";
		}
		else if(type==4)//DB2
		{
			db.createConn("com.ibm.db2.jdbc.app.DB2Driver","jdbc:db2://"+ip+":"+port+"/"+dbname,usr,pwd);
			DBType =  "DB2";
		}
		else if(type==5)//Informix
		{
			db.createConn("com.informix.jdbc.IfxDriver","jdbc:informix-sqli://"+ip+":"+port+"/"+dbname+":INFORMIXSERVER="+dbsrv,usr,pwd);
			DBType =  "Informix";
		}
		else if(type==6)//Sybase
		{
			db.createConn("com.sybase.jdbc.SybDriver","jdbc:sybase:Tds:"+ip+":"+port+"/"+dbname,usr,pwd);
			DBType =  "Sybase";
		}
		else if(type==7)//MySQL
		{
			db.createConn("org.gjt.mm.mysql.Driver","jdbc:mysql://"+ip+":"+port+"/"+dbname,usr,pwd);
		    //System.out.println("ip:"+ip);
		    //System.out.println("port:"+port);
		    //System.out.println("dbname:"+dbname);
		    //System.out.println("usr:"+usr);
		    //System.out.println("pwd:"+pwd);
			//db.createConn("jdbc:mysql://localhost/BookStore?user=root&password=admin&&useUnicode=true&characterEncoding=8859_1","","","");
			DBType =  "MySQL";
		}
		else if(type==8)//PostgreSQL
		{
			db.createConn("org.postgresql.Driver","jdbc:postgresql://"+ip+"/s"+dbname,usr,pwd);
			DBType =  "PostgreSQL";
		}
		else if(type==9)//Dbtools
		{
			DBType =  "Dbtools";
		}
		else if(type==10)//Foxpro
		{
			DBType =  "Foxpro";
		}
		else if(type==11)//Paradox
		{
			DBType =  "Paradox";
		}
		else if(type==12)//Excel
		{
			DBType =  "Excel";
		}
		else if(type==13)//Text
		{
			DBType =  "Text";
		}
		else if(type==14)//XBase
		{
			DBType =  "XBase";
		}
		else if(type==15)//dBase
		{
			DBType =  "dBase";
		}
		else if(type==16)//FoxBas
		{
			DBType =  "FoxBas";
		}
		else if(type==17)//SQLBase
		{
			DBType =  "SQLBase";
		}
		else if(type==18)//Approach
		{
			DBType =  "Approach";
		}
		else
		{
			DBType =  "NULL";
		}
	}

	/**释放连接*/
	public void closeConn()
	{
		DBType = "NULL";
		db.releaseConn();
		//System.out.println("释放连接");

	//	System.out.print("\r当前实际连接数:["+ds.toLengthStr(curnum+"",5)+"]                接受请求连接数:["+ds.toLengthStr(sumnum+"",10)+"]");

	}
	public static void showConnNUM(boolean bool)
	{
		if(bool)//申请连接
		{
			curnum++;
			sumnum++;
		}
		else//释放连接
		{
			curnum--;
		}
		System.out.print("\r当前实际连接数:["+ds.toLengthStr(curnum+"",5)+"]                接受请求连接数:["+ds.toLengthStr(sumnum+"",10)+"]");
	}

	/**查询记录*/
	protected ResultSet selectRecord(String sql)
	{
		return db.QuerySQL(sql);
	}

	/**新增记录*/
	protected int insertRecord(Vector vect)
	{
		/**Vector:第1项 表名(String)
		//		 第2项 列名(Vector[Field(String),Value(String,CLOB,BLOB),Type("CHAR","NUM","TIME","CLOB","BLOB")])*/

		//临时变量
		String sqlField = "";//形如(F1,F2)
		String sqlValue = "";//形如(V1,V2)
		String field = "";
		String value = "";
		String type = "";

		for(int i=1;i<vect.size();i++)
		{
			//对某一个字段
			Vector v_t = (Vector)vect.get(i);
			field = (String)v_t.get(0);
			value = (String)v_t.get(1);
			if (value.indexOf("'")!=-1)
			{
				value = value.replaceAll("'","''");
			}
			type = (String)v_t.get(2);

			//组合字段SQL
			if(sqlField.equals(""))sqlField = " (";
			else sqlField = sqlField + ",";
			sqlField = sqlField + field;

			//组合值SQL
			if(sqlValue.equals(""))sqlValue = "(";
			else sqlValue = sqlValue + ",";
			if(value.equals(""))//为空时
			{
				sqlValue = sqlValue + "null";
			}
			else if(type.equals("CHAR"))//字符串
			{
				sqlValue = sqlValue + "'" + value + "'";
			}
			else if(type.equals("NUM"))//数值
			{
				sqlValue = sqlValue + value;
			}
			else if(type.equals("TIME"))//日期
			{
				sqlValue = sqlValue + "to_date('yyyy-MM-dd HH:mm:ss','" + value + "')";
			}
			else if(type.equals("CLOB"))//clob类型
			{
				sqlValue = sqlValue + "empty_clob()";
			}
			else if(type.equals("BLOB"))//blob类型
			{
				sqlValue = sqlValue + "empty_blob()";
			}
		}

		sqlField = sqlField + ")";
		sqlValue = sqlValue + ")";
		String sql = "insert into " + (String)vect.get(0) + sqlField + " values" + sqlValue;
		return db.ExecuteSQL(sql);
	}

	/**修改记录*/
	protected int updateRecord(Vector vect)
	{
		/**Vector:第1项 表名(String)
		//		 第2项 列名(Vector[Field(String),Value(String,CLOB,BLOB),Type("CHAR","NUM","TIME","CLOB","BLOB")])
		//		 第3项 条件(String sql)*/

		//临时变量
		String sqlSet = "";//形如(Name='name',ID=9)
		String field = "";
		String value = "";
		String type = "";

		int i = 1;
		int n = vect.size();
		for(;i<(n-1);i++)
		{
			//对某一个字段
			Vector v_t = (Vector)vect.get(i);
			field = (String)v_t.get(0);
			value = (String)v_t.get(1);
			if (value.indexOf("'")!=-1)
			{
				value = value.replaceAll("'","''");
			}
			type = (String)v_t.get(2);

			//组合字段SQL
			if(sqlSet.equals(""))sqlSet = " ";
			else sqlSet = sqlSet + ",";
			sqlSet = sqlSet + field + "=";
			if(value.equals("")&&type.equals("NUM"))//为空时
			{
				sqlSet = sqlSet + "null";
			}
			if(type.equals("CHAR"))//字符串
			{
				sqlSet = sqlSet + "'" + value + "'";
			}
			else if(type.equals("NUM"))//数值
			{
				sqlSet = sqlSet + value;
			}
			else if(type.equals("TIME"))//日期
			{
				sqlSet = sqlSet + "to_date('yyyy-MM-dd HH:mm:ss','" + value + "')";
			}
			else if(type.equals("CLOB"))//clob类型
			{
			}
			else if(type.equals("BLOB"))//blob类型
			{
			}
		}

		String sql = "update " + (String)vect.get(0) + " set " + sqlSet;
		String sqlWhere = (String)vect.get(vect.size()-1);
		if(!sqlWhere.equals(""))
		{
			sql = sql + " where " + sqlWhere;
		}

		return db.ExecuteSQL(sql);
	}

	/**删除记录*/
	protected int deleteRecord(String sql)
	{
		return db.ExecuteSQL(sql);
	}
	/**执行语句*/
	protected int executeUpdate(String sql)
	{
		return db.ExecuteSQL(sql);
	}



	/**产生唯一编号*/
	public int makeID(String table,String field1,String field2,String value1,boolean type1)
	{
		return db.makeID(table,field1,field2,value1,type1);
	}
 	public int makeID(String table,String field1,String field2,String field3,String value1,String value2,boolean type1,boolean type2)
	{
		return db.makeID(table,field1,field2,field3,value1,value2,type1,type2);
	}

	public int makeID_Add1(String table,String field1,String field2,String value1,boolean type1)
	{
		return db.makeID(table,field1,field2,value1,type1);
	}
 	public int makeID_Add1(String table,String field1,String field2,String field3,String value1,String value2,boolean type1,boolean type2)
	{
		return db.makeID(table,field1,field2,field3,value1,value2,type1,type2);
	}

	/**将名称转换为编号*/
	public int toID(String table,String field1,String field2,String value1)
	{
		return db.toID(table,field1,field2,value1);
	}

	/**将编号转换为名称*/
	public String toName(String table,String field1,String field2,String value1)
	{
		return db.toName(table,field1,field2,value1);
	}

	/**写数据库时某一个字段的存储类型*/
	protected Vector addVector(String field,String value,String type)
	{
		Vector vect = new Vector();
		vect.add(field);
		vect.add(value);
		vect.add(type);
		return vect;
	}

	/**分页时取得一页的数据量*/
	public Vector getOnePage(String sql,int page,int records)
	{
		return db.getOnePage(sql,page,records);
	}

	/**为某一个字段进行重新排序*/
	public int setSort(String table,String field1,String field2,String wherestr,String orderstr,boolean b)
	{
		return db.setSort(table,field1,field2,wherestr,orderstr,b);
	}

	/**查询CLOB类型值*/
	public String QueryCLOB(String table,String wherestr,String clobfield)
	{
		return db.QueryCLOB(table,wherestr,clobfield);
	}

	/**修改CLOB类型值*/
	public int UpdateCLOB(String table,String wherestr,String clobfield,String clobvalue)
	{
		return db.UpdateCLOB(table,wherestr,clobfield,clobvalue);
	}

	/**查询BLOB类型值*/
	public String QueryBLOB(String table,String wherestr,String blobfield)
	{
		return db.QueryBLOB(table,wherestr,blobfield);
	}

	/**修改BLOB类型值*/
	public int UpdateBLOB(String table,String wherestr,String blobfield,String blobvalue)
	{
		return db.UpdateBLOB(table,wherestr,blobfield,blobvalue);
	}
	/**查询BLOB类型值*/
	public String QueryBLOB_JNDI(String table,String wherestr,String blobfield)
	{
		return db.QueryBLOB_JNDI(table,wherestr,blobfield);
	}

	/**修改BLOB类型值*/
	public int UpdateBLOB_JNDI(String table,String wherestr,String blobfield,String blobvalue)
	{
		return db.UpdateBLOB_JNDI(table,wherestr,blobfield,blobvalue);
	}


	/**数据库信息*/
	public Hashtable getDataBaseInfo()
	{
		return db.getDataBaseInfo();
	}

	/**数据表列表*/
	public Vector getTableList()
	{
		return db.getTableList();
	}

	/**数据表的结构*/
	public Vector getTableStruct(String table)
	{
		return db.getTableStruct(table);
	}

	/**取得数据集内容*/
	public Vector getResultSetData(ResultSet rs)
	{
		return db.getResultSetData(rs);
	}

	/**创建申明对象*/
	public void prepareStatement(String sql)
	{
		db.prepareStatement(sql);
	}

	/**执行查询*/
	public void executeQuery()
	{
		db.executeQuery();
	}

	/**转向下一条*/
	public boolean next()
	{
		return db.next();
	}

	/**取得数据并根据数据类型转化为字符串*/
	public String getObject(String field,String sqlType)
	{
		return db.getObject(field,sqlType);
	}

	/**根据数据类型保存到数据库*/
	public void setObject(int index,String value,String sqlType)
	{
		db.setObject(index,value,sqlType);
	}

	/**执行更新*/
	public void executeUpdate()
	{
		db.executeUpdate();
	}

	/**关闭申明对象*/
	public void closePstm()
	{
		db.closePstm();
	}

	/**关闭游标*/
	public void closeRs()
	{
		db.closeRs();
	}

	public static void main(String args[]) throws Exception
	{
		ParentBean mb = new ParentBean();


		System.out.println("begin\r\n\r\n");

		//修改方法
		//Vector vect = new Vector();
		//vect.add("test");
		////vect.add(mb.addVector("ID","100","NUM"));
		//vect.add(mb.addVector("NAME","libohua","CHAR"));
		//vect.add("");
		//System.out.println(mb.updateRecord(vect));

		//新增方法
		//vect.clear();
		////vect.add(mb.addVector("ID","100","NUM"));
		//vect.add(mb.addVector("NAME","libohua","CHAR"));
		//vect.add("");
		//System.out.println(mb.insertRecord(vect));

		//分页方法
		//System.out.println(mb.getOnePage("select * from css",2,3));

		System.out.println(mb.FilesPath);

		System.out.println("\r\n\r\nend");
	}
}