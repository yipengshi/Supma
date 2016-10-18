package supma.beans;


import java.io.Serializable;
import java.lang.*;
import java.util.*;
import java.sql.*;

import supma.common.CommonKey;
import supma.db.BasicDatabase;
import supma.util.StringUtil;

public class GoodsBean implements Serializable {
	

	private static final long serialVersionUID = 203171853619606053L;

	static  String  tableName = "goodstab" ;
    
    int bianliangcount=72;//������ݿ���ֶθ��������ӵ�ʱ��Ҫ�޸�����(�����Զ�������id)
    public String goods_id            ="";//��Ʒid 0000000001��ʼ��������*
    public String goods_name          ="";//��Ʒ��*                       
    public String goods_type_id       ="";//��Ʒ�������͵�3��*ѡ��
    public String goods_type_id_lv1       ="";//��Ʒ�������͵�1��*ϵͳ�Զ�
    public String goods_type_id_lv2       ="";//��Ʒ�������͵�2��*ϵͳ�Զ�
    public String goods_pinpai  ="";//��ƷƷ��*
    public String goods_made_address  ="";//��Ʒ����#��ѡ                     
    public String goods_made_factory  ="";//��Ʒ��������   #��ѡ               
    public String goods_made_date     ="";//��Ʒ��������   *ѡ�����д               
    public String goods_bad_date      ="";//��ƷʧЧ����     *ϵͳ�Զ�            
    public String goods_baozhi_date   ="";//��Ʒ������(��)  * ��д              
    public String goods_jiage         ="";//��Ʒ�۸�            * ��д          
    public String goods_zhekou_jiage  ="";//��Ʒ�ۿۺ�۸� 
    public String goods_jinchiJiage  ="";//��Ʒ������۸�
    public String goods_pifashang     ="";//��Ʒ��Ӧ��������(id)         
    public String goods_duanmingcheng ="";//��Ʒ�����ƣ�����������ѯ��)  *ϵͳ�Զ�����Ʒ��+Ʒ��+���صȵ�
    public String goods_tiaoxingma    ="";//��Ʒ������
    public String goods_guige   ="";//��Ʒ���(�ͺ�)
    public String goods_zhongliang    ="";//��Ʒ��װ����(��)  
    public String goods_xiaoliang     ="";//��Ʒ����                     
    public String goods_kucunshu      ="";//��Ʒ�����                   
    public String goods_tuihuoshu     ="";//��Ʒ�˻�����
    public String goods_xiangoushuliang     ="";//��Ʒ�˻�����          
    public String goods_jifen     ="";//��Ʒ�˻�����          
    public String goods_liulanshu     ="";//��Ʒ�������                 
    public String goods_shangjia      ="";//��Ʒ�Ƿ��ϼ�flag             
    public String goods_shenhe        ="";//��Ʒͨ�����flag 
    public String goods_tejia        ="";//��Ʒ�Ƿ��ؼ���Ʒ
    public String goods_huodong        ="";//��Ʒ�μӵĻ 
    public String goods_dazeng1        ="";//������Ʒid  
    public String goods_dazeng_tflag1        ="";//������Ʒ�Ƿ���˻�(Y/N)
    public String goods_dazeng_count1        ="";//������Ʒ����
    public String goods_dazeng_tiaojian1        ="";//���򼸸���Ʒ���Դ���    
    public String goods_dazeng_shuoming1        ="";//������Ʒ˵��
    public String goods_dazeng2        ="";//������Ʒid  
    public String goods_dazeng_tflag2        ="";//������Ʒ�Ƿ���˻�(Y/N)
    public String goods_dazeng_count2        ="";//������Ʒ���� 
    public String goods_dazeng_tiaojian2        ="";//���򼸸���Ʒ���Դ��� 
    public String goods_dazeng_shuoming2        ="";//������Ʒ˵��
    public String goods_zuixiao_count        ="";//��С������
    public String goods_shangjia_date ="";//��Ʒ�ϼ�����  
    public String goods_pifashuliang1 ="";//��������1  
    public String goods_pifashuliang2 ="";//��������2 
    public String goods_pifashuliang3 ="";//��������3  
    public String goods_pifajiage1 ="";//�����۸�1  
    public String goods_pifajiage2 ="";//�����۸�2  
    public String goods_pifajiage3 ="";//�����۸�3  
    public String goods_shuoming1     ="";//��Ʒ˵��1->�������ڴ洢��ζ                    
    public String goods_shuoming2     ="";//��Ʒ˵��2                    
    public String goods_shuoming3     ="";//��Ʒ˵��3                    
    public String goods_shuoming4     ="";//��Ʒ˵��4
    public String goods_fuwuchengnuo     ="";//��Ʒ�����ŵ 
    public String goods_wenxintishi     ="";//��Ʒ��ܰ��ʾ  
    public String goods_small_pic1    ="";//��ƷСͼ1                    
    public String goods_small_pic2    ="";//��ƷСͼ2                    
    public String goods_small_pic3    ="";//��ƷСͼ3                    
    public String goods_small_pic4    ="";//��ƷСͼ4                    
    public String goods_small_pic5    ="";//��ƷСͼ5                    
    public String goods_small_pic6    ="";//��ƷСͼ6                    
    public String goods_mid_pic1      ="";//��Ʒ��ͼ1                    
    public String goods_mid_pic2      ="";//��Ʒ��ͼ2                    
    public String goods_mid_pic3      ="";//��Ʒ��ͼ3                    
    public String goods_mid_pic4      ="";//��Ʒ��ͼ4                   
    public String goods_big_pic1      ="";//��Ʒ��ͼ1                    
    public String goods_big_pic2      ="";//��Ʒ��ͼ2                    
    public String goods_big_pic3      ="";//��Ʒ��ͼ3                    
    public String goods_big_pic4      ="";//��Ʒ��ͼ4
    public String goods_input_date      ="";//��Ʒ¼������
    public String goods_modify_date      ="";//��Ʒ����޸�����
    public String goods_modify_user      ="";//��Ʒ����޸���
    public String goods_modify_user_ip      ="";//��Ʒ����޸��˵�ip
    public String goods_luxiang       ="";//��Ʒ��Ƶ˵��                               
    public String goods_notsale       ="";//�Ƿ������Ʒ������(Y/N)        
    
