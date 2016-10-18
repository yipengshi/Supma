package supma.common;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import javax.servlet.http.*;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.fileupload.FileItem;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;

public class CommonFileUtil{
	
	/**
	 * ������ʵ����,����2�ֽ�(GBK)
	 * @param _baseSt   
	 * @return int      
	 */
	public static void createNewDir(String dir) {
		File file = new File(dir);
		//���Ŀ¼������
		if(!file.exists()){
		 //����Ŀ¼
		 file.mkdirs();
		}
	}
	
	/**
	 * �ϴ��ļ�
	 * @param _baseSt   
	 * @return int      
	 */
	public static void upLoadFiles(HttpServletRequest req, HttpServletResponse res,String _savePath) {
		try{
		  	req.setCharacterEncoding("utf-8"); // ���ñ���  
		  	res.setCharacterEncoding("utf-8");  
		  	res.setContentType("text/xml; charset=UTF-8");
		  	
			//��ô����ļ���Ŀ����
			DiskFileItemFactory factory = new DiskFileItemFactory();
			//��ȡ�ļ���Ҫ�ϴ�����·��
			String path = _savePath;
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
				if(!list.get(i).isFormField()){
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
						InputStream in = list.get(i).getInputStream() ;
						bol1=isImage(in);
						if(bol1){//�����ͼƬ�Ļ����ϴ���tmp�ļ�������
							Date now = new Date(); 
							SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
							String	filenameoutput=CommonKey.uploadFile_chaoshizhizhao_tempfile+dateFormat.format(now)+filename;
							File newimage = new File( req.getServletContext().getRealPath(CommonKey.uploadDir_chaoshizhizhao_tmp)+"\\"+ filenameoutput);
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
						}
					}
				}
			}

		}catch(Exception e){
			
		}	  	
	}

	/**�ж��ļ��Ƿ���ͼƬ*/
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