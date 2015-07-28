package ds;

/**
 * Created by Mayank Singh on 27-07-2015.
 */
public class SinglyLinkedList<E> implements Cloneable {
    // instance variables of the SinglyLinkedList
    private Node<E> head = null; // head node of the list (or null if empty)
    private Node<E> tail = null; // last node of the list (or null if empty)
    private int size = 0; // number of nodes in the list

    /**
     * Creates a new empty list
     */
    public SinglyLinkedList() {
    } // constructs an initially empty list

    // access methods

    /**
     * it return the size of the list
     * @return the size of list
     */
    public int size() {
        return size;
    }

    /**
     * checks if the list is empty
     * @return true if the list has no elements, false otherwise
     */
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * returns (but does not remove) the first element
     * @return the element at the head of the list
     */
    public E first() {
        if (isEmpty()) return null;
        return head.getElement();
    }

    /**
     * returns (but does not remove) the last element
     * @return the element at the tail of the list
     */
    public E last() {
        if (isEmpty()) return null;
        return tail.getElement();
    }

    // update methods

    /**
     * adds element e to the front of the list
     * @param e the element to add to the start of the list
     */
    public void addFirst(E e) {
        head = new Node<>(e, head); // create and link a new node
        if (size == 0)
            tail = head; // special case: new node becomes tail also
        size++;
    }

    /**
     * adds element e to the end of the list
     * @param e the element to add at the end of the list
     */
    public void addLast(E e) {
        Node<E> newest = new Node<>(e, null); // node will eventually be the tail
        if (isEmpty())
            head = newest; // special case: previously empty list
        else
            tail.setNext(newest); // new node after existing tail
        tail = newest; // new node becomes the tail
        size++;
    }

    /**
     * removes and returns the first element
     * @return the element removed
     */
    public E removeFirst() {
        if (isEmpty()) return null; // nothing to remove
        E answer = head.getElement();
        head = head.getNext(); // will become null if list had only one node
        size--;
        if (size == 0)
            tail = null; // special case as list is now empty
        return answer;
    }

    public boolean equals(Object o) {
        if (o == null) return false;
        if (getClass() != o.getClass()) return false;
        SinglyLinkedList other = (SinglyLinkedList) o; // use nonparameterized type
        if (size != other.size) return false;
        Node walkA = head; // traverse the primary list
        Node walkB = other.head; // traverse the secondary list
        while (walkA != null) {
            if (!walkA.getElement().equals(walkB.getElement())) return false; //mismatch
            walkA = walkA.getNext();
            walkB = walkB.getNext();
        }
        return true; // if we reach this, everything matched successfully
    }

    public SinglyLinkedList<E> clone() throws CloneNotSupportedException {
        // always use inherited Object.clone() to create the initial copy
        SinglyLinkedList<E> other = (SinglyLinkedList<E>) super.clone(); // safe cast
        if (size > 0) { // we need independent chain of nodes
            other.head = new Node<>(head.getElement(), null);
            Node<E> walk = head.getNext(); // walk through remainder of original list
            Node<E> otherTail = other.head; // remember most recently created node
            while (walk != null) { // make a new node storing same element
                Node<E> newest = new Node<>(walk.getElement(), null);
                otherTail.setNext(newest); // link previous node to this one
                otherTail = newest;
                walk = walk.getNext();
            }
        }
        return other;
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
