package simplespring.aop;

/**
 * 
 * ��������������ĳ�����
 *
 * @date  2019��7��27��
 * @author ������
 *
 */
public abstract class AbstractAopProxy implements AopProxy {

    protected AdvisedSupport advised;
    
    public AbstractAopProxy(AdvisedSupport advised) {
        this.advised = advised;
    }

}
