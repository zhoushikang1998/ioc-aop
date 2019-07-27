package simplespring.ioc;

import java.io.FileNotFoundException;

/**
 * 
 * 解析xml配置文件的接口
 *
 * @date  2019年7月27日
 * @author 周世康
 *
 */
public interface BeanDefinitionReader {
    void loadBeanDefinition(String location) throws FileNotFoundException, Exception;
}
