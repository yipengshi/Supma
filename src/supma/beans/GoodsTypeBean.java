package supma.beans;


import java.io.Serializable;
import java.lang.*;
import java.util.*;
import java.sql.*;

import supma.common.CommonKey;
import supma.db.BasicDatabase;

public class GoodsTypeBean  implements Serializable
{


	private static final long serialVersionUID = -4542255897176705325L;

	String tableName = "goodsmastertab" ;
    
    public static String goods_type_level_1      ="1";        //level  
    public static String goods_type_level_2     ="2";        //level  
    public static String goods_type_level_3     ="3";        //level  
    
    public String goods_type_id      ="";        //商品id    
    public String goods_type_name     ="";        //商品类型名        
    public String goods_type_level     ="";        //商品分类等级        
    public String goods_type_up_level  ="";        //商品上层分类        
    public String goods_type_notuser1 ="";        //商品上层分类code
    public String goods_type_notuser2    ="";        //商品所属大类    
    public String goods_type_notuser3   ="";        //空位3    
    public String goods_type_notuser4     ="";        //空位4    
    public String goods_type_notuser5 ="";     //空位5    
  
    public LinkedHashMap<String,GoodsTypeBean> list1 = null;//1级商品分类
    public LinkedHashMap<String,GoodsTypeBean> list2 = null;//2级商品分类
    public LinkedHashMap<String,GoodsTypeBean> list3 = null;//3级商品分类
    
  PreparedStatement pre = null;
    
