/**********************************************************
Copyright (C),2003-10-14, Beijing  USTB.
All rights reserved.
Filename: ParentBean.java
Author: ���б�
Version 1.0
Date:2003-10-15
Description:��װ�����ݿ���֮��,Bean֮��,����ѡ������,�ͷ�����
Other:
Variable List:
1.DataBase db = new DataBase();
2.DealString ds = new DealString()//�ַ�������ʱʹ�ö���

Function List:
1.public ParentBean()//���캯��,��ʼ������
2.public Connection getConn()//����һ�����ݿ�����
3.public void createConn(int type,String url,String usr,String pwd)//ȡ�ò�ͬ���ݿ������
4.public void closeConn()//�ͷ�����
5.public static void showConnNUM(boolean bool)//�������Ӽ���

//��������
6.protected ResultSet selectRecord(String sql)//��ѯ��¼
7.protected int insertRecord(Vector vect)//������¼
8.protected int updateRecord(Vector vect)//�޸ļ�¼
9.protected void deleteRecord(String sql)//ɾ����¼
10.protected int executeUpdate(String sql)//ִ�����

//����ʹ�ú���
11.public int makeID(String table,String field1,String field2,String value1,boolean type1)//����Ψһ���
   public int makeID(String table,String field1,String field2,String field3,String value1,String value2,boolean type1,boolean type2)
   public int makeID_Add1(String table,String field1,String field2,String value1,boolean type1)//����Ψһ���
   public int makeID_Add1(String table,String field1,String field2,String field3,String value1,String value2,boolean type1,boolean type2)
12.public int toID(String table,String field1,String field2,String value1)//������ת��Ϊ���
13.public String toName(String table,String field1,String field2,String value1)//�����ת��Ϊ����
14.protected Vector addVector(String field,String value,String type)//����4��5����ֵ
15.public Vector getOnePage(String sql,int page,int records)//��ҳʱȡ��һҳ��������
16.public int setSort(String table,String field1,String field2,String wherestr,String orderstr,boolean b)//Ϊĳһ���ֶν�����������

//��ѯ�����LOB����
17.public String QueryCLOB(String table,String wherestr,String clobfield)//��ѯCLOB����ֵ
18.public int UpdateCLOB(String table,String wherestr,String blobfield,String blobvalue)//�޸�CLOB����ֵ
19.public String QueryBLOB(String table,String wherestr,String clobfield)//��ѯBLOB����ֵ
20.public int UpdateBLOB(String table,String wherestr,String blobfield,String blobvalue)//�޸�BLOB����ֵ
21.public String QueryBLOB_JNDI(String table,String wherestr,String blobfield)//��ѯBLOB����ֵ
22.public int UpdateBLOB_JNDI(String table,String wherestr,String blobfield,String blobvalue)//�޸�BLOB����ֵ

//���ݿ�ṹ��Ϣ
23.public Hashtable getDataBaseInfo()//���ݿ���Ϣ
24.public Vector getTableList()//���ݱ��б�
25.public Vector getTableStruct(String table)//���ݱ�Ľṹ
26.public Vector getResultSetData(ResultSet rs)//ȡ�����ݼ�����

//��sql���ķֲ�����
27.public void prepareStatement(String sql)//������������
28.public void executeQuery()//ִ�в�ѯ
29.public boolean next()//ת����һ��
30.public String getObject(String field,String sqlType)//ȡ�����ݲ�������������ת��Ϊ�ַ���
31.public void setObject(int index,String value,String sqlType)//�����������ͱ��浽���ݿ�
32.public void executeUpdate()//ִ�и���
33.public void closePstm()//�ر���������
34.public void closeRs()//�ر��α�

History:
***********************************************************/
package com.ideas.bean;

import java.lang.*;
import java.util.*;
import java.sql.*;
import com.ideas.util.*;

public class ParentBean extends Object
{
	//���ݿ⴦�����*/
	protected DataBase db = new DataBase();
	//�ַ�������ʱʹ�ö���*/
	protected static DealString ds = new DealString();

	//ͳ�����ݿ⵱ǰʵ��������*/
	static int curnum = 0;
	//ͳ�����ݿ��������������*/
	static long sumnum= 0;


	/**��ǰ���ӵ����ݿ�����*/
	public static String DBType = "NULL";
	public static String DBName = "";
	public static String FilesPath = "";

