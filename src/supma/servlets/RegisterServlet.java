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

      	req.setCharacterEncoding("utf-8"); // ���ñ���  
      	res.setCharacterEncoding("utf-8");  
      	res.setContentType("text/html;charset=UTF-8");  
      	//session
        HttpSession session = req.getSession(true);
      	//log
  		LogManager lm = LogManager.getInstance(this.getServletContext().getRealPath(CommonKey.logDir));
  		//print out
  		PrintWriter out = res.getWriter();
  		//�û���Ϣ
  		userBean ufo = new userBean();//����ʹ���û�����Ϣ;
  	   	ufo = new userBean();
  	   	//ȡ���û��ύ��ע����Ϣ,�ŵ�bean����

    	
		//��ô����ļ���Ŀ����
		DiskFileItemFactory factory = new DiskFileItemFactory();
		//��ˮƽ��API�ļ��ϴ�����
		ServletFileUpload upload = new ServletFileUpload(factory);
		//�����û���Ϣ��bean�ͱ����ļ�
		boolean bool =false;
		//�����û���Ϣ�����ݿ�
		int insertcount=0;
		try{
			bool=setUserInfo(ufo,req,lm,upload);
			insertcount=ufo.insertOneRow(ufo);
		}catch(Exception e){
			e.printStackTrace();
			lm.println("E", "LoginServlet", "doPost", "��������:"+e,ufo);
//			throw new ServletException(e);
		}
		if(insertcount==1&&bool==true){//����ɹ��������ݲ��ҳɹ��ϴ��ļ�
			session.setAttribute("loginUserInfo",null);//ע�ᳬ�С���Ϊ�ո�ע�ᣬû��½���԰�userinfo���
			if(CommonKey.user_type1.equals(ufo.user_type)){//����
				out.write("{\"success\":1}");//jqueryҳ��Ǩ�Ʋ�������success 1
			}else if(CommonKey.user_type2.equals(ufo.user_type)){//������
				out.write("{\"success\":2}");//jqueryҳ��Ǩ�Ʋ�������success 2
			}else if(CommonKey.user_type3.equals(ufo.user_type)){//����Ա
				out.write("{\"success\":3}");//jqueryҳ��Ǩ�Ʋ�������success 3
			}
		}else if(insertcount==1&&bool==false){
			//û�ɹ�д���ļ�
			out.write("{\"success\":4}");//jqueryҳ��Ǩ�Ʋ�������success 4��Ǩ�Ƶ�����ҳ��
		}else if(insertcount==0&&bool==true){
			//û�ɹ���������
			out.write("{\"success\":4}");//jqueryҳ��Ǩ�Ʋ�������success 4��Ǩ�Ƶ�����ҳ��
		}else{
			//û�ɹ�д���ļ�����û�ɹ���������
			out.write("{\"success\":4}");//jqueryҳ��Ǩ�Ʋ�������success 4��Ǩ�Ƶ�����ҳ��
		}


  }
 
 
  //ȡ���û��ύ��ע����Ϣ�ŵ�bean����
  private boolean setUserInfo(userBean bean,HttpServletRequest req,LogManager lm,ServletFileUpload upload) throws Exception{
	  boolean bolreturn=true;
		try {
			//�����ϴ�����ļ�
			List<FileItem> list = (List<FileItem>)upload.parseRequest(req);
			
			for(int i=0;i<list.size();i++){
				//��ȡ������������
				String name = list.get(i).getFieldName();
				//�����ȡ�� ����Ϣ����ͨ�� �ı� ��Ϣ
				if(list.get(i).isFormField()){					
					//��ȡ�û�����������ַ��� ���������ͦ�ã���Ϊ���ύ�������� �ַ������͵�
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
					bean.user_sco="0";//��ע�����0
					bean.user_level=CommonKey.user_levle_1;//��ע��ȼ�1
					Date now = new Date(); 
					SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
					bean.user_regis_date=dateFormat.format(now);
					bean.user_last_login_date=dateFormat.format(now);
					bean.user_islock=CommonKey.user_islock_2;//��ע�ᣨ�����)
					bean.user_notuser11=BaseGetIP.getIpAddr(req);//ip
				}
			}//end for
			 HttpSession sessionq = req.getSession(true);
			 String sessionfile = (String)sessionq.getAttribute("yingyezhizhao");
			 if(sessionfile!=null && !"".equals(sessionfile)){//���session�����ϴ����ļ�������һ���ƶ�
				 //String newFileName = bean.user_id+sessionfile.substring(sessionfile.lastIndexOf("."));//���ļ���
				 String newFilePath = CommonStringUtil.makePath(sessionfile, CommonKey.uploadDir_chaoshizhizhao);
				 String newFileName =CommonStringUtil.makeFileName(sessionfile);
				 //���Ŀ¼�����ڴ���Ŀ¼
				 CommonFileUtil.createNewDir(this.getServletContext().getRealPath(newFilePath));
				 File oldfile = new File(this.getServletContext().getRealPath(CommonKey.uploadDir_chaoshizhizhao_tmp)+"/"+ sessionfile); //���ļ�
				 File newfile = new File(this.getServletContext().getRealPath(newFilePath+"/"+ newFileName));//���ļ�
				 bolreturn = oldfile.renameTo(newfile);//�ļ��ƶ�
				 oldfile.delete();
				 bean.user_notuser2= newFilePath+"/"+newFileName;//����filename
			 }
		} catch (Exception e) {
			// TODO Auto-generated catch block
    	  lm.println("E", "RegisterServlet", "doPost", "�ļ��ϴ�ʧ��:",bean);
    	  bolreturn=false;
    	  throw e;
			//e.printStackTrace();
		}
		return bolreturn;
  }
  
  
}