package simplespring.ioc.factory;

/**
 * 
 * 实现该接口的类把相应的依赖设置到 bean对象中
 *
 * @date  2019年7月27日
 * @author 周世康
 *
 */
public interface BeanFactoryAware {
    void setBeanFactory(BeanFactory beanFactory) throws Exception;
}