	//���캯��,��ʼ������*/
	public ParentBean()
	{
		//���ӳ�ȡ������
		//db.getConnPool();
		//System.out.println("�����ӳش������ӳɹ�!");

		//������ȡ������
		//db.createConn("oracle.jdbc.driver.OracleDriver","jdbc:oracle:thin:@10.0.0.94:1521:hg","oaadminuser","lancom4454");
		//db.createConn("oracle.jdbc.driver.OracleDriver","jdbc:oracle:thin:@192.168.0.53:1521:jw","jw","jw");
		//db.createConn(oracle.jdbc.driver.OracleDriver","jdbc:oracle:thin:@10.2.4.253:1521:jw","jw","u2s0t0B3");
		//db.createConn("oracle.jdbc.driver.OracleDriver","jdbc:oracle:thin:@192.168.0.63:1521:hg","oa","oa");

		//��Accessȡ������
		//db.createConn("sun.jdbc.odbc.JdbcOdbcDriver","jdbc:odbc:HANFENG","admin","");

		//System.out.println("ͨ�����������ӳɹ�!");

		DBType = "Oracle";
		DBName = "jw";
		//init();

//		System.out.print("\r��ǰʵ��������:["+ds.toLengthStr(curnum+"",5)+"]                ��������������:["+ds.toLengthStr(sumnum+"",10)+"]");
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
			ResultSet rs = selectRecord("select XMMC from CODE_ZDB where ZDMC='�ϴ��ļ����·��'");
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

	/**����һ�����ݿ�����*/
	public Connection getConn()
	{
		DBType = "Oracle";
		DBName = "jw";
		return db.conn;
	}

	/**ȡ�ò�ͬ���ݿ������*/
	public void createConn(int type,String ip,String port,String dbsrv,String dbname,String usr,String pwd)
	{
		/**
		 *type: ���ݿ�����
		 *      1. Oracle		urlʾ��:"jdbc:oracle:thin:@localhost:1521:orcl"
		 *      2. Access		urlʾ��:"jdbc:odbc:HANFENG"
		 *      3. SQL Server	urlʾ��:"jdbc:microsoft:sqlserver://localhost:1433;DatabaseName=pubs"
		 *      4. DB2			urlʾ��:"jdbc:db2://localhost:5000/sample"
		 *      5. Informix		urlʾ��:"jdbc:informix-sqli://123.45.67.89:1533/testDB:INFORMIXSERVER=myserver"
		 *      6. Sybase		urlʾ��:"jdbc:sybase:Tds:localhost:5007/tsdata"
		 *		7. MySQL		urlʾ��:"jdbc:mysql://localhost/softforum?user=soft&password=soft1234&useUnicode=true&characterEncoding=8859_1"
		 *		8. PostgreSQL	urlʾ��:"jdbc:postgresql://localhost/soft"
		 *		9. Dbtools		urlʾ��:
		 *		10.Foxpro		urlʾ��:
		 *		11.Paradox		urlʾ��:
		 *		12.Excel		urlʾ��:
		 *		13.Text			urlʾ��:
		 *		14.XBase		urlʾ��:
		 *		15.dBase		urlʾ��:
		 *		16.FoxBase		urlʾ��:
		 *		17.SQLBase		urlʾ��:
		 *		18.Approach		urlʾ��:
		 *
		 *ip:		��ַ����192.168.0.1,��localhost
		 *port:		�˿ں�
		 *dbsrv:	���ݿ������(Informixר��)
		 *dbname:	���ݿ���,������Դ��
		 *
		 *�����÷�:
		 *			1.type��ͬ���ݿ�ʹ�ò�ͬ��ţ�
		 *			2.Accessֻ��������Դdbname��
		 *			3.dbsrvֻ��ʹ��Informix��
		 *			4.port��MySQL,PostgreSQL���ã�
		 *			5.ip,port,dbnme��������2��4֮�ⶼҪʹ�ã�
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

	/**�ͷ�����*/
	public void closeConn()
	{
		DBType = "NULL";
		db.releaseConn();
		//System.out.println("�ͷ�����");

	//	System.out.print("\r��ǰʵ��������:["+ds.toLengthStr(curnum+"",5)+"]                ��������������:["+ds.toLengthStr(sumnum+"",10)+"]");

	}
	public static void showConnNUM(boolean bool)
	{
		if(bool)//��������
		{
			curnum++;
			sumnum++;
		}
		else//�ͷ�����
		{
			curnum--;
		}
		System.out.print("\r��ǰʵ��������:["+ds.toLengthStr(curnum+"",5)+"]                ��������������:["+ds.toLengthStr(sumnum+"",10)+"]");
	}

	/**��ѯ��¼*/
	protected ResultSet selectRecord(String sql)
	{
		return db.QuerySQL(sql);
	}

	/**������¼*/
	protected int insertRecord(Vector vect)
	{
		/**Vector:��1�� ����(String)
		//		 ��2�� ����(Vector[Field(String),Value(String,CLOB,BLOB),Type("CHAR","NUM","TIME","CLOB","BLOB")])*/

		//��ʱ����
		String sqlField = "";//����(F1,F2)
		String sqlValue = "";//����(V1,V2)
		String field = "";
		String value = "";
		String type = "";

		for(int i=1;i<vect.size();i++)
		{
			//��ĳһ���ֶ�
			Vector v_t = (Vector)vect.get(i);
			field = (String)v_t.get(0);
			value = (String)v_t.get(1);
			if (value.indexOf("'")!=-1)
			{
				value = value.replaceAll("'","''");
			}
			type = (String)v_t.get(2);

			//����ֶ�SQL
			if(sqlField.equals(""))sqlField = " (";
			else sqlField = sqlField + ",";
			sqlField = sqlField + field;

			//���ֵSQL
			if(sqlValue.equals(""))sqlValue = "(";
			else sqlValue = sqlValue + ",";
			if(value.equals(""))//Ϊ��ʱ
			{
				sqlValue = sqlValue + "null";
			}
			else if(type.equals("CHAR"))//�ַ���
			{
				sqlValue = sqlValue + "'" + value + "'";
			}
			else if(type.equals("NUM"))//��ֵ
			{
				sqlValue = sqlValue + value;
			}
			else if(type.equals("TIME"))//����
			{
				sqlValue = sqlValue + "to_date('yyyy-MM-dd HH:mm:ss','" + value + "')";
			}
			else if(type.equals("CLOB"))//clob����
			{
				sqlValue = sqlValue + "empty_clob()";
			}
			else if(type.equals("BLOB"))//blob����
			{
				sqlValue = sqlValue + "empty_blob()";
			}
		}

		sqlField = sqlField + ")";
		sqlValue = sqlValue + ")";
		String sql = "insert into " + (String)vect.get(0) + sqlField + " values" + sqlValue;
		return db.ExecuteSQL(sql);
	}

	/**�޸ļ�¼*/
	protected int updateRecord(Vector vect)
	{
		/**Vector:��1�� ����(String)
		//		 ��2�� ����(Vector[Field(String),Value(String,CLOB,BLOB),Type("CHAR","NUM","TIME","CLOB","BLOB")])
		//		 ��3�� ����(String sql)*/

		//��ʱ����
		String sqlSet = "";//����(Name='name',ID=9)
		String field = "";
		String value = "";
		String type = "";

		int i = 1;
		int n = vect.size();
		for(;i<(n-1);i++)
		{
			//��ĳһ���ֶ�
			Vector v_t = (Vector)vect.get(i);
			field = (String)v_t.get(0);
			value = (String)v_t.get(1);
			if (value.indexOf("'")!=-1)
			{
				value = value.replaceAll("'","''");
			}
			type = (String)v_t.get(2);

			//����ֶ�SQL
			if(sqlSet.equals(""))sqlSet = " ";
			else sqlSet = sqlSet + ",";
			sqlSet = sqlSet + field + "=";
			if(value.equals("")&&type.equals("NUM"))//Ϊ��ʱ
			{
				sqlSet = sqlSet + "null";
			}
			if(type.equals("CHAR"))//�ַ���
			{
				sqlSet = sqlSet + "'" + value + "'";
			}
			else if(type.equals("NUM"))//��ֵ
			{
				sqlSet = sqlSet + value;
			}
			else if(type.equals("TIME"))//����
			{
				sqlSet = sqlSet + "to_date('yyyy-MM-dd HH:mm:ss','" + value + "')";
			}
			else if(type.equals("CLOB"))//clob����
			{
			}
			else if(type.equals("BLOB"))//blob����
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

	/**ɾ����¼*/
	protected int deleteRecord(String sql)
	{
		return db.ExecuteSQL(sql);
	}
	/**ִ�����*/
	protected int executeUpdate(String sql)
	{
		return db.ExecuteSQL(sql);
	}



	/**����Ψһ���*/
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

	/**������ת��Ϊ���*/
	public int toID(String table,String field1,String field2,String value1)
	{
		return db.toID(table,field1,field2,value1);
	}

	/**�����ת��Ϊ����*/
	public String toName(String table,String field1,String field2,String value1)
	{
		return db.toName(table,field1,field2,value1);
	}

	/**д���ݿ�ʱĳһ���ֶεĴ洢����*/
	protected Vector addVector(String field,String value,String type)
	{
		Vector vect = new Vector();
		vect.add(field);
		vect.add(value);
		vect.add(type);
		return vect;
	}

	/**��ҳʱȡ��һҳ��������*/
	public Vector getOnePage(String sql,int page,int records)
	{
		return db.getOnePage(sql,page,records);
	}

	/**Ϊĳһ���ֶν�����������*/
	public int setSort(String table,String field1,String field2,String wherestr,String orderstr,boolean b)
	{
		return db.setSort(table,field1,field2,wherestr,orderstr,b);
	}

	/**��ѯCLOB����ֵ*/
	public String QueryCLOB(String table,String wherestr,String clobfield)
	{
		return db.QueryCLOB(table,wherestr,clobfield);
	}

	/**�޸�CLOB����ֵ*/
	public int UpdateCLOB(String table,String wherestr,String clobfield,String clobvalue)
	{
		return db.UpdateCLOB(table,wherestr,clobfield,clobvalue);
	}

	/**��ѯBLOB����ֵ*/
	public String QueryBLOB(String table,String wherestr,String blobfield)
	{
		return db.QueryBLOB(table,wherestr,blobfield);
	}

	/**�޸�BLOB����ֵ*/
	public int UpdateBLOB(String table,String wherestr,String blobfield,String blobvalue)
	{
		return db.UpdateBLOB(table,wherestr,blobfield,blobvalue);
	}
	/**��ѯBLOB����ֵ*/
	public String QueryBLOB_JNDI(String table,String wherestr,String blobfield)
	{
		return db.QueryBLOB_JNDI(table,wherestr,blobfield);
	}

	/**�޸�BLOB����ֵ*/
	public int UpdateBLOB_JNDI(String table,String wherestr,String blobfield,String blobvalue)
	{
		return db.UpdateBLOB_JNDI(table,wherestr,blobfield,blobvalue);
	}


	/**���ݿ���Ϣ*/
	public Hashtable getDataBaseInfo()
	{
		return db.getDataBaseInfo();
	}

	/**���ݱ��б�*/
	public Vector getTableList()
	{
		return db.getTableList();
	}

	/**���ݱ�Ľṹ*/
	public Vector getTableStruct(String table)
	{
		return db.getTableStruct(table);
	}

	/**ȡ�����ݼ�����*/
	public Vector getResultSetData(ResultSet rs)
	{
		return db.getResultSetData(rs);
	}

	/**������������*/
	public void prepareStatement(String sql)
	{
		db.prepareStatement(sql);
	}

	/**ִ�в�ѯ*/
	public void executeQuery()
	{
		db.executeQuery();
	}

	/**ת����һ��*/
	public boolean next()
	{
		return db.next();
	}

	/**ȡ�����ݲ�������������ת��Ϊ�ַ���*/
	public String getObject(String field,String sqlType)
	{
		return db.getObject(field,sqlType);
	}

	/**�����������ͱ��浽���ݿ�*/
	public void setObject(int index,String value,String sqlType)
	{
		db.setObject(index,value,sqlType);
	}

	/**ִ�и���*/
	public void executeUpdate()
	{
		db.executeUpdate();
	}

	/**�ر���������*/
	public void closePstm()
	{
		db.closePstm();
	}

	/**�ر��α�*/
	public void closeRs()
	{
		db.closeRs();
	}

	public static void main(String args[]) throws Exception
	{
		ParentBean mb = new ParentBean();


		System.out.println("begin\r\n\r\n");

		//�޸ķ���
		//Vector vect = new Vector();
		//vect.add("test");
		////vect.add(mb.addVector("ID","100","NUM"));
		//vect.add(mb.addVector("NAME","libohua","CHAR"));
		//vect.add("");
		//System.out.println(mb.updateRecord(vect));

		//��������
		//vect.clear();
		////vect.add(mb.addVector("ID","100","NUM"));
		//vect.add(mb.addVector("NAME","libohua","CHAR"));
		//vect.add("");
		//System.out.println(mb.insertRecord(vect));

		//��ҳ����
		//System.out.println(mb.getOnePage("select * from css",2,3));

		System.out.println(mb.FilesPath);

		System.out.println("\r\n\r\nend");
	}
}