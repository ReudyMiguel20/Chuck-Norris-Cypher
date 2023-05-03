package chucknorris;


import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        //Initializing Variables and Objects
        Scanner scanner = new Scanner(System.in);
        StringBuilder sb = new StringBuilder();
        int counterForZeros = 0;
        int counterForOnes = 0;
        String binaryValuesConcat = "";

        //Asking user for input and creating an Array type char.
        while (true) {
            System.out.print("\n");
            System.out.println("Please input operation (encode/decode/exit):");
            String userResponse = scanner.nextLine();


            if (userResponse.equals("decode")) {
                try {
                    System.out.println("Input encoded string:");
                    String userInput = scanner.nextLine();
                    //Probably converting the string to number to see if they are number or not.
                    String[] inputArray = userInput.split(" ");

                    //If the string starts with 3 or more zeros is going to print the message and go back to loop.
                    if (inputArray[0].contains("000") || inputArray[0].contains("0000")) {
                        System.out.println("Encoded string is not valid.");
                        continue;
                    }

                    //Processing the 0's and converting them into Binary code: 1's and 0's.
                    for (String s : inputArray) {

                        if (counterForOnes >= 1) {
                            counterForOnes -= 1;

                            String[] splitZeros = s.split("");

                            for (String zeros : splitZeros) {
                                counterForOnes++;
                            }

                            for (int i = 0; i < counterForOnes; i++) {
                                sb.append(1);
                            }

                            counterForOnes = 0;
                        } else if (counterForZeros >= 1) {
                            counterForZeros -= 1;

                            String[] splitZeros = s.split("");

                            for (String zeros : splitZeros) {
                                counterForZeros++;
                            }

                            for (int i = 0; i < counterForZeros; i++) {
                                sb.append(0);
                            }
                            counterForZeros = 0;
                        } else if (s.equals("0")) {
                            counterForOnes++;
                        } else if (s.equals("00")) {
                            counterForZeros++;
                        }

                    }

                    //Assigning the StringBuilder string to a string variable and then clearing it.
                    String binaryCode = sb.toString();
                    sb.setLength(0);

                    //New Arrays to split the numbers and to classify them into binary.
                    String[] binaryArraySplit = binaryCode.split("");
                    String[] binaryArray = new String[binaryArraySplit.length / 7];


                    //Counters for the process below
                    int counterS = 0;
                    int counterIndexArray = 0;

                    //Indexing the binary numbers 7-bit
                    for (String s : binaryArraySplit) {
                        if (counterS == 7) {
                            binaryArray[counterIndexArray] = sb.toString();
                            counterS = 0;
                            counterIndexArray++;
                            sb.setLength(0);
                        }

                        sb.append(s);
                        counterS++;

                    }

                    //This is for when the above for loop ends and theres still other number to be added to the Array
                    if (!(sb.toString().isEmpty())) {
                        binaryArray[counterIndexArray] = sb.toString();
                    }

                    //New Array to add the decimal values of the binary conversion
                    int[] decimalConversion = new int[binaryArray.length];

                    for (int i = 0; i < binaryArray.length; i++) {
                        int decimalValue = Integer.parseInt(binaryArray[i], 2);
                        decimalConversion[i] = decimalValue;
                    }

                    //Clearing the StringBuilder variable again
                    sb.setLength(0);

                    //Concatenating the chars
                    for (int value : decimalConversion) {
                        char c = (char) value;
                        sb.append(c);
                    }

                    String stringDecimal = sb.toString();

                    System.out.println("Decoded string:");
                    System.out.println(stringDecimal);

                }
                catch (ArrayIndexOutOfBoundsException e) {
                    System.out.println("Encoded string is not valid.");
                }

            }

            else if (userResponse.equals("encode")) {
                System.out.println("Input string:");
                String userInput = scanner.nextLine();

                char[] charArray = userInput.toCharArray();

                //Printing out the result
                System.out.println("Encoded string:");
                for (char c : charArray) {
                    String binaryValue = Integer.toBinaryString((int) c);
                    String binaryValueIfSix = "";

                    //If binary value equal to 6 then add a 0 in the front.
                    if (binaryValue.length() == 6) {
                        binaryValueIfSix = "0" + binaryValue;
                        sb.append(binaryValuesConcat).append(binaryValueIfSix);
                    } else {
                        sb.append(binaryValuesConcat).append(binaryValue);
                    }

                }

                //Merging the binary numbers that 'sb' contains into a big one String.
                binaryValuesConcat = sb.toString();

                //For-loop for Chuck Norris Cypher.
                for (int i = 0; i < binaryValuesConcat.length(); i++) {

                    if (binaryValuesConcat.charAt(i) == '1' && counterForZeros > 0) {
                        StringBuilder numberOfZeroZeros = new StringBuilder();

                        for (int k = 0; k < counterForZeros; k++) {
                            numberOfZeroZeros.append("0");
                        }

                        String stringZeroZeros = numberOfZeroZeros.toString();

                        System.out.print("00" + " " + stringZeroZeros + " ");
                        counterForOnes++;
                        counterForZeros = 0;
                    } else if (binaryValuesConcat.charAt(i) == '0' && counterForOnes > 0) {
                        StringBuilder numberOfZeroOnes = new StringBuilder();

                        for (int j = 0; j < counterForOnes; j++) {
                            numberOfZeroOnes.append("0");
                        }

                        String stringZeroOnes = numberOfZeroOnes.toString();

                        System.out.print("0" + " " + stringZeroOnes + " ");
                        counterForZeros++;
                        counterForOnes = 0;

                    } else if (binaryValuesConcat.charAt(i) == '1') {
                        counterForOnes++;
                    } else if (binaryValuesConcat.charAt(i) == '0') {
                        counterForZeros++;
                    }

                }

                //This is for when the string is over and determining the last sequence of numbers.
                if (counterForOnes > 0) {

                    StringBuilder numberOfZeroOnes = new StringBuilder();

                    for (int j = 0; j < counterForOnes; j++) {
                        numberOfZeroOnes.append("0");
                    }

                    String stringZeroOnes = numberOfZeroOnes.toString();

                    System.out.print("0" + " " + stringZeroOnes + " ");
                    numberOfZeroOnes.setLength(0);
                    stringZeroOnes = "";

                } else {
                    StringBuilder numberOfZeroZeros = new StringBuilder();

                    for (int k = 0; k < counterForZeros; k++) {
                        numberOfZeroZeros.append("0");
                    }

                    String stringZeroZeros = numberOfZeroZeros.toString();
                    System.out.print("00" + " " + stringZeroZeros + " ");
                    numberOfZeroZeros.setLength(0);

            }
                //Resetting values and inputting a space in console after ending
                System.out.println();
                counterForOnes = 0;
                counterForZeros = 0;
                sb.setLength(0);
                binaryValuesConcat = "";
            }

            else if (userResponse.equals("exit")){
                System.out.println("Bye!");
                break;
            }

            else {
                System.out.println("There is no " + "'" + userResponse + "'" + " operation");
            }

        }

    }
}


