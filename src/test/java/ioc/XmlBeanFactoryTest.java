package ioc;

import org.junit.Test;

import simplespring.bean.Car;
import simplespring.bean.HelloService;
import simplespring.bean.Wheel;
import simplespring.ioc.xml.XmlBeanFactory;

public class XmlBeanFactoryTest {
    
    @Test
    public void getBean() throws Exception {
        System.out.println("------- IOC test -------");
        String location = getClass().getClassLoader().getResource("toy-spring.xml").getFile();
        XmlBeanFactory bf = new XmlBeanFactory(location);
        Wheel wheel = (Wheel) bf.getBean("wheel");
        System.out.println(wheel);
        Car car = (Car) bf.getBean("car");
        System.out.println(car);
        
        System.out.println("-------- AOP test -------");
        HelloService helloService = (HelloService) bf.getBean("helloService");
        helloService.sayHelloWorld();
    }
}
