package structures;

public class BaggyCab implements Cab{
	ConstrainedCapacityBag<Person> bag;
	private String name; 
	
	public BaggyCab(String name,int capacity) {
		this.bag = new ConstrainedCapacityBag<>(capacity);
		this.name = name; 
	}
	
	public BaggyCab(int capacity) {
		this.bag = new ConstrainedCapacityBag<>(capacity);

	}

	public int getBagCapicity(){
		return bag.getCapacity(); 
	}
	public String getName() {
		return name;
	}
	
	@Override
	public boolean isFull() {
		if(bag.isFull() == true) {
			System.out.println("baggy cab is full");
			return true; 
		}
		else {
			return false;
		}
	}

	@Override
	public boolean addPassenger(Person p) {
		if(isFull() == true) {
			return false;
		}
		bag.add(p);
		
		return true;
	}

}
