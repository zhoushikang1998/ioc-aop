package simplespring.aop;

import org.aopalliance.aop.Advice;

/**
 * 
 * ��ȡ Advice ����Ľӿ�
 *
 * @date  2019��7��27��
 * @author ������
 *
 */
public interface Advisor {
    Advice getAdvice();
}
