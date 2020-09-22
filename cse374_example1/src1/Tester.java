import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.Random;

import com.github.javafaker.Faker;

import structures.AggregateCab;
import structures.BaggyCab;
import structures.Bus;
import structures.Cab;
import structures.IndividualsCab;
import structures.Person;

public class Tester {
	
	public static void testBaggyCab() {
		BaggyCab bag = new BaggyCab(3);
		bag.addPassenger(new Person("ty")); 
		System.out.println("Baggy Cab has one passenger isFull should be False , actual: " + bag.isFull());
		bag.addPassenger(new Person("ty1")); 
		bag.addPassenger(new Person("ty2")); 
		System.out.println("Baggy Cab has 3 passenger isFull should be true , actual: " + bag.isFull());
	}
	public static void testAggCab() {
		Cab aggregate = new AggregateCab(3); //creates a cab with three available seats
		aggregate.addPassenger(new Person("Vijay"));
		aggregate.addPassenger(new Person("Paula"));
		aggregate.addPassenger(new Person("Brad"));
		if(aggregate.isFull()){ 
			System.out.println("Agg Cab shuold be full so should be true, actual: " + aggregate.isFull()); 
			}  //works
	}
	//store people in a bus
	//tested does infect people who sit next to each other
	public static int storeBus(List<Person> p, Bus b) {
		
		int personCounter = 0; 
		ArrayList<Person> tester = new ArrayList<Person>();
		while(!p.isEmpty() || b.isFull() != true) { // while people need to get on bus
			//current person can get into current cab
			b.addPassenger(p.get(0));
			personCounter++;
			tester.add(p.get(0));
			p.remove(0);
		}
		// loop through people on bus and see if the sickness spread to people next to each other
		for(Person person: tester) {
			System.out.println(person.isSick());
		}
		return 0;
		}
	//checking if adding people to bus spreads sickness
	//tested does infect people who sit next to each other
	public static void test3() {
		//Create cabs
		Bus bus = new Bus(12);
	 
		//add people to list so we can store them in cab
		List<Person> personList = new ArrayList();
		Faker faker = new Faker();
		for(int i =0; i<12; i++) {
			personList.add(new Person(faker.name().fullName()));
//			System.out.println(personList.get(i) + " " + i);  // checking everyone got added properly
		}
		//while there are still people add them to cabs 
		while(!personList.isEmpty()) {
			if(storeBus(personList, bus) == 0){
				System.out.println("Bus is full"); 
			}
		}
	}
	//store people in a bus
		//tested does infect people who sit next to each other
	public static int storeIndCab(List<Person> p, IndividualsCab c) {
		
		while(!p.isEmpty()) { // while people need cabs
			//current person can get into current cab
			c.addPassenger(p.get(0));
			//remove person from list and remove person from cab since they would have been dropped off
			c.remove(p.get(0));
			p.remove(0);	
		}
		for (Person person : c.getCustomerTracker()) {
			System.out.println("sick people " + person.isSick());
		}
		return 0;
		}
		//checking if adding people to individuals cab spreads sickness
		// TODO did it work
		public static void test4() {
			
			//add people to list so we can store them in cab
			IndividualsCab cab = new IndividualsCab();
			List<Person> personList = new ArrayList<Person>();
			Faker faker = new Faker();
			for(int i =0; i<10; i++) {
				personList.add(new Person(faker.name().fullName()));
//				System.out.println(personList.get(i) + " " + i);  // checking everyone got added properly
			}
			//while there are still people add them to cabs 
			while(!personList.isEmpty()) {
				if(storeIndCab(personList, cab) == 0){
					System.out.println("Cab took all passengers"); 
				}
			}
		}
	
}
