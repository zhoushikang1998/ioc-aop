package simplespring.aop;

/**
 * 
 * 代理对象生成器的抽象类
 *
 * @date  2019年7月27日
 * @author 周世康
 *
 */
public abstract class AbstractAopProxy implements AopProxy {

    protected AdvisedSupport advised;
    
    public AbstractAopProxy(AdvisedSupport advised) {
        this.advised = advised;
    }

}
