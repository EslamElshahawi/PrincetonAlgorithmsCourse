import java.util.Iterator;
import edu.princeton.cs.algs4.StdOut;
public class Deque<Item> implements Iterable<Item> {
    private Node head, tail;
    private int size;
    private class Node {
        Item item;
        Node next;
        Node prev;
    }
    // construct an empty deque
    public Deque() {
        size = 0;
        head = null;
        tail = null;
    }

    private class DequeIterator implements Iterator<Item> {
        private Node current = head;

        public boolean hasNext() {
            return current != null;
        }
        public Item next() {
            if (!hasNext()) throw new java.util.NoSuchElementException("Queue is empty");
            Item item = current.item;
            current = current.next;
            return item;
        }
        public void remove() {
            throw new UnsupportedOperationException("No support for remove");
        }
    }

    // is the deque empty?
    public boolean isEmpty() {
        return size == 0;
    }

    // return the number of items on the deque
    public int size() {
        return size;
    }

    // add the item to the front
    public void addFirst(Item item) {
        if (item == null) throw new IllegalArgumentException("Item is null");
        Node oldFirst = head;
        head = new Node();
        head.item = item;
        head.next = oldFirst;
        head.prev = null;
        if (isEmpty()) tail = head;
        else oldFirst.prev = head;
        size++;
    }

    // add the item to the back
    public void addLast(Item item) {
        if (item == null) throw new IllegalArgumentException("Item is null");
        Node oldLast = tail;
        tail = new Node();
        tail.item = item;
        tail.next = null;
        tail.prev = oldLast;
        if (isEmpty()) head = tail;
        else oldLast.next = tail;
        size++;
    }

    // remove and return the item from the front
    public Item removeFirst() {
        if (isEmpty()) throw new java.util.NoSuchElementException("The queue is empty");
        Item item = head.item;
        head = head.next;
        size--;
        if (isEmpty()) tail = head;
        else head.prev = null;
        return item;
    }

    // remove and return the item from the back
    public Item removeLast() {
        if (isEmpty()) throw new java.util.NoSuchElementException("The queue is empty");
        Item item = tail.item;
        tail = tail.prev;
        size--;
        if (isEmpty()) head = tail;
        else tail.next = null;
        return item;
    }

    // return an iterator over items in order from front to back
    public Iterator<Item> iterator() {
        return new DequeIterator();
    }

    // unit testing (required)
    public static void main(String[] args) {
        Deque<Integer> deque = new Deque<Integer>();
        deque.addFirst(5);
        deque.addFirst(6);
        deque.addLast(7);
        deque.addLast(8);
        Iterator<Integer> i = deque.iterator();
        while (i.hasNext()) {
            int j = i.next();
            StdOut.println(j);
        }
        StdOut.println(deque.removeFirst());
        StdOut.println(deque.removeLast());

    }
}
