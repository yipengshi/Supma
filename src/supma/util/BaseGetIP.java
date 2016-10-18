package supma.util;

import java.net.InetAddress;
import java.net.UnknownHostException;

import javax.servlet.http.HttpServletRequest;




public class BaseGetIP{

	private static final long serialVersionUID = 111L;
	
	public BaseGetIP(){

	}
	 //��ȡ�û���ʵIP��ַ����Խ�����
		public static String getIpAddr(HttpServletRequest req)  {
			String ipAddress = null;
			// ipAddress = this.getRequest().getRemoteAddr();
			ipAddress = req.getHeader("x-forwarded-for");
			if (ipAddress == null || ipAddress.length() == 0
					|| "unknown".equalsIgnoreCase(ipAddress)) {
				ipAddress = req.getHeader("Proxy-Client-IP");
			}
			if (ipAddress == null || ipAddress.length() == 0
					|| "unknown".equalsIgnoreCase(ipAddress)) {
				ipAddress = req.getHeader("WL-Proxy-Client-IP");
			}
			if (ipAddress == null || ipAddress.length() == 0
					|| "unknown".equalsIgnoreCase(ipAddress)) {
				ipAddress = req.getRemoteAddr();
				if (ipAddress.equals("127.0.0.1")) {
					// ��������ȡ�������õ�IP
					InetAddress inet = null;
					try {
						inet = InetAddress.getLocalHost();
					} catch (UnknownHostException e) {
						e.printStackTrace();
					}
					ipAddress = inet.getHostAddress();
				}

			}
			// ����ͨ�����������������һ��IPΪ�ͻ�����ʵIP,���IP����','�ָ�
			if (ipAddress != null && ipAddress.length() > 15) { // "***.***.***.***".length()
																// = 15
				if (ipAddress.indexOf(",") > 0) {
					ipAddress = ipAddress.substring(0, ipAddress.indexOf(","));
				}
			}
			if(ipAddress ==null || "".equals(ipAddress)){//������Ȣ������ֻ��ȡ�����ֵ�
				ipAddress = req.getRemoteAddr();
			}
			return ipAddress;
		}
}