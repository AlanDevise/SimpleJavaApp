package TechInsight.Collection;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

/**
 * @Filename: MyHashMapTest.java
 * @Package: TechInsight.Collection
 * @Version: V1.0.0
 * @Description: 1.
 * @Author: Alan Zhang [initiator@alandevise.com]
 * @Date: 2025年06月02日 14:14
 */

public class MyHashMapTest {
    @Test
    public void testApi() {
        MyHashMap<String, String> myHashMap = new MyHashMap<>();
        int count = 10000000;
        for (int i = 0; i < count; i++) {
            myHashMap.put(String.valueOf(i), String.valueOf(i));
        }

        assertEquals(count, myHashMap.size());

        for (int i = 0; i < count; i++) {
            assertEquals(String.valueOf(i), myHashMap.get(String.valueOf(i)));
        }

        myHashMap.remove("8");
        assertNull(myHashMap.get("8"));

        assertEquals(count - 1, myHashMap.size());

    }
}
