
////////////////////////////////////////////////////////////////////////////////
//  Benjamin Bishop                                                           //
//  8 Feb 2021                                                                //
//  TicaTacToe game allowing options to go against computer or human.         //
////////////////////////////////////////////////////////////////////////////////

import java.util.Scanner;
import java.util.Random;
/*
going to need functions to;
drawBoard - a function to draw the board
checkMove - a function to check if a move is valid
Empty - check if empty
setMove - a function to set the move
gameOver - determines if the game is over
getPlayerTurn - return turn order
gameWon - determines if the game was won
declareWinner - declared the winner
setCPUmove - sets the CPU's move
*/

class TicTacToe{
/*////////////////////////////////////////////////
 Start of Main
*/////////////////////////////////////////////////
  public static void main(String[] args) {
    //scanner obj for input
    Scanner scan= new Scanner(System.in);

    //integer to hold move
    int move;

    //two human players
    //start of a new game
if(args.length==0){
      ActualGame play = new ActualGame();
      play.drawBoard();

      //creating a do/while loop until the game ends
      do{
        //user prompt for turn
        do{
          System.out.print("Player");

          if(play.getPlayerTurn())
            System.out.print("1, ");
          else
            System.out.print("2, ");

          System.out.println("Enter a position 1-9");
          move=scan.nextInt();
          System.out.println();
        }
        //valid move
        while(!play.checkMove(move));
        //record Move
        play.setMove(move);
        //display the board
        play.drawBoard();
      }
      //once loop conditions are met; end and display winnner
      while(!play.gameOver()&&!play.gameWon());

      //declare the winner
      play.declareWinner();
    }

    //invalid Usage
    else if(args.length!=2){
      System.out.println("Usage: java TicTacToe [-c[1][2]]");
    }

    //two computer players
else if (args.length==1){
      if(!args[0].equals("-c")){
        System.out.println("Usage: java TicTacToe [-c[1][2]]");
        return;
      }
      
    }
    // CPU goes first////////////////////////////////////////////
else if (args[0].equals("-c") && args[1].equals("1")){
    // start a new game and display board
      ActualGameCPU play = new ActualGameCPU(true);
      play.drawBoard();

      // loop until game ends
      do{
        // computer moves
        play.setCPUmove();

        play.drawBoard();

        //if game has not ended
        if(!play.gameOver() && !play.gameWon()){
            do{
                System.out.print("Player ");

                  if(play.getPlayerTurn())
                   System.out.print("1, ");
                  else
                   System.out.print("2, ");

                   System.out.print("enter a move 1-9");
                   move = scan.nextInt();
                   System.out.println();
                 }
                 while(!play.checkMove(move)); // valid move
           // set move
           play.setMove(move);
           //draw board
           play.drawBoard();
         }
       }
       while(!play.gameOver() && !play.gameWon());

       // declare winner
      if(play.getPlayerTurn())
       play.declareWinner(false);
      else
       play.declareWinner(true);
     }

    // CPU goes second////////////////////////////////////////////
else if (args[0].equals("-c") && args[1].equals("2")){
    //diplay the board
    ActualGameCPU play = new ActualGameCPU(false);
    play.drawBoard();
    do{
      //prompt user
      do{
        System.out.print("Player ");

          if(play.getPlayerTurn())
           System.out.print("1, ");
          else
           System.out.print("2, ");

           System.out.print("enter a move 1-9");
           move = scan.nextInt();
           System.out.println();
      }
      //check for valid move
      while(!play.checkMove(move));

      play.setMove(move);
      play.drawBoard();

      if(!play.gameOver() && !play.gameWon()){
        //comp turn
        play.setCPUmove();
        play.drawBoard();
      }
    }
    while(!play.gameOver() && !play.gameWon());

    //declare the winner
    if(play.getPlayerTurn())
      play.declareWinner(true);//comp wins
    else
      play.declareWinner(false);//player wins
  }
   else { //improper usage
       System.out.println("Usage: java TicTacToe [-c[1][2]]");
   }

   //advanced ai with minimax functions
//else if (args[0].equals("-c") && args[1].equals("1") && args[2].equals("-a"))
//else if (args[0].equals("-c") && args[1].equals("2") && args[2].equals("-a"))

  }//End of Main
}//End of TicTacToe Class

/*/////////////////////////////////////////////
ActualGame Class
*//////////////////////////////////////////////
class ActualGame{
  //an array to hold the X and O
  protected char [][] game=new char[3][3];

  //a bool to determin turn order
  protected boolean player1;

  //constructor, making a 3x3 grid
  public ActualGame(){
    for (int i=0;i<3 ;i++ ) {
      for (int j=0;j<3 ;j++ ) {
        game[i][j]=' ';// setting all position to empty ' '
      }
    }
    player1=true;
  }

