package structures;

import java.util.Random;

public class Person {
	private String name;
	private int groupNum;
	private boolean isSick;
	
	public Person() {
		Random random = new Random();
		
		if(random.nextDouble() <= .1) {
			this.isSick =true;
		}
		else {
			this.isSick = false;
		}
	}
	public Person(String name) {
		this.name = name;
		Random random = new Random();
		
		if(random.nextDouble() <= .1) {
			this.isSick =true;
		}
		else {
			this.isSick = false;
		}
	}
	public Person(String name, int groupNum) {
		Random random = new Random();
		
		if(random.nextDouble() <= .1) {
			this.isSick =true;
		}
		else {
			this.isSick = false;
		}
		this.groupNum = groupNum; 
		this.name = name;
	}
	public int getGroupNum() {
		return groupNum; 
	}
	public String getName() {
		return name; 
	}

	public String toString() {
		return name; 
	}
	public boolean isSick() {
		if(this.isSick == true) {
			return true;
		}
		else return false;
	}
	public void makeSick() {
		this.isSick = true; 
	}
}
