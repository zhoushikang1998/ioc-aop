package simplespring.ioc.factory;

/**
 * 
 * ʵ�ָýӿڵ������Ӧ���������õ� bean������
 *
 * @date  2019��7��27��
 * @author ������
 *
 */
public interface BeanFactoryAware {
    void setBeanFactory(BeanFactory beanFactory) throws Exception;
}
