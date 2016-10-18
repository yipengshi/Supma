package supma.common;

import java.util.UUID;

import org.apache.commons.lang3.StringUtils;

public class CommonStringUtil{
	
	/**
	 * 返回真实长度,汉字2字节(GBK)
	 * @param _baseSt   
	 * @return int      
	 */
	public static int getTrueLength(String _baseSt) {
		
		if(_baseSt == null || _baseSt.length() < 1) {
			return 0;
		}// end if
		int retInt = _baseSt.length();
		try {
			String sjisSt = new String(_baseSt.getBytes("GBK"), "ISO8859_1");

			retInt = sjisSt.length();

		}catch(java.io.UnsupportedEncodingException e) {
			e.printStackTrace();
		}// end try

		return retInt;
	}
	/**
	 * 返回一个随机文件名32+1+4(uuid+.+jpg=37位)
	 * @param 原始文件名   
	 * @return String
	 */
	public static String makeFileName(String _filename) {
		String tempName ="";
		if(StringUtils.isEmpty(_filename)){
			tempName="";
		}
		String uid = UUID.randomUUID().toString() ;
		String partName = StringUtils.substring(_filename, _filename.lastIndexOf("."), _filename.lastIndexOf(".")+4);
		tempName=uid+partName;;
		return tempName;
	}
	/**
	 * 为防止一个目录下面出现太多文件，要使用hash算法打散存储
	* @Method: makePath
	* @Description: 
	* @Anthor:孤傲苍狼
	*
	* @param filename 文件名，要根据文件名生成存储目录(最大6位)
	* @param savePath 文件存储路径
	* @return 新的存储目录
	*/ 
	public static String makePath(String filename,String savePath){
		  //得到文件名的hashCode的值，得到的就是filename这个字符串对象在内存中的地址
	    int hashcode = filename.hashCode();
	    int dir1 = hashcode&0xf;  //0--15
	    int dir2 = (hashcode&0xf0)>>4;  //0-15
	    //构造新的保存目录
	    String dir = savePath + "/" + dir1 + "/" + dir2;  //upload\2\3  upload\3\5
	    //File既可以代表文件也可以代表目录
//	    File file = new File(dir);
//	    //如果目录不存在
//	    if(!file.exists()){
//	        //创建目录
//	        file.mkdirs();
//	    }
	    return dir;
	}
}