/*
 * Title:	  Word Statistics
 * 
 * Prompts the user to input a list of words separated by spaces, program will then give options
 * to run on the given list of words.
 */

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/**
 * Prompts the user to input a list of words separated by spaces, then does different functions on 
 * a list of Words.
 */
public class Project1 {

	public static void main(String[] args) {
		// Calls method to get word array
		String[] userWordArray = promptUser();
		
		Scanner scnr = new Scanner(System.in);
		boolean flag = true;
		
		do {
			// Print Menu
			System.out.println("\nPlease make a selection:");
			System.out.println("1) Display Word List Ordered A-Z");
			System.out.println("2) Display Length of each Word in the List");
			System.out.println("3) Display Word List Statistics");
			System.out.println("4) Count Number of Words with Even/Odd Length");
			System.out.println("5) Count Number of Words that have a Prime Length");
			System.out.println("6) Enter New Word List");
			System.out.println("7) Quit Program\n");
			
			String menuChoice = scnr.nextLine(); // Gets input from keyboard for menu choice
			
			switch(menuChoice) {
			
				case "1":
					displaySortedList(userWordArray);
					break;
				case "2":
					displayWordlength(userWordArray);
					break;
				case "3":
					displayListStatistics(userWordArray);
					break;
				case "4":
					numEvenAndOdd(userWordArray);
					break;
				case "5":
					numPrimeWords(userWordArray);
					break;
				case "6":
					userWordArray = promptUser();
					break;
				case "7":
					System.out.println("Program Exiting");
					flag = false;
					break;
				default:
					System.out.println("Wrong Selection!"); // Prints when user enters invalid input			
			}
			
		}while(flag);
		scnr.close();				
	}
	
	/**
	 * Creates an array of the words that the user inputs, it is also the method that is called when
	 * user wants to enter a new list.
	 * @return The String array containing the words.
	 */
	public static String[] promptUser() {
		
		Scanner promptScnr = new Scanner(System.in);
		// Get user input
		System.out.println("Enter List of Words Seperated by Spaces:");
		
		String userWords = promptScnr.nextLine(); // Gets input from keyboard for list of words
		
		return userWords.split(" ");
	}
	
	/**
	 * Sorts and displays the list.
	 * @param userList The array of Strings containing the user words.
	 */
	public static void displaySortedList(String[] userList) {
		// Sorts the array and prints the list after ordered properly
		Arrays.sort(userList);
		List<String> orderedList = Arrays.asList(userList);
		System.out.println(orderedList);
	}
	
	/**
	 * Displays the length of each word in the list.
	 * @param userList The array of Strings containing the user words.
	 */
	public static void displayWordlength(String[] userList) {
		// Goes through each word in list
		for(int i = 0 ; i < userList.length ; i++) {
			
			System.out.println(userList[i] + " Length is " + userList[i].length()); // Prints length
		}
	}
	
	/**
	 * Displays the list statistics which includes minimum word length, maximum word length, total
	 * number of characters, number of nouns, average word length, and most frequent word.
	 * @param userList The array of Strings containing the user words.
	 */
	public static void displayListStatistics(String[] userList) {
		// Variable declaration
		int first = 0;
		int minWordLength = userList[first].length();
		int maxWordLength = userList[first].length();
		int totalNumChars = 0;
		double averageWordLength = 0.00;
		int numNouns = 0;
		int count = 0;
		int mostWordFrequency = 0;
		int wordFrequencyIndex = 0;
		
		// Outer for loop to go through each word in list
		for(int i = 0 ; i < userList.length ; i++) {
			
			if(userList[i].length() < minWordLength) {
				
				minWordLength = userList[i].length(); // Stores minimum word length
			}
			if(userList[i].length() > maxWordLength) {
				
				maxWordLength = userList[i].length(); // Stores maximum word length 
			}
			
			// Increments numNouns variable if word starts with upper case
			if(Character.isUpperCase(userList[i].charAt(0))) {
				
				numNouns++;
			}
			
			totalNumChars = totalNumChars + userList[i].length(); // Stores the total characters
			
			// Inner for loop to compare each word with all the other words in the list
			for(int j = 0 ; j < userList.length ; j++) {
				
				if((userList[i].toLowerCase()).equals(userList[j].toLowerCase())) {
					count++; // Increases count if similarity is found
				}
				if(mostWordFrequency < count) {
					mostWordFrequency = count; // Stores max count value
					wordFrequencyIndex = i; // Stores index of word with max frequency
				}
			}
			count = 0; // Resets count variable for the next word in the list
		}
		
		// Calculates the average and then rounds it to two decimal places
		averageWordLength = (double) totalNumChars / userList.length;
		averageWordLength = Math.round(averageWordLength * 100.0) / 100.0;
		
		// Displays all the word statistics data for user
		System.out.println("Min Word Length: " + minWordLength);
		System.out.println("Max Word Length: " + maxWordLength);
		System.out.println("Total Number of Characters: " + totalNumChars);
		System.out.println("Number of Nouns: " + numNouns);
		System.out.println("Average Word Length: " + averageWordLength);
		// Prints No Mode if there are no duplicate words
		if(mostWordFrequency > 1) {
			System.out.println("Most Frequent Word: " + userList[wordFrequencyIndex]);	
		}
		else {
			System.out.println("No Mode");
		}
	}
	
	/**
	 * Displays the number of words that have an even number of letters and an odd number of letters.
	 * @param userList The array of Strings containing the user words.
	 */
	public static void numEvenAndOdd(String[] userList) {
		// Variable declaration
		int numEven = 0;
		int numOdd = 0;
		
		// Goes through each word in the list
		for(int i = 0 ; i < userList.length ; i++) {
			
			// Finds out if length of word is divisible by 2 and increments corresponding variable
			if(userList[i].length() % 2 == 0) {
				
				numEven++;
			}
			else {
				
				numOdd++;
			}
		}
		
		// Displays number of even and odd word lengths for user
		System.out.println("Number Even: " + numEven);
		System.out.println("Number Odd: " + numOdd);
	}
	
	/**
	 * Displays the number of words that have a prime number amount of letters.
	 * @param userList The array of Strings containing the user words.
	 */
	public static void numPrimeWords(String[] userList) {
		// Variable declaration
		int numPrime = 0;
		int factorCount = 0;
		
		// Outer for loop goes through each word in the list
		for(int i = 0 ; i < userList.length ; i++) {
			
			// Inner for loop to increment factors that will be used with modulo operator 
			for(int factor = 2 ; factor <= userList[i].length() / 2; factor++) {
				
				// Increments factorCount variable if word length has a factor
				if((userList[i].length() % factor == 0)) {
					
					factorCount++;
				}
			}
			
			// Increments numPrime if no factors were found for the current word
			if(factorCount == 0) {
				
				numPrime++;
			}
			factorCount = 0; // Resets factorCount variable for the next word in the list
		}
		
		// Displays number of primes to the user
		System.out.println("Number of Prime in list: " + numPrime);
	}
	
	
}
