package com.ideas.bean;


import java.lang.*;
import java.util.*;
import java.sql.*;

public class menu2Bean extends ParentBean
{
    String id = "-1";

    public void setID(String id)
    {
        this.id = id;
    }

    public Vector getCurPage(int cur,int records)
    {
        return getOnePage("SELECT menu2.*,menu1.name as menu1name FROM menu2,menu1 where menu2.id1=menu1.id order by id1,id",cur,records);
    }

    //取得数据表中的某一条记录
    public Vector getOneData() throws Exception
    {
        String sql = " select * from menu2 where id= "+id;
        return getResultSetData(selectRecord(sql));
    }

    public int add(Hashtable hash) throws Exception
    {
        String name  = ds.toString((String)hash.get("name"));
        String id1	= ds.toString((String)hash.get("id1"));
        String href = ds.toString((String)hash.get("href"));
        String rect	= ds.toString((String)hash.get("rect"));
        String isuse	= ds.toString((String)hash.get("isuse"));

        Vector vect =new Vector();
        vect.add("menu2");
        vect.add(addVector("name",name,"CHAR"));
        vect.add(addVector("id1",id1,"NUM"));
        vect.add(addVector("href",href,"CHAR"));
        vect.add(addVector("rect",rect,"CHAR"));
        vect.add(addVector("isuse",isuse,"CHAR"));

        String sql = "select * from menu2 where name='"+name+"'";
        if(getResultSetData(selectRecord(sql)).size()!=0)
        {
            return 1;
        }
        return insertRecord(vect);
    }

    public int mod(Hashtable hash) throws Exception
    {
        String name  = ds.toString((String)hash.get("name"));
        String id1	= ds.toString((String)hash.get("id1"));
        String href = ds.toString((String)hash.get("href"));
        String rect	= ds.toString((String)hash.get("rect"));
        String isuse	= ds.toString((String)hash.get("isuse"));

        Vector vect =new Vector();
        vect.add("menu2");
        vect.add(addVector("name",name,"CHAR"));
        vect.add(addVector("id1",id1,"NUM"));
        vect.add(addVector("href",href,"CHAR"));
        vect.add(addVector("rect",rect,"CHAR"));
        vect.add(addVector("isuse",isuse,"CHAR"));
        vect.add("ID="+id+"");

        String sql = "select * from menu2 where id!="+id+" and name='"+name+"'";
        if(getResultSetData(selectRecord(sql)).size()!=0)
        {
            return 1;
        }
        return updateRecord(vect);
    }

    public void del()
    {
        deleteRecord("delete from menu2 where id="+id);
    }

    public static void main(String args[]) throws Exception
    {
        menu2Bean mb = new menu2Bean();

        //连接数据库
        //mb.createConn("org.gjt.mm.mysql.Driver","jdbc:mysql://localhost:3306/yj","admin","admin");
        mb.createConn(7,"localhost","3306","","hpj","admin","admin");

        Hashtable hash= new Hashtable();
        mb.setID("2");
        hash.put("user", "admin");
        hash.put("pwd", "pwd");
        hash.put("info", "test");
        //System.out.println(mb.add(hash));



        //取得当前也的数据
        System.out.println(mb.getCurPage(1,100));

        //取得记录数
        //System.out.println(mb.getResultSetData(mb.selectRecord("select count(*) from snals")));

        //释放连接
        System.out.println("\r\n\r\nend");
    }
};
