package com.ideas.servlet;

import javax.servlet.*;
import javax.servlet.http.*;
import com.ideas.util.*;


public class InitServlet
    extends HttpServlet {

  public String m_path;

  public void init(ServletConfig conf) throws ServletException {
    super.init(conf);
    ServletContext servletContext = conf.getServletContext();

    String config = conf.getInitParameter("configURI");
    String path = servletContext.getRealPath(config);
	m_path = path;
    new ConfigReader(path);
    //
    
    
  }
}