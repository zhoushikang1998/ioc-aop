package simplespring.aop;

/**
 * AdvisedSupport�����࣬��ȡ���������Ĺ���
 * <p>ʹ�ø�����ʵ�������������������
 * @author ������
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
