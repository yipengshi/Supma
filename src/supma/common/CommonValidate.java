package supma.common;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;

import javax.servlet.ServletContext;

import supma.beans.ErrorMessageBean;
import supma.beans.GoodsBean;
import supma.util.StringUtil;

import org.apache.commons.lang3.StringUtils;

public class CommonValidate{
	

	private ServletContext context = null;
	
	public CommonValidate(ServletContext _context){
		this.context=_context;
	}
	
	public  LinkedHashMap<String,String> ValidateInputGoods(GoodsBean submitGoods){
		HashMap<String,ErrorMessageBean>errorMap =(HashMap<String, ErrorMessageBean>) context.getAttribute(CommonKey.errorMessageKey);
		LinkedHashMap<String,String>returnMessage = new LinkedHashMap<String,String>();
		ErrorMessageBean tempError=null;
		//��Ʒ����
		if(StringUtils.isEmpty(submitGoods.goods_name) || CommonStringUtil.getTrueLength(submitGoods.goods_name)>80 ||  !submitGoods.goods_name.matches(CommonKey.exp2)){
			 tempError = errorMap.get("1");
			returnMessage.put(tempError.error_id, tempError.error_detail);
		}
		//��ƷƷ��
		if(StringUtils.isEmpty(submitGoods.goods_pinpai) || CommonStringUtil.getTrueLength(submitGoods.goods_pinpai)>20 || !submitGoods.goods_name.matches(CommonKey.exp2) ){
			 tempError = errorMap.get("2");
			returnMessage.put(tempError.error_id, tempError.error_detail);
		}
		//��װ����
		if(StringUtils.isEmpty(submitGoods.goods_zhongliang) || CommonStringUtil.getTrueLength(submitGoods.goods_zhongliang)>8 || !submitGoods.goods_zhongliang.matches(CommonKey.exp3) || "0".equals(submitGoods.goods_zhongliang) ){
			 tempError = errorMap.get("3");
			returnMessage.put(tempError.error_id, tempError.error_detail);
		}
		//�������(����0)
		if(StringUtils.isEmpty(submitGoods.goods_kucunshu) || CommonStringUtil.getTrueLength(submitGoods.goods_kucunshu)>8 ||  !submitGoods.goods_kucunshu.matches(CommonKey.exp3) || "0".equals(submitGoods.goods_kucunshu) ){
			 tempError = errorMap.get("4");
			returnMessage.put(tempError.error_id, tempError.error_detail);
		}	
		//������
		if(StringUtils.isEmpty(submitGoods.goods_baozhi_date) || CommonStringUtil.getTrueLength(submitGoods.goods_baozhi_date)>8 ||  !submitGoods.goods_baozhi_date.matches(CommonKey.exp3) || "0".equals(submitGoods.goods_baozhi_date) ){
			 tempError = errorMap.get("5");
			returnMessage.put(tempError.error_id, tempError.error_detail);
		}	
		//��Ʒ�����۸�1
		boolean b1flag = true;//�۸�1�Ƿ�Ϸ�
		boolean b2flag = true;//�۸�2�Ƿ�Ϸ�
		boolean b3flag = true;//�۸�2�Ƿ�Ϸ�
		if(StringUtils.isEmpty(submitGoods.goods_pifajiage1) || CommonStringUtil.getTrueLength(submitGoods.goods_pifajiage1)>8 ||  !submitGoods.goods_pifajiage1.matches(CommonKey.exp4) || "0".equals(submitGoods.goods_pifajiage1) ){
			 tempError = errorMap.get("6");
			returnMessage.put(tempError.error_id, tempError.error_detail);
			b1flag=false;
		}
		//��������Ϊ"�̶���"ʱ��,�����۸�2�ͼ۸�3����֤
		if(CommonKey.pifashuliang1Value_guding.equals(submitGoods.goods_pifashuliang1)){
			submitGoods.goods_pifajiage2="";
			submitGoods.goods_pifajiage3="";
			submitGoods.goods_pifashuliang2=CommonKey.pifashuliang2Value_guding;
			submitGoods.goods_pifashuliang3=CommonKey.pifashuliang2Value_guding;
		}else if(StringUtils.isEmpty(submitGoods.goods_pifashuliang1)){
			 tempError = errorMap.get("32");
			returnMessage.put(tempError.error_id, tempError.error_detail);
		}else{
			 if(StringUtils.isEmpty(submitGoods.goods_pifashuliang2)){//�����۸�2�Ķ�������ѡ��Ϊ��(������δѡ��0��)
				 tempError = errorMap.get("33");
				 returnMessage.put(tempError.error_id, tempError.error_detail);
				 b2flag=false;
			 }else if(!CommonKey.pifashuliang2Value_guding.equals(submitGoods.goods_pifashuliang2)){//�����۸�2���������ǡ�δѡ��ʱ�����۸���֤
				BigDecimal p1 = new BigDecimal(submitGoods.goods_pifashuliang1);//�����۸�1�Ķ�������
				BigDecimal p2 = new BigDecimal(submitGoods.goods_pifashuliang2);//�����۸�2�Ķ�������
				if(p1.compareTo(p2)==1){//����1��������2
					 tempError = errorMap.get("17");
					returnMessage.put(tempError.error_id, tempError.error_detail);
				}			
				if(StringUtils.isEmpty(submitGoods.goods_pifajiage2) || CommonStringUtil.getTrueLength(submitGoods.goods_pifajiage2)>8 ||  !submitGoods.goods_pifajiage2.matches(CommonKey.exp4) || "0".equals(submitGoods.goods_pifajiage2) ){
					 tempError = errorMap.get("7");
					returnMessage.put(tempError.error_id, tempError.error_detail);
					b2flag=false;
				}else{//�����۸�2����С�ڵ��������۸�1(����Խ��Խ����)
					if(b1flag&&b2flag){
						BigDecimal b1 = new BigDecimal(submitGoods.goods_pifajiage1);
						BigDecimal b2 = new BigDecimal(submitGoods.goods_pifajiage2);
						if(b2.compareTo(b1)==1){//�۸�2���ڼ۸�1
							 tempError = errorMap.get("9");
							returnMessage.put(tempError.error_id, tempError.error_detail);
						}
					}
				}
			}else{//�����۸�2δѡ��
				b2flag=false;
			}
			 if(StringUtils.isEmpty(submitGoods.goods_pifashuliang3)){//�����۸�3�Ķ�������ѡ��Ϊ��(������δѡ��0��)
				 tempError = errorMap.get("34");
				 returnMessage.put(tempError.error_id, tempError.error_detail);
				 b3flag=false;
			 }else if(!CommonKey.pifashuliang2Value_guding.equals(submitGoods.goods_pifashuliang3)){//�����۸�3���������ǡ�δѡ��ʱ�����۸���֤
				BigDecimal p1 = new BigDecimal(submitGoods.goods_pifashuliang1);//�����۸�1�Ķ�������
				BigDecimal p2 = new BigDecimal(submitGoods.goods_pifashuliang2);//�����۸�2�Ķ�������
				BigDecimal p3 = new BigDecimal(submitGoods.goods_pifashuliang3);//�����۸�3�Ķ�������
				if(p1.compareTo(p3)==1){//����1��������3
					 tempError = errorMap.get("18");
					returnMessage.put(tempError.error_id, tempError.error_detail);
				}	
				if(!CommonKey.pifashuliang2Value_guding.equals(submitGoods.goods_pifashuliang2)){//�����۸�2���������ǡ�δѡ��ʱ�����۸���֤
					if(p2.compareTo(p3)==1){//����2��������3
						 tempError = errorMap.get("19");
						returnMessage.put(tempError.error_id, tempError.error_detail);
					}	
				}
				if(StringUtils.isEmpty(submitGoods.goods_pifajiage3) || CommonStringUtil.getTrueLength(submitGoods.goods_pifajiage3)>8 ||  !submitGoods.goods_pifajiage3.matches(CommonKey.exp4) || "0".equals(submitGoods.goods_pifajiage3) ){
					 tempError = errorMap.get("8");
					returnMessage.put(tempError.error_id, tempError.error_detail);
					b3flag=false;
				}else{//�����۸�3����С�ڵ��������۸�1,2(����Խ��Խ����)
					if(b1flag&&b3flag){
						BigDecimal b1 = new BigDecimal(submitGoods.goods_pifajiage1);
						BigDecimal b3 = new BigDecimal(submitGoods.goods_pifajiage3);
						if(b3.compareTo(b1)==1){//�۸�3���ڼ۸�1
							 tempError = errorMap.get("10");
							returnMessage.put(tempError.error_id, tempError.error_detail);
						}
					}
					if(b2flag&&b3flag){
						BigDecimal b2 = new BigDecimal(submitGoods.goods_pifajiage2);
						BigDecimal b3 = new BigDecimal(submitGoods.goods_pifajiage3);
						if(b3.compareTo(b2)==1){//�۸�3���ڼ۸�2
							 tempError = errorMap.get("10");
							returnMessage.put(tempError.error_id, tempError.error_detail);
						}
					}
				}
			}else{//�����۸�3δѡ��
				b3flag=false;
			}
			
		}
		//�������ۼ�
		boolean j1flag = true;//�������ۼ��Ƿ�Ϸ�
		if(StringUtils.isEmpty(submitGoods.goods_jiage) || CommonStringUtil.getTrueLength(submitGoods.goods_jiage)>8 ||  !submitGoods.goods_jiage.matches(CommonKey.exp4) || "0".equals(submitGoods.goods_jiage) ){
			 tempError = errorMap.get("11");
			returnMessage.put(tempError.error_id, tempError.error_detail);
			j1flag=false;
		}else{
			if(j1flag&&b1flag){//�������ۼ۱�����������۸�1
				BigDecimal b1 = new BigDecimal(submitGoods.goods_pifajiage1);
				BigDecimal j1 = new BigDecimal(submitGoods.goods_jiage);
				if(j1.compareTo(b1) != 1){//�������ۼ۲����ڼ۸�1
					 tempError = errorMap.get("12");
					returnMessage.put(tempError.error_id, tempError.error_detail);
				}
			}
		}
		//��С������
		if(StringUtils.isEmpty(submitGoods.goods_zuixiao_count) || CommonStringUtil.getTrueLength(submitGoods.goods_zuixiao_count)>8 ||  !submitGoods.goods_zuixiao_count.matches(CommonKey.exp3) || "0".equals(submitGoods.goods_zuixiao_count) ){
			 tempError = errorMap.get("13");
			returnMessage.put(tempError.error_id, tempError.error_detail);
		}
		//��Ʒ����
		if(StringUtils.isEmpty(submitGoods.goods_type_id_lv1)||  
			StringUtils.isEmpty(submitGoods.goods_type_id_lv2)||
			StringUtils.isEmpty(submitGoods.goods_type_id)||
			!submitGoods.goods_type_id_lv1.matches(CommonKey.exp3) ||  !submitGoods.goods_type_id_lv2.matches(CommonKey.exp3) ||  !submitGoods.goods_type_id.matches(CommonKey.exp3)){
			 tempError = errorMap.get("25");
			returnMessage.put(tempError.error_id, tempError.error_detail);
		}
		//ֻ��Ϊ����Ʒ
		if(StringUtils.isEmpty(submitGoods.goods_notsale)|| !submitGoods.goods_notsale.matches(CommonKey.exp5) ){
			 tempError = errorMap.get("26");
			returnMessage.put(tempError.error_id, tempError.error_detail);
		}
		//�Ƿ�ֱ���ϼ�
		if(StringUtils.isEmpty(submitGoods.goods_shangjia)||  !submitGoods.goods_shangjia.matches(CommonKey.exp5) ){
			 tempError = errorMap.get("27");
			returnMessage.put(tempError.error_id, tempError.error_detail);
		}
		//�Ƿ�Ϊ�ؼ���Ʒ
		if(StringUtils.isEmpty(submitGoods.goods_tejia)|| !submitGoods.goods_tejia.matches(CommonKey.exp5) ){
			 tempError = errorMap.get("28");
			returnMessage.put(tempError.error_id, tempError.error_detail);
		}
		//�ϴ���Ʒ��ͼ��Ƭ
		if(StringUtils.isEmpty(submitGoods.goods_big_pic1)|| !submitGoods.goods_big_pic1.matches(CommonKey.exp6) ){
			 tempError = errorMap.get("29");
			returnMessage.put(tempError.error_id, tempError.error_detail);
		}
		//�ϴ���Ʒ������Ƭ
		if(StringUtils.isEmpty(submitGoods.goods_mid_pic1)|| !submitGoods.goods_mid_pic1.matches(CommonKey.exp6) ){
			 tempError = errorMap.get("30");
			returnMessage.put(tempError.error_id, tempError.error_detail);
		}		
		//��Ʒ������Ƭ
		if( StringUtils.isEmpty(submitGoods.goods_small_pic1)||!submitGoods.goods_small_pic1.matches(CommonKey.exp6) ){
			 tempError = errorMap.get("31");
			returnMessage.put(tempError.error_id, tempError.error_detail);
		}				
		//--------------------�Ǳ�����Ŀ��ʼ--------------------------
		//������
		if(!StringUtils.isEmpty(submitGoods.goods_tiaoxingma)){
			if(!StringUtil.isBarCode(submitGoods.goods_tiaoxingma)){
				 tempError = errorMap.get("14");
				returnMessage.put(tempError.error_id, tempError.error_detail);
			}
		}
		//��ζ
		if(!StringUtils.isEmpty(submitGoods.goods_shuoming1)){
			if(StringUtils.isEmpty(submitGoods.goods_shuoming1) || CommonStringUtil.getTrueLength(submitGoods.goods_shuoming1)>50 || !submitGoods.goods_shuoming1.matches(CommonKey.exp2) ){
				 tempError = errorMap.get("15");
				returnMessage.put(tempError.error_id, tempError.error_detail);
			}
		}
		//���
		if(!StringUtils.isEmpty(submitGoods.goods_guige)){
			if(StringUtils.isEmpty(submitGoods.goods_guige) || CommonStringUtil.getTrueLength(submitGoods.goods_guige)>8 || !submitGoods.goods_guige.matches(CommonKey.exp2) ){
				 tempError = errorMap.get("16");
				returnMessage.put(tempError.error_id, tempError.error_detail);
			}
		}
		//����
		if(!StringUtils.isEmpty(submitGoods.goods_made_address)){
			if(StringUtils.isEmpty(submitGoods.goods_made_address) || CommonStringUtil.getTrueLength(submitGoods.goods_made_address)>20 || !submitGoods.goods_made_address.matches(CommonKey.exp2) ){
				 tempError = errorMap.get("20");
				returnMessage.put(tempError.error_id, tempError.error_detail);
			}
		}
		//��������
		if(!StringUtils.isEmpty(submitGoods.goods_made_factory)){
			if(StringUtils.isEmpty(submitGoods.goods_made_factory) || CommonStringUtil.getTrueLength(submitGoods.goods_made_factory)>50 || !submitGoods.goods_made_factory.matches(CommonKey.exp2) ){
				 tempError = errorMap.get("21");
				returnMessage.put(tempError.error_id, tempError.error_detail);
			}
		}
		//��Ʒ˵��
		if(!StringUtils.isEmpty(submitGoods.goods_shuoming3)){
			if(StringUtils.isEmpty(submitGoods.goods_shuoming3) || CommonStringUtil.getTrueLength(submitGoods.goods_shuoming3)>800 || !submitGoods.goods_shuoming3.matches(CommonKey.exp1) ){
				 tempError = errorMap.get("22");
				returnMessage.put(tempError.error_id, tempError.error_detail);
			}
		}
		//��������ŵ
		if(!StringUtils.isEmpty(submitGoods.goods_fuwuchengnuo)){
			if(StringUtils.isEmpty(submitGoods.goods_fuwuchengnuo) || CommonStringUtil.getTrueLength(submitGoods.goods_fuwuchengnuo)>200 || !submitGoods.goods_fuwuchengnuo.matches(CommonKey.exp1) ){
				 tempError = errorMap.get("23");
				returnMessage.put(tempError.error_id, tempError.error_detail);
			}
		}
		//��ܰ��ʾ
		if(!StringUtils.isEmpty(submitGoods.goods_wenxintishi)){
			if(StringUtils.isEmpty(submitGoods.goods_wenxintishi) || CommonStringUtil.getTrueLength(submitGoods.goods_wenxintishi)>200 || !submitGoods.goods_wenxintishi.matches(CommonKey.exp1) ){
				 tempError = errorMap.get("24");
				returnMessage.put(tempError.error_id, tempError.error_detail);
			}
		}
		return returnMessage;
	}

	
}