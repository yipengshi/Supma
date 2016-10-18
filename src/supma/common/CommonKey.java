package supma.common;

public class CommonKey{
	
	private static final long serialVersionUID = 18L;
	
	//log相关
	public static String logLevel = "INFO";
	public static String logDir = "log/online";
	public static String logFileName = "supmalog";
	public static String logWriteMode = "new";
	//upload相关
	public static String uploadDir_chaoshizhizhao = "WEB-INF/upload/ZZ";//超市执照图片21位
	public static String uploadDir_chaoshizhizhao_tmp = "WEB-INF/upload/chaoshizhizhao/temp";//超市执照图片临时文件夹
	public static String uploadFile_chaoshizhizhao_tempfile = "tempzhizhao";//超市执照图片临时文件名
	public static long uploadmaxsize_zhizhao=1024*1024*2;//营业执照最大2M
	//用户相关
	public static String user_type1 = "1";//超市
	public static String user_type2 = "2";//供货商
	public static String user_type3 = "3";//管理员
	public static String user_type1_name = "超市";//超市
	public static String user_type2_name = "批发商";//超市
	public static String user_type3_name = "管理员";//超市
	public static String user_levle_1 = "1";//用户等级（最低)
	public static String user_levle_2 = "2";//用户等级
	public static String user_levle_3 = "3";//用户等级
	public static String user_levle_4 = "4";//用户等
	public static String user_levle_5 = "5";//用户等级
	public static String user_islock_0 = "0";//没锁定用户
	public static String user_islock_1 = "1";///因为某事被锁定
	public static String user_islock_2 = "2";//新注册用户(待审核)
	
	//批发商配送片区
	public static String[] pianqu_id ={"00","01","02"};
	public static String[] pianqu_name ={"中山区","西岗区","沙河口区"};
	//其他
	public static String jinghao = "#";
	public static String db_mysql ="mysql";
	public static String db_db2="db2";
	//public static String use_db="db2";
	public static String use_db="mysql";
	public static long goods_id_plus=5;
	
	//index页面设置
	public static String index_show_pifashang = "7";//index各个楼层显示的批发商数量
	public static String index_show_shangpin= "5";	//index各个楼层显示商品图片数
	public static String index_show_pinpai= "8";	//index各个楼层显示畅销品牌数量
	
	//商品相关设置
	public static String goods_shangjia = "Y";//商品上架
	public static String goods_not_shangjia = "N";//商品不上架
	public static String goods_shenhezhong= "N";//上架审核中
	public static String goods_shenheWan= "Y";//上架审核完成
	
	//flag相关的Y/N
	public static String goods_flag_n = "N";//N
	public static String goods_flag_y = "Y";//Y
	
	//application key相关
	public static String goodsTypeListData = "goodsTypeListData";//商品三层列表数据(josn)。（放在servletcontext里)
	public static String goodsTypeListData_1 = "goodsTypeListData_1";//商品1层列表数据。（放在servletcontext里)
	public static String goodsTypeListData_2 = "goodsTypeListData_2";//商品2层列表数据。（放在servletcontext里)
	public static String goodsTypeListData_3 = "goodsTypeListData_3";//商品3层列表数据。（放在servletcontext里)
	//error key
	public static String errorMessageKey="errorMessageKey";//所有错误信息从db里取得，放到application scope里面
	public static String requestScopeerrorMessage="request_errorMessage";//request范围内校验用的错误信息存储
	//success key
	public static String submitsuccesskey="submit_successkey";//数据插入成功的提示
	//各种select值的初始化
	public static String [] pifashuliang1Value= {"1","5","10","20","50","100","101"};//批发价格1
	public static String [] pifashuliang1Name= {"固定价","1-5","6-10","11-20","21-50","51-100","100以上"};//批发价格1
	public static String  pifashuliang1Value_guding= "1";//批发价格1为固定价格时候
	public static String  pifashuliang2Value_guding= "0";//批发价格2,3为“未选择”时候的值
	
	public static String [] pifashuliang2Value= {"0","10","20","50","100","101"};//批发价格2
	public static String [] pifashuliang2Name= {"未选择","6-10","11-20","21-50","51-100","100以上"};//批发价格2
	
	public static String [] pifashuliang3Value= {"0","20","50","100","101"};//批发价格3
	public static String [] pifashuliang3Name= {"未选择","11-20","21-50","51-100","100以上"};//批发价格3
	
	public static String [] goods_notsale_Value= {CommonKey.goods_flag_n,CommonKey.goods_flag_y};//搭赠品
	public static String [] goods_notsale_Name= {"售卖品","非卖品(只搭赠)"};//搭赠品
	
	public static String [] goods_shangjia_Value= {CommonKey.goods_flag_y,CommonKey.goods_flag_n};//是否直接上架
	public static String [] goods_shangjia_Name= {"是","否"};//是否直接上架
	
	public static String [] goods_tejia_Value= {CommonKey.goods_flag_n,CommonKey.goods_flag_y};//是否为特价商品
	public static String [] goods_tejia_Name= {"否","是"};//是否为特价商品
	
	public static String [] tuihuan_Value= {CommonKey.goods_flag_n,CommonKey.goods_flag_y};//是否为可退换商品
	public static String [] tuihuan_Name= {"不可退换","可退换"};//是否为可退换商品 
	
	//校验正则表达式
	public static String exp1="^[A-Za-z0-9_:+() @\\-\\u0391-\\uFFE5]+$";//允许范围：\\u0391-\\uFFE5代表双字节字和符号比如全角符号＠也包括,这里面也允许半角空格
	public static String exp2="^[A-Za-z0-9_+:：+()（）\\-\\u4e00-\\u9fa5]+$";//允许范围：\\u4e00-\\u9fa5代表汉字。不包括全角字符。这里面没有半角空格
	public static String exp3="^[0-9]+$";//允许范围：半角数字
	public static String exp4="^[0-9]+([.]{1}[0-9]+){0,1}$";//允许范围：半角数字加小数点
	public static String exp5="^["+CommonKey.goods_flag_n+CommonKey.goods_flag_y+"]+$";//允许范围：Y,N
	public static String exp6="[^/\\\\:*?\"<>|]+$";//允许范围：除了"/"和"\"外的字符一或多个。用于文件名验证
}