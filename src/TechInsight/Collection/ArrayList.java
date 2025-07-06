package TechInsight.Collection;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

/**
 * 手写一个ArrayList
 *
 * @Filename: ArrayList.java
 * @Package: TechInsight.Collection
 * @Version: V1.0.0
 * @Description: 1.
 * @Author: Alan Zhang [initiator@alandevise.com]
 * @Date: 2025年07月06日 15:27
 */

public class ArrayList<E> implements List<E> {

    /**
     * 底层数组
     */
    private Object[] table = new Object[10];

    /**
     * 数组长度
     */
    private int size = 0;

    @Override
    public void add(E element) {
        // 如果数组长度等于底层数组长度，则扩容
        if (size == table.length) {
            resize();
        }
        // 将元素添加到数组中
        table[size] = element;
        // 数组长度加1
        size++;
    }

    /**
     * 扩容
     */
    private void resize() {
        // 新建一个数组，长度为原来的2倍
        Object[] newTable = new Object[table.length * 2];
        // 将原来的数组复制到新数组中，并将新数组赋值给原来的数组。arraycopy方法的作用是将一个数组的内容复制到另一个数组中。
        System.arraycopy(table, 0, newTable, 0, table.length);
        this.table = newTable;
    }

    @Override
    public void add(int index, E element) {
        // 如果下标小于0或者大于数组长度，则抛出异常
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException();
        }
        // 如果数组长度等于底层数组长度，则扩容
        if (size == table.length) {
            resize();
        }
        // 将元素添加到数组中
        System.arraycopy(table, index, table, index + 1, size - index);
        table[index] = element;
        size++;
    }

    @Override
    public E get(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }
        return (E) table[index];
    }

    @Override
    public E set(int index, E element) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }
        E oldElement = (E) table[index];
        table[index] = element;
        return oldElement;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public E remove(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }
        E removeElement = (E) table[index];
        // 将元素从数组中删除
        System.arraycopy(table, index + 1, table, index, size - index - 1);
        size--;
        // 将数组最后一个元素赋值为null
        table[size] = null;
        return removeElement;
    }

    @Override
    public boolean remove(E element) {
        for (int i = 0; i < size; i++) {
            if (Objects.equals(element, table[i])) {
                remove(i);
                return true;
            }
        }
        return false;
    }

    @Override
    public Iterator<E> iterator() {
        return new ArrayIterator();
    }


    /**
     * 数组迭代器
     *
     * @Author: Alan [initator@alandevise.com]
     * @Date: 2025/7/6 15:56
     * @return: 数组迭代器
     **/
    class ArrayIterator implements Iterator<E> {

        /**
         * 游标
         */
        int cursor = 0;

        /**
         * 判断是否有下一个元素
         *
         * @Author: Alan [initator@alandevise.com]
         * @Date: 2025/7/6 15:58
         * @return: true表示有下一个元素，false表示没有下一个元素
         **/
        @Override
        public boolean hasNext() {
            // 只要游标不等于数组长度，就有下一个元素
            return cursor != size;
        }

        /**
         * 获取下一个元素
         *
         * @Author: Alan [initator@alandevise.com]
         * @Date: 2025/7/6 15:59
         * @return: E
         **/
        @Override
        public E next() {
            if (cursor >= size) {
                throw new NoSuchElementException();
            }
            E element = (E) table[cursor];
            cursor++;
            return element;
        }
    }
}
