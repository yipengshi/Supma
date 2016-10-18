package supma.beans;


import java.io.Serializable;
import java.lang.*;
import java.util.*;
import java.sql.*;
import supma.common.*;
import supma.db.*;

public class userBean  implements Serializable
{
	private static final long serialVersionUID = 2183797744031438841L;
	public String isLogin = "0";//0:not login. 1:login 2:user被锁
    String tableName = "usertab" ;
    
    int bianliangcount=38;//这个数据库的字段个数，增加的时候，要修改这里
    public String user_id      ="";        //用户名      
    public String user_pwd     ="";        //密码        
    public String user_sex     ="";        //性别        
    public String user_tel_no  ="";        //号码        
    public String user_tel_no2 ="";        //座机号码    
    public String user_name    ="";        //用户名称    
    public String user_level   ="";        //用户等级    
    public String user_sco     ="";        //用户积分    
    public String user_regis_date ="";     //注册日期    
    public String user_last_login_date ="";//上次登录或者本次日期
    public String user_qq             =""; //QQ号        
    public String user_weixin         =""; //微信号      
    public String user_address1       =""; //超市的店/批发商的厂址/管理员的地址
    public String user_address2       =""; //超市所在片区/批发商可配送片区/管理员管辖片区     CommonKey.pianqu_id
    public String user_address3       =""; //地址3       
    public String user_address4       =""; //地址4       
    public String user_zip1           =""; //邮政编码1   
    public String user_zip2           =""; //邮政编码2   
    public String user_zip3           =""; //邮政编码3   
    public String user_zip4           =""; //邮政编码4   
    public String user_notuser1       =""; //批发商的商品种类:  goodsmastertab
    public String user_notuser2       =""; //超市营业执照图片     
    public String user_notuser3       =""; //空位3       
    public String user_notuser4       =""; //空位4       
    public String user_notuser5       =""; //空位5       
    public String user_notuser6       =""; //空位6       
    public String user_notuser7       =""; //空位7       
    public String user_notuser8       =""; //空位8       
    public String user_notuser9       =""; //空位9       
    public String user_notuser10      =""; //空位10      
    public String user_notuser11      =""; //user 上次或者本次登录ip
    public String user_photo            ="";//用户头像地址
    public String user_islock ="";//用户是否被锁定
    public String user_mail_address="";//用户邮箱
    public String user_question ="";//用户密码保护问题
    public String user_question_answer ="";//用户密码保护问题答案
    public String user_nicheng = "";//用户昵称
    public String user_type= "";//用户类型:1超市2.供货商3.管理员
    
    
  PreparedStatement pre = null;
    

//    public Vector getCurPage(int cur,int records)
//    {
//        return getOnePage("SELECT * FROM backuser",cur,records);
//    }
//
  
  //登录用户信息写入数据库,把本次登录时间，登录ip等内容写入数据库(update)
  public  String updateLoginUserSql() {
      String sql = " update  "+tableName+" set user_last_login_date=?,user_notuser11=?  where user_id=?";
      return sql;
  }
  
  //检查用户名是否存在
    public  String getLoginUserSql() {
        String sql = " select  *  from "+tableName+" where  user_id=? ";
        return sql;
    }
    //用户名存在时候，验证密码是否正确
    public  String getLoginSql() {
        String sql = " select  *  from "+tableName+" where  user_id=?  and user_pwd=?";
        return sql;
    }
    //检查信箱是否存在
    public  String getLoginMailSql() {
        String sql = " select  *  from "+tableName+" where  user_mail_address=? ";
        return sql;
    }
    //检查手机是否存在
    public  String getLoginMoboSql() {
        String sql = " select  *  from "+tableName+" where  user_tel_no=? ";
        return sql;
    }
    //login后初始化userbean
    public  void initUserBean(ResultSet rs) throws Exception{
        if(rs!=null && rs.next()){
        	setUserBean(this,rs);
        	 if(CommonKey.user_islock_0.equals(user_islock)){
            	 this.isLogin = "1";//正常用户
        	 }else if(CommonKey.user_islock_1.equals(user_islock)){
        		 this.isLogin = "2";//锁定用户
        	 }else if(CommonKey.user_islock_2.equals(user_islock)){
        		 this.isLogin = "3";//新注册待审核用户
        	 }
        	 rs.close();
        }
    }
  
