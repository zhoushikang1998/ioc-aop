package simplespring.aop;

/**
 * 
 * ��ȡPointcut �� Advice �Ľӿ�
 *
 * @date  2019��7��27��
 * @author ������
 *
 */
public interface PointcutAdvisor extends Advisor {
    Pointcut getPointcut();
}
