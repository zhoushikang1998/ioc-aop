package simplespring.aop;

/**
 * 
 * 目标bean的相关信息类
 *
 * @date  2019年7月27日
 * @author 周世康
 *
 */
public class TargetSource {
    
    private Class<?> targetClass;
    
    private Class<?>[] interfaces;
    
    private Object target;
    
    public TargetSource(Object target, Class<?> targetClass, Class<?>... interfaces) {
        this.target = target;
        this.targetClass = targetClass;
        this.interfaces = interfaces;
    }

    public Class<?> getTargetClass() {
        return targetClass;
    }

    public Class<?>[] getInterfaces() {
        return interfaces;
    }

    public Object getTarget() {
        return target;
    }
    
}
