CREATE  TABLE    goodstab
      (
        goods_id                                       CHAR(10)          NOT NULL WITH DEFAULT,--��Ʒid 0000000001��ʼ��������
        goods_name                                 varchar(80)          NOT NULL WITH DEFAULT,--��Ʒ��--
        goods_type_id                                CHAR(6)          NOT NULL WITH DEFAULT,--��Ʒ�������͵�����
        goods_type_id_lv1                                CHAR(6)          NOT NULL WITH DEFAULT,--��Ʒ�������͵�1��
        goods_type_id_lv2                                CHAR(6)          NOT NULL WITH DEFAULT,--��Ʒ�������͵�2��
        goods_pinpai                                varchar(20)          NOT NULL WITH DEFAULT,--��ƷƷ��--
         goods_made_address                   varchar(20)       NOT NULL WITH DEFAULT,--��Ʒ����--
         goods_made_factory                   varchar(50)       NOT NULL WITH DEFAULT,--��Ʒ��������--
        goods_made_date                           varchar(10)       NOT NULL WITH DEFAULT,--��Ʒ��������
        goods_bad_date                           varchar(10)       NOT NULL WITH DEFAULT,--��ƷʧЧ����
        goods_baozhi_date                           varchar(4)       NOT NULL WITH DEFAULT,--��Ʒ������(��)--
        goods_jiage                                         varchar(8)       NOT NULL WITH DEFAULT,--��Ʒ�۸� (�������ۼ�)--    
         goods_zhekou_jiage                  varchar(8)       NOT NULL WITH DEFAULT,--��Ʒ�ۿۺ�۸�
         goods_jinchiJiage                                         varchar(8)       NOT NULL WITH DEFAULT,--��Ʒ������۸� 
          goods_pifashang                  varchar(12)       NOT NULL WITH DEFAULT,--��Ʒ��Ӧ��������(id)
           goods_duanmingcheng                  varchar(200)       NOT NULL WITH DEFAULT,--��Ʒ�����ƣ�����������ѯ��)
           goods_tiaoxingma                  varchar(8)       NOT NULL WITH DEFAULT,--��Ʒ������--
           goods_guige                  varchar(8)       NOT NULL WITH DEFAULT,--��Ʒ���(�ͺ�)--
           goods_zhongliang                  varchar(8)       NOT NULL WITH DEFAULT,--��Ʒ��װ����(��)--
            goods_kucunshu                 varchar(8)       NOT NULL WITH DEFAULT,--��Ʒ����� --
            goods_xiaoliang                  varchar(8)       NOT NULL WITH DEFAULT,--��Ʒ���� 
             goods_tuihuoshu                  varchar(8)       NOT NULL WITH DEFAULT,--��Ʒ�˻�����
             goods_xiangoushuliang                  varchar(8)       NOT NULL WITH DEFAULT,--��Ʒ�޹�����--
             goods_jifen                  varchar(8)       NOT NULL WITH DEFAULT,--��Ʒ����1����û�����                                
             goods_liulanshu                  varchar(8)       NOT NULL WITH DEFAULT,--��Ʒ�������     
               goods_shangjia                  char(1)       NOT NULL WITH DEFAULT,--��Ʒ�Ƿ��ϼ�flag--
                goods_shenhe                 char(1)       NOT NULL WITH DEFAULT,--��Ʒͨ�����flag
                 goods_tejia                 char(1)       NOT NULL WITH DEFAULT,--��Ʒ�Ƿ��ؼ���Ʒ--              
                goods_huodong                 varchar(20)       NOT NULL WITH DEFAULT,--��Ʒ�μӵĻ--
               goods_dazeng1                 varchar(10)       NOT NULL WITH DEFAULT,--������Ʒid--
               goods_dazeng_tflag1                 varchar(1)       NOT NULL WITH DEFAULT,--������Ʒ�Ƿ���˻�(Y/N)
                 goods_dazeng_count1                 varchar(8)       NOT NULL WITH DEFAULT,--������Ʒ����
                 goods_dazeng_tiaojian1                 varchar(4)       NOT NULL WITH DEFAULT,--���򼸸���Ʒ���Դ���
                goods_dazeng_shuoming1                 varchar(100)       NOT NULL WITH DEFAULT,--������Ʒ˵��
                 goods_dazeng2                 varchar(10)       NOT NULL WITH DEFAULT,--������Ʒid
               goods_dazeng_tflag2                 varchar(1)       NOT NULL WITH DEFAULT,--������Ʒ�Ƿ���˻�(Y/N)
                 goods_dazeng_count2                 varchar(8)       NOT NULL WITH DEFAULT,--������Ʒ����   
                goods_dazeng_tiaojian2                 varchar(4)       NOT NULL WITH DEFAULT,--���򼸸���Ʒ���Դ���
                goods_dazeng_shuoming2                 varchar(100)       NOT NULL WITH DEFAULT,--������Ʒ˵��              
      
                        goods_zuixiao_count                          varchar(4)       NOT NULL WITH DEFAULT,--��С������
        goods_shangjia_date                           varchar(10)       NOT NULL WITH DEFAULT,--��Ʒ�ϼ�����                       

                   goods_pifashuliang1                                         varchar(8)       NOT NULL WITH DEFAULT,--��������1
                   goods_pifashuliang2                                         varchar(8)       NOT NULL WITH DEFAULT,--��������2
                   goods_pifashuliang3                                         varchar(8)       NOT NULL WITH DEFAULT,--��������3                                      
                   goods_pifajiage1                                         varchar(8)       NOT NULL WITH DEFAULT,--�����۸�1
                   goods_pifajiage2                                         varchar(8)       NOT NULL WITH DEFAULT,--�����۸�2
                     goods_pifajiage3                                         varchar(8)       NOT NULL WITH DEFAULT,--�����۸�3
                             
          goods_shuoming1                          varchar(50)       NOT NULL WITH DEFAULT,--��Ʒ˵��1 --        
         goods_shuoming2                          varchar(100)       NOT NULL WITH DEFAULT,--��Ʒ˵��2            
         goods_shuoming3                          varchar(400)       NOT NULL WITH DEFAULT,--��Ʒ˵��3 
         goods_shuoming4                          varchar(100)       NOT NULL WITH DEFAULT,--��Ʒ˵��4  
         
                  goods_fuwuchengnuo                          varchar(200)       NOT NULL WITH DEFAULT,--��Ʒ�����ŵ--
                  goods_wenxintishi                         varchar(100)       NOT NULL WITH DEFAULT,--��Ʒ��ܰ��ʾ--
                                                  
        goods_small_pic1                           varchar(100)       NOT NULL WITH DEFAULT,--��ƷСͼ1
        goods_small_pic2                           varchar(100)       NOT NULL WITH DEFAULT,--��ƷСͼ2
         goods_small_pic3                           varchar(100)       NOT NULL WITH DEFAULT,--��ƷСͼ3
          goods_small_pic4                           varchar(100)       NOT NULL WITH DEFAULT,--��ƷСͼ4      
          goods_small_pic5                          varchar(50)       NOT NULL WITH DEFAULT,--��ƷСͼ5     
         goods_small_pic6                           varchar(50)       NOT NULL WITH DEFAULT,--��ƷСͼ6        
          goods_mid_pic1                           varchar(100)       NOT NULL WITH DEFAULT,--��Ʒ��ͼ1          
          goods_mid_pic2                           varchar(100)       NOT NULL WITH DEFAULT,--��Ʒ��ͼ2        
          goods_mid_pic3                          varchar(100)       NOT NULL WITH DEFAULT,--��Ʒ��ͼ3       
           goods_mid_pic4                           varchar(100)       NOT NULL WITH DEFAULT,--��Ʒ��ͼ4         
          goods_mid_pic5                           varchar(50)       NOT NULL WITH DEFAULT,--��Ʒ��ͼ5                      
           goods_big_pic1                           varchar(100)       NOT NULL WITH DEFAULT,--��Ʒ��ͼ1                  
           goods_big_pic2                           varchar(100)       NOT NULL WITH DEFAULT,--��Ʒ��ͼ2   
           goods_big_pic3                           varchar(100)       NOT NULL WITH DEFAULT,--��Ʒ��ͼ3
           goods_mid_pic6                          varchar(50)       NOT NULL WITH DEFAULT,--��Ʒ��ͼ6        
            goods_big_pic4                           varchar(100)       NOT NULL WITH DEFAULT,--��Ʒ��ͼ4 ��Ʒ����޸���                  
           goods_big_pic5                           varchar(50)       NOT NULL WITH DEFAULT,--��Ʒ��ͼ5  ��Ʒ¼������      
           goods_big_pic6                           varchar(50)       NOT NULL WITH DEFAULT,--��Ʒ��ͼ6  ��Ʒ�޸�����          
     
                goods_luxiang                           varchar(100)       NOT NULL WITH DEFAULT,--��Ʒ��Ƶ˵��   
             goods_notsale                           varchar(1)       NOT NULL WITH DEFAULT,--�Ƿ������Ʒ������(Y/N)      
                                     	    
        PRIMARY KEY(
                  goods_id                            
        )
)