  PreparedStatement pre = null;
  
  //��ѯһ�����������еĴ���Ʒ
  public    ArrayList<GoodsBean> getDaZengPinList (userBean ufo) throws Exception{
      ArrayList <GoodsBean>list = new ArrayList<GoodsBean>();
      if(ufo==null){
    	  return list;
      }
	  String userid = ufo.user_id;
	  String sql ="select * from "+tableName+" where goods_pifashang='"+userid+"' and goods_notsale='"+CommonKey.goods_flag_y+"'";
      BasicDatabase basic = new BasicDatabase();
      ResultSet rs= basic.executeSql("GoodsBean", "select", sql, null, null, tableName, null,null,ufo);
      while(rs.next()){
    	  GoodsBean bean = new GoodsBean();
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
  
  //��������������Ʒ����Ʒ���ͼ���
  //����1����������Ʒ����
  //����2:��������Ʒ���ͼ���
  //���أ�������������С���ϣ������������Ʒ��������Ʒ���ͼ�������û�У��򷵻صļ�����Ҳ������������
  public    ArrayList<GoodsTypeBean> getGoodsTypes1 (ArrayList<GoodsBean> _goods,ArrayList <GoodsTypeBean> _goodstype) {
	  ArrayList<GoodsTypeBean> returnlist = new ArrayList<GoodsTypeBean>();
	  if(_goods==null || _goods.size()==0 || _goodstype==null || _goodstype.size()==0){
		  return returnlist;
	  }
	  LinkedHashMap<String,String> typelist = new LinkedHashMap<String,String>();
	  //ȡ�ø�����Ʒ���ϵ���������(��һ������),�ظ���ɾ����
	  for(GoodsBean onegoods:_goods){
		  typelist.put(onegoods.goods_type_id_lv1, "");
	  }
  	Iterator  <Map.Entry<String,String>> iter =	typelist.entrySet().iterator();
	while (iter.hasNext()) {
		Map.Entry <String,String>entry = (Map.Entry <String,String>) iter.next();
		for(GoodsTypeBean temptype:_goodstype){
			if( entry.getKey().equals(temptype.goods_type_id)){
				returnlist.add(temptype);
			}
		}
	}//end while
		return returnlist;
  }

  // �����û�indexҳ�����¥����Ʒ��ʾ�ĳ�ʼ��
  //����1:��Ʒ��𣨵�1����ࣩ����2���õ�¼�û������Ϳ��������̣�����3���Ƿ��ѯ�̶�����true,false
  public  ArrayList<GoodsBean> getShangPinList(String str1,ArrayList<userBean> pifa,boolean limit,userBean ufo) throws Exception{
  	String fetchfirst = "";
  	String rowsonly = "";
  	String sql="";
  	StringBuffer str1_notnull = new StringBuffer();//����1��Ϊ�յ�sql����
  	StringBuffer pifa_notnull = new StringBuffer();//����2��Ϊ�յ�sql����
  	StringBuffer tempPifa = new StringBuffer();
  	if(!"".equals(str1)){
  		str1_notnull.append(" and goods_type_id_lv1='"+str1.trim()+"' ");
  		
	  	if(pifa!=null && pifa.size()>0){//��Ʒ���ڸõ�¼�û�Ƭ���ͻ����ܵ�������
  	  		for(int i=0;i<pifa.size();i++){
  				userBean beanx = (userBean)pifa.get(i);
  				String tmpStr = CommonKey.jinghao+str1.trim()+CommonKey.jinghao;//����01,02�ȵ�
  				if(beanx.user_notuser1.indexOf(tmpStr) !=-1){
  					tempPifa.append("'"+beanx.user_id.trim()+"',");
  				}
  	  		}
  	  		if(!"".equals(tempPifa.toString())){
	  	  		pifa_notnull.append(" and goods_pifashang in( ");
	  	  		String stemp = tempPifa.toString();
	  	  		pifa_notnull.append(stemp.subSequence(0, stemp.length()-1));
	  	  		pifa_notnull.append(" ) ");
  	  		}
  	  	}
  	}
  	
  	if(limit){
      	if(CommonKey.use_db.equals(CommonKey.db_db2)){
      		fetchfirst = " fetch first ";
      		rowsonly=" rows only ";
      		// sql���������ͨ��(Y),��治��0     sql�����ϼ�ʱ��������Ʒ��ʾ��ǰ��Ȼ�����������򡣺�����ǰ
      		 sql = " select  *  from "+tableName+" where  goods_shenhe='"+CommonKey.goods_shenheWan+"' "+str1_notnull.toString()+pifa_notnull.toString()+" and goods_kucunshu>'0' "+"order by goods_shangjia_date,goods_xiaoliang desc "+
      		 fetchfirst+CommonKey.index_show_shangpin+rowsonly;//����level,�ͻ��ֵ��򡣻��ָߵ���������ǰ��
      	}else if(CommonKey.use_db.equals(CommonKey.db_mysql)){
      		fetchfirst=" limit 1,"+CommonKey.index_show_shangpin;
      		 sql = " select  *  from "+tableName+" where  goods_shenhe='"+CommonKey.goods_shenheWan+"' "+str1_notnull.toString()+pifa_notnull.toString()+" and goods_kucunshu>'0' "+"order by goods_shangjia_date,goods_xiaoliang desc "+
     		 	fetchfirst;
      	}
  	}else{
  		 sql = " select  *  from "+tableName+" where  goods_shenhe='"+CommonKey.goods_shenheWan+"' "+str1_notnull.toString()+pifa_notnull.toString()+" and goods_kucunshu>'0' "+"order by goods_shangjia_date,goods_xiaoliang desc ";
  	}
      BasicDatabase basic = new BasicDatabase();
      ResultSet rs= basic.executeSql("userBean", "select", sql, null, null, tableName, null,null,ufo);
      ArrayList list = new ArrayList();
      while(rs.next()){
    	  GoodsBean bean = new GoodsBean();
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
  
  
  //���ݿ�ȡ���ŵ�bean��(����)
  public void setGoodsBean(GoodsBean bean,ResultSet rs) throws Exception{
	  bean.goods_id              =rs.getString("goods_id").trim();    
	  bean.goods_name            =rs.getString("goods_name").trim();    
	  bean.goods_type_id         =rs.getString("goods_type_id").trim();    
	  bean.goods_type_id_lv1         =rs.getString("goods_type_id_lv1").trim();   
	  bean.goods_type_id_lv2         =rs.getString("goods_type_id_lv2").trim();   
	  bean.goods_pinpai         =rs.getString("goods_pinpai").trim();    
	  bean.goods_made_address    =rs.getString("goods_made_address").trim();    
	  bean.goods_made_factory    =rs.getString("goods_made_factory").trim();    
	  bean.goods_made_date       =rs.getString("goods_made_date").trim();    
	  bean.goods_bad_date        =rs.getString("goods_bad_date").trim();    
	  bean.goods_baozhi_date     =rs.getString("goods_baozhi_date").trim();    
	  bean.goods_jiage           =rs.getString("goods_jiage").trim();    
	  bean.goods_zhekou_jiage    =rs.getString("goods_zhekou_jiage").trim();    
	  bean.goods_jinchiJiage    =rs.getString("goods_jinchiJiage").trim(); 
	  bean.goods_pifashang       =rs.getString("goods_pifashang").trim();    
	  bean.goods_duanmingcheng   =rs.getString("goods_duanmingcheng").trim();    
	  bean.goods_tiaoxingma      =rs.getString("goods_tiaoxingma").trim(); 
	  bean.goods_guige      =rs.getString("goods_guige").trim(); 
	  bean.goods_zhongliang      =rs.getString("goods_zhongliang").trim();    
	  bean.goods_xiaoliang       =rs.getString("goods_xiaoliang").trim();    
	  bean.goods_kucunshu        =rs.getString("goods_kucunshu").trim();    
	  bean.goods_tuihuoshu       =rs.getString("goods_tuihuoshu").trim();   
	  bean.goods_xiangoushuliang       =rs.getString("goods_xiangoushuliang").trim();    
	  bean.goods_jifen       =rs.getString("goods_jifen").trim();    
	  bean.goods_liulanshu       =rs.getString("goods_liulanshu").trim();    
	  bean.goods_shangjia        =rs.getString("goods_shangjia").trim();    
	  bean.goods_shenhe          =rs.getString("goods_shenhe").trim(); 
	  bean.goods_tejia          =rs.getString("goods_tejia").trim();    
	  bean.goods_huodong          =rs.getString("goods_huodong").trim();   
	  bean.goods_dazeng1          =rs.getString("goods_dazeng1").trim();   
	  bean.goods_dazeng_tflag1          =rs.getString("goods_dazeng_tflag1").trim();   
	  bean.goods_dazeng_count1          =rs.getString("goods_dazeng_count1").trim();   
	  bean.goods_dazeng_tiaojian1          =rs.getString("goods_dazeng_tiaojian1").trim();   
	  bean.goods_dazeng_shuoming1          =rs.getString("goods_dazeng_shuoming1").trim();   
	  bean.goods_dazeng2          =rs.getString("goods_dazeng2").trim();   
	  bean.goods_dazeng_tflag2          =rs.getString("goods_dazeng_tflag2").trim();   
	  bean.goods_dazeng_count2          =rs.getString("goods_dazeng_count2").trim();   
	  bean.goods_dazeng_tiaojian2          =rs.getString("goods_dazeng_tiaojian2").trim();   
	  bean.goods_dazeng_shuoming2          =rs.getString("goods_dazeng_shuoming2").trim();   
	  bean.goods_zuixiao_count   =rs.getString("goods_zuixiao_count").trim();
	  bean.goods_shangjia_date   =rs.getString("goods_shangjia_date").trim();
	  bean.goods_pifashuliang1   =rs.getString("goods_pifashuliang1").trim();
	  bean.goods_pifashuliang2   =rs.getString("goods_pifashuliang2").trim();
	  bean.goods_pifashuliang3   =rs.getString("goods_pifashuliang3").trim();
	  bean.goods_pifajiage1   =rs.getString("goods_pifajiage1").trim();
	  bean.goods_pifajiage2   =rs.getString("goods_pifajiage2").trim();
	  bean.goods_pifajiage3   =rs.getString("goods_pifajiage3").trim();
	  bean.goods_shuoming1       =rs.getString("goods_shuoming1").trim();    
	  bean.goods_shuoming2       =rs.getString("goods_shuoming2").trim();    
	  bean.goods_shuoming3       =rs.getString("goods_shuoming3").trim();    
	  bean.goods_shuoming4       =rs.getString("goods_shuoming4").trim(); 
	  bean.goods_fuwuchengnuo       =rs.getString("goods_fuwuchengnuo").trim();    
	  bean.goods_wenxintishi       =rs.getString("goods_wenxintishi").trim();    
	  bean.goods_small_pic1      =rs.getString("goods_small_pic1").trim();    
	  bean.goods_small_pic2      =rs.getString("goods_small_pic2").trim();    
	  bean.goods_small_pic3      =rs.getString("goods_small_pic3").trim();    
	  bean.goods_small_pic4      =rs.getString("goods_small_pic4").trim();    
	  bean.goods_small_pic5      =rs.getString("goods_small_pic5").trim();    
	  bean.goods_small_pic6      =rs.getString("goods_small_pic6").trim();    
	  bean.goods_mid_pic1        =rs.getString("goods_mid_pic1").trim();    
	  bean.goods_mid_pic2        =rs.getString("goods_mid_pic2").trim();    
	  bean.goods_mid_pic3        =rs.getString("goods_mid_pic3").trim();    
	  bean.goods_mid_pic4        =rs.getString("goods_mid_pic4").trim();    
	  bean.goods_big_pic1        =rs.getString("goods_big_pic1").trim();    
	  bean.goods_big_pic2        =rs.getString("goods_big_pic2").trim();    
	  bean.goods_big_pic3        =rs.getString("goods_big_pic3").trim();    
	  bean.goods_big_pic4        =rs.getString("goods_big_pic4").trim();    
	  bean.goods_input_date        =rs.getString("goods_input_date").trim();    
	  bean.goods_modify_date        =rs.getString("goods_modify_date").trim();    
	  bean.goods_modify_user        =rs.getString("goods_modify_user").trim();   
	  bean.goods_modify_user_ip        =rs.getString("goods_modify_user_ip").trim();   
	  bean.goods_luxiang         =rs.getString("goods_luxiang").trim();    
	  bean.goods_notsale         =rs.getString("goods_notsale").trim();    
  }
 
  //�����ݿ����һ������
  public  int insertOneRow(GoodsBean bean,userBean ufo) throws Exception{
      String [] columns = new String[bianliangcount];
      String [] values = new String[bianliangcount];
      int returncode=0;
      columns[0]="goods_name";
      columns[1]="goods_type_id";
      columns[2]="goods_type_id_lv1";
      columns[3]="goods_type_id_lv2";
      columns[4]="goods_pinpai";
      columns[5]="goods_made_address";
      columns[6]="goods_made_factory";
      columns[7]="goods_made_date";
      columns[8]="goods_bad_date";
      columns[9]="goods_baozhi_date";
      columns[10]="goods_jiage";
      columns[11]="goods_zhekou_jiage";
      columns[12]="goods_jinchiJiage";
      columns[13]="goods_pifashang";
      columns[14]="goods_duanmingcheng";
      columns[15]="goods_tiaoxingma";
      columns[16]="goods_guige";
      columns[17]="goods_zhongliang";
      columns[18]="goods_xiaoliang";
      columns[19]="goods_kucunshu";
      columns[20]="goods_tuihuoshu";
      columns[21]="goods_xiangoushuliang";
      columns[22]="goods_jifen";
      columns[23]="goods_liulanshu";
      columns[24]="goods_shangjia";
      columns[25]="goods_shenhe";
      columns[26]="goods_tejia";
      columns[27]="goods_huodong";
      columns[28]="goods_dazeng1";
      columns[29]="goods_dazeng_tflag1";
      columns[30]="goods_dazeng_count1";
      columns[31]="goods_dazeng_tiaojian1";
      columns[32]="goods_dazeng_shuoming1";
      columns[33]="goods_dazeng2";
      columns[34]="goods_dazeng_tflag2";
      columns[35]="goods_dazeng_count2";
      columns[36]="goods_dazeng_tiaojian2";
      columns[37]="goods_dazeng_shuoming2";
      columns[38]="goods_zuixiao_count";
      columns[39]="goods_shangjia_date";
      columns[40]="goods_pifashuliang1";
      columns[41]="goods_pifashuliang2";
      columns[42]="goods_pifashuliang3";
      columns[43]="goods_pifajiage1";
      columns[44]="goods_pifajiage2";
      columns[45]="goods_pifajiage3";
      columns[46]="goods_shuoming1";
      columns[47]="goods_shuoming2";
      columns[48]="goods_shuoming3";
      columns[49]="goods_shuoming4";
      columns[50]="goods_fuwuchengnuo";
      columns[51]="goods_wenxintishi";
      columns[52]="goods_small_pic1";
      columns[53]="goods_small_pic2";
      columns[54]="goods_small_pic3";
      columns[55]="goods_small_pic4";
      columns[56]="goods_small_pic5";
      columns[57]="goods_small_pic6";
      columns[58]="goods_mid_pic1";
      columns[59]="goods_mid_pic2";
      columns[60]="goods_mid_pic3";
      columns[61]="goods_mid_pic4";
      columns[62]="goods_big_pic1";
      columns[63]="goods_big_pic2";
      columns[64]="goods_big_pic3";
      columns[65]="goods_big_pic4";
      columns[66]="goods_input_date";
      columns[67]="goods_modify_date";
      columns[68]="goods_modify_user";
      columns[69]="goods_modify_user_ip";
      columns[70]="goods_luxiang";
      columns[71]="goods_notsale";

      values[0]=bean.goods_name;
      values[1]=bean.goods_type_id;
      values[2]=bean.goods_type_id_lv1;
      values[3]=bean.goods_type_id_lv2;
      values[4]=bean.goods_pinpai;
      values[5]=bean.goods_made_address;
      values[6]=bean.goods_made_factory;
      values[7]=bean.goods_made_date;
      values[8]=bean.goods_bad_date;
      values[9]=bean.goods_baozhi_date;
      values[10]=bean.goods_jiage;
      values[11]=bean.goods_zhekou_jiage;
      values[12]=bean.goods_jinchiJiage;
      values[13]=bean.goods_pifashang;
      values[14]=bean.goods_duanmingcheng;
      values[15]=bean.goods_tiaoxingma;
      values[16]=bean.goods_guige;
      values[17]=bean.goods_zhongliang;
      values[18]=bean.goods_xiaoliang;
      values[19]=bean.goods_kucunshu;
      values[20]=bean.goods_tuihuoshu;
      values[21]=bean.goods_xiangoushuliang;
      values[22]=bean.goods_jifen;
      values[23]=bean.goods_liulanshu;
      values[24]=bean.goods_shangjia;
      values[25]=bean.goods_shenhe;
      values[26]=bean.goods_tejia;
      values[27]=bean.goods_huodong;
      values[28]=StringUtil.emptyToZero(bean.goods_dazeng1);
      values[29]=bean.goods_dazeng_tflag1;
      values[30]=bean.goods_dazeng_count1;
      values[31]=bean.goods_dazeng_tiaojian1;
      values[32]=bean.goods_dazeng_shuoming1;
      values[33]=StringUtil.emptyToZero(bean.goods_dazeng2);
      values[34]=bean.goods_dazeng_tflag2;
      values[35]=bean.goods_dazeng_count2;
      values[36]=bean.goods_dazeng_tiaojian2;
      values[37]=bean.goods_dazeng_shuoming2;
      values[38]=StringUtil.emptyToZero(bean.goods_zuixiao_count);
      values[39]=bean.goods_shangjia_date;
      values[40]=bean.goods_pifashuliang1;
      values[41]=bean.goods_pifashuliang2;
      values[42]=bean.goods_pifashuliang3;
      values[43]=bean.goods_pifajiage1;
      values[44]=bean.goods_pifajiage2;
      values[45]=bean.goods_pifajiage3;
      values[46]=bean.goods_shuoming1;
      values[47]=bean.goods_shuoming2;
      values[48]=bean.goods_shuoming3;
      values[49]=bean.goods_shuoming4;
      values[50]=bean.goods_fuwuchengnuo;
      values[51]=bean.goods_wenxintishi;
      values[52]=bean.goods_small_pic1;
      values[53]=bean.goods_small_pic2;
      values[54]=bean.goods_small_pic3;
      values[55]=bean.goods_small_pic4;
      values[56]=bean.goods_small_pic5;
      values[57]=bean.goods_small_pic6;
      values[58]=bean.goods_mid_pic1;
      values[59]=bean.goods_mid_pic2;
      values[60]=bean.goods_mid_pic3;
      values[61]=bean.goods_mid_pic4;
      values[62]=bean.goods_big_pic1;
      values[63]=bean.goods_big_pic2;
      values[64]=bean.goods_big_pic3;
      values[65]=bean.goods_big_pic4;
      values[66]=bean.goods_input_date;
      values[67]=bean.goods_modify_date;
      values[68]=bean.goods_modify_user;
      values[69]=bean.goods_modify_user_ip;
      values[70]=bean.goods_luxiang;
      values[71]=bean.goods_notsale;

      BasicDatabase basic = new BasicDatabase();

       basic.executeSql("GoodsBean", "insert", null, columns, values, tableName, null,null,ufo);
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
  
}
