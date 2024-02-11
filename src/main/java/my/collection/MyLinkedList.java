package my.collection;

import java.util.Objects;

public class MyLinkedList <T> implements MyList<T> {
    private int size;
    private Node<T> head;
    private Node<T> tail;

    public MyLinkedList() {
    }

    public MyLinkedList(MyList<? extends T> list) {
        for (int i = 0; i < list.size(); i++) {
            add(list.get(i));
        }
    }

    @Override
    public boolean add(T element) {
        Node<T> node = new Node<>(tail, element, null);
        if (tail == null) {
            head = node;
        } else {
            tail.after = node;
        }
        tail = node;
        size++;
        return true;
    }

    @Override
    public T get(int index) {
        Objects.checkIndex(index, size);
        if (index == 0) {
            return head.element;
        }
        if (index == size - 1) {
            return tail.element;
        }
        return findNodeByIndex(index).element;
    }

    private Node<T> findNodeByIndex(int index) {
        Node<T> node = head;
        int count = 0;
        while (count != index) {
            node = node.after;
            count++;
        }
        return node;
    }

    @Override
    public T remove(int index) {
        Objects.checkIndex(index, size);
        T removeElement;
        if (index == 0) {
            removeElement = head.element;
            head = head.after;
            head.before = null;
        } else if (index == size - 1) {
            removeElement = tail.element;
            tail = tail.before;
            tail.after = null;
        } else {
            Node<T> nodeNeedToRemove = findNodeByIndex(index);
            removeElement = nodeNeedToRemove.element;
            Node<T> before = nodeNeedToRemove.before;
            Node<T> after = nodeNeedToRemove.after;
            before.after = after;
            after.before = before;
        }
        size--;
        return removeElement;
    }

    @Override
    public boolean addAll(MyList<? extends T> list) {
        if (list.size() == 0) {
            return false;
        }
        for (int i = 0; i < list.size(); i++) {
            add(list.get(i));
        }
        return true;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public T[] toArray() {
        if (size == 0) {
            return (T[]) new Object[0];
        }

        Object[] array = new Object[size];

        Node<T> node = head;
        for (int i = 0; i < size; i++) {
            array[i] = node.element;
            node = node.after;
        }
        return (T[]) array;
    }

    @Override
    public T set(int index, T newElement) {
        Objects.checkIndex(index, size);
        Node<T> node = findNodeByIndex(index);
        T oldElement = node.element;
        node.element = newElement;

        return oldElement;
    }

    @Override
    public String toString() {
        if (size > 0)  {
            StringBuilder sb = new StringBuilder();
            Node<T> node = head;
            sb.append('[');
            while (node != null) {
                sb.append(node.element)
                        .append(',')
                        .append(' ');
                node = node.after;
            }
            sb.replace(sb.length() - 2, sb.length(), "]");
            return sb.toString();
        }
        return "[]";
    }

    private static class Node<T> {
        T element;
        Node<T> before;
        Node<T> after;

        public Node(Node<T> before, T element, Node<T> after) {
            this.element = element;
            this.before = before;
            this.after = after;
        }
    }
}
