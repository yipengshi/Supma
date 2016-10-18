package life.wuye.bean;

import java.util.*;
import java.sql.*;

import yao.yao.basic.*;


/**
 * <p>
 * Title: HTML相关的正则表达式工具类
 * </p>
 * <p>
 * Description: 包括过滤HTML标记，转换HTML标记，替换特定HTML标记
 * </p>
 * <p>
 * Copyright: Copyright (c) 2006
 * </p>
 * 
 * @author hejian
 * @version 1.0
 * @createtime 2006-10-16
 */

public class ShouFei {

public String shoufeibiaoid="";
public String yzshenfenzheng="";
public String yzname="";
public String feiyongcode="";
public String shoufeifangshicode="";
public String shoufeiniandu="";
public String shoufeinianyue="";
public String shoufeijiner="";
public String jiaofeizhuangtai="";

public String shoufeishijian="";
public String weijiaofeiliyou="";


public String other = "";
public String other2 = "";//存储业主id

public String other3="";
public String other4="";

public BasicDatabase basices = null;
public ArrayList listLWdetail = null;
//收费方式map
public LinkedHashMap shoufeifangshiMap =null;
//年map 10年
public LinkedHashMap nianMap =null;
//半年map 
public LinkedHashMap bannianMap =null;
//季度map 
public LinkedHashMap jiduMap =null;
//月map 10年
public LinkedHashMap yueMap =null;
//收费项目(费用code)map
public LinkedHashMap feiyongMap =null;

//交费状态map
public LinkedHashMap jiaofeizhuangtaiMap =null;

//交费状态map
public void setJiaofeizhuangtaiMap(){
	jiaofeizhuangtaiMap = new LinkedHashMap();
	jiaofeizhuangtaiMap.put("0","已交费");
	jiaofeizhuangtaiMap.put("1","未交费");
}

//收费方式
public void setShoufeifangshiMap(){
	shoufeifangshiMap = new LinkedHashMap();
	shoufeifangshiMap.put("0","按年度");
	shoufeifangshiMap.put("1","按半年");
	shoufeifangshiMap.put("2","按季度");
	shoufeifangshiMap.put("3","按月份");
}

//年map 
public void setNianMap(){
	nianMap = new LinkedHashMap();
	nianMap.put("1","2011");
	nianMap.put("2","2012");
	nianMap.put("3","2013");
	nianMap.put("4","2014");
	nianMap.put("5","2015");
	nianMap.put("6","2016");
	nianMap.put("7","2017");
	nianMap.put("8","2018");
}
//半年map 
public void setBanNianMap(){
	bannianMap = new LinkedHashMap();
	bannianMap.put("1","上半年");
	bannianMap.put("2","下半年");
}

//季度map 
public void setJiDuMap(){
	jiduMap = new LinkedHashMap();
	jiduMap.put("1","一季度");
	jiduMap.put("2","二季度");
	jiduMap.put("3","三季度");
	jiduMap.put("4","四季度");
}

//月map 
public void setYueMap(){
	yueMap = new LinkedHashMap();
	yueMap.put("1","1月");
	yueMap.put("2","2月");
	yueMap.put("3","3月");
	yueMap.put("4","4月");
	yueMap.put("5","5月");
	yueMap.put("6","6月");
	yueMap.put("7","7月");
	yueMap.put("8","8月");
	yueMap.put("9","9月");
	yueMap.put("10","10月");
	yueMap.put("11","11月");
	yueMap.put("12","12月");
}
//收费项目(费用code)map
public void setFeiYongMap(){
	feiyongMap = new LinkedHashMap();
	feiyongMap.put("1","物业费");
	feiyongMap.put("2","卫生费");
	feiyongMap.put("3","暖气费");
	feiyongMap.put("4","停车费");
	feiyongMap.put("6","杂费");
	feiyongMap.put("7","其他费");
}


public int allCount=0;

