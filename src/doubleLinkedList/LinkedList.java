package doubleLinkedList;

class LinkedList {

	Node start;
	Node end;
	int size;

	public LinkedList() {
		this.start = null;
		this.end = null;
		this.size = 0;
	}

	public boolean isEmpty() {
		return start == null;
	}

	public int getSize() {
		return size;
	}

	public void insertAtStart(int val) {
		Node node = new Node(val, null, null);
		if (isEmpty()) {
			start = node;
			end = start;
		} else {
			start.setPrev(node);
			node.setNext(start);
			start = node;
		}
		size++;
	}

	public void insertAtEnd(int val) {
		Node node = new Node(val, null, null);
		if (isEmpty()) {
			start = node;
			end = start;
		} else {
			node.setPrev(end);
			end.setNext(node);
			end = node;
		}
		size++;
	}

	public void insertAtPos(int val, int pos) {
		Node nptr = new Node(val, null, null);
		if (pos == 1) {
			insertAtStart(val);
			return;
		}
		Node node = start;
		for (int i = 2; i <= size; i++) {
			if (i == pos) {
				Node temp = node.getNext();
				node.setNext(nptr);
				nptr.setPrev(node);

				nptr.setNext(temp);
				temp.setPrev(nptr);
			}
			node = node.getNext();
		}
		size++;
	}

	public void deleteAtPos(int pos) {
		if (pos == 1) {
			if (size == 1) {
				start = null;
				end = null;
				size = 0;
				return;
			}
			start = start.getNext();
			start.setPrev(null);
			size--;
			return;
		}
		if (pos == size) {
			end = end.getPrev();
			end.setNext(null);
			size--;
			return;
		}
		Node ptr = start.getNext();
		for (int i = 2; i <= size; i++) {
			if (i == pos) {
				Node p = ptr.getPrev();
				Node n = ptr.getNext();

				p.setNext(n);
				n.setPrev(p);
				size--;
				return;

			}
			ptr = ptr.getNext();
		}

	}

	public void display() {
		System.out.println("Displaying LinkedList=======>");
		if (size == 0) {
			System.out.println("Empty List");
			return;
		}
		if (start.getNext() == null) {
			System.out.println(start.getData());
			return;
		}
		Node ptr = start;
		System.out.print(start.getData() + " <-> ");
		ptr = start.getNext();
		while (ptr.getNext() != null) {
			System.out.print(ptr.getData() + " <-> ");
			ptr = ptr.getNext();
		}
		System.out.print(ptr.getData() + "\n");
	}
}
