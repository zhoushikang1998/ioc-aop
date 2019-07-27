package simplespring.aop;

/**
 * 
 * 切点接口
 * <p>获取方法匹配器和目标类过滤器
 * @date  2019年7月27日
 * @author 周世康
 *
 */
public interface Pointcut {
    ClassFilter getClassFilter();
    MethodMatcher getMethodMatcher();
}
