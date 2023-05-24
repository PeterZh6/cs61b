/**first part of project1A.
 * Deque implemented by Linked List
 *
 * @author Peter & PKUFlyingPig
 */
public class LinkedListDeque<T> {
    private int size = 0;
    private final Node sentinel;

    public class Node {
        private T item;
        private Node prev;
        private Node next;
        public Node(T item, Node prev, Node next) {
            this.item = item;
            this.prev = prev;
            this.next = next;
        }


        public Node(Node prev, Node next) {
            this.prev = prev;
            this.next = next;
        }
    }

    public void addFirst(T item) {
        Node newNode = new Node(item, sentinel, sentinel.next);
        sentinel.next.prev = newNode;
        sentinel.next = newNode;
        size++;
    }

    public void addLast(T item) {
        Node newNode = new Node(item, sentinel.prev, sentinel);
        sentinel.prev.next = newNode;
        sentinel.prev = newNode;
        size++;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    public void printDeque() {
        Node current = sentinel.next;
        while (current != sentinel) {
            System.out.print(current.item + " ");
            current = current.next;
        }
    }

    public T removeFirst() {
        if (size == 0) {
            return null;
        }
        T deleted = sentinel.next.item;
        sentinel.next = sentinel.next.next;
        size--;
        return deleted;
    }

    public T removeLast() {
        if (size == 0) {
            return null;
        }
        T deleted = sentinel.prev.item;
        sentinel.prev = sentinel.prev.prev;
        size--;
        sentinel.prev.next = sentinel;
        return deleted;
    }

    public T get(int index) {
        if (index < 0 || index > size) {
            return null;
        }
        Node current = sentinel;
        for (int i = 0; i < index; i++) {
            current = current.next;
        }
        return current.item;
    }

    public T getRecursive(int index) {
        if (index < 0 || index > size) {
            return null;
        }
        return getR(index, sentinel);

    }

    private T getR(int index, Node current) {
        if (index == 0) {
            return current.item;
        }
        return getR(index - 1, current.next);
    }

    public LinkedListDeque() {
        sentinel = new Node(null, null);
        sentinel.next = sentinel;
        sentinel.prev = sentinel;
    }
}
