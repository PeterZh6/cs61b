/**  first part of project1A.
 *   Deque implemented by Linked List
 *  @author Peter & PKUFlyingPig
 */
public class LinkedListDeque<T>{
	private int size = 0;
	private Node sentinel;
	public class Node {
        private T item;
        private Node prev;
        private Node next;

        public Node(T item, Node prev, Node next) {
            this.item = item;
            this.prev = prev;
            this.next = next;
        }

        /** constructor for Node.(especially for sentinel node). */
        public Node(Node prev, Node next) {
            this.prev = prev;
            this.next = next;
        }
    }

	public void addFirst(T item){
		sentinel.next = new Node(item, sentinel, sentinel.next);
		size++;
		sentinel.next.next.prev = sentinel.next;
	}

	public void addLast(T item){
		sentinel.prev = new Node(item, sentinel.prev.prev, sentinel);
		size++;
		sentinel.prev.prev.next = sentinel.prev;
	}

	public boolean isEmpty(){
		return size == 0;
	}
	public int size(){
		return size;
	}

	public void printDeque(){
		Node current = sentinel.next;
		while (current != sentinel){
			System.out.println(current.item);
			current = current.next;
			}
		}

	public T removeFirst(){
		if(size == 0){
			return null;
		}
		T deleted = sentinel.next.item;
		sentinel.next = sentinel.next.next;
		size--;
		return deleted;
	}

	public T removeLast(){
		if(size == 0){
			return null;
		}
		T deleted = sentinel.prev.item;
		sentinel.prev = sentinel.prev.prev;
		size--;
		sentinel.prev.next = sentinel;
		return deleted;
	}

	public T get(int index){
		if (index ==  0 || index >= size){
			return null;
		}
		Node current = sentinel;
		for (int i=0; i<index; i++){
			current = current.next;
		}
		return current.item;
	}

	public LinkedListDeque(){
		sentinel = new Node(null, null);
		sentinel.next = sentinel;
		sentinel.prev = sentinel;
	}
}