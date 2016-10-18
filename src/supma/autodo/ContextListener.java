package supma.autodo;

import java.lang.*;
import java.util.*;
import java.sql.*;
import java.util.TimerTask;

import supma.common.CommonKey;
import supma.db.BasicDatabase;
import supma.log.LogManager;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextListener;
import javax.servlet.ServletContextEvent;

public class ContextListener implements ServletContextListener {
	private java.util.Timer timer = null;//����ɾ����ʱ�ļ���
	private java.util.Timer timer2 = null;//���ڸ��¸��������б������
	private java.util.Timer timer3 = null;//ɾ��log�ļ�,���8Сʱ(ɾ��һ����ǰ��log�ļ�->��֪�����÷������ǲ���ÿ����������������8Сʱִ��һ��)

	public void contextInitialized(ServletContextEvent event) {
		LogManager lm = LogManager.getInstance(event.getServletContext().getRealPath(CommonKey.logDir));
		//��ʱ��1:ɾ���ļ�(���1Сʱ)
		timer = new java.util.Timer(true);
		lm.println("I", "ContextListener", "contextInitialized", "init�ɹ�����ʱ��1 ������", null);
		timer.schedule(new MyTask(event.getServletContext()), 0,60 * 60 * 1000);
		lm.println("I", "ContextListener", "contextInitialized", "��ʱ��1 �Ѿ����������ȱ�", null);
		//��ʱ��2:���ڸ��¸��������б������(��������)
		timer2 = new java.util.Timer(true);
		lm.println("I", "ContextListener", "contextInitialized", "init�ɹ�����ʱ��2  ������", null);
		timer.schedule(new getAllListTask(event.getServletContext()), 0,5 * 60 * 1000);
		lm.println("I", "ContextListener", "contextInitialized", "��ʱ��2 �Ѿ����������ȱ�", null);
		//��ʱ��3:ɾ��log�ļ�(���8Сʱ)->ɾ��һ����ǰ��log
		timer3 = new java.util.Timer(true);
		lm.println("I", "ContextListener", "contextInitialized", "init�ɹ�����ʱ��3 ������", null);
		timer3.schedule(new DeleteLogTask(event.getServletContext()), 0,8*60 * 60 * 1000);
		lm.println("I", "ContextListener", "contextInitialized", "��ʱ��3 �Ѿ����������ȱ�", null);
	}

	public void contextDestroyed(ServletContextEvent event) {
		timer.cancel();
		LogManager lm = LogManager.getInstance(event.getServletContext().getRealPath(CommonKey.logDir));
		lm.println("I", "ContextListener", "contextDestroyed", "��ʱ��1 ������", null);
		timer2.cancel();
		lm.println("I", "ContextListener", "contextDestroyed", "��ʱ��2 ������", null);
		timer3.cancel();
		lm.println("I", "ContextListener", "contextDestroyed", "��ʱ��3 ������", null);
	}
}
