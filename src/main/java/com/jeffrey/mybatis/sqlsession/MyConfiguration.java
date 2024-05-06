package com.jeffrey.mybatis.sqlsession;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.File;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

/**
 * @program: mybatisPPP
 * @author: Jeffrey
 * @create: 2024-05-06 12:20
 * @description:
 **/
public class MyConfiguration {
    public static ClassLoader loader =
                ClassLoader.getSystemClassLoader();

    public Connection build(String resource){
        Connection connection = null;
        try {
            InputStream resourceAsStream =
                    loader.getResourceAsStream(resource);
            //加载 mybatis-config.xml
        SAXReader saxReader = new SAXReader();
            Document read = saxReader.read(resourceAsStream);

            Element rootElement = read.getRootElement();
            connection = evalDataSource(rootElement);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return connection;
    }

    // 解析 xml 文件信息 ，返回 connection
    public Connection evalDataSource(Element root){
        if (! root.getName().equalsIgnoreCase("database")){
            throw new RuntimeException("根节点不正确");
        }
        String driverClassName = null;
        String url = null;
        String userName = null;
        String password = null;
        for (Object o :root.elements("property")) {
            Element element = (Element)o;
            String name = element.attributeValue("name");
            String value = element.attributeValue("value");
            //判断是否得到name 和 value
            if (name == null || value == null){
                throw new RuntimeException("property 节点没有设置 name/value");
            }

            switch (name){
                case "driverClassName":
                    driverClassName = value;
                    break;
                case "url" :
                    url = value;
                    break;
                case "username" :
                    userName = value;
                    break;
                case "password":
                    password = value;
                    break;
                default:
                    throw new RuntimeException("属性名没有匹配");
            }
        }
        Connection connection = null;
        try {
            Class.forName(driverClassName);
            connection = DriverManager.getConnection(url, userName, password);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return connection;
    }


}
