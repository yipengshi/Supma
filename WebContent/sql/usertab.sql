CREATE  TABLE    usertab
      (
        user_id                                       CHAR(40)          NOT NULL WITH DEFAULT,--用户名
        user_pwd                                      CHAR(40)          NOT NULL WITH DEFAULT,--密码md5
        user_sex                                  char(1)	 NOT NULL WITH DEFAULT,--性别
        user_tel_no                             VARCHAR(12)       NOT NULL WITH DEFAULT,--手机号码
	user_tel_no2                             VARCHAR(14)       NOT NULL WITH DEFAULT,--座机号码
	user_name                                VARCHAR(80)       NOT NULL WITH DEFAULT,--用户名称
	user_level                                VARCHAR(8)       NOT NULL WITH DEFAULT,--用户等级
	user_sco                               VARCHAR(8)       NOT NULL WITH DEFAULT,--客户积分
        user_regis_date                               varCHAR(0020)          NOT NULL WITH DEFAULT,--注册日期
        user_last_login_date                               varCHAR(0020)          NOT NULL WITH DEFAULT,--上次登录日期
	user_qq                                         varCHAR(0020)          NOT NULL WITH DEFAULT,--QQ号
	user_weixin					 varCHAR(0020)          NOT NULL WITH DEFAULT,--微信号
	user_address1                                  varCHAR(0160)          NOT NULL WITH DEFAULT,--地址1
	user_address2                                  varCHAR(0480)          NOT NULL WITH DEFAULT,--地址2
	user_address3                                  varCHAR(0080)          NOT NULL WITH DEFAULT,--地址3
	user_address4                                  varCHAR(0080)          NOT NULL WITH DEFAULT,--地址4
        user_zip1                               varCHAR(0006)          NOT NULL WITH DEFAULT,--邮政编码1
	user_zip2                               varCHAR(0006)          NOT NULL WITH DEFAULT,--邮政编码2
	user_zip3                               varCHAR(0006)          NOT NULL WITH DEFAULT,--邮政编码3
	user_zip4                               varCHAR(0006)          NOT NULL WITH DEFAULT,--邮政编码4
	user_photo                               varCHAR(0100)          NOT NULL WITH DEFAULT,--用户头像地址
	user_islock                               CHAR(2)          NOT NULL WITH DEFAULT,--用户是否被锁定	
	user_mail_address                               varCHAR(0040)          NOT NULL WITH DEFAULT,--用户邮箱	
	user_question                               varCHAR(050)          NOT NULL WITH DEFAULT,--用户密码保护问题
	user_question_answer                               varCHAR(050)          NOT NULL WITH DEFAULT,--用户密码保护问题答案
	user_nicheng                               varCHAR(020)          NOT NULL WITH DEFAULT,--用户昵称
	user_type                               char(1)          NOT NULL WITH DEFAULT,--用户类型
		
	user_notuser1                               varCHAR(0080)          NOT NULL WITH DEFAULT,--空位1
	user_notuser2                               varCHAR(0080)          NOT NULL WITH DEFAULT,--空位2
	user_notuser3                               varCHAR(0080)          NOT NULL WITH DEFAULT,--空位3
	user_notuser4                               varCHAR(0080)          NOT NULL WITH DEFAULT,--空位4
	user_notuser5                               varCHAR(0080)          NOT NULL WITH DEFAULT,--空位5
	user_notuser6                               varCHAR(0080)          NOT NULL WITH DEFAULT,--空位6
	user_notuser7                               varCHAR(0020)          NOT NULL WITH DEFAULT,--空位7
	user_notuser8                               varCHAR(0020)          NOT NULL WITH DEFAULT,--空位8
	user_notuser9                               varCHAR(0020)          NOT NULL WITH DEFAULT,--空位9
	user_notuser10                               varCHAR(0020)          NOT NULL WITH DEFAULT,--空位10
	user_notuser11                              varCHAR(0020)          NOT NULL WITH DEFAULT,--空位11


        PRIMARY KEY(
                  user_id                            
        )
)

mysql

CREATE  TABLE    usertab
      (
        user_id                                       CHAR(40)          default'',
        user_pwd                                      CHAR(40)          default'',
        user_sex                                  char(1)	 default'',
        user_tel_no                             VARCHAR(12)       default'',
	user_tel_no2                             VARCHAR(14)       default'',
	user_name                                VARCHAR(80)       default'',
	user_level                                VARCHAR(8)       default'',
	user_sco                               VARCHAR(8)       default'',
        user_regis_date                               varCHAR(0020)          default'',
        user_last_login_date                               varCHAR(0020)          default'',
	user_qq                                         varCHAR(0020)          default'',
	user_weixin					 varCHAR(0020)          default'',
	user_address1                                  varCHAR(0160)          default'',
	user_address2                                  varCHAR(600)          default'',
	user_address3                                  varCHAR(0080)          default'',
	user_address4                                  varCHAR(0080)          default'',
        user_zip1                               varCHAR(0006)          default'',
	user_zip2                               varCHAR(0006)          default'',
	user_zip3                               varCHAR(0006)          default'',
	user_zip4                               varCHAR(0006)          default'',
	user_photo                               varCHAR(0100)          default'',
	user_islock                               CHAR(2)          default'',	
	user_mail_address                               varCHAR(0050)          default'',	
	user_question                               varCHAR(050)          default'',
	user_question_answer                               varCHAR(050)          default'',
	user_nicheng                               varCHAR(020)          default'',
	user_type                               char(1)          default'',
		
	user_notuser1                               varCHAR(0080)          default'',
	user_notuser2                               varCHAR(0080)          default'',
	user_notuser3                               varCHAR(0080)          default'',
	user_notuser4                               varCHAR(0040)          default'',
	user_notuser5                               varCHAR(0040)          default'',
	user_notuser6                               varCHAR(0040)          default'',
	user_notuser7                               varCHAR(0020)          default'',
	user_notuser8                               varCHAR(0020)          default'',
	user_notuser9                               varCHAR(0020)          default'',
	user_notuser10                               varCHAR(0020)          default'',
	user_notuser11                              varCHAR(0020)          default'',


        PRIMARY KEY(
                  user_id                            
        )
)
