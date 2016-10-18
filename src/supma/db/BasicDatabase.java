package supma.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.*;


import java.text.*;
import supma.beans.*;
import supma.common.CommonKey;
import supma.log.LogManager;

public class BasicDatabase{
	
	private static final long serialVersionUID = 15L;

	public BasicDatabase(){
		
	}
	
	public Connection conn = null;
	public PreparedStatement pstm = null;
	public ResultSet rs = null;
	LogManager lm = null;
	
	public String Tablename = "";
	public String Sql = "";
	
	
	public int executeCount=-1;
	
	String resourceName="dbresource";

	ResourceBundle bund =null;

	
	public void init () throws Exception{
		try{
			//todo
		}catch (Exception e){
			throw e;
		}
	}

	
	public Connection getMysqlConnection() throws Exception{
		
		try{
			//  Connection的获得和释放 
			ConnectionFactory jd =  ConnectionFactory.getInstance();
			conn = jd.getJNDIConnectio();
			return conn;
		}catch (Exception e){
			if (conn!=null){
				conn.close();
			}
			throw e;
		}finally{
			
		}
	}

	public void setAutoCommit(boolean bool) throws Exception{
		conn.setAutoCommit(bool);
	}
	 
	//myName:bean name. type:select,update,insert,delete
	public ResultSet executeSql (String myName,String type,String sql,String[] column,String[] values,String tableName,String[] wherecolumn,String[] wherevalues,userBean ufo)throws Exception{
		lm=LogManager.getInstance(CommonKey.logDir);
		try{
			
			if(conn==null){
				getMysqlConnection();
				 
			}
			if("select".equals(type)){
				lm.println("I", "BasicDatabase", "executeSql|页面:"+myName+"|操作类型:"+type, sql,ufo);
				pstm=conn.prepareStatement(sql);
				if(column!=null && column.length>0){
					for(int i=0;i<column.length;i++){
						pstm.setString(i+1, column[i]);
					}
				}
				rs=pstm.executeQuery();
				return rs;
			}else if("update".equals(type)){
				
				if(sql!=null){
					pstm=conn.prepareStatement(sql);
					
					//System.out.println(sql);
					
					executeCount=pstm.executeUpdate();
				}else{
					StringBuffer buffer = new StringBuffer();
					for(int i=0;i<column.length;i++){
						buffer.append(column[i]);
						buffer.append("=?");
						if(i!=(column.length-1)){
							buffer.append(",");
						}
					}
					String tempSql = "update "+tableName+" set "+buffer.toString();
					if(wherecolumn != null && wherecolumn.length>0){
						StringBuffer buffer2 = new StringBuffer();
						buffer2.append(" where ");
						for(int i=0;i<wherecolumn.length;i++){
							buffer2.append(wherecolumn[i]);
							buffer2.append("=?");
							if(i!=(wherecolumn.length-1)){
								buffer2.append(",");
							}
						}
						tempSql = tempSql + buffer2.toString();
					}
					System.out.println(tempSql);
					pstm=conn.prepareStatement(tempSql);
					
					int d=0;
					for(int i=0;i<column.length;i++,d++){
						System.out.println("*"+d);
						pstm.setString(d+1,values[i]);
					}
					for(int i=0;i<wherecolumn.length;i++,d++){
						System.out.println("*"+d);
						pstm.setString(d+1,wherevalues[i]);
					}
					
					lm.println("I", "BasicDatabase", "executeSql|页面:"+myName+"|操作类型:"+type, sql,ufo);
					
					executeCount = pstm.executeUpdate();
				}
				
			}else if("insert".equals(type)){
				
				if(sql!=null){
					pstm=conn.prepareStatement(sql);
					executeCount=pstm.executeUpdate();
					
					//System.out.println(sql);
				}else{
					StringBuffer buffer = new StringBuffer();
					StringBuffer buffer2 = new StringBuffer();
					for(int i=0;i<column.length;i++){
						buffer.append(column[i]);
						buffer2.append("?");
						if(i!=(column.length-1)){
							buffer.append(",");
							buffer2.append(",");
						}
					}
					
					String tempSql = "insert into "+tableName+" ( "+buffer.toString()+" ) values ( "+buffer2.toString()+" ) ";
					pstm=conn.prepareStatement(tempSql);			
					for(int i=0;i<column.length;i++){
						pstm.setString(i+1,values[i]);
					}
					
					lm.println("I", "BasicDatabase", "executeSql|页面:"+myName+"|操作类型:"+type, sql,ufo);
					
					executeCount = pstm.executeUpdate();
				}
				
			}else if("delete".equals(type)){
				pstm=conn.prepareStatement(sql);
				
				lm.println("I", "BasicDatabase", "executeSql|页面:"+myName+"|操作类型:"+type, sql,ufo);
				
				executeCount = pstm.executeUpdate();
			}
			
			
			return null;
			
		}catch (Exception e){
			if(pstm!=null){
				pstm.close();
				pstm=null;
			}
			if(conn!=null){
				conn.close();
				conn=null;
			}
			lm.println("I", "BasicDatabase", "executeSql|页面:"+myName+"|操作类型:"+type, sql,ufo);
			throw  e;
		}finally{

		}
	}
	
	
	public void closeConn() throws Exception{
		conn.close();
	}
	
