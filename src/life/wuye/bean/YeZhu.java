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

public class YeZhu {
	
public String yzid="";
public String yzpassword="";
public String yzname="";
public String yzshenfenzheng="";
public String yzshouji="";
public String yzshijian="";
public String yzlouhao="";
public String yzwuhao="";
public String yezhugongzuo="";

public String other = "";
public String other2 = "";

public String other3="";
public String other4="";

public BasicDatabase basices = null;
public ArrayList listLWdetail = null;
public HashMap leibieMap =null;
public HashMap qikanMap =null;

public int allCount=0;

	public YeZhu() {

	}

//	查询所有条数
	public int selectallYeZhu_count() throws Exception{
		
		basices = new BasicDatabase();
		basices.init();
		basices.conn=basices.getMysqlConnection();
		
		ResultSet res1 = basices.executeSql("select","select count(*) as ALLROWS from yezhu ",null,null,null,null);

		if (res1.next()){

			return Integer.parseInt(basices.rs.getString("ALLROWS"));
		}
		
		if(basices.conn!=null){
			basices.conn.close();
		}
		
		return 0;
	}
	
//	查询所有条数2(条件查询)
	public int selectallYeZhu_count2(String _yzname,String _yzlouhao,String _yzwuhao,String _yzshijian) throws Exception{
		
		basices = new BasicDatabase();
		basices.init();
		basices.conn=basices.getMysqlConnection();
		
		StringBuffer bf = new StringBuffer();
		bf.append("select count(*) as ALLROWS from  yezhu ");
		ArrayList keyList = new ArrayList();
		ArrayList valueList = new ArrayList();
		if(!"".equals(_yzname)){
			keyList.add("yzname");
			valueList.add(_yzname);
		}
		if(!"".equals(_yzlouhao)){
			keyList.add("yzlouhao");
			valueList.add(_yzlouhao);
		}
		if(!"".equals(_yzwuhao)){
			keyList.add("yzwuhao");
			valueList.add(_yzwuhao);
		}
		if(!"".equals(_yzshijian)){
			keyList.add("yzshijian");
			valueList.add(_yzshijian);
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
	public void selectOneYeZhu(String _s) throws Exception{
		basices = new BasicDatabase();
		basices.init();
		basices.conn=basices.getMysqlConnection();
		ResultSet res1 = basices.executeSql("select","select * from  yezhu where yzid='"+_s+"' ",null,null,null,null);
		while (res1.next()){
			
			yzid = basices.rs.getString("yzid");
			yzpassword = basices.rs.getString("yzpassword");
			yzname = basices.rs.getString("yzname");
			yzshenfenzheng = basices.rs.getString("yzshenfenzheng");
			yzshouji = basices.rs.getString("yzshouji");
			yzshijian = basices.rs.getString("yzshijian");
			yzlouhao = basices.rs.getString("yzlouhao");
			yzwuhao = basices.rs.getString("yzwuhao");
			yezhugongzuo = basices.rs.getString("yezhugongzuo");
			other = basices.rs.getString("other");
			other2 = basices.rs.getString("other2");
			other3 = basices.rs.getString("other3");
			other4 = basices.rs.getString("other4");

		}
	}
	
//查询所有
public void selectallLunWen() throws Exception{
	
	basices = new BasicDatabase();
	basices.init();
	basices.conn=basices.getMysqlConnection();
	
	basices.rs = basices.executeSql("select","select * from  yezhu order by yzid desc ",null,null,null,null);
	
	listLWdetail = new ArrayList();
	while (basices.rs.next()){
		YeZhu tempLunwen = new YeZhu();
		tempLunwen.yzid = basices.rs.getString("yzid");
		tempLunwen.yzpassword = basices.rs.getString("yzpassword");
		tempLunwen.yzname = basices.rs.getString("yzname");
		tempLunwen.yzshenfenzheng = basices.rs.getString("yzshenfenzheng");
		tempLunwen.yzshouji = basices.rs.getString("yzshouji");
		tempLunwen.yzshijian = basices.rs.getString("yzshijian");
		tempLunwen.yzlouhao = basices.rs.getString("yzlouhao");
		tempLunwen.yzwuhao = basices.rs.getString("yzwuhao");
		tempLunwen.yezhugongzuo = basices.rs.getString("yezhugongzuo");
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
public void selectPartYeZhu(String _pegasStart,String _onePageCount) throws Exception{

	
	basices = new BasicDatabase();
	basices.init();
	basices.conn=basices.getMysqlConnection();
	
	basices.rs = basices.executeSql("select","select * from  yezhu order by yzid desc limit "+_pegasStart+","+_onePageCount,null,null,null,null);
	
	listLWdetail = new ArrayList();
	while (basices.rs.next()){
		YeZhu tempLunwen = new YeZhu();
		tempLunwen.yzid = basices.rs.getString("yzid");
		tempLunwen.yzpassword = basices.rs.getString("yzpassword");
		tempLunwen.yzname = basices.rs.getString("yzname");
		tempLunwen.yzshenfenzheng = basices.rs.getString("yzshenfenzheng");
		tempLunwen.yzshouji = basices.rs.getString("yzshouji");
		tempLunwen.yzshijian = basices.rs.getString("yzshijian");
		tempLunwen.yzlouhao = basices.rs.getString("yzlouhao");
		tempLunwen.yzwuhao = basices.rs.getString("yzwuhao");
		tempLunwen.yezhugongzuo = basices.rs.getString("yezhugongzuo");
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
public void selectPartYeZhu2(String _pegasStart,String _onePageCount,String _yzname,String _yzlouhao,String _yzwuhao,String _yzshijian) throws Exception{

	
	basices = new BasicDatabase();
	basices.init();
	basices.conn=basices.getMysqlConnection();
	
	StringBuffer bf = new StringBuffer();
	bf.append("select * from  yezhu ");
	ArrayList keyList = new ArrayList();
	ArrayList valueList = new ArrayList();
	if(!"".equals(_yzname)){
		keyList.add("yzname");
		valueList.add(_yzname);
	}
	if(!"".equals(_yzlouhao)){
		keyList.add("yzlouhao");
		valueList.add(_yzlouhao);
	}
	if(!"".equals(_yzwuhao)){
		keyList.add("yzwuhao");
		valueList.add(_yzwuhao);
	}
	if(!"".equals(_yzshijian)){
		keyList.add("yzshijian");
		valueList.add(_yzshijian);
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
	
	bf.append(" order by yzid desc limit "+_pegasStart+","+_onePageCount);
	basices.rs = basices.executeSql("select",bf.toString(),null,null,null,null);
	
	listLWdetail = new ArrayList();
	while (basices.rs.next()){
		YeZhu tempLunwen = new YeZhu();
		tempLunwen.yzid = basices.rs.getString("yzid");
		tempLunwen.yzpassword = basices.rs.getString("yzpassword");
		tempLunwen.yzname = basices.rs.getString("yzname");
		tempLunwen.yzshenfenzheng = basices.rs.getString("yzshenfenzheng");
		tempLunwen.yzshouji = basices.rs.getString("yzshouji");
		tempLunwen.yzshijian = basices.rs.getString("yzshijian");
		tempLunwen.yzlouhao = basices.rs.getString("yzlouhao");
		tempLunwen.yzwuhao = basices.rs.getString("yzwuhao");
		tempLunwen.yezhugongzuo = basices.rs.getString("yezhugongzuo");
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
public int updateYeZhu(YeZhu _lunwen) throws Exception{
	basices = new BasicDatabase();
	basices.init();
	basices.conn=basices.getMysqlConnection();
	
	StringBuffer buffer = new StringBuffer();
	BasicHtmlUtil bsUtil = new BasicHtmlUtil();
	buffer.append("update yezhu set yzpassword='"+bsUtil.replaceString(_lunwen.yzpassword)+"',yzname='"+bsUtil.replaceString(_lunwen.yzname)+"',yzshenfenzheng='"
			+bsUtil.replaceString(_lunwen.yzshenfenzheng)+"',yzshouji='"+bsUtil.replaceString(_lunwen.yzshouji));
	buffer.append("',yzshijian='"+bsUtil.replaceString(_lunwen.yzshijian)+"',yzlouhao='"+bsUtil.replaceString(_lunwen.yzlouhao)+"',yzwuhao='"+bsUtil.replaceString(_lunwen.yzwuhao)
			+"',other='"+bsUtil.replaceString(_lunwen.other)+"',other2='"+bsUtil.replaceString(_lunwen.other2)+"',other3='"+bsUtil.replaceString(_lunwen.other3)+"',other4='"+bsUtil.replaceString(_lunwen.other4)+"' where yzid='"+_lunwen.yzid+"'");
	//System.out.println(buffer.toString());
	basices.executeSql("update",buffer.toString(),null,null,null,null);
	
	if(basices.conn!=null){
		basices.conn.close();
	}
	
	return basices.executeCount;
}

//删除yezhu,按照yzid
public int deleteYeZhu(YeZhu _lunwen) throws Exception{
	basices = new BasicDatabase();
	basices.init();
	basices.conn=basices.getMysqlConnection();
	
	StringBuffer buffer = new StringBuffer();
	buffer.append("delete from yezhu where yzid='"+_lunwen.yzid+"'");
	basices.executeSql("delete",buffer.toString(),null,null,null,null);
	
	if(basices.conn!=null){
		basices.conn.close();
	}
	
	return basices.executeCount;
}



//插入,按照yzid
public int insertYeZhu(YeZhu _lunwen) throws Exception{
	basices = new BasicDatabase();
	basices.init();
	basices.conn=basices.getMysqlConnection();
	
	StringBuffer buffer = new StringBuffer();
	BasicHtmlUtil bsUtil = new BasicHtmlUtil();
	buffer.append("insert into  yezhu (yzpassword,yzname,yzshenfenzheng,yzshouji,yzshijian,yzlouhao,yzwuhao,yezhugongzuo,other,other2,other3,other4) values ('"
			+bsUtil.replaceString(_lunwen.yzpassword)+"','"+bsUtil.replaceString(_lunwen.yzname)+"','"+bsUtil.replaceString(_lunwen.yzshenfenzheng)+"','");
	buffer.append(bsUtil.replaceString(_lunwen.yzshouji)+"','"+bsUtil.replaceString(_lunwen.yzshijian)+"','"+bsUtil.replaceString(_lunwen.yzlouhao)+"','"+bsUtil.replaceString(_lunwen.yzwuhao)+"','"
			+bsUtil.replaceString(_lunwen.yezhugongzuo)+"','"+bsUtil.replaceString(_lunwen.other)+"','"+bsUtil.replaceString(_lunwen.other2)+"','"+bsUtil.replaceString(_lunwen.other3)+"','"+bsUtil.replaceString(_lunwen.other4)+"')");
	System.out.println("插入沦文sql:"+buffer.toString()+"**");
	basices.executeSql("insert",buffer.toString(),null,null,null,null);
	
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
