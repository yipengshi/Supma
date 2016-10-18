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
	public String pianqu_id       ="";//Ƭ����ֵ     
    public String pianqu_name       ="";//Ƭ����������   
    public ArrayList pianqu_id_list       =null;//Ƭ����ֵ  
    public ArrayList pianqu_name_list       =null;//Ƭ����������    
 
    //Ƭ����listȡ��
    public  void initPianQuList_NOFenGe(){
    	pianqu_id_list = new  ArrayList();
    	pianqu_name_list = new ArrayList();
    	for(int i=0;i<CommonKey.pianqu_id.length;i++){
    		pianqu_id_list.add(CommonKey.pianqu_id[i]);
    		pianqu_name_list.add(CommonKey.pianqu_name[i]);
    	}
    }
    
    //Ƭ����listȡ��(���ָ�������ڳ�ʼ������ѡ��Ƭ����radion button)
    public  void initPianQuList_YesFenGe(){
    	pianqu_id_list = new  ArrayList();
    	pianqu_name_list = new ArrayList();
    	for(int i=0;i<CommonKey.pianqu_id.length;i++){
    		pianqu_id_list.add(CommonKey.jinghao+CommonKey.pianqu_id[i]+CommonKey.jinghao);//����10���#10#����
    		pianqu_name_list.add(CommonKey.pianqu_name[i]);
    	}
    }
 
}
