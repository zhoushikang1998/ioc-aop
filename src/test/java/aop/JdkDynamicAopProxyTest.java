package aop;

import org.junit.Test;

import simplespring.aop.AdvisedSupport;
import simplespring.aop.JdkDynamicAopProxy;
import simplespring.aop.LogInterceptor;
import simplespring.aop.TargetSource;
import simplespring.bean.HelloService;
import simplespring.bean.HelloServiceImpl;



public class JdkDynamicAopProxyTest {
    
    @Test
    public void getProxy() throws Exception {
        System.out.println("------ no proxy -------");
        HelloService helloService = new HelloServiceImpl();
        helloService.sayHelloWorld();
        
        System.out.println("\n --------- proxy --------");
        AdvisedSupport advised = new AdvisedSupport();
        advised.setMethodInterceptor(new LogInterceptor());
        
        TargetSource targetSource = new TargetSource(
                helloService, HelloServiceImpl.class, HelloServiceImpl.class.getInterfaces());
        advised.setTargetSource(targetSource);
        advised.setMethodMatcher((method, beanClass) -> true);
        
        helloService = (HelloService) new JdkDynamicAopProxy(advised).getProxy();
        helloService.sayHelloWorld();
    }
}