    public void setUserBean(userBean bean,ResultSet rs) throws Exception{
    	bean.user_id              =rs.getString("user_id").trim();           
    	bean. user_pwd             =rs.getString("user_pwd").trim();            
    	bean.user_sex             =rs.getString("user_sex").trim();            
    	bean.user_tel_no          =rs.getString("user_tel_no").trim();         
    	bean.user_tel_no2         =rs.getString("user_tel_no2").trim();        
	 bean.user_name            =rs.getString("user_name").trim();           
	 bean.user_level           =rs.getString("user_level").trim();          
	 bean.user_sco             =rs.getString("user_sco").trim();            
	 bean.user_regis_date      =rs.getString("user_regis_date").trim();     
	 bean.user_last_login_date =rs.getString("user_last_login_date").trim();
	 bean.user_qq              =rs.getString("user_qq").trim();             
	 bean.user_weixin          =rs.getString("user_weixin").trim();         
	 bean.user_address1        =rs.getString("user_address1").trim();       
	 bean.user_address2        =rs.getString("user_address2").trim();       
	 bean.user_address3        =rs.getString("user_address3").trim();       
	 bean.user_address4        =rs.getString("user_address4").trim();       
	 bean.user_zip1            =rs.getString("user_zip1").trim();           
	 bean.user_zip2            =rs.getString("user_zip2").trim();           
	 bean.user_zip3            =rs.getString("user_zip3").trim();           
	 bean.user_zip4            =rs.getString("user_zip4").trim();           
	 bean.user_notuser1        =rs.getString("user_notuser1").trim();       
	 bean.user_notuser2        =rs.getString("user_notuser2").trim();       
	 bean.user_notuser3        =rs.getString("user_notuser3").trim();       
	 bean.user_notuser4        =rs.getString("user_notuser4").trim();       
	 bean.user_notuser5        =rs.getString("user_notuser5").trim();       
	 bean.user_notuser6        =rs.getString("user_notuser6").trim();       
	 bean.user_notuser7        =rs.getString("user_notuser7").trim();       
	 bean.user_notuser8        =rs.getString("user_notuser8").trim();       
	 bean.user_notuser9        =rs.getString("user_notuser9").trim();       
	 bean.user_notuser10       =rs.getString("user_notuser10").trim();      
	 bean.user_notuser11       =rs.getString("user_notuser11").trim();      
	 bean.user_photo           =rs.getString("user_photo").trim();          
	 bean.user_islock          =rs.getString("user_islock").trim();         
	 bean.user_mail_address    =rs.getString("user_mail_address").trim();   
	 bean.user_question        =rs.getString("user_question").trim();       
	 bean.user_question_answer =rs.getString("user_question_answer").trim();
	 bean.user_nicheng         =rs.getString("user_nicheng").trim(); 
	 bean.user_type         =rs.getString("user_type").trim(); 
    }

