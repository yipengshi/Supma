CREATE  TABLE    goodsmastertab
      (
        goods_type_id                                       CHAR(6)          NOT NULL WITH DEFAULT,--商品id
        goods_type_name                                 varchar(40)          NOT NULL WITH DEFAULT,--商品名
        goods_type_level                                 char(2)	 NOT NULL WITH DEFAULT,--商品分类等级
        goods_type_up_level                            char(2)       NOT NULL WITH DEFAULT,--商品上层分类
		goods_type_notuser1                            VARCHAR(10)       NOT NULL WITH DEFAULT,--空位1
	    goods_type_notuser2                            VARCHAR(10)       NOT NULL WITH DEFAULT,--空位2
	    goods_type_notuser3                            VARCHAR(10)       NOT NULL WITH DEFAULT,--空位3
	    goods_type_notuser4                            VARCHAR(20)       NOT NULL WITH DEFAULT,--空位4
	    goods_type_notuser5                            VARCHAR(20)       NOT NULL WITH DEFAULT,--空位5
	    
        PRIMARY KEY(
                  goods_type_id                            
        )
)


mysql
CREATE  TABLE    goodsmastertab
      (
        goods_type_id                                       CHAR(6)          default'',#商品id
        goods_type_name                                 varchar(40)          default'',#商品名
        goods_type_level                                 char(2)	 default'',#商品分类等级
        goods_type_up_level                            char(2)       default'',#商品上层分类
		goods_type_notuser1                            VARCHAR(10)       default'',#空位1
	    goods_type_notuser2                            VARCHAR(10)       default'',#空位2
	    goods_type_notuser3                            VARCHAR(10)       default'',#空位3
	    goods_type_notuser4                            VARCHAR(20)       default'',#空位4
	    goods_type_notuser5                            VARCHAR(20)       default'',#空位5
	    
        PRIMARY KEY(
                  goods_type_id                            
        )
)


