
#mysql
CREATE  TABLE    cuxiaotab
      (
        cuxiao_id                                        int (4) not null auto_increment, #����id
        cuxiao_name                                 varchar(80)          default'', #������
        cuxiao_gaiyao                          varchar(1000)          default'', #������Ҫ
         cuxiao_xiangxi                          varchar(1000)          default'',#������ϸ
          cuxiao_shuoming                   varchar(1000)          default'',#����˵��
         cuxiao_date_start                  varchar(10)       default'',#������ʼʱ��
         cuxiao_date_end                  varchar(10)       default'',#��������ʱ��
       	cuxiao_youxiao                  char(1)       default'',#�����Ƿ���Ч
         cuxiao_notuse1                  varchar(10)       default'',#δ��1
         cuxiao_notuse2                  varchar(10)       default'',#δ��2
         cuxiao_notuse3                  varchar(10)       default'',#δ��3
                                     	    
        PRIMARY KEY(
                  cuxiao_id                            
        )
)

