package simplespring.ioc.factory;

/**
 * 
 * ��ȡbean�Ĺ����ӿ�
 *
 * @date  2019��7��27��
 * @author ������
 *
 */
public interface BeanFactory {
    Object getBean(String beanId) throws Exception;
}
