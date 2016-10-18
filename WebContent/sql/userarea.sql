CREATE  TABLE    userarea
      (
        userarea_id                                       CHAR(4)          NOT NULL WITH DEFAULT,--片区id
        userarea_name                                      varCHAR(100)          NOT NULL WITH DEFAULT,--片区名
        userarea_level                                 char(2)	 NOT NULL WITH DEFAULT,--第几级别的片区
        userarea_up_level                           char(2)       NOT NULL WITH DEFAULT,--上层片区级别
		 userarea_notuser1                            VARCHAR(10)       NOT NULL WITH DEFAULT,--空位1
	     userarea_notuser2                            VARCHAR(10)       NOT NULL WITH DEFAULT,--空位2
	     userarea_notuser3                            VARCHAR(10)       NOT NULL WITH DEFAULT,--空位3
	     userarea_notuser4                            VARCHAR(20)       NOT NULL WITH DEFAULT,--空位4
	     userarea_notuser5                            VARCHAR(20)       NOT NULL WITH DEFAULT,--空位5


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
