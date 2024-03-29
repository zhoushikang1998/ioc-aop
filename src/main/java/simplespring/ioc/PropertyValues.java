package simplespring.ioc;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * 相当于存储PropertyValue对象的List
 *
 * @date  2019年7月27日
 * @author 周世康
 *
 */
public class PropertyValues {
    private final List<PropertyValue> propertyValueList = new ArrayList<PropertyValue>();
    
    public void addPropertyValue(PropertyValue pv) {
        // 在这里可以对参数值 pv 做一些处理，如果直接使用 List，就不行了
        this.propertyValueList.add(pv);
    }
    
    public List<PropertyValue> getPropertyValues() {
        return this.propertyValueList;
    }
}
