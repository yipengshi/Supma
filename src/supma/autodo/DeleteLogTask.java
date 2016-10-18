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
	private static boolean isRunning = false;// ����ڶ����ֱ�����������ִ�г�ͻ�������˵�ǰ�Ƿ�����ִ�е�״̬��־
	private ServletContext context = null;

	public DeleteLogTask(ServletContext context) {
		this.context = context;
	}

	public void run() {
		if (!isRunning) {
			isRunning = true;
			LogManager lm = LogManager.getInstance(context.getRealPath(CommonKey.logDir));
			//lm.println("I", "DeleteLogTask", "run", "��ʼִ��ָ������", null);
			
			// TODO ����Զ������ϸ����
			//1.ɾ��log�ļ���log/online�µ�log�ļ�(ÿ��1����ִ��һ�Σ������ļ�����ʱ���ж�
			String dir = context.getRealPath(CommonKey.logDir);
			Calendar calendar = Calendar.getInstance();
			calendar.set(Calendar.MONTH, calendar.get(Calendar.MONTH) - 1);//ȡ�õ�ǰʱ��1������ǰ��ʱ��
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHH");
			String before1month = dateFormat.format(calendar.getTime());
			deleteFile(new File(dir),Integer.parseInt(before1month));



			isRunning = false;
			//lm.println("I", "DeleteLogTask", "run", "ָ������ִ�н���", null);
		} else {
			LogManager lm = LogManager.getInstance(context.getRealPath(CommonKey.logDir));
			//lm.println("I", "DeleteLogTask", "run", "��һ������ִ�л�δ����", null);
		}
	}

    /**
     * ɾ���ļ����µ������ļ�
     * @param oldPath
     * @param date->ĳ��ʱ����ǰ��
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
