import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class HashTableDS implements SequenceInterface {

	private static final int INITIAL_CAPACITY = 5;
	private static final int MAX_LOAD_FACTOR = 1; 
	private Node[] HT; // separate-chaining hash table
	private int size; // number of entries in the hash table
	private int capacity; // number of hash table buckets
	//String line;

	public HashTableDS() 
	{
		//TODO: initialize HT, capacity and size
		this.capacity = INITIAL_CAPACITY;
		HT = new Node[capacity];
		this.size = 0;
		

	}

	public HashTableDS(int capacity) 
	{
		//CAPACITY NEEDS TO BE A PRIME NUMBER!!!!!!!!!
		//TODO: initialize HT, capacity and size
		if(!isPrime(capacity))
		{
			this.capacity = getNextPrime(capacity);
		}
		else
		{
			this.capacity = capacity;
		}

		this.size = 0;
		HT = new Node[capacity];
	}

	@Override
	public boolean load(String fileName) {
		boolean result = false;

		Scanner fileScanner = null;

		try {
			fileScanner = new Scanner(new File(fileName));
			result = true;
		} catch (FileNotFoundException e) {
			System.out.println("File not found: " + e);
		}

		//TODO Complete this method

		//THIS IS LOAD METHOD

		/*
		 * read through file and call put to put all words and word pairs into hash table
		 * next(), hasNext()
		 * only one scanner
		 */
		 //go through every word in the file
		 String  previous = null;
		 String first;
		 String second;
		 String nextStr;

		 first = fileScanner.next();
		 put(first);
		 
		 while(fileScanner.hasNext())
		 {
			//if there is a space between the current and next word, 
			//then they are pairs...so concatenate
			if(previous!=null)
			{
				nextStr = fileScanner.next();
				put(nextStr);
				put(previous, nextStr);
				previous = nextStr;
			}
			else
			{
				second = fileScanner.next();
				put(second);
				put(first, second);
				previous = second;
			}
			//read current and the next word then call put
			//showTable();
	
		 }

		 return result;
		
	}
	// TODO Write this method
		/*
		 * hash first and/or second 
		 * put into the hash table, after check if it already exists
		 * if it already exists, only increase the frequency
		 * check for resize
		 * 
		 * call hash
		 * check if second is null, call hash on first word
		 * if it's not null, then concatenate then call hash on first and second
		 * 
		 * check for resize size/capacity = MAX_LOAD_factor
		 */

		//checking for resize

	public void put(String first, String second) 
	{
		//showTable();
		int hashNum;
		boolean match = false;
		
		if(size/capacity==MAX_LOAD_FACTOR)
		{
			resize();
		}
		if(second==null)
		{
			hashNum = hash(first);
		}
		else
		{	
			String wordPair = first+second;
			hashNum = hash(wordPair);
		}
		//if it already exists...
		if(HT[hashNum]!=null)
		{	match = false;
			Node linkedList = HT[hashNum];
			//if the first node list is correct...
			/*if(linkedList.first.equals(first))
			{
				if(linkedList.second==null && second==null)
				{
					linkedList.frequency++;
					match = true;
				}
				else if(linkedList.second!=null)
				{
					if(second==null)
					{
						match = false;
					}
					else if(linkedList.second.equals(second))
					{
						linkedList.frequency++;
						match = true;
					}
				}
			}*/
			//if it's not then we need to go through all of the other linked lists
			//else
			//{
				while(linkedList!=null)
				{
					if(linkedList.first.equals(first))
					{
						if(linkedList.second==null && second==null)
						{
							linkedList.frequency++;
							match = true;
						}
						else if(linkedList.second!=null)
						{
							if(second==null)
							{
								match = false;
							}
							else if(linkedList.second.equals(second))
							{
								linkedList.frequency++;
								match = true;
							}
						}
					}
					if(match == true)
						break;
					
					linkedList = linkedList.next;
				}
				if(match==false)
				{
					Node newNode = new Node(first,second);
					//newNode.frequency++;
					//since it is already at linkedList.next which == null,
					newNode.next = HT[hashNum];
					HT[hashNum] = newNode;
					newNode.frequency++;
					size++;
					//had to make it true so that I didn't go into the 
					//if statement below
				}
			//}
		}
		//if it doesn't exist yet
		if(HT[hashNum]==null)
		{
			
			Node newNode = new Node(first,second);
			//newNode.frequency++;
			HT[hashNum] = newNode;
			newNode.frequency++;
			size++;
		}	
		//showTable();
	}

	public void put(String first) {
		put(first, null);
	}

	private void resize() {
		HashTableDS temp = new HashTableDS(getNextPrime(2 * capacity));
		for (int i = 0; i < capacity; i++) {
			Node current = HT[i];
			while (current != null) 
			{
				//TODO Complete this method

				//call put

				//iterate through the hash table and call put on all of the items
				if(current.frequency>1)
				{
					for(int j=0; j<current.frequency; j++)
					{
						temp.put(current.first,current.second);
					}
				}
				else
				{
					temp.put(current.first, current.second);
				}

				current = current.next;
			}
		}
		HT = temp.HT;
		size = temp.size;
		capacity = temp.capacity;
	}

	/**
	 * Horner's method
	 * 
	 * @param item the String to be hashed
	 * @return the hash value of item
	 */
	private int hash(String item) {
		int hash = 0;
		for (int i = 0; i < item.length(); i++) {
			hash = 256 * hash + item.charAt(i);
			hash = hash % capacity;
		}
		return hash % capacity;
	}

	@Override
	public int getFrequencyOf(String item) 
	{
		// TODO Write this method
		/*
		 * hash item to find the index in hash table
		 * find the ndoe that has first=item. and second=null
		 * get frequency from that node]
		 * 
		 * MAKE SURE TO FIX THE RUNTIME 
		 */
		int frequency = 0;
		frequency = getFrequencyOf(item, null);
		

		return frequency;

	}

	@Override
	public int getFrequencyOf(String first, String second) {
		// TODO Write this method
		int hashNum;
		int frequency=0;
		String word;
		boolean match = false;

		if(second==null)
		{
			word = first;
		}
		else
		{
			word = first+second;
		}

		hashNum = hash(word);
		Node linkedList = HT[hashNum];
		while(linkedList!=null)
		{
			if(linkedList.first.equals(first))
			{
				if(linkedList.second==null && second == null)
				{
					frequency = linkedList.frequency;
					match = true;
				}
				else if(linkedList.second!=null)
				{
					if(second==null)
					{
						frequency = 0;
						match = false;
					}
					else
					{
						if(linkedList.second.equals(second))
						{
							frequency = linkedList.frequency;
							match = true;
						}
					}
				}
					
				if(match==true)
					break;
			}
			linkedList = linkedList.next;
		}
		return frequency;
	}

	/**
	 * Helper method to display the contents of the hash table. This is useful
	 * for debugging.
	 */
	private void showTable() {
		for (int i = 0; i < capacity; i++) {
			System.out.print(i + ": ");
			Node current = HT[i];
			while (current != null) {
				System.out.print("(" + current.first + ", " + current.second + ", " + current.frequency + ") ");
				current = current.next;
			}
			System.out.println();
		}
	}

	// Returns a prime integer that is >= the given integer.
	private int getNextPrime(int integer) {
		// if even, add 1 to make odd
		if (integer % 2 == 0) {
			integer++;
		} // end if

		// test odd integers
		while (!isPrime(integer)) {
			integer = integer + 2;
		} // end while

		return integer;
	} // end getNextPrime

	// Returns true if the given intege is prime.
	private boolean isPrime(int integer) {
		boolean result;
		boolean done = false;

		// 1 and even numbers are not prime
		if ((integer == 1) || (integer % 2 == 0)) {
			result = false;
		}

		// 2 and 3 are prime
		else if ((integer == 2) || (integer == 3)) {
			result = true;
		}

		else // integer is odd and >= 5
		{
			assert (integer % 2 != 0) && (integer >= 5);

			// a prime is odd and not divisible by every odd integer up to its square root
			result = true; // assume prime
			for (int divisor = 3; !done && (divisor * divisor <= integer); divisor = divisor + 2) {
				if (integer % divisor == 0) {
					result = false; // divisible; not prime
					done = true;
				} // end if
			} // end for
		} // end if

		return result;
	} // end isPrime

	private static class Node {
		private String first;
		private String second;
		private int frequency;
		private Node next;

		private Node(String first, String second) {
			this.first = first;
			this.second = second;
			frequency = 0;
			next = null;
			/*
			 * can call node.first
			 * node.second
			 * etc.
			 */
		}
	}

}
/*
 * Horner's Method --- mod by size 
 * don't forget to increase size when you put another NODE in (not when you increase the frequency)
 * create new node when adding to the hash table
 * before moving onto the next word, make sure you add the next word to the node
 * resize using a prime number; double the size and go to the next prime number. resize function will do this for you
 * after you resize the hash table, you have to go through the old hash table and rehash every string 
 * when size == capacity, you must resize; size/capacity == 1
 * 
 * helper method showTable will help debug
 * 
 * 
 * 
 * java *.java
 * java A5Test small.txt
 */