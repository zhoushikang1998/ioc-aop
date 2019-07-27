package simplespring.aop;

import java.util.List;

import org.aopalliance.intercept.MethodInterceptor;

import simplespring.ioc.BeanPostProcessor;
import simplespring.ioc.factory.BeanFactory;
import simplespring.ioc.factory.BeanFactoryAware;
import simplespring.ioc.xml.XmlBeanFactory;

/**
 * 实现了 BeanPostProcessor 和 BeanFactoryAware 接口
 * <P>通过BeanPostProcessor的postProcessAfterInitialzation方法生成bean的代理类
 * <p>实现BeanFactoryAware接口，把aop依赖设置到 bean对象中
 * @date  2019年7月27日
 * @author 周世康
 *
 */
public class AspectJAwareAdvisorAutoProxyCreator implements BeanPostProcessor, BeanFactoryAware {

    private XmlBeanFactory xmlBeanFactory;
    
    /**
     * 设置BeanFactory
     */
    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws Exception {
        xmlBeanFactory = (XmlBeanFactory) beanFactory;
    }

    @Override
    public Object postProcessBeforeInitialzation(Object bean, String beanName) throws Exception {
        return bean;
    }
    
    /**
     *  bean初始化后使用后置处理器生成bean的代理对象
     */
    @Override
    public Object postProcessAfterInitialzation(Object bean, String beanName) throws Exception {
        /*
         * 这里两个 if 判断很有必要，如果删除将会使程序进入死循环状态，
         * 最终导致 StackOverflowError 错误发生
         */
        if (bean instanceof AspectJExpressionPointcutAdvisor) {
            return bean;
        }
        if (bean instanceof MethodInterceptor) {
            return bean;
        }
        
        // 1. 从 BeanFactory 查找 AspectJExpressionPointcutAdvisor 的对象
        List<AspectJExpressionPointcutAdvisor> advisors = xmlBeanFactory.getBeansForType(AspectJExpressionPointcutAdvisor.class);
        
        for (AspectJExpressionPointcutAdvisor advisor : advisors) {
            
            // 2.使用Pointcut 对象匹配当前 bean 对象
            if (advisor.getPointcut().getClassFilter().matchers(bean.getClass())) {
                ProxyFactory advisedSupport = new ProxyFactory();
                advisedSupport.setMethodInterceptor((MethodInterceptor) advisor.getAdvice());
                advisedSupport.setMethodMatcher(advisor.getPointcut().getMethodMatcher());
                
                TargetSource targetSource = new TargetSource(bean, bean.getClass(), bean.getClass().getInterfaces());
                advisedSupport.setTargetSource(targetSource);
                
                // 3.生成代理对象，并返回
                return advisedSupport.getProxy();
            }
        }
        // 4.匹配失败，返回 bean
        return bean;
    }
    
}
