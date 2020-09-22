package structures;

import java.awt.Container;
import java.util.ArrayList;

public class IndividualsCab implements Cab{
	private Person person; 
	private boolean full;
	private boolean contaminated;
	private ArrayList<Person> customerTracker;
	private int index;
	
	public IndividualsCab() {
		this.person = new Person(); 
		this.full = true;
		this.contaminated = false;
		customerTracker = new ArrayList<Person>();
		this.index= 0;
	}
	public ArrayList<Person> getCustomerTracker(){
		return customerTracker; 
	}
	@Override
	public boolean isFull() {
		if(full) {
			return true;
		}
		return false;
	}
	@Override
	public boolean addPassenger(Person p) {
		if(isFull()) return false;
		//anyone who gets in this cab after someone who is sick will get sick
		//check if first person is sick if they were then contaminate cab else continue
		if(this.contaminated == false) {
			if(person.isSick()) {
				this.contaminated = true; 
			}
		}
		//if contaminated and person is not already sick
		if(this.contaminated){
			if(person.isSick() == false) {
				person.makeSick();
			} 
		}
		customerTracker.add(p);
		index++;
		full = true;  
		return true;
	}
	public void remove(Person p) {
		if(full == true) {
			full = false;
		}
		else System.out.println("There is no one in cab");
	}

}
