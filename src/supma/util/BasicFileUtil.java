package supma.util;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class BasicFileUtil {

	private static final long serialVersionUID = 9L;
	
	public BasicFileUtil() {

	}

	public String UpLoad(HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		int MAX_SIZE = 102400 * 102400;
		//		 创建根路径的保存变量
		String rootPath;
		//		声明文件读入类
		DataInputStream in = null;
		FileOutputStream fileOut = null;
		//		取得客户端的网络地址
		String remoteAddr = "";
		//String remoteAddr = request.getRemoteAddr();
		//		获得服务器的名字;
		String serverName = request.getServerName();

		//		取得互联网程序的绝对地址
		String realPath = request.getRealPath(serverName);

		realPath = realPath.substring(0, realPath.lastIndexOf("\\"));
		//		创建文件的保存目录
		rootPath = realPath + "\\image_qk\\";
		//		取得客户端上传的数据类型;
		String contentType = request.getContentType();
		try {
			if (contentType.indexOf("multipart/form-data") >= 0) {
				//		读入上传的数据
				in = new DataInputStream(request.getInputStream());
				int formDataLength = request.getContentLength();
				if (formDataLength > MAX_SIZE) {
					//out.println("<P>上传的文件字节数不可以超过" + MAX_SIZE + "</p>");
					return "<P>上传的文件字节数不可以超过" + MAX_SIZE + "</p>";
				}
				//		保存上传文件的数据
				byte dataBytes[] = new byte[formDataLength];
				int byteRead = 0;
				int totalBytesRead = 0;
				//		上传的数据保存在byte数组
				while (totalBytesRead < formDataLength) {
					byteRead = in.read(dataBytes, totalBytesRead,
							formDataLength);
					totalBytesRead += byteRead;
				}
				//		根据byte数组创建字符串
				String file = new String(dataBytes);
				//		out.println(file);
				//		取得上传的数据的文件名
				String saveFile = file
						.substring(file.indexOf("filename=\"") + 10);
				saveFile = saveFile.substring(0, saveFile.indexOf("\n"));
				saveFile = saveFile.substring(saveFile.lastIndexOf("\\") + 1,
						saveFile.indexOf("\""));
				int lastIndex = contentType.lastIndexOf("=");
				//		取得数据的分隔字符串
				String boundary = contentType.substring(lastIndex + 1,
						contentType.length());
				//		创建保存路径的文件名
				String fileName = rootPath + saveFile;
				//		out.print(fileName);
				//System.out.println("fileName:"+fileName+"**");
				int pos;
				pos = file.indexOf("filename=\"");
				pos = file.indexOf("\n", pos) + 1;
				pos = file.indexOf("\n", pos) + 1;
				pos = file.indexOf("\n", pos) + 1;
				int boundaryLocation = file.indexOf(boundary, pos) - 4;
				//		out.println(boundaryLocation);
				//System.out.println("boundaryLocation:"+boundaryLocation+"**");
				//		取得文件数据的开始的位置
				int startPos = ((file.substring(0, pos)).getBytes()).length;
				//		out.println(startPos);
				//System.out.println("startPos:"+startPos+"**");
				//		取得文件数据的结束的位置
				int endPos = ((file.substring(0, boundaryLocation)).getBytes()).length;
				//		out.println(endPos);
				//System.out.println("endPos:"+endPos+"**");
				//		检查上载文件是否存在
				File checkFile = new File(fileName);
				if (checkFile.exists()) {
					return "<p>" + saveFile + "文件已经存在.</p>";
					//out.println("<p>" + saveFile + "文件已经存在.</p>");
				}
				//		检查上载文件的目录是否存在
				File fileDir = new File(rootPath);
				if (!fileDir.exists()) {
					fileDir.mkdirs();
				}
				//		创建文件的写出类
				fileOut = new FileOutputStream(fileName);
				//		保存文件的数据
				fileOut.write(dataBytes, startPos, (endPos - startPos));
				fileOut.close();
					return saveFile + "文件成功上载.</p>";
			} else {
				String content = request.getContentType();
				 return "<p>上传的数据类型不是multipart/form-data</p>";
			}
		} catch (Exception ex) {
			throw new ServletException(ex.getMessage());
		}
	}
	
	
//	public void doGet(HttpServletRequest request, HttpServletResponse response)
//	throws ServletException, IOException {
//if(ServletFileUpload.isMultipartContent(request)){
//String fileSuffix="";
//BufferedInputStream in=null;			
//BufferedOutputStream out=null;
//String path="";	
//String uuid="";
//String account="";
//DiskFileItemFactory dff=new DiskFileItemFactory();
//ServletFileUpload sfu=new ServletFileUpload(dff);
//try {
//	List<FileItem> fileItems=sfu.parseRequest(request);
//	Iterator<FileItem> fii=fileItems.iterator();
//	while(fii.hasNext()){
//		//FileItemStream fis=fii.next();
//		FileItem fis=fii.next();
//		//处理文件域
//		if(!fis.isFormField()&& fis.getName().length()>0){//是上传文件的
//			fileSuffix=fis.getName().substring(fis.getName().lastIndexOf("."));
//			in=new BufferedInputStream(fis.getInputStream());
//			path=request.getSession().getServletContext().getRealPath("\\images\\upload\\");
//			//如果上传的文件同名，后来者会覆盖之前的，所以产生UUID来作为名字
//			uuid=UUID.randomUUID().toString()+fileSuffix;
//			out=new BufferedOutputStream(new FileOutputStream(new File(path+"\\"+uuid)));
//			Streams.copy(in, out, true);
//		}else{	//是普通的									
//			String fieldName=fis.getFieldName();
//			if(fieldName.equals("account")){//判断文本框（也可以是其他）的名字
//				account=fis.getString();//得到内容
//			}
//			//普通的不止一个，就照上边得到那个普通的写
//		}
//	}
//	//得到所有后进行你的处理
//	request.getRequestDispatcher("……").forward(request, response);
//} catch (FileUploadException e) {
//	e.printStackTrace();
//}
//
//}
//}

}
