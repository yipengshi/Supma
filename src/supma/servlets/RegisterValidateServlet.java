package supma.servlets;

import javax.imageio.ImageIO;
import javax.servlet.*;
import javax.servlet.http.*;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import java.awt.image.BufferedImage;
import java.io.*;
import java.net.InetAddress;
import java.net.URLDecoder;
import java.net.UnknownHostException;

import supma.common.CommonKey;
import supma.db.BasicDatabase;
import supma.log.LogManager;
import supma.util.BaseGetIP;
import supma.util.StringUtil;

import java.text.SimpleDateFormat;
import java.util.*;
import java.util.Date;

import supma.beans.*;

import java.sql.*;

public class RegisterValidateServlet
    extends HttpServlet {

	private static final long serialVersionUID = -7997146869320701826L;

public void doGet(HttpServletRequest req, HttpServletResponse res) throws
      IOException, ServletException{

  	req.setCharacterEncoding("utf-8"); // 设置编码  
  	res.setCharacterEncoding("utf-8");  
  	res.setContentType("text/xml; charset=UTF-8");
	LogManager lm = LogManager.getInstance(this.getServletContext().getRealPath(CommonKey.logDir));
    String loginIP = BaseGetIP.getIpAddr(req);
    HttpSession session = req.getSession(true);
    PrintWriter out = res.getWriter();
    
	String uid = URLDecoder.decode(StringUtil.nulltoblank(req.getParameter("uid")), "UTF-8").trim();//uid
	String mail = req.getParameter("mail");//mail
	String mobo = req.getParameter("mobo");//mobo
	String authcode = req.getParameter("authcode");//authcode	

	
	userBean ufo = new userBean();//现在使用用户的信息;
   	ufo = new userBean();
   	ufo.user_notuser11=loginIP;

    String action = req.getParameter("action");//验证用户名存在性
    if("CheckUnicknme".equals(action)){
    	//String uid = req.getParameter("uid");
    	ufo.user_id=uid;
    	String reurnJosn = getJson(ufo.getLoginUserSql(),new String[]{uid},ufo,lm);
    	 out.write(reurnJosn);
    	 return;
    }else if ("CheckUnickmail".equals(action)){//验证mail存在性
    	ufo.user_id="";
    	String reurnJosn = getJson(ufo.getLoginMailSql(),new String[]{mail},ufo,lm);
    	out.write(reurnJosn);
    	return;
    }else if ("CheckUnickmobo".equals(action)){
    	ufo.user_id="";
    	String reurnJosn = getJson(ufo.getLoginMoboSql(),new String[]{mobo},ufo,lm);
    	out.write(reurnJosn);
    	return;
    }else if ("CheckViewFile".equals(action)){

    }else if("AuditCode".equals(action)){
    	String validateinsession = (String) session.getAttribute("validateCode");
    	String data="";
    	if(validateinsession!=null && !"".equals(validateinsession) ){
        	if(authcode!=null&&!"".equals(authcode)){
        		if( validateinsession.toLowerCase().equals(authcode.toLowerCase())){
        			data = "{\"success\":0}";//验证码正确
        		}else{
        			data = "{\"success\":1}";//验证码不正确
        		}
        	}else{
        		data="{\"success\":2}";//验证码为空
        	}
    	}else{
    		data="{\"success\":2}";//验证码为空
    	}
    	out.write(data);
    	return;
    }
    
  }

  public void doPost(HttpServletRequest req, HttpServletResponse res) throws
      IOException, ServletException {
	  
      	req.setCharacterEncoding("utf-8"); // 设置编码  
      	res.setCharacterEncoding("utf-8");  
      	res.setContentType("text/html;charset=UTF-8");  
      	
		LogManager lm = LogManager.getInstance(this.getServletContext().getRealPath(CommonKey.logDir));
  		userBean ufo = new userBean();//现在使用用户的信息;
  	   	ufo = new userBean();
  	    String loginIP = BaseGetIP.getIpAddr(req);
  	    HttpSession session = req.getSession(true);
  	   	ufo.user_notuser11=loginIP;
    	ufo.user_id="";
    	
    	PrintWriter out = res.getWriter();
    	
		//获得磁盘文件条目工厂
		DiskFileItemFactory factory = new DiskFileItemFactory();
		//获取文件需要上传到的路径
		String path = this.getServletConfig().getServletContext().getRealPath(CommonKey.uploadDir_chaoshizhizhao_tmp);
		//如果没以下两行设置的话，上传大的 文件 会占用 很多内存，
		//设置暂时存放的 存储室 , 这个存储室，可以和 最终存储文件 的目录不同
		/**
		 * 原理 它是先存到 暂时存储室，然后在真正写到 对应目录的硬盘上， 
		 * 按理来说 当上传一个文件时，其实是上传了两份，第一个是以 .tem 格式的 
		 * 然后再将其真正写到 对应目录的硬盘上
		 */
		factory.setRepository(new File(path));
		//设置 缓存的大小，当上传文件的容量超过该缓存时，直接放到 暂时存储室
		factory.setSizeThreshold(1024*1024*5) ;//5M
		//高水平的API文件上传处理
		ServletFileUpload upload = new ServletFileUpload(factory);
		try {
			//可以上传多个文件
			List<FileItem> list = (List<FileItem>)upload.parseRequest(req);
			//文件个数
			int file_count=0;
			//哪个页面哪个upload控件
			String uploader = "";
			//一个文件的时候返回值
			boolean bol1 = false;
			//超过文件大小限制fla
			boolean bol2 =false;
			
			for(int i=0;i<list.size();i++){
				//获取表单的属性名字
				String name = list.get(i).getFieldName();
				//如果获取的 表单信息是普通的 文本 信息
				if(list.get(i).isFormField()){					
					//获取用户具体输入的字符串 ，名字起得挺好，因为表单提交过来的是 字符串类型的
					String value = list.get(i).getString() ;
					if("action".equals(name) && "CheckViewFile".equals(value)){//注册页面的营业执照上传
						uploader="CheckViewFile";
					}
				}
				//对传入的非 简单的字符串进行处理 ，比如说二进制的 图片，电影这些
				else{
					file_count++;//进这个循环一次，上传文件数加一。
					lm.println("I", "RegisterServlet", "doPost", "获取上传文件的总共的容量："+list.get(i).getSize(),ufo);
					if(list.get(i).getSize()>CommonKey.uploadmaxsize_zhizhao){
						bol2=true;
					}else{
						/**
						 * 以下三步，主要获取 上传文件的名字
						 */
						//获取路径名
						String value  = list.get(i).getName() ;
						//索引到最后一个反斜杠
						int start = value.lastIndexOf("\\");
						//截取 上传文件的 字符串名字，加1是 去掉反斜杠，
						String filename = value.substring(start+1);					
						//item.write( new File(path,filename) );//第三方提供的
						InputStream in = list.get(i).getInputStream() ;
						if("CheckViewFile".equals(uploader)){
							bol1=isImage(in);
							if(bol1){//如果是图片的话，上传到tmp文件夹里面
								Date now = new Date(); 
								SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
								String	filenameoutput=CommonKey.uploadFile_chaoshizhizhao_tempfile+dateFormat.format(now)+filename;
								File newimage = new File( this.getServletContext().getRealPath(CommonKey.uploadDir_chaoshizhizhao_tmp)+"\\"+ filenameoutput);
								OutputStream outpic =  new FileOutputStream( newimage.getAbsolutePath(), false );
								 in =  list.get(i).getInputStream() ;
									int length = 0 ;
									byte [] buf = new byte[1024] ;
									while( (length = in.read(buf) ) != -1)
									{
										outpic.write(buf, 0, length);
										outpic.flush();					    
									}
									in.close();
									outpic.close();
									session.setAttribute("yingyezhizhao", filenameoutput);//把文件名放到session中，等用户提交时候把文件转移到放营业执照的那个文件夹,如果用户多次更改图片，这里存最后一次的
							}
						}
					}
				}//end for
			}
			String returnJosn=getJsonForUpload(uploader,bol1,bol2);
			out.write(returnJosn);
			
		} catch (FileUploadException e) {
			// TODO Auto-generated catch block
			lm.println("E", "RegisterServlet", "doPost", "文件上传验证失败:",ufo);
			e.printStackTrace();
			out.write("{\"success\":2}");
		}
		catch (Exception e) {
			// TODO Auto-generated catch block
    	  lm.println("E", "RegisterServlet", "doPost", "文件上传验证失败:",ufo);
    	  e.printStackTrace();
    	  out.write("{\"success\":2}");
		}
  }
 
  public static String bytesToHexString(byte[] src) {  
      StringBuilder stringBuilder = new StringBuilder();  
      if (src == null || src.length <= 0) {  
          return null;  
      }  
      for (int i = 0; i < src.length; i++) {  
          int v = src[i] & 0xFF;  
          String hv = Integer.toHexString(v);  
          if (hv.length() < 2) {  
              stringBuilder.append(0);  
          }  
          stringBuilder.append(hv);  
      }  
      return stringBuilder.toString();  
  } 
  
  //返回json字符串.数据库查询时候
  private String getJson(String sql,String[] param,userBean logbean,LogManager lm) {
	  
	    BasicDatabase bs = new BasicDatabase();
	    String data = "";
	   try{
	    	ResultSet rs = bs.executeSql("LoginServlet","select",sql,param,null,null,null,null,logbean);
	    	if(rs!=null && rs.next()){//检测的信息已经存在
	    		   data = "{\"success\":1}";//json字符串:{"xxxx":"xxxx","xxxx":"xxxxx"}
	    	}else{
	    			data = "{\"success\":0}";//检测的信息不存在，用户可以用此信息申请
	    	}
	    	
	    }catch(Exception e){
	    	lm.println("E", "LoginServlet", "doGet", "致命错误:"+e,logbean);
 		   data = "{\"success\":2}";//json字符串:{"xxxx":"xxxx","xxxx":"xxxxx"}
	    	e.printStackTrace();
	    	  return data;
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
	    	}catch(Exception e){
	        	lm.println("E", "LoginServlet", "doGet", "致命错误:"+e,logbean);
	  		   data = "{\"success\":2}";//json字符串:{"xxxx":"xxxx","xxxx":"xxxxx"}
	    		e.printStackTrace();
	    	}
	    }   
	    return data;

}
  //返回json字符串,在文件上传时
  private String getJsonForUpload(String uploader,boolean bool,boolean bool2) {
	  
	  String data = "";
	  if(bool2){
		  data = "{\"success\":3}";//超过规定大小，返回
		  return data;
	  }
	  if("CheckViewFile".equals(uploader)&&bool==true){//是图片，验证成功
		  data = "{\"success\":0}";//json字符串:{"xxxx":"xxxx","xxxx":"xxxxx"}
	  }else if("CheckViewFile".equals(uploader)&&bool==false){//不是图片，验证失败
		  data = "{\"success\":1}";//json字符串:{"xxxx":"xxxx","xxxx":"xxxxx"}
	  }
	    return data;

}
  
  public static boolean isImage(InputStream in) {  

	    BufferedImage img = null;  
	    try {  
	        img = ImageIO.read(in);  
	        if (img == null || img.getWidth(null) <= 0 || img.getHeight(null) <= 0) {  
	            return false;  
	        }  
	        return true;  
	    } catch (Exception e) {  
	        return false;  
	    } finally {  
	        img = null;  
	    }  
	} 
  
}