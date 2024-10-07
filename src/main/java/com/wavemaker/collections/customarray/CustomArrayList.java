package com.wavemaker.collections.customarray;

import com.wavemaker.collections.exception.IndexOutOfBoundException;
import com.wavemaker.collections.exception.InvalidInputException;

import java.util.Arrays;
import java.util.Iterator;

@SuppressWarnings("unchecked")
public class CustomArrayList<E> implements CustomList<E> {

    private static final int DEFAULT_CAPACITY = 10;
    private static final Object[] EMPTY_ELEMENTDATA = {};
    private static final Object[] DEFAULTCAPACITY_EMPTY_ELEMENTDATA = {};
    public Object[] elementData;
    private int size;

    public CustomArrayList() {
        elementData = new Object[DEFAULT_CAPACITY];   //default capacity for no input size;
    }

    public CustomArrayList(int initialCapacity) {
        if (initialCapacity >= 0) {
            elementData = new Object[initialCapacity];   //having initial capacity;
        } else if (initialCapacity == 0) {
            elementData = EMPTY_ELEMENTDATA;
        } else {
            throw new InvalidInputException("Illegal Capacity : " + initialCapacity);
        }
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public boolean contains(Object o) {
        return indexOf(o) >= 0;
    }

    @Override
    public Iterator<E> iterator() {
        return null;
    }

    @Override
    public E get(int index) {
        rangeCheckForIndex(index, size);
        return (E) elementData[index];
    }

    @Override
    public int indexOf(Object o) {
        if (o == null) {
            for (int i = 0; i < size; i++) {
                if (elementData[i] == null) return i;
            }
        } else {
            for (int i = 0; i < size; i++) {
                if (o.equals(elementData[i])) return i;
            }
        }
        return -1;
    }

    @Override
    public int lastIndexOf(Object o) {
        return 0;
    }

    @Override
    public E set(int index, E element) {
        return null;
    }

    @Override
    public void add(E element) {
        add(element, elementData, size);
    }

    private void add(E element, Object[] elementData, int s) {
        if (size == elementData.length) {
            elementData = grow();
        }
        elementData[s] = element;
        size = s + 1;
    }

    private Object[] grow() {
        int oldCapacity = elementData.length;
        if (oldCapacity > 0) {
            int newCapacity = elementData.length + (elementData.length >> 1); //element data / 2
            return elementData = Arrays.copyOf(elementData, newCapacity);
        } else {
            return elementData = new Object[Math.max(DEFAULT_CAPACITY, size + 1)];
        }
    }

    @Override
    public E remove(int index) {
        rangeCheckForIndex(index, size);
        E oldValue = (E) elementData[index];
        removeElementByIndex(index);
        return oldValue;
    }

    private void removeElementByIndex(int index) {
        System.arraycopy(elementData, index + 1, elementData, index, size - index - 1);
        elementData[size - 1] = null;
        size--;
    }

    private void rangeCheckForIndex(int index, int size) {
        if (index > size || index < 0) throw new IndexOutOfBoundException("Index : " + index + " size : " + size);
    }

    @Override
    public boolean remove(Object o) {
        return false;
    }
}
