////////////////////////////////////////////////////////////////////////////////
//  Made by Benjamin Bishop                                                   //
//  this program will take and string of digits from the user and             //
//  print out the reverse of the entered string.                              //
////////////////////////////////////////////////////////////////////////////////

import java.util.Scanner;

public class Reverse{
  public static void main(String[] args) {
    //usering scanner to get inpur from user
    Scanner scan=new Scanner(System.in);

    //store user input
    int userNum = 1;
    while (userNum != 0){
    System.out.println("Please enter a long integer (0 to quit):");
    userNum=scan.nextInt();

    //while loop to continue program

      System.out.printf("The number reversed is: %d",reverseDigits(userNum));
      System.out.println("");
    }
  }

  //function to reverse number
  static int reverseDigits(int userNum){

    //store reversed data
    int reversed = 0;
    //loop to get reverse number
    if(userNum == 0){
      System.out.print("Goodbye");
      System.exit(0);
    }
    while(userNum>0){
      //last digit
      int last = userNum % 10;
      //add last to reverse*10
      reversed = reversed*10+last;
      //divide userNum by 10
      userNum/=10;
    }
    //store into reversed
    return reversed;
  }
}
