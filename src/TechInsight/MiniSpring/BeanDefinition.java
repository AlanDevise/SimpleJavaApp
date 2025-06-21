package TechInsight.MiniSpring;

import TechInsight.MiniSpring.sub.Autowired;
import TechInsight.MiniSpring.sub.PostConstruct;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;

/**
 * @Filename: BeanDefinition.java
 * @Package: TechInsight.MiniSpring
 * @Version: V1.0.0
 * @Description: 1.
 * @Author: Alan Zhang [initiator@alandevise.com]
 * @Date: 2025年06月21日 17:56
 */

public class BeanDefinition {

    /**
     * Bean的名称
     */
    private final String name;

    /**
     * Bean的构造函数
     */
    private final Constructor<?> constructor;

    /**
     * 构造函数之后执行的方法
     */
    private final Method postConstructMethod;

    /**
     * 需要自动注入的属性
     */
    private final List<Field> autowiredFields;

    /**
     * Bean的类型
     */
    private final Class<?> beanType;

    /**
     * 类似一个Bean工厂的设计图
     *
     * @Author: Alan [initator@alandevise.com]
     * @Date: 2025/6/21 17:56
     * @param: null
     * @return: null
     **/
    public BeanDefinition(Class<?> type) {
        this.beanType = type;
        Component component = type.getDeclaredAnnotation(Component.class);
        this.name = component.name().isEmpty() ? type.getSimpleName() : component.name();
        try {
            this.constructor = type.getConstructor();
            this.postConstructMethod =
                    Arrays.stream(type.getDeclaredMethods())
                            .filter(m -> m.isAnnotationPresent(PostConstruct.class))
                            .findFirst()
                            .orElse(null);
            this.autowiredFields =
                    Arrays.stream(type.getDeclaredFields())
                            .filter(x -> x.isAnnotationPresent(Autowired.class))
                            .toList();
        } catch (NoSuchMethodException e) {
            throw new RuntimeException(e);
        }
    }

    public String getName() {
        return name;
    }

    /**
     * 拿到指定Bean的构造函数
     *
     * @Author: Alan [initator@alandevise.com]
     * @Date: 2025/6/21 17:58
     * @param: null
     * @return: null
     **/
    public Constructor<?> getConstructor() {
        return constructor;
    }

    public Method getPostConstructMethod() {
        return postConstructMethod;
    }

    public List<Field> getAutowiredFields() {
        return autowiredFields;
    }

    public Class<?> getBeanType() {
        return beanType;
    }
}
