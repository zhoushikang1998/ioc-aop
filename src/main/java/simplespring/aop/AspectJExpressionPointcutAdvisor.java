package simplespring.aop;

import org.aopalliance.aop.Advice;

/**
 * 
 * 切点和通知的实现类
 * <p>xml配置文件中配置 pointcut 和 Advice的bean 
 *
 * @date  2019年7月27日
 * @author 周世康
 *
 */
public class AspectJExpressionPointcutAdvisor implements PointcutAdvisor {

    private AspectJExpressionPointcut pointcut = new AspectJExpressionPointcut();
    
    private Advice advice;
    
    private String expression;
    
    public String getExpression() {
        return expression;
    }

    public void setExpression(String expression) {
        pointcut.setExpression(expression);
    }

    public void setPointcut(AspectJExpressionPointcut pointcut) {
        this.pointcut = pointcut;
    }

    public void setAdvice(Advice advice) {
        this.advice = advice;
    }

    @Override
    public Advice getAdvice() {
        return advice;
    }

    @Override
    public Pointcut getPointcut() {
        return pointcut;
    }

}
