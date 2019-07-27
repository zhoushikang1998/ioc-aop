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
 * ʵ�ʵ�bean������
 * <P>ʵ�ʵ�BeanDefinition��������ע�� BeanPostProcessor ���ʵ���ൽ BeanPostProcessor ������
 * 
 * @date  2019��7��27��
 * @author ������
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
     * ��ȡ beanDefinition�е�bean����beanDefinition�е�beanΪnull���򴴽�����ʼ��bean
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
     * ����bean, ��Ϊbean�����������
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
     * ��BeanDefinition�е��������õ���ӦBeanDefinition��
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
     * ��ʼ��bean��Ϊ��bean ��Ӻ��ô������Ĵ�����
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
     * ʹ��XmlBeanDefinitionReader�����xml�����ļ�
     * <p>��BeanDefinition��ӵ�beanDefinitionMap��
     * <p>��bean��������ӵ�beanDefinitionNames��
     * <p>��beanDefinitionMap�м̳���BeanPostProcessor���bean��ӵ�beanPostProcessors��
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
     * ��BeanDefinition��ӵ�beanDefinitionMap��
     * <p>��bean��������ӵ�beanDefinitionNames��
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
     * ��beanDefinitionMap�м̳���BeanPostProcessor���bean��ӵ�beanPostProcessors��
     * @throws Exception
     */
    private void registryBeanPostProcessor() throws Exception {
        List beans = getBeansForType(BeanPostProcessor.class);
        for (Object bean : beans) {
            addBeanPostProcessor((BeanPostProcessor) bean);
        }
    }

    /**
     * ��beanDefinitionMap�м̳���BeanPostProcessor���bean��ӵ�List�з���
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
     * ��beanPrPostProcessor��ӵ�beanPostProcessors
     * @param beanPrPostProcessor
     */
    private void addBeanPostProcessor(BeanPostProcessor beanPrPostProcessor) {
        beanPostProcessors.add(beanPrPostProcessor);
    }

    

}
