package by.it.group410971.kurlianski.lesson10;

import java.util.Deque;

public class MyArrayDeque<E> implements Deque<E> {
    private static final int DEFAULT_CAPACITY = 10;
    private Object[] elements;
    private int head;
    private int tail;
    private int size;

    public MyArrayDeque() {
        this.elements = new Object[DEFAULT_CAPACITY];
        this.head = 0;
        this.tail = 0;
        this.size = 0;
    }

    @Override
    public String toString() {
        if (size == 0) {
            return "[]";
        }
        StringBuilder sb = new StringBuilder("[");
        for (int i = 0; i < size; i++) {
            int index = (head + i) % elements.length;
            sb.append(elements[index]);
            if (i < size - 1) {
                sb.append(", ");
            }
        }
        sb.append("]");
        return sb.toString();
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean add(E element) {
        addLast(element);
        return true;
    }

    @Override
    public void addFirst(E element) {
        if (element == null) {
            throw new NullPointerException();
        }
        ensureCapacity();
        head = (head - 1 + elements.length) % elements.length;
        elements[head] = element;
        size++;
    }

    @Override
    public void addLast(E element) {
        if (element == null) {
            throw new NullPointerException();
        }
        ensureCapacity();
        elements[tail] = element;
        tail = (tail + 1) % elements.length;
        size++;
    }

    @Override
    public E element() {
        return getFirst();
    }

    @Override
    public E getFirst() {
        if (size == 0) {
            throw new java.util.NoSuchElementException();
        }
        return (E) elements[head];
    }

    @Override
    public E getLast() {
        if (size == 0) {
            throw new java.util.NoSuchElementException();
        }
        int lastIndex = (tail - 1 + elements.length) % elements.length;
        return (E) elements[lastIndex];
    }

    @Override
    public E poll() {
        return pollFirst();
    }

    @Override
    public E pollFirst() {
        if (size == 0) {
            return null;
        }
        E element = (E) elements[head];
        elements[head] = null;
        head = (head + 1) % elements.length;
        size--;
        return element;
    }

    @Override
    public E pollLast() {
        if (size == 0) {
            return null;
        }
        tail = (tail - 1 + elements.length) % elements.length;
        E element = (E) elements[tail];
        elements[tail] = null;
        size--;
        return element;
    }

    private void ensureCapacity() {
        if (size == elements.length) {
            int newCapacity = elements.length * 2;
            Object[] newElements = new Object[newCapacity];

            for (int i = 0; i < size; i++) {
                int index = (head + i) % elements.length;
                newElements[i] = elements[index];
            }

            elements = newElements;
            head = 0;
            tail = size;
        }
    }

    // Остальные методы интерфейса Deque - оставляем пустыми по умолчанию
    @Override public boolean offer(E e) { return false; }
    @Override public boolean offerFirst(E e) { return false; }
    @Override public boolean offerLast(E e) { return false; }
    @Override public E remove() { return null; }
    @Override public E removeFirst() { return null; }
    @Override public E removeLast() { return null; }
    @Override public E peek() { return null; }
    @Override public E peekFirst() { return null; }
    @Override public E peekLast() { return null; }
    @Override public boolean removeFirstOccurrence(Object o) { return false; }
    @Override public boolean removeLastOccurrence(Object o) { return false; }
    @Override public boolean addAll(java.util.Collection<? extends E> c) { return false; }
    @Override public boolean removeAll(java.util.Collection<?> c) { return false; }
    @Override public boolean retainAll(java.util.Collection<?> c) { return false; }
    @Override public void clear() { }
    @Override public boolean containsAll(java.util.Collection<?> c) { return false; }
    @Override public boolean contains(Object o) { return false; }
    @Override public boolean isEmpty() { return false; }
    @Override public java.util.Iterator<E> iterator() { return null; }
    @Override public java.util.Iterator<E> descendingIterator() { return null; }
    @Override public boolean remove(Object o) { return false; }
    @Override public void push(E e) { }
    @Override public E pop() { return null; }
    @Override public Object[] toArray() { return new Object[0]; }
    @Override public <T> T[] toArray(T[] a) { return null; }
}