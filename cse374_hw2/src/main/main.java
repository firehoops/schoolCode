package main;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class main {
	
	public static void main(String[] args) {
		Random rand = new Random(); 
		
		List<Integer> arrL = new ArrayList<>();
		List<Integer> linkL = new LinkedList<>();

//		
//		for(int i =0; i < 1000000; i++) {
//			//check to make sure its working
//			//if(i/10000 == 0) System.out.println("1000");
//			L.add(i);
//		}
//		System.out.print(measureTime(L)); //works
		//System.out.println("Testing Array List");
		linearEvaluation("data1.txt", arrL); //testing how add works with ArrayList
		//System.out.println("\n---------------------------------------\n");
		System.out.println("Testing Linked List");
		linearEvaluation("data2.txt", linkL); // testing add with linkedlist

	}
	
	/*
	 * Calculating the sum from a list of integers
	 * Params: List of integers
	 * Returns: total sum of the list as an int
	 */
	public static int getSum(List<Integer> L) {
		 int total = 0;
		 for(int i=0; i<L.size(); i++)
		 total+=L.get(i);
		 return total;
		 }
	/*
	 * tracks how long in milliseconds to add all the integers to the list
	 * Params: List of integers
	 * returns: time it took in ms as a double
	 */
	public static double measureTime(List<Integer> L) {
		Stopwatch timer = new Stopwatch();
		getSum(L); 
		double timeElapsed = timer.elapsedTime();
		return timeElapsed; 
	}
	/*
	 * Evaluates different types of Lists starting from 10,000 integers up to 200,000 and increments by 10,000
	 * Runs 3 times and takes the mean of the 3 tests
	 */
	public static void linearEvaluation(String fileName, List<Integer> emptyList) { 
		try {
			BufferedWriter bw = new BufferedWriter(new FileWriter(fileName));
			System.out.println("N\tMeanTime");
			//loop through and start at 10000 in the list and increment by 10000
			for(int i =10000; i <= 200000; i += 10000) {
				double meanTime = 0;
				//loop through 3 times checking time
				for(int j =0; j<3; j++) {
					emptyList.clear(); // clearing list so can re add to get proper mean 
					
					//add 10000 at a time to the list
					for(int k = 0; k<i; k++) {
						emptyList.add(k);
					}
					meanTime += measureTime(emptyList);
				}//end of 3x loop print N and take the mean of the times
				bw.write(i + "\t" + roundDouble((meanTime/3.0),2));
				bw.newLine();
				System.out.println(i + "\t" + roundDouble((meanTime/3.0),2));

			}//end main for loop
			bw.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	/*
	 * Rounds a double to n amount of places
	 * Params: double num, the place you want to round to as an int
	 * Return: a double rounded to n places
	 */
	private static double roundDouble(double d, int roundTo) {
	    BigDecimal bigDecimal = new BigDecimal(Double.toString(d));
	    bigDecimal = bigDecimal.setScale(roundTo, RoundingMode.HALF_UP);
	    return bigDecimal.doubleValue();
	} 
}
