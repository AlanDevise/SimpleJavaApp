package TechInsight.Collection;

/**
 * <h1>自定义的HashMap实现</h1>
 * 注意这里只实现了扩容，没有实现缩容
 *
 * @Filename: MyHashMap.java
 * @Package: TechInsight.Collection
 * @Version: V1.0.0
 * @Description: 1.
 * @Author: Alan Zhang [initiator@alandevise.com]
 * @Date: 2025年06月02日 13:56
 */

public class MyHashMap<K, V> {

    /**
     * 初始Node数组容量设置为16个节点
     **/
    private Pair<K, V>[] table = new Pair[16];

    /**
     * 记录当前HashMap中存储的键值对数量
     **/
    private int size = 0;

    /**
     * 更新方法
     *
     * @Author: Alan [initator@alandevise.com]
     * @Date: 2025/6/2 13:57
     * @param: key 键
     * @param: value 值
     * @return: 成功或失败
     **/
    public V put(K key, V value) {
        // 根据指定的key找到对应的下标
        int keyIndex = indexOf(key);
        // 根据下标找到对应的头节点
        Pair<K, V> head = table[keyIndex];
        // 如果头节点为空，直接将新的键值对作为头节点插入到数组中，并且size加1
        if (head == null) {
            table[keyIndex] = new Pair<>(key, value);
            size++;
            // 如果插入后size达到了阈值，需要进行扩容
            resizeIfNecessary();
            return null;
        }
        // 如果头节点不为空，遍历链表，直到找到对应的key，
        while (true) {
            // 如果找到了对应的key，更新value值，并且返回旧的value值
            if (head.key.equals(key)) {
                V oldValue = head.value;
                head.value = value;
                return oldValue;
            }
            // 如果没有找到对应的key，将新的键值对插入到链表的末尾，并且size加1
            if (head.next == null) {
                head.next = new Pair<>(key, value);
                size++;
                resizeIfNecessary();
                return null;
            }
            head = head.next;
        }
    }

    /**
     * 根据键获取值
     *
     * @Author: Alan [initator@alandevise.com]
     * @Date: 2025/6/2 13:58
     * @param: key 键
     * @return: value值
     **/
    public V get(K key) {
        // 根据指定的key找到链表对应的头节点下标
        int keyIndex = indexOf(key);
        Pair<K, V> head = table[keyIndex];
        // 遍历这个链表，直到找到对应的key
        while (head != null) {
            if (head.key.equals(key)) {
                return head.value;
            }
            head = head.next;
        }
        // 遍历完了也没有找到对应的key，返回null
        return null;
    }

    /**
     * 移除指定键值对
     *
     * @Author: Alan [initator@alandevise.com]
     * @Date: 2025/6/2 13:59
     * @param: key 键
     * @return: 移除成功或失败
     **/
    public V remove(K key) {
        // 根据指定的key找到对应的下标
        int keyIndex = indexOf(key);
        // 根据下标找到对应的头节点
        Pair<K, V> head = table[keyIndex];
        if (head == null) {
            // 如果头节点为空，说面链表为空，直接返回null，表示没有找到对应的键值对
            return null;
        }
        // 如果头节点已经是要删除的节点，直接将头节点的下一个节点作为新的头节点
        if (head.key.equals(key)) {
            table[keyIndex] = head.next;
            // MyHashMap的size减1，表示键值对数量减少了1个
            size--;
            // 返回被删除的节点的value值
            return head.value;
        }
        // 头节点不是要被删除的节点，表示需要遍历整个链表，找到要被删除的节点
        Pair<K, V> pre = head;
        Pair<K, V> current = head.next;
        // 当current不为null便一直循环遍历
        while (current != null) {
            if (current.key.equals(key)) {
                // 将前一个节点的next指针指向当前节点的next指针，跳过当前节点，相当于当前节点的前后连接被断开，实现删除效果
                pre.next = current.next;
                // MyHashMap的大小减1
                size--;
                // 返回被删除的节点的value值
                return current.value;
            }
            // 当前节点不是要被删除的节点，跳到下一个节点上
            pre = pre.next;
            current = current.next;
        }
        // 遍历完整个链表都没有找到要被删除的节点，返回null表示没有找到对应的键值对
        return null;
    }


