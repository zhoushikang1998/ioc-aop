package simplespring.aop;

import java.lang.reflect.AccessibleObject;
import java.lang.reflect.Method;

import org.aopalliance.intercept.MethodInvocation;

/**
 * 
 * MethodInvocation ʵ�������
 * <p>MethodInvocation ---> Invocation ---> Joinpoint
 * <p>�൱�ڽ�bean��ԭʼmethod ��װ�� joinpoint
 * @date  2019��7��27��
 * @author ������
 *
 */
public class ReflectiveMethodInvovation implements MethodInvocation {

    protected Object target;
    
    protected Method method;
    
    protected Object[] arguments;
    
    public ReflectiveMethodInvovation(Object target, Method method, Object[] arguments) {
        super();
        this.target = target;
        this.method = method;
        this.arguments = arguments;
    }

    public Object[] getArguments() {
        return arguments;
    }

    public Object proceed() throws Throwable {
        return method.invoke(target, arguments);
    }

    public Object getThis() {
        return target;
    }

    public AccessibleObject getStaticPart() {
        return method;
    }

    public Method getMethod() {
        return method;
    }

}
