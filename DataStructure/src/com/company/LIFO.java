package com.company;
import java.util.Iterator;
public class ResizingArrayStack<Item> implements Iterable<Item> {

    private Item[] a = (Item []) new Object[1];
    private int N = 0;

    private void resize(int max) {
        Item[] temp = (Item[]) new Object[max];
        for (int i = 0; i < N; i++)
            temp[i] = a[i];
        a = temp;
    }

    private class ReverseArrayIterator implements Iterable<Item> {
        private int i = N;
        public boolean hasNext() {
            return i > 0;
        }
        public Item next() {
            return a[--i];
        }
        public void remove() {

        }
    }

    public boolean isEmpty() {
        return N == 0;
    }

    public int size() {
        return N;
    }

    public Item pop() {
        Item item = a[--N];
        a[N] = null;
        if (N > 0 && N == a.length/4)
            resize(a.length/2);
        return item;
    }

    public Iterable<Item> iterator() {
        return new ReverseArrayIterator();
    }

}
