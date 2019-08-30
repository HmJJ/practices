package com.nott.gof._03_factoryMethod.test.test1;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;

/**
 * @Author: wangjun
 * @Description:
 * @Date: created in 2019/7/24 16:33
 * @Modified By:
 **/
public class ReadXML1 {

    public static final Logger logger = LoggerFactory.getLogger(ReadXML1.class);

    public static Object getObject() {
        try {
            //创建文档对象
            DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = builderFactory.newDocumentBuilder();
            Document document;
            document = builder.parse(new File("src/main/resources/files/config1.xml"));
            //获取包含类名的文本节点
            NodeList nodeList = document.getElementsByTagName("className");
            Node classNode = nodeList.item(0).getFirstChild();
            String cName = "com.nott.gof._03_factoryMethod.code." + classNode.getNodeValue();
            System.out.println("新类名：" + cName);
            //通过类名生成实例对象并将其返回
            Class<?> c = Class.forName(cName);
            Object obj = c.newInstance();
            return  obj;
        } catch (Exception e) {
            logger.info(e.getMessage());
            return null;
        }
    }

}
