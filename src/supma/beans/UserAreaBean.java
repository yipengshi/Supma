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
    
    public String userarea_id      ="";        //Ƭ��id    
    public String userarea_name     ="";        //Ƭ����        
    public String userarea_level     ="";        //�ڼ������Ƭ��        
    public String userarea_up_level  ="";        //�ϲ�Ƭ������        
    public String userarea_notuser1 ="";        //�ϲ�Ƭ��code
    public String userarea_notuser2    ="";        //��λ2    
    public String userarea_notuser3   ="";        //��λ3    
    public String userarea_notuser4     ="";        //��λ4    
    public String userarea_notuser5 ="";     //��λ5    
  
    public LinkedHashMap<String,UserAreaBean> list1 = null;//1��Ƭ��
    public LinkedHashMap<String,UserAreaBean> list2 = null;//2��Ƭ��
    public LinkedHashMap<String,UserAreaBean> list3 = null;//3��Ƭ��
    
    public ArrayList<UserAreaBean> arylist1 = null;//1��Ƭ��
    public ArrayList<UserAreaBean> arylist2 = null;//2��Ƭ��
    public ArrayList<UserAreaBean> arylist3 = null;//3��Ƭ��
    
  PreparedStatement pre = null;
    
 //�Ѹ���Ƭ��master�ŵ�collection����
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
        		if(user_area_level_1.equals(goods.userarea_level)){//��һ��������ͷ���
        			list1.put(goods.userarea_id, goods);
        			arylist1.add(goods);
        		}else if(user_area_level_2.equals(goods.userarea_level)){//��2��������ͷ���
        			list2.put(goods.userarea_id, goods);//codeΪkey
        			arylist2.add(goods);
        		}else if(user_area_level_3.equals(goods.userarea_level)){//��3��������ͷ���
        			list3.put(goods.userarea_id, goods);//codeΪkey
        			arylist3.add(goods);
        		}
        	}
        	 rs.close();
        }
    }
    
    //ȡ������Ƭ��������Ƭ��������
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
//    //ȡ�����ݱ��е�ĳһ����¼
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
//        //�������ݿ�
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
//        //ȡ�õ�ǰҲ������
//        System.out.println(mb.getCurPage(1,100));
//        //System.out.println(mb.login("admin","e"));
//
//        //ȡ�ü�¼��
//        //System.out.println(mb.getResultSetData(mb.selectRecord("select count(*) from snals")));
//
//        //�ͷ�����
//        System.out.println("\r\n\r\nend");
//    }
}
