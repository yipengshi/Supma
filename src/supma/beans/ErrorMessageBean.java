package supma.beans;

import java.io.Serializable;

public class ErrorMessageBean  implements Serializable {

	private static final long serialVersionUID = -3186445941967227706L;

	public static String tableName = "errormessage" ;
    
    public String error_id            ="";
    public String error_type            ="";
    public String error_summery            ="";
    public String error_detail            ="";
    public String error_notuser1            ="";
    public String error_notuser2            ="";

}
