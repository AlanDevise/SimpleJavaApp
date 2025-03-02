package TestSwitchCase;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/**
 * @Filename: test.java
 * @Package: TestSwitchCase
 * @Version: V1.0.0
 * @Description: 1.
 * @Author: Alan Zhang [initiator@alandevise.com]
 * @Date: 2023年07月20日 22:49
 */

public class test {
    public static void main(String[] args) {
        // 设置一个静态的map
        HashMap<String, Integer> sheetNameWithNo = new HashMap<>();
        sheetNameWithNo.put("sheet1", 1);
        sheetNameWithNo.put("sheet2", 2);
        sheetNameWithNo.put("sheet3", 3);
        sheetNameWithNo.put("sheet4", 4);
        sheetNameWithNo.put("sheet5", 5);

        HashMap<Integer, String> sheetNameWithNo2 = new HashMap<>();
        sheetNameWithNo2.put( 1,"sheet1");
        sheetNameWithNo2.put( 2,"sheet2");
        sheetNameWithNo2.put( 3,"sheet3");
        sheetNameWithNo2.put( 4,"sheet4");
        sheetNameWithNo2.put( 5,"sheet5");

        List<Integer> sheetList = Arrays.asList(5, 2);
        for (Integer sheetName : sheetList) {
            switch (sheetName) {
                case 1:
                    System.out.println("This is sheet1");
                    break;
                case 2:
                    System.out.println("This is sheet2");
                    break;
                case 3:
                    System.out.println("This is sheet3");
                    break;
                case 4:
                    System.out.println("This is sheet4");
                    break;
                case 5:
                    System.out.println("This is sheet5");
                    break;
            }
        }

        List<String> sheetList2 = Arrays.asList("sadf", "trher4t5");
        for (String sheetName : sheetList2) {
            switch (sheetName) {
                case "erthgertgh":
                    System.out.println("This is sheet1");
                    break;
                case "trher4t5":
                    System.out.println("This is sheet2");
                    break;
                case "sre5hgyes":
                    System.out.println("This is sheet3");
                    break;
                case "r5thgye4":
                    System.out.println("This is sheet4");
                    break;
                case "sadf":
                    System.out.println("This is sheet5");
                    break;
            }
        }
    }
}
