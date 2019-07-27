package simplespring.ioc;

/**
 * 
 * 记录 bean 配置中的标签的属性值
 *
 * @date  2019年7月27日
 * @author 周世康
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
