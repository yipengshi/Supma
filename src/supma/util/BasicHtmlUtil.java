package supma.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * <p>
 * Title: HTML��ص�������ʽ������
 * </p>
 * <p>
 * Description: ��������HTML��ǣ�ת��HTML��ǣ��滻�ض�HTML���
 * </p>
 * <p>
 * Copyright: Copyright (c) 2006
 * </p>
 * 
 * @author hejian
 * @version 1.0
 * @createtime 2006-10-16
 */

public class BasicHtmlUtil {
	private static final long serialVersionUID =10L;
	
	private final static String regxpForHtml = "<([^>]*)>"; // ����������<��ͷ��>��β�ı�ǩ

	private final static String regxpForImgTag = "<\\s*img\\s+([^>]*)\\s*>"; // �ҳ�IMG��ǩ

	private final static String regxpForImaTagSrcAttrib = "src=\"([^\"]+)\""; // �ҳ�IMG��ǩ��SRC����

	/**
	 * 
	 */
	public BasicHtmlUtil() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * 
	 * �������ܣ��滻�����������ʾ
	 * <p>
	 * 
	 * @param input
	 * @return String
	 */
	public static String  replaceTag(String input) {
		if (!hasSpecialChars(input)) {
			return input;
		}
		input = input.replaceAll("\r\n", "<br />").replaceAll("\n", "<br />");
		StringBuffer filtered = new StringBuffer(input.length());
		char c;
		for (int i = 0; i <= input.length() - 1; i++) {
			c = input.charAt(i);
			switch (c) {
			case '<':
				filtered.append("&lt;");
				break;
			case '>':
				filtered.append("&gt;");
				break;
			case '"':
				filtered.append("&quot;");
				break;
			case '&':
				filtered.append("&amp;");
				break;
			default:
				filtered.append(c);
			}

		}
		return (filtered.toString());
	}

	/**
	 * 
	 * �������ܣ��жϱ���Ƿ����
	 * <p>
	 * 
	 * @param input
	 * @return boolean
	 */
	public  static boolean hasSpecialChars(String input) {
		boolean flag = false;
		if ((input != null) && (input.length() > 0)) {
			char c;
			for (int i = 0; i <= input.length() - 1; i++) {
				c = input.charAt(i);
				switch (c) {
				case '>':
					flag = true;
					break;
				case '<':
					flag = true;
					break;
				case '"':
					flag = true;
					break;
				case '&':
					flag = true;
					break;
				}
			}
			if(input.indexOf("\r\n") != -1 || input.indexOf("\n") != -1){
				flag = true;
			}
		}
		return flag;
	}

	/**
	 * 
	 * �������ܣ�����������"<"��ͷ��">"��β�ı�ǩ
	 * <p>
	 * 
	 * @param str
	 * @return String
	 */
	public static String filterHtml(String str) {
		Pattern pattern = Pattern.compile(regxpForHtml);
		Matcher matcher = pattern.matcher(str);
		StringBuffer sb = new StringBuffer();
		boolean result1 = matcher.find();
		while (result1) {
			matcher.appendReplacement(sb, "");
			result1 = matcher.find();
		}
		matcher.appendTail(sb);
		return sb.toString();
	}

	/**
	 * 
	 * �������ܣ�����ָ����ǩ
	 * <p>
	 * 
	 * @param str
	 * @param tag
	 *            ָ����ǩ
	 * @return String
	 */
	public static String fiterHtmlTag(String str, String tag) {
		String regxp = "<\\s*" + tag + "\\s+([^>]*)\\s*>";
		Pattern pattern = Pattern.compile(regxp);
		Matcher matcher = pattern.matcher(str);
		StringBuffer sb = new StringBuffer();
		boolean result1 = matcher.find();
		while (result1) {
			matcher.appendReplacement(sb, "");
			result1 = matcher.find();
		}
		matcher.appendTail(sb);
		return sb.toString();
	}

	/**
	 * 
	 * �������ܣ��滻ָ���ı�ǩ
	 * <p>
	 * 
	 * @param str
	 * @param beforeTag
	 *            Ҫ�滻�ı�ǩ
	 * @param tagAttrib
	 *            Ҫ�滻�ı�ǩ����ֵ
	 * @param startTag
	 *            �±�ǩ��ʼ���
	 * @param endTag
	 *            �±�ǩ�������
	 * @return String
	 * @�磺�滻img��ǩ��src����ֵΪ[img]����ֵ[/img]
	 */
	public static String replaceHtmlTag(String str, String beforeTag,
			String tagAttrib, String startTag, String endTag) {
		String regxpForTag = "<\\s*" + beforeTag + "\\s+([^>]*)\\s*>";
		String regxpForTagAttrib = tagAttrib + "=\"([^\"]+)\"";
		Pattern patternForTag = Pattern.compile(regxpForTag);
		Pattern patternForAttrib = Pattern.compile(regxpForTagAttrib);
		Matcher matcherForTag = patternForTag.matcher(str);
		StringBuffer sb = new StringBuffer();
		boolean result = matcherForTag.find();
		while (result) {
			StringBuffer sbreplace = new StringBuffer();
			Matcher matcherForAttrib = patternForAttrib.matcher(matcherForTag
					.group(1));
			if (matcherForAttrib.find()) {
				matcherForAttrib.appendReplacement(sbreplace, startTag
						+ matcherForAttrib.group(1) + endTag);
			}
			matcherForTag.appendReplacement(sb, sbreplace.toString());
			result = matcherForTag.find();
		}
		matcherForTag.appendTail(sb);
		return sb.toString();
	}
	
	public  String html2Text(String inputString) {    
	      String htmlStr = inputString; //��html��ǩ���ַ���    
	      String textStr ="";    
	      java.util.regex.Pattern p_script;    
	      java.util.regex.Matcher m_script;    
	      java.util.regex.Pattern p_style;    
	      java.util.regex.Matcher m_style;    
	      java.util.regex.Pattern p_html;    
	      java.util.regex.Matcher m_html;    
	          
	      try {    
	       String regEx_script = "<[/s]*?script[^>]*?>[/s/S]*?<[/s]*?//[/s]*?script[/s]*?>"; //����script��������ʽ{��<script[^>]*?>[/s/S]*?<//script> }    
	       String regEx_style = "<[/s]*?style[^>]*?>[/s/S]*?<[/s]*?//[/s]*?style[/s]*?>"; //����style��������ʽ{��<style[^>]*?>[/s/S]*?<//style> }    
	          String regEx_html = "<[^>]+>"; //����HTML��ǩ��������ʽ    
	           
	          p_script = Pattern.compile(regEx_script,Pattern.CASE_INSENSITIVE);    
	          m_script = p_script.matcher(htmlStr);    
	          htmlStr = m_script.replaceAll(""); //����script��ǩ    
	   
	          p_style = Pattern.compile(regEx_style,Pattern.CASE_INSENSITIVE);    
	          m_style = p_style.matcher(htmlStr);    
	          htmlStr = m_style.replaceAll(""); //����style��ǩ    
	           
	          p_html = Pattern.compile(regEx_html,Pattern.CASE_INSENSITIVE);    
	          m_html = p_html.matcher(htmlStr);    
	          htmlStr = m_html.replaceAll(""); //����html��ǩ    
	           
	       textStr = htmlStr;    
	           
	      }catch(Exception e) {    
	                  System.err.println("Html2Text: " + e.getMessage());    
	      }    
	          
	      return   textStr;
	}
	
    public  String replaceString(String _str) {
		if(_str==null)
			return "";
		return _str.replaceAll("\"","&#34;").replaceAll("'","&#39;");
	}
    
}
