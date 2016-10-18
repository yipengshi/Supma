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

public class GuanLiYuan {
	
public String admin_id="";
public String admin_name="";
public String admin_password="";
public String admin_logintime="";
public String admin_level="";

public String other = "";
public String other2 = "";

public String other3="";
public String other4="";

public BasicDatabase basices = null;
public ArrayList listLWdetail = null;
public LinkedHashMap jiBieMap =null;


//管理员级别
public void setJiBieMap(){
	jiBieMap = new LinkedHashMap();
	jiBieMap.put("2","普通管理员");
	jiBieMap.put("1","数据录入员");
	jiBieMap.put("0","高级管理员");
}


public int allCount=0;

	public GuanLiYuan() {
		setJiBieMap();
	}

//	查询所有条数
	public int selectallGuanLiYuan_count() throws Exception{
		
		basices = new BasicDatabase();
		basices.init();
		basices.conn=basices.getMysqlConnection();
		
		ResultSet res1 = basices.executeSql("select","select count(*) as ALLROWS from guanliyuanxinxibiao ",null,null,null,null);

		if (res1.next()){

			return Integer.parseInt(basices.rs.getString("ALLROWS"));
		}
		
		if(basices.conn!=null){
			basices.conn.close();
		}
		
		return 0;
	}
	
//	查询所有条数2(条件查询)
	public int selectallGuanLiYuan_count2(String _admin_id,String _admin_name,String _admin_level) throws Exception{
		
		basices = new BasicDatabase();
		basices.init();
		basices.conn=basices.getMysqlConnection();
		
		StringBuffer bf = new StringBuffer();
		bf.append("select count(*) as ALLROWS from  guanliyuanxinxibiao ");
		ArrayList keyList = new ArrayList();
		ArrayList valueList = new ArrayList();
		if(!"".equals(_admin_id)){
			keyList.add("admin_id");
			valueList.add(_admin_id);
		}
		if(!"".equals(_admin_name)){
			keyList.add("admin_name");
			valueList.add(_admin_name);
		}
		if(!"".equals(_admin_level)){
			keyList.add("admin_level");
			valueList.add(_admin_level);
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
	public void selectOneGuanLiYuan(String _s) throws Exception{
		basices = new BasicDatabase();
		basices.init();
		basices.conn=basices.getMysqlConnection();
		ResultSet res1 = basices.executeSql("select","select * from  guanliyuanxinxibiao where admin_id='"+_s+"' ",null,null,null,null);
		while (res1.next()){
			

			admin_id = basices.rs.getString("admin_id");
			admin_name = basices.rs.getString("admin_name");
			admin_password = basices.rs.getString("admin_password");
			admin_logintime = basices.rs.getString("admin_logintime");
			admin_level = basices.rs.getString("admin_level");
			other = basices.rs.getString("other");
			other2 = basices.rs.getString("other2");
			other3 = basices.rs.getString("other3");
			other4 = basices.rs.getString("other4");

		}
	}

	
//查询所有
public void selectallGuanLiYuan() throws Exception{
	
	basices = new BasicDatabase();
	basices.init();
	basices.conn=basices.getMysqlConnection();
	
	basices.rs = basices.executeSql("select","select * from  guanliyuanxinxibiao order by admin_id desc ",null,null,null,null);
	
	listLWdetail = new ArrayList();
	while (basices.rs.next()){
		GuanLiYuan tempLunwen = new GuanLiYuan();
		tempLunwen.admin_id = basices.rs.getString("admin_id");
		tempLunwen.admin_name = basices.rs.getString("admin_name");
		tempLunwen.admin_password = basices.rs.getString("admin_password");
		tempLunwen.admin_logintime = basices.rs.getString("admin_logintime");
		tempLunwen.admin_level = basices.rs.getString("admin_level");
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
public void selectPartGuanLiYuan(String _pegasStart,String _onePageCount) throws Exception{

	
	basices = new BasicDatabase();
	basices.init();
	basices.conn=basices.getMysqlConnection();
	
	basices.rs = basices.executeSql("select","select * from  guanliyuanxinxibiao order by admin_id desc limit "+_pegasStart+","+_onePageCount,null,null,null,null);
	
	listLWdetail = new ArrayList();
	while (basices.rs.next()){
		GuanLiYuan tempLunwen = new GuanLiYuan();
		tempLunwen.admin_id = basices.rs.getString("admin_id");
		tempLunwen.admin_name = basices.rs.getString("admin_name");
		tempLunwen.admin_password = basices.rs.getString("admin_password");
		tempLunwen.admin_logintime = basices.rs.getString("admin_logintime");
		tempLunwen.admin_level = basices.rs.getString("admin_level");
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
public void selectPartGuanLiYuan2(String _pegasStart,String _onePageCount,String _admin_id,String _admin_name,String _admin_level) throws Exception{

	
	basices = new BasicDatabase();
	basices.init();
	basices.conn=basices.getMysqlConnection();
	
	StringBuffer bf = new StringBuffer();
	bf.append("select * from  guanliyuanxinxibiao ");
	ArrayList keyList = new ArrayList();
	ArrayList valueList = new ArrayList();
	if(!"".equals(_admin_id)){
		keyList.add("admin_id");
		valueList.add(_admin_id);
	}
	if(!"".equals(_admin_name)){
		keyList.add("admin_name");
		valueList.add(_admin_name);
	}
	if(!"".equals(_admin_level)){
		keyList.add("admin_level");
		valueList.add(_admin_level);
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
	
	bf.append(" order by admin_id desc limit "+_pegasStart+","+_onePageCount);
	basices.rs = basices.executeSql("select",bf.toString(),null,null,null,null);
	listLWdetail = new ArrayList();
	while (basices.rs.next()){
		GuanLiYuan tempLunwen = new GuanLiYuan();
		tempLunwen.admin_id = basices.rs.getString("admin_id");
		tempLunwen.admin_name = basices.rs.getString("admin_name");
		tempLunwen.admin_password = basices.rs.getString("admin_password");
		tempLunwen.admin_logintime = basices.rs.getString("admin_logintime");
		tempLunwen.admin_level = basices.rs.getString("admin_level");
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
public int updateGuanLiYuan(GuanLiYuan _lunwen) throws Exception{
	basices = new BasicDatabase();
	basices.init();
	basices.conn=basices.getMysqlConnection();
	
	StringBuffer buffer = new StringBuffer();
	BasicHtmlUtil bsUtil = new BasicHtmlUtil();
	buffer.append("update guanliyuanxinxibiao set admin_name='"+bsUtil.replaceString(_lunwen.admin_name)+"',admin_level='"+bsUtil.replaceString(_lunwen.admin_level)
			+"',other='"+bsUtil.replaceString(_lunwen.other)+"',other3='"+bsUtil.replaceString(_lunwen.other3)+"',other4='"+bsUtil.replaceString(_lunwen.other4)+"' where admin_id='"+_lunwen.admin_id+"'");
	//System.out.println(buffer.toString());
	basices.executeSql("update",buffer.toString(),null,null,null,null);
	
	if(basices.conn!=null){
		basices.conn.close();
	}
	
	return basices.executeCount;
}

//change password
public int updatePassword(String _userid,String _password) throws Exception{
	basices = new BasicDatabase();
	basices.init();
	basices.conn=basices.getMysqlConnection();
	
	StringBuffer buffer = new StringBuffer();
	BasicHtmlUtil bsUtil = new BasicHtmlUtil();
	buffer.append("update guanliyuanxinxibiao set admin_password='"+bsUtil.replaceString(_password)+"' where admin_id='"+_userid+"'");
	//System.out.println(buffer.toString());
	basices.executeSql("update",buffer.toString(),null,null,null,null);
	
	if(basices.conn!=null){
		basices.conn.close();
	}
	
	return basices.executeCount;
}

//删除yezhu,按照yzid
public int deleteGuanLiYuan(GuanLiYuan _lunwen) throws Exception{
	basices = new BasicDatabase();
	basices.init();
	basices.conn=basices.getMysqlConnection();
	
	StringBuffer buffer = new StringBuffer();
	buffer.append("delete from guanliyuanxinxibiao where admin_id='"+_lunwen.admin_id+"'");
	basices.executeSql("delete",buffer.toString(),null,null,null,null);
	
	if(basices.conn!=null){
		basices.conn.close();
	}
	
	return basices.executeCount;
}


//插入,按照yzid
public int insertGuanLiYuan(GuanLiYuan _lunwen) throws Exception{
	basices = new BasicDatabase();
	basices.init();
	basices.conn=basices.getMysqlConnection();
	
	StringBuffer buffer = new StringBuffer();
	BasicHtmlUtil bsUtil = new BasicHtmlUtil();
	buffer.append("insert into  guanliyuanxinxibiao (admin_id,admin_name,admin_password,admin_logintime,admin_level,other,other2,other3,other4) values ('"
			+bsUtil.replaceString(_lunwen.admin_id)+"','"+bsUtil.replaceString(_lunwen.admin_name)+"','"+bsUtil.replaceString(_lunwen.admin_password)+"','");
	buffer.append(bsUtil.replaceString(_lunwen.admin_logintime)+"','"+bsUtil.replaceString(_lunwen.admin_level)+"','"
			+bsUtil.replaceString(_lunwen.other)+"','"+bsUtil.replaceString(_lunwen.other2)+"','"+bsUtil.replaceString(_lunwen.other3)+"','"+bsUtil.replaceString(_lunwen.other4)+"')");
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
