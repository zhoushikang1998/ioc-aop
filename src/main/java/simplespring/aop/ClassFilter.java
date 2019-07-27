package simplespring.aop;

/**
 * 目标类过滤器
 * <p>实现该接口的类，判断beanClass这个切点是否可以匹配到一个连接点
 *
 * @date  2019年7月27日
 * @author 周世康
 *
 */
public interface ClassFilter {
    Boolean matchers(Class beanClass) throws Exception;
}
