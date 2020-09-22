package main;

import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

public class LinkedMemoryList<E> implements List<E>{
	private Node<E> head;
    private Node<E> tail;
    private int size;
    
    //These variables are created specifically for your assignment:
    private Node<E> lastAccess;
    private int lastAccessIndex;

    /** Creates a new instance of LinkedList */
    public LinkedMemoryList() {
        head = null;
        tail = null;
        size = 0;
        //Initialized for your assignment:
        lastAccess=null;
        lastAccessIndex=-1;
    }
    
    public boolean add(E o){
        size++;
        if(head==null){
            head = new Node(o);
            //For your assignment. Now that the list finally has at least one element, we can initialize the memory:
            lastAccess=head;
            lastAccessIndex=0;
            return true;
        }        Node last = new Node(o);
        if(tail==null)
            head.next = last;
        else
            tail.next = last;
        tail = last;
        return true;
    }
 
    public void addMany(E...args){
        for(E o: args)
            add(o);
    } 

	@Override
	public boolean remove(Object o) {
		// TODO Auto-generated method stub
		return false;
	}
    
    public E remove(int i){
        if(i<0 || i>=size) return null;
        size--; // whatever happens, we decrease the size
        if(size==0){    //if we end up with nothing, we clean
            head = null;
            tail = null;
            lastAccess = null;
            lastAccessIndex = 0;
            return null;
        }        if(i==0){//if we only want to delete the first one
            head = head.next;
            return null;
        }
        //General case. Go to the node before what you want to delete
        Node previous = head;
        while(i!=1){
            previous = previous.next;
            i--;
        }
        if(previous.next==tail){    // delete the last one?
            tail = previous;    // then the tail is the one before
            tail.next = null;   // and there are no more neighbours after
        }
        else
            previous.next = previous.next.next; // jump, general case
        return null;
    }
    
    //if user wants element before most recent then go from begining
    //if element  after ith start from most recent
    public E get(int i){
    	if(i<0 || i>=size) return null;
        Node n = head;
//        if(i == size) { //checking if tail is the index
//        	lastAccess = tail; 
//        	lastAccessIndex = temp; 
//        	return (E) tail.data;
//        }
        //if position is before last recorded index start from beginning and move on
        if(i == lastAccessIndex) {
        	lastAccess = n; 
        	return (E) n.data; 
        } else if(i < lastAccessIndex) {
        	while(i!=0){
                n=n.next;
                i--;
            }
        	lastAccessIndex = i; 
        } else {
        	n = lastAccess;
        	while(i > lastAccessIndex) {
        		n = n.next;  
        		lastAccessIndex++; 
        	}
        	
        }
        lastAccess = n;  
        return (E) n.data;
    }
    
    public String toString(){
        String s = "";
        Node n = head;
        while(n!=null){
            s += n.toString();
            n = n.next;
        }
        return s;
    }
    
    private class Node<E>{
        public E data;
        public Node next;
        public Node(E o){
            data = o;
            next=null;
        }

        public String toString() {
            return data.toString();
        }

    }
    
	@Override
	public void clear() {
        head = null;
        tail = null;
        size = 0;
	}

	@Override
	public int size() {
		return size;
	}

	@Override
	public boolean isEmpty() {
		return(size==0);
	}
	
	/**********************************************************************************************************
	 * The following methods are left empty. They must be provided so the object can fit within a List ADT.
	 * They are not needed to complete this assignment. So you can ignore what comes after this.
	 **********************************************************************************************************/

	@Override
	public boolean contains(Object o) {
		throw new UnsupportedOperationException();
	}

	@Override
	public Iterator<E> iterator() {
		throw new UnsupportedOperationException();
	}

	@Override
	public Object[] toArray() {
		throw new UnsupportedOperationException();
	}

	@Override
	public <T> T[] toArray(T[] a) {
		throw new UnsupportedOperationException();
	}

	@Override
	public boolean containsAll(Collection<?> c) {
		throw new UnsupportedOperationException();
	}

	@Override
	public boolean addAll(Collection<? extends E> c) {
		throw new UnsupportedOperationException();
	}

	@Override
	public boolean addAll(int index, Collection<? extends E> c) {
		throw new UnsupportedOperationException();
	}

	@Override
	public boolean removeAll(Collection<?> c) {
		throw new UnsupportedOperationException();
	}

	@Override
	public boolean retainAll(Collection<?> c) {
		throw new UnsupportedOperationException();
	}

	@Override
	public E set(int index, E element) {
		throw new UnsupportedOperationException();
	}

	@Override
	public void add(int index, E element) {
		throw new UnsupportedOperationException();
	}

	@Override
	public int indexOf(Object o) {
		throw new UnsupportedOperationException();
	}

	@Override
	public int lastIndexOf(Object o) {
		throw new UnsupportedOperationException();
	}

	@Override
	public ListIterator<E> listIterator() {
		throw new UnsupportedOperationException();
	}

	@Override
	public ListIterator<E> listIterator(int index) {
		throw new UnsupportedOperationException();
	}

	@Override
	public List<E> subList(int fromIndex, int toIndex) {
		throw new UnsupportedOperationException();
	}
    
}

