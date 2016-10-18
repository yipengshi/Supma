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
		//商品名称
		if(StringUtils.isEmpty(submitGoods.goods_name) || CommonStringUtil.getTrueLength(submitGoods.goods_name)>80 ||  !submitGoods.goods_name.matches(CommonKey.exp2)){
			 tempError = errorMap.get("1");
			returnMessage.put(tempError.error_id, tempError.error_detail);
		}
		//商品品牌
		if(StringUtils.isEmpty(submitGoods.goods_pinpai) || CommonStringUtil.getTrueLength(submitGoods.goods_pinpai)>20 || !submitGoods.goods_name.matches(CommonKey.exp2) ){
			 tempError = errorMap.get("2");
			returnMessage.put(tempError.error_id, tempError.error_detail);
		}
		//包装重量
		if(StringUtils.isEmpty(submitGoods.goods_zhongliang) || CommonStringUtil.getTrueLength(submitGoods.goods_zhongliang)>8 || !submitGoods.goods_zhongliang.matches(CommonKey.exp3) || "0".equals(submitGoods.goods_zhongliang) ){
			 tempError = errorMap.get("3");
			returnMessage.put(tempError.error_id, tempError.error_detail);
		}
		//库存数量(大于0)
		if(StringUtils.isEmpty(submitGoods.goods_kucunshu) || CommonStringUtil.getTrueLength(submitGoods.goods_kucunshu)>8 ||  !submitGoods.goods_kucunshu.matches(CommonKey.exp3) || "0".equals(submitGoods.goods_kucunshu) ){
			 tempError = errorMap.get("4");
			returnMessage.put(tempError.error_id, tempError.error_detail);
		}	
		//保质期
		if(StringUtils.isEmpty(submitGoods.goods_baozhi_date) || CommonStringUtil.getTrueLength(submitGoods.goods_baozhi_date)>8 ||  !submitGoods.goods_baozhi_date.matches(CommonKey.exp3) || "0".equals(submitGoods.goods_baozhi_date) ){
			 tempError = errorMap.get("5");
			returnMessage.put(tempError.error_id, tempError.error_detail);
		}	
		//商品批发价格1
		boolean b1flag = true;//价格1是否合法
		boolean b2flag = true;//价格2是否合法
		boolean b3flag = true;//价格2是否合法
		if(StringUtils.isEmpty(submitGoods.goods_pifajiage1) || CommonStringUtil.getTrueLength(submitGoods.goods_pifajiage1)>8 ||  !submitGoods.goods_pifajiage1.matches(CommonKey.exp4) || "0".equals(submitGoods.goods_pifajiage1) ){
			 tempError = errorMap.get("6");
			returnMessage.put(tempError.error_id, tempError.error_detail);
			b1flag=false;
		}
		//批发数量为"固定价"时候,不做价格2和价格3的验证
		if(CommonKey.pifashuliang1Value_guding.equals(submitGoods.goods_pifashuliang1)){
			submitGoods.goods_pifajiage2="";
			submitGoods.goods_pifajiage3="";
			submitGoods.goods_pifashuliang2=CommonKey.pifashuliang2Value_guding;
			submitGoods.goods_pifashuliang3=CommonKey.pifashuliang2Value_guding;
		}else if(StringUtils.isEmpty(submitGoods.goods_pifashuliang1)){
			 tempError = errorMap.get("32");
			returnMessage.put(tempError.error_id, tempError.error_detail);
		}else{
			 if(StringUtils.isEmpty(submitGoods.goods_pifashuliang2)){//批发价格2的订货数量选择为空(并非是未选择（0）)
				 tempError = errorMap.get("33");
				 returnMessage.put(tempError.error_id, tempError.error_detail);
				 b2flag=false;
			 }else if(!CommonKey.pifashuliang2Value_guding.equals(submitGoods.goods_pifashuliang2)){//批发价格2的数量不是“未选择”时候，做价格验证
				BigDecimal p1 = new BigDecimal(submitGoods.goods_pifashuliang1);//批发价格1的订货数量
				BigDecimal p2 = new BigDecimal(submitGoods.goods_pifashuliang2);//批发价格2的订货数量
				if(p1.compareTo(p2)==1){//数量1大于数量2
					 tempError = errorMap.get("17");
					returnMessage.put(tempError.error_id, tempError.error_detail);
				}			
				if(StringUtils.isEmpty(submitGoods.goods_pifajiage2) || CommonStringUtil.getTrueLength(submitGoods.goods_pifajiage2)>8 ||  !submitGoods.goods_pifajiage2.matches(CommonKey.exp4) || "0".equals(submitGoods.goods_pifajiage2) ){
					 tempError = errorMap.get("7");
					returnMessage.put(tempError.error_id, tempError.error_detail);
					b2flag=false;
				}else{//批发价格2必须小于等于批发价格1(订货越多越便宜)
					if(b1flag&&b2flag){
						BigDecimal b1 = new BigDecimal(submitGoods.goods_pifajiage1);
						BigDecimal b2 = new BigDecimal(submitGoods.goods_pifajiage2);
						if(b2.compareTo(b1)==1){//价格2大于价格1
							 tempError = errorMap.get("9");
							returnMessage.put(tempError.error_id, tempError.error_detail);
						}
					}
				}
			}else{//批发价格2未选择
				b2flag=false;
			}
			 if(StringUtils.isEmpty(submitGoods.goods_pifashuliang3)){//批发价格3的订货数量选择为空(并非是未选择（0）)
				 tempError = errorMap.get("34");
				 returnMessage.put(tempError.error_id, tempError.error_detail);
				 b3flag=false;
			 }else if(!CommonKey.pifashuliang2Value_guding.equals(submitGoods.goods_pifashuliang3)){//批发价格3的数量不是“未选择”时候，做价格验证
				BigDecimal p1 = new BigDecimal(submitGoods.goods_pifashuliang1);//批发价格1的订货数量
				BigDecimal p2 = new BigDecimal(submitGoods.goods_pifashuliang2);//批发价格2的订货数量
				BigDecimal p3 = new BigDecimal(submitGoods.goods_pifashuliang3);//批发价格3的订货数量
				if(p1.compareTo(p3)==1){//数量1大于数量3
					 tempError = errorMap.get("18");
					returnMessage.put(tempError.error_id, tempError.error_detail);
				}	
				if(!CommonKey.pifashuliang2Value_guding.equals(submitGoods.goods_pifashuliang2)){//批发价格2的数量不是“未选择”时候，做价格验证
					if(p2.compareTo(p3)==1){//数量2大于数量3
						 tempError = errorMap.get("19");
						returnMessage.put(tempError.error_id, tempError.error_detail);
					}	
				}
				if(StringUtils.isEmpty(submitGoods.goods_pifajiage3) || CommonStringUtil.getTrueLength(submitGoods.goods_pifajiage3)>8 ||  !submitGoods.goods_pifajiage3.matches(CommonKey.exp4) || "0".equals(submitGoods.goods_pifajiage3) ){
					 tempError = errorMap.get("8");
					returnMessage.put(tempError.error_id, tempError.error_detail);
					b3flag=false;
				}else{//批发价格3必须小于等于批发价格1,2(订货越多越便宜)
					if(b1flag&&b3flag){
						BigDecimal b1 = new BigDecimal(submitGoods.goods_pifajiage1);
						BigDecimal b3 = new BigDecimal(submitGoods.goods_pifajiage3);
						if(b3.compareTo(b1)==1){//价格3大于价格1
							 tempError = errorMap.get("10");
							returnMessage.put(tempError.error_id, tempError.error_detail);
						}
					}
					if(b2flag&&b3flag){
						BigDecimal b2 = new BigDecimal(submitGoods.goods_pifajiage2);
						BigDecimal b3 = new BigDecimal(submitGoods.goods_pifajiage3);
						if(b3.compareTo(b2)==1){//价格3大于价格2
							 tempError = errorMap.get("10");
							returnMessage.put(tempError.error_id, tempError.error_detail);
						}
					}
				}
			}else{//批发价格3未选择
				b3flag=false;
			}
			
		}
		//建议零售价
		boolean j1flag = true;//建议零售价是否合法
		if(StringUtils.isEmpty(submitGoods.goods_jiage) || CommonStringUtil.getTrueLength(submitGoods.goods_jiage)>8 ||  !submitGoods.goods_jiage.matches(CommonKey.exp4) || "0".equals(submitGoods.goods_jiage) ){
			 tempError = errorMap.get("11");
			returnMessage.put(tempError.error_id, tempError.error_detail);
			j1flag=false;
		}else{
			if(j1flag&&b1flag){//建议零售价必须大于批发价格1
				BigDecimal b1 = new BigDecimal(submitGoods.goods_pifajiage1);
				BigDecimal j1 = new BigDecimal(submitGoods.goods_jiage);
				if(j1.compareTo(b1) != 1){//建议零售价不大于价格1
					 tempError = errorMap.get("12");
					returnMessage.put(tempError.error_id, tempError.error_detail);
				}
			}
		}
		//最小起批量
		if(StringUtils.isEmpty(submitGoods.goods_zuixiao_count) || CommonStringUtil.getTrueLength(submitGoods.goods_zuixiao_count)>8 ||  !submitGoods.goods_zuixiao_count.matches(CommonKey.exp3) || "0".equals(submitGoods.goods_zuixiao_count) ){
			 tempError = errorMap.get("13");
			returnMessage.put(tempError.error_id, tempError.error_detail);
		}
		//商品类型
		if(StringUtils.isEmpty(submitGoods.goods_type_id_lv1)||  
			StringUtils.isEmpty(submitGoods.goods_type_id_lv2)||
			StringUtils.isEmpty(submitGoods.goods_type_id)||
			!submitGoods.goods_type_id_lv1.matches(CommonKey.exp3) ||  !submitGoods.goods_type_id_lv2.matches(CommonKey.exp3) ||  !submitGoods.goods_type_id.matches(CommonKey.exp3)){
			 tempError = errorMap.get("25");
			returnMessage.put(tempError.error_id, tempError.error_detail);
		}
		//只作为搭赠品
		if(StringUtils.isEmpty(submitGoods.goods_notsale)|| !submitGoods.goods_notsale.matches(CommonKey.exp5) ){
			 tempError = errorMap.get("26");
			returnMessage.put(tempError.error_id, tempError.error_detail);
		}
		//是否直接上架
		if(StringUtils.isEmpty(submitGoods.goods_shangjia)||  !submitGoods.goods_shangjia.matches(CommonKey.exp5) ){
			 tempError = errorMap.get("27");
			returnMessage.put(tempError.error_id, tempError.error_detail);
		}
		//是否为特价商品
		if(StringUtils.isEmpty(submitGoods.goods_tejia)|| !submitGoods.goods_tejia.matches(CommonKey.exp5) ){
			 tempError = errorMap.get("28");
			returnMessage.put(tempError.error_id, tempError.error_detail);
		}
		//上传商品主图照片
		if(StringUtils.isEmpty(submitGoods.goods_big_pic1)|| !submitGoods.goods_big_pic1.matches(CommonKey.exp6) ){
			 tempError = errorMap.get("29");
			returnMessage.put(tempError.error_id, tempError.error_detail);
		}
		//上传商品侧面照片
		if(StringUtils.isEmpty(submitGoods.goods_mid_pic1)|| !submitGoods.goods_mid_pic1.matches(CommonKey.exp6) ){
			 tempError = errorMap.get("30");
			returnMessage.put(tempError.error_id, tempError.error_detail);
		}		
		//商品其他照片
		if( StringUtils.isEmpty(submitGoods.goods_small_pic1)||!submitGoods.goods_small_pic1.matches(CommonKey.exp6) ){
			 tempError = errorMap.get("31");
			returnMessage.put(tempError.error_id, tempError.error_detail);
		}				
		//--------------------非必须项目开始--------------------------
		//条形码
		if(!StringUtils.isEmpty(submitGoods.goods_tiaoxingma)){
			if(!StringUtil.isBarCode(submitGoods.goods_tiaoxingma)){
				 tempError = errorMap.get("14");
				returnMessage.put(tempError.error_id, tempError.error_detail);
			}
		}
		//口味
		if(!StringUtils.isEmpty(submitGoods.goods_shuoming1)){
			if(StringUtils.isEmpty(submitGoods.goods_shuoming1) || CommonStringUtil.getTrueLength(submitGoods.goods_shuoming1)>50 || !submitGoods.goods_shuoming1.matches(CommonKey.exp2) ){
				 tempError = errorMap.get("15");
				returnMessage.put(tempError.error_id, tempError.error_detail);
			}
		}
		//规格
		if(!StringUtils.isEmpty(submitGoods.goods_guige)){
			if(StringUtils.isEmpty(submitGoods.goods_guige) || CommonStringUtil.getTrueLength(submitGoods.goods_guige)>8 || !submitGoods.goods_guige.matches(CommonKey.exp2) ){
				 tempError = errorMap.get("16");
				returnMessage.put(tempError.error_id, tempError.error_detail);
			}
		}
		//产地
		if(!StringUtils.isEmpty(submitGoods.goods_made_address)){
			if(StringUtils.isEmpty(submitGoods.goods_made_address) || CommonStringUtil.getTrueLength(submitGoods.goods_made_address)>20 || !submitGoods.goods_made_address.matches(CommonKey.exp2) ){
				 tempError = errorMap.get("20");
				returnMessage.put(tempError.error_id, tempError.error_detail);
			}
		}
		//生产厂家
		if(!StringUtils.isEmpty(submitGoods.goods_made_factory)){
			if(StringUtils.isEmpty(submitGoods.goods_made_factory) || CommonStringUtil.getTrueLength(submitGoods.goods_made_factory)>50 || !submitGoods.goods_made_factory.matches(CommonKey.exp2) ){
				 tempError = errorMap.get("21");
				returnMessage.put(tempError.error_id, tempError.error_detail);
			}
		}
		//商品说明
		if(!StringUtils.isEmpty(submitGoods.goods_shuoming3)){
			if(StringUtils.isEmpty(submitGoods.goods_shuoming3) || CommonStringUtil.getTrueLength(submitGoods.goods_shuoming3)>800 || !submitGoods.goods_shuoming3.matches(CommonKey.exp1) ){
				 tempError = errorMap.get("22");
				returnMessage.put(tempError.error_id, tempError.error_detail);
			}
		}
		//特殊服务承诺
		if(!StringUtils.isEmpty(submitGoods.goods_fuwuchengnuo)){
			if(StringUtils.isEmpty(submitGoods.goods_fuwuchengnuo) || CommonStringUtil.getTrueLength(submitGoods.goods_fuwuchengnuo)>200 || !submitGoods.goods_fuwuchengnuo.matches(CommonKey.exp1) ){
				 tempError = errorMap.get("23");
				returnMessage.put(tempError.error_id, tempError.error_detail);
			}
		}
		//温馨提示
		if(!StringUtils.isEmpty(submitGoods.goods_wenxintishi)){
			if(StringUtils.isEmpty(submitGoods.goods_wenxintishi) || CommonStringUtil.getTrueLength(submitGoods.goods_wenxintishi)>200 || !submitGoods.goods_wenxintishi.matches(CommonKey.exp1) ){
				 tempError = errorMap.get("24");
				returnMessage.put(tempError.error_id, tempError.error_detail);
			}
		}
		return returnMessage;
	}

	
}