  //checkMove// checks the move for validity
  public boolean checkMove(int move){
    //if move if a number out of bounds
    if (move < 1||move > 9) {
      System.out.println("invalid move, pick between 1-9");
      return false;
    }
    //if move is filled
    if(!Empty(move)){
      System.out.println("invalid move, pick an empty position");
      return false;
    }
    //if none of the checks are hit then it is valid and empty.
    return true;
  }//end of checkMove//////////////////////////

    //draw function//-draws the board
    public void drawBoard(){
      int pos=1; //the board label for positions
      for (int i=0;i<3 ;i++ ) {
        for (int j=0;j<3 ;j++ ) {
          System.out.print(" "+game[i][j]+" ");
          if(j<2)
            System.out.print("|");
        }
        System.out.print(" ");

        for (int j=0;j<3 ;j++ ) {
          System.out.print(" "+pos+" ");
          pos++;

          if (j<2)
            System.out.print("|");
        }
        System.out.println();

        if(i<2){
          System.out.print("-----------");
          System.out.print(" ");
          System.out.println("-----------");
        }
      }
    }//end of draw ///////////////////////////////

  //Empty// check if pos is empty.
  public boolean Empty(int move){
    boolean empty = true;
    switch(move){
      //9 cases, 9 positions
      case 1:
      if (game[0][0]!= ' ' )
          empty = false;
          break;

      case 2:
      if (game[0][1]!=  ' ' )
        empty = false;
        break;

      case 3:
      if (game[0][2]!=  ' ' )
        empty = false;
        break;

      case 4:
      if (game[1][0]!=  ' ' )
        empty = false;
        break;

      case 5:
      if (game[1][1]!=  ' ' )
        empty = false;
        break;

      case 6:
      if (game[1][2]!=  ' ' )
        empty = false;
        break;

      case 7:
      if (game[2][0]!=  ' ' )
        empty = false;
        break;

      case 8:
      if (game[2][1]!=  ' ' )
        empty = false;
        break;

      case 9:
      if (game[2][2]!=  ' ' )
        empty = false;
        break;

    }
    return empty;
  }//end of Empty/////////////////////////////

  //setMove// parameter to set a chosen move.
  public void setMove(int move){
    char mov; //holding the move to be added

    if(player1)
      mov='X';
    else
      mov='O';

      switch(move){
        case 1: game[0][0]=mov;
          break;
        case 2: game[0][1]=mov;
          break;
        case 3: game[0][2]=mov;
          break;
        case 4: game[1][0]=mov;
          break;
        case 5: game[1][1]=mov;
          break;
        case 6: game[1][2]=mov;
          break;
        case 7: game[2][0]=mov;
          break;
        case 8: game[2][1]=mov;
          break;
        case 9: game[2][2]=mov;
          break;
      }
      //switch player1 from true to false and viceversa;
      player1^=true;
  }//end of setMove///////////////////////////

  //gameOver// check if game is over/////
  public boolean gameOver(){
    //need to loop through all positions
    for (int i=0;i<3 ;i++ ) {
      for (int j=0;j<3 ;j++ ) {
        if(game[i][j]==' ')
        return false;
      }
    }
    return true;
  }//end of gameOver/////////////////////////

  //getPlayerTurn// check who's turn it is//
  public boolean getPlayerTurn(){
    return player1;
  }

  //gameWon// check if the game is won///////
  public boolean gameWon(){
    char mov='X';

    //must check two times, because of two players
    for (int i=0;i<2 ;i++ ) {
      //must check through all combinations
    if(game[1][1] == mov){
               if(game[0][1] == mov && game[2][1] == mov)
                   return true;   // center column
               if(game[0][2] == mov && game[2][0] == mov)
                   return true;   // 3-7 diagonal
               if(game[1][2] == mov && game[1][0] == mov)
                   return true;   // center row
               if(game[2][2] == mov && game[0][0] == mov)
                   return true;   // 9-1 diagonal
           }
           // top left corner
    if(game[0][0] == mov){
               if(game[0][1] == mov && game[0][2] == mov)
                   return true;   // top row
               if(game[1][0] == mov && game[2][0] == mov)
                   return true;   // left column
           }
           // bottom right corner
    if(game[2][2] == mov){
               if(game[1][2] == mov && game[0][2] == mov)
                   return true;   // right column
               if(game[2][1] == mov && game[2][0] == mov)
                   return true;   // bottom row
           }
           mov = 'O';   // switch to player2
       }
       //no winnner
       return false;
  }//end of game won///////////////////////////

  public void declareWinner(){
    //need to determine if not a draw.
    if(gameWon()){
      //game is won by someone.
      System.out.print("Player");

      if(!player1)
        System.out.print("1 ");
      else
        System.out.print("2 ");

      System.out.println("wins");
    }
    else
      System.out.println("Draw");
  }
}//End of ActualGame Class

