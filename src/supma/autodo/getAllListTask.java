package supma.autodo;

import java.io.File;
import java.lang.*;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.Date;
import java.sql.*;
import java.util.TimerTask;

import supma.beans.GoodsTypeBean;
import supma.beans.userBean;
import supma.common.CommonKey;
import supma.db.BasicDatabase;
import supma.log.LogManager;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

public class getAllListTask extends TimerTask {

	private static boolean isRunning = false;// 避免第二次又被调度以引起执行冲突，设置了当前是否正在执行的状态标志
	private ServletContext context = null;

	public getAllListTask(ServletContext context) {
		this.context = context;
	}

	public void run() {
		if (!isRunning) {
			isRunning = true;
			LogManager lm = LogManager.getInstance(context.getRealPath(CommonKey.logDir));
		//	lm.println("I", "getAllListTask", "run", "开始执行指定任务", null);
			
			// TODO 添加自定义的详细任务
			//1.取得商品类型列表
			try{
				GoodsTypeBean goodsTypeBean = getGoodsType();
				//设置第一级别的商品类别
				context.setAttribute(CommonKey.goodsTypeListData_1, goodsTypeBean.list1);
				context.setAttribute(CommonKey.goodsTypeListData_2, goodsTypeBean.list2);
				context.setAttribute(CommonKey.goodsTypeListData_3, goodsTypeBean.list3);
			}catch(Exception e){
	  				e.printStackTrace();
	  				lm.println("E", "getAllListTask", "getGoodsTypeJosn", "致命错误:"+e.toString(),null);
			}
			isRunning = false;
			//lm.println("I", "getAllListTask", "run", "指定任务执行结束", null);
		} else {
			LogManager lm = LogManager.getInstance(context.getRealPath(CommonKey.logDir));
			//lm.println("I", "getAllListTask", "run", "上一次任务执行还未结束", null);
		}
	  	
	}

	//生成商品类型选择三层下拉列表的数据
		private static GoodsTypeBean getGoodsType() throws Exception {
			GoodsTypeBean goodsTypeBean = new GoodsTypeBean();
			BasicDatabase basicDataBase = new BasicDatabase();
			goodsTypeBean.initGoodsTypeBean(basicDataBase.executeSql("GoodsInputServlet","select",goodsTypeBean.getAllGoodsTypeSql(),null,null,null,null,null,null));
			if(basicDataBase.pstm !=null){
				basicDataBase.pstm.close();
				basicDataBase.pstm=null;
			}
			if(basicDataBase.conn != null){
				basicDataBase.conn.close();
				basicDataBase.conn=null;
			}
				return goodsTypeBean;
		}
}
