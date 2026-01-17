package by.it.group410971.kurlianski.lesson10;

import java.util.Deque;

public class MyLinkedList<E> implements Deque<E> {
    private Node<E> first;
    private Node<E> last;
    private int size;

    private static class Node<E> {
        E item;
        Node<E> next;
        Node<E> prev;

        Node(Node<E> prev, E element, Node<E> next) {
            this.item = element;
            this.next = next;
            this.prev = prev;
        }
    }

    @Override
    public String toString() {
        if (size == 0) {
            return "[]";
        }
        StringBuilder sb = new StringBuilder("[");
        Node<E> current = first;
        while (current != null) {
            sb.append(current.item);
            if (current.next != null) {
                sb.append(", ");
            }
            current = current.next;
        }
        sb.append("]");
        return sb.toString();
    }

    @Override
    public boolean add(E element) {
        addLast(element);
        return true;
    }

    public E remove(int index) {
        checkElementIndex(index);
        return unlink(node(index));
    }

    @Override
    public boolean remove(Object o) {
        if (o == null) {
            for (Node<E> x = first; x != null; x = x.next) {
                if (x.item == null) {
                    unlink(x);
                    return true;
                }
            }
        } else {
            for (Node<E> x = first; x != null; x = x.next) {
                if (o.equals(x.item)) {
                    unlink(x);
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void addFirst(E element) {
        if (element == null) {
            throw new NullPointerException();
        }
        final Node<E> f = first;
        final Node<E> newNode = new Node<>(null, element, f);
        first = newNode;
        if (f == null) {
            last = newNode;
        } else {
            f.prev = newNode;
        }
        size++;
    }

    @Override
    public void addLast(E element) {
        if (element == null) {
            throw new NullPointerException();
        }
        final Node<E> l = last;
        final Node<E> newNode = new Node<>(l, element, null);
        last = newNode;
        if (l == null) {
            first = newNode;
        } else {
            l.next = newNode;
        }
        size++;
    }

    @Override
    public E element() {
        return getFirst();
    }

    @Override
    public E getFirst() {
        final Node<E> f = first;
        if (f == null) {
            throw new java.util.NoSuchElementException();
        }
        return f.item;
    }

    @Override
    public E getLast() {
        final Node<E> l = last;
        if (l == null) {
            throw new java.util.NoSuchElementException();
        }
        return l.item;
    }

    @Override
    public E poll() {
        return pollFirst();
    }

    @Override
    public E pollFirst() {
        final Node<E> f = first;
        if (f == null) {
            return null;
        }
        return unlinkFirst();
    }

    @Override
    public E pollLast() {
        final Node<E> l = last;
        if (l == null) {
            return null;
        }
        return unlinkLast();
    }

    private E unlink(Node<E> x) {
        final E element = x.item;
        final Node<E> next = x.next;
        final Node<E> prev = x.prev;

        if (prev == null) {
            first = next;
        } else {
            prev.next = next;
            x.prev = null;
        }

        if (next == null) {
            last = prev;
        } else {
            next.prev = prev;
            x.next = null;
        }

        x.item = null;
        size--;
        return element;
    }

    private E unlinkFirst() {
        final Node<E> f = first;
        final E element = f.item;
        final Node<E> next = f.next;

        f.item = null;
        f.next = null;

        first = next;
        if (next == null) {
            last = null;
        } else {
            next.prev = null;
        }
        size--;
        return element;
    }

    private E unlinkLast() {
        final Node<E> l = last;
        final E element = l.item;
        final Node<E> prev = l.prev;

        l.item = null;
        l.prev = null;

        last = prev;
        if (prev == null) {
            first = null;
        } else {
            prev.next = null;
        }
        size--;
        return element;
    }

    private Node<E> node(int index) {
        if (index < (size >> 1)) {
            Node<E> x = first;
            for (int i = 0; i < index; i++) {
                x = x.next;
            }
            return x;
        } else {
            Node<E> x = last;
            for (int i = size - 1; i > index; i--) {
                x = x.prev;
            }
            return x;
        }
    }

    private void checkElementIndex(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
    }

    // Для задания B - специфичный метод remove(E element) нужно переименовать
    // или добавить отдельно, но в задании указано именно remove(E element)
    // Оставим его как отдельный публичный метод, но он не переопределяет метод Deque
    public boolean removeElement(E element) {
        return remove(element);
    }

    // Остальные методы интерфейса Deque
    @Override public boolean offer(E e) { return false; }
    @Override public boolean offerFirst(E e) { return false; }
    @Override public boolean offerLast(E e) { return false; }
    @Override public E remove() {
        E x = poll();
        if (x == null) {
            throw new java.util.NoSuchElementException();
        }
        return x;
    }
    @Override public E removeFirst() {
        E x = pollFirst();
        if (x == null) {
            throw new java.util.NoSuchElementException();
        }
        return x;
    }
    @Override public E removeLast() {
        E x = pollLast();
        if (x == null) {
            throw new java.util.NoSuchElementException();
        }
        return x;
    }
    @Override public E peek() {
        return (first == null) ? null : first.item;
    }
    @Override public E peekFirst() {
        return (first == null) ? null : first.item;
    }
    @Override public E peekLast() {
        return (last == null) ? null : last.item;
    }
    @Override public boolean removeFirstOccurrence(Object o) {
        return remove(o);
    }
    @Override public boolean removeLastOccurrence(Object o) {
        if (o == null) {
            for (Node<E> x = last; x != null; x = x.prev) {
                if (x.item == null) {
                    unlink(x);
                    return true;
                }
            }
        } else {
            for (Node<E> x = last; x != null; x = x.prev) {
                if (o.equals(x.item)) {
                    unlink(x);
                    return true;
                }
            }
        }
        return false;
    }
    @Override public boolean addAll(java.util.Collection<? extends E> c) {
        boolean modified = false;
        for (E e : c) {
            add(e);
            modified = true;
        }
        return modified;
    }
    @Override public boolean removeAll(java.util.Collection<?> c) { return false; }
    @Override public boolean retainAll(java.util.Collection<?> c) { return false; }
    @Override public void clear() {
        for (Node<E> x = first; x != null; ) {
            Node<E> next = x.next;
            x.item = null;
            x.next = null;
            x.prev = null;
            x = next;
        }
        first = last = null;
        size = 0;
    }
    @Override public boolean containsAll(java.util.Collection<?> c) { return false; }
    @Override public boolean contains(Object o) {
        return indexOf(o) >= 0;
    }

    private int indexOf(Object o) {
        int index = 0;
        if (o == null) {
            for (Node<E> x = first; x != null; x = x.next) {
                if (x.item == null) {
                    return index;
                }
                index++;
            }
        } else {
            for (Node<E> x = first; x != null; x = x.next) {
                if (o.equals(x.item)) {
                    return index;
                }
                index++;
            }
        }
        return -1;
    }

    @Override public boolean isEmpty() {
        return size == 0;
    }
    @Override public java.util.Iterator<E> iterator() {
        return new Iterator();
    }

    private class Iterator implements java.util.Iterator<E> {
        private Node<E> next = first;
        private Node<E> lastReturned = null;

        @Override
        public boolean hasNext() {
            return next != null;
        }

        @Override
        public E next() {
            if (!hasNext()) {
                throw new java.util.NoSuchElementException();
            }
            lastReturned = next;
            next = next.next;
            return lastReturned.item;
        }
    }

    @Override public java.util.Iterator<E> descendingIterator() { return null; }
    @Override public void push(E e) {
        addFirst(e);
    }
    @Override public E pop() {
        return removeFirst();
    }
    @Override public Object[] toArray() {
        Object[] result = new Object[size];
        int i = 0;
        for (Node<E> x = first; x != null; x = x.next) {
            result[i++] = x.item;
        }
        return result;
    }
    @Override public <T> T[] toArray(T[] a) { return null; }
}