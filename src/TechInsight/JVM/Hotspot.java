package TechInsight.JVM;

import java.io.File;
import java.util.Arrays;
import java.util.List;

/**
 * 手写一个Hotspot
 *
 * @Filename: Hotspot.java
 * @Package: TechInsight.JVM
 * @Version: V1.0.0
 * @Description: 1.
 * @Author: Alan Zhang [initiator@alandevise.com]
 * @Date: 2025年07月06日 17:27
 */

public class Hotspot {

    /**
     * 主类
     */
    private String mainClass;

    /**
     * 类路径
     */
    private List<String> classPath;

    public Hotspot(String mainClass,
                   String classPathString) {
        this.mainClass = mainClass;
        this.classPath = Arrays.asList(classPathString.split(File.pathSeparator));
    }

    void start(){
        // 启动
    }

}
