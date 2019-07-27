package simplespring.ioc.factory;

/**
 * 
 * 获取bean的工厂接口
 *
 * @date  2019年7月27日
 * @author 周世康
 *
 */
public interface BeanFactory {
    Object getBean(String beanId) throws Exception;
}
