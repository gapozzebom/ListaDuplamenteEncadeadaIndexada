

public class ListaEncadeada<T> implements Iterable<T> {
	private class Node {
		private T data;
		public long level;
		private Node next;
		private Node previous;
		public Node down;
		
		//construtor tem que passar um valor
		public Node(T value) {
			data = value;
		}
	}
	
	private class ListIterator implements Iterador<T> {
		private Node current = null;
		private Node previous = null;
		
		
		@Override
		public boolean hasNext() {
			if (current == null)
				return head != null;
			return current.next != null;
		}
		@Override
		public T next() {
			if (!hasNext())
				throw new IllegalStateException("Sem next!");
			
			if (current == null) {
				current = head;
			} else {
				previous = current;
				current = current.next;
			}
			return current.data;
		}
		@Override
		public void remove() {
			if (current == null) {
				throw new IllegalStateException("Use next()!");
			}
			Node next = current.next;
			
			if (previous == null) {
				head = next;
			} else {
				previous.next = next;
			}
			if (next == null) {
				tail = previous;
			}
		}
		@Override
		public void append(T dado) {
			if (current == null) {
				throw new IllegalStateException("Use next()!");
			}
			
			Node node = new Node(dado);
			Node next = current.next;
			
			node.next = next;
			current.next = node;
			
			if (current == tail) {
				tail = node;
			}
		}
		@Override
		public void insert(T dado) {
			if (current == null) {
				throw new IllegalStateException("Use next()!");
			}
			
			Node node = new Node(dado);
			
			node.next = current;
			if (previous != null)
				previous.next = node;
			else {
				head = node;
			}
		}
	}
	
	private Node head;
	private Node tail;

	void append(T value) {
		Node novo = new Node(value);
		if (tail != null)
		{
			novo.previous = tail;
			tail.next = novo;			
		}
		else			
			head = novo;
		tail = novo;
	}

	void pushFront(T value) {
		Node novo = new Node(value);		
		novo.next = head;
		if (head == null)
			tail = novo;
		else
			head.previous = novo;
		head = novo;
	}
	
	public Iterador<T> iterator() {
		return new ListIterator();
	}
}