	public void closePstm() throws Exception{
		try{
			if(pstm!=null){
				pstm.close();
				pstm=null;
			}
		}catch(Exception e){
			throw e;
		}finally{
			if(pstm!=null){
				pstm.close();
				pstm=null;
			}
		}
	}

	public void closeRs() throws Exception{
		try{
			if(rs!=null){
				rs.close();
				rs=null;
			}
		}catch(Exception e){
			throw e;
		}finally{
			if(rs!=null){
				rs.close();
				rs=null;
			}
		}
	}
	
	public String changeToIsoEncoding(String s) throws Exception{
		return new String( s.getBytes( "iso8859-1" ), "GBK" );
	}
	
	
	public boolean isHalfStroke(String prmStr) {

		if (prmStr == null || prmStr.length() == 0) {

			return true;
		}

		StringCharacterIterator sci = new StringCharacterIterator(prmStr);

		for (char c = sci.first(); c < CharacterIterator.DONE; c = sci.next()) {
			if ((c >= '0' && c <= '9') ||
					(c >= 'A' && c <= 'Z') ||
					(c >= 'a' && c <= 'z')) {
			} else {
				return false;
			}
		}
		return true;
	}
	
	public boolean hasDouHao(String prmStr) {

		if (prmStr == null || prmStr.length() == 0) {

			return true;
		}
		if(prmStr.indexOf("'")!=-1||prmStr.indexOf("|")!=-1||prmStr.indexOf("-")!=-1||prmStr.indexOf("$")!=-1||prmStr.indexOf("%")!=-1||prmStr.indexOf("\"")!=-1||prmStr.indexOf("*")!=-1||prmStr.indexOf(")")!=-1||prmStr.indexOf(")")!=-1){
			return false;
		}
		return true;
	}
	
	public static String fillInRight(String data, char fill, int len) {
		if (len == 0)
			return data;
		if (data == null)
			data = "";
		if (data.getBytes().length > len)
			return data;
		int diffLen = len - data.getBytes().length;
		StringBuffer fmtStr = new StringBuffer(data);
		for (int i = 0; i < diffLen; i++)
			fmtStr.append(fill);
		return fmtStr.toString();
	}
	
	public static String format8date(String data) {
		if(data==null || data.length() != 8){
			return data;
		}else{
			return new String(data.substring(0,4)+"-"+data.substring(4,6)+"-"+data.substring(6,8));
		}
		
	}
	
	public static boolean isHalfNumericalSt(String _baseSt) {
		
		if(_baseSt == null || _baseSt.length() < 1) {
			return false;
		}// end if

		
		int leng = _baseSt.length();
		for(int i=0; i < leng; i++) {
			char checkChar = _baseSt.charAt(i);
			//0=0x0030
			//9=0x0039
			if(!(checkChar >= 0x0030 && checkChar <= 0x0039)) {
				
				return false;
			}// end if
		}// end for
		return true;
	}
	
}