package supma.servlets;

import javax.imageio.ImageIO;
import javax.imageio.stream.*;
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

import supma.common.CommonFileUtil;
import supma.common.CommonKey;
import supma.common.CommonStringUtil;
import supma.db.BasicDatabase;
import supma.log.LogManager;
import supma.util.BaseGetIP;
import supma.util.StringUtil;

import java.text.SimpleDateFormat;
import java.util.*;
import java.util.Date;

import supma.beans.*;

import java.sql.*;

public class RegisterServlet
    extends HttpServlet {
	


	private static final long serialVersionUID = 2350863337953156844L;


public void doGet(HttpServletRequest req, HttpServletResponse res) throws
      IOException, ServletException{
	  doPost(req,res);
  }


  
  public void doPost(HttpServletRequest req, HttpServletResponse res) throws
      IOException, ServletException {

      	req.setCharacterEncoding("utf-8"); // 设置编码  
      	res.setCharacterEncoding("utf-8");  
      	res.setContentType("text/html;charset=UTF-8");  
      	//session
        HttpSession session = req.getSession(true);
      	//log
  		LogManager lm = LogManager.getInstance(this.getServletContext().getRealPath(CommonKey.logDir));
  		//print out
  		PrintWriter out = res.getWriter();
  		//用户信息
  		userBean ufo = new userBean();//现在使用用户的信息;
  	   	ufo = new userBean();
  	   	//取得用户提交的注册信息,放到bean里面

    	
		//获得磁盘文件条目工厂
		DiskFileItemFactory factory = new DiskFileItemFactory();
		//高水平的API文件上传处理
		ServletFileUpload upload = new ServletFileUpload(factory);
		//保存用户信息到bean和保存文件
		boolean bool =false;
		//保存用户信息到数据库
		int insertcount=0;
		try{
			bool=setUserInfo(ufo,req,lm,upload);
			insertcount=ufo.insertOneRow(ufo);
		}catch(Exception e){
			e.printStackTrace();
			lm.println("E", "LoginServlet", "doPost", "致命错误:"+e,ufo);
//			throw new ServletException(e);
		}
		if(insertcount==1&&bool==true){//如果成功插入数据并且成功上传文件
			session.setAttribute("loginUserInfo",null);//注册超市。因为刚刚注册，没登陆所以把userinfo清空
			if(CommonKey.user_type1.equals(ufo.user_type)){//超市
				out.write("{\"success\":1}");//jquery页面迁移参数返回success 1
			}else if(CommonKey.user_type2.equals(ufo.user_type)){//批发商
				out.write("{\"success\":2}");//jquery页面迁移参数返回success 2
			}else if(CommonKey.user_type3.equals(ufo.user_type)){//管理员
				out.write("{\"success\":3}");//jquery页面迁移参数返回success 3
			}
		}else if(insertcount==1&&bool==false){
			//没成功写入文件
			out.write("{\"success\":4}");//jquery页面迁移参数返回success 4，迁移到错误页面
		}else if(insertcount==0&&bool==true){
			//没成功插入数据
			out.write("{\"success\":4}");//jquery页面迁移参数返回success 4，迁移到错误页面
		}else{
			//没成功写入文件并且没成功插入数据
			out.write("{\"success\":4}");//jquery页面迁移参数返回success 4，迁移到错误页面
		}


  }
 
 
  //取得用户提交的注册信息放到bean里面
  private boolean setUserInfo(userBean bean,HttpServletRequest req,LogManager lm,ServletFileUpload upload) throws Exception{
	  boolean bolreturn=true;
		try {
			//可以上传多个文件
			List<FileItem> list = (List<FileItem>)upload.parseRequest(req);
			
			for(int i=0;i<list.size();i++){
				//获取表单的属性名字
				String name = list.get(i).getFieldName();
				//如果获取的 表单信息是普通的 文本 信息
				if(list.get(i).isFormField()){					
					//获取用户具体输入的字符串 ，名字起得挺好，因为表单提交过来的是 字符串类型的
					String value = list.get(i).getString() ;
					if("usertype".equals(name)){
						bean.user_type=value.trim();
					}else if("username".equals(name)){
						bean.user_id=URLDecoder.decode(StringUtil.nulltoblank(value), "UTF-8").trim();
					}else if("pwd".equals(name)){
						bean.user_pwd=URLDecoder.decode(StringUtil.nulltoblank(value), "UTF-8").trim();
					}else if("userallname".equals(name)){
						bean.user_name=URLDecoder.decode(StringUtil.nulltoblank(value), "UTF-8").trim();
					}else if("u_area".equals(name)){
						bean.user_address2=URLDecoder.decode(StringUtil.nulltoblank(value), "UTF-8").trim();
					}else if("user_address1".equals(name)){
						bean.user_address1=URLDecoder.decode(StringUtil.nulltoblank(value), "UTF-8").trim();
					}else if("mobo".equals(name)){
						bean.user_tel_no=URLDecoder.decode(StringUtil.nulltoblank(value), "UTF-8").trim();
					}else if("telno".equals(name)){
						bean.user_tel_no2=URLDecoder.decode(StringUtil.nulltoblank(value), "UTF-8").trim();
					}else if("mail".equals(name)){
						bean.user_mail_address=URLDecoder.decode(StringUtil.nulltoblank(value), "UTF-8").trim();
					}else if("u_goodstype".equals(name)){
						bean.user_notuser1=URLDecoder.decode(StringUtil.nulltoblank(value), "UTF-8").trim();
					}
					bean.user_sco="0";//刚注册积分0
					bean.user_level=CommonKey.user_levle_1;//刚注册等级1
					Date now = new Date(); 
					SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
					bean.user_regis_date=dateFormat.format(now);
					bean.user_last_login_date=dateFormat.format(now);
					bean.user_islock=CommonKey.user_islock_2;//新注册（待审核)
					bean.user_notuser11=BaseGetIP.getIpAddr(req);//ip
				}
			}//end for
			 HttpSession sessionq = req.getSession(true);
			 String sessionfile = (String)sessionq.getAttribute("yingyezhizhao");
			 if(sessionfile!=null && !"".equals(sessionfile)){//如果session里有上传的文件名，做一个移动
				 //String newFileName = bean.user_id+sessionfile.substring(sessionfile.lastIndexOf("."));//新文件名
				 String newFilePath = CommonStringUtil.makePath(sessionfile, CommonKey.uploadDir_chaoshizhizhao);
				 String newFileName =CommonStringUtil.makeFileName(sessionfile);
				 //如果目录不存在创建目录
				 CommonFileUtil.createNewDir(this.getServletContext().getRealPath(newFilePath));
				 File oldfile = new File(this.getServletContext().getRealPath(CommonKey.uploadDir_chaoshizhizhao_tmp)+"/"+ sessionfile); //旧文件
				 File newfile = new File(this.getServletContext().getRealPath(newFilePath+"/"+ newFileName));//新文件
				 bolreturn = oldfile.renameTo(newfile);//文件移动
				 oldfile.delete();
				 bean.user_notuser2= newFilePath+"/"+newFileName;//放入filename
			 }
		} catch (Exception e) {
			// TODO Auto-generated catch block
    	  lm.println("E", "RegisterServlet", "doPost", "文件上传失败:",bean);
    	  bolreturn=false;
    	  throw e;
			//e.printStackTrace();
		}
		return bolreturn;
  }
  
  
}