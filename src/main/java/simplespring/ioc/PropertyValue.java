package simplespring.ioc;

/**
 * 
 * ��¼ bean �����еı�ǩ������ֵ
 *
 * @date  2019��7��27��
 * @author ������
 *
 */
public class PropertyValue {
    private final String name;
    private final Object value;
    
    public PropertyValue(String name, Object value) {
        this.name = name;
        this.value = value;
    }
    
    public String getName() {
        return name;
    }
    
    public Object getValue() {
        return value;
    }
}
