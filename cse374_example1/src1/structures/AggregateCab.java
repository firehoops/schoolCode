package structures;

import java.util.ArrayList;

public class AggregateCab implements Cab{
	private int numOfPassengers;
	private int numOfSeats;
	private int groupNum;
	private ArrayList<Person> personArray;

	
	public AggregateCab(int numberOfSeats) {
		this.numOfSeats = numberOfSeats;
		numOfPassengers = 0;
		this.personArray = new ArrayList<Person>();
	}
	public AggregateCab(int numberOfSeats, int num) {
		this.numOfSeats = numberOfSeats;
		numOfPassengers = 0; 
		this.groupNum = num; 
	}

	@Override
	public boolean isFull() {
		if(numOfPassengers == numOfSeats) {
			return true; 
		}
		else {
			return false; 
		}
	}

	@Override
	public boolean addPassenger(Person p) {
		numOfPassengers++;
		personArray.add(p);
		
		return true;
	}
}
