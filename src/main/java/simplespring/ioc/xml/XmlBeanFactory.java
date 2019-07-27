package simplespring.ioc.xml;

import java.io.FileNotFoundException;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import simplespring.ioc.BeanDefinition;
import simplespring.ioc.BeanPostProcessor;
import simplespring.ioc.BeanReference;
import simplespring.ioc.PropertyValue;
import simplespring.ioc.factory.BeanFactory;
import simplespring.ioc.factory.BeanFactoryAware;

/**
 * 实际的bean工厂类
 * <P>实际的BeanDefinition容器，并注册 BeanPostProcessor 相关实现类到 BeanPostProcessor 容器中
 * 
 * @date  2019年7月27日
 * @author 周世康
 *
 */
public class XmlBeanFactory implements BeanFactory {

    private Map<String, BeanDefinition> beanDefinitionMap = new HashMap<String, BeanDefinition>();
    
    private List<String> beanDefinitionNames = new ArrayList<String>();
    
    private List<BeanPostProcessor> beanPostProcessors = new ArrayList<BeanPostProcessor>();
    
    private XmlBeanDefinitionReader beanDefinitionReader;
    
    public XmlBeanFactory(String location) throws Exception {
        beanDefinitionReader = new XmlBeanDefinitionReader();
        loadBeanDefinitions(location);
    }
    
    /**
     * 获取 beanDefinition中的bean，若beanDefinition中的bean为null，则创建并初始化bean
     */
    public Object getBean(String name) throws Exception {
        BeanDefinition beanDefinition = beanDefinitionMap.get(name);
        if (beanDefinition == null) {
            throw new IllegalArgumentException("no this bean with name " + name);
        }
        
        Object bean = beanDefinition.getBean();
        if (bean == null) {
            bean = createBean(beanDefinition);
            bean = initializeBean(bean, name);
            beanDefinition.setBean(bean);
        }
        return bean;
    }
    
    
    
    /**
     * 创建bean, 并为bean添加配置属性
     * @param beanDefinition
     * @return
     * @throws Exception
     */
    private Object createBean(BeanDefinition beanDefinition) throws Exception {
        Object bean = beanDefinition.getBeanClass().newInstance();
        applyPropertyValues(bean, beanDefinition);
        return bean;
    }
    /**
     * 将BeanDefinition中的属性配置到对应BeanDefinition中
     * @param bean
     * @param beanDefinition
     * @throws Exception
     */
    private void applyPropertyValues(Object bean, BeanDefinition beanDefinition) throws Exception {
        if (bean instanceof BeanFactoryAware) {
            ((BeanFactoryAware) bean).setBeanFactory(this);
        }
        for (PropertyValue propertyValue : beanDefinition.getPropertyValues().getPropertyValues()) {
            Object value = propertyValue.getValue();
            if (value instanceof BeanReference) {
                BeanReference beanReference = (BeanReference) value;
                value = getBean(beanReference.getName());
            }
            
            try {
                Method declaredMethod = bean.getClass().getDeclaredMethod(
                        "set" + propertyValue.getName().substring(0, 1).toUpperCase()
                        + propertyValue.getName().substring(1), value.getClass());
                declaredMethod.setAccessible(true);
                declaredMethod.invoke(bean, value);
            } catch (NoSuchMethodException e) {
                Field declaredField = bean.getClass().getDeclaredField(propertyValue.getName());
                declaredField.setAccessible(true);
                declaredField.set(bean, value);
            }
        }
    }
    
    /**
     * 初始化bean，为该bean 添加后置处理器的处理方法
     * @param bean
     * @param name
     * @return
     * @throws Exception
     */
    private Object initializeBean(Object bean, String name) throws Exception {
        for (BeanPostProcessor beanPostProcessor : beanPostProcessors) {
            bean = beanPostProcessor.postProcessBeforeInitialzation(bean, name);
        }
        
        for (BeanPostProcessor beanPostProcessor : beanPostProcessors) {
            bean = beanPostProcessor.postProcessAfterInitialzation(bean, name);
        }
        
        return bean;
    }
    
    /**
     * 使用XmlBeanDefinitionReader类解析xml配置文件
     * <p>将BeanDefinition添加到beanDefinitionMap中
     * <p>将bean的名字添加到beanDefinitionNames中
     * <p>将beanDefinitionMap中继承了BeanPostProcessor类的bean添加到beanPostProcessors中
     * 
     * @param location
     * @throws FileNotFoundException
     * @throws Exception
     */
    private void loadBeanDefinitions(String location) throws FileNotFoundException, Exception {
        beanDefinitionReader.loadBeanDefinition(location);
        registryBeanDefinition();
        registryBeanPostProcessor();
    }
    /**
     * 将BeanDefinition添加到beanDefinitionMap中
     * <p>将bean的名字添加到beanDefinitionNames中
     */
    private void registryBeanDefinition() {
        for (Map.Entry<String, BeanDefinition> entry : beanDefinitionReader.getRegistry().entrySet()) {
            String name = entry.getKey();
            BeanDefinition beanDefinition = entry.getValue();
            beanDefinitionMap.put(name, beanDefinition);
            beanDefinitionNames.add(name);
        }
    }

    /**
     * 将beanDefinitionMap中继承了BeanPostProcessor类的bean添加到beanPostProcessors中
     * @throws Exception
     */
    private void registryBeanPostProcessor() throws Exception {
        List beans = getBeansForType(BeanPostProcessor.class);
        for (Object bean : beans) {
            addBeanPostProcessor((BeanPostProcessor) bean);
        }
    }

    /**
     * 将beanDefinitionMap中继承了BeanPostProcessor类的bean添加到List中返回
     * @param <T>
     * @param type
     * @return
     * @throws Exception
     */
    @SuppressWarnings("unchecked")
    public <T> List<T>  getBeansForType(Class<?> type) throws Exception {
        List<T> beans = new ArrayList<T>();
        for (String beanDefinitionName : beanDefinitionNames) {
            if (type.isAssignableFrom(beanDefinitionMap.get(beanDefinitionName).getBeanClass()) ) {
                beans.add((T) getBean(beanDefinitionName));
            }
        }
        return beans;
    }
    
    /**
     * 将beanPrPostProcessor添加到beanPostProcessors
     * @param beanPrPostProcessor
     */
    private void addBeanPostProcessor(BeanPostProcessor beanPrPostProcessor) {
        beanPostProcessors.add(beanPrPostProcessor);
    }

    

}
