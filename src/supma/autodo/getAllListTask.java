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

	private static boolean isRunning = false;// ����ڶ����ֱ�����������ִ�г�ͻ�������˵�ǰ�Ƿ�����ִ�е�״̬��־
	private ServletContext context = null;

	public getAllListTask(ServletContext context) {
		this.context = context;
	}

	public void run() {
		if (!isRunning) {
			isRunning = true;
			LogManager lm = LogManager.getInstance(context.getRealPath(CommonKey.logDir));
		//	lm.println("I", "getAllListTask", "run", "��ʼִ��ָ������", null);
			
			// TODO ����Զ������ϸ����
			//1.ȡ����Ʒ�����б�
			try{
				GoodsTypeBean goodsTypeBean = getGoodsType();
				//���õ�һ�������Ʒ���
				context.setAttribute(CommonKey.goodsTypeListData_1, goodsTypeBean.list1);
				context.setAttribute(CommonKey.goodsTypeListData_2, goodsTypeBean.list2);
				context.setAttribute(CommonKey.goodsTypeListData_3, goodsTypeBean.list3);
			}catch(Exception e){
	  				e.printStackTrace();
	  				lm.println("E", "getAllListTask", "getGoodsTypeJosn", "��������:"+e.toString(),null);
			}
			isRunning = false;
			//lm.println("I", "getAllListTask", "run", "ָ������ִ�н���", null);
		} else {
			LogManager lm = LogManager.getInstance(context.getRealPath(CommonKey.logDir));
			//lm.println("I", "getAllListTask", "run", "��һ������ִ�л�δ����", null);
		}
	  	
	}

	//������Ʒ����ѡ�����������б������
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
