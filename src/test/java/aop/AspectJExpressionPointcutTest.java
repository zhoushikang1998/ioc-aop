package aop;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import simplespring.aop.AspectJExpressionPointcut;
import simplespring.bean.HelloService;
import simplespring.bean.HelloServiceImpl;

public class AspectJExpressionPointcutTest {

    @Test
    public void testClassFilter() throws Exception {
        String expression = "execution(* toyspring.*.*(..))";
        AspectJExpressionPointcut pointcut = new AspectJExpressionPointcut();
        pointcut.setExpression(expression);
        Boolean matchers = pointcut.matchers(HelloService.class);
        assertTrue(matchers);
    }
    
    @Test
    public void testMethodMatcher() throws Exception {
        String expression = "execution(* toyspring.bean.*.sayHelloWorld(..))";
        AspectJExpressionPointcut pointcut = new AspectJExpressionPointcut();
        pointcut.setExpression(expression);
        Boolean matches = pointcut.matchers(
                HelloServiceImpl.class.getDeclaredMethod("sayHelloWorld"),
                HelloServiceImpl.class);
        assertTrue(matches);
    }
}
