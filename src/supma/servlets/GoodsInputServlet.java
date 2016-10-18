package supma.servlets;

import javax.imageio.ImageIO;
import javax.imageio.stream.*;
import javax.servlet.*;
import javax.servlet.http.*;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.lang3.StringUtils;

import java.awt.image.BufferedImage;
import java.io.*;
import java.net.InetAddress;
import java.net.URLDecoder;
import java.net.UnknownHostException;

import supma.common.CommonKey;
import supma.common.CommonValidate;
import supma.db.BasicDatabase;
import supma.log.LogManager;
import supma.util.BaseGetIP;
import supma.util.StringUtil;

import java.text.SimpleDateFormat;
import java.util.*;
import java.util.Date;

import supma.beans.*;

import java.sql.*;
import net.sf.json.JSONArray;

public class GoodsInputServlet
    extends HttpServlet {
	


  /**
	 * 
	 */
	private static final long serialVersionUID = 597684511931133438L;
public void doGet(HttpServletRequest req, HttpServletResponse res) throws
      IOException, ServletException{
	  doPost(req,res);
  }


  
  public void doPost(HttpServletRequest req, HttpServletResponse res) throws
      IOException, ServletException {
	  System.out.println("innnn");
      	req.setCharacterEncoding("utf-8"); // 设置编码  
      	res.setCharacterEncoding("utf-8");  
      	res.setContentType("text/html;charset=UTF-8");  
      	//session
        HttpSession session = req.getSession(true);
      	//log
  		LogManager lm = LogManager.getInstance(this.getServletContext().getRealPath(CommonKey.logDir));
  		//print out
  		PrintWriter out = res.getWriter();
  		//用户信息
  		userBean ufo = (userBean)session.getAttribute("loginUserInfo") ;//因为登陆了，所以从session里取得
  		if(ufo == null){
  			//如果session里没有信息，跳到登陆页面
  	  		res.sendRedirect("notlogin.aspx");
  	  		return;
  		}else{
  	  		ufo.user_notuser11=BaseGetIP.getIpAddr(req);
  	  		String action = req.getParameter("action");
  	  		System.out.println("action:"+action+"**");
  	  		if("init".equals(action)){
  	  			req.getRequestDispatcher("inputgoods.aspx").forward(req, res);//res.sendRedirect("inputgoods.aspx");这样，是重定向，request不是原来的request了
  	  	  		//res.sendRedirect("inputgoods.aspx");
  	  		}else if("copy_next".equals(action) || "notcopy_next".equals(action)){
  	  			GoodsBean uicontent = setUIdata(req);
  	  			CommonValidate validater = new CommonValidate(req.getServletContext());
  	  			LinkedHashMap<String,String> validateresult = validater.ValidateInputGoods(uicontent);
  	  			if(validateresult==null || validateresult.size()==0){//校验没错。插入数据库，并且request内放入成功提示信息
  	  				HashMap<String,ErrorMessageBean>errorMap =(HashMap<String, ErrorMessageBean>) req.getServletContext().getAttribute(CommonKey.errorMessageKey);
  	  				uicontent=setIpandDate(uicontent,ufo);//插入数据库前，把录入人信息写入bean
	  	  			//保存用户信息到数据库
	  	  			int insertcount=0;
	  	  			try{
	  	  				insertcount=uicontent.insertOneRow(uicontent,ufo);
	  	  			}catch(Exception e){
	  	  				e.printStackTrace();
	  	  				lm.println("E", "LoginServlet", "doPost", "致命错误:"+e,ufo);
	  	  			}
	  	  			if(insertcount>0){
		  	  			//数据录入成功，提示成功信息
	  	  				req.setAttribute(CommonKey.submitsuccesskey, errorMap.get("999").error_detail);
	  	  				if("copy_next".equals(action) ){
	  	  					req.setAttribute("uicontent", uicontent);//把页面数据放到request里，供回显用
	  	  				}
	  	  			}else{
	  	  				//数据未成功录入，提示错误信息
	  	  				LinkedHashMap<String,String>errormessageMap = new LinkedHashMap<String,String>();
	  	  				errormessageMap.put("998", errorMap.get("998").error_detail);
	  	  				req.setAttribute(CommonKey.requestScopeerrorMessage, errormessageMap);
	  	  	  			req.setAttribute("uicontent", uicontent);//把页面数据放到request里，供回显用
	  	  			}
  	  			}else{
  	  	  			req.setAttribute(CommonKey.requestScopeerrorMessage, validateresult);
  	  	  			req.setAttribute("uicontent", uicontent);//把页面数据放到request里，供回显用
  	  			}
  	  			req.getRequestDispatcher("inputgoods.aspx").forward(req, res);	  	  	
  	  		}
  		}
  }
 
//把录入日期和录入人ip写入bean
  private GoodsBean setIpandDate(GoodsBean _bean,userBean _ufo){
	  _bean.goods_modify_user_ip=_ufo.user_notuser11;
	  _bean.goods_modify_user=_ufo.user_id;
		Date now = new Date(); 
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	 _bean.goods_input_date=dateFormat.format(now);
	 _bean.goods_modify_date=	 _bean.goods_input_date;
	 //是否直接上架Y的时候，设置上架日
     if(CommonKey.goods_flag_y.equals(_bean.goods_shangjia)){
    	 _bean.goods_shangjia_date= _bean.goods_input_date;
     }
     //是否通过审核N
     _bean.goods_shenhe=CommonKey.goods_flag_n;
     //商品所有批发商
     _bean.goods_pifashang=_ufo.user_id;
     //搭赠品为空时候，清除所有搭赠信息
     if(StringUtils.isEmpty(_bean.goods_dazeng1)){
    	 _bean.goods_dazeng_tiaojian1="";
    	 _bean.goods_dazeng_count1="";
    	 _bean.goods_dazeng_tflag1=CommonKey.goods_flag_n;
    	 _bean.goods_dazeng_shuoming1="";
     }
     if(StringUtils.isEmpty(_bean.goods_dazeng2)){
    	 _bean.goods_dazeng_tiaojian2="";
    	 _bean.goods_dazeng_count2="";
    	 _bean.goods_dazeng_tflag2=CommonKey.goods_flag_n;
    	 _bean.goods_dazeng_shuoming2="";
     }
     //商品批发价格1为固定价时候，清除价格2和价格3
     if(CommonKey.pifashuliang1Value_guding.equals(_bean.goods_pifashuliang1)){
    	 _bean.goods_pifashuliang2=CommonKey.pifashuliang2Value_guding;
    	 _bean.goods_pifajiage2="";
    	 _bean.goods_pifashuliang3=CommonKey.pifashuliang2Value_guding;
    	 _bean.goods_pifajiage3="";
     }else{
         if(CommonKey.pifashuliang2Value_guding.equals(_bean.goods_pifashuliang2)){
        	 _bean.goods_pifajiage2="";
         }
         if(CommonKey.pifashuliang2Value_guding.equals(_bean.goods_pifashuliang3)){
        	 _bean.goods_pifajiage3="";
         }
     }

	  return _bean;
  }
//从画面取得数据。放到ban里面  
  private GoodsBean setUIdata(HttpServletRequest req){
	  GoodsBean submitGoods = new GoodsBean();
		submitGoods.goods_name=req.getParameter("goods_name");
		submitGoods.goods_pinpai=req.getParameter("goods_pinpai");
		submitGoods.goods_zhongliang=req.getParameter("goods_zhongliang");
		submitGoods.goods_kucunshu=req.getParameter("goods_kucunshu");
		submitGoods.goods_baozhi_date=req.getParameter("goods_baozhi_date");
		submitGoods.goods_pifashuliang1=req.getParameter("goods_pifashuliang1");
		submitGoods.goods_pifashuliang2=req.getParameter("goods_pifashuliang2");
		submitGoods.goods_pifashuliang3=req.getParameter("goods_pifashuliang3");
		submitGoods.goods_pifajiage1=req.getParameter("goods_pifajiage1");
		submitGoods.goods_pifajiage2=req.getParameter("goods_pifajiage2");
		submitGoods.goods_pifajiage3=req.getParameter("goods_pifajiage3");
		submitGoods.goods_jiage=req.getParameter("goods_jiage");
		submitGoods.goods_zuixiao_count=req.getParameter("goods_zuixiao_count");
		submitGoods.goods_type_id_lv1=req.getParameter("goods_type_id_lv1");
		submitGoods.goods_type_id_lv2=req.getParameter("goods_type_id_lv2");
		submitGoods.goods_type_id=req.getParameter("goods_type_id");
		submitGoods.goods_notsale=req.getParameter("goods_notsale");
		submitGoods.goods_shangjia=req.getParameter("goods_shangjia");
		submitGoods.goods_tejia=req.getParameter("goods_tejia");
		submitGoods.goods_big_pic1=req.getParameter("viewfile");//photo
		submitGoods.goods_mid_pic1=req.getParameter("viewfile2");
		submitGoods.goods_small_pic1=req.getParameter("viewfile3");
		submitGoods.goods_tiaoxingma=req.getParameter("goods_tiaoxingma");
		submitGoods.goods_shuoming1=req.getParameter("goods_shuoming1");
		submitGoods.goods_guige=req.getParameter("goods_guige");
		submitGoods.goods_made_address=req.getParameter("goods_made_address");
		submitGoods.goods_made_factory=req.getParameter("goods_made_factory");
		submitGoods.goods_shuoming3=req.getParameter("goods_shuoming3");
		submitGoods.goods_fuwuchengnuo=req.getParameter("goods_fuwuchengnuo");
		submitGoods.goods_wenxintishi=req.getParameter("goods_wenxintishi");
		submitGoods.goods_dazeng1=req.getParameter("goods_dazeng1");
		submitGoods.goods_dazeng_tiaojian1=req.getParameter("goods_dazeng_tiaojian1");
		submitGoods.goods_dazeng_count1=req.getParameter("goods_dazeng_count1");
		submitGoods.goods_dazeng_tflag1=req.getParameter("goods_dazeng_tflag1");
		submitGoods.goods_dazeng_shuoming1=req.getParameter("goods_dazeng_shuoming1");
		submitGoods.goods_dazeng2=req.getParameter("goods_dazeng2");
		submitGoods.goods_dazeng_tiaojian2=req.getParameter("goods_dazeng_tiaojian2");
		submitGoods.goods_dazeng_count2=req.getParameter("goods_dazeng_count2");
		submitGoods.goods_dazeng_tflag2=req.getParameter("goods_dazeng_tflag2");
		submitGoods.goods_dazeng_shuoming2=req.getParameter("goods_dazeng_shuoming2");
		submitGoods.goods_huodong=req.getParameter("goods_huodong");

	  return submitGoods;
  }

}