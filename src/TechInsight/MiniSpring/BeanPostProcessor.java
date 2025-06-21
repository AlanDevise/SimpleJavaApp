package TechInsight.MiniSpring;

/**
 * @Filename: BeanPostProcessor.java
 * @Package: TechInsight.MiniSpring
 * @Version: V1.0.0
 * @Description: 1.
 * @Author: Alan Zhang [initiator@alandevise.com]
 * @Date: 2025年06月21日 21:12
 */

public interface BeanPostProcessor {

    /**
     * 初始化前置动作，默认不做任何操作
     *
     * @Author: Alan [initator@alandevise.com]
     * @Date: 2025/6/21 21:26
     **/
    default Object beforeInitializeBean(Object bean,
                                        String beanName) {
        return bean;
    }

    /**
     * 初始化后置动作，默认不做任何操作
     *
     * @Author: Alan [initator@alandevise.com]
     * @Date: 2025/6/21 21:26
     **/
    default Object afterInitializeBean(Object bean,
                                       String beanName) {
        return bean;
    }

}
