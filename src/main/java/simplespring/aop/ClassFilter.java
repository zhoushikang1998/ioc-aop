package simplespring.aop;

/**
 * Ŀ���������
 * <p>ʵ�ָýӿڵ��࣬�ж�beanClass����е��Ƿ����ƥ�䵽һ�����ӵ�
 *
 * @date  2019��7��27��
 * @author ������
 *
 */
public interface ClassFilter {
    Boolean matchers(Class beanClass) throws Exception;
}