  //取得第三层小类所属的大类->大类里面的第三层小类
  //参数：大类
  public  ArrayList <GoodsTypeBean>getNo1GoodsTypeSql(String str1,userBean ufo) throws Exception{
      String sql = " select  *  from "+tableName+" where goods_type_notuser2= '"+str1.trim()+"' order by goods_type_id";
      BasicDatabase basic = new BasicDatabase();
      ResultSet rs= basic.executeSql("GoodsTypeBean.getNo1GoodsTypeSql", "select", sql, null, null, tableName, null,null,ufo);
      ArrayList <GoodsTypeBean> list = new ArrayList <GoodsTypeBean>();
      while(rs.next()){
    	  GoodsTypeBean bean = new GoodsTypeBean();
    	  setBeanDate(bean,rs);
    	  if(goods_type_level_3.equals(bean.goods_type_level)){
            	list.add(bean);
    	  }else{
    		  continue;
    	  }
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
  
  //取得所有商品分类
    public  String getAllGoodsTypeSql() throws Exception{
        String sql = " select  *  from "+tableName+" order by goods_type_level,goods_type_id";
        return sql;
    }
//    //用户名存在时候，验证密码是否正确
//    public  String getLoginSql() throws Exception{
//        String sql = " select  *  from "+tableName+" where user_islock='0' and user_id=?  and user_pwd=?";
//        return sql;
//    }
    public  void initGoodsTypeBean(ResultSet rs) throws Exception{
        if(rs!=null  ){
        	list1=new LinkedHashMap<String,GoodsTypeBean>();
        	list2=new LinkedHashMap<String,GoodsTypeBean>();
        	list3=new LinkedHashMap<String,GoodsTypeBean>();
        	while(rs.next()){
        		GoodsTypeBean goods = new GoodsTypeBean();
        		setBeanDate(goods,rs);
        		if(goods_type_level_1.equals(goods.goods_type_level)){//第一级别的类型放入
        			list1.put(goods.goods_type_id, goods);
        		}else if(goods_type_level_2.equals(goods.goods_type_level)){//第2级别的类型放入
        			list2.put(goods.goods_type_id, goods);//商品上层分类code为key
        		}else if(goods_type_level_3.equals(goods.goods_type_level)){//第3级别的类型放入
        			list3.put(goods.goods_type_id, goods);//商品上层分类code为key
        		}
        	}
        	 rs.close();
        }
    }
    
    public void setBeanDate(GoodsTypeBean bean,ResultSet rs) throws Exception{
    	bean.goods_type_id              =rs.getString("goods_type_id").trim();     
    	bean. goods_type_name             =rs.getString("goods_type_name").trim();            
    	bean.goods_type_level             =rs.getString("goods_type_level").trim();            
    	bean.goods_type_up_level          =rs.getString("goods_type_up_level").trim();         
    	bean.goods_type_notuser1         =rs.getString("goods_type_notuser1").trim();        
	 bean.goods_type_notuser2            =rs.getString("goods_type_notuser2").trim();           
	 bean.goods_type_notuser3           =rs.getString("goods_type_notuser3").trim();          
	 bean.goods_type_notuser4             =rs.getString("goods_type_notuser4").trim();            
	 bean.goods_type_notuser5             =rs.getString("goods_type_notuser5").trim();       	
    }
    
	/**
	 *返回生成商品类型联动下拉列表的josn数据
	 *@param 第一层数据
	 *@param 第二层数据
	 *@param 第三层数据
	 *@param userBean某个登录用户，用来取得此批发商用户所拥有的商品类型
	 */
	public static String getGoodsTypeJosn(LinkedHashMap<String,GoodsTypeBean> list1,LinkedHashMap<String,GoodsTypeBean> list2,LinkedHashMap<String,GoodsTypeBean> list3,userBean _ufo) {
		StringBuffer sb = new StringBuffer();
		if(list1==null||list2==null||list3==null||_ufo==null||_ufo.user_notuser1==null || "".equals(_ufo.user_notuser1)){
			return sb.toString();
		}
		//此用户可配送商品类型
		String[] userType = _ufo.user_notuser1.split(CommonKey.jinghao);
		//取得type1 list
		Iterator  <Map.Entry<String,GoodsTypeBean>> itertemp1 =	list1.entrySet().iterator();
		//包含本用户类型的第一层数据取得listtemp1
		LinkedHashMap<String,GoodsTypeBean> listtemp1 = new LinkedHashMap<String,GoodsTypeBean>();
		while (itertemp1.hasNext()) {
			Map.Entry <String,GoodsTypeBean>entry = (Map.Entry <String,GoodsTypeBean>) itertemp1.next();
			String key = (String)entry.getKey();
			for(int i=0;i<userType.length;i++){
				if(key.equals(userType[i])){
					listtemp1.put(key, entry.getValue());
					break;
				}
			}
		}
		Iterator  <Map.Entry<String,GoodsTypeBean>> iter =	listtemp1.entrySet().iterator();

		int i1=0;
		sb.append("{\"citylist\":[");
		while (iter.hasNext()) {
			Map.Entry <String,GoodsTypeBean>entry = (Map.Entry <String,GoodsTypeBean>) iter.next();
			String key = (String)entry.getKey();
			GoodsTypeBean val = (GoodsTypeBean)entry.getValue();
			sb.append("{\"p\":\""+key+"\",\"m\":\""+val.goods_type_name+"\",\"c\":[");
			Iterator <Map.Entry<String,GoodsTypeBean>>iter2 =list2.entrySet().iterator();
			int i2=0;
			while (iter2.hasNext()) {
				Map.Entry<String,GoodsTypeBean> entry2 = (Map.Entry<String,GoodsTypeBean>) iter2.next();
				String key2 = (String)entry2.getKey();
				GoodsTypeBean val2 = (GoodsTypeBean)entry2.getValue();	
				if(!val2.goods_type_notuser1.equals(key)){//第2层type的上层code不等于第1层的type code,这条忽略
					continue;
				}
				i2++;
				sb.append("{\"n\":\""+key2+"\",\"x\":\""+val2.goods_type_name+"\",\"a\":[");
				Iterator <Map.Entry<String,GoodsTypeBean>>iter3 =list3.entrySet().iterator();
				int i3 =0;
				while (iter3.hasNext()) {
					Map.Entry<String,GoodsTypeBean> entry3 = (Map.Entry<String,GoodsTypeBean>) iter3.next();
					String key3 = (String)entry3.getKey();
					GoodsTypeBean val3 = (GoodsTypeBean)entry3.getValue();	
					if(!val3.goods_type_notuser1.equals(val2.goods_type_id)){//第3层type的上层code不等于第2层的type code,这条忽略
						continue;
					}
					i3++;
					sb.append("{\"s\":\""+key3+"\",\"y\":\""+val3.goods_type_name+"\"},");
				}//end iter3
				//最后一个逗号去掉
				if(i3==0){
					sb.append("]},");
				}else{
					String tmp = sb.toString();
					sb = new StringBuffer();
					sb.append(tmp.substring(0, tmp.length()-1));	
					sb.append("]},");
				}
			}//end iter2
			//最后一个逗号去掉
			if(i2==0){
				sb.append("]},");
			}else{
				String tmp = sb.toString();
				sb = new StringBuffer();
				sb.append(tmp.substring(0, tmp.length()-1));	
				sb.append("]},");
			}
			
			i1++;
		}//end iter1
		if(i1==0){
			sb.append("]}");
		}else{
			String tmp = sb.toString();
			sb = new StringBuffer();
			sb.append(tmp.substring(0, tmp.length()-1));	
			sb.append("]}");
		}
		return sb.toString();
	}
///*
//        Vector v =  getResultSetData(selectRecord(sql));
//        if(v.size()==0)
//        {
//            return "-1";
//        }
//        else
//        {
//            return (String)((Hashtable)v.get(0)).get("id");
//        }*/
 //   }
    
//
//    //取得数据表中的某一条记录
//    public Vector getOneData() throws Exception
//    {
//        String sql = " select * from backuser where id= "+id;
//        return getResultSetData(selectRecord(sql));
//    }
//
//    public int add(Hashtable hash) throws Exception
//    {
//        String user  = ds.toString((String)hash.get("user"));
//        String pwd = ds.toString((String)hash.get("pwd"));
//        String info	= ds.toString((String)hash.get("info"));
//
//        Vector vect =new Vector();
//        vect.add("backuser");
//        vect.add(addVector("user",user,"CHAR"));
//        vect.add(addVector("pwd",pwd,"CHAR"));
//        vect.add(addVector("info",info,"CHAR"));
//
//        String sql = "select * from backuser where user='"+user+"'";
//        if(getResultSetData(selectRecord(sql)).size()!=0)
//        {
//            return 1;
//        }
//        return insertRecord(vect);
//    }
//
//    public int mod(Hashtable hash) throws Exception
//    {
//        String user  = ds.toString((String)hash.get("user"));
//        String pwd = ds.toString((String)hash.get("pwd"));
//        String info	= ds.toString((String)hash.get("info"));
//        String islock	= ds.toString((String)hash.get("islock"));
//
//        Vector vect =new Vector();
//        vect.add("backuser");
//        vect.add(addVector("user",user,"CHAR"));
//        vect.add(addVector("pwd",pwd,"CHAR"));
//        vect.add(addVector("info",info,"CHAR"));
//        vect.add(addVector("islock",islock,"CHAR"));
//        vect.add("ID="+id+"");
//        String sql = "select * from backuser where id!="+id+" and user='"+user+"'";
//        if(getResultSetData(selectRecord(sql)).size()!=0)
//        {
//            return 1;
//        }
//        return updateRecord(vect);
//    }
//
//    public void del()
//    {
//        deleteRecord("delete from backuser where id="+id);
//    }
//
//    public static void main(String args[]) throws Exception
//    {
//        userBean mb = new userBean();
//
//        //连接数据库
//        //mb.createConn("org.gjt.mm.mysql.Driver","jdbc:mysql://localhost:3306/yj","admin","admin");
//        mb.createConn(7,"localhost","3306","","hpj","admin","admin");
//
//        Hashtable hash= new Hashtable();
//        mb.setID("2");
//        hash.put("backuser", "admin");
//        hash.put("pwd", "pwd");
//        hash.put("info", "test");
//        //System.out.println(mb.add(hash));
//
//
//
//        //取得当前也的数据
//        System.out.println(mb.getCurPage(1,100));
//        //System.out.println(mb.login("admin","e"));
//
//        //取得记录数
//        //System.out.println(mb.getResultSetData(mb.selectRecord("select count(*) from snals")));
//
//        //释放连接
//        System.out.println("\r\n\r\nend");
//    }
}
