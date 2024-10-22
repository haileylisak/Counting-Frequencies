/**  SequenceInterface interface for CS 0445 Assignment 5
 * @author Sherif Khattab
 *
 * Carefully read the specifications for each of the operations below.
 * Please make sure to implement them correctly in your HashTableDS class.
 *
 * The overall logic of the SequenceInterface is the following:
 * String items are organized in a Sequence. Your only 
 * requirement for the HashTableDS class is that all of the methods work as 
 * specified and that your class has a separate-chaining hash table as its 
 * primary data structure. More details are in the README file of the assignment and
 * the accompanying support slides.
 *
 * You MAY NOT use ArrayList, Vector, or any predefined collection class for your 
 * HashTableDS class. You MAY NOT declare arrays except for the hash table.
 */

public interface SequenceInterface
{

	/**
	 * Loads the contents of the file named fileName into the Sequence in O(n) time 
  	 * on average. Spaces, tabs, and new lines separate items in the file.
	 * @param FileName the name of the file to load
	 * @return true if loading was successful and false otherwise
	 */
	public boolean load(String fileName);

	/**
	 * Inserts first into the sequence in O(1) time on average
	 * @param first the String to be inserted
	 */
	public void put(String first);


	/**
	 * Inserts the pair (first, second) into the sequence in O(1) time on average
	 * @param first the first String in the pair to be added
	 * @param second the second String in the pair to be added
	 */
	public void put(String first, String second);


        /** 
	 * Returns the frequency of an item in the sequence in O(1) time on average.
	 * @param item a String item
	 * @return the number of occurrences of item in the sequence; return 0 if item is not found
	 */
	public int getFrequencyOf(String item);

	/** 
         * Returns the frequency of an ordered pair of items in O(1) time on average.
	 * @param first the first item in the pair
	 * @param second the second item in the pair
	 * @return the number of occurrences of (first, second); return 0 if pair doesn't exist
	 */
	public int getFrequencyOf(String first, String second);
}
