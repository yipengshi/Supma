CREATE  TABLE    goodsmastertab
      (
        goods_type_id                                       CHAR(6)          NOT NULL WITH DEFAULT,--��Ʒid
        goods_type_name                                 varchar(40)          NOT NULL WITH DEFAULT,--��Ʒ��
        goods_type_level                                 char(2)	 NOT NULL WITH DEFAULT,--��Ʒ����ȼ�
        goods_type_up_level                            char(2)       NOT NULL WITH DEFAULT,--��Ʒ�ϲ����
		goods_type_notuser1                            VARCHAR(10)       NOT NULL WITH DEFAULT,--��λ1
	    goods_type_notuser2                            VARCHAR(10)       NOT NULL WITH DEFAULT,--��λ2
	    goods_type_notuser3                            VARCHAR(10)       NOT NULL WITH DEFAULT,--��λ3
	    goods_type_notuser4                            VARCHAR(20)       NOT NULL WITH DEFAULT,--��λ4
	    goods_type_notuser5                            VARCHAR(20)       NOT NULL WITH DEFAULT,--��λ5
	    
        PRIMARY KEY(
                  goods_type_id                            
        )
)


mysql
CREATE  TABLE    goodsmastertab
      (
        goods_type_id                                       CHAR(6)          default'',#��Ʒid
        goods_type_name                                 varchar(40)          default'',#��Ʒ��
        goods_type_level                                 char(2)	 default'',#��Ʒ����ȼ�
        goods_type_up_level                            char(2)       default'',#��Ʒ�ϲ����
		goods_type_notuser1                            VARCHAR(10)       default'',#��λ1
	    goods_type_notuser2                            VARCHAR(10)       default'',#��λ2
	    goods_type_notuser3                            VARCHAR(10)       default'',#��λ3
	    goods_type_notuser4                            VARCHAR(20)       default'',#��λ4
	    goods_type_notuser5                            VARCHAR(20)       default'',#��λ5
	    
        PRIMARY KEY(
                  goods_type_id                            
        )
)


