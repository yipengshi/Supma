package life.wuye.bean;

import java.util.*;
import java.sql.*;

import yao.yao.basic.*;


/**
 * <p>
 * Title: HTML��ص�������ʽ������
 * </p>
 * <p>
 * Description: ��������HTML��ǣ�ת��HTML��ǣ��滻�ض�HTML���
 * </p>
 * <p>
 * Copyright: Copyright (c) 2006
 * </p>
 * 
 * @author hejian
 * @version 1.0
 * @createtime 2006-10-16
 */

public class RenYuan {

public String ryid="";
public String rypassword="";
public String ryname="";
public String ryshenfenzheng="";
public String ryshouji="";
public String ryruzhishijian="";
public String rylizhishijian="";
public String ryzhiwu="";
public String ryzaizhi="";

public String ryzhuzhi = "";
public String other = "";
public String other2 = "";

public String other3="";
public String other4="";

public BasicDatabase basices = null;
public ArrayList listLWdetail = null;
public LinkedHashMap zhiWuMap =null;
public LinkedHashMap zhuangTaiMap =null;

//Ա��״̬��ֵ�����ݼ٣���ְ��
public void setZhuangTaiMap(){
	zhuangTaiMap = new LinkedHashMap();
	zhuangTaiMap.put("","    ");
	zhuangTaiMap.put("1","������ְ");
	zhuangTaiMap.put("2","���ڲ���");
	zhuangTaiMap.put("3","ͣн��ְ");
	zhuangTaiMap.put("4","��ְ");
	zhuangTaiMap.put("5","����");
}

//Ա��ְ���
public void setZhiWuMap(){
	zhiWuMap = new LinkedHashMap();
	zhiWuMap.put("","    ");
	zhiWuMap.put("1","��ҵ����");
	zhiWuMap.put("2","��ҵ������");
	zhiWuMap.put("3","��ҵ����");
	zhiWuMap.put("4","����");
	zhiWuMap.put("5","�繤");
	zhiWuMap.put("6","����");
	zhiWuMap.put("7","԰��");
	zhiWuMap.put("8","����");
}
public int allCount=0;

