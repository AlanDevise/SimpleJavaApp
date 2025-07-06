package TechInsight.Collection;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

/**
 * 手写的链表
 *
 * @Filename: LinkedList.java
 * @Package: TechInsight.Collection
 * @Version: V1.0.0
 * @Description: 1.
 * @Author: Alan Zhang [initiator@alandevise.com]
 * @Date: 2025年07月06日 16:30
 */

public class LinkedList<E> implements List<E> {

    /**
     * 有效长度
     */
    private int size;

    /**
     * 头节点
     */
    private Node<E> head;

    /**
     * 尾节点
     */
    private Node<E> tail;

    @Override
    public void add(E element) {
        Node<E> node = new Node<>(tail, element, null);
        if (tail != null) {
            tail.next = node;
        } else {
            head = node;
        }
        tail = node;
        size++;
    }

    @Override
    public void add(int index, E element) {

    }

    private Node<E> findNode(int index) {
        Node<E> result = null;
        if (index < size / 2) {
            // 从前往后遍历
            result = head;
            for (int i = 0; i < index; i++) {
                result = result.next;
            }
        } else {
            // 从后往前遍历
            result = tail;
            for (int i = size - 1; i > index; i--) {
                result = result.pre;
            }
        }
        return result;
    }

    @Override
    public E get(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }
        return findNode(index).value;
    }

    @Override
    public E set(int index, E element) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }
        Node<E> node = findNode(index);
        E oldValue = node.value;
        node.value = element;
        return oldValue;
    }

    /**
     * 获取有效长度
     *
     * @Author: Alan [initator@alandevise.com]
     * @Date: 2025/7/6 16:36
     * @return: 有效长度
     **/
    @Override
    public int size() {
        return this.size;
    }

    @Override
    public E remove(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }
        return removeNode(findNode(index));
    }

    private E removeNode(Node<E> node) {
        Node<E> pre = node.pre;
        Node<E> next = node.next;
        if (pre == null) {
            head = next;
        } else {
            pre.next = next;
        }
        if (next == null) {
            tail = pre;
        } else {
            next.pre = pre;
        }
        node.pre = null;
        node.next = null;
        size--;
        return node.value;
    }

    @Override
    public boolean remove(E element) {
        Node<E> node = head;
        while (node != null) {
            if (Objects.equals(element, node.value)) {
                removeNode(node);
                return true;
            }
            node = node.next;
        }
        return false;
    }

    @Override
    public Iterator<E> iterator() {
        return new LinkedListIterator();
    }

    class LinkedListIterator implements Iterator<E> {

        Node<E> node = head;

        @Override
        public boolean hasNext() {
            return node != null;
        }

        @Override
        public E next() {
            if (node == null) {
                throw new NoSuchElementException();
            }
            E result = node.value;
            node = node.next;
            return result;
        }
    }

    class Node<E> {
        /**
         * 前驱节点
         */
        Node<E> pre;
        /**
         * 后继节点
         */
        Node<E> next;
        /**
         * 节点元素
         */
        E value;

        /**
         * 节点的构造方法
         *
         * @Author: Alan [initator@alandevise.com]
         * @Date: 2025/7/6 16:33
         * @param: 元素值
         * @return: 一个构造出来的节点
         **/
        public Node(E value) {
            this.value = value;
        }

        /**
         * 节点的构造方法
         *
         * @Author: Alan [initator@alandevise.com]
         * @Date: 2025/7/6 16:33
         * @param: 前驱节点、后继节点、元素值
         * @return: 一个构造出来的节点
         **/
        public Node(Node<E> pre,
                    E value,
                    Node<E> next) {
            this.pre = pre;
            this.next = next;
            this.value = value;
        }
    }
}
