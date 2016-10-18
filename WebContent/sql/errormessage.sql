
mysql
CREATE  TABLE    errormessage
      (
        error_id                                        int (4) not null auto_increment,
        error_type                                 char(4)          default'',
       error_summery                                varchar(100)          default'',
        error_detail                                varchar(200)          default'',
   		 error_notuser1                            VARCHAR(20)       default'',
	     error_notuser2                            VARCHAR(20)       default'',
                                     	    
        PRIMARY KEY(
                  error_id                            
        )
)

