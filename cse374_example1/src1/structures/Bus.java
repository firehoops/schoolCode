package structures;

import java.util.ArrayList;

public class Bus implements Cab{
	private int numOfSeats; 
	private int rows;
	private ArrayList<Person> personArray; 

	public Bus(int numOfSeats) {
		this.numOfSeats = numOfSeats;
		this.rows = numOfSeats/3; 
		this.personArray = new ArrayList<Person>();

	}
	
	@Override
	public boolean isFull() {
		if(personArray.size() == numOfSeats) {
			return true;
		}
		return false; 
	}

	@Override
	public boolean addPassenger(Person p) {
		if(isFull()) {
			return false;
		}
		//making people sick based on if a even seat because the person directly behind them in array will be sitting next to them
		//if even seat check infect
		if(personArray.size() >=2 && personArray.size()%2 == 0) {
			//checking if both are already sick
			if(personArray.get(personArray.size()-1).isSick() && personArray.get(personArray.size()-2).isSick()) {
				personArray.add(p);
				return true;
			}
			//checking if person in the right seat will infect left person
			if(personArray.get(personArray.size()-1).isSick()) {
				personArray.get(personArray.size()-2).makeSick();
			}
			//check if person in left seat is sick then infect right seat
			else if(personArray.get(personArray.size()-2).isSick()) {
				personArray.get(personArray.size()-1).makeSick();
			}
			//neither sick continue
			else {
				personArray.add(p);
				return true;
			}
		}
		//if odd seat add to bus
		personArray.add(p);
		return true; 
	}

}
