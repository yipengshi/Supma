CREATE  TABLE    pifashangtab
      (
        pifashang_id                                       CHAR(12)          NOT NULL WITH DEFAULT,--�������û���
        pifashang_jieshao                                VARCHAR(500)       NOT NULL WITH DEFAULT,--�����̽���
        pifashang_yucunkuan                                VARCHAR(10)       NOT NULL WITH DEFAULT,--������Ԥ�����
         pifashang_notuse1                              VARCHAR(100)       NOT NULL WITH DEFAULT,--ûʹ��1
           pifashang_notuse2                              VARCHAR(100)       NOT NULL WITH DEFAULT,--ûʹ��2                      
           pifashang_notuse3                              VARCHAR(100)       NOT NULL WITH DEFAULT,--ûʹ��1
         pifashang_notuse4                              VARCHAR(100)       NOT NULL WITH DEFAULT,--ûʹ��1
         pifashang_notuse5                             VARCHAR(100)       NOT NULL WITH DEFAULT,--ûʹ��1
          pifashang_notuse6                              VARCHAR(100)       NOT NULL WITH DEFAULT,--ûʹ��1
                  
        PRIMARY KEY(
                  pifashang_id                            
        )
)

mysql
CREATE  TABLE    pifashangtab
      (
        pifashang_id                                       CHAR(12)          default'',
        pifashang_jieshao                                VARCHAR(500)       default'',
        pifashang_yucunkuan                                VARCHAR(10)       default'',
         pifashang_notuse1                              VARCHAR(100)       default'',
           pifashang_notuse2                              VARCHAR(100)       default'',
           pifashang_notuse3                              VARCHAR(100)       default'',
         pifashang_notuse4                              VARCHAR(100)       default'',
         pifashang_notuse5                             VARCHAR(100)       default'',
          pifashang_notuse6                              VARCHAR(100)       default'',
                  
        PRIMARY KEY(
                  pifashang_id                            
        )
)
