package simplespring.aop;

/**
 * 
 * 获取Pointcut 和 Advice 的接口
 *
 * @date  2019年7月27日
 * @author 周世康
 *
 */
public interface PointcutAdvisor extends Advisor {
    Pointcut getPointcut();
}