    /**
     * 如果有必要进行扩容，按照当前Node数组的2倍进行扩容
     *
     * @Author: Alan [initator@alandevise.com]
     * @Date: 2025/6/2 15:12
     **/
    private void resizeIfNecessary() {
        /*
        为什么这里用0.75？

        因为0.75是一个经验值，当HashMap中的元素数量达到Node数组长度的0.75倍时，
        就认为HashMap已经达到了负载因子的上限，需要进行扩容。

        这个经验值是如何得出的？

        这个经验值是由时间和空间复杂度之间的平衡得出的，
        当负载因子达到0.75时，HashMap的查找效率和插入效率都比较高。
        */
        if (this.size < table.length * 0.75) {
            // 未达到阈值时直接返回
            return;
        }
        // 创建一个新的Node数组，长度时旧数组的2倍
        Pair<K, V>[] newTable = new Pair[this.table.length * 2];
        // 遍历旧的Node数组，将每个链表中的元素重新计算索引并放入新的Node数组中
        for (Pair<K, V> head : this.table) {
            // 如果头节点为空，跳过当前链表，继续遍历下一个链表
            if (head == null) {
                continue;
            }
            // 如果头节点不为空，遍历当前链表，将每个节点重新计算索引并放入新的Node数组中
            Pair<K, V> current = head;
            while (current != null) {
            /*
            为什么要使用头插法？

            因为头插法相比于尾插法，不需要遍历链表，直接将新节点插入到链表的头部。
            */
                int newIndex = current.key.hashCode() & (newTable.length - 1);
                if (newTable[newIndex] == null) {
                    newTable[newIndex] = current;
                    Pair<K, V> next = current.next;
                    current.next = null;
                    current = next;
                } else {
                    Pair<K, V> next = current.next;
                    current.next = newTable[newIndex];
                    newTable[newIndex] = current;
                    current = next;
                }
            }
        }
        this.table = newTable;
        System.out.println("扩容了，扩容到" + this.table.length);
    }

    /**
     * 返回当前HashMap中存储的键值对数量
     *
     * @Author: Alan [initator@alandevise.com]
     * @Date: 2025/6/2 15:06
     * @return: 当前HashMap中存储的键值对数量
     **/
    public int size() {
        return size;
    }

    /**
     * 计算指定键的哈希值，并返回该键在Node数组中的索引
     *
     * @Author: Alan [initator@alandevise.com]
     * @Date: 2025/6/2 15:09
     * @param: key 键
     * @return: 该键在Node数组中的索引
     **/
    private int indexOf(Object key) {
        /*
        为什么这里要使用 & 运算？

        因为table.length是2的幂次方，所以table.length - 1的二进制表示中，所有位都是1，
        例如table.length=16，那么table.length - 1=15，二进制表示为00001111，
        当我们对一个数进行&运算时，只有对应的二进制位都为1时，结果才为1，
        例如10101010 & 00001111 = 00001010，这样可以保证计算出的索引在0到table.length-1之间，不会超出数组的范围。
        */
        return key.hashCode() & (table.length - 1);
    }

    /**
     * 一个键值对的对象，包含四个个参数：键、值、前一个节点、后一个节点
     *
     * @Author: Alan [initator@alandevise.com]
     * @Date: 2025/6/2 15:07
     **/
    static class Pair<K, V> {
        K key;
        V value;
        Pair<K, V> pre;
        Pair<K, V> next;

        /**
         * 键值对的构造方法
         *
         * @Author: Alan [initator@alandevise.com]
         * @Date: 2025/6/2 15:08
         * @param: key 键
         * @param: value 值
         * @return: 一个键值对对象
         **/
        public Pair(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }

}