    //查询超市用户对应的可以供货的批发商，显示超市首页，根据批发商等级排序level 9最高
    // 参数1是超市用户所在片区,参数2表示是否全取。limit=true。就是取前xxx条
    public  ArrayList getPifaShangList(String str1,boolean limit,userBean ufo) throws Exception{
    	String fetchfirst = "";
    	String rowsonly = "";
    	String sql="";
    	String str1_notnull = "";
    	if(!"".equals(str1)){
    		str1_notnull = " and user_address2 like '%"+str1+"%'  ";
    	}
    	if(limit){
        	if(CommonKey.use_db.equals(CommonKey.db_db2)){
        		fetchfirst = " fetch first ";
        		rowsonly=" rows only ";
        		 sql = " select  *  from "+tableName+" where  user_type='"+CommonKey.user_type2+"'"+str1_notnull+" order by user_level,user_sco desc "+
        		 fetchfirst+CommonKey.index_show_pifashang+rowsonly;//按照level,和积分倒序。积分高的批发商在前面
        	}else if(CommonKey.use_db.equals(CommonKey.db_mysql)){
        		fetchfirst=" limit 1,"+CommonKey.index_show_pifashang;
        		sql = " select  *  from "+tableName+" where  user_type='"+CommonKey.user_type2+"'"+str1_notnull+" order by user_level,user_sco desc "+
       		 	fetchfirst;//按照level,和积分倒序。积分高的批发商在前面
        	}
    	}else{
    		sql = " select  *  from "+tableName+" where  user_type='"+CommonKey.user_type2+"'"+str1_notnull+" order by user_level,user_sco desc ";
    	}

        BasicDatabase basic = new BasicDatabase();
        ResultSet rs= basic.executeSql("userBean", "select", sql, null, null, tableName, null,null,ufo);
        ArrayList list = new ArrayList();
        while(rs.next()){
        	userBean bean = new userBean();
        	setUserBean(bean,rs);
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
    
    //查询超市用户对应的可以供货某种类别(第一层类别)商品的批发商，显示超市首页，根据批发商等级排序level 9最高
    // 参数1是超市用户所在片区,参数2是商品类别
    public  ArrayList getPifaShangListByGoodsType(String str1,String str2,userBean ufo) throws Exception{
    	String fetchfirst = "";
    	String rowsonly = "";
    	String sql="";
    	String str1_notnull = "";
    	if(!"".equals(str1)){
    		str1_notnull = " and user_address2 like '%"+str1+CommonKey.jinghao+"%'  ";
    	}
    	if(CommonKey.use_db.equals(CommonKey.db_db2)){
    		fetchfirst = " fetch first ";
    		rowsonly=" rows only ";
    		 sql = " select  *  from "+tableName+" where  user_type='"+CommonKey.user_type2+"'  "+str1_notnull+" and user_notuser1 like '%"+CommonKey.jinghao+str2+CommonKey.jinghao+"' order by user_level,user_sco desc "+
    		 fetchfirst+CommonKey.index_show_pifashang+rowsonly;//按照level,和积分倒序。积分高的批发商在前面
    	}else if(CommonKey.use_db.equals(CommonKey.db_mysql)){
    		fetchfirst=" limit 1,"+CommonKey.index_show_pifashang;
    		sql = " select  *  from "+tableName+" where  user_type='"+CommonKey.user_type2+"'  "+str1_notnull+" and user_notuser1 like '%"+CommonKey.jinghao+str2+CommonKey.jinghao+"' order by user_level,user_sco desc "+
   		 	fetchfirst;//按照level,和积分倒序。积分高的批发商在前面
    	}
    	
		 System.out.println(sql);
		 
        BasicDatabase basic = new BasicDatabase();
        ResultSet rs= basic.executeSql("userBean", "select", sql, null, null, tableName, null,null,ufo);
        ArrayList list = new ArrayList();
        while(rs.next()){
        	userBean bean = new userBean();
        	setUserBean(bean,rs);
        	list.add(bean);
        }
		if(basic.pstm !=null){
			basic.pstm.close();
			basic.pstm=null;
		}
		if(basic.conn != null){
			basic.conn.close();
			basic.conn=null;
		}
        rs.close();
        return list;
    }
    
    //向数据库里插一条数据
    public  int insertOneRow(userBean bean) throws Exception{
        String [] columns = new String[bianliangcount];
        String [] values = new String[bianliangcount];
        int returncode=0;
        columns[0]="user_id";
        columns[1]="user_pwd";
        columns[2]="user_sex";
        columns[3]="user_tel_no";
        columns[4]="user_tel_no2";
        columns[5]="user_name";
        columns[6]="user_level";
        columns[7]="user_sco";
        columns[8]="user_regis_date";
        columns[9]="user_last_login_date";
        columns[10]="user_qq";
        columns[11]="user_weixin";
        columns[12]="user_address1";
        columns[13]="user_address2";
        columns[14]="user_address3";
        columns[15]="user_address4";
        columns[16]="user_zip1";
        columns[17]="user_zip2";
        columns[18]="user_zip3";
        columns[19]="user_zip4";
        columns[20]="user_photo";
        columns[21]="user_islock";
        columns[22]="user_mail_address";
        columns[23]="user_question";
        columns[24]="user_question_answer";
        columns[25]="user_nicheng";
        columns[26]="user_type";
        columns[27]="user_notuser1";
        columns[28]="user_notuser2";
        columns[29]="user_notuser3";
        columns[30]="user_notuser4";
        columns[31]="user_notuser5";
        columns[32]="user_notuser6";
        columns[33]="user_notuser7";
        columns[34]="user_notuser8";
        columns[35]="user_notuser9";
        columns[36]="user_notuser10";
        columns[37]="user_notuser11";

        values[0]=bean.user_id;
        values[1]=bean.user_pwd;
        values[2]=bean.user_sex;
        values[3]=bean.user_tel_no;
        values[4]=bean.user_tel_no2;
        values[5]=bean.user_name;
        values[6]=bean.user_level;
        values[7]=bean.user_sco;
        values[8]=bean.user_regis_date;
        values[9]=bean.user_last_login_date;
        values[10]=bean.user_qq;
        values[11]=bean.user_weixin;
        values[12]=bean.user_address1;
        values[13]=bean.user_address2;
        values[14]=bean.user_address3;
        values[15]=bean.user_address4;
        values[16]=bean.user_zip1;
        values[17]=bean.user_zip2;
        values[18]=bean.user_zip3;
        values[19]=bean.user_zip4;
        values[20]=bean.user_photo;
        values[21]=bean.user_islock;
        values[22]=bean.user_mail_address;
        values[23]=bean.user_question;
        values[24]=bean.user_question_answer;
        values[25]=bean.user_nicheng;
        values[26]=bean.user_type;
        values[27]=bean.user_notuser1;
        values[28]=bean.user_notuser2;
        values[29]=bean.user_notuser3;
        values[30]=bean.user_notuser4;
        values[31]=bean.user_notuser5;
        values[32]=bean.user_notuser6;
        values[33]=bean.user_notuser7;
        values[34]=bean.user_notuser8;
        values[35]=bean.user_notuser9;
        values[36]=bean.user_notuser10;
        values[37]=bean.user_notuser11;
        BasicDatabase basic = new BasicDatabase();

         basic.executeSql("userBean", "insert", null, columns, values, tableName, null,null,bean);
 		if(basic.pstm !=null){
			basic.pstm.close();
			basic.pstm=null;
		}
		if(basic.conn != null){
			basic.conn.close();
			basic.conn=null;
		}
         returncode=1;
        return returncode;
    }
    
    //判断访问某个页面的权限
    //TODO
    public static boolean isDisplay(String pagid,userBean user){
    	//usertype
    	String userType = user.user_type;
    	System.out.println("usertype:"+userType);
    	return true;
    }
    //获得本批发商所有商品类别(第1层)列表
    public static ArrayList<GoodsTypeBean> getGoodsTypeName(LinkedHashMap <String,GoodsTypeBean>_hashmap,userBean _user){
    	ArrayList<GoodsTypeBean> returnList = new ArrayList<GoodsTypeBean>();
    	if(_hashmap==null || _user==null){
    		return returnList;
    	}
    	String[] userGoodsType = _user.user_notuser1.split(CommonKey.jinghao);
    	Iterator  <Map.Entry<String,GoodsTypeBean>> iter =	_hashmap.entrySet().iterator();
		while (iter.hasNext()) {
			Map.Entry <String,GoodsTypeBean>entry = (Map.Entry <String,GoodsTypeBean>) iter.next();
			for(String strGoodstype:userGoodsType){
				if(entry.getValue().goods_type_id.equals(strGoodstype)){
					returnList.add(entry.getValue());
					break;
				}
			}
		}
    	return returnList;
    }  
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
