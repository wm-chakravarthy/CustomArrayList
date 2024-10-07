package com.wavemaker.collections.customarray;

import com.wavemaker.collections.exception.InvalidInputException;

import java.util.Iterator;

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
        return 0;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public boolean contains(Object o) {
        return false;
    }

    @Override
    public Iterator<E> iterator() {
        return null;
    }

    @Override
    public E get(int index) {
        return null;
    }

    @Override
    public int indexOf(Object o) {
        return 0;
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

    }

    @Override
    public E remove(int index) {
        return null;
    }

    @Override
    public boolean remove(Object o) {
        return false;
    }
}
