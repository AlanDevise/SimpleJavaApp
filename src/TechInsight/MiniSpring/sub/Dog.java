package TechInsight.MiniSpring.sub;

import TechInsight.MiniSpring.Component;

/**
 * @Filename: Dog.java
 * @Package: TechInsight.MiniSpring.sub
 * @Version: V1.0.0
 * @Description: 1.
 * @Author: Alan Zhang [initiator@alandevise.com]
 * @Date: 2025年06月21日 18:38
 */

@Component(name = "mydog")
public class Dog {

    @Autowired
    private Cat cat;

    @PostConstruct
    public void init() {
        System.out.println("Dog创建了，里面有一只猫" + cat);
    }

}
