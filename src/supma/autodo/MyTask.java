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
	private static final int C_SCHEDULE_HOUR = 0;// ����12�㣬Ҳ��0��
	private static boolean isRunning = false;// ����ڶ����ֱ�����������ִ�г�ͻ�������˵�ǰ�Ƿ�����ִ�е�״̬��־
	private ServletContext context = null;

	public MyTask(ServletContext context) {
		this.context = context;
	}

	public void run() {
		Calendar cal = Calendar.getInstance();
		if (!isRunning) {
			isRunning = true;
			LogManager lm = LogManager.getInstance(context.getRealPath(CommonKey.logDir));
			//lm.println("I", "MyTask", "run", "��ʼִ��ָ������", null);
			
			// TODO ����Զ������ϸ����
			//1.ɾ��ע��ʱ��upload/chaoshizhizhao/temp�µ���ʱ�ļ�(ÿ��3Сʱִ��һ�Σ���Ϊ2Сʱsession���ڣ�����3Сʱ��������ˡ������ļ�����ʱ���ж�
			String dir = context.getRealPath(CommonKey.uploadDir_chaoshizhizhao_tmp);
			Calendar calendar = Calendar.getInstance();
			calendar.set(Calendar.HOUR_OF_DAY, calendar.get(Calendar.HOUR_OF_DAY) - 3);//ȡ�õ�ǰʱ��3Сʱǰ��ʱ��
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHH");
			String before3hour = dateFormat.format(calendar.getTime());
			deleteFile(new File(dir),Integer.parseInt(before3hour));



			isRunning = false;
			//lm.println("I", "MyTask", "run", "ָ������ִ�н���", null);
		} else {
			LogManager lm = LogManager.getInstance(context.getRealPath(CommonKey.logDir));
			//lm.println("I", "MyTask", "run", "��һ������ִ�л�δ����", null);
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
        	int position =Integer.parseInt(filename.substring(CommonKey.uploadFile_chaoshizhizhao_tempfile.length(),CommonKey.uploadFile_chaoshizhizhao_tempfile.length()+10)) ;
        	if(date>=position){
                oldPath.delete();
        	}
          }
        }
}
