

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Random;

import com.github.javafaker.Faker;

import structures.AggregateCab;
import structures.BaggyCab;
import structures.Bus;
import structures.Cab;
import structures.Person;

public class main{
public static void main(String[] args) {
	Person p  = new Person("Ty");
	Tester test = new Tester();
	
	//testing BaggyCab,AggregateCab,and FastStore
	test.testAggCab();
	test.testBaggyCab();
	
	System.out.println("\n---------------------------------------------------------------------------\n");


	System.out.println("Test 1 everyone gets a cab");
	//test1();  //works
	System.out.println("\n---------------------------------------------------------------------------\n");

	System.out.println("Test 2 not everyone gets a cab");
	//test2();
	System.out.println("\n---------------------------------------------------------------------------\n");

	System.out.println("Test 3 spreading sickness on a bus");
	test.test3(); //sick bus test -- does work
	System.out.println("\n---------------------------------------------------------------------------\n");

	System.out.println("Test 4 spreading sickness on a cab");
	test.test4();
	System.out.println("\n---------------------------------------------------------------------------\n");

	System.out.println("Test 5 testing fast store");
	testFastStore();
	System.out.println("\n---------------------------------------------------------------------------\n");

	System.out.println("Test 6 testing group store");
	testStoreGroup();
 }

public static int storeGroupPeople(Queue<Person> p, Queue<BaggyCab> c) {
	 int currentGroupNum = p.peek().getGroupNum();
	 int cabCapacity = c.peek().getBagCapicity(); 
	
	 //store everyone in a dictionary with corresponding number
	 List<String> nameArr = new ArrayList();
	 
	 HashMap<Integer,List<String>> map = new HashMap<>();
	 if(p.isEmpty() || c.isEmpty()) return -1;
	 int counter = 0;
	 int size = p.size(); //was getting null pointer at last person in queue so this a temporary fix doing counter to handle last person
	 while(!p.isEmpty()) {
		 if(counter == size-1) {
			 currentGroupNum = p.peek().getGroupNum();
			 nameArr.add(p.peek().getName());
			 map.put(currentGroupNum,nameArr);
			 break;
		 }
		 //check current person groupnumber and add them to array
		 while(currentGroupNum == p.peek().getGroupNum()) {
			 nameArr.add(p.remove().getName());
			 counter++;
		 }
		 //map are array of people to key as the groupNumber, then update new groupNum and clear array of names
		 map.put(currentGroupNum,nameArr);
		 currentGroupNum = p.peek().getGroupNum();
		 nameArr = new ArrayList();
	 }
	 //we have array of people stored to corresponding group number
	 //add people to cab based on group size
	 //check all sizes of group numbers and store largest group in largest cab capacity
	 
	 //loop check biggest group add and then add to largest cab then remove those from 
	 if(getBiggestGroup(map, c) == 0) {
		 System.out.println("everyone got cab");
	 }
	 else System.out.println("not everyone got a cab in their group");

	 return 0;
	 
	
}
public static int getBiggestGroup(HashMap<Integer,List<String>> map,Queue<BaggyCab> c) {
	//store sizes in array
	Integer[] sizes = new Integer[map.size()+1];
	sizes[0] = 0;
	for(int i = 1; i<map.size()+1; i++) {
		sizes[i] = map.get(i).size();
		System.out.println(map.get(i).size());
	}
	//sorted largest to smallest
	Arrays.sort(sizes, Collections.reverseOrder());
	System.out.println(Arrays.toString(sizes));
	//checking for current largest list
	int currentBig= 0;
	//we know are current biggest if cabs capcity is < currentBig then try another group in that cab. If cab capacity is >  add biggest group
	 for(int i = 0; i<map.size(); i++) {
		 currentBig = sizes[i];
		 int cap = c.peek().getBagCapicity();
		
		  if(cap < currentBig) {
			  for(int j = i+1; i <sizes.length; j++) {
				  if(cap < sizes[j] && j+1 < sizes.length) {
					  continue; 
				  }
				  else {
					  //found a cab now loop through map and store the list in
					  for(int k = 0; k<sizes[j]; k++) {
						  c.peek().addPassenger(new Person(map.get(j).get(k)));
						  
					  }
					  c.remove();
				  }
			  }
		  }
		  else {
			  
			  for(int k = 0; k<currentBig; k++) {
				  c.peek().addPassenger(new Person(map.get(i+1).get(k)));
			  }
			  c.remove();
		  }
	 }
	return 0; 
}

public static void testStoreGroup() {
	Queue<Person> personQ = new LinkedList<Person>();
	Queue<BaggyCab> cabQ = new LinkedList<BaggyCab>();
	
	cabQ.add(new BaggyCab("Booze Waggon", 12));
	cabQ.add(new BaggyCab("Taxi", 4));
	cabQ.add(new BaggyCab("carriage", 2));
	
//	personQ.add(new Person("bob", 1));
//	personQ.add(new Person("ty", 1));
//	personQ.add(new Person("j", 2));
	
	Faker faker = new Faker();
	personQ.add(new Person(faker.name().fullName(), 1));
	personQ.add(new Person(faker.name().fullName(), 1));
	personQ.add(new Person(faker.name().fullName(), 1));
	personQ.add(new Person(faker.name().fullName(), 1));

	personQ.add(new Person(faker.name().fullName(), 2));
	personQ.add(new Person(faker.name().fullName(), 2));
	personQ.add(new Person(faker.name().fullName(), 2));

	personQ.add(new Person(faker.name().fullName(), 3));
	
	storeGroupPeople(personQ,cabQ);
//		if(storeGroupPeople(personQ, cabQ) == 0){
//			System.out.println("Everyone got a cab"); 
//		}
//		else {
//			System.out.println("No more cabs");
//		}	
}
public static int storePeople(List<Person> p, List<Cab> c) {
	int cabNum = 0;
	int personCounter = 0; 
	
	while(!p.isEmpty()) { // while people need cabs
		// no more cabs
		if(c.isEmpty()) {
			return -1;			 
		}
		if(c.get(0).isFull()) { //if current cab is full
			cabNum++;
			c.remove(0);
			if(c.isEmpty()) {
				System.out.println("\nNo more cabs the people stranded are\n ");
				
				p.forEach(name ->{
		            System.out.println(name);
		        });
				return p.size();
			}
		}
		//current person can get into current cab
		c.get(0).addPassenger(p.get(0));
		System.out.println("Cab number: " + cabNum + " is taking person number: " + personCounter + " and are they sick: " + p.get(0).isSick());
		personCounter++; 
		p.remove(0);
		
	}
	return 0;
	}
public static int fastStorePeople(Queue<Person> personQ , Queue<Cab> cabQ) {
	//people still in q
	while(!personQ.isEmpty()) {
		if(cabQ.isEmpty()) {
			return -1;
		}
		//if all cabs are full 
		if(cabQ.peek().isFull()) {
			cabQ.remove();
			if(cabQ.isEmpty()) {
				System.out.println("\nNo more cabs the people stranded are\n ");
				
				personQ.forEach(name ->{
		            System.out.println(name);
		        });
				return personQ.size();
			}
		}
		//grab first available cab then add popped passenger
		cabQ.peek().addPassenger(personQ.remove());
	}
	return 0;
}
public static void testFastStore() {
	Queue<Person> personQ = new LinkedList<Person>();
	Queue<Cab> cabQ = new LinkedList<Cab>();
	
	int cabSeats = 0;
	//add cabs until 20 seats total
	while(cabSeats < 20) {
		//randomize seat 3 or 5
		int randomSeat = new Random().nextBoolean() ? 3 : 5;
		cabSeats += randomSeat;
		cabQ.add(new AggregateCab(randomSeat));
	}
	
	//add people to queue
	Faker faker = new Faker();
	for(int i =0; i<10; i++) {
		personQ.add(new Person(faker.name().fullName()));
//		System.out.println(personList.get(i) + " " + i);  // checking everyone got added properly
	}
	System.out.println("number of cabs " + cabQ.size() + " number of people " + personQ.size());
	//while there are still people add them to cabs
	//@TODO works until cabs run out. 
	while(!personQ.isEmpty()) {
		if(fastStorePeople(personQ, cabQ) == 0){
			System.out.println("Everyone got a cab"); 
		}
		else {
			System.out.println("No more cabs");
		}
	}
		
}
//checking if a aggregate cab stores all people
public static void test1() {
	//Create cabs
	List<Cab> cabList = new ArrayList();
	int cabSeats = 0;
	while(cabSeats < 50) {
		//randomize seat 3 or 5
		int randomSeat = new Random().nextBoolean() ? 3 : 5;
		cabSeats += randomSeat;
		cabList.add(new AggregateCab(randomSeat)); 
	}
	//add people to list so we can store them in cab
	List<Person> personList = new ArrayList();
	Faker faker = new Faker();
	for(int i =0; i<50; i++) {
		personList.add(new Person(faker.name().fullName()));
//		System.out.println(personList.get(i) + " " + i);  // checking everyone got added properly
	}
	//while there are still people add them to cabs 
	while(!personList.isEmpty()) {
		if(storePeople(personList, cabList) == 0){
			System.out.println("Everyone got a cab"); 
		}
	}
}
//checking if aggregate cab handles not having enough cabs
public static void test2() {
	//Create cabs
		List<Cab> cabList = new ArrayList();
		int cabSeats = 0;
		while(cabSeats < 42) {
			//randomize seat 3 or 5
			int randomSeat = new Random().nextBoolean() ? 3 : 5;
			cabSeats += randomSeat;
			cabList.add(new AggregateCab(randomSeat)); 
		}
	//add people to list so we can store them in cab
	List<Person> personList = new ArrayList();
	
	Faker faker = new Faker();
	for(int i =0; i<50; i++) {
		personList.add(new Person(faker.name().fullName()));
		System.out.println(personList.get(i) + " " + i);  // checking everyone got added properly
	}
	//while there are still people add them to cabs 
	while(!personList.isEmpty()) {
		if(storePeople(personList, cabList) == 0){
			System.out.println("Everyone got a cab"); 
		}
	}
}



}

