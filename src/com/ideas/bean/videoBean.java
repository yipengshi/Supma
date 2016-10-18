package com.ideas.bean;


import java.lang.*;
import java.util.*;
import java.sql.*;
import java.io.*;

public class videoBean extends ParentBean
{
    String id = "-1";

    public void setID(String id)
    {
        this.id = id;
    }

    public Vector getCurPage(int cur,int records)
    {
        return getOnePage("SELECT * FROM video order by addtime desc",cur,records);
    }

    //取得数据表中的某一条记录
    public Vector getOneData() throws Exception
    {
        String sql = " select * from video where id= "+id;
        return getResultSetData(selectRecord(sql));
    }

    public Vector getData() throws Exception
    {
        String sql = " SELECT * FROM video where isuse='1' order by addtime desc";
        return getResultSetData(selectRecord(sql));
    }


    public int add(Hashtable hash) throws Exception
    {
        String title  = ds.toString((String)hash.get("title"));
        String file	= ds.toString((String)hash.get("file"));
        String addtime = ds.toString((String)hash.get("addtime"));
        String isuse	= ds.toString((String)hash.get("isuse"));

        Vector vect =new Vector();
        vect.add("video");
        vect.add(addVector("title",title,"CHAR"));
        vect.add(addVector("file",file,"CHAR"));
        vect.add(addVector("addtime",addtime,"CHAR"));
        vect.add(addVector("isuse",isuse,"CHAR"));

        return insertRecord(vect);
    }

    public int mod(Hashtable hash,String saveDirectory) throws Exception
    {
        String title  = ds.toString((String)hash.get("title"));
        String file	= ds.toString((String)hash.get("file"));
        String addtime = ds.toString((String)hash.get("addtime"));
        String isuse	= ds.toString((String)hash.get("isuse"));

        //删除旧的文件
        String file_old = toName("video","id","file",id);
        if(!file_old.equals(file)&&!file.equals(""))
        {
            File f = new File(saveDirectory+file_old);
            f.delete();
        }
        if(file.equals(""))file = file_old;


        Vector vect =new Vector();
        vect.add("video");
        vect.add(addVector("title",title,"CHAR"));
        vect.add(addVector("file",file,"CHAR"));
        vect.add(addVector("addtime",addtime,"CHAR"));
        vect.add(addVector("isuse",isuse,"CHAR"));
        vect.add("ID="+id+"");

        return updateRecord(vect);
    }

    public void del(String saveDirectory)
    {
        //删除旧的文件
        String file_old = toName("video","id","file",id);
        if(!file_old.equals(""))
        {
            File f = new File(saveDirectory+file_old);
            f.delete();
        }

        deleteRecord("delete from video where id="+id);
    }

    public Vector getFileList(String vid)
    {
        String sql = "select distinct file from video order by addtime";
        if(!id.equals(""))sql = "select distinct file from video where id!="+vid+" order by addtime";
        return getResultSetData(selectRecord(sql));
    }

    public static void main(String args[]) throws Exception
    {
        videoBean mb = new videoBean();

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
