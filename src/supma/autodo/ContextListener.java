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
	private java.util.Timer timer = null;//定期删除临时文件用
	private java.util.Timer timer2 = null;//定期更新各种下拉列表的数据
	private java.util.Timer timer3 = null;//删除log文件,间隔8小时(删除一个月前的log文件->不知道公用服务器是不是每天重启，所以设置8小时执行一次)

	public void contextInitialized(ServletContextEvent event) {
		LogManager lm = LogManager.getInstance(event.getServletContext().getRealPath(CommonKey.logDir));
		//定时器1:删除文件(间隔1小时)
		timer = new java.util.Timer(true);
		lm.println("I", "ContextListener", "contextInitialized", "init成功。定时器1 已启动", null);
		timer.schedule(new MyTask(event.getServletContext()), 0,60 * 60 * 1000);
		lm.println("I", "ContextListener", "contextInitialized", "定时器1 已经添加任务调度表", null);
		//定时器2:定期更新各种下拉列表的数据(间隔五分钟)
		timer2 = new java.util.Timer(true);
		lm.println("I", "ContextListener", "contextInitialized", "init成功。定时器2  已启动", null);
		timer.schedule(new getAllListTask(event.getServletContext()), 0,5 * 60 * 1000);
		lm.println("I", "ContextListener", "contextInitialized", "定时器2 已经添加任务调度表", null);
		//定时器3:删除log文件(间隔8小时)->删除一个月前的log
		timer3 = new java.util.Timer(true);
		lm.println("I", "ContextListener", "contextInitialized", "init成功。定时器3 已启动", null);
		timer3.schedule(new DeleteLogTask(event.getServletContext()), 0,8*60 * 60 * 1000);
		lm.println("I", "ContextListener", "contextInitialized", "定时器3 已经添加任务调度表", null);
	}

	public void contextDestroyed(ServletContextEvent event) {
		timer.cancel();
		LogManager lm = LogManager.getInstance(event.getServletContext().getRealPath(CommonKey.logDir));
		lm.println("I", "ContextListener", "contextDestroyed", "定时器1 已销毁", null);
		timer2.cancel();
		lm.println("I", "ContextListener", "contextDestroyed", "定时器2 已销毁", null);
		timer3.cancel();
		lm.println("I", "ContextListener", "contextDestroyed", "定时器3 已销毁", null);
	}
}
