package TechInsight.Collection;

/**
 * @Filename: List.java
 * @Package: TechInsight.Collection
 * @Version: V1.0.0
 * @Description: 1.
 * @Author: Alan Zhang [initiator@alandevise.com]
 * @Date: 2025年07月06日 15:23
 */

public interface List<E> extends Iterable<E> {

    void add(E element);

    void add(int index, E element);

    E get(int index);

    E set(int index, E element);

    int size();

    E remove(int index);

    boolean remove(E element);

}
