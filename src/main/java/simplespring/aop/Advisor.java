package simplespring.aop;

import org.aopalliance.aop.Advice;

/**
 * 
 * 获取 Advice 对象的接口
 *
 * @date  2019年7月27日
 * @author 周世康
 *
 */
public interface Advisor {
    Advice getAdvice();
}
