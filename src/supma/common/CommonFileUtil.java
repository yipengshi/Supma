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
	 * 返回真实长度,汉字2字节(GBK)
	 * @param _baseSt   
	 * @return int      
	 */
	public static void createNewDir(String dir) {
		File file = new File(dir);
		//如果目录不存在
		if(!file.exists()){
		 //创建目录
		 file.mkdirs();
		}
	}
	
	/**
	 * 上传文件
	 * @param _baseSt   
	 * @return int      
	 */
	public static void upLoadFiles(HttpServletRequest req, HttpServletResponse res,String _savePath) {
		try{
		  	req.setCharacterEncoding("utf-8"); // 设置编码  
		  	res.setCharacterEncoding("utf-8");  
		  	res.setContentType("text/xml; charset=UTF-8");
		  	
			//获得磁盘文件条目工厂
			DiskFileItemFactory factory = new DiskFileItemFactory();
			//获取文件需要上传到的路径
			String path = _savePath;
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
				if(!list.get(i).isFormField()){
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
						InputStream in = list.get(i).getInputStream() ;
						bol1=isImage(in);
						if(bol1){//如果是图片的话，上传到tmp文件夹里面
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

	/**判断文件是否是图片*/
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