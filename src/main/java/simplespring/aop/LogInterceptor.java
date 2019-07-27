package simplespring.aop;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

/**
 * 
 * MethodInterceptor�ľ���ʵ����
 * <p>ʵ�ʵ�֪ͨ��
 *
 * @date  2019��7��27��
 * @author ������
 *
 */
public class LogInterceptor implements MethodInterceptor {
    
    /**
     * ֪ͨ�ľ����߼�
     */
    public Object invoke(MethodInvocation invocation) throws Throwable {
        System.out.println(invocation.getMethod().getName() + " method start...");
        Object object = invocation.proceed();
        System.out.println(invocation.getMethod().getName() + " method end...");
        return object;
    }
}
