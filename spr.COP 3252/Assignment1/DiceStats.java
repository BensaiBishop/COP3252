////////////////////////////////////////////////////////////////////////////////
//  Made by Benjamin Bishop                                                   //
//  Program will prompt user to put number of dice to roll aswell as          //
//  the number  of rolls to be made . It will then calculate the likelihood   //
//  of acheiving that roll.                                                   //
////////////////////////////////////////////////////////////////////////////////

import java.util.Scanner;
import java.util.Random;

public class DiceStats{
  public static void main(String[] args) {
    //random number generator for the dice rolls
    Random rand = new Random();
    //Scanner for user input
    Scanner scan = new Scanner(System.in);
    //user prompts
    //number of dice
    System.out.print("How many dice constitute a roll?");
    int numDice = scan.nextInt();
    //number of rolls
    System.out.print("How many rolls?");
    int numRoll = scan.nextInt();
    //make an array of size 6*numDice - numDice+1 = 5numDice+1
    int[] arr = new int[(5*numDice)+1];
    //loop for rolls
    for (int i=0;i<numRoll;i++) {
      int sum = 0;
      //loop for each dice
      for (int j=0;j<numDice ;j++ )
      sum +=rand.nextInt(6)+1;
      //increase value of array in accordance with sum
      arr[sum-numDice]++;
    }
    //Print the table
    System.out.println("Sum\t# of times\tPrecentage");
    //loop for different sums
    for (int i = 0;i<(5*numDice)+1 ;i++ ) {
      System.out.printf("%d\t%d\t\t%.2f",i+numDice, arr[i],(float)(arr[i]*100)/numRoll);
      System.out.println(" %");
    }
    //close the scanner
    scan.close();
  }
}
