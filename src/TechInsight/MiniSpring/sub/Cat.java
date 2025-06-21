package TechInsight.MiniSpring.sub;

import TechInsight.MiniSpring.Component;

/**
 * @Filename: Cat.java
 * @Package: TechInsight.MiniSpring.sub
 * @Version: V1.0.0
 * @Description: 1.
 * @Author: Alan Zhang [initiator@alandevise.com]
 * @Date: 2025年06月21日 18:17
 */

@Component
public class Cat {

    /**
     * 自动注入一个dog对象，以强行造成循环依赖
     */
    @Autowired
    private Dog dog;

    @PostConstruct
    public void init() {
        System.out.println("Cat创建了，cat里面有一个属性" + dog);
    }

}
