package TechInsight.MiniSpring;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URL;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 手写一个Spring容器
 *
 * @Filename: ApplicationContext.java
 * @Package: TechInsight.MiniSpring
 * @Version: V1.0.0
 * @Description: 1.
 * @Author: Alan Zhang [initiator@alandevise.com]
 * @Date: 2025年06月21日 17:41
 */

public class ApplicationContext {

    /**
     * ApplicationContext构造函数
     *
     * @Author: Alan [initator@alandevise.com]
     * @Date: 2025/6/21 18:58
     * @param: packageName 包路径名
     **/
    public ApplicationContext(String packageName) throws IOException {
        initContext(packageName);
    }

    /**
     * Bean定义的集合，用于存放所有的Bean定义<br/>
     */
    private final Map<String, BeanDefinition> beanDefinitionMap = new HashMap<>();

    /**
     * IOC容器本体
     **/
    private final Map<String, Object> ioc = new HashMap<>();

    /**
     * 容器中还没有初始化的完的对象
     */
    private final Map<String, Object> loadingIoc = new HashMap<>();

    /**
     * BeanPostProcessor的集合
     */
    private final List<BeanPostProcessor> postProcessors = new ArrayList<>();

    /**
     * 让所有应该初始化的对象全都初始化完成，造对象<br/>
     * 造哪些，怎么造？<br/>
     * 利用自定义的注解如@Component 标示出所有应该初始化出来的对象
     *
     * @Author: Alan [initator@alandevise.com]
     * @Date: 2025/6/21 17:44
     * @param: packageName 要扫描的包名，如：com.alandevise.thread.threadpool
     * @return: null
     **/
    public void initContext(String packageName) throws IOException {
        scanPackage(packageName)
                .stream()
                .filter(this::scanCreate)
                .forEach(this::wrapper);
        initBeanPostProcessor();
        // 现将所有的beanDefinition都创建出来，然后再去创建bean
        beanDefinitionMap.values().forEach(this::createBean);
    }

    /**
     * 初始化BeanPostProcessor
     *
     * @Author: Alan [initator@alandevise.com]
     * @Date: 2025/6/21 21:20
     * @param: null
     * @return: null
     **/
    private void initBeanPostProcessor() {
        beanDefinitionMap.values().stream()
                .filter(bd -> BeanPostProcessor.class.isAssignableFrom(bd.getBeanType()))
                .map(this::createBean)
                .map((bean) -> (BeanPostProcessor) bean)
                .forEach(postProcessors::add);
    }

    /**
     * 创建Bean的方法<br/>
     * 用两层缓存来解决循环依赖问题<br/>
     *
     * @Author: Alan [initator@alandevise.com]
     * @Date: 2025/6/21 18:25
     * @param: beanDefinition bean定义
     **/
    protected Object createBean(BeanDefinition beanDefinition) {
        String name = beanDefinition.getName();
        // 先从IOC容器中获取，如果有就直接返回
        if (ioc.containsKey(name)) {
            return ioc.get(name);
        }
        // 再从正在半成品IoC容器中获取，如果有就直接返回
        if (loadingIoc.containsKey(name)) {
            return ioc.get(name);
        }
        // 如果都没有，就创建一个bean
        return doCreateBean(beanDefinition);
    }

