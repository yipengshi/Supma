package supma.servlets;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.sql.ResultSet;
import java.util.HashMap;
import java.io.*;

import javax.servlet.*;
import javax.servlet.http.*;

import supma.log.LogManager;
import supma.util.StringUtil;
import supma.beans.ErrorMessageBean;
import supma.beans.userBean;
import supma.common.*;
import supma.db.BasicDatabase;

public class InitServlet extends HttpServlet {

	

	/**
	 * 
	 */
	private static final long serialVersionUID = 4529953014256203291L;


	public void init(ServletConfig conf) throws ServletException {
		super.init(conf);
		ServletContext servletContext = conf.getServletContext();

		String config = conf.getInitParameter("configURI");
		String path = servletContext.getRealPath(config);
		String m_path = path;
		System.out.println("m_path:" + m_path);
		//CREATE DIR IF NOT EXIST
		File file = new File(this.getServletConfig().getServletContext().getRealPath(CommonKey.uploadDir_chaoshizhizhao_tmp));
		File file2 = new File(this.getServletConfig().getServletContext().getRealPath(CommonKey.uploadDir_chaoshizhizhao));
		//如果目录不存在
		if(!file.exists()){
		 //创建目录
		 file.mkdirs();
		}
		if(!file2.exists()){
			 //创建目录
			 file2.mkdirs();
			}
		// log init
		String turepath = servletContext.getRealPath(CommonKey.logDir);
		System.out.println("turepath:" + turepath);
		LogManager lm = LogManager.getInstance(m_path);
		lm.println("I", "InitServlet", "init", "init成功。log文件创建", null);
		//删除/upload/temp下所有临时文件
		String dir = this.getServletConfig().getServletContext().getRealPath(CommonKey.uploadDir_chaoshizhizhao_tmp);
		deleteFile(new File(dir));
		
		//取得错误信息-> from errormessage table
	    BasicDatabase basic = new BasicDatabase();
	    HashMap<String,ErrorMessageBean> errorMap = new HashMap<String,ErrorMessageBean>();
	    ResultSet rs =null;
	    try{
	     	rs = basic.executeSql("InitServlet","select","select * from "+ErrorMessageBean.tableName+" order by error_id ",null,null,null,null,null,null);
	     	while(rs.next()){
	     		ErrorMessageBean error = new ErrorMessageBean();
	     		error. error_id=rs.getString("error_id");
	     		error. error_type =rs.getString("error_type");
	     		error. error_summery =rs.getString("error_summery");
	     		error. error_detail =rs.getString("error_detail");
	     		error. error_notuser1 =rs.getString("error_notuser1");
	     		error. error_notuser2 =rs.getString("error_notuser2");
	     		errorMap.put(error.error_id, error);
	     	}
     		servletContext.setAttribute(CommonKey.errorMessageKey, errorMap);
     		lm.println("I", "InitServlet", "init", "errorMessage取得成功", null);
	    }catch(Exception e){
	    	lm.println("I", "InitServlet", "init", "errorMessage取得失败:"+e.toString(), null);
	    }finally{
	    	try{
				if(basic.pstm !=null){
					basic.pstm.close();
					basic.pstm=null;
				}
				if(basic.conn != null){
					basic.conn.close();
					basic.conn=null;
				}
				if(rs!=null){
					rs.close();
				}
	    	}catch(Exception e1){
	    		lm.println("I", "InitServlet", "init", "errorMessage取得失败:"+e1.toString(), null);
	    	}

	    }
	}

	
    /**
     * 删除文件夹下的所有文件
     * @param oldPath
     */
    public void deleteFile(File oldPath) {
          if (oldPath.isDirectory()) {
           File[] files = oldPath.listFiles();
           for (File file : files) {
             deleteFile(file);
           }
          }else{
            oldPath.delete();
          }
        }

}