import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		try {
			Scanner in = new Scanner(System.in);
			System.out.print("Enter Card Numbers To Test: ");
			String inputLine = in.nextLine();
			in.close();
			String[] cardNumbers = inputLine.split(" ");
			
			System.out.println("\nUsing No Threads: ");
			long noThreadStartTime = System.nanoTime();
			for(String cardNumber: cardNumbers) {
				System.out.println(verifyNumbersWithoutThreads(cardNumber));
			}
			long noThreadElapsedTime = System.nanoTime() - noThreadStartTime;
			System.out.println("Time taken: " + noThreadElapsedTime);
			
			System.out.println("\nUsing Threads: ");
			long threadStartTime = System.nanoTime();
			for(String cardNumber: cardNumbers) {
				System.out.println(verifyNumberUsingThreads(cardNumber));
			}
			long threadElapsedTime = System.nanoTime() - threadStartTime;
			System.out.println("Time taken: " + threadElapsedTime);
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static String verifyNumbersWithoutThreads(String cardNum) {
		String validString = "";
		boolean[] testResults = new boolean[2];
		int[] calcResults = new int[2];
		
		testResults[0] = HelperMethods.verifyLength(cardNum);
		testResults[1] = HelperMethods.verifyStartingNumber(cardNum);
		calcResults[0] = HelperMethods.calc1(cardNum);
		calcResults[1] = HelperMethods.calc2(cardNum);
		if(testResults[0] == true && testResults[1] == true && (calcResults[0] + calcResults[1]) % 10 == 0) {
			validString = "The number " + cardNum + " is valid";
		}
		else {
			validString = "The number " + cardNum + " is invalid";
		}
		
		return validString;
	}
	
	public static String verifyNumberUsingThreads(String cardNum) throws InterruptedException{
		String validString = "";
		boolean[] testResults = new boolean[2];
		int[] calcResults = new int[2];
		
		Thread[] threads = new Thread[4];
		
		threads[0] = new Thread(() -> {
			testResults[0] = HelperMethods.verifyLength(cardNum);
		});
		threads[0].start();
		threads[1] = new Thread(() -> {
			testResults[1] = HelperMethods.verifyStartingNumber(cardNum);
		});
		threads[1].start();
		threads[2] = new Thread(() -> {
			calcResults[0] = HelperMethods.calc1(cardNum);
		});
		threads[2].start();
		threads[3] = new Thread(() -> {
			calcResults[1] = HelperMethods.calc2(cardNum);
		});
		threads[3].start();
		
		for(Thread thread: threads) {
			thread.join();
		}
		
		if(testResults[0] == true && testResults[1] == true && (calcResults[0]+ calcResults[1]) % 10 == 0) {
			validString = "The number " + cardNum + " is valid";
		}
		else {
			validString = "The number " + cardNum + " is invalid";
		}
		
		return validString;
	}

}
