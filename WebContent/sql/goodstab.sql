CREATE  TABLE    goodstab
      (
        goods_id                                       CHAR(10)          NOT NULL WITH DEFAULT,--商品id 0000000001开始连续增加
        goods_name                                 varchar(80)          NOT NULL WITH DEFAULT,--商品名--
        goods_type_id                                CHAR(6)          NOT NULL WITH DEFAULT,--商品所属类型第三层
        goods_type_id_lv1                                CHAR(6)          NOT NULL WITH DEFAULT,--商品所属类型第1层
        goods_type_id_lv2                                CHAR(6)          NOT NULL WITH DEFAULT,--商品所属类型第2层
        goods_pinpai                                varchar(20)          NOT NULL WITH DEFAULT,--商品品牌--
         goods_made_address                   varchar(20)       NOT NULL WITH DEFAULT,--商品产地--
         goods_made_factory                   varchar(50)       NOT NULL WITH DEFAULT,--商品生产厂家--
        goods_made_date                           varchar(10)       NOT NULL WITH DEFAULT,--商品生产日期
        goods_bad_date                           varchar(10)       NOT NULL WITH DEFAULT,--商品失效日期
        goods_baozhi_date                           varchar(4)       NOT NULL WITH DEFAULT,--商品保质期(天)--
        goods_jiage                                         varchar(8)       NOT NULL WITH DEFAULT,--商品价格 (建议零售价)--    
         goods_zhekou_jiage                  varchar(8)       NOT NULL WITH DEFAULT,--商品折扣后价格
         goods_jinchiJiage                                         varchar(8)       NOT NULL WITH DEFAULT,--商品金池网价格 
          goods_pifashang                  varchar(12)       NOT NULL WITH DEFAULT,--商品供应的批发商(id)
           goods_duanmingcheng                  varchar(200)       NOT NULL WITH DEFAULT,--商品长名称（用于条件查询等)
           goods_tiaoxingma                  varchar(8)       NOT NULL WITH DEFAULT,--商品条形码--
           goods_guige                  varchar(8)       NOT NULL WITH DEFAULT,--商品规格(型号)--
           goods_zhongliang                  varchar(8)       NOT NULL WITH DEFAULT,--商品包装重量(克)--
            goods_kucunshu                 varchar(8)       NOT NULL WITH DEFAULT,--商品库存数 --
            goods_xiaoliang                  varchar(8)       NOT NULL WITH DEFAULT,--商品销量 
             goods_tuihuoshu                  varchar(8)       NOT NULL WITH DEFAULT,--商品退货数量
             goods_xiangoushuliang                  varchar(8)       NOT NULL WITH DEFAULT,--商品限购数量--
             goods_jifen                  varchar(8)       NOT NULL WITH DEFAULT,--商品购买1件获得积分数                                
             goods_liulanshu                  varchar(8)       NOT NULL WITH DEFAULT,--商品被浏览数     
               goods_shangjia                  char(1)       NOT NULL WITH DEFAULT,--商品是否上架flag--
                goods_shenhe                 char(1)       NOT NULL WITH DEFAULT,--商品通过审核flag
                 goods_tejia                 char(1)       NOT NULL WITH DEFAULT,--商品是否特价商品--              
                goods_huodong                 varchar(20)       NOT NULL WITH DEFAULT,--商品参加的活动--
               goods_dazeng1                 varchar(10)       NOT NULL WITH DEFAULT,--搭赠商品id--
               goods_dazeng_tflag1                 varchar(1)       NOT NULL WITH DEFAULT,--搭赠商品是否可退换(Y/N)
                 goods_dazeng_count1                 varchar(8)       NOT NULL WITH DEFAULT,--搭赠商品数量
                 goods_dazeng_tiaojian1                 varchar(4)       NOT NULL WITH DEFAULT,--购买几个商品可以搭赠
                goods_dazeng_shuoming1                 varchar(100)       NOT NULL WITH DEFAULT,--搭赠商品说明
                 goods_dazeng2                 varchar(10)       NOT NULL WITH DEFAULT,--搭赠商品id
               goods_dazeng_tflag2                 varchar(1)       NOT NULL WITH DEFAULT,--搭赠商品是否可退换(Y/N)
                 goods_dazeng_count2                 varchar(8)       NOT NULL WITH DEFAULT,--搭赠商品数量   
                goods_dazeng_tiaojian2                 varchar(4)       NOT NULL WITH DEFAULT,--购买几个商品可以搭赠
                goods_dazeng_shuoming2                 varchar(100)       NOT NULL WITH DEFAULT,--搭赠商品说明              
      
                        goods_zuixiao_count                          varchar(4)       NOT NULL WITH DEFAULT,--最小起批量
        goods_shangjia_date                           varchar(10)       NOT NULL WITH DEFAULT,--商品上架日期                       

                   goods_pifashuliang1                                         varchar(8)       NOT NULL WITH DEFAULT,--批发数量1
                   goods_pifashuliang2                                         varchar(8)       NOT NULL WITH DEFAULT,--批发数量2
                   goods_pifashuliang3                                         varchar(8)       NOT NULL WITH DEFAULT,--批发数量3                                      
                   goods_pifajiage1                                         varchar(8)       NOT NULL WITH DEFAULT,--批发价格1
                   goods_pifajiage2                                         varchar(8)       NOT NULL WITH DEFAULT,--批发价格2
                     goods_pifajiage3                                         varchar(8)       NOT NULL WITH DEFAULT,--批发价格3
                             
          goods_shuoming1                          varchar(50)       NOT NULL WITH DEFAULT,--商品说明1 --        
         goods_shuoming2                          varchar(100)       NOT NULL WITH DEFAULT,--商品说明2            
         goods_shuoming3                          varchar(400)       NOT NULL WITH DEFAULT,--商品说明3 
         goods_shuoming4                          varchar(100)       NOT NULL WITH DEFAULT,--商品说明4  
         
                  goods_fuwuchengnuo                          varchar(200)       NOT NULL WITH DEFAULT,--商品服务承诺--
                  goods_wenxintishi                         varchar(100)       NOT NULL WITH DEFAULT,--商品温馨提示--
                                                  
        goods_small_pic1                           varchar(100)       NOT NULL WITH DEFAULT,--商品小图1
        goods_small_pic2                           varchar(100)       NOT NULL WITH DEFAULT,--商品小图2
         goods_small_pic3                           varchar(100)       NOT NULL WITH DEFAULT,--商品小图3
          goods_small_pic4                           varchar(100)       NOT NULL WITH DEFAULT,--商品小图4      
          goods_small_pic5                          varchar(50)       NOT NULL WITH DEFAULT,--商品小图5     
         goods_small_pic6                           varchar(50)       NOT NULL WITH DEFAULT,--商品小图6        
          goods_mid_pic1                           varchar(100)       NOT NULL WITH DEFAULT,--商品中图1          
          goods_mid_pic2                           varchar(100)       NOT NULL WITH DEFAULT,--商品中图2        
          goods_mid_pic3                          varchar(100)       NOT NULL WITH DEFAULT,--商品中图3       
           goods_mid_pic4                           varchar(100)       NOT NULL WITH DEFAULT,--商品中图4         
          goods_mid_pic5                           varchar(50)       NOT NULL WITH DEFAULT,--商品中图5                      
           goods_big_pic1                           varchar(100)       NOT NULL WITH DEFAULT,--商品大图1                  
           goods_big_pic2                           varchar(100)       NOT NULL WITH DEFAULT,--商品大图2   
           goods_big_pic3                           varchar(100)       NOT NULL WITH DEFAULT,--商品大图3
           goods_mid_pic6                          varchar(50)       NOT NULL WITH DEFAULT,--商品中图6        
            goods_big_pic4                           varchar(100)       NOT NULL WITH DEFAULT,--商品大图4 商品最后修改人                  
           goods_big_pic5                           varchar(50)       NOT NULL WITH DEFAULT,--商品大图5  商品录入日期      
           goods_big_pic6                           varchar(50)       NOT NULL WITH DEFAULT,--商品大图6  商品修改日期          
     
                goods_luxiang                           varchar(100)       NOT NULL WITH DEFAULT,--商品视频说明   
             goods_notsale                           varchar(1)       NOT NULL WITH DEFAULT,--是否搭赠商品不出售(Y/N)      
                                     	    
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

