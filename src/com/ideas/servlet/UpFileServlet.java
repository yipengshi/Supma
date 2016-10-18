package com.ideas.servlet;

import java.io.*;
import java.io.File;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import com.jspsmart.upload.*;
import com.ideas.bean.*;

public class UpFileServlet extends HttpServlet
{
	private ServletConfig config;

	final public void init(ServletConfig config) throws ServletException
	{
		this.config = config;
	}

	final public ServletConfig getServletConfig()
	{
		return config;
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		doPost(request,response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		
    }

	public void  destroy ()
	{
	}
}
