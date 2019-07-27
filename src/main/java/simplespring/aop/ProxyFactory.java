package simplespring.aop;

/**
 * AdvisedSupport的子类，获取代理对象类的工厂
 * <p>使用该类来实际生产代理对象生成器
 * @author 周世康
 *
 */
public class ProxyFactory extends AdvisedSupport implements AopProxy {

    public Object getProxy() {
        return createAopProxy().getProxy();
    }

    private AopProxy createAopProxy() {
        return new JdkDynamicAopProxy(this);
    }
}
