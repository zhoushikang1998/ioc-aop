package simplespring.ioc;

/**
 * 
 * ���ô������ӿ�
 * <p>ʵ�ָýӿڿ�����bean��ʼ��ǰ����д����������ɴ������
 *
 * @date  2019��7��27��
 * @author ������
 *
 */
public interface BeanPostProcessor {
    
    Object postProcessBeforeInitialzation(Object bean, String beanName) throws Exception;
    
    Object postProcessAfterInitialzation(Object bean, String beanName) throws Exception;
}
