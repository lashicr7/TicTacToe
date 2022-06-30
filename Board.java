import java.util.Scanner;

//This class models the 3X3 grid of the game
public class Board{
    private char    boardgrid[][];//Stores the state of the game
    private boolean PlayerToMove;//Stores whether its Player 1/2 to move
    private boolean vscomputer;//Is the second player AI?
    
    //Constructor of Class
    public Board(int kai){
        boardgrid=new char [3][3];
        setToStart();
        if(kai!=0)    vscomputer=true;
        else           vscomputer=false;
        PlayerToMove=true;
    }
    
    //Mark the boardgrid to initial position
    public void setToStart(){
        for(int i=0;i<3;i++){
            for(int j=0;j<3;j++){
                boardgrid[i][j]=' ';
            }
        }
    }

    //Print the grid of the game
    public void printBoard(){
        for(int i=0;i<3;i++){
            for(int j=0;j<3;j++){
                 if(j!=2)System.out.print(boardgrid[i][j]+"|");
                   else System.out.print(boardgrid[i][j]);
            }
            
            System.out.print("\n");
            System.out.println("-------");
        }
    }
//Finds the row and column for given position
public int[] rowcol(int pos){
    int[] map=new int [2];
    map[0]=(pos-1)/3;
    map[1]=(pos - (map[0]*3))-1;
    return map;
}
//Finds whther the given position is valid top insert or not
public boolean valid(int pos){
   int[] map= rowcol(pos);
    int row = map[0];
        int col = map[1];
        if(row>-1 && row<3 && col>-1 &&col<3 && boardgrid[row][col] == ' '){
         return true;   
        } 
        else return false;
}            
    //Takes Input from player
     public void makeMove(){
        Scanner input=new Scanner(System.in);
        int pos,row,col;
            if(PlayerToMove){
            System.out.print("Player 1 to move\n");
                    while(true){
        System.out.println("Enter the number to insert");
            pos=input.nextInt();
           int[] map= rowcol(pos);
              row = map[0];
              col = map[1];
            if(valid(pos)) break;
            else{
                System.out.println("The given coordinate is invalid or filled, Please try again");
            }
        }
          boardgrid[row][col]='X';
            PlayerToMove=false;
     }
        else if(!vscomputer){
            System.out.print("Player 2 to move\n");
            while(true){
            System.out.println("Enter the number to insert");
            pos=input.nextInt();
               int[] map= rowcol(pos);
              row = map[0];
              col = map[1];
            if(valid(pos)) break;
            else{
                System.out.println("The given coordinate is invalid or filled, Please try again");
            }}
           boardgrid[row][col]='O';
            PlayerToMove=true;
        }
         else{
            //Invoke The AI to make a move
            System.out.println("Computer to move");
            AIPlayer ai=new AIPlayer();
            ai.makeMove(boardgrid);
            PlayerToMove=true;
        }
    }
    //Checks if the Game is over
    public int gameOver(){
        /*
        0=> Game should go on
        1=> Player1 won
        2=> Player2 won
        3=> Draw 
        */
   
    //possibility:1
    if(boardgrid[0][0]==' ')  return 0;
    for(int i=0;i<3;i++){
            if((boardgrid[i][0]==boardgrid[i][1] && boardgrid[i][1]==boardgrid[i][2] && boardgrid[i][0]!=' ') //Check for matches horizontally
            || (boardgrid[0][i]==boardgrid[1][i] && boardgrid[1][i]==boardgrid[2][i] && boardgrid[0][i]!=' ')) //Check for matches vertically 
            {
            if((boardgrid[1][0]=='X' && boardgrid[2][0]=='X' && boardgrid[0][0]=='X') || (boardgrid[1][2]=='X' && boardgrid[2][2]=='X' && boardgrid[0][2]=='X') || (boardgrid[0][1]=='X' && boardgrid[0][2]=='X' && boardgrid[0][0]=='X') || (boardgrid[2][0]=='X' && boardgrid[2][1]=='X' && boardgrid[2][2]=='X'))   return 1;
               // if(boardgrid[i][0]=='X')    return 1;
                else return 2;
            }
        }
        
        if((boardgrid[0][0]==boardgrid[1][1] && boardgrid[1][1]==boardgrid[2][2] && boardgrid[0][0]!=' ')//check top left to bottom right diagonal
        ||(boardgrid[0][2]==boardgrid[1][1] && boardgrid[1][1]==boardgrid[2][0] && boardgrid[1][1]!=' '))//check top right to bottom left diagonal
        {
           if((boardgrid[0][0]=='X' && boardgrid[1][1]=='X' && boardgrid[2][2]=='X')||(boardgrid[2][0]=='X' && boardgrid[1][1]=='X' && boardgrid[0][2]=='X')) return 1;
            else return 2;
        }
        for(int i=0;i<3;i++){
            for(int j=0;j<3;j++){
                if(boardgrid[i][j]==' '){
                    return 0;
                }
            }
        }
        return 3;
    }


}
