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

  	req.setCharacterEncoding("utf-8"); // ���ñ���  
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

	
	userBean ufo = new userBean();//����ʹ���û�����Ϣ;
   	ufo = new userBean();
   	ufo.user_notuser11=loginIP;

    String action = req.getParameter("action");//��֤�û���������
    if("CheckUnicknme".equals(action)){
    	//String uid = req.getParameter("uid");
    	ufo.user_id=uid;
    	String reurnJosn = getJson(ufo.getLoginUserSql(),new String[]{uid},ufo,lm);
    	 out.write(reurnJosn);
    	 return;
    }else if ("CheckUnickmail".equals(action)){//��֤mail������
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
        			data = "{\"success\":0}";//��֤����ȷ
        		}else{
        			data = "{\"success\":1}";//��֤�벻��ȷ
        		}
        	}else{
        		data="{\"success\":2}";//��֤��Ϊ��
        	}
    	}else{
    		data="{\"success\":2}";//��֤��Ϊ��
    	}
    	out.write(data);
    	return;
    }
    
  }

  public void doPost(HttpServletRequest req, HttpServletResponse res) throws
      IOException, ServletException {
	  
      	req.setCharacterEncoding("utf-8"); // ���ñ���  
      	res.setCharacterEncoding("utf-8");  
      	res.setContentType("text/html;charset=UTF-8");  
      	
		LogManager lm = LogManager.getInstance(this.getServletContext().getRealPath(CommonKey.logDir));
  		userBean ufo = new userBean();//����ʹ���û�����Ϣ;
  	   	ufo = new userBean();
  	    String loginIP = BaseGetIP.getIpAddr(req);
  	    HttpSession session = req.getSession(true);
  	   	ufo.user_notuser11=loginIP;
    	ufo.user_id="";
    	
    	PrintWriter out = res.getWriter();
    	
		//��ô����ļ���Ŀ����
		DiskFileItemFactory factory = new DiskFileItemFactory();
		//��ȡ�ļ���Ҫ�ϴ�����·��
		String path = this.getServletConfig().getServletContext().getRealPath(CommonKey.uploadDir_chaoshizhizhao_tmp);
		//���û�����������õĻ����ϴ���� �ļ� ��ռ�� �ܶ��ڴ棬
		//������ʱ��ŵ� �洢�� , ����洢�ң����Ժ� ���մ洢�ļ� ��Ŀ¼��ͬ
		/**
		 * ԭ�� �����ȴ浽 ��ʱ�洢�ң�Ȼ��������д�� ��ӦĿ¼��Ӳ���ϣ� 
		 * ������˵ ���ϴ�һ���ļ�ʱ����ʵ���ϴ������ݣ���һ������ .tem ��ʽ�� 
		 * Ȼ���ٽ�������д�� ��ӦĿ¼��Ӳ����
		 */
		factory.setRepository(new File(path));
		//���� ����Ĵ�С�����ϴ��ļ������������û���ʱ��ֱ�ӷŵ� ��ʱ�洢��
		factory.setSizeThreshold(1024*1024*5) ;//5M
		//��ˮƽ��API�ļ��ϴ�����
		ServletFileUpload upload = new ServletFileUpload(factory);
		try {
			//�����ϴ�����ļ�
			List<FileItem> list = (List<FileItem>)upload.parseRequest(req);
			//�ļ�����
			int file_count=0;
			//�ĸ�ҳ���ĸ�upload�ؼ�
			String uploader = "";
			//һ���ļ���ʱ�򷵻�ֵ
			boolean bol1 = false;
			//�����ļ���С����fla
			boolean bol2 =false;
			
			for(int i=0;i<list.size();i++){
				//��ȡ������������
				String name = list.get(i).getFieldName();
				//�����ȡ�� ����Ϣ����ͨ�� �ı� ��Ϣ
				if(list.get(i).isFormField()){					
					//��ȡ�û�����������ַ��� ���������ͦ�ã���Ϊ���ύ�������� �ַ������͵�
					String value = list.get(i).getString() ;
					if("action".equals(name) && "CheckViewFile".equals(value)){//ע��ҳ���Ӫҵִ���ϴ�
						uploader="CheckViewFile";
					}
				}
				//�Դ���ķ� �򵥵��ַ������д��� ������˵�����Ƶ� ͼƬ����Ӱ��Щ
				else{
					file_count++;//�����ѭ��һ�Σ��ϴ��ļ�����һ��
					lm.println("I", "RegisterServlet", "doPost", "��ȡ�ϴ��ļ����ܹ���������"+list.get(i).getSize(),ufo);
					if(list.get(i).getSize()>CommonKey.uploadmaxsize_zhizhao){
						bol2=true;
					}else{
						/**
						 * ������������Ҫ��ȡ �ϴ��ļ�������
						 */
						//��ȡ·����
						String value  = list.get(i).getName() ;
						//���������һ����б��
						int start = value.lastIndexOf("\\");
						//��ȡ �ϴ��ļ��� �ַ������֣���1�� ȥ����б�ܣ�
						String filename = value.substring(start+1);					
						//item.write( new File(path,filename) );//�������ṩ��
						InputStream in = list.get(i).getInputStream() ;
						if("CheckViewFile".equals(uploader)){
							bol1=isImage(in);
							if(bol1){//�����ͼƬ�Ļ����ϴ���tmp�ļ�������
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
									session.setAttribute("yingyezhizhao", filenameoutput);//���ļ����ŵ�session�У����û��ύʱ����ļ�ת�Ƶ���Ӫҵִ�յ��Ǹ��ļ���,����û���θ���ͼƬ����������һ�ε�
							}
						}
					}
				}//end for
			}
			String returnJosn=getJsonForUpload(uploader,bol1,bol2);
			out.write(returnJosn);
			
		} catch (FileUploadException e) {
			// TODO Auto-generated catch block
			lm.println("E", "RegisterServlet", "doPost", "�ļ��ϴ���֤ʧ��:",ufo);
			e.printStackTrace();
			out.write("{\"success\":2}");
		}
		catch (Exception e) {
			// TODO Auto-generated catch block
    	  lm.println("E", "RegisterServlet", "doPost", "�ļ��ϴ���֤ʧ��:",ufo);
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
  
  //����json�ַ���.���ݿ��ѯʱ��
  private String getJson(String sql,String[] param,userBean logbean,LogManager lm) {
	  
	    BasicDatabase bs = new BasicDatabase();
	    String data = "";
	   try{
	    	ResultSet rs = bs.executeSql("LoginServlet","select",sql,param,null,null,null,null,logbean);
	    	if(rs!=null && rs.next()){//������Ϣ�Ѿ�����
	    		   data = "{\"success\":1}";//json�ַ���:{"xxxx":"xxxx","xxxx":"xxxxx"}
	    	}else{
	    			data = "{\"success\":0}";//������Ϣ�����ڣ��û������ô���Ϣ����
	    	}
	    	
	    }catch(Exception e){
	    	lm.println("E", "LoginServlet", "doGet", "��������:"+e,logbean);
 		   data = "{\"success\":2}";//json�ַ���:{"xxxx":"xxxx","xxxx":"xxxxx"}
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
	        	lm.println("E", "LoginServlet", "doGet", "��������:"+e,logbean);
	  		   data = "{\"success\":2}";//json�ַ���:{"xxxx":"xxxx","xxxx":"xxxxx"}
	    		e.printStackTrace();
	    	}
	    }   
	    return data;

}
  //����json�ַ���,���ļ��ϴ�ʱ
  private String getJsonForUpload(String uploader,boolean bool,boolean bool2) {
	  
	  String data = "";
	  if(bool2){
		  data = "{\"success\":3}";//�����涨��С������
		  return data;
	  }
	  if("CheckViewFile".equals(uploader)&&bool==true){//��ͼƬ����֤�ɹ�
		  data = "{\"success\":0}";//json�ַ���:{"xxxx":"xxxx","xxxx":"xxxxx"}
	  }else if("CheckViewFile".equals(uploader)&&bool==false){//����ͼƬ����֤ʧ��
		  data = "{\"success\":1}";//json�ַ���:{"xxxx":"xxxx","xxxx":"xxxxx"}
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