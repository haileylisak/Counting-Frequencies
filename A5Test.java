/**
 * Test file for Bonus Assignment. Run this file as 
 *    java A5Test <filename>
 */
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class A5Test {
    public static void main(String[] args) {
        if (args.length < 1) {
            System.out.println("Usage: java A5Test <filename>");
        } else {
            SequenceInterface hashDS = new HashTableDS();
            hashDS.load(args[0]);

            SequenceInterface hashDS2 = new HashTableDS();

            Scanner fileScanner = null;

            try {
                fileScanner = new Scanner(new File(args[0]));
            } catch (FileNotFoundException e) {
                System.out.println("File not found: " + e);
            }

            String first = null, second = null;


            if (fileScanner.hasNext()) {
                first = fileScanner.next();
                hashDS2.put(first);
                System.out.println(first + " appeared " +
                        hashDS.getFrequencyOf(first) + " time(s)");
            }
            if (fileScanner.hasNext()) {
                second = fileScanner.next();
                if (hashDS2.getFrequencyOf(second) == 0) {
                    System.out.println(second + " appeared " +
                            hashDS.getFrequencyOf(second) + " time(s)");
                    System.out.println("(" + first + ", " + second +
                            ") appeared " + hashDS.getFrequencyOf(first, second)
                            + " time(s)");

                    hashDS2.put(second);
                    hashDS2.put(first, second);
                }
            }

            while (fileScanner.hasNext()) {
                first = second;
                if (fileScanner.hasNext()) {
                    second = fileScanner.next();
                    if (hashDS2.getFrequencyOf(second) == 0) {
                        System.out.println(second + " appeared " +
                                hashDS.getFrequencyOf(second) + " time(s)");
                        hashDS2.put(second);
                    }
                    if (hashDS2.getFrequencyOf(first, second) == 0) {
                        System.out.println("(" + first + ", " + second +
                                ") appeared " + hashDS.getFrequencyOf(first, second)
                                + " time(s)");
                        hashDS2.put(first, second);
                    }
                }
            }
        }
    }
}
