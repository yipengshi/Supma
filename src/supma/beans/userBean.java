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
	public String isLogin = "0";//0:not login. 1:login 2:user����
    String tableName = "usertab" ;
    
    int bianliangcount=38;//������ݿ���ֶθ��������ӵ�ʱ��Ҫ�޸�����
    public String user_id      ="";        //�û���      
    public String user_pwd     ="";        //����        
    public String user_sex     ="";        //�Ա�        
    public String user_tel_no  ="";        //����        
    public String user_tel_no2 ="";        //��������    
    public String user_name    ="";        //�û�����    
    public String user_level   ="";        //�û��ȼ�    
    public String user_sco     ="";        //�û�����    
    public String user_regis_date ="";     //ע������    
    public String user_last_login_date ="";//�ϴε�¼���߱�������
    public String user_qq             =""; //QQ��        
    public String user_weixin         =""; //΢�ź�      
    public String user_address1       =""; //���еĵ�/�����̵ĳ�ַ/����Ա�ĵ�ַ
    public String user_address2       =""; //��������Ƭ��/�����̿�����Ƭ��/����Ա��ϽƬ��     CommonKey.pianqu_id
    public String user_address3       =""; //��ַ3       
    public String user_address4       =""; //��ַ4       
    public String user_zip1           =""; //��������1   
    public String user_zip2           =""; //��������2   
    public String user_zip3           =""; //��������3   
    public String user_zip4           =""; //��������4   
    public String user_notuser1       =""; //�����̵���Ʒ����:  goodsmastertab
    public String user_notuser2       =""; //����Ӫҵִ��ͼƬ     
    public String user_notuser3       =""; //��λ3       
    public String user_notuser4       =""; //��λ4       
    public String user_notuser5       =""; //��λ5       
    public String user_notuser6       =""; //��λ6       
    public String user_notuser7       =""; //��λ7       
    public String user_notuser8       =""; //��λ8       
    public String user_notuser9       =""; //��λ9       
    public String user_notuser10      =""; //��λ10      
    public String user_notuser11      =""; //user �ϴλ��߱��ε�¼ip
    public String user_photo            ="";//�û�ͷ���ַ
    public String user_islock ="";//�û��Ƿ�����
    public String user_mail_address="";//�û�����
    public String user_question ="";//�û����뱣������
    public String user_question_answer ="";//�û����뱣�������
    public String user_nicheng = "";//�û��ǳ�
    public String user_type= "";//�û�����:1����2.������3.����Ա
    
    
  PreparedStatement pre = null;
    

//    public Vector getCurPage(int cur,int records)
//    {
//        return getOnePage("SELECT * FROM backuser",cur,records);
//    }
//
  
  //��¼�û���Ϣд�����ݿ�,�ѱ��ε�¼ʱ�䣬��¼ip������д�����ݿ�(update)
  public  String updateLoginUserSql() {
      String sql = " update  "+tableName+" set user_last_login_date=?,user_notuser11=?  where user_id=?";
      return sql;
  }
  
  //����û����Ƿ����
    public  String getLoginUserSql() {
        String sql = " select  *  from "+tableName+" where  user_id=? ";
        return sql;
    }
    //�û�������ʱ����֤�����Ƿ���ȷ
    public  String getLoginSql() {
        String sql = " select  *  from "+tableName+" where  user_id=?  and user_pwd=?";
        return sql;
    }
    //��������Ƿ����
    public  String getLoginMailSql() {
        String sql = " select  *  from "+tableName+" where  user_mail_address=? ";
        return sql;
    }
    //����ֻ��Ƿ����
    public  String getLoginMoboSql() {
        String sql = " select  *  from "+tableName+" where  user_tel_no=? ";
        return sql;
    }
    //login���ʼ��userbean
    public  void initUserBean(ResultSet rs) throws Exception{
        if(rs!=null && rs.next()){
        	setUserBean(this,rs);
        	 if(CommonKey.user_islock_0.equals(user_islock)){
            	 this.isLogin = "1";//�����û�
        	 }else if(CommonKey.user_islock_1.equals(user_islock)){
        		 this.isLogin = "2";//�����û�
        	 }else if(CommonKey.user_islock_2.equals(user_islock)){
        		 this.isLogin = "3";//��ע�������û�
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

    //��ѯ�����û���Ӧ�Ŀ��Թ����������̣���ʾ������ҳ�����������̵ȼ�����level 9���
    // ����1�ǳ����û�����Ƭ��,����2��ʾ�Ƿ�ȫȡ��limit=true������ȡǰxxx��
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
        		 fetchfirst+CommonKey.index_show_pifashang+rowsonly;//����level,�ͻ��ֵ��򡣻��ָߵ���������ǰ��
        	}else if(CommonKey.use_db.equals(CommonKey.db_mysql)){
        		fetchfirst=" limit 1,"+CommonKey.index_show_pifashang;
        		sql = " select  *  from "+tableName+" where  user_type='"+CommonKey.user_type2+"'"+str1_notnull+" order by user_level,user_sco desc "+
       		 	fetchfirst;//����level,�ͻ��ֵ��򡣻��ָߵ���������ǰ��
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
    
    //��ѯ�����û���Ӧ�Ŀ��Թ���ĳ�����(��һ�����)��Ʒ�������̣���ʾ������ҳ�����������̵ȼ�����level 9���
    // ����1�ǳ����û�����Ƭ��,����2����Ʒ���
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
    		 fetchfirst+CommonKey.index_show_pifashang+rowsonly;//����level,�ͻ��ֵ��򡣻��ָߵ���������ǰ��
    	}else if(CommonKey.use_db.equals(CommonKey.db_mysql)){
    		fetchfirst=" limit 1,"+CommonKey.index_show_pifashang;
    		sql = " select  *  from "+tableName+" where  user_type='"+CommonKey.user_type2+"'  "+str1_notnull+" and user_notuser1 like '%"+CommonKey.jinghao+str2+CommonKey.jinghao+"' order by user_level,user_sco desc "+
   		 	fetchfirst;//����level,�ͻ��ֵ��򡣻��ָߵ���������ǰ��
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
    
    //�����ݿ����һ������
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
    
    //�жϷ���ĳ��ҳ���Ȩ��
    //TODO
    public static boolean isDisplay(String pagid,userBean user){
    	//usertype
    	String userType = user.user_type;
    	System.out.println("usertype:"+userType);
    	return true;
    }
    //��ñ�������������Ʒ���(��1��)�б�
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
//    //ȡ�����ݱ��е�ĳһ����¼
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
//        //�������ݿ�
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
//        //ȡ�õ�ǰҲ������
//        System.out.println(mb.getCurPage(1,100));
//        //System.out.println(mb.login("admin","e"));
//
//        //ȡ�ü�¼��
//        //System.out.println(mb.getResultSetData(mb.selectRecord("select count(*) from snals")));
//
//        //�ͷ�����
//        System.out.println("\r\n\r\nend");
//    }
}
