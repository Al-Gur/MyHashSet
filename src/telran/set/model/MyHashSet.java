package telran.set.model;

import telran.set.interfaces.ISet;

import java.util.Iterator;
import java.util.LinkedList;

public class MyHashSet<E> implements ISet<E> {
    private LinkedList<E>[] hashset;
    private int size;
    private int capacity;
    private double loadFactor;

    public MyHashSet(int capacity, double loadFactor) {
        this.capacity = capacity;
        this.loadFactor = loadFactor;
        hashset = new LinkedList[capacity];
    }

    public MyHashSet(int capacity) {
        this(capacity, 0.75);
    }

    public MyHashSet() {
        this(16);
    }

    @Override
    public boolean add(E element) {
        if (size >= loadFactor * capacity) {
            rebuildArray();
        }
        int index = getIndex(element);
        if (hashset[index] == null) {
            hashset[index] = new LinkedList<>();
        }
        if (hashset[index].contains(element)) {
            return false;
        }
        hashset[index].add(element);
        size++;
        return true;
    }

    private void rebuildArray() {
        capacity = capacity * 2;
        LinkedList<E>[] newHashSet = new LinkedList[capacity];
        for (int i = 0; i < hashset.length; i++) {
            if (hashset[i] != null) {
                for (E e : hashset[i]) {
                    int index = getIndex(e);
                    if (newHashSet[index] == null) {
                        newHashSet[index] = new LinkedList<>();
                    }
                    newHashSet[index].add(e);
                }
            }
        }
        hashset = newHashSet;
    }

    private int getIndex(E element) {
        int hashcode = element.hashCode();
        hashcode = hashcode >= 0 ? hashcode : -hashcode;
        return hashcode % capacity;
    }

    @Override
    public boolean contains(E element) {
        int index = getIndex(element);
        if (hashset[index] == null) {
            return false;
        }
        return hashset[index].contains(element);
    }

    @Override
    public boolean remove(E element) {
        int index = getIndex(element);
        if (hashset[index] == null) {
            return false;
        }
        if (!hashset[index].remove(element)){
            return false;
        }
        size--;
        return true;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {
            private int index;
            private int listIndex;

            @Override
            public boolean hasNext() {
                while (index < capacity) {
                    if (hashset[index] != null && listIndex < hashset[index].size()) {
                        return true;
                    }
                    listIndex = 0;
                    index++;
                }
                return false;
            }

            @Override
            public E next() {
                hasNext();
                return hashset[index].get(listIndex++);
            }
        };
    }
}
