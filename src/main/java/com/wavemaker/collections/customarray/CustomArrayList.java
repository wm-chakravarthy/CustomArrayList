package com.wavemaker.collections.customarray;

import java.io.Serializable;
import java.util.*;

@SuppressWarnings("unchecked")
public class CustomArrayList<E> implements List<E>, Serializable {
    @java.io.Serial
    private static final long serialVersionUID = 8683452581122892452L;
    private static final int DEFAULT_CAPACITY = 10;
    private static final Object[] EMPTY_ELEMENTDATA = {};
    public Object[] elementData;
    protected transient int modCount = 0;
    private int size;

    public CustomArrayList() {
        elementData = new Object[DEFAULT_CAPACITY];   //default capacity for no input size;
    }

    public CustomArrayList(int initialCapacity) {
        if (initialCapacity > 0) {
            elementData = new Object[initialCapacity];   //having initial capacity;
        } else if (initialCapacity == 0) {
            elementData = EMPTY_ELEMENTDATA;
        } else {
            throw new IllegalArgumentException("Illegal Capacity: " + initialCapacity);
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
    public boolean add(E element) {
        modCount++;
        add(element, elementData, size);
        return true;
    }


    @Override
    public boolean remove(Object o) {
        for (int i = 0; i < size; i++) {
            if (Objects.equals(o, elementData[i])) {
                removeElementByIndex(i);
                return true;
            }
        }
        return false;
    }

    @Override
    public E get(int index) {
        rangeCheckForIndex(index, size);
        return (E) elementData[index];
    }


    @Override
    public E remove(int index) {
        rangeCheckForIndex(index, size);
        E oldValue = (E) elementData[index];
        removeElementByIndex(index);
        return oldValue;
    }

    @Override
    public int indexOf(Object o) {
        for (int i = 0; i < size; i++) {
            if (Objects.equals(o, elementData[i])) return i;
        }
        return -1;
    }

    @Override
    public int lastIndexOf(Object o) {
        for (int i = size - 1; i >= 0; i--) {
            if (Objects.equals(o, elementData[i])) return i;
        }
        return -1;
    }

    @Override
    public void add(int index, E element) {
        rangeCheckForAdd(index, size);
        if (size == elementData.length) {
            elementData = grow();
        }
        addElementToArray(index, element);
        elementData[index] = element;
        size++;
    }

    @Override
    public E set(int index, E element) {
        rangeCheckForIndex(index, size);
        E oldValue = (E) elementData[index];
        elementData[index] = element;
        return oldValue;
    }

    @Override
    public Iterator<E> iterator() {
        return new Itr();
    }

    @Override
    public boolean containsAll(Collection<?> collection) {
        Iterator iteratored = collection.iterator();

        while (iteratored.hasNext()) {
            Object element = iteratored.next();
            if (!contains(element)) {
                return false;
            }
        }
        return true;
    }


    @Override
    public boolean addAll(Collection<? extends E> collection) {
        Object[] collectionArray = collection.toArray();
        modCount++;
        int collectionArrayLength = collectionArray.length;
        if (collectionArrayLength == 0) {
            return false;
        }
        int newElementDataSize = size + collectionArrayLength;
        if (newElementDataSize > elementData.length) {
            elementData = grow(newElementDataSize);
        }
        customArrayCopy(collectionArray, 0, elementData, size, collectionArrayLength);
        size = newElementDataSize;
        return true;
    }


    @Override
    public boolean addAll(int index, Collection<? extends E> c) {
        rangeCheckForAdd(index, size);
        Object[] array = convertCollectionToArray(c);
        int arrayLength = array.length;
        if (arrayLength == 0) {
            return false;
        }

        modCount++;
        int newSize = size + arrayLength;
        if (newSize > elementData.length) {
            elementData = grow(newSize);
        }

        int elementsToShift = size - index;
        if (elementsToShift > 0) {
            customArrayCopy(elementData, index, elementData, index + arrayLength, elementsToShift);
        }

        customArrayCopy(array, 0, elementData, index, arrayLength);
        size = newSize;
        return true;
    }

    @Override
    public void clear() {
        modCount++;
        for (int i = 0; i < size; i++) {
            elementData[i] = null;
        }
        size = 0;
    }

    @Override
    public Object[] toArray() {
        Object[] array = new Object[size];
        for (int i = 0; i < size; i++) {
            array[i] = elementData[i];
        }
        return array;
    }


    @Override
    public boolean removeAll(Collection<?> c) {
        Object[] removableArray = c.toArray();
        for (Object o : removableArray) {
            remove(o);
        }
        return true;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        Object[] retainableArray = c.toArray();
        for (int i = 0; i < size; i++) {
            if (!contains(retainableArray[i])) {
                removeElementByIndex(i);
            }
        }
        return true;
    }

    @Override
    public ListIterator<E> listIterator() {
        return new ListItr(0);
    }

    @Override
    public ListIterator<E> listIterator(int index) {
        return new ListItr(index);
    }

    @Override
    public List<E> subList(int fromIndex, int toIndex) {
        subListRangeCheck(fromIndex, toIndex);
        List<E> subList = new ArrayList<>(toIndex - fromIndex + 1);
        for (int i = fromIndex; i <= toIndex; i++) {
            subList.add((E) elementData[i]);
        }
        return subList;
    }

    @Override
    public <T> T[] toArray(T[] a) {
        return a;
    }

    private void subListRangeCheck(int fromIndex, int toIndex) {
        if (fromIndex > toIndex)
            throw new IndexOutOfBoundsException("fromIndex : " + fromIndex + " toIndex : " + toIndex);
        if (fromIndex < 0 || toIndex > size)
            throw new IndexOutOfBoundsException("fromIndex : " + fromIndex + " toIndex : " + toIndex);
    }

    private void rangeCheckForAdd(int index, int size) {
        if (index > size || index < 0) throw new IndexOutOfBoundsException("Index : " + index + " size : " + size);
    }

    private void add(E element, Object[] elementData, int s) {
        if (size == elementData.length) {
            elementData = grow();
        }
        elementData[s] = element;
        size = s + 1;
    }

    private void addElementToArray(int index, E element) {
        elementData = grow(size + 1);
        for (int i = size - 1; i > index; i--) {
            elementData[i] = elementData[i - 1];
        }
        elementData[index] = element;

    }

    private void removeElementByIndex(int index) {
        modCount++;
        for (int i = index; i < size - 1; i++) {
            elementData[i] = elementData[i + 1];
        }
        elementData[size - 1] = null;
        size--;
    }

    private void rangeCheckForIndex(int index, int size) {
        if (index >= size || index < 0) throw new IndexOutOfBoundsException("Index : " + index + " size : " + size);
    }

    private Object[] convertCollectionToArray(Collection<? extends E> collection) {
        Object[] array = new Object[collection.size()];
        int i = 0;
        for (E e : collection) {
            array[i++] = e;
        }
        return array;
    }

    private void customArrayCopy(Object[] source, int sourcePosition, Object[] destination, int destinationPosition, int length) {
        for (int i = 0; i < length; i++) {
            destination[destinationPosition + i] = source[sourcePosition + i];
        }
    }


    private Object[] customCopyOf(Object[] original, int newLength) {
        Object[] copy = new Object[newLength];
        int lengthToCopy = Math.min(original.length, newLength);

        for (int i = 0; i < lengthToCopy; i++) {
            copy[i] = original[i];
        }

        return copy;
    }

    private Object[] grow(int newCapacity) {
        int oldCapacity = elementData.length;
        if (oldCapacity > 0) {
            return elementData = customCopyOf(elementData, newCapacity);
        }
        return null;
    }

    private Object[] grow() {
        int oldCapacity = elementData.length;
        if (oldCapacity > 0) {
            int newCapacity = elementData.length + (elementData.length >> 1); //element data / 2
            return elementData = customCopyOf(elementData, newCapacity);
        } else {
            return elementData = new Object[Math.max(DEFAULT_CAPACITY, size + 1)];
        }
    }

    private class Itr implements Iterator<E> {
        int cursor;       // index of next element to return
        int lastReturnedElementIndex = -1; // index of last element returned; -1 if no such
        int expectedModCount = modCount;

        @Override
        public boolean hasNext() {
            return cursor != size;
        }

        @Override
        public E next() {
            checkForModification();
            int nextElementIndex = cursor;
            if (nextElementIndex >= size)
                throw new NoSuchElementException();
            Object[] elementData = CustomArrayList.this.elementData;
            if (nextElementIndex >= elementData.length)
                throw new ConcurrentModificationException();
            cursor = nextElementIndex + 1;
            return (E) elementData[lastReturnedElementIndex = nextElementIndex];
        }

        @Override
        public void remove() {
            if (lastReturnedElementIndex < 0)
                throw new IllegalStateException();
            checkForModification();
            try {
                CustomArrayList.this.remove(lastReturnedElementIndex);
                cursor = lastReturnedElementIndex;
                lastReturnedElementIndex = -1;
                expectedModCount = modCount;
            } catch (IndexOutOfBoundsException ex) {
                throw new ConcurrentModificationException();
            }
        }

        final void checkForModification() {
            if (modCount != expectedModCount)
                throw new ConcurrentModificationException();
        }
    }

    private class ListItr extends Itr implements ListIterator<E> {
        ListItr(int index) {
            super();
            cursor = index;
        }

        public boolean hasPrevious() {
            return cursor != 0;
        }

        public int nextIndex() {
            return cursor;
        }

        public int previousIndex() {
            return cursor - 1;
        }

        @Override
        public E previous() {
            checkForModification();
            int previousElementIndex = cursor - 1;
            if (previousElementIndex < 0)
                throw new NoSuchElementException();
            Object[] elementData = CustomArrayList.this.elementData;
            if (previousElementIndex >= elementData.length)
                throw new ConcurrentModificationException();
            cursor = previousElementIndex;
            return (E) elementData[lastReturnedElementIndex = previousElementIndex];
        }

        @Override
        public void set(E e) {
            if (lastReturnedElementIndex < 0)
                throw new IllegalStateException();
            checkForModification();
            try {
                CustomArrayList.this.set(lastReturnedElementIndex, e);
            } catch (IndexOutOfBoundsException ex) {
                throw new ConcurrentModificationException();
            }
        }

        @Override
        public void add(E e) {
            checkForModification();
            try {
                int i = cursor;
                CustomArrayList.this.add(i, e);
                cursor = i + 1;
                lastReturnedElementIndex = -1;
                expectedModCount = modCount;
            } catch (IndexOutOfBoundsException ex) {
                throw new ConcurrentModificationException();
            }
        }

    }
}



