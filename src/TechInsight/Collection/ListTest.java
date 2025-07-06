package TechInsight.Collection;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * @Filename: ListTest.java
 * @Package: TechInsight.Collection
 * @Version: V1.0.0
 * @Description: 1.
 * @Author: Alan Zhang [initiator@alandevise.com]
 * @Date: 2025年07月06日 16:01
 */

public class ListTest {

    @Test
    public void operateTest(){
        // List<String> list =  new ArrayList<>();
        List<String> list =  new LinkedList<>();
        for (int i = 0; i < 30; i++) {
            list.add(String.valueOf(i));
        }
        assertEquals(30, list.size());
        list.remove(15);
        list.remove("18");
        assertEquals(28, list.size());
        assertEquals("16", list.get(15));
        assertEquals("24", list.get(22));

        list.forEach(System.out::println);
    }

}
