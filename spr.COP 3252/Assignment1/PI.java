////////////////////////////////////////////////////////////////////////////////
//  Made by Benjamin Bishop                                                   //
//  Program will prompt user to put number of terms to calculate              //
//  the infinite series of Pi. It should then display those terms             //
//  on a new line.                                                            //
////////////////////////////////////////////////////////////////////////////////

//import Scanner for collection of input for primative data types.
import java.util.Scanner;

public class PI{
public static void main(String[] args) {
  //create scan to allow for collection of input
  Scanner scan = new Scanner(System.in);
  System.out.println("Compute to how many terms of the series?");
  int depth = scan.nextInt();
  //first term , then preceeding terms.
  double pi=0.0;
  int odd=1;
  System.out.println("PI approximation");
  //display terms
  for(int i=1;i<=depth;i++){
      double currentTerm=0.0;
      if(i%2==0)
        {
        currentTerm=(double)-4/odd;
        }
      else
        {
        currentTerm=(double)4/odd;
        }
      odd=odd+2;
      pi=pi+currentTerm;
      System.out.println(i+"     "+pi);
    }
  //close scanner
  scan.close();
  }
}
