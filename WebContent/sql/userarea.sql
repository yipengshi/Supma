CREATE  TABLE    userarea
      (
        userarea_id                                       CHAR(4)          NOT NULL WITH DEFAULT,--Ƭ��id
        userarea_name                                      varCHAR(100)          NOT NULL WITH DEFAULT,--Ƭ����
        userarea_level                                 char(2)	 NOT NULL WITH DEFAULT,--�ڼ������Ƭ��
        userarea_up_level                           char(2)       NOT NULL WITH DEFAULT,--�ϲ�Ƭ������
		 userarea_notuser1                            VARCHAR(10)       NOT NULL WITH DEFAULT,--��λ1
	     userarea_notuser2                            VARCHAR(10)       NOT NULL WITH DEFAULT,--��λ2
	     userarea_notuser3                            VARCHAR(10)       NOT NULL WITH DEFAULT,--��λ3
	     userarea_notuser4                            VARCHAR(20)       NOT NULL WITH DEFAULT,--��λ4
	     userarea_notuser5                            VARCHAR(20)       NOT NULL WITH DEFAULT,--��λ5


        PRIMARY KEY(
                  userarea_id                            
        )
)

mysql

CREATE  TABLE    userarea
      (
         userarea_id                                       CHAR(4)          default'',
        userarea_name                                 varCHAR(100)          default'',
        userarea_level                                 char(2)	 default'',
        userarea_up_level                           char(2)       default'',
		 userarea_notuser1                            VARCHAR(10)       default'',
	     userarea_notuser2                            VARCHAR(10)       default'',
	     userarea_notuser3                            VARCHAR(10)       default'',
	     userarea_notuser4                            VARCHAR(20)      default'',
	     userarea_notuser5                            VARCHAR(20)      default'',
	    

        PRIMARY KEY(
                  userarea_id                            
        )
)
