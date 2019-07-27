package simplespring.aop;

import java.util.List;

import org.aopalliance.intercept.MethodInterceptor;

import simplespring.ioc.BeanPostProcessor;
import simplespring.ioc.factory.BeanFactory;
import simplespring.ioc.factory.BeanFactoryAware;
import simplespring.ioc.xml.XmlBeanFactory;

/**
 * ʵ���� BeanPostProcessor �� BeanFactoryAware �ӿ�
 * <P>ͨ��BeanPostProcessor��postProcessAfterInitialzation��������bean�Ĵ�����
 * <p>ʵ��BeanFactoryAware�ӿڣ���aop�������õ� bean������
 * @date  2019��7��27��
 * @author ������
 *
 */
public class AspectJAwareAdvisorAutoProxyCreator implements BeanPostProcessor, BeanFactoryAware {

    private XmlBeanFactory xmlBeanFactory;
    
    /**
     * ����BeanFactory
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
     *  bean��ʼ����ʹ�ú��ô���������bean�Ĵ������
     */
    @Override
    public Object postProcessAfterInitialzation(Object bean, String beanName) throws Exception {
        /*
         * �������� if �жϺ��б�Ҫ�����ɾ������ʹ���������ѭ��״̬��
         * ���յ��� StackOverflowError ������
         */
        if (bean instanceof AspectJExpressionPointcutAdvisor) {
            return bean;
        }
        if (bean instanceof MethodInterceptor) {
            return bean;
        }
        
        // 1. �� BeanFactory ���� AspectJExpressionPointcutAdvisor �Ķ���
        List<AspectJExpressionPointcutAdvisor> advisors = xmlBeanFactory.getBeansForType(AspectJExpressionPointcutAdvisor.class);
        
        for (AspectJExpressionPointcutAdvisor advisor : advisors) {
            
            // 2.ʹ��Pointcut ����ƥ�䵱ǰ bean ����
            if (advisor.getPointcut().getClassFilter().matchers(bean.getClass())) {
                ProxyFactory advisedSupport = new ProxyFactory();
                advisedSupport.setMethodInterceptor((MethodInterceptor) advisor.getAdvice());
                advisedSupport.setMethodMatcher(advisor.getPointcut().getMethodMatcher());
                
                TargetSource targetSource = new TargetSource(bean, bean.getClass(), bean.getClass().getInterfaces());
                advisedSupport.setTargetSource(targetSource);
                
                // 3.���ɴ�����󣬲�����
                return advisedSupport.getProxy();
            }
        }
        // 4.ƥ��ʧ�ܣ����� bean
        return bean;
    }
    
}
