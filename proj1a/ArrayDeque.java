/**loop array
 * head coming from tail with tail coming from head, and meet at the middle
 * @Peter
 */
public class ArrayDeque<T> {

	private T[] array;
	private int size;
	private static final int defaultLength = 8;
	private int head = defaultLength - 1;
	/**
	 * position of first element of the array
	 */
	private int tail = 0;

	/**
	 * position of last element of the array
	 */


	public ArrayDeque() {
		array = (T[]) new Object[defaultLength];
		size = 0;
	}


	private void resize(int capacity) {
		T[] tempContainer = (T[]) new Object[capacity];
		int newSize = 0;

		while (newSize < size) {
			tempContainer[newSize] = array[(head + 1 + newSize) % array.length];
			newSize++;
		}

		array = tempContainer;
		head = array.length - 1;
		tail = size;
	}

	private void checkAndResize() {
		double lowestUsageRate = 0.25;
		if (size == array.length) {
			resize(size * 2);
		} else if (array.length >= 16 && size * 1.0 / (array.length) < lowestUsageRate) {
			resize((int) (array.length * 0.5));
		}
	}

	public void addFirst(T item) {
		checkAndResize();
		array[head] = item;
		head--;
		size++;
	}
	/* my own wrong code:public void addFirst(T item){
		checkAndResize();
		array[head] = item;
		head--;
		size++;
	} */

	public void addLast(T item) {
		checkAndResize();
		array[tail] = item;
		tail++;
		size++;
	}

	public T removeFirst() {
		if (isEmpty()) {
			return null;
		}
		T returnObject = array[head+1];
		array[head+1] = null;
		head++;
		size--;
		checkAndResize();
		return returnObject;
	}

	public T removeLast() {
		if (isEmpty()) {
			return null;
		}
		T returnObject = array[tail-1];
		array[tail-1] = null;
		tail--;
		size--;
		checkAndResize();
		return returnObject;
	}

	public boolean isEmpty() {
		return size == 0;
	}

	public int size() {
		return size;
	}

	public void printDeque() {
		for (int i = 0; i < size; i++) {
			System.out.print(get(i) + " ");
		}
	}

	public T get(int index) {
		if (index < 0 || index > array.length) {
			return null;
		}
		return array[(head + index + 1) % array.length]; /* use modulus operation to realize circular array */
	}

}
