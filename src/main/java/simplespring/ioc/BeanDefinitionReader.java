package simplespring.ioc;

import java.io.FileNotFoundException;

/**
 * 
 * ����xml�����ļ��Ľӿ�
 *
 * @date  2019��7��27��
 * @author ������
 *
 */
public interface BeanDefinitionReader {
    void loadBeanDefinition(String location) throws FileNotFoundException, Exception;
}
