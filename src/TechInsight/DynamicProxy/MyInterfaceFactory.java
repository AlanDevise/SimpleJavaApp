package TechInsight.DynamicProxy;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

/**
 * @Filename: MyInterfaceFactory.java
 * @Package: TechInsight.DynamicProxy
 * @Version: V1.0.0
 * @Description: 1.
 * @Author: Alan Zhang [initiator@alandevise.com]
 * @Date: 2025年05月24日 15:55
 */

public class MyInterfaceFactory {

    public static File createJavaFile() throws IOException {
        String context = "package TechInsight.DynamicProxy;\n" +
                "\n" +
                "public class NameImpl implements MyInterface {\n" +
                "    @Override\n" +
                "    public void func1() {\n" +
                "        System.out.println(\"func1\");\n" +
                "    }\n" +
                "\n" +
                "    @Override\n" +
                "    public void func2() {\n" +
                "        System.out.println(\"func2\");\n" +
                "    }\n" +
                "\n" +
                "    @Override\n" +
                "    public void func3() {\n" +
                "        System.out.println(\"func3\");\n" +
                "    }\n" +
                "}\n";
        File javaFile = new File("NameImpl.java");
        Files.writeString(javaFile.toPath(), context);
        return javaFile;
    }

    public static void main(String[] args) throws IOException {
        createJavaFile();
    }
}
