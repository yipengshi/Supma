package supma.common;

import java.util.UUID;

import org.apache.commons.lang3.StringUtils;

public class CommonStringUtil{
	
	/**
	 * ������ʵ����,����2�ֽ�(GBK)
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
	 * ����һ������ļ���32+1+4(uuid+.+jpg=37λ)
	 * @param ԭʼ�ļ���   
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
	 * Ϊ��ֹһ��Ŀ¼�������̫���ļ���Ҫʹ��hash�㷨��ɢ�洢
	* @Method: makePath
	* @Description: 
	* @Anthor:�°�����
	*
	* @param filename �ļ�����Ҫ�����ļ������ɴ洢Ŀ¼(���6λ)
	* @param savePath �ļ��洢·��
	* @return �µĴ洢Ŀ¼
	*/ 
	public static String makePath(String filename,String savePath){
		  //�õ��ļ�����hashCode��ֵ���õ��ľ���filename����ַ����������ڴ��еĵ�ַ
	    int hashcode = filename.hashCode();
	    int dir1 = hashcode&0xf;  //0--15
	    int dir2 = (hashcode&0xf0)>>4;  //0-15
	    //�����µı���Ŀ¼
	    String dir = savePath + "/" + dir1 + "/" + dir2;  //upload\2\3  upload\3\5
	    //File�ȿ��Դ����ļ�Ҳ���Դ���Ŀ¼
//	    File file = new File(dir);
//	    //���Ŀ¼������
//	    if(!file.exists()){
//	        //����Ŀ¼
//	        file.mkdirs();
//	    }
	    return dir;
	}
}