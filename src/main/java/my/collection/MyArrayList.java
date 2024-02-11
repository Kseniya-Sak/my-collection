package my.collection;

import java.util.Arrays;
import java.util.Objects;

public class MyArrayList<T> implements MyList<T> {
    private Object[] array;
    private static final int DEFAULT_CAPACITY = 10;
    private int size;

    public MyArrayList() {
        array = new Object[DEFAULT_CAPACITY];
    }

    public MyArrayList(int capacity) {
        array = new Object[capacity];
    }

    public MyArrayList(MyList<? extends T> list) {
        array = new Object[list.size()];
        for (int i = 0; i < list.size(); i++) {
            add(list.get(i));
        }
    }

    @Override
    public boolean add(T element) {
        checkCapacity(size);
        array[size++] = element;
        return true;
    }

    private void checkCapacity(int size) {
        if (size >= array.length) {
            // если добавляется коллекция, то выбираем болеьшее число из:
            // - либо array.size + размер добавляемой коллекции
            // - либо capacity в полтара раза увеличиваем
            int newCapacity = Integer.max(size, array.length + (array.length >> 1));
            Object[] newArray = new Object[newCapacity];
            System.arraycopy(array, 0,newArray, 0, array.length);
            array = newArray;
        }
    }

    @Override
    public T get(int index) {
        Objects.checkIndex(index, size);
        return getElement(index);
    }


    @Override
    public int size() {
        return size;
    }

    @Override
    public T remove(int index) {
        Objects.checkIndex(index, size);
        T element = getElement(index);
        System.arraycopy(array, index + 1, array, index, array.length - index - 1);
        size--;
        return element;
    }

    @Override
    public boolean addAll(MyList<? extends T> list) {
        if (list.size() == 0) {
            return false;
        }
        checkCapacity(size + list.size());
        System.arraycopy(list.toArray(), 0, array, size, list.size());
        size += list.size();
        return true;
    }

    @SuppressWarnings("Unchecked")
    public T[] toArray() {
        return (T[]) Arrays.copyOf(array, size);
    }

    @SuppressWarnings("Unchecked")
    private T getElement(int index) {
        return (T) array[index];
    }

    @Override
    public String toString() {
        if (size > 0) {
            StringBuilder sb = new StringBuilder();
            sb.append('[');
            for (int i = 0; i < size; i++) {
                sb.append(array[i])
                        .append(',')
                        .append(' ');
            }
            sb.delete(sb.length() - 2, sb.length())
                    .append(']');
            return sb.toString();
        }
        return "[]";
    }

    @Override
    public T set(int index, T newElement) {
        Objects.checkIndex(index, size);
        T oldElement = getElement(index);
        array[index] = newElement;
        return oldElement;
    }
}


