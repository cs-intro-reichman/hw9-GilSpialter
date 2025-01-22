/**
 * Represents a list of Nodes. 
 */
public class LinkedList {
	
	private Node first; // pointer to the first element of this list
	private Node last;  // pointer to the last element of this list
	private int size;   // number of elements in this list
	
	/**
	 * Constructs a new list.
	 */ 
	public LinkedList () {
		first = null;
		last = first;
		size = 0;
	}
	
	/**
	 * Gets the first node of the list
	 * @return The first node of the list.
	 */		
	public Node getFirst() {
		return this.first;
	}

	/**
	 * Gets the last node of the list
	 * @return The last node of the list.
	 */		
	public Node getLast() {
		return this.last;
	}
	
	/**
	 * Gets the current size of the list
	 * @return The size of the list.
	 */		
	public int getSize() {
		return this.size;
	}
	
	/**
	 * Gets the node located at the given index in this list. 
	 * 
	 * @param index
	 *        the index of the node to retrieve, between 0 and size
	 * @throws IllegalArgumentException
	 *         if index is negative or greater than the list's size
	 * @return the node at the given index
	 */		
	public Node getNode(int index) {
		if (index < 0 || index > size) {
			throw new IllegalArgumentException(
					"index must be between 0 and size");
		}
		Node n = first;
		for (int i = 0; i < index; i++) {
			n = n.next;
		}
		return n;
	}
	
	/**
	 * Creates a new Node object that points to the given memory block, 
	 * and inserts the node at the given index in this list.
	 * <p>
	 * If the given index is 0, the new node becomes the first node in this list.
	 * <p>
	 * If the given index equals the list's size, the new node becomes the last 
	 * node in this list.
     * <p>
	 * The method implementation is optimized, as follows: if the given 
	 * index is either 0 or the list's size, the addition time is O(1). 
	 * 
	 * @param block
	 *        the memory block to be inserted into the list
	 * @param index
	 *        the index before which the memory block should be inserted
	 * @throws IllegalArgumentException
	 *         if index is negative or greater than the list's size
	 */
	public void add(int index, MemoryBlock block) {
		if (index < 0 || index > size) {
			throw new IllegalArgumentException("index must be between 0 and size");
		}
		Node n = new Node (block);

		if (index == 0) {
			addFirst(block);
		}
		else if (index == this.size) {
			addLast(block);
		}
		else {
			Node before = getNode(index-1);
			n.next = before.next;
			before.next = n;
			size ++;
		}
	}

	/**
	 * Creates a new node that points to the given memory block, and adds it
	 * to the end of this list (the node will become the list's last element).
	 * 
	 * @param block
	 *        the given memory block
	 */
	public void addLast(MemoryBlock block) {
		Node n = new Node (block);
		if (first == null) {
			first = n;
			last = first;
			size ++;
		}
		else {
			last.next = n;
			last = n;
			size ++;
		}
	}
	
	/**
	 * Creates a new node that points to the given memory block, and adds it 
	 * to the beginning of this list (the node will become the list's first element).
	 * 
	 * @param block
	 *        the given memory block
	 */
	public void addFirst(MemoryBlock block) {
		Node n = new Node (block);
		if (first == null) {
			first = n;
			last = first;
			size ++;
		}
		else {
			n.next = first;
			first = n;
			size ++;
		}
	}

	/**
	 * Gets the memory block located at the given index in this list.
	 * 
	 * @param index
	 *        the index of the retrieved memory block
	 * @return the memory block at the given index
	 * @throws IllegalArgumentException
	 *         if index is negative or greater than or equal to size
	 */
	public MemoryBlock getBlock(int index) {
		if (index < 0 || index > size || size == 0) {
			throw new IllegalArgumentException ("index must be between 0 and size");
		}
		else {
		Node n = getNode(index);
		return n.block;
		}
	}	

	/**
	 * Gets the index of the node pointing to the given memory block.
	 * 
	 * @param block
	 *        the given memory block
	 * @return the index of the block, or -1 if the block is not in this list
	 */
	public int indexOf(MemoryBlock block) {
		Node pointer = first;
		int index = 0;
		if (first == null) {
			return -1;
		}
		if (first.block.equals(block)) {
			return 0;
		}
		while (pointer.next != null && !pointer.next.block.equals(block)) {
			pointer = pointer.next;
			index ++;
		}
		if (pointer.next == null)  {
			return -1; }
		else return index+1;
	}

	/**
	 * Removes the given node from this list.	
	 * 
	 * @param node
	 *        the node that will be removed from this list
	 */
	public void remove(Node node) {
	
		if (node == null) {
			throw new NullPointerException("Node cannot be null.");
		}
		if (first == null) { //size = 0
			throw new IllegalStateException("Cannot remove from an empty list.");
		}

		if (size == 1 && first == node) {	//only 1 element --> take it out
			first = null;
			last = null;
		} 
		else { 
			if (first == node) {									//removing first node
				first = first.next;
			}
			else if (last == node) {								//removing last node
				last = getNode(size - 2);
				last.next = null;
			}
			else {													//removing a middle node
				Node pointer = first;
				while (pointer.next != null && pointer.next != node) {
					pointer = pointer.next;
				}
				if (pointer.next == null) {
					throw new IllegalArgumentException ( "Node not in list." );
				}
				//therefore we want to remove - pointer.next 
				pointer.next = node.next;
				}
		}
		size--;
		}

	/**
	 * Removes from this list the node which is located at the given index.
	 * 
	 * @param index the location of the node that has to be removed.
	 * @throws IllegalArgumentException
	 *         if index is negative or greater than or equal to size
	 */
	public void remove(int index) {
		if (index > size || index < 0) {
			throw new IllegalArgumentException("index must be between 0 and size." );
		}
		remove(getNode(index));
	}

	/**
	 * Removes from this list the node pointing to the given memory block.
	 * 
	 * @param block the memory block that should be removed from the list
	 * @throws IllegalArgumentException
	 * 
	 *         if the given memory block is not in this list
	 */
	public void remove(MemoryBlock block) {
		if (block == null) {
			throw new IllegalArgumentException("index must be between 0 and size" );
		}
		int index = indexOf(block);
		if (index == -1) {
			throw new IllegalArgumentException("index must be between 0 and size" );
		}
		else remove(index);
	}	

	/**
	 * Returns an iterator over this list, starting with the first element.
	 */
	public ListIterator iterator(){
		return new ListIterator(first);
	}
	
	/**
	 * A textual representation of this list, for debugging.
	 */
	public String toString() {
		String s = "";
		if (size > 0)   
			s += getBlock(0).toString();
		for (int i = 1; i < size; i++)
			s += getBlock(i).toString();
		return s;
	}
}