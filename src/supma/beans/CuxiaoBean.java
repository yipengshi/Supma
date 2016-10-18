package supma.beans;


import java.io.Serializable;
import java.lang.*;
import java.util.*;
import java.sql.*;

import supma.common.*;
import supma.db.*;

public class CuxiaoBean implements Serializable
{

	private static final long serialVersionUID = -7764448576471396742L;
	/**tablename*/
    private static String tableName    ="cuxiaotab";
	/**促销id*/
    private String cuxiao_id    ="";
	/**促销名*/
    private String cuxiao_name    ="";
	/**促销概要*/
    private String cuxiao_gaiyao    ="";
	/**促销详细*/
    private String cuxiao_xiangxi    ="";
	/**促销说明*/
    private String cuxiao_shuoming    ="";
	/**促销开始时间*/
    private String cuxiao_date_start    ="";
	/**促销结束时间*/
    private String cuxiao_date_end    ="";
	/**销是否有效*/
    private String cuxiao_youxiao    ="";
	/**未用1*/
    private String cuxiao_notuse1    ="";
	/**未用2*/
    private String cuxiao_notuse2    ="";
	/**未用3*/
    private String cuxiao_notuse3   ="";
    
    
    //查询所有有效的促销活动
    public    ArrayList<CuxiaoBean> getCuXiaoList (userBean ufo) throws Exception{
        ArrayList <CuxiaoBean>list = new ArrayList<CuxiaoBean>();
  	  String sql ="select * from "+tableName+" where cuxiao_youxiao='"+CommonKey.goods_flag_y+"' order by cuxiao_id ";
        BasicDatabase basic = new BasicDatabase();
        ResultSet rs= basic.executeSql("CuxiaoBean", "select", sql, null, null, tableName, null,null,ufo);
        while(rs.next()){
        	CuxiaoBean bean = new CuxiaoBean();
      	  	setGoodsBean(bean,rs);
        	list.add(bean);
        }
        rs.close();
  		if(basic.pstm !=null){
  			basic.pstm.close();
  			basic.pstm=null;
  		}
  		if(basic.conn != null){
  			basic.conn.close();
  			basic.conn=null;
  		}
  		return list;
    }  
    
    //数据库取出放到bean里(单条)
    public void setGoodsBean(CuxiaoBean bean,ResultSet rs) throws Exception{
  	  bean.cuxiao_id=rs.getString("cuxiao_id").trim();
  	  bean.cuxiao_name=rs.getString("cuxiao_name").trim();
  	  bean.cuxiao_gaiyao=rs.getString("cuxiao_gaiyao").trim();
  	  bean.cuxiao_xiangxi=rs.getString("cuxiao_xiangxi").trim();
  	  bean.cuxiao_shuoming=rs.getString("cuxiao_shuoming").trim();
  	  bean.cuxiao_date_start=rs.getString("cuxiao_date_start").trim();
  	  bean.cuxiao_date_end=rs.getString("cuxiao_date_end").trim();
  	  bean.cuxiao_youxiao=rs.getString("cuxiao_youxiao").trim();
  	  bean.cuxiao_notuse1=rs.getString("cuxiao_notuse1").trim();
  	  bean.cuxiao_notuse2=rs.getString("cuxiao_notuse2").trim();
  	  bean.cuxiao_notuse3=rs.getString("cuxiao_notuse3").trim();
    }
    
	/**
	 * @return cuxiao_youxiao
	 */
	public String getCuxiao_youxiao() {
		return cuxiao_youxiao;
	}



	/**
	 * @param cuxiao_youxiao the cuxiao_youxiao to set
	 */
	public void setCuxiao_youxiao(String cuxiao_youxiao) {
		this.cuxiao_youxiao = cuxiao_youxiao;
	}



	/**
	 * @return cuxiao_id
	 */
	public String getCuxiao_id() {
		return cuxiao_id;
	}
	/**
	 * @param cuxiao_id the cuxiao_id to set
	 */
	public void setCuxiao_id(String cuxiao_id) {
		this.cuxiao_id = cuxiao_id;
	}
	/**
	 * @return cuxiao_name
	 */
	public String getCuxiao_name() {
		return cuxiao_name;
	}
	/**
	 * @param cuxiao_name the cuxiao_name to set
	 */
	public void setCuxiao_name(String cuxiao_name) {
		this.cuxiao_name = cuxiao_name;
	}
	/**
	 * @return cuxiao_gaiyao
	 */
	public String getCuxiao_gaiyao() {
		return cuxiao_gaiyao;
	}
	/**
	 * @param cuxiao_gaiyao the cuxiao_gaiyao to set
	 */
	public void setCuxiao_gaiyao(String cuxiao_gaiyao) {
		this.cuxiao_gaiyao = cuxiao_gaiyao;
	}
	/**
	 * @return cuxiao_xiangxi
	 */
	public String getCuxiao_xiangxi() {
		return cuxiao_xiangxi;
	}
	/**
	 * @param cuxiao_xiangxi the cuxiao_xiangxi to set
	 */
	public void setCuxiao_xiangxi(String cuxiao_xiangxi) {
		this.cuxiao_xiangxi = cuxiao_xiangxi;
	}
	/**
	 * @return cuxiao_shuoming
	 */
	public String getCuxiao_shuoming() {
		return cuxiao_shuoming;
	}
	/**
	 * @param cuxiao_shuoming the cuxiao_shuoming to set
	 */
	public void setCuxiao_shuoming(String cuxiao_shuoming) {
		this.cuxiao_shuoming = cuxiao_shuoming;
	}
	/**
	 * @return cuxiao_date_start
	 */
	public String getCuxiao_date_start() {
		return cuxiao_date_start;
	}
	/**
	 * @param cuxiao_date_start the cuxiao_date_start to set
	 */
	public void setCuxiao_date_start(String cuxiao_date_start) {
		this.cuxiao_date_start = cuxiao_date_start;
	}
	/**
	 * @return cuxiao_date_end
	 */
	public String getCuxiao_date_end() {
		return cuxiao_date_end;
	}
	/**
	 * @param cuxiao_date_end the cuxiao_date_end to set
	 */
	public void setCuxiao_date_end(String cuxiao_date_end) {
		this.cuxiao_date_end = cuxiao_date_end;
	}
	/**
	 * @return cuxiao_notuse1
	 */
	public String getCuxiao_notuse1() {
		return cuxiao_notuse1;
	}
	/**
	 * @param cuxiao_notuse1 the cuxiao_notuse1 to set
	 */
	public void setCuxiao_notuse1(String cuxiao_notuse1) {
		this.cuxiao_notuse1 = cuxiao_notuse1;
	}
	/**
	 * @return cuxiao_notuse2
	 */
	public String getCuxiao_notuse2() {
		return cuxiao_notuse2;
	}
	/**
	 * @param cuxiao_notuse2 the cuxiao_notuse2 to set
	 */
	public void setCuxiao_notuse2(String cuxiao_notuse2) {
		this.cuxiao_notuse2 = cuxiao_notuse2;
	}
	/**
	 * @return cuxiao_notuse3
	 */
	public String getCuxiao_notuse3() {
		return cuxiao_notuse3;
	}
	/**
	 * @param cuxiao_notuse3 the cuxiao_notuse3 to set
	 */
	public void setCuxiao_notuse3(String cuxiao_notuse3) {
		this.cuxiao_notuse3 = cuxiao_notuse3;
	}
    
   
}
