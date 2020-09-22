package structures;



public class ConstrainedCapacityBag<Item>{
	private Node first; // first node in list
	private int capacity;
	private int count; 
	
	private class Node{
		Item item;
		Node next;				
	}
	public ConstrainedCapacityBag(int capacity){
		this.capacity = capacity;
		count = 0; 
	}
	public boolean isFull() {
		if(count < capacity) {
			return false;
		}
		else {
			return true;
		}
	}
	public int getCapacity() {
		return capacity; 
	}
	public void add(Item item) {
		// same as push() in Stack
		if(isFull() == true) {
			System.out.println("Bag is Full");
			return;
		}
		count++;
		Node oldfirst = first;
		first = new Node();
		first.item = item;
		first.next = oldfirst;
	}
}
