package simplespring.ioc;

/**
 * 
 * BeanReference ���󱣴���� bean ������ ref ���Զ�Ӧ��ֵ
 *
 * @date  2019��7��27��
 * @author ������
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
