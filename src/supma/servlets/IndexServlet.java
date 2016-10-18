package supma.servlets;

import javax.servlet.*;
import javax.servlet.http.*;

import java.io.*;
import java.net.InetAddress;
import java.net.UnknownHostException;

import supma.db.BasicDatabase;
import supma.util.BaseGetIP;

import java.util.*;
import supma.beans.*;

import java.sql.*;

public class IndexServlet
    extends HttpServlet {
	

  /**
	 * 
	 */
	private static final long serialVersionUID = -271159610876745814L;

public void doGet(HttpServletRequest req, HttpServletResponse res) throws
      IOException, ServletException{
	  
    String name = req.getParameter("loginname");
    String password = req.getParameter("loginpwd");
    HttpSession session = req.getSession(true);
    res.setContentType("text/xml; charset=utf-8");
    PrintWriter out = res.getWriter();
    
    String loginIP = BaseGetIP.getIpAddr(req);
    userBean ufo = new userBean();//现在使用用户的信息;
	ufo = new userBean();
	ufo.user_id=name;
	ufo.user_notuser11=loginIP;
	
    BasicDatabase bs = new BasicDatabase();
    userBean userbean =new userBean();
    try{
    	ResultSet rs = bs.executeSql("IndexServlet","select",userbean.getLoginUserSql(),new String[]{name},null,null,null,null,ufo);
    	if(rs!=null && rs.next()){
    		userbean.initUserBean(bs.executeSql("IndexServlet","select",userbean.getLoginSql(),new String[]{name,password},null,null,null,null,ufo));
    	      if("1".equals(userbean.isLogin)){
    	    	  //密码正确
    	    	  session.setAttribute("loginUserInfo", userbean);
    	    	  out.write("1");
    	      }else{
    	    	  //密码不正确
    	    	  session.setAttribute("loginUserInfo",null);
    	    	  out.write("-2");
    	      }
    	      out.close();
    	}else{
    		//用户名不存在
	    	  session.setAttribute("loginUserInfo",null);
	    	  out.write("-1");
    	}
    	out.flush();
    	
    	System.out.println(userbean.user_pwd+"***");
    }catch(Exception e){
    	e.printStackTrace();
    }finally{
    	try{
    		if(bs.pstm !=null){
    			bs.pstm.close();
    			bs.pstm=null;
    		}
    		if(bs.conn != null){
    			bs.conn.close();
    			bs.conn=null;
    		}
    		out.close();
    	}catch(Exception e){
    		e.printStackTrace();
    	}
    }

     // go = "login.jsp";
   // res.sendRedirect(go);

  }

  public void doPost(HttpServletRequest req, HttpServletResponse res) throws
      IOException, ServletException {
    doGet(req, res);
    
    res.setHeader("Cache-Control", "no-cache");
    res.setHeader("Pragma", "no-cache");
    res.setContentType("text/xml; charset=utf-8");
    
    String name = req.getParameter("loginname");
    String password = req.getParameter("loginpwd");
    HttpSession session = req.getSession(true);
    PrintWriter out = res.getWriter();
    
    String loginIP = BaseGetIP.getIpAddr(req);
    userBean ufo = new userBean();//现在使用用户的信息;
	ufo = new userBean();
	ufo.user_id=name;
	ufo.user_notuser11=loginIP;
	
    BasicDatabase bs = new BasicDatabase();
    userBean userbean =new userBean();
    try{
    	ResultSet rs = bs.executeSql("IndexServlet","select",userbean.getLoginUserSql(),new String[]{name},null,null,null,null,ufo);
    	if(rs!=null && rs.next()){
    		userbean.initUserBean(bs.executeSql("IndexServlet","select",userbean.getLoginSql(),new String[]{name,password},null,null,null,null,ufo));
    	      if("1".equals(userbean.isLogin)){
    	    	  session.setAttribute("loginUserInfo", userbean);
    	    	  out.write("1");
    	      }else{
    	    	  session.setAttribute("loginUserInfo",null);
    	    	  out.write("-2");
    	      }
    	}else{
	    	  session.setAttribute("loginUserInfo",null);
	    	  out.write("-1");
    	}
    	out.flush();

    	System.out.println(userbean.user_pwd+"***");
    }catch(Exception e){
    	e.printStackTrace();
    }finally{
    	try{
    		if(bs.pstm !=null){
    			bs.pstm.close();
    			bs.pstm=null;
    		}
    		if(bs.conn != null){
    			bs.conn.close();
    			bs.conn=null;
    		}
    		out.close();
    	}catch(Exception e){
    		e.printStackTrace();
    	}
    }
  }
  

}