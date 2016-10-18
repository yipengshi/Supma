package com.ideas.util;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
 
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.util.*;


public class ConnectionFactory {
	
	private static DataSource dsCache = null;
	//private 	static ResourceBundle bund =null;
	//private static String DataSourceFileName= "dsname";
	private static String DataSourceName= "jdbc/wuyeDataSource";
	private static  ConnectionFactory instance;// 唯一数据库管理实例类
	
	/**
	 * 得到唯一实例管理类
	 * 
	 * @return
	 */
	public static synchronized  ConnectionFactory getInstance() {
		if (instance == null) {
			System.out.println("eeeeeeeeeeexxeeeeeexxxx");
			instance = new ConnectionFactory();
		}
		return instance;
	}
	
    public Connection  getJNDIConnectio(){
    	Connection conn = null;
        if(dsCache == null){  
            synchronized(this){  
                try{
                    Context ct = new InitialContext();
                    dsCache = (DataSource) ct.lookup(DataSourceName);   
                }catch(Exception e){
                	 e.printStackTrace();
                }
            }  
        } 
       
        try{
        	 conn = dsCache.getConnection(); 
        }catch(Exception e){
        	 e.printStackTrace();
        }
        return conn;
    }  


//    public Connection JNDIgetConnection() {
//        Connection conn = null;
//        Context ic;
//        try {
//            ic = new InitialContext();
//            Context envContext= (Context)  ic.lookup("java:comp/env");///jdbc/wuyeDataSource
//            DataSource ds = (DataSource) ic.lookup("jdbc/wuyeDataSource");
//            conn = ds.getConnection();
//        } catch (NamingException e) {
//            e.printStackTrace();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return conn;
//    }
    
	public static void main(String[] args) {
		ConnectionFactory jd = new ConnectionFactory();
		Connection conn =jd. getJNDIConnectio();
		  //使用 
		   java.sql.PreparedStatement pre = null;
		   try{
			   pre = conn.prepareStatement("select * from test");
			   ResultSet resultset = pre.executeQuery();
			   if (resultset.next()){
				   System.out.println(resultset.getString("EMPL_NO"));
			   }
		   }catch ( java.sql.SQLException e){
			   System.out.println(e);
		   }finally{
			   try{
				   conn.close();
			   }catch(Exception ex){
				   System.out.println(ex);
			   }
			   
		   }
	}
}