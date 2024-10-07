package com.wavemaker.collections.customarray;

import java.util.Iterator;

public interface CustomList<E> {

    int size();

    boolean isEmpty();

    boolean contains(Object o);

    //Returns an iterator over the elements in this list in proper sequence
    Iterator<E> iterator();

    E get(int index);

    int indexOf(Object o);

    int lastIndexOf(Object o);

    //replaces the element at the specified position in this list with the
    E set(int index, E element);

    //adds the specified element to the end of this list
    void add(E element);

    //removes the element at the specified position in this list
    E remove(int index);

    //removes the first occurrence of the specified element from this list
    boolean remove(Object o);
}
