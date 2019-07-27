package simplespring.aop;

import org.aopalliance.intercept.MethodInterceptor;

/**
 * 
 * 支持各项通知的类
 *
 * @date  2019年7月27日
 * @author 周世康
 *
 */
public class AdvisedSupport {

    private TargetSource targetSource;
    
    // 继承了Advice类的Interceptor类
    private MethodInterceptor methodInterceptor;
    
    private MethodMatcher methodMatcher;

    public TargetSource getTargetSource() {
        return targetSource;
    }

    public void setTargetSource(TargetSource targetSource) {
        this.targetSource = targetSource;
    }

    public MethodInterceptor getMethodInterceptor() {
        return methodInterceptor;
    }

    public void setMethodInterceptor(MethodInterceptor methodInterceptor) {
        this.methodInterceptor = methodInterceptor;
    }

    public MethodMatcher getMethodMatcher() {
        return methodMatcher;
    }

    public void setMethodMatcher(MethodMatcher methodMatcher) {
        this.methodMatcher = methodMatcher;
    }
    
    
}
