package com.ideas.bean;


import java.lang.*;
import java.util.*;
import java.sql.*;

public class mainBean extends ParentBean
{
    String id = "-1";

    public void setID(String id)
    {
        this.id = id;
    }

    public String login(String loginimg)
    {
        String sql = "select href from menu1 where loginimg='"+loginimg+"' and isuse='1'";
        String href = "";
        Vector v = getDataBySql(sql);
        if(v.size()==1)
        {
            href = (String)((Hashtable)v.get(0)).get("href");
        }
        return href;
    }

    public Vector getMenu1List()
    {
        String sql = "select id,rect from menu1 where isuse='1' order by id";
        return getDataBySql(sql);
    }

    public Vector getMenu2List(String menu1id)
    {
        String sql = "select id,name from menu2 where isuse='1' and id1="+menu1id+" order by id";
        return getDataBySql(sql);
    }


    public Vector getMenu3List(String menu2id)
    {
        String sql = "select id,name from menu3 where isuse='1' and id2="+menu2id+" order by id";
        return getDataBySql(sql);
    }

    public static void main(String args[]) throws Exception
    {
        mainBean mb = new mainBean();

        //连接数据库
        //mb.createConn("org.gjt.mm.mysql.Driver","jdbc:mysql://localhost:3306/yj","admin","admin");
        mb.createConn(7,"localhost","3306","","hpj","admin","admin");

        //System.out.println(mb.add(hash));



        //取得当前也的数据
        System.out.println(mb.login("body_roll_01.gif"));

        //取得记录数
        //System.out.println(mb.getResultSetData(mb.selectRecord("select count(*) from snals")));

        //释放连接
        System.out.println("\r\n\r\nend");
    }
};
