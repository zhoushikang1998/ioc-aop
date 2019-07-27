package simplespring.aop;

import java.lang.reflect.Method;
import java.util.HashSet;
import java.util.Set;

import org.aspectj.weaver.tools.PointcutExpression;
import org.aspectj.weaver.tools.PointcutParser;
import org.aspectj.weaver.tools.PointcutPrimitive;
import org.aspectj.weaver.tools.ShadowMatch;

/**
 * 
 * 使用 AspectJ Expression 的匹配类
 * <p>判断方法与切点是否匹配
 *
 * @date  2019年7月27日
 * @author 周世康
 *
 */
public class AspectJExpressionPointcut implements Pointcut, ClassFilter, MethodMatcher {

    private PointcutParser pointcutParser;
    
    private String expression;
    
    private PointcutExpression pointcutExpression;
    
    private static final Set<PointcutPrimitive> DEFAULT_SUPPORTED_PRIMITIVES = new HashSet<>();
    
    static {
        DEFAULT_SUPPORTED_PRIMITIVES.add(PointcutPrimitive.EXECUTION);
        DEFAULT_SUPPORTED_PRIMITIVES.add(PointcutPrimitive.ARGS);
        DEFAULT_SUPPORTED_PRIMITIVES.add(PointcutPrimitive.REFERENCE);
        DEFAULT_SUPPORTED_PRIMITIVES.add(PointcutPrimitive.THIS);
        DEFAULT_SUPPORTED_PRIMITIVES.add(PointcutPrimitive.TARGET);
        DEFAULT_SUPPORTED_PRIMITIVES.add(PointcutPrimitive.WITHIN);
        DEFAULT_SUPPORTED_PRIMITIVES.add(PointcutPrimitive.AT_ANNOTATION);
        DEFAULT_SUPPORTED_PRIMITIVES.add(PointcutPrimitive.AT_WITHIN);
        DEFAULT_SUPPORTED_PRIMITIVES.add(PointcutPrimitive.AT_ARGS);
        DEFAULT_SUPPORTED_PRIMITIVES.add(PointcutPrimitive.AT_TARGET);
    }
    
    public AspectJExpressionPointcut() {
        this(DEFAULT_SUPPORTED_PRIMITIVES);
    }
    
    public AspectJExpressionPointcut(Set<PointcutPrimitive> supportedPrimitives) {
        pointcutParser  = PointcutParser.getPointcutParserSupportingSpecifiedPrimitivesAndUsingContextClassloaderForResolution(supportedPrimitives);
    }
    
    /**
     * 使用 AspectJ Expression 匹配类
     * @param method
     * @param beanClass
     * @return 成功匹配返回 true，否则返回 false
     */
    @Override
    public Boolean matchers(Method method, Class beanClass) {
        CheckReadyToMatch();
        ShadowMatch shadowMatch = pointcutExpression.matchesMethodExecution(method);
        
        // 
        if (shadowMatch.alwaysMatches()) {
            return true;
        } else if (shadowMatch.neverMatches()) {
            return false;
        }
        return false;
    }

    private void CheckReadyToMatch() {
        if (getExpression() == null) {
            throw new IllegalStateException("Must set property 'expression' before attempting to match");
        }
        if (pointcutExpression == null) {
            pointcutExpression = pointcutParser.parsePointcutExpression(expression);
        }
    }

    /**
     * 使用 AspectJ Expression 匹配类
     * @param beanClass
     * @return 成功匹配返回 true，否则返回 false
     */
    @Override
    public Boolean matchers(Class beanClass) throws Exception {
        CheckReadyToMatch();
        return pointcutExpression.couldMatchJoinPointsInType(beanClass);
    }

    @Override
    public ClassFilter getClassFilter() {
        return this;
    }

    @Override
    public MethodMatcher getMethodMatcher() {
        return this;
    }

    public String getExpression() {
        return expression;
    }

    public void setExpression(String expression) {
        this.expression = expression;
    }
    
}
