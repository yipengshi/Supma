CREATE  TABLE    usertab
      (
        user_id                                       CHAR(40)          NOT NULL WITH DEFAULT,--�û���
        user_pwd                                      CHAR(40)          NOT NULL WITH DEFAULT,--����md5
        user_sex                                  char(1)	 NOT NULL WITH DEFAULT,--�Ա�
        user_tel_no                             VARCHAR(12)       NOT NULL WITH DEFAULT,--�ֻ�����
	user_tel_no2                             VARCHAR(14)       NOT NULL WITH DEFAULT,--��������
	user_name                                VARCHAR(80)       NOT NULL WITH DEFAULT,--�û�����
	user_level                                VARCHAR(8)       NOT NULL WITH DEFAULT,--�û��ȼ�
	user_sco                               VARCHAR(8)       NOT NULL WITH DEFAULT,--�ͻ�����
        user_regis_date                               varCHAR(0020)          NOT NULL WITH DEFAULT,--ע������
        user_last_login_date                               varCHAR(0020)          NOT NULL WITH DEFAULT,--�ϴε�¼����
	user_qq                                         varCHAR(0020)          NOT NULL WITH DEFAULT,--QQ��
	user_weixin					 varCHAR(0020)          NOT NULL WITH DEFAULT,--΢�ź�
	user_address1                                  varCHAR(0160)          NOT NULL WITH DEFAULT,--��ַ1
	user_address2                                  varCHAR(0480)          NOT NULL WITH DEFAULT,--��ַ2
	user_address3                                  varCHAR(0080)          NOT NULL WITH DEFAULT,--��ַ3
	user_address4                                  varCHAR(0080)          NOT NULL WITH DEFAULT,--��ַ4
        user_zip1                               varCHAR(0006)          NOT NULL WITH DEFAULT,--��������1
	user_zip2                               varCHAR(0006)          NOT NULL WITH DEFAULT,--��������2
	user_zip3                               varCHAR(0006)          NOT NULL WITH DEFAULT,--��������3
	user_zip4                               varCHAR(0006)          NOT NULL WITH DEFAULT,--��������4
	user_photo                               varCHAR(0100)          NOT NULL WITH DEFAULT,--�û�ͷ���ַ
	user_islock                               CHAR(2)          NOT NULL WITH DEFAULT,--�û��Ƿ�����	
	user_mail_address                               varCHAR(0040)          NOT NULL WITH DEFAULT,--�û�����	
	user_question                               varCHAR(050)          NOT NULL WITH DEFAULT,--�û����뱣������
	user_question_answer                               varCHAR(050)          NOT NULL WITH DEFAULT,--�û����뱣�������
	user_nicheng                               varCHAR(020)          NOT NULL WITH DEFAULT,--�û��ǳ�
	user_type                               char(1)          NOT NULL WITH DEFAULT,--�û�����
		
	user_notuser1                               varCHAR(0080)          NOT NULL WITH DEFAULT,--��λ1
	user_notuser2                               varCHAR(0080)          NOT NULL WITH DEFAULT,--��λ2
	user_notuser3                               varCHAR(0080)          NOT NULL WITH DEFAULT,--��λ3
	user_notuser4                               varCHAR(0080)          NOT NULL WITH DEFAULT,--��λ4
	user_notuser5                               varCHAR(0080)          NOT NULL WITH DEFAULT,--��λ5
	user_notuser6                               varCHAR(0080)          NOT NULL WITH DEFAULT,--��λ6
	user_notuser7                               varCHAR(0020)          NOT NULL WITH DEFAULT,--��λ7
	user_notuser8                               varCHAR(0020)          NOT NULL WITH DEFAULT,--��λ8
	user_notuser9                               varCHAR(0020)          NOT NULL WITH DEFAULT,--��λ9
	user_notuser10                               varCHAR(0020)          NOT NULL WITH DEFAULT,--��λ10
	user_notuser11                              varCHAR(0020)          NOT NULL WITH DEFAULT,--��λ11


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
