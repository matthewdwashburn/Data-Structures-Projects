package datafinal;

import datafinal.Task;

/**
 * COS212 Final Project
 * 
 * Task will hold the minimum heap 
 * 
 * @author Matthew Washburn
 * @version 1
 *
 */

public class Task implements Comparable<Task> {
	//---------------Attributes--------------
	private String description;
	private int priority;
	
	//--------------Constructors--------------
	public Task(String description, int priority) {
		this.description = description;
		this.priority = priority;
	}
	
	public Task() {
		// empty constructor 
	}

	//--------------Getters--------------
	public String getDescription() {
		return description;
	}
	
	public int getPriority() {
		return priority;
	}
	
	
	//--------------Other Methods--------------
	@Override
	public int compareTo(Task other) {
		return Integer.compare(this.priority, other.priority);
	}

	//--------------MinHeap Class--------------
	public static class BinaryMinHeap<T extends Comparable<? super T>> {
		//--------------Attributes--------------
		// The actual heap storage
		private Task[] heap;

		// Number of items in the heap, doubles as index of next open spot in array
		public int size;

		//--------------Constructors--------------
		/**
		 * Constructor
		 * 
		 * @param capacity max number of items this heap can hold
		 */
		public BinaryMinHeap(int capacity) {
			heap = new Task[capacity];
			size = 0;
		}

		/**
		 * Constructor Note this uses the supplied array as our heap storage, not a copy
		 * 
		 * @param array Turn this array into a minHeap
		 */
		public BinaryMinHeap(Task[] array) {
			buildHeap(array);
		}

		//--------------Methods--------------
		public void insertArray(Task[] array) {
			buildHeap(array);
		}
		
		/**
		 * 
		 * @return true if there are no items in the heap, false otherwise
		 */
		public boolean isEmpty() {
			return size == 0;
		}

		/**
		 * @return the minimum item in this heap
		 * @throws IllegalStateException if the heap is empty
		 */
		public Task findMin() throws IllegalStateException {
			if (isEmpty()) {
				throw new IllegalStateException("Empty heap has no min");
			}
			return heap[0];
		}

		/**
		 * @param the priority that is being checked
		 * @return true if the priority is in the heap
		 */
		public boolean isPriortyPresent(int pri) {
			for (int i = 0; i < size; i++) {
				if (heap[i].priority == pri) {
					return true;
				}
			}
			return false;
		}
		/**
		 * Insert the specified item into the heap and percolate as needed
		 * 
		 * @param item the new element to place in the heap
		 */
		public void insert(Task item) {
			int parent;
			int curIndex = size;
			size ++;
			parent = (curIndex - 1) / 2;
			heap[curIndex] = item;
			percUp(curIndex, parent);
		}
		
		/**
		 * Percolate the item at the specified index as far as appropriate bottem up
		 * 
		 * @param curIndex the current index 
		 * @param parent the parent of the node that is currently looked at
		 */
		private void percUp(int curIndex, int parent) {
			while (curIndex != 0 && heap[curIndex].getPriority() < heap[parent].getPriority()) {
				Task temp = heap[curIndex];
				heap[curIndex] = heap[parent];
				heap[parent] = temp;
				curIndex = parent;
				parent = (curIndex - 1) / 2;
			}
			
		}

		/**
		 * remove the minimum item in the heap
		 * 
		 * @return the former minimum item in the heap
		 * @throws IllegalStateException if the heap is empty
		 */
		public Task deleteMin() throws IllegalStateException {
			if (isEmpty()) {
				throw new IllegalStateException("Empty heap has no min");
			}
			Task maxitem = heap[0];
			heap[0] = heap[size-1];
			size--;
			if (size > 1) percDown(0);
			return maxitem;
		}
		
		/**
		 * remove the first instance of a priority in the heap
		 * 
		 * @throws IllegalStateException if the heap is empty
		 */
		public void deletePriority(int priority) throws IllegalStateException {
			if (isEmpty()) {
				throw new IllegalStateException("Empty heap has no element with " + priority + " priority");
			}
			//System.out.println(size);
			for (int i = 0; i < size; i++) {
				if (heap[i].priority == priority) {
					heap[i] = heap[size-1];
					size--;
					if (size > 1) percDown(0);
					
					return;
				}
			}
		}
		
		/**
		 * Percolate the item at the specified index as far as appropriate top down
		 * 
		 * @param curIndex index of item that needs to be percolated down
		 */
		private void percDown(int curIndex) {
			int swapIndex;
			int left, right;
			left = curIndex * 2 + 1;
			right = curIndex * 2 + 2;
			while (heap[left] != null && heap[right] != null) {
				
				if(left >= size){
					break;
				}
				swapIndex = curIndex;
				
				if(heap[left].getPriority() < heap[curIndex].getPriority()) {
					swapIndex = left;
				}
				if (right < size) {
					if(heap[right].getPriority() < heap[swapIndex].getPriority()) {
						swapIndex = right;
					}
				}
				
				if (swapIndex == curIndex) {break;}
				
				Task temp = heap[curIndex];
				heap[curIndex] = heap[swapIndex];
				heap[swapIndex] = temp;
			}
		}

		/**
		 * Turn an unsorted array into a minHeap. Note that incoming array becomes this
		 * heap, so no copy/clone is produced. We alter the original.
		 * 
		 * @param array the array of values we want to turn into a heap
		 */
		public void buildHeap(Task[] array) {
			heap = array;
			int j = 0;
			while (heap[j] != null) {
				j++;
			}
			size = j;

			for (int i = (size -1 -1)/2; i >=0; i--) {
				percDown(i);
			}
		}

		/**
		 * Perform in-place heapsort algorithm
		 * 
		 * @param array the array you wish to sort
		 */
		public static <Type extends Comparable<? super Type>> void heapSort(Task[] array) {
			BinaryMinHeap<Type> bmh = new BinaryMinHeap<Type>(array);
			for(int i = array.length -1; i > 0; i--) {
				array[i] = bmh.deleteMin();
				}
		}
		
	}//BinaryMinHeap
	
	public String toString() {
		return priority + "\n" + description;
	}
}//Task