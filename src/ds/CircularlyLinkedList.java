package ds;

/**
 * Created by Mayank Singh on 28-07-2015.
 */
public class CircularlyLinkedList<E> {
    // instance variables of the CircularlyLinkedList
    private Node<E> tail = null; // we store tail (but not head)
    private int size = 0; // number of nodes in the list

    public CircularlyLinkedList() {
    } // constructs an initially empty list

    // access methods
    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    // returns (but does not remove) the first element
    public E first() {
        if (isEmpty()) return null;
        return tail.getNext().getElement(); // the head is *after* the tail
    }

    // returns (but does not remove) the first element
    public E last() {
        if (isEmpty()) return null;
        return tail.getElement();
    }

    // update methods

    // rotate the first element to the back of the list
    public void rotate() {
        if (tail != null) // if empty, do nothing
            tail = tail.getNext(); // the old head becomes the new tail
    }

    // adds element e to the front of the list
    public void addFirst(E e) {
        if (size == 0) {
            tail = new Node<>(e, null);
            tail.setNext(tail); // link to itself circularly
        } else {
            Node<E> newest = new Node<>(e, tail.getNext());
            tail.setNext(newest);
        }
        size++;
    }

    // adds element e to the end of the list
    public void addLast(E e) {
        addFirst(e); // insert new element at front of list
        tail = tail.getNext(); // now new element becomes the tail
    }

    // removes and returns the first element
    public E removeFirst() {
        if (isEmpty()) return null; // nothing to remove
        Node<E> head = tail.getNext();
        if (head == tail) tail = null; // must be the only node left
        else tail.setNext(head.getNext()); // removes ”head” from the list
        size--;
        return head.getElement();
    }

    private static class Node<E> {
        private E element; // reference to the element stored at this node
        private Node<E> next; // reference to the subsequent node in the list

        public Node(E e, Node<E> n) {
            element = e;
            next = n;
        }

        public E getElement() {
            return element;
        }

        public Node<E> getNext() {
            return next;
        }

        public void setNext(Node<E> n) {
            next = n;
        }
    }
}