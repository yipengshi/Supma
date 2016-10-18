package supma.log;

import java.util.*;
import java.io.*;
import java.text.*;
import supma.beans.*;
import supma.common.CommonKey;

//intServlet启动后，本class的初始化被调用。生命周期是服务停止。生命周期内只有一个class
public class LogManager {
	private static final long serialVersionUID = 14L;
	private static LogManager _lm;
	String level="";
	String initLevel;
	static String dir;
	 PrintWriter fw;
	String logFilename;
	File logFile;
	Locale locale;
	boolean appendMode=true;
	String logWriteModeText="new";

	
	protected LogManager() {
//		ResourceBundle bund =null;
//		bund= ResourceBundle.getBundle("SupmaLogManager");
//		this.level = bund.getString("logLevel");
//		this.initLevel = this.level;
//		this.dir = bund.getString("logDir");
//		this.logFilename = bund.getString("logFileName");
//		String lwm = bund.getString("logWriteMode");
		
		this.level = CommonKey.logLevel;
		this.initLevel = this.level;
		this.logFilename = CommonKey.logFileName;
		
		Date now = new Date(); 
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");

		logFilename =dir+"\\"+ logFilename+dateFormat.format(now)+".txt";
		
		System.out.println("log to "+dir+logFilename);//
		try{
			fw=getFileWriterInstance(logFilename,appendMode);
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}

	
	  protected static PrintWriter getFileWriterInstance( String fileName, boolean append ) throws Exception {
		    File logFile = new File( fileName );
		    FileOutputStream fos;
		    try {
		      fos = new FileOutputStream( logFile.getAbsolutePath(), append );
		     
		    } catch ( IOException e ) {
		      throw e;
		    }
		    return new PrintWriter( new OutputStreamWriter(fos,"GBK") );
		  }
	
	public  static LogManager getInstance(String s) {
		if (_lm == null) {
			dir = s;
			_lm=new LogManager();
		}
		return _lm;
	}
	

	  //messagecod一般写方法名等
		public synchronized void println(String logtype, String className, String messageCode, String e,userBean ufo) {
			String userid = "";
			String userIp = "";
			if(ufo!=null){
				userid = ufo.user_id==null?"":ufo.user_id;
				userIp = ufo.user_notuser11==null?"":ufo.user_notuser11;
			}
			Date now = new Date(); 
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
			String s = dateFormat.format(now);
			try{
				 fw.write("["+s+"]"+"[登录用户名:"+userid+"]"+"[用户IP:"+userIp+"]"+logtype+":"+className+"|"+messageCode+"|"+e); 
				 fw.println();
				 fw.flush();
			}catch(Exception es){
				es.printStackTrace();
			}
			 
		} 

		
		private synchronized void logClose() {
					this.println("I", "LogManager", "LOG00002", "Log File :" + logFilename + " Closed",null);
			        fw.close();  
		}

}