mysql
CREATE  TABLE    goodstab
      (
        goods_id                                        bigint (8) not null auto_increment,
        goods_name                                 varchar(80)          default'',
        goods_type_id                                CHAR(6)          default'',
        goods_type_id_lv1                                CHAR(6)          default'',
        goods_type_id_lv2                                CHAR(6)          default'',
        goods_pinpai                                varchar(20)          default'',
         goods_made_address                   varchar(20)       default'',
         goods_made_factory                   varchar(50)       default'',
        goods_made_date                           varchar(10)       default'',
        goods_bad_date                           varchar(10)       default'',
        goods_baozhi_date                           varchar(4)       default'',
        goods_jiage                                         varchar(8)       default'',
         goods_zhekou_jiage                  varchar(8)       default'',
         goods_jinchiJiage                                         varchar(8)       default'',
          goods_pifashang                  varchar(12)       default'',
           goods_duanmingcheng                  varchar(200)       default'',
           goods_tiaoxingma                  varchar(13)       default'',
           goods_guige                  varchar(8)       default'',
           goods_zhongliang                  varchar(8)       default'',
            goods_kucunshu                 varchar(8)       default'',
            goods_xiaoliang                  varchar(8)       default'',
             goods_tuihuoshu                  varchar(8)       default'',
             goods_xiangoushuliang                  varchar(8)       default'',
             goods_jifen                  varchar(8)       default'',
             goods_liulanshu                  varchar(8)       default'',
               goods_shangjia                  char(1)       default'',
                goods_shenhe                 char(1)       default'',
                 goods_tejia                 char(1)       default'',
                goods_huodong                 varchar(100)       default'',
                goods_dazeng1  int(4)  default 0,
               goods_dazeng_tflag1                 varchar(1)       default'',
                 goods_dazeng_count1                 varchar(8)       default'',
                  goods_dazeng_tiaojian1                 varchar(4)       default'',                
                goods_dazeng_shuoming1                varchar(100)       default'',
                goods_dazeng2  int(4)  default 0,
               goods_dazeng_tflag2                 varchar(1)       default'',
                 goods_dazeng_count2                 varchar(8)       default'',
                  goods_dazeng_tiaojian2                 varchar(4)       default'',                 
                goods_dazeng_shuoming2                varchar(100)       default'',
 
                                goods_zuixiao_count  int(4)  default 0,
        goods_shangjia_date                           varchar(20)       default'',

                   goods_pifashuliang1                                         varchar(8)       default'',
                   goods_pifashuliang2                                         varchar(8)       default'',
                   goods_pifashuliang3                                         varchar(8)       default'',
                   goods_pifajiage1                                         varchar(8)       default'',
                   goods_pifajiage2                                         varchar(8)       default'',
                     goods_pifajiage3                                         varchar(8)       default'',
                             
          goods_shuoming1                          varchar(50)       default'',
         goods_shuoming2                          varchar(100)       default'',
         goods_shuoming3                          varchar(800)       default'',
         goods_shuoming4                          varchar(200)       default'',
         
                  goods_fuwuchengnuo                          varchar(200)       default'',
                  goods_wenxintishi                         varchar(200)       default'',
                                                  
        goods_small_pic1                           varchar(100)       default'',
        goods_small_pic2                           varchar(100)       default'',
         goods_small_pic3                           varchar(60)       default'',
          goods_small_pic4                           varchar(50)       default'',
          goods_small_pic5                          varchar(50)       default'',
         goods_small_pic6                           varchar(50)       default'',
          goods_mid_pic1                           varchar(100)       default'',
          goods_mid_pic2                           varchar(100)       default'',
          goods_mid_pic3                          varchar(60)       default'',
           goods_mid_pic4                           varchar(50)       default'',
           goods_big_pic1                           varchar(100)       default'',
           goods_big_pic2                           varchar(100)       default'',
           goods_big_pic3                           varchar(60)       default'',
            goods_big_pic4                           varchar(50)       default'',
           goods_input_date                           varchar(20)       default'',
           goods_modify_date                        varchar(20)       default'',
           goods_modify_user                         varchar(40)       default'',
           goods_modify_user_ip                          varchar(20)       default'',
     
                goods_luxiang                           varchar(100)       default'',
                     goods_notsale                           varchar(1)       default'',      
                                     	    
        PRIMARY KEY(
                  goods_id                            
        )
)