    /**
     * 用途：创建Bean<br/>
     * 从bean定义中获取一个构造函数<br/>
     *
     * @Author: Alan [initator@alandevise.com]
     * @Date: 2025/6/21 18:59
     * @param: beanDefinition bean定义
     * @return: 创建完成的Bean对象
     **/
    protected Object doCreateBean(BeanDefinition beanDefinition) {
        Constructor<?> constructor = beanDefinition.getConstructor();
        Object bean;
        try {
            bean = constructor.newInstance();
            loadingIoc.put(beanDefinition.getName(), bean);
            // 自动注入依赖
            autowiredBean(bean, beanDefinition);
            // 初始化bean
            bean = initializeBean(bean, beanDefinition);
            // 从正在半成品IoC容器中移除
            loadingIoc.remove(beanDefinition.getName());
            // 将最后真正初始化完成的bean放入IoC容器中
            ioc.put(beanDefinition.getName(), bean);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return bean;
    }

    /**
     * 初始化Bean
     *
     * @Author: Alan [initator@alandevise.com]
     * @Date: 2025/6/21 21:09
     * @param: bean bean对象
     * @param: beanDefinition bean定义
     **/
    private Object initializeBean(Object bean,
                                  BeanDefinition beanDefinition)
            throws InvocationTargetException, IllegalAccessException {
        // 调用所有的BeanPostProcessor的beforeInitializeBean方法
        for (BeanPostProcessor postProcessor : postProcessors) {
            bean = postProcessor.beforeInitializeBean(bean, beanDefinition.getName());
        }
        Method postConstructMethod = beanDefinition.getPostConstructMethod();
        if (postConstructMethod != null) {
            postConstructMethod.invoke(bean);
        }
        // 调用所有的BeanPostProcessor的afterInitializeBean方法
        for (BeanPostProcessor postProcessor : postProcessors) {
            bean = postProcessor.afterInitializeBean(bean, beanDefinition.getName());
        }
        return bean;
    }

    /**
     * 自动注入属性依赖
     *
     * @Author: Alan [initator@alandevise.com]
     * @Date: 2025/6/21 21:11
     * @param: bean bean对象
     * @param: beanDefinition bean定义
     **/
    private void autowiredBean(Object bean,
                               BeanDefinition beanDefinition) throws IllegalAccessException {
        for (Field autowiredField : beanDefinition.getAutowiredFields()) {
            // 所有这些属性都要注入，先将其设置为可访问
            autowiredField.setAccessible(true);
            Object autowireBean = null;
            autowiredField.set(bean, getBean(autowiredField.getType()));
        }
    }

    /**
     * 为什么这里要用protected 修饰？<br/>
     * 因为如果有其他的类想要继承ApplicationContext，那么就可以重写这个方法，来实现自己的扫描规则<br/>
     *
     * @Author: Alan [initator@alandevise.com]
     * @Date: 2025/6/21 18:00
     * @param: null
     * @return: null
     **/
    protected boolean scanCreate(Class<?> type) {
        return type.isAnnotationPresent(Component.class);
    }

    /**
     * 用途：把一个类的定义信息封装成一个BeanDefinition<br/>
     * 封装的过程中，要判断是否有重复的Bean定义<br/>
     *
     * @Author: Alan [initator@alandevise.com]
     * @Date: 2025/6/21 19:00
     * @param: null
     * @return: null
     **/
    protected BeanDefinition wrapper(Class<?> type) {
        BeanDefinition beanDefinition = new BeanDefinition(type);
        if (beanDefinitionMap.containsKey(beanDefinition.getName())) {
            throw new RuntimeException("bean名字重复");
        }
        beanDefinitionMap.put(beanDefinition.getName(), beanDefinition);
        return beanDefinition;
    }

    /**
     * 返回指定路径下的所有包的类定义信息
     *
     * @Author: Alan [initator@alandevise.com]
     * @Date: 2025/6/21 17:53
     * @param: packageName 要扫描的包名，如：com.alandevise.thread.threadpool
     * @return:
     **/
    private List<Class<?>> scanPackage(String packageName) throws IOException {
        List<Class<?>> classList = new ArrayList<>();
        // 扫描包下的所有类
        // 遍历所有类，判断是否有@Component注解
        URL resource = this.getClass().getClassLoader().getResource(packageName.replace(".", File.separator));
        Path path = Path.of(resource.getFile());
        Files.walkFileTree(path, new SimpleFileVisitor<Path>() {
            @Override
            public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) {
                Path absolutePath = file.toAbsolutePath();
                if (absolutePath.toString().endsWith(".class")) {
                    String replaceStr = absolutePath.toString().replace(File.separator, ".");
                    int packageIndex = replaceStr.indexOf(packageName);
                    String className = replaceStr.substring(packageIndex, replaceStr.length() - ".class".length());
                    try {
                        classList.add(Class.forName(className));
                    } catch (ClassNotFoundException e) {
                        throw new RuntimeException(e);
                    }
                }
                // 无论遇到什么问题都继续遍历
                return FileVisitResult.CONTINUE;
            }
        });
        return classList;
    }

    /**
     * 从容器中获取一个bean对象
     *
     * @Author: Alan [initator@alandevise.com]
     * @Date: 2025/6/21 17:42
     * @param: name bean的名称
     * @return: 对应bean的实例
     **/
    public Object getBean(String name) {
        if (name == null) {
            return null;
        }
        Object bean = this.ioc.get(name);
        if (bean != null) {
            return bean;
        }
        if (beanDefinitionMap.containsKey(name)) {
            return createBean(beanDefinitionMap.get(name));
        }
        return null;
    }

    public <T> T getBean(Class<T> beanType) {
        String beanName =
                this.beanDefinitionMap.values().stream()
                        .filter(bd -> beanType.isAssignableFrom(bd.getBeanType()))
                        .map(BeanDefinition::getName)
                        .findFirst()
                        .orElse(null);
        return (T) getBean(beanName);
    }

    public <T> List<T> getBeans(Class<T> beanType) {
        return this.beanDefinitionMap.values().stream()
                .filter(bd -> beanType.isAssignableFrom(bd.getBeanType()))
                .map(BeanDefinition::getName)
                .map(this::getBean)
                .map((bean) -> (T) bean)
                .toList();

    }
}
