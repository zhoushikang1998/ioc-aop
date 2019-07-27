package simplespring.ioc;

/**
 * 
 * BeanReference 对象保存的是 bean 配置中 ref 属性对应的值
 *
 * @date  2019年7月27日
 * @author 周世康
 *
 */
public class BeanReference {
    private String name;
    private Object bean;
    
    public BeanReference(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Object getBean() {
        return bean;
    }

    public void setBean(Object bean) {
        this.bean = bean;
    }
    
    
}
