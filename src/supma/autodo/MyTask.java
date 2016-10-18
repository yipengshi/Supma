package supma.autodo;

import java.io.File;
import java.lang.*;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.Date;
import java.sql.*;
import java.util.TimerTask;

import supma.common.CommonKey;
import supma.db.BasicDatabase;
import supma.log.LogManager;

import javax.servlet.ServletContext;

public class MyTask extends TimerTask {
	private static final long serialVersionUID = 30L;
	private static final int C_SCHEDULE_HOUR = 0;// 晚上12点，也即0点
	private static boolean isRunning = false;// 避免第二次又被调度以引起执行冲突，设置了当前是否正在执行的状态标志
	private ServletContext context = null;

	public MyTask(ServletContext context) {
		this.context = context;
	}

	public void run() {
		Calendar cal = Calendar.getInstance();
		if (!isRunning) {
			isRunning = true;
			LogManager lm = LogManager.getInstance(context.getRealPath(CommonKey.logDir));
			//lm.println("I", "MyTask", "run", "开始执行指定任务", null);
			
			// TODO 添加自定义的详细任务
			//1.删除注册时候upload/chaoshizhizhao/temp下的临时文件(每隔3小时执行一次，因为2小时session过期，所以3小时可以清除了。根据文件名的时间判断
			String dir = context.getRealPath(CommonKey.uploadDir_chaoshizhizhao_tmp);
			Calendar calendar = Calendar.getInstance();
			calendar.set(Calendar.HOUR_OF_DAY, calendar.get(Calendar.HOUR_OF_DAY) - 3);//取得当前时间3小时前的时间
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHH");
			String before3hour = dateFormat.format(calendar.getTime());
			deleteFile(new File(dir),Integer.parseInt(before3hour));



			isRunning = false;
			//lm.println("I", "MyTask", "run", "指定任务执行结束", null);
		} else {
			LogManager lm = LogManager.getInstance(context.getRealPath(CommonKey.logDir));
			//lm.println("I", "MyTask", "run", "上一次任务执行还未结束", null);
		}
	}

    /**
     * 删除文件夹下的所有文件
     * @param oldPath
     * @param date->某个时间以前的
     */
    public void deleteFile(File oldPath,int date) {
          if (oldPath.isDirectory()) {
           File[] files = oldPath.listFiles();
           for (File file : files) {
             deleteFile(file,date);
           }
          }else{
        	String filename = oldPath.getName();
        	int position =Integer.parseInt(filename.substring(CommonKey.uploadFile_chaoshizhizhao_tempfile.length(),CommonKey.uploadFile_chaoshizhizhao_tempfile.length()+10)) ;
        	if(date>=position){
                oldPath.delete();
        	}
          }
        }
}
