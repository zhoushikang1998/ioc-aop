package simplespring.aop;

import java.lang.reflect.Method;

/**
 * 
 * ����ƥ�����ӿ�
 *
 * @date  2019��7��27��
 * @author ������
 *
 */
public interface MethodMatcher {
    Boolean matchers(Method method, Class beanClass);
}