	public ShouFei() {
		setShoufeifangshiMap();//收费方式,全年，半年等
		setFeiYongMap();//收费项目
		setNianMap();//年度值
		setBanNianMap();//半年
		setJiDuMap();//季度
		setYueMap();//月度
		setJiaofeizhuangtaiMap();//交费状态
	}

//	查询所有条数
	public int selectallShouFei_count() throws Exception{
		
		basices = new BasicDatabase();
		basices.init();
		basices.conn=basices.getMysqlConnection();
		
		ResultSet res1 = basices.executeSql("select","select count(*) as ALLROWS from shoufei ",null,null,null,null);

		if (res1.next()){

			return Integer.parseInt(basices.rs.getString("ALLROWS"));
		}
		
		if(basices.conn!=null){
			basices.conn.close();
		}
		
		return 0;
	}
	

//	查询所有条数2(条件查询)
	//身份证，名字，费用code,收费方式，收费年度，收费年月季度,交费状态
	public int selectallShouFei_count2(String _yzshenfenzheng,String _yzname,String _feiyongcode,String _shoufeifangshicode,String _shoufeiniandu,String _shoufeinianyue,String _jiaofeizhuangtai) throws Exception{
		
		basices = new BasicDatabase();
		basices.init();
		basices.conn=basices.getMysqlConnection();
		
		StringBuffer bf = new StringBuffer();
		bf.append("select count(*) as ALLROWS from  shoufei ");
		ArrayList keyList = new ArrayList();
		ArrayList valueList = new ArrayList();
		if(!"".equals(_yzshenfenzheng)){
			keyList.add("yzshenfenzheng");
			valueList.add(_yzshenfenzheng);
		}
		if(!"".equals(_yzname)){
			keyList.add("yzname");
			valueList.add(_yzname);
		}
		if(!"".equals(_feiyongcode)){
			keyList.add("feiyongcode");
			valueList.add(_feiyongcode);
		}
		if(!"".equals(_shoufeifangshicode)){
			keyList.add("shoufeifangshicode");
			valueList.add(_shoufeifangshicode);
		}
		if(!"".equals(_shoufeiniandu)){
			keyList.add("shoufeiniandu");
			valueList.add(_shoufeiniandu);
		}
		if(!"".equals(_shoufeinianyue)){
			keyList.add("shoufeinianyue");
			valueList.add(_shoufeinianyue);
		}
		if(!"".equals(_jiaofeizhuangtai)){
			keyList.add("jiaofeizhuangtai");
			valueList.add(_jiaofeizhuangtai);
		}
		for(int i=0;i<keyList.size();i++){
			if(i==0){
				bf.append(" where ");
				bf.append(keyList.get(i));
				bf.append(" like '%");
				bf.append(valueList.get(i));
				bf.append("%' ");
			}else{
				bf.append(" and ");
				bf.append(keyList.get(i));
				bf.append(" like '%");
				bf.append(valueList.get(i));
				bf.append("%' ");
			}
		}
		
		ResultSet res1 = basices.executeSql("select",bf.toString(),null,null,null,null);

		if (res1.next()){

			return Integer.parseInt(basices.rs.getString("ALLROWS"));
		}
		
		if(basices.conn!=null){
			basices.conn.close();
		}
		
		return 0;
	}

	
//	查询1条论文 by id
	public void selectOneShouFei(String _s) throws Exception{
		basices = new BasicDatabase();
		basices.init();
		basices.conn=basices.getMysqlConnection();
		ResultSet res1 = basices.executeSql("select","select * from  shoufei where shoufeibiaoid='"+_s+"' ",null,null,null,null);
		while (res1.next()){
			

			shoufeibiaoid = basices.rs.getString("shoufeibiaoid");
			yzshenfenzheng = basices.rs.getString("yzshenfenzheng");
			yzname = basices.rs.getString("yzname");
			feiyongcode = basices.rs.getString("feiyongcode");
			shoufeifangshicode = basices.rs.getString("shoufeifangshicode");
			shoufeiniandu = basices.rs.getString("shoufeiniandu");
			shoufeinianyue = basices.rs.getString("shoufeinianyue");
			shoufeijiner = basices.rs.getString("shoufeijiner");
			jiaofeizhuangtai = basices.rs.getString("jiaofeizhuangtai");
			shoufeishijian = basices.rs.getString("shoufeishijian");
			weijiaofeiliyou = basices.rs.getString("weijiaofeiliyou");
			other = basices.rs.getString("other");
			other2 = basices.rs.getString("other2");
			other3 = basices.rs.getString("other3");
			other4 = basices.rs.getString("other4");

		}
	}

	
//查询所有
public void selectallShouFei() throws Exception{
	
	basices = new BasicDatabase();
	basices.init();
	basices.conn=basices.getMysqlConnection();
	
	basices.rs = basices.executeSql("select","select * from  shoufei order by shoufeibiaoid desc ",null,null,null,null);
	
	listLWdetail = new ArrayList();
	while (basices.rs.next()){
		ShouFei tempLunwen = new ShouFei();
		tempLunwen.shoufeibiaoid = basices.rs.getString("shoufeibiaoid");
		tempLunwen.yzshenfenzheng = basices.rs.getString("yzshenfenzheng");
		tempLunwen.yzname = basices.rs.getString("yzname");
		tempLunwen.feiyongcode = basices.rs.getString("feiyongcode");
		tempLunwen.shoufeifangshicode = basices.rs.getString("shoufeifangshicode");
		tempLunwen.shoufeiniandu = basices.rs.getString("shoufeiniandu");
		tempLunwen.shoufeinianyue = basices.rs.getString("shoufeinianyue");
		tempLunwen.shoufeijiner = basices.rs.getString("shoufeijiner");
		tempLunwen.jiaofeizhuangtai = basices.rs.getString("jiaofeizhuangtai");
		tempLunwen.shoufeishijian = basices.rs.getString("shoufeishijian");
		tempLunwen.weijiaofeiliyou = basices.rs.getString("weijiaofeiliyou");
		tempLunwen.other = basices.rs.getString("other");
		tempLunwen.other2 = basices.rs.getString("other2");
		tempLunwen.other3 = basices.rs.getString("other3");
		tempLunwen.other4 = basices.rs.getString("other4");
		listLWdetail.add(tempLunwen);
	}
	
	if(basices.conn!=null){
		basices.conn.close();
	}
}


//查询所有论文
public void selectPartShouFei(String _pegasStart,String _onePageCount) throws Exception{

	
	basices = new BasicDatabase();
	basices.init();
	basices.conn=basices.getMysqlConnection();
	basices.rs = basices.executeSql("select","select * from  shoufei order by shoufeibiaoid desc limit "+_pegasStart+","+_onePageCount,null,null,null,null);
	listLWdetail = new ArrayList();
	while (basices.rs.next()){
		ShouFei tempLunwen = new ShouFei();
		tempLunwen.shoufeibiaoid = basices.rs.getString("shoufeibiaoid");
		tempLunwen.yzshenfenzheng = basices.rs.getString("yzshenfenzheng");
		tempLunwen.yzname = basices.rs.getString("yzname");
		tempLunwen.feiyongcode = basices.rs.getString("feiyongcode");
		tempLunwen.shoufeifangshicode = basices.rs.getString("shoufeifangshicode");
		tempLunwen.shoufeiniandu = basices.rs.getString("shoufeiniandu");
		tempLunwen.shoufeinianyue = basices.rs.getString("shoufeinianyue");
		tempLunwen.shoufeijiner = basices.rs.getString("shoufeijiner");
		tempLunwen.jiaofeizhuangtai = basices.rs.getString("jiaofeizhuangtai");
		tempLunwen.shoufeishijian = basices.rs.getString("shoufeishijian");
		tempLunwen.weijiaofeiliyou = basices.rs.getString("weijiaofeiliyou");
		tempLunwen.other = basices.rs.getString("other");
		tempLunwen.other2 = basices.rs.getString("other2");
		tempLunwen.other3 = basices.rs.getString("other3");
		tempLunwen.other4 = basices.rs.getString("other4");
		listLWdetail.add(tempLunwen);
	}
	
	if(basices.conn!=null){
		basices.conn.close();
	}
}

//查询所有论文2
public void selectPartShouFei2(String _pegasStart,String _onePageCount,String _yzshenfenzheng,String _yzname,String _feiyongcode,String _shoufeifangshicode,String _shoufeiniandu,String _shoufeinianyue,String _jiaofeizhuangtai) throws Exception{

	
	basices = new BasicDatabase();
	basices.init();
	basices.conn=basices.getMysqlConnection();
	
	StringBuffer bf = new StringBuffer();
	bf.append("select * from  shoufei ");
	ArrayList keyList = new ArrayList();
	ArrayList valueList = new ArrayList();
	if(!"".equals(_yzshenfenzheng)){
		keyList.add("yzshenfenzheng");
		valueList.add(_yzshenfenzheng);
	}
	if(!"".equals(_yzname)){
		keyList.add("yzname");
		valueList.add(_yzname);
	}
	if(!"".equals(_feiyongcode)){
		keyList.add("feiyongcode");
		valueList.add(_feiyongcode);
	}
	if(!"".equals(_shoufeifangshicode)){
		keyList.add("shoufeifangshicode");
		valueList.add(_shoufeifangshicode);
	}
	if(!"".equals(_shoufeiniandu)){
		keyList.add("shoufeiniandu");
		valueList.add(_shoufeiniandu);
	}
	if(!"".equals(_shoufeinianyue)){
		keyList.add("shoufeinianyue");
		valueList.add(_shoufeinianyue);
	}
	if(!"".equals(_jiaofeizhuangtai)){
		keyList.add("jiaofeizhuangtai");
		valueList.add(_jiaofeizhuangtai);
	}

	for(int i=0;i<keyList.size();i++){
		if(i==0){
			bf.append(" where ");
			bf.append(keyList.get(i));
			bf.append(" like '%");
			bf.append(valueList.get(i));
			bf.append("%' ");
		}else{
			bf.append(" and ");
			bf.append(keyList.get(i));
			bf.append(" like '%");
			bf.append(valueList.get(i));
			bf.append("%' ");
		}
	}
	
	bf.append(" order by shoufeibiaoid desc limit "+_pegasStart+","+_onePageCount);
	basices.rs = basices.executeSql("select",bf.toString(),null,null,null,null);
	listLWdetail = new ArrayList();
	while (basices.rs.next()){
		ShouFei tempLunwen = new ShouFei();
		tempLunwen.shoufeibiaoid = basices.rs.getString("shoufeibiaoid");
		tempLunwen.yzshenfenzheng = basices.rs.getString("yzshenfenzheng");
		tempLunwen.yzname = basices.rs.getString("yzname");
		tempLunwen.feiyongcode = basices.rs.getString("feiyongcode");
		tempLunwen.shoufeifangshicode = basices.rs.getString("shoufeifangshicode");
		tempLunwen.shoufeiniandu = basices.rs.getString("shoufeiniandu");
		tempLunwen.shoufeinianyue = basices.rs.getString("shoufeinianyue");
		tempLunwen.shoufeijiner = basices.rs.getString("shoufeijiner");
		tempLunwen.jiaofeizhuangtai = basices.rs.getString("jiaofeizhuangtai");
		tempLunwen.shoufeishijian = basices.rs.getString("shoufeishijian");
		tempLunwen.weijiaofeiliyou = basices.rs.getString("weijiaofeiliyou");
		tempLunwen.other = basices.rs.getString("other");
		tempLunwen.other2 = basices.rs.getString("other2");
		tempLunwen.other3 = basices.rs.getString("other3");
		tempLunwen.other4 = basices.rs.getString("other4");
		listLWdetail.add(tempLunwen);
	}
	
	if(basices.conn!=null){
		basices.conn.close();
	}
}

//修改 by yzid
public int updateShouFei(ShouFei _lunwen) throws Exception{
	basices = new BasicDatabase();
	basices.init();
	basices.conn=basices.getMysqlConnection();
	
	StringBuffer buffer = new StringBuffer();
	BasicHtmlUtil bsUtil = new BasicHtmlUtil();
	buffer.append("update shoufei set feiyongcode='"+bsUtil.replaceString(_lunwen.feiyongcode)
			+"',shoufeifangshicode='"+bsUtil.replaceString(_lunwen.shoufeifangshicode)
			+"',shoufeiniandu='"+bsUtil.replaceString(_lunwen.shoufeiniandu)
			+"',shoufeinianyue='"+bsUtil.replaceString(_lunwen.shoufeinianyue)
			+"',shoufeijiner='"+bsUtil.replaceString(_lunwen.shoufeijiner)
			+"',jiaofeizhuangtai='"+bsUtil.replaceString(_lunwen.jiaofeizhuangtai)
			+"',shoufeishijian='"+bsUtil.replaceString(_lunwen.shoufeishijian)
			+"',weijiaofeiliyou='"+bsUtil.replaceString(_lunwen.weijiaofeiliyou)
			+"',other='"+bsUtil.replaceString(_lunwen.other)
			//+"',other2='"+bsUtil.replaceString(_lunwen.other2)
			+"',other3='"+bsUtil.replaceString(_lunwen.other3)+"',other4='"
			+bsUtil.replaceString(_lunwen.other4)+"' where shoufeibiaoid='"+_lunwen.shoufeibiaoid+"'");
	System.out.println(buffer.toString());
	basices.executeSql("update",buffer.toString(),null,null,null,null);
	
	if(basices.conn!=null){
		basices.conn.close();
	}
	
	return basices.executeCount;
}
//插入,按照yzid
public int insertShouFei(ShouFei _lunwen) throws Exception{
	basices = new BasicDatabase();
	basices.init();
	basices.conn=basices.getMysqlConnection();
	
	StringBuffer buffer = new StringBuffer();
	BasicHtmlUtil bsUtil = new BasicHtmlUtil();
	buffer.append("insert into  shoufei (yzshenfenzheng,yzname,feiyongcode,shoufeifangshicode,shoufeiniandu,"
			+"shoufeinianyue,shoufeijiner,jiaofeizhuangtai,shoufeishijian,weijiaofeiliyou,other,other2,other3,other4) values ('"
			+bsUtil.replaceString(_lunwen.yzshenfenzheng)+"','"+bsUtil.replaceString(_lunwen.yzname)
			+"','"+bsUtil.replaceString(_lunwen.feiyongcode)+"','");
	buffer.append(bsUtil.replaceString(_lunwen.shoufeifangshicode)
			+"','"+bsUtil.replaceString(_lunwen.shoufeiniandu)
			+"','"+bsUtil.replaceString(_lunwen.shoufeinianyue)
			+"','"+bsUtil.replaceString(_lunwen.shoufeijiner)
			+"','"+bsUtil.replaceString(_lunwen.jiaofeizhuangtai)
			+"','"+bsUtil.replaceString(_lunwen.shoufeishijian)
			+"','"+bsUtil.replaceString(_lunwen.weijiaofeiliyou)
			+"','"+bsUtil.replaceString(_lunwen.other)+"','"+bsUtil.replaceString(_lunwen.other2)+"','"+bsUtil.replaceString(_lunwen.other3)+"','"+bsUtil.replaceString(_lunwen.other4)+"')");
	System.out.println("插入沦文sql:"+buffer.toString()+"**");
	basices.executeSql("insert",buffer.toString(),null,null,null,null);
	
	if(basices.conn!=null){
		basices.conn.close();
	}
	return basices.executeCount;
}
//删除yezhu,按照yzid
public int deleteShouFei(ShouFei _lunwen) throws Exception{
	basices = new BasicDatabase();
	basices.init();
	basices.conn=basices.getMysqlConnection();
	
	StringBuffer buffer = new StringBuffer();
	buffer.append("delete from shoufei where shoufeibiaoid='"+_lunwen.shoufeibiaoid+"'");
	basices.executeSql("delete",buffer.toString(),null,null,null,null);
	
	if(basices.conn!=null){
		basices.conn.close();
	}
	
	return basices.executeCount;
}


public static String fillInRight(String data, char fill, int len) {
	if (len == 0)
		return data;
	if (data == null)
		data = "";
	if (data.getBytes().length > len)
		return data;
	int diffLen = len - data.getBytes().length;
	StringBuffer fmtStr = new StringBuffer(data);
	for (int i = 0; i < diffLen; i++)
		fmtStr.append(fill);
	return fmtStr.toString();
}
//对象文字按照java的length计算。非半角字节数
public static String fillInRight2(String data, char fill, int len) {
	if (len == 0)
		return data;
	if (data == null)
		data = "";
	if (data.length() > len)
		return data;
	int diffLen = len - data.length();
	StringBuffer fmtStr = new StringBuffer(data);
	for (int i = 0; i < diffLen; i++)
		fmtStr.append(fill);
	return fmtStr.toString();
}

}
