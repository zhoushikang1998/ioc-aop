package simplespring.aop;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

/**
 * 
 * MethodInterceptor的具体实现类
 * <p>实际的通知类
 *
 * @date  2019年7月27日
 * @author 周世康
 *
 */
public class LogInterceptor implements MethodInterceptor {
    
    /**
     * 通知的具体逻辑
     */
    public Object invoke(MethodInvocation invocation) throws Throwable {
        System.out.println(invocation.getMethod().getName() + " method start...");
        Object object = invocation.proceed();
        System.out.println(invocation.getMethod().getName() + " method end...");
        return object;
    }
}
