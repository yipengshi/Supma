package supma.beans;


import java.io.Serializable;
import java.lang.*;
import java.util.*;
import java.sql.*;

import supma.common.CommonKey;
import supma.db.BasicDatabase;

public class PianQuBean  implements Serializable
{

	private static final long serialVersionUID = 8499792144345036431L;
	public String pianqu_id       ="";//片区的值     
    public String pianqu_name       ="";//片区的中文名   
    public ArrayList pianqu_id_list       =null;//片区的值  
    public ArrayList pianqu_name_list       =null;//片区的中文名    
 
    //片区的list取得
    public  void initPianQuList_NOFenGe(){
    	pianqu_id_list = new  ArrayList();
    	pianqu_name_list = new ArrayList();
    	for(int i=0;i<CommonKey.pianqu_id.length;i++){
    		pianqu_id_list.add(CommonKey.pianqu_id[i]);
    		pianqu_name_list.add(CommonKey.pianqu_name[i]);
    	}
    }
    
    //片区的list取得(带分割符，用于初始化超市选择片区的radion button)
    public  void initPianQuList_YesFenGe(){
    	pianqu_id_list = new  ArrayList();
    	pianqu_name_list = new ArrayList();
    	for(int i=0;i<CommonKey.pianqu_id.length;i++){
    		pianqu_id_list.add(CommonKey.jinghao+CommonKey.pianqu_id[i]+CommonKey.jinghao);//比如10变成#10#这样
    		pianqu_name_list.add(CommonKey.pianqu_name[i]);
    	}
    }
 
}
