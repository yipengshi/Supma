package com.ideas.bean;


import java.lang.*;
import java.util.*;
import java.sql.*;

public class commentBean extends ParentBean
{
	int strID = -1; //Ψһ���

	public void setID(String o)//�������۱��
	{
		strID = Integer.parseInt(o);
	}	

    public Vector getCurPage(int cur,int records,String articleid)
    {
        return getOnePage("SELECT * FROM commentb where articleid='"+articleid+"' order by id",cur,records);
    }

	public Vector getData()//ȡ���������۱����м�¼
	{
		Vector vect = new Vector();
		String sql = "";

		sql = " select * from commentb order by articleid,id ";
			
		ResultSet rs = selectRecord(sql);
		Statement stmt = null;

		try{
		//ȡ������������
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
		}catch(Exception e){System.out.println("commentBean.getData()����ʱ����"+e);}
		finally{
			if(rs!=null)try{ stmt = rs.getStatement(); rs.close();}catch(Exception e){System.out.println("commentBean.getData()�رռ�¼��rsʱ����"+e);}
			if(stmt!=null) try{stmt.close();}catch(Exception e){System.out.println("commentBean.getData()�ر�����ʱstatement����"+e);}		
		}
		return vect;
	}

	public Vector getData(String str,String id)//ȡ��ĳһ�����Ż�ĳ�˵��������ۼ�¼ 
	{
		Vector vect = new Vector();
		String sql = "";

		if(id.equals("1"))  //ȡ��ĳһ�����ŵ��������ۼ�¼ 
			sql = " select * from commentb where articleid='"+str+"' order by time desc ";
		if(id.equals("2"))  //ȡ��ĳ�˵��������ۼ�¼ 
			sql = " select * from commentb where critic='"+str+"' order by time desc ";

		ResultSet rs = selectRecord(sql);
		Statement stmt = null;
		try{

		//ȡ������������
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

		}catch(Exception e){System.out.println("commentBean.getData(String,String)����ʱ����"+e);}
		finally{
			if(rs!=null)try{ stmt = rs.getStatement(); rs.close();}catch(Exception e){System.out.println("commentBean.getData(String,String)�رռ�¼��rsʱ����"+e);}
			if(stmt!=null) try{stmt.close();}catch(Exception e){System.out.println("commentBean.getData(String,String)�ر�����ʱstatement����"+e);}		
		}
		return vect;
	}

	public void delete(String cid)
	{
		deleteRecord("delete from commentb where id="+cid);
	}

	
	public int addCom(Hashtable hash)//�����������۱����ݼ�¼
	{			
		String strArticleid = ds.toString((String)hash.get("articleid"));
		String strComment = ds.toString((String)hash.get("content"));
		String strTime = ds.getDateTime();
		String strCritic = ds.toString((String)hash.get("critic"));


		Vector vect =new Vector();
		vect.add("commentb");
		vect.add(addVector("articleid",strArticleid,"CHAR"));
		vect.add(addVector("content",strComment,"CHAR"));
		vect.add(addVector("time",strTime,"CHAR"));
		vect.add(addVector("critic",strCritic,"CHAR"));
	
		return insertRecord(vect);
		
	}	

	public static void main(String args[]) 
	{
		commentBean mb = new commentBean();		
		Hashtable ht = new Hashtable();
			ht.put("ARTICLEID","2");
			ht.put("CONTENT","���ֳ�ffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffff");
			ht.put("CRITIC","1");
		System.out.println("begin\r\n\r\n");
		System.out.println(ht+"\r\n\r\n");
		System.out.println(mb.addCom(ht));
		System.out.println(mb.getData());
		System.out.println(mb.getData("1","1"));
		System.out.println(mb.getData("1","2"));
		mb.closeConn();
		System.out.println("\r\n\r\nend");
	}
}