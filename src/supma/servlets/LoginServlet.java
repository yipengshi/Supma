package supma.servlets;

import javax.servlet.*;
import javax.servlet.http.*;

import java.io.*;
import java.net.InetAddress;
import java.net.UnknownHostException;

import supma.common.CommonKey;
import supma.db.BasicDatabase;
import supma.log.LogManager;
import supma.util.BaseGetIP;
import supma.util.BasicAESUtil;

import java.text.SimpleDateFormat;
import java.util.*;
import java.util.Date;

import supma.beans.*;
import java.sql.*;

public class LoginServlet
    extends HttpServlet {

  /**
	 * 
	 */
	private static final long serialVersionUID = 5236622420063167196L;

public void doGet(HttpServletRequest req, HttpServletResponse res) throws
      IOException, ServletException{
	 res.setContentType("text/xml; charset=UTF-8");
	 req.setCharacterEncoding("UTF-8");
	LogManager lm = LogManager.getInstance(this.getServletContext().getRealPath(CommonKey.logDir));
    String name = req.getParameter("loginname");
	System.out.println("name:"+name);
	
//	byte[] by = BasicAESUtil.encrypt(name, "abcd1234");
//	System.out.println("���ܺ�"+new String(by));
//	byte[] dy = BasicAESUtil.decrypt(by,"abcd1234");   
//	System.out.println("���ܺ�"+new String(dy,"utf-8"));
	
    String password = req.getParameter("loginpwd");
    String loginIP = BaseGetIP.getIpAddr(req);
    HttpSession session = req.getSession(true);
    
    userBean ufo = new userBean();//����ʹ���û�����Ϣ;
    	ufo = new userBean();
    	ufo.user_id=name;
    	ufo.user_notuser11=loginIP;
    	 
	String logout =req.getParameter("exituser");//�˳���¼
	if(logout!=null){
		if(session.getAttribute("loginUserInfo")!=null){
			   lm.println("I", "LoginServlet", "doGet", "�˳���¼:"+((userBean)session.getAttribute("loginUserInfo")).user_id, ufo);
		}
		session.setAttribute("loginUserInfo",null);
		session.invalidate();
		res.sendRedirect("login.aspx");
		return;
	}
	
    PrintWriter out = res.getWriter();
    
    BasicDatabase bs = new BasicDatabase();
    userBean userbean =new userBean();
    try{
    	ResultSet rs = bs.executeSql("LoginServlet","select",userbean.getLoginUserSql(),new String[]{name},null,null,null,null,ufo);
    	if(rs!=null && rs.next()){
    		userbean.initUserBean(bs.executeSql("LoginServlet","select",userbean.getLoginSql(),new String[]{name,password},null,null,null,null,ufo));
    		System.out.println("userbean.isLogin:"+userbean.isLogin);
    	      if("1".equals(userbean.isLogin)){
    	    	  //������ȷ,�ɹ���¼
    	    	  session.setAttribute("loginUserInfo", userbean);
    	    	  //�ѱ��ε�¼ʱ�䣬��¼ip������д�����ݿ�
    				Date now = new Date(); 
    				SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    				String s = dateFormat.format(now);
    	    	  String [] column= new String [] {"user_last_login_date","user_notuser11"};
    	    	  String [] value = new String [] {s,loginIP};
    	    	  String [] whercondcolumn =new String [] {"user_id"};
    	    	  String [] whercondvalue =new String [] {name};
    	    	  bs.executeSql("LoginServlet","update",null,column,value,"usertab",whercondcolumn,whercondvalue,ufo);
    	    	  if(CommonKey.user_type1.equals(userbean.user_type)){
        	    	  out.write("1");//���е�¼
    	    	  }else if(CommonKey.user_type2.equals(userbean.user_type)){
    	    		  out.write("11");//������
    	    	  }else if(CommonKey.user_type3.equals(userbean.user_type)){
    	    		  out.write("111");//����վ
    	    	  }

  	    	    	lm.println("I", "LoginServlet", "doGet", "login�ɹ�use:"+name, ufo);
    	      }else if("2".equals(userbean.isLogin)){
    	    	  //�û�������
    	    	  session.setAttribute("loginUserInfo",null);
    	    	  out.write("-3");
    	    	  lm.println("I", "LoginServlet", "doGet", "loginʧ��(�û�������)use:"+name, ufo);
    	      }else if("3".equals(userbean.isLogin)){
    	    	  //�û�������,��ע������
    	    	  session.setAttribute("loginUserInfo",null);
    	    	  out.write("-5");
    	    	  lm.println("I", "LoginServlet", "doGet", "loginʧ��(�û�������,��ע������)use:"+name, ufo);
    	      }else{
    	    	  //���벻��ȷ
    	    	  session.setAttribute("loginUserInfo",null);
    	    	  out.write("-2");
    	    	  lm.println("I", "LoginServlet", "doGet", "loginʧ��(�������)use:"+name, ufo);
    	      }
    	      out.close();
    	}else{
    		//�û���������
	    	  session.setAttribute("loginUserInfo",null);
	    	  out.write("-1");
	    	  lm.println("I", "LoginServlet", "doGet", "loginʧ��(�û���������)use:"+name,ufo);
    	}
    	out.flush();
    	
    	 
    }catch(Exception e){
    	out.write("-4");
    	lm.println("E", "LoginServlet", "doGet", "��������:"+e,ufo);
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
        	out.write("-4");
        	lm.println("E", "LoginServlet", "doGet", "��������:"+e,ufo);
    		e.printStackTrace();
    	}
    }

     // go = "login.jsp";
   // res.sendRedirect(go);

  }

  public void doPost(HttpServletRequest req, HttpServletResponse res) throws
      IOException, ServletException {
	  
	  
    doGet(req, res);

  }

}