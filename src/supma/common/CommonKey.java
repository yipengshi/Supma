package supma.common;

public class CommonKey{
	
	private static final long serialVersionUID = 18L;
	
	//log���
	public static String logLevel = "INFO";
	public static String logDir = "log/online";
	public static String logFileName = "supmalog";
	public static String logWriteMode = "new";
	//upload���
	public static String uploadDir_chaoshizhizhao = "WEB-INF/upload/ZZ";//����ִ��ͼƬ21λ
	public static String uploadDir_chaoshizhizhao_tmp = "WEB-INF/upload/chaoshizhizhao/temp";//����ִ��ͼƬ��ʱ�ļ���
	public static String uploadFile_chaoshizhizhao_tempfile = "tempzhizhao";//����ִ��ͼƬ��ʱ�ļ���
	public static long uploadmaxsize_zhizhao=1024*1024*2;//Ӫҵִ�����2M
	//�û����
	public static String user_type1 = "1";//����
	public static String user_type2 = "2";//������
	public static String user_type3 = "3";//����Ա
	public static String user_type1_name = "����";//����
	public static String user_type2_name = "������";//����
	public static String user_type3_name = "����Ա";//����
	public static String user_levle_1 = "1";//�û��ȼ������)
	public static String user_levle_2 = "2";//�û��ȼ�
	public static String user_levle_3 = "3";//�û��ȼ�
	public static String user_levle_4 = "4";//�û���
	public static String user_levle_5 = "5";//�û��ȼ�
	public static String user_islock_0 = "0";//û�����û�
	public static String user_islock_1 = "1";///��Ϊĳ�±�����
	public static String user_islock_2 = "2";//��ע���û�(�����)
	
	//����������Ƭ��
	public static String[] pianqu_id ={"00","01","02"};
	public static String[] pianqu_name ={"��ɽ��","������","ɳ�ӿ���"};
	//����
	public static String jinghao = "#";
	public static String db_mysql ="mysql";
	public static String db_db2="db2";
	//public static String use_db="db2";
	public static String use_db="mysql";
	public static long goods_id_plus=5;
	
	//indexҳ������
	public static String index_show_pifashang = "7";//index����¥����ʾ������������
	public static String index_show_shangpin= "5";	//index����¥����ʾ��ƷͼƬ��
	public static String index_show_pinpai= "8";	//index����¥����ʾ����Ʒ������
	
	//��Ʒ�������
	public static String goods_shangjia = "Y";//��Ʒ�ϼ�
	public static String goods_not_shangjia = "N";//��Ʒ���ϼ�
	public static String goods_shenhezhong= "N";//�ϼ������
	public static String goods_shenheWan= "Y";//�ϼ�������
	
	//flag��ص�Y/N
	public static String goods_flag_n = "N";//N
	public static String goods_flag_y = "Y";//Y
	
	//application key���
	public static String goodsTypeListData = "goodsTypeListData";//��Ʒ�����б�����(josn)��������servletcontext��)
	public static String goodsTypeListData_1 = "goodsTypeListData_1";//��Ʒ1���б����ݡ�������servletcontext��)
	public static String goodsTypeListData_2 = "goodsTypeListData_2";//��Ʒ2���б����ݡ�������servletcontext��)
	public static String goodsTypeListData_3 = "goodsTypeListData_3";//��Ʒ3���б����ݡ�������servletcontext��)
	//error key
	public static String errorMessageKey="errorMessageKey";//���д�����Ϣ��db��ȡ�ã��ŵ�application scope����
	public static String requestScopeerrorMessage="request_errorMessage";//request��Χ��У���õĴ�����Ϣ�洢
	//success key
	public static String submitsuccesskey="submit_successkey";//���ݲ���ɹ�����ʾ
	//����selectֵ�ĳ�ʼ��
	public static String [] pifashuliang1Value= {"1","5","10","20","50","100","101"};//�����۸�1
	public static String [] pifashuliang1Name= {"�̶���","1-5","6-10","11-20","21-50","51-100","100����"};//�����۸�1
	public static String  pifashuliang1Value_guding= "1";//�����۸�1Ϊ�̶��۸�ʱ��
	public static String  pifashuliang2Value_guding= "0";//�����۸�2,3Ϊ��δѡ��ʱ���ֵ
	
	public static String [] pifashuliang2Value= {"0","10","20","50","100","101"};//�����۸�2
	public static String [] pifashuliang2Name= {"δѡ��","6-10","11-20","21-50","51-100","100����"};//�����۸�2
	
	public static String [] pifashuliang3Value= {"0","20","50","100","101"};//�����۸�3
	public static String [] pifashuliang3Name= {"δѡ��","11-20","21-50","51-100","100����"};//�����۸�3
	
	public static String [] goods_notsale_Value= {CommonKey.goods_flag_n,CommonKey.goods_flag_y};//����Ʒ
	public static String [] goods_notsale_Name= {"����Ʒ","����Ʒ(ֻ����)"};//����Ʒ
	
	public static String [] goods_shangjia_Value= {CommonKey.goods_flag_y,CommonKey.goods_flag_n};//�Ƿ�ֱ���ϼ�
	public static String [] goods_shangjia_Name= {"��","��"};//�Ƿ�ֱ���ϼ�
	
	public static String [] goods_tejia_Value= {CommonKey.goods_flag_n,CommonKey.goods_flag_y};//�Ƿ�Ϊ�ؼ���Ʒ
	public static String [] goods_tejia_Name= {"��","��"};//�Ƿ�Ϊ�ؼ���Ʒ
	
	public static String [] tuihuan_Value= {CommonKey.goods_flag_n,CommonKey.goods_flag_y};//�Ƿ�Ϊ���˻���Ʒ
	public static String [] tuihuan_Name= {"�����˻�","���˻�"};//�Ƿ�Ϊ���˻���Ʒ 
	
	//У��������ʽ
	public static String exp1="^[A-Za-z0-9_:+() @\\-\\u0391-\\uFFE5]+$";//����Χ��\\u0391-\\uFFE5����˫�ֽ��ֺͷ��ű���ȫ�Ƿ��ţ�Ҳ����,������Ҳ�����ǿո�
	public static String exp2="^[A-Za-z0-9_+:��+()����\\-\\u4e00-\\u9fa5]+$";//����Χ��\\u4e00-\\u9fa5�����֡�������ȫ���ַ���������û�а�ǿո�
	public static String exp3="^[0-9]+$";//����Χ���������
	public static String exp4="^[0-9]+([.]{1}[0-9]+){0,1}$";//����Χ��������ּ�С����
	public static String exp5="^["+CommonKey.goods_flag_n+CommonKey.goods_flag_y+"]+$";//����Χ��Y,N
	public static String exp6="[^/\\\\:*?\"<>|]+$";//����Χ������"/"��"\"����ַ�һ�����������ļ�����֤
}