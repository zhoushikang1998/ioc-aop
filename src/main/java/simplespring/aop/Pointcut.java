package simplespring.aop;

/**
 * 
 * �е�ӿ�
 * <p>��ȡ����ƥ������Ŀ���������
 * @date  2019��7��27��
 * @author ������
 *
 */
public interface Pointcut {
    ClassFilter getClassFilter();
    MethodMatcher getMethodMatcher();
}
