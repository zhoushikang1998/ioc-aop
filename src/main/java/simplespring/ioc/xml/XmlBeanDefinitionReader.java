package simplespring.ioc.xml;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import simplespring.ioc.BeanDefinition;
import simplespring.ioc.BeanDefinitionReader;
import simplespring.ioc.BeanReference;
import simplespring.ioc.PropertyValue;

/**
 * ����xml�����ļ����������� Bean ���÷�װ�� BeanDefinition ����
 * @author ������
 *
 */
public class XmlBeanDefinitionReader implements BeanDefinitionReader {
    
    /**
     * ʹ��HashMapģ��BeanDefinition����
     */
    private Map<String, BeanDefinition> registry;
    
    public XmlBeanDefinitionReader() {
        registry = new HashMap<String, BeanDefinition>();
    }

    /**
     * ʹ��Document����࣬����xml�����ļ�
     */
    public void loadBeanDefinition(String location) throws FileNotFoundException, Exception {
        InputStream inputStream = new FileInputStream(location);
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder documentBuilder = factory.newDocumentBuilder();
        Document document = documentBuilder.parse(inputStream);
        Element root = document.getDocumentElement();
        parseBeanDefinitions(root);
    }
    
    /**
     * ������BeanDefinition
     * @param root
     * @throws Exception 
     */
    private void parseBeanDefinitions(Element root) throws Exception {
        NodeList nodes = root.getChildNodes();
        for (int i = 0; i < nodes.getLength(); i++) {
            Node node = nodes.item(i);
            if (node instanceof Element) {
                Element ele = (Element) node;
                parseBeanDefinition(ele);
            }
        }
    }

    /**
     * Ϊÿһ��bean����BeanDefinition���������ԣ�������������
     * @param ele
     */
    private void parseBeanDefinition(Element ele) throws Exception {
        String name = ele.getAttribute("id");
        String className = ele.getAttribute("class");
        BeanDefinition beanDefinition = new BeanDefinition();
        beanDefinition.setBeanClassName(className);
        processProperty(ele, beanDefinition);
        registry.put(name, beanDefinition);
    }

    /**
     * Ϊÿһ��bean ��������ֵ
     * @param ele
     * @param beanDefinition
     */
    private void processProperty(Element ele, BeanDefinition beanDefinition) {
        NodeList propertyNodes = ele.getElementsByTagName("property");
        for (int i = 0; i < propertyNodes.getLength(); i++) {
            Node propertyNode = propertyNodes.item(i);
            if (propertyNode instanceof Element) {
                Element propertyElement = (Element) propertyNode;
                String name = propertyElement.getAttribute("name");
                String value = propertyElement.getAttribute("value");
                if (value != null && value.length() > 0) {
                    beanDefinition.getPropertyValues().addPropertyValue(new PropertyValue(name, value));
                } else {
                    String ref = propertyElement.getAttribute("ref");
                    if (ref == null || ref.length() == 0) {
                        throw new IllegalArgumentException("ref config error");
                    }
                    BeanReference reference = new BeanReference(ref);
                    beanDefinition.getPropertyValues().addPropertyValue(new PropertyValue(name, reference));
                }
            }
            
        }
    }
    
    
    public Map<String, BeanDefinition> getRegistry() {
        return registry;
    }
}
