package com.ideas.servlet;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import com.ideas.util.Configuration;
import com.ideas.bean.userBean;
import java.util.*;

public class LoginServlet
    extends HttpServlet {

  public void doGet(HttpServletRequest req, HttpServletResponse res) throws
      IOException, ServletException {
    String name = req.getParameter("sUserName");
    String password = req.getParameter("sPassword");
	
    userBean myBean = new userBean();
    myBean.getMyConnPool();
    String id = myBean.login(name,password);

    String go = "";
    if (!id.equals("-1")) {
      HttpSession session = req.getSession(true);
      session.setAttribute("login", "true");
      session.setAttribute("user",name);



     go = "index.jsp";
    }
    else {
      go = "login.jsp";
    }

    myBean.releaseMyConnPool();
    res.sendRedirect(go);

  }

  public void doPost(HttpServletRequest req, HttpServletResponse res) throws
      IOException, ServletException {
    doGet(req, res);

  }
}