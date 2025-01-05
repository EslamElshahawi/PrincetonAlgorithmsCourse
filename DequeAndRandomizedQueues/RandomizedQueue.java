import java.util.Iterator;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdOut;

public class RandomizedQueue<Item> implements Iterable<Item> {
    private Item[] q;
    private int size;

    // construct an empty randomized queue
    @SuppressWarnings("unchecked")
    public RandomizedQueue() {
        size = 0;
        q = (Item[]) new Object[1];
    }

    private class RandomizedQueueIterator implements Iterator<Item> {
        private int i = size;
        private int[] order;

        public RandomizedQueueIterator() {
            order = new int[i];
            for(int j = 0; j < i; ++j) {
                order[j] = j;
            }
            StdRandom.shuffle(order);
        }

        public boolean hasNext() { return i > 0; }
        public void remove() { throw new java.lang.UnsupportedOperationException("Unsupported Operation"); }
        public Item next() {
            if (!hasNext()) throw new java.util.NoSuchElementException();
            return q[order[--i]];
        }
    }

    // is the randomized queue empty?
    public boolean isEmpty() { return size == 0; }

    // return the number of items on the randomized queue
    public int size() { return size; }

    private void resize(int capacity) {
        @SuppressWarnings("unchecked") Item[] copy = (Item[]) new Object[capacity];
        for (int i = 0; i < size; i++) {
            copy[i] = q[i];
        }
        q = copy;
    }

    // add the item
    public void enqueue(Item item) {
        if (item == null) throw new IllegalArgumentException("item is null");
        q[size++] = item;
        if (q.length == size) resize(q.length*2);
    }

    // remove and return a random item
    public Item dequeue() {
        if (isEmpty()) throw new java.util.NoSuchElementException("Queue is Empty");
        int r = StdRandom.uniformInt(size);
        Item item = q[r];
        q[r] = q[size-1];
        q[--size] = null;
        if (size > 0 && size == q.length/4) resize(q.length/2);
        return item;
    }

    // return a random item (but do not remove it)
    public Item sample() {
        if (isEmpty()) throw new java.util.NoSuchElementException("Queue is Empty");
        int r = StdRandom.uniformInt(size);
        return q[r];
    }

    // return an independent iterator over items in random order
    public Iterator<Item> iterator() { return new RandomizedQueueIterator(); }

    // unit testing (required)
    public static void main(String[] args) {
        RandomizedQueue<Integer> q = new RandomizedQueue<Integer>();
        q.enqueue(5);
        q.enqueue(6);
        q.enqueue(7);
        q.enqueue(8);
        Iterator<Integer> i =  q.iterator();
        while (i.hasNext()) {
            StdOut.println(i.next());
        }
        StdOut.println(q.dequeue());
        StdOut.println(q.sample());
    }
}
