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
		//		 ������·���ı������
		String rootPath;
		//		�����ļ�������
		DataInputStream in = null;
		FileOutputStream fileOut = null;
		//		ȡ�ÿͻ��˵������ַ
		String remoteAddr = "";
		//String remoteAddr = request.getRemoteAddr();
		//		��÷�����������;
		String serverName = request.getServerName();

		//		ȡ�û���������ľ��Ե�ַ
		String realPath = request.getRealPath(serverName);

		realPath = realPath.substring(0, realPath.lastIndexOf("\\"));
		//		�����ļ��ı���Ŀ¼
		rootPath = realPath + "\\image_qk\\";
		//		ȡ�ÿͻ����ϴ�����������;
		String contentType = request.getContentType();
		try {
			if (contentType.indexOf("multipart/form-data") >= 0) {
				//		�����ϴ�������
				in = new DataInputStream(request.getInputStream());
				int formDataLength = request.getContentLength();
				if (formDataLength > MAX_SIZE) {
					//out.println("<P>�ϴ����ļ��ֽ��������Գ���" + MAX_SIZE + "</p>");
					return "<P>�ϴ����ļ��ֽ��������Գ���" + MAX_SIZE + "</p>";
				}
				//		�����ϴ��ļ�������
				byte dataBytes[] = new byte[formDataLength];
				int byteRead = 0;
				int totalBytesRead = 0;
				//		�ϴ������ݱ�����byte����
				while (totalBytesRead < formDataLength) {
					byteRead = in.read(dataBytes, totalBytesRead,
							formDataLength);
					totalBytesRead += byteRead;
				}
				//		����byte���鴴���ַ���
				String file = new String(dataBytes);
				//		out.println(file);
				//		ȡ���ϴ������ݵ��ļ���
				String saveFile = file
						.substring(file.indexOf("filename=\"") + 10);
				saveFile = saveFile.substring(0, saveFile.indexOf("\n"));
				saveFile = saveFile.substring(saveFile.lastIndexOf("\\") + 1,
						saveFile.indexOf("\""));
				int lastIndex = contentType.lastIndexOf("=");
				//		ȡ�����ݵķָ��ַ���
				String boundary = contentType.substring(lastIndex + 1,
						contentType.length());
				//		��������·�����ļ���
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
				//		ȡ���ļ����ݵĿ�ʼ��λ��
				int startPos = ((file.substring(0, pos)).getBytes()).length;
				//		out.println(startPos);
				//System.out.println("startPos:"+startPos+"**");
				//		ȡ���ļ����ݵĽ�����λ��
				int endPos = ((file.substring(0, boundaryLocation)).getBytes()).length;
				//		out.println(endPos);
				//System.out.println("endPos:"+endPos+"**");
				//		��������ļ��Ƿ����
				File checkFile = new File(fileName);
				if (checkFile.exists()) {
					return "<p>" + saveFile + "�ļ��Ѿ�����.</p>";
					//out.println("<p>" + saveFile + "�ļ��Ѿ�����.</p>");
				}
				//		��������ļ���Ŀ¼�Ƿ����
				File fileDir = new File(rootPath);
				if (!fileDir.exists()) {
					fileDir.mkdirs();
				}
				//		�����ļ���д����
				fileOut = new FileOutputStream(fileName);
				//		�����ļ�������
				fileOut.write(dataBytes, startPos, (endPos - startPos));
				fileOut.close();
					return saveFile + "�ļ��ɹ�����.</p>";
			} else {
				String content = request.getContentType();
				 return "<p>�ϴ����������Ͳ���multipart/form-data</p>";
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
//		//�����ļ���
//		if(!fis.isFormField()&& fis.getName().length()>0){//���ϴ��ļ���
//			fileSuffix=fis.getName().substring(fis.getName().lastIndexOf("."));
//			in=new BufferedInputStream(fis.getInputStream());
//			path=request.getSession().getServletContext().getRealPath("\\images\\upload\\");
//			//����ϴ����ļ�ͬ���������߻Ḳ��֮ǰ�ģ����Բ���UUID����Ϊ����
//			uuid=UUID.randomUUID().toString()+fileSuffix;
//			out=new BufferedOutputStream(new FileOutputStream(new File(path+"\\"+uuid)));
//			Streams.copy(in, out, true);
//		}else{	//����ͨ��									
//			String fieldName=fis.getFieldName();
//			if(fieldName.equals("account")){//�ж��ı���Ҳ������������������
//				account=fis.getString();//�õ�����
//			}
//			//��ͨ�Ĳ�ֹһ���������ϱߵõ��Ǹ���ͨ��д
//		}
//	}
//	//�õ����к������Ĵ���
//	request.getRequestDispatcher("����").forward(request, response);
//} catch (FileUploadException e) {
//	e.printStackTrace();
//}
//
//}
//}

}
