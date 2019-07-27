package simplespring.aop;

import org.aopalliance.aop.Advice;

/**
 * 
 * �е��֪ͨ��ʵ����
 * <p>xml�����ļ������� pointcut �� Advice��bean 
 *
 * @date  2019��7��27��
 * @author ������
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