/*//////////////////////////////////////////////
ActualGameCPU Class
*///////////////////////////////////////////////
class ActualGameCPU extends ActualGame{
  //a boolean to hold all possible moves for CPU
  boolean [] moves= new boolean [9];

  //holds comp move label
  char movCPU;
  //another to hold the other label
  char movCPU2;

  //Constructor
  public ActualGameCPU(boolean CPUfirst){

    super();//parent constructor call

    if(CPUfirst){
      movCPU='X';
      movCPU2='O';
    }
    //opposing moves
    else{
      movCPU='O';
      movCPU2='X';
    }
  }

  //setCPUmove// method to set CPU's moves
  public void setCPUmove(){
    //go for the winning move
    winningMove(movCPU);
    for (int i=0;i<9 ;i++ ) {
      if (moves[i]=true) {
        setMove(i+1);
        displayCPUmove(i+1);
        resetMoves();
        return;
      }
    }
    //block winning moves
    winningMove(movCPU2);
    for (int i=0;i<9 ;i++ ) {
      if (moves[i]==true) {
        setMove(i+1);
        displayCPUmove(i+1);
        resetMoves();
        return;
      }
    }
    if(game[1][1]==' '){
      setMove(5);
      displayCPUmove(5);
      return;
    }
    //just pick a random square
    boolean pick = false;
    Random rand = new Random();
    int n;

    do{
      n=rand.nextInt(9)+1;

      if(Empty(n)){
        setMove(n);
        displayCPUmove(n);
        pick = true;
      }
    }
    while(!pick);
  }

  //displayCPUmove-method to display the CPU's moves
  public void displayCPUmove(int Cmov){
    System.out.println("CPU picked "+ Cmov);
  }

  //resetMoves-method to reset array
  public void resetMoves(){
    for (int i=0;i<9 ;i++ ) {
      moves[i]=false;
    }
  }

  //declare winner//-method to declare the winner
  public void declareWinner(boolean Win){
    //check if the game is
    if(gameWon()){
      if(Win)
         System.out.print("CPU, wins");
      else
         System.out.println("You win");
    }
    else
       System.out.println("draw");
  }

  //winningMove- method to pick the winning move//
  public void winningMove(char win){
    //check all the combinations starting from middle
    //brute force check all combos, I don't know any other way to do this
    if(game[1][1]==win){
           if(game[0][1] == win && game[2][1] == ' ')
               moves[7] = true; // 8 is winning move
           if(game[2][1] == win && game[0][1] == ' ')
               moves[1] = true; // 2
           if(game[0][2] == win && game[1][2] == ' ')
               moves[6] = true; // 6
           if(game[2][0] == win && game[0][2] == ' ')
               moves[2] = true; // 3
           if(game[1][2] == win && game[1][0] == ' ')
               moves[3] = true; // 4
           if(game[1][0] == win && game[1][2] == ' ')
               moves[5] = true; // 6
           if(game[2][2] == win && game[0][0] == ' ')
               moves[0] = true; // 1
           if(game[0][0] == win && game[2][2] == ' ')
               moves[8] = true; // 9
       }
       // bottom right corner
    if(game[2][2] == win){
           if(game[1][2] == win && game[0][2] == ' ')
               moves[2] = true; // 3
           if(game[0][2] == win && game[1][2] == ' ')
               moves[5] = true; // 6
           if(game[2][1] == win && game[2][0] == ' ')
               moves[6] = true; // 7
           if(game[2][0] == win && game[2][1] == ' ')
               moves[7] = true; // 8
       }
       // top right corner
       if(game[0][2] == win){
           if(game[0][1] == win && game[0][0] == ' ')
               moves[0] = true; // 1
           if(game[0][0] == win && game[0][1] == ' ')
               moves[1] = true; // 2
           if(game[1][2] == win && game[2][2] == ' ')
               moves[8] = true; // 9
           if(game[2][2] == win && game[1][2] == ' ')
               moves[5] = true; // 6
       }
       // top left corner
    if(game[0][0] == win){
           if(game[0][1] == win && game[0][2] == ' ')
               moves[2] = true; // 3
           if(game[0][2] == win && game[0][1] == ' ')
               moves[1] = true; // 2
           if(game[1][0] == win && game[2][0] == ' ')
               moves[6] = true; // 7
           if(game[2][0] == win && game[1][0] == ' ')
               moves[3] = true; // 4
       }
       // bottom left corner
    if(game[2][0] == win){
           if(game[1][0] == win && game[0][0] == ' ')
               moves[0] = true; // 1
           if(game[0][0] == win && game[1][0] == ' ')
               moves[3] = true; // 4
           if(game[2][1] == win && game[2][2] == ' ')
               moves[8] = true; // 9
           if(game[2][2] == win && game[2][1] == ' ')
               moves[7] = true; // 8
       }
   }
}//End of ActualGameCPU Class
