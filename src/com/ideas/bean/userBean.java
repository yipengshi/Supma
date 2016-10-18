package com.ideas.bean;


import java.lang.*;
import java.util.*;
import java.sql.*;

public class userBean extends ParentBean
{
    String id = "-1";

    public void setID(String id)
    {
        this.id = id;
    }

    public Vector getCurPage(int cur,int records)
    {
        return getOnePage("SELECT * FROM backuser",cur,records);
    }

    public String login(String backuser,String pwd)
    {
        String sql = " select id from backuser where islock='0' and user='"+backuser+"' and pwd='"+pwd+"'";
		return ("1");
/*
        Vector v =  getResultSetData(selectRecord(sql));
        if(v.size()==0)
        {
            return "-1";
        }
        else
        {
            return (String)((Hashtable)v.get(0)).get("id");
        }*/
    }

    //ȡ�����ݱ��е�ĳһ����¼
    public Vector getOneData() throws Exception
    {
        String sql = " select * from backuser where id= "+id;
        return getResultSetData(selectRecord(sql));
    }

    public int add(Hashtable hash) throws Exception
    {
        String user  = ds.toString((String)hash.get("user"));
        String pwd = ds.toString((String)hash.get("pwd"));
        String info	= ds.toString((String)hash.get("info"));

        Vector vect =new Vector();
        vect.add("backuser");
        vect.add(addVector("user",user,"CHAR"));
        vect.add(addVector("pwd",pwd,"CHAR"));
        vect.add(addVector("info",info,"CHAR"));

        String sql = "select * from backuser where user='"+user+"'";
        if(getResultSetData(selectRecord(sql)).size()!=0)
        {
            return 1;
        }
        return insertRecord(vect);
    }

    public int mod(Hashtable hash) throws Exception
    {
        String user  = ds.toString((String)hash.get("user"));
        String pwd = ds.toString((String)hash.get("pwd"));
        String info	= ds.toString((String)hash.get("info"));
        String islock	= ds.toString((String)hash.get("islock"));

        Vector vect =new Vector();
        vect.add("backuser");
        vect.add(addVector("user",user,"CHAR"));
        vect.add(addVector("pwd",pwd,"CHAR"));
        vect.add(addVector("info",info,"CHAR"));
        vect.add(addVector("islock",islock,"CHAR"));
        vect.add("ID="+id+"");
        String sql = "select * from backuser where id!="+id+" and user='"+user+"'";
        if(getResultSetData(selectRecord(sql)).size()!=0)
        {
            return 1;
        }
        return updateRecord(vect);
    }

    public void del()
    {
        deleteRecord("delete from backuser where id="+id);
    }

    public static void main(String args[]) throws Exception
    {
        userBean mb = new userBean();

        //�������ݿ�
        //mb.createConn("org.gjt.mm.mysql.Driver","jdbc:mysql://localhost:3306/yj","admin","admin");
        mb.createConn(7,"localhost","3306","","hpj","admin","admin");

        Hashtable hash= new Hashtable();
        mb.setID("2");
        hash.put("backuser", "admin");
        hash.put("pwd", "pwd");
        hash.put("info", "test");
        //System.out.println(mb.add(hash));



        //ȡ�õ�ǰҲ������
        System.out.println(mb.getCurPage(1,100));
        //System.out.println(mb.login("admin","e"));

        //ȡ�ü�¼��
        //System.out.println(mb.getResultSetData(mb.selectRecord("select count(*) from snals")));

        //�ͷ�����
        System.out.println("\r\n\r\nend");
    }
};
