import java.util.Scanner;

//A class to run the game
public class TicTacToe{
    public static void main(String[] args){
        Scanner input=new Scanner(System.in);
        System.out.println("for playing a 2-player game press 0,for playing against the computer press 1");
        int k=input.nextInt();
        Board temp=new Board(k);
        int gameState=temp.gameOver();
        while(true){
            if(gameState!=0)  break;  
            temp.printBoard();
            temp.makeMove();
            gameState=temp.gameOver();
        }
        temp.printBoard();
        if(gameState==3){
            System.out.println("The Game is drawn.");
        }
        else{
            System.out.println("GAME OVER. Player "+gameState+" won the match!!!");
        }
    }
}
