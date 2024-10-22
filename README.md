# CS 0445 – Algorithms and Data Structures 1 – Assignment 5

**Due: Friday December 8th @ 11:59pm**

You should submit the file `HashTableDS.java` to GradeScope (the link is on
Canvas).

**Late submission: Sunday December 10th @ 11:59 pm with 10% penalty per late day**

## TABLE OF CONTENTS

- [Overview](#overview)
- [Details](#Details)
- [Submission Requirements](#submission-requirements)
- [Rubrics](#rubrics)

## OVERVIEW

**Purpose:** To emphasize the object-oriented programming approach used in Java, and to practice working with hash tables, arrays, and linked lists. Specifically, you will create and utilize a hash table data structure, `HashTableDS`, that will act as a data structure for sequences of Strings. Your `HashTableDS` class will primarily implement the interface `SequenceInterface`. The details of the interface are explained in the file `SequenceInterface.java`. Read this file over very carefully before implementing `HashTableDS`.

[^1]: Some text adapted from Dr. John Ramirez’s CS 0445 class.


## Details

Sequences appear in many applications. A number can be viewed as a sequence of digits, a String as a sequence of characters, a genome as a sequence of nucleotides (A, T, C, G), and text files as a sequence of words. Some of these sequences may be huge! We usually use arrays or linked chains to implement Sequences. However, some operations are in-efficient in array/linked-chain implementations, in particular the operation of counting the frequencies of all items and all pairs of items stored in a large file. This assignment investigates an alternative implementation that is more efficient for counting the frequencies of all words and all consecutive word pairs in a file.

Consider a Sequence of Strings stored in a file. The text file below is an example of such a Sequence.

```
int search( int a[], int v, int l, int r)
  { 
    for ( int i = l; i <= r; i++)
      if (v == a[i]) return i;
    return -1;
  }
int search( int a[], int v, int l, int r)
  { 
    while (r >= l)
      { int m = (l+r)/2;
        if (v == a[m]) return m;
        if (v < a[m]) r = m-1; else l = m+1;
      }
    return -1;
  }
```
In that file, words are separated by spaces, tabs, and new lines. So the Sequence contains: `“int”, “search(“, “int”, “a[],”, “int”, “v,” …`. The frequency of an item is the number of times it appears in the Sequence. For example, the frequency of `“int”` is `12`. The frequency of a pair of consecutive items is the number of times the pair appears in the sequence. For instance, the frequency of the pair `(“int”, “v,”)` is `2`.

We will implement a Sequence using a hash table with separate chaining. Each table entry is a linked list of all items and item pairs that hash into the entry. Each node in the linked lists contains the frequency of an item or item pair. We will use Horner’s method with modular hashing for computing hash values. 

```java
private int hash(String item) {
	int hash = 0;
	for (int i = 0; i < item.length(); i++) {
		hash = 256 * hash + item.charAt(i);
		hash = hash % capacity; //capacity is the number of entries in hash table
	}
	return hash % capacity;
}
```

Please check the file `A5Support.pptx` for a full trace of a small example.

For the details on the functionality of your `HashTableDS` class, carefully read over the files
`SequenceInterface.java` and `A5Test.java`. You must use these files as specified and **cannot remove/alter any of
the code already written in them**. There are different ways of implementing the
`SequenceInterface` methods, some of which are more efficient than
others. Your implementation should meet the running times specified in the method comments inside `SequenceInterface.java`. A lot of pencil-and-paper work is recommended before starting to write your code. Your `HashTableDS` class header should be:

`public class HashTableDS implements SequenceInterface {`

**Important Note: The primary data within `HashTableDS` class *must be* a *resizable* hash table that uses separate chaining to handle collisions. It must be a one-dimensional array of _linked lists_. You may not use any predefined Java collection class (e.g., `ArrayList`, `HashMap`, `HashSet`, etc.) for your `HashTableDS` data fields. 

You must use the following instance variables inside the `HashTableDS` class:

```java
	private static final int INITIAL_CAPACITY = 5;
	private static final int MAX_LOAD_FACTOR = 1;
	private Node[] HT; // separate-chaining hash table
	private int size; // number of items in the sequence
	private int capacity; // number of chains
```
You should define the inner `Node` class as below.

```java
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
    }
}
```

Besides the methods of `SequenceInterface`, the following constructors are required:

```java
public HashTableDS()
public HashTableDS(int capacity)
```


After you have finished coding of `HashTableDS`, the `A5Test.java` file provided for you should compile and run correctly and should give output identical to the output shown in the files with the files `small-out.txt`, `medium-out.txt`, etc.

## SUBMISSION REQUIREMENTS

The only source file that you can change is:

1. `HashTableDS.java`

The idea from your submission is that the autograder can compile and run your code **from the command line** WITHOUT ANY additional files or changes, so be sure to test them thoroughly before submitting it.

**Note: If you use an IDE such as NetBeans, Eclipse, or IntelliJ, to develop your programs, make sure
they will compile and run on the command line before submitting – this may require some
modifications to your program (such as removing some package information).**


## RUBRICS

Please note that if an autograder is available, its score will be used as guidance for the TA, not as an official final score. Please also note that the autograder rubrics are the definitive rubrics for the assignment. **There is no partial credit for this assignment.**

| Item | Grade |
|------|-------|
|Autograder Score| 60|
| Efficiency (meeting the running time specification) | 40 |
