package simplespring.aop;

import org.aopalliance.intercept.MethodInterceptor;

/**
 * 
 * ֧�ָ���֪ͨ����
 *
 * @date  2019��7��27��
 * @author ������
 *
 */
public class AdvisedSupport {

    private TargetSource targetSource;
    
    // �̳���Advice���Interceptor��
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
