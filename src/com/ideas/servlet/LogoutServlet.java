package com.ideas.servlet;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import com.ideas.util.Configuration;

public class LogoutServlet
    extends HttpServlet {
  public void doGet(HttpServletRequest req, HttpServletResponse res) throws
      IOException, ServletException {
    HttpSession session = req.getSession(true);
    session.setAttribute("login", null);
    res.sendRedirect("login.jsp");
  }

  public void doPost(HttpServletRequest req, HttpServletResponse res) throws
      IOException, ServletException {
    doGet(req, res);

  }
}
