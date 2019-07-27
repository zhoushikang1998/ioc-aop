package simplespring.aop;

import java.lang.reflect.AccessibleObject;
import java.lang.reflect.Method;

import org.aopalliance.intercept.MethodInvocation;

/**
 * 
 * MethodInvocation 实现类对象
 * <p>MethodInvocation ---> Invocation ---> Joinpoint
 * <p>相当于将bean的原始method 封装成 joinpoint
 * @date  2019年7月27日
 * @author 周世康
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
