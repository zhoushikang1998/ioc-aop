package simplespring.aop;

import java.lang.reflect.Method;

/**
 * 
 * 方法匹配器接口
 *
 * @date  2019年7月27日
 * @author 周世康
 *
 */
public interface MethodMatcher {
    Boolean matchers(Method method, Class beanClass);
}