	public RenYuan() {
		setZhuangTaiMap();
		setZhiWuMap();
	}

//	��ѯ��������
	public int selectallRenYuan_count() throws Exception{
		
		basices = new BasicDatabase();
		basices.init();
		basices.conn=basices.getMysqlConnection();
		
		ResultSet res1 = basices.executeSql("select","select count(*) as ALLROWS from wuyerenyuan ",null,null,null,null);

		if (res1.next()){

			return Integer.parseInt(basices.rs.getString("ALLROWS"));
		}
		
		if(basices.conn!=null){
			basices.conn.close();
		}
		
		return 0;
	}
	
//	��ѯ��������2(������ѯ)
	public int selectallRenYuan_count2(String _ryname,String _ryruzhishijian,String _rylizhishijian,String _ryzhiwu,String _ryzaizhi) throws Exception{
		
		basices = new BasicDatabase();
		basices.init();
		basices.conn=basices.getMysqlConnection();
		
		StringBuffer bf = new StringBuffer();
		bf.append("select count(*) as ALLROWS from  wuyerenyuan ");
		ArrayList keyList = new ArrayList();
		ArrayList valueList = new ArrayList();
		if(!"".equals(_ryname)){
			keyList.add("ryname");
			valueList.add(_ryname);
		}
		if(!"".equals(_ryruzhishijian)){
			keyList.add("ryruzhishijian");
			valueList.add(_ryruzhishijian);
		}
		if(!"".equals(_rylizhishijian)){
			keyList.add("rylizhishijian");
			valueList.add(_rylizhishijian);
		}
		if(!"".equals(_ryzhiwu)){
			keyList.add("ryzhiwu");
			valueList.add(_ryzhiwu);
		}
		if(!"".equals(_ryzaizhi)){
			keyList.add("ryzaizhi");
			valueList.add(_ryzaizhi);
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

	


	
//	��ѯ1������ by id
	public void selectOneRenYuan(String _s) throws Exception{
		basices = new BasicDatabase();
		basices.init();
		basices.conn=basices.getMysqlConnection();
		ResultSet res1 = basices.executeSql("select","select * from  wuyerenyuan where ryid='"+_s+"' ",null,null,null,null);
		while (res1.next()){
			
			ryid = basices.rs.getString("ryid");
			rypassword = basices.rs.getString("rypassword");
			ryname = basices.rs.getString("ryname");
			ryshenfenzheng = basices.rs.getString("ryshenfenzheng");
			ryshouji = basices.rs.getString("ryshouji");
			ryruzhishijian = basices.rs.getString("ryruzhishijian");
			rylizhishijian = basices.rs.getString("rylizhishijian");
			ryzhiwu = basices.rs.getString("ryzhiwu");
			ryzaizhi = basices.rs.getString("ryzaizhi");
			ryzhuzhi = basices.rs.getString("ryzhuzhi");
			other = basices.rs.getString("other");
			other2 = basices.rs.getString("other2");
			other3 = basices.rs.getString("other3");
			other4 = basices.rs.getString("other4");

		}
	}

	
//��ѯ����
public void selectallRenYuan() throws Exception{
	
	basices = new BasicDatabase();
	basices.init();
	basices.conn=basices.getMysqlConnection();
	
	basices.rs = basices.executeSql("select","select * from  wuyerenyuan order by ryid desc ",null,null,null,null);
	
	listLWdetail = new ArrayList();
	while (basices.rs.next()){
		RenYuan tempLunwen = new RenYuan();
		tempLunwen.ryid = basices.rs.getString("ryid");
		tempLunwen.rypassword = basices.rs.getString("rypassword");
		tempLunwen.ryname = basices.rs.getString("ryname");
		tempLunwen.ryshenfenzheng = basices.rs.getString("ryshenfenzheng");
		tempLunwen.ryshouji = basices.rs.getString("ryshouji");
		tempLunwen.ryruzhishijian = basices.rs.getString("ryruzhishijian");
		tempLunwen.rylizhishijian = basices.rs.getString("rylizhishijian");
		tempLunwen.ryzhiwu = basices.rs.getString("ryzhiwu");
		tempLunwen.ryzaizhi = basices.rs.getString("ryzaizhi");
		tempLunwen.ryzhuzhi = basices.rs.getString("ryzhuzhi");
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


//��ѯ��������
public void selectPartRenYuan(String _pegasStart,String _onePageCount) throws Exception{

	
	basices = new BasicDatabase();
	basices.init();
	basices.conn=basices.getMysqlConnection();
	
	basices.rs = basices.executeSql("select","select * from  wuyerenyuan order by ryid desc limit "+_pegasStart+","+_onePageCount,null,null,null,null);
	
	listLWdetail = new ArrayList();
	while (basices.rs.next()){
		RenYuan tempLunwen = new RenYuan();
		tempLunwen.ryid = basices.rs.getString("ryid");
		tempLunwen.rypassword = basices.rs.getString("rypassword");
		tempLunwen.ryname = basices.rs.getString("ryname");
		tempLunwen.ryshenfenzheng = basices.rs.getString("ryshenfenzheng");
		tempLunwen.ryshouji = basices.rs.getString("ryshouji");
		tempLunwen.ryruzhishijian = basices.rs.getString("ryruzhishijian");
		tempLunwen.rylizhishijian = basices.rs.getString("rylizhishijian");
		tempLunwen.ryzhiwu = basices.rs.getString("ryzhiwu");
		tempLunwen.ryzaizhi = basices.rs.getString("ryzaizhi");
		tempLunwen.ryzhuzhi = basices.rs.getString("ryzhuzhi");
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

//��ѯ��������2
public void selectPartRenYuan2(String _pegasStart,String _onePageCount,String _ryname,String _ryruzhishijian,String _rylizhishijian,String _ryzhiwu,String _ryzaizhi) throws Exception{

	
	basices = new BasicDatabase();
	basices.init();
	basices.conn=basices.getMysqlConnection();
	
	StringBuffer bf = new StringBuffer();
	bf.append("select * from  wuyerenyuan ");
	ArrayList keyList = new ArrayList();
	ArrayList valueList = new ArrayList();
	if(!"".equals(_ryname)){
		keyList.add("ryname");
		valueList.add(_ryname);
	}
	if(!"".equals(_ryruzhishijian)){
		keyList.add("ryruzhishijian");
		valueList.add(_ryruzhishijian);
	}
	if(!"".equals(_rylizhishijian)){
		keyList.add("rylizhishijian");
		valueList.add(_rylizhishijian);
	}
	if(!"".equals(_ryzhiwu)){
		keyList.add("ryzhiwu");
		valueList.add(_ryzhiwu);
	}
	if(!"".equals(_ryzaizhi)){
		keyList.add("ryzaizhi");
		valueList.add(_ryzaizhi);
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
	
	bf.append(" order by ryid desc limit "+_pegasStart+","+_onePageCount);
	basices.rs = basices.executeSql("select",bf.toString(),null,null,null,null);
	
	listLWdetail = new ArrayList();
	while (basices.rs.next()){
		RenYuan tempLunwen = new RenYuan();
		tempLunwen.ryid = basices.rs.getString("ryid");
		tempLunwen.rypassword = basices.rs.getString("rypassword");
		tempLunwen.ryname = basices.rs.getString("ryname");
		tempLunwen.ryshenfenzheng = basices.rs.getString("ryshenfenzheng");
		tempLunwen.ryshouji = basices.rs.getString("ryshouji");
		tempLunwen.ryruzhishijian = basices.rs.getString("ryruzhishijian");
		tempLunwen.rylizhishijian = basices.rs.getString("rylizhishijian");
		tempLunwen.ryzhiwu = basices.rs.getString("ryzhiwu");
		tempLunwen.ryzaizhi = basices.rs.getString("ryzaizhi");
		tempLunwen.ryzhuzhi = basices.rs.getString("ryzhuzhi");
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

//�޸� by yzid
public int updateRenYuan(RenYuan _lunwen) throws Exception{
	basices = new BasicDatabase();
	basices.init();
	basices.conn=basices.getMysqlConnection();
	
	StringBuffer buffer = new StringBuffer();
	BasicHtmlUtil bsUtil = new BasicHtmlUtil();
	buffer.append("update wuyerenyuan set rypassword='"+bsUtil.replaceString(_lunwen.rypassword)+"',ryname='"+bsUtil.replaceString(_lunwen.ryname)+"',ryshenfenzheng='"
			+bsUtil.replaceString(_lunwen.ryshenfenzheng)+"',ryshouji='"+bsUtil.replaceString(_lunwen.ryshouji));
	buffer.append("',ryruzhishijian='"+bsUtil.replaceString(_lunwen.ryruzhishijian)+"',rylizhishijian='"+bsUtil.replaceString(_lunwen.rylizhishijian)+"',ryzhiwu='"+bsUtil.replaceString(_lunwen.ryzhiwu)
			+"',ryzaizhi='"+bsUtil.replaceString(_lunwen.ryzaizhi)+"',ryzhuzhi='"+bsUtil.replaceString(_lunwen.ryzhuzhi)
			+"',other='"+bsUtil.replaceString(_lunwen.other)+"',other2='"+bsUtil.replaceString(_lunwen.other2)+"',other3='"+bsUtil.replaceString(_lunwen.other3)+"',other4='"+bsUtil.replaceString(_lunwen.other4)+"' where ryid='"+_lunwen.ryid+"'");
	//System.out.println(buffer.toString());
	basices.executeSql("update",buffer.toString(),null,null,null,null);
	
	if(basices.conn!=null){
		basices.conn.close();
	}
	
	return basices.executeCount;
}

//ɾ��yezhu,����yzid
public int deleteYeZhu(YeZhu _lunwen) throws Exception{
	basices = new BasicDatabase();
	basices.init();
	basices.conn=basices.getMysqlConnection();
	
	StringBuffer buffer = new StringBuffer();
	buffer.append("delete from wuyerenyuan where ryid='"+_lunwen.yzid+"'");
	basices.executeSql("delete",buffer.toString(),null,null,null,null);
	
	if(basices.conn!=null){
		basices.conn.close();
	}
	
	return basices.executeCount;
}


//����,����yzid
public int insertRenYuan(RenYuan _lunwen) throws Exception{
	basices = new BasicDatabase();
	basices.init();
	basices.conn=basices.getMysqlConnection();
	
	StringBuffer buffer = new StringBuffer();
	BasicHtmlUtil bsUtil = new BasicHtmlUtil();
	buffer.append("insert into  wuyerenyuan (rypassword,ryname,ryshenfenzheng,ryshouji,ryruzhishijian,rylizhishijian,ryzhiwu,ryzaizhi,ryzhuzhi,other,other2,other3,other4) values ('"
			+bsUtil.replaceString(_lunwen.rypassword)+"','"+bsUtil.replaceString(_lunwen.ryname)+"','"+bsUtil.replaceString(_lunwen.ryshenfenzheng)+"','");
	buffer.append(bsUtil.replaceString(_lunwen.ryshouji)+"','"+bsUtil.replaceString(_lunwen.ryruzhishijian)+"','"+bsUtil.replaceString(_lunwen.rylizhishijian)+"','"+bsUtil.replaceString(_lunwen.ryzhiwu)+"','"
			+bsUtil.replaceString(_lunwen.ryzaizhi)+"','"+bsUtil.replaceString(_lunwen.ryzhuzhi)+"','"+bsUtil.replaceString(_lunwen.other)+"','"+bsUtil.replaceString(_lunwen.other2)+"','"+bsUtil.replaceString(_lunwen.other3)+"','"+bsUtil.replaceString(_lunwen.other4)+"')");
	System.out.println("��������sql:"+buffer.toString()+"**");
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
//�������ְ���java��length���㡣�ǰ���ֽ���
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
