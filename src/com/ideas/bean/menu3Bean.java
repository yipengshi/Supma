package com.ideas.bean;


import java.lang.*;
import java.util.*;
import java.sql.*;

public class menu3Bean extends ParentBean
{
    String id = "-1";

    public void setID(String id)
    {
        this.id = id;
    }

    public Vector getCurPage(int cur,int records)
    {
        return getOnePage("SELECT menu3.*,menu1.name as menu1name,menu2.name as menu2name FROM menu3,menu2,menu1 where menu3.id2=menu2.id and menu2.id1=menu1.id order by id1,id2,menu3.id",cur,records);
    }

    //取得数据表中的某一条记录
    public Vector getOneData() throws Exception
    {
        String sql = " select * from menu3 where id= "+id;
        return getResultSetData(selectRecord(sql));
    }

    public int add(Hashtable hash) throws Exception
    {
        String name  = ds.toString((String)hash.get("name"));
        String id2	= ds.toString((String)hash.get("id2"));
        String href = ds.toString((String)hash.get("href"));
        String isuse	= ds.toString((String)hash.get("isuse"));

        Vector vect =new Vector();
        vect.add("menu3");
        vect.add(addVector("name",name,"CHAR"));
        vect.add(addVector("id2",id2,"NUM"));
        vect.add(addVector("href",href,"CHAR"));
        vect.add(addVector("isuse",isuse,"CHAR"));

        String sql = "select * from menu3 where name='"+name+"'";
        if(getResultSetData(selectRecord(sql)).size()!=0)
        {
            return 1;
        }
        return insertRecord(vect);
    }

    public int mod(Hashtable hash) throws Exception
    {
        String name  = ds.toString((String)hash.get("name"));
        String id2	= ds.toString((String)hash.get("id2"));
        String href = ds.toString((String)hash.get("href"));
        String isuse	= ds.toString((String)hash.get("isuse"));

        Vector vect =new Vector();
        vect.add("menu3");
        vect.add(addVector("name",name,"CHAR"));
        vect.add(addVector("id2",id2,"NUM"));
        vect.add(addVector("href",href,"CHAR"));
        vect.add(addVector("isuse",isuse,"CHAR"));
        vect.add("ID="+id+"");

        String sql = "select * from menu3 where id!="+id+" and name='"+name+"'";
        if(getResultSetData(selectRecord(sql)).size()!=0)
        {
            return 1;
        }
        return updateRecord(vect);
    }

    public void del()
    {
        deleteRecord("delete from menu3 where id="+id);
    }

    public static void main(String args[]) throws Exception
    {
        menu3Bean mb = new menu3Bean();

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
