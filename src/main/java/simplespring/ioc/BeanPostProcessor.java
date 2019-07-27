package simplespring.ioc;

/**
 * 
 * 后置处理器接口
 * <p>实现该接口可以在bean初始化前后进行处理，比如生成代理对象
 *
 * @date  2019年7月27日
 * @author 周世康
 *
 */
public interface BeanPostProcessor {
    
    Object postProcessBeforeInitialzation(Object bean, String beanName) throws Exception;
    
    Object postProcessAfterInitialzation(Object bean, String beanName) throws Exception;
}
