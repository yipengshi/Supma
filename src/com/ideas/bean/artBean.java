package com.ideas.bean;


import java.io.File;
import java.lang.*;
import java.util.*;
import java.sql.*;

public class artBean extends ParentBean
{
    String id = "-1";

    public void setID(String id)
    {
        this.id = id;
    }

    public Vector getCurPage(int cur,int records)
    {
        return getOnePage("SELECT * FROM art order by id",cur,records);
    }

    //取得数据表中的某一条记录
    public Vector getOneData() throws Exception
    {
        String sql = " select * from art where id= "+id;
        return getResultSetData(selectRecord(sql));
    }

    public int add(Hashtable hash) throws Exception
    {
        String name  = ds.toString((String)hash.get("name"));
        String isuse = ds.toString((String)hash.get("isuse"));
        String menuid	= ds.toString((String)hash.get("menuid"));
        String menufloor = ds.toString((String)hash.get("menufloor"));
        String addtime	= ds.toString((String)hash.get("addtime"));
        String edittime	= ds.toString((String)hash.get("edittime"));
        String title	= ds.toString((String)hash.get("title"));
        String title1	= ds.toString((String)hash.get("title1"));
        String writer	= ds.toString((String)hash.get("writer"));
        String reporter	= ds.toString((String)hash.get("reporter"));
        String fromer	= ds.toString((String)hash.get("fromer"));
        String file	= ds.toString((String)hash.get("file"));
        String editer	= ds.toString((String)hash.get("editer"));
        String Content	= ds.toString((String)hash.get("Content"));
        String keywords	= ds.toString((String)hash.get("keywords"));
        Content = Content.replaceAll("&lt;","<").replaceAll("&gt;",">");

        //文件重名时返回
        String f = file.substring(file.lastIndexOf("/") + 1, file.length());
        String sql = "select * from art where file='"+f+"'";
        if(getResultSetData(selectRecord(sql)).size() != 0)
        {
            return 1;
        }
        else
        {
            DealFile df = new DealFile();
//            File files = new File("e:/temp/"+file.substring(file.lastIndexOf("/")));
//            if(!files.exists()){
//                System.out.println("is not exitsts");
//                files.createNewFile();
//            }
            df.connFOS(file);
            System.out.println("add file name:"+file+"*");
            df.writeCHStr(Content);
            df.closeFOS();

            Vector vect = new Vector();
            vect.add("art");
            vect.add(addVector("name", name, "CHAR"));
            vect.add(addVector("isuse", isuse, "CHAR"));
            vect.add(addVector("menuid", menuid, "NUM"));
            vect.add(addVector("menufloor", menufloor, "NUM"));
            vect.add(addVector("addtime", addtime, "CHAR"));
            vect.add(addVector("edittime", edittime, "CHAR"));
            vect.add(addVector("title", title, "CHAR"));
            vect.add(addVector("title1", title1, "CHAR"));
            vect.add(addVector("writer", writer, "CHAR"));
            vect.add(addVector("reporter", reporter, "CHAR"));
            vect.add(addVector("fromer", fromer, "CHAR"));
            vect.add(addVector("file", f, "CHAR"));
            vect.add(addVector("editer", editer, "CHAR"));
            vect.add(addVector("keywords", keywords, "CHAR"));
            return insertRecord(vect);
        }
    }

    public Vector getMenuList(String table)
    {
        String sql = "select id,name from "+table+" order by id";
        return getResultSetData(selectRecord(sql));
    }

    public int mod(Hashtable hash) throws Exception
    {
        String name  = ds.toString((String)hash.get("name"));
        String isuse = ds.toString((String)hash.get("isuse"));
        String menuid	= ds.toString((String)hash.get("menuid"));
        String menufloor = ds.toString((String)hash.get("menufloor"));
        String addtime	= ds.toString((String)hash.get("addtime"));
        String edittime	= ds.toString((String)hash.get("edittime"));
        String title	= ds.toString((String)hash.get("title"));
        String title1	= ds.toString((String)hash.get("title1"));
        String writer	= ds.toString((String)hash.get("writer"));
        String reporter	= ds.toString((String)hash.get("reporter"));
        String fromer	= ds.toString((String)hash.get("fromer"));
        String file	= ds.toString((String)hash.get("file"));
        String editer	= ds.toString((String)hash.get("editer"));
        String Content	= ds.toString((String)hash.get("Content"));
        String keywords	= ds.toString((String)hash.get("keywords"));
        Content = Content.replaceAll("&lt;","<").replaceAll("&gt;",">");

        //文件重名时返回
       String f = file.substring(file.lastIndexOf("/") + 1, file.length());
       String sql = "select * from art where id!="+id+" and file='"+f+"'";
       if(getResultSetData(selectRecord(sql)).size() != 0)
       {
           return 1;
       }
       else
       {

           DealFile df = new DealFile();
           df.connFOS(file);
           df.writeCHStr(Content);
           df.closeFOS();

           Vector vect = new Vector();
           vect.add("art");
           vect.add(addVector("name", name, "CHAR"));
           vect.add(addVector("isuse", isuse, "CHAR"));
           vect.add(addVector("menuid", menuid, "NUM"));
           vect.add(addVector("menufloor", menufloor, "NUM"));
           vect.add(addVector("addtime", addtime, "CHAR"));
           vect.add(addVector("edittime", edittime, "CHAR"));
           vect.add(addVector("title", title, "CHAR"));
           vect.add(addVector("title1", title1, "CHAR"));
           vect.add(addVector("writer", writer, "CHAR"));
           vect.add(addVector("reporter", reporter, "CHAR"));
           vect.add(addVector("fromer", fromer, "CHAR"));
           vect.add(addVector("file", f, "CHAR"));
           vect.add(addVector("editer", editer, "CHAR"));
           vect.add(addVector("keywords", keywords, "CHAR"));
           vect.add("ID=" + id + "");
           return updateRecord(vect);
       }
    }

    public void del()
    {
        deleteRecord("delete from art where id="+id);
    }

	public Vector search(String keywords)
	{
		String sql = "select * from art where keywords like '%"+keywords+"%'";
		return getDataBySql(sql);
	}
    public String getHTML(String file)
    {
        DealFile df = new DealFile();
        df.connFIS(file);
	String str = df.readCHStr();
        df.closeFIS();
        return str;
    }

    public Vector getFileList()
    {
        String sql = "select file,name from art order by id";
        return getResultSetData(selectRecord(sql));
    }

    public static void main(String args[]) throws Exception
    {
        artBean mb = new artBean();

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
        //System.out.println(mb.login("admin","e"));

        //取得记录数
        //System.out.println(mb.getResultSetData(mb.selectRecord("select count(*) from snals")));

        //释放连接
        System.out.println("\r\n\r\nend");
    }
};
