package supma.beans;


import java.io.Serializable;
import java.lang.*;
import java.util.*;
import java.sql.*;

import supma.db.BasicDatabase;

public class UserAreaBean  implements Serializable
{

	private static final long serialVersionUID = -6028579268762669969L;

	String tableName = "userarea" ;
    
    public static String user_area_level_1      ="1";        //level  
    public static String user_area_level_2     ="2";        //level  
    public static String user_area_level_3     ="3";        //level  
    
    public String userarea_id      ="";        //片区id    
    public String userarea_name     ="";        //片区名        
    public String userarea_level     ="";        //第几级别的片区        
    public String userarea_up_level  ="";        //上层片区级别        
    public String userarea_notuser1 ="";        //上层片区code
    public String userarea_notuser2    ="";        //空位2    
    public String userarea_notuser3   ="";        //空位3    
    public String userarea_notuser4     ="";        //空位4    
    public String userarea_notuser5 ="";     //空位5    
  
    public LinkedHashMap<String,UserAreaBean> list1 = null;//1级片区
    public LinkedHashMap<String,UserAreaBean> list2 = null;//2级片区
    public LinkedHashMap<String,UserAreaBean> list3 = null;//3级片区
    
    public ArrayList<UserAreaBean> arylist1 = null;//1级片区
    public ArrayList<UserAreaBean> arylist2 = null;//2级片区
    public ArrayList<UserAreaBean> arylist3 = null;//3级片区
    
  PreparedStatement pre = null;
    
 //把各个片区master放到collection里面
    public  void initUserareaBean(ResultSet rs) throws Exception{
        if(rs!=null  ){
        	list1=new LinkedHashMap<String,UserAreaBean>();
        	list2=new LinkedHashMap<String,UserAreaBean>();
        	list3=new LinkedHashMap<String,UserAreaBean>();
        	arylist1=new ArrayList<UserAreaBean>();
        	arylist2=new ArrayList<UserAreaBean>();
        	arylist3=new ArrayList<UserAreaBean>();
        	
        	while(rs.next()){
        		UserAreaBean goods = new UserAreaBean();
        		setBeanDate(goods,rs);
        		if(user_area_level_1.equals(goods.userarea_level)){//第一级别的类型放入
        			list1.put(goods.userarea_id, goods);
        			arylist1.add(goods);
        		}else if(user_area_level_2.equals(goods.userarea_level)){//第2级别的类型放入
        			list2.put(goods.userarea_id, goods);//code为key
        			arylist2.add(goods);
        		}else if(user_area_level_3.equals(goods.userarea_level)){//第3级别的类型放入
        			list3.put(goods.userarea_id, goods);//code为key
        			arylist3.add(goods);
        		}
        	}
        	 rs.close();
        }
    }
    
    //取得所有片区。按照片区号排序
    public  ResultSet getAlluserArea (BasicDatabase basic, userBean ufo) throws Exception{
    	String sql = "select * from userarea order by userarea_id";
        ResultSet rs= basic.executeSql("UserAreaBean", "select", sql, null, null, tableName, null,null,ufo);
        return rs;
    }
    
    public void setBeanDate(UserAreaBean bean,ResultSet rs) throws Exception{
    	bean.userarea_id              =rs.getString("userarea_id").trim();     
    	bean. userarea_name             =rs.getString("userarea_name").trim();            
    	bean.userarea_level             =rs.getString("userarea_level").trim();            
    	bean.userarea_up_level          =rs.getString("userarea_up_level").trim();         
    	bean.userarea_notuser1         =rs.getString("userarea_notuser1").trim();        
	 bean.userarea_notuser2            =rs.getString("userarea_notuser2").trim();           
	 bean.userarea_notuser3           =rs.getString("userarea_notuser3").trim();          
	 bean.userarea_notuser4             =rs.getString("userarea_notuser4").trim();            
	 bean.userarea_notuser5             =rs.getString("userarea_notuser5").trim();       	
    }
///*
//        Vector v =  getResultSetData(selectRecord(sql));
//        if(v.size()==0)
//        {
//            return "-1";
//        }
//        else
//        {
//            return (String)((Hashtable)v.get(0)).get("id");
//        }*/
 //   }
    
//
//    //取得数据表中的某一条记录
//    public Vector getOneData() throws Exception
//    {
//        String sql = " select * from backuser where id= "+id;
//        return getResultSetData(selectRecord(sql));
//    }
//
//    public int add(Hashtable hash) throws Exception
//    {
//        String user  = ds.toString((String)hash.get("user"));
//        String pwd = ds.toString((String)hash.get("pwd"));
//        String info	= ds.toString((String)hash.get("info"));
//
//        Vector vect =new Vector();
//        vect.add("backuser");
//        vect.add(addVector("user",user,"CHAR"));
//        vect.add(addVector("pwd",pwd,"CHAR"));
//        vect.add(addVector("info",info,"CHAR"));
//
//        String sql = "select * from backuser where user='"+user+"'";
//        if(getResultSetData(selectRecord(sql)).size()!=0)
//        {
//            return 1;
//        }
//        return insertRecord(vect);
//    }
//
//    public int mod(Hashtable hash) throws Exception
//    {
//        String user  = ds.toString((String)hash.get("user"));
//        String pwd = ds.toString((String)hash.get("pwd"));
//        String info	= ds.toString((String)hash.get("info"));
//        String islock	= ds.toString((String)hash.get("islock"));
//
//        Vector vect =new Vector();
//        vect.add("backuser");
//        vect.add(addVector("user",user,"CHAR"));
//        vect.add(addVector("pwd",pwd,"CHAR"));
//        vect.add(addVector("info",info,"CHAR"));
//        vect.add(addVector("islock",islock,"CHAR"));
//        vect.add("ID="+id+"");
//        String sql = "select * from backuser where id!="+id+" and user='"+user+"'";
//        if(getResultSetData(selectRecord(sql)).size()!=0)
//        {
//            return 1;
//        }
//        return updateRecord(vect);
//    }
//
//    public void del()
//    {
//        deleteRecord("delete from backuser where id="+id);
//    }
//
//    public static void main(String args[]) throws Exception
//    {
//        userBean mb = new userBean();
//
//        //连接数据库
//        //mb.createConn("org.gjt.mm.mysql.Driver","jdbc:mysql://localhost:3306/yj","admin","admin");
//        mb.createConn(7,"localhost","3306","","hpj","admin","admin");
//
//        Hashtable hash= new Hashtable();
//        mb.setID("2");
//        hash.put("backuser", "admin");
//        hash.put("pwd", "pwd");
//        hash.put("info", "test");
//        //System.out.println(mb.add(hash));
//
//
//
//        //取得当前也的数据
//        System.out.println(mb.getCurPage(1,100));
//        //System.out.println(mb.login("admin","e"));
//
//        //取得记录数
//        //System.out.println(mb.getResultSetData(mb.selectRecord("select count(*) from snals")));
//
//        //释放连接
//        System.out.println("\r\n\r\nend");
//    }
}
