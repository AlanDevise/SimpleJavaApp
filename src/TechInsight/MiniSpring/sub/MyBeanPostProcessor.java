package TechInsight.MiniSpring.sub;

import TechInsight.MiniSpring.BeanPostProcessor;
import TechInsight.MiniSpring.Component;

/**
 * @Filename: MyBeanPostProcessor.java
 * @Package: TechInsight.MiniSpring.sub
 * @Version: V1.0.0
 * @Description: 1.
 * @Author: Alan Zhang [initiator@alandevise.com]
 * @Date: 2025年06月21日 21:24
 */

@Component
public class MyBeanPostProcessor implements BeanPostProcessor {

    @Override
    public Object afterInitializeBean(Object bean, String beanName) {
        System.out.println(beanName + "初始化完成");
        return bean;
    }

}
