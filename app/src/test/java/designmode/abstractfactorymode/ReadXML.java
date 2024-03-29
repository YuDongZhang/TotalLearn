package designmode.abstractfactorymode;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

class ReadXML
    {
        public static Object getObject()
        {
            try
            {
                DocumentBuilderFactory dFactory=DocumentBuilderFactory.newInstance();
                DocumentBuilder builder=dFactory.newDocumentBuilder();
                Document doc;
                doc=builder.parse(new File("src/AbstractFactory/config.xml"));
                NodeList nl=doc.getElementsByTagName("className");
                Node classNode=nl.item(0).getFirstChild();
                String cName="AbstractFactory."+classNode.getNodeValue();
                System.out.println("新类名："+cName);
                Class<?> c=Class.forName(cName);
                  Object obj=c.newInstance();
                return obj;
            }  
            catch(Exception e)
            {
                   e.printStackTrace();
                   return null;
            }
        }
    }

