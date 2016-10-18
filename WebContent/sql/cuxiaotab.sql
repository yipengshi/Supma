
#mysql
CREATE  TABLE    cuxiaotab
      (
        cuxiao_id                                        int (4) not null auto_increment, #促销id
        cuxiao_name                                 varchar(80)          default'', #促销名
        cuxiao_gaiyao                          varchar(1000)          default'', #促销概要
         cuxiao_xiangxi                          varchar(1000)          default'',#促销详细
          cuxiao_shuoming                   varchar(1000)          default'',#促销说明
         cuxiao_date_start                  varchar(10)       default'',#促销开始时间
         cuxiao_date_end                  varchar(10)       default'',#促销结束时间
       	cuxiao_youxiao                  char(1)       default'',#促销是否有效
         cuxiao_notuse1                  varchar(10)       default'',#未用1
         cuxiao_notuse2                  varchar(10)       default'',#未用2
         cuxiao_notuse3                  varchar(10)       default'',#未用3
                                     	    
        PRIMARY KEY(
                  cuxiao_id                            
        )
)

