package linkedlist;
/**
 * @author Matthew Washburn
 * @version 1
 */
public class SortedLinkedList<T extends Comparable<? super T>> {

	  private int size;
	  private ListNode<T> head; // Sentinel node
	  
private class ListNode<T> {
	T data;
	ListNode<T> next;
	public ListNode(T data) {
		this.data = data;
		this.next = null;
	}
}
// initializing constructor
public SortedLinkedList() {
    size = 0;
    head = new ListNode<T>(null); // Sentinel with null data
  }
public void addItem(T data) {
	// new and current node calls
	ListNode<T> newNode = new ListNode<>(data);
	ListNode<T> curNode = head; // sentinel node
	//find where the next node should be placed
	while(curNode.next != null && curNode.next.data.compareTo(data) < 0)
		curNode = curNode.next;
	//insert newNode between curNode and curNode.next
	newNode.next = curNode.next;
	curNode.next = newNode;
	//increase size
	size++;
}
public void deleteItem(T data) throws IllegalArgumentException {
	//set current node equal to sentinel
	ListNode<T> curNode = head;
	//look for matching data in the linked list
	while(curNode.next != null && curNode.next.data.equals(data)) {
		curNode = curNode.next;
	}
	// If data not found throw an exception
	if(curNode.next == null) {
		throw new IllegalArgumentException("Item not found!");
	}
	// link the previous node to the node after the current node to remove it
	curNode.next = curNode.next.next;
	//decrease size
	size--;
}

@Override
public String toString() {
// build the strings to output
  StringBuilder sb = new StringBuilder();
  sb.append("Size: ").append(size).append("\n");
// skip the sentinel and start from first node
  ListNode<T> curNode = head.next;
// counting nodes
  int index = 0;
// go through list and append each new value while not null
  while (curNode != null) {
    sb.append(index).append(": ");
    sb.append(curNode.data).append("\n");
    curNode = curNode.next;
    index++;
	}

  return sb.toString();
}


}


	