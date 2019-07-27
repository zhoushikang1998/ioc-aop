package simplespring.ioc;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * �൱�ڴ洢PropertyValue�����List
 *
 * @date  2019��7��27��
 * @author ������
 *
 */
public class PropertyValues {
    private final List<PropertyValue> propertyValueList = new ArrayList<PropertyValue>();
    
    public void addPropertyValue(PropertyValue pv) {
        // ��������ԶԲ���ֵ pv ��һЩ�������ֱ��ʹ�� List���Ͳ�����
        this.propertyValueList.add(pv);
    }
    
    public List<PropertyValue> getPropertyValues() {
        return this.propertyValueList;
    }
}
