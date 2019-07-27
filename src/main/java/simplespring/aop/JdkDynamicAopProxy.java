package simplespring.aop;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

import org.aopalliance.intercept.MethodInterceptor;

/**
 * ���� JDK ��̬����Ĵ������������
 * @author ������
 *
 */
public class JdkDynamicAopProxy extends AbstractAopProxy implements InvocationHandler {

    public JdkDynamicAopProxy(AdvisedSupport advised) {
        super(advised);
    }

    /**
     * ΪĿ�� bean ���ɴ������
     * 
     * @return bean �Ĵ������
     */
    public Object getProxy() {
        return Proxy.newProxyInstance(getClass().getClassLoader(), advised.getTargetSource().getInterfaces(), this);
    }
    
    /**
     *  InvocationHandler �ӿڵ� invoke ��������ʵ�֣���װ�˾���Ĵ����߼�
     *  @param proxy, method, args
     *  @return ��������ԭ�����ķ���ֵ
     *  @throws Throwable
     */
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        MethodMatcher methodMatcher = advised.getMethodMatcher();
        
        // ʹ�÷���ƥ���� methodMatcher ���� bean ��ԭʼ���� method �Ƿ����ƥ�����
        if (methodMatcher != null && methodMatcher.matchers(method, advised.getTargetSource().getTargetClass())) {
            // ��ȡ Advice�� MethodInterceptor �ĸ��ӿڼ̳���Advice
            MethodInterceptor methodInterceptor = advised.getMethodInterceptor();
            
            // �� bean ��ԭʼ method ��װ�� MethodInvocation ʵ�������
            // �� ���ɵĶ��󴫸� Advice ʵ�������ִ��֪ͨ�߼�
            return methodInterceptor.invoke(
                    new ReflectiveMethodInvovation(advised.getTargetSource().getTarget(), method, args));
        } else {
            // ��ǰ method ������ƥ�����ֱ�ӵ��� bean �е�ԭʼ method
            return method.invoke(advised.getTargetSource().getTarget(), args);
        }
    }
}
