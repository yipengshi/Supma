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

public class DeleteLogTask extends TimerTask {
	private static final long serialVersionUID = 32L;
	private static boolean isRunning = false;// 避免第二次又被调度以引起执行冲突，设置了当前是否正在执行的状态标志
	private ServletContext context = null;

	public DeleteLogTask(ServletContext context) {
		this.context = context;
	}

	public void run() {
		if (!isRunning) {
			isRunning = true;
			LogManager lm = LogManager.getInstance(context.getRealPath(CommonKey.logDir));
			//lm.println("I", "DeleteLogTask", "run", "开始执行指定任务", null);
			
			// TODO 添加自定义的详细任务
			//1.删除log文件：log/online下的log文件(每隔1个月执行一次，根据文件名的时间判断
			String dir = context.getRealPath(CommonKey.logDir);
			Calendar calendar = Calendar.getInstance();
			calendar.set(Calendar.MONTH, calendar.get(Calendar.MONTH) - 1);//取得当前时间1个月以前的时间
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHH");
			String before1month = dateFormat.format(calendar.getTime());
			deleteFile(new File(dir),Integer.parseInt(before1month));



			isRunning = false;
			//lm.println("I", "DeleteLogTask", "run", "指定任务执行结束", null);
		} else {
			LogManager lm = LogManager.getInstance(context.getRealPath(CommonKey.logDir));
			//lm.println("I", "DeleteLogTask", "run", "上一次任务执行还未结束", null);
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
        	int position =Integer.parseInt(filename.substring(CommonKey.logFileName.length(),CommonKey.logFileName.length()+10)) ;
        	if(date>=position){
                oldPath.delete();
        	}
          }
        }
}
