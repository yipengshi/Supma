package com.ideas.util;

import org.jdom.input.SAXBuilder;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import java.util.List;
import java.awt.Color;

/**
 * @author zjm
 * @since 2003-05-22
 */
public class ConfigReader
{

    public ConfigReader(String configFileName)
    {
        parseConfigXML(configFileName);
    }

    public void parseConfigXML(String configURI)
    {
		Configuration.ConfigFilePath = configURI;

        System.out.println("���ڶ�ȡ�����ļ�......");
        try
        {
            SAXBuilder builder = new SAXBuilder(false);

			
            Document doc = builder.build(configURI);
            Element root = doc.getRootElement();

            //���ӳ�����
            Configuration.ConnectionPoolName = "idb";
            Configuration.DB_URL = root.getChild("database").getChild("url").
                getTextTrim()+"?useUnicode=true&characterEncoding=gb2312";
            Configuration.DB_JDBCDRIVER = root.getChild("database").getChild(
                "jdbcdriver").getTextTrim();
            Configuration.DB_USERNAME = root.getChild("database").getChild(
                "dbusername").getTextTrim();
            Configuration.DB_PASSWORD = root.getChild("database").getChild(
                "dbpassword").getTextTrim();
            Configuration.DB_MAXCONNNUM = root.getChild("database").getChild(
                "maxconnection").getTextTrim();
            Configuration.DB_LOGFILE = root.getChild("database").getChild("logfile").
                getTextTrim();
			
            Configuration.Number_shown = Integer.parseInt(root.getChild("page").
                getChild("number_shown").getTextTrim());

            System.out.println("��ȡ�����ļ���ɣ���");
			Configuration.ConfigFilePath = configURI + "��ȡ�����ļ���ɣ���";

        }
        catch(Exception jdome)
//        catch(Exception jdome)
        {
            System.err.println("��ȡ�����ļ�ʧ�ܣ�");
            Configuration.ConfigFilePath = configURI + "��ȡ�����ļ�ʧ�ܣ�" + jdome;
			jdome.printStackTrace();
        }
    }

    public static void main(String[] args)
    {
        ConfigReader configReader = new ConfigReader(
            "C:\\yj\\webapp\\WEB-INF\\conf.xml");
        //new ConfigReader("WEB-INF/alert.properties");
    }

}
