package TechInsight.MiniSpring;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @Filename: Component.java
 * @Package: TechInsight.MiniSpring
 * @Version: V1.0.0
 * @Description: 1.
 * @Author: Alan Zhang [initiator@alandevise.com]
 * @Date: 2025年06月21日 17:46
 */

@Target(ElementType.TYPE)   // 令这个注解能加在类上
@Retention(RetentionPolicy.RUNTIME) // 令运行时能够获取到类型的属性信息
public @interface Component {

    /**
     * 没写名字，默认用类名，写了名字默认用写的名字
     *
     * @return
     */
    String name() default "";

}
