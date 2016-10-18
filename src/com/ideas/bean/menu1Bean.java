package com.ideas.bean;


import java.lang.*;
import java.util.*;
import java.sql.*;

public class menu1Bean extends ParentBean
{
    String id = "-1";

    public void setID(String id)
    {
        this.id = id;
    }

    public Vector getCurPage(int cur,int records)
    {
        return getOnePage("SELECT * FROM menu1",cur,records);
    }

    //ȡ�����ݱ��е�ĳһ����¼
    public Vector getOneData() throws Exception
    {
        String sql = " select * from menu1 where id= "+id;
        return getResultSetData(selectRecord(sql));
    }

    public int add(Hashtable hash) throws Exception
    {
        String name  = ds.toString((String)hash.get("name"));
        String href = ds.toString((String)hash.get("href"));
        String loginimg	= ds.toString((String)hash.get("loginimg"));
        String rect	= ds.toString((String)hash.get("rect"));
        String menu2img	= ds.toString((String)hash.get("menu2img"));
        String isuse	= ds.toString((String)hash.get("isuse"));

        Vector vect =new Vector();
        vect.add("menu1");
        vect.add(addVector("name",name,"CHAR"));
        vect.add(addVector("href",href,"CHAR"));
        vect.add(addVector("loginimg",loginimg,"CHAR"));
        vect.add(addVector("rect",rect,"CHAR"));
        vect.add(addVector("menu2img",menu2img,"CHAR"));
        vect.add(addVector("isuse",isuse,"CHAR"));

        String sql = "select * from menu1 where name='"+name+"'";
        if(getResultSetData(selectRecord(sql)).size()!=0)
        {
            return 1;
        }
        return insertRecord(vect);
    }

    public int mod(Hashtable hash) throws Exception
    {
        String name  = ds.toString((String)hash.get("name"));
        String href = ds.toString((String)hash.get("href"));
        String loginimg	= ds.toString((String)hash.get("loginimg"));
        String rect	= ds.toString((String)hash.get("rect"));
        String menu2img	= ds.toString((String)hash.get("menu2img"));
        String isuse	= ds.toString((String)hash.get("isuse"));

        Vector vect =new Vector();
        vect.add("menu1");
        vect.add(addVector("name",name,"CHAR"));
        vect.add(addVector("href",href,"CHAR"));
        vect.add(addVector("loginimg",loginimg,"CHAR"));
        vect.add(addVector("rect",rect,"CHAR"));
        vect.add(addVector("menu2img",menu2img,"CHAR"));
        vect.add(addVector("isuse",isuse,"CHAR"));
        vect.add("ID="+id+"");

        String sql = "select * from menu1 where id!="+id+" and name='"+name+"'";
        if(getResultSetData(selectRecord(sql)).size()!=0)
        {
            return 1;
        }
        return updateRecord(vect);
    }

    public void del()
    {
        deleteRecord("delete from menu1 where id="+id);
    }

    public static void main(String args[]) throws Exception
    {
        menu1Bean mb = new menu1Bean();

        //�������ݿ�
        //mb.createConn("org.gjt.mm.mysql.Driver","jdbc:mysql://localhost:3306/yj","admin","admin");
        mb.createConn(7,"localhost","3306","","hpj","admin","admin");

        Hashtable hash= new Hashtable();
        mb.setID("2");
        hash.put("user", "admin");
        hash.put("pwd", "pwd");
        hash.put("info", "test");
        //System.out.println(mb.add(hash));



        //ȡ�õ�ǰҲ������
        System.out.println(mb.getCurPage(1,100));

        //ȡ�ü�¼��
        //System.out.println(mb.getResultSetData(mb.selectRecord("select count(*) from snals")));

        //�ͷ�����
        System.out.println("\r\n\r\nend");
    }
};
