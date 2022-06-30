import java.util.Random;

//This Class is to maintain the input functions of the player
public class AIPlayer{
    public void makeMove(char [][] board){
        /*
        The AI will first look for moves which are immediately winning and make those moves if any
        If there are no winning moves, the AI looks whether the player can win(in atleast one way) immediately, it blocks the winning square for opponent
        If both the above conditions fail, AI looks to occupy the center, if it is filled, it does a random move
        */
        for(int i=0;i<3;i++){
            for(int j=0;j<3;j++){
                if(board[i][j]==' '){
                    if(winningMove(board, i, j)){
                        printMove(i,j);
                        board[i][j]='O';
                        return;
                    }
                    if(forcedMove(board, i, j)){
                        printMove(i,j);
                        board[i][j]='O';
                        return;
                    }
                }
            }
        }
        if(board[1][1]==' '){
            printMove(1,1);
            board[1][1]='O';
            return;
        }
        makeRandomMove(board);
    }
    
    private boolean winningMove(char [][] board,int x,int y){
        int count=0;
        for(int i=0;i<3;i++){
            if(board[x][i]=='O') count++;
            else if(board[x][i]=='X')   count--;
        }
        if(count==2)    return true;
        count=0;
        for(int i=0;i<3;i++){
            if(board[i][y]=='O') count++;
            else if(board[i][y]=='X')   count--;
        }
        if(count==2)    return true;
        count=0;
        if(x==y){
            for(int i=0;i<3;i++){
                if(board[i][i]=='O')    count++;
                else if(board[i][i]=='X')   count--;
            }
        }
        if (count==2)   return true;
        if(x+y==2){
            for(int i=0;i<3;i++){
                if(board[i][2-i]=='O')    count++;
                else if(board[i][2-i]=='X')   count--;
            }
        }
        if(count==2)    return true;
        return false;
    }

    private boolean forcedMove(char [][] board,int x,int y){
        int count=0;
        for(int i=0;i<3;i++){
            if(board[x][i]=='X') count++;
            else if(board[x][i]=='O')   count--;
        }
        if(count==2)    return true;
        count=0;
        for(int i=0;i<3;i++){
            if(board[i][y]=='X') count++;
            else if(board[i][y]=='O')   count--;
        }
        if(count==2)    return true;
        count=0;
        if(x==y){
            for(int i=0;i<3;i++){
                if(board[i][i]=='X')    count++;
                else if(board[i][i]=='O')   count--;
            }
        }
        if (count==2)   return true;
        if(x+y==2){
            for(int i=0;i<3;i++){
                if(board[i][2-i]=='X')    count++;
                else if(board[i][2-i]=='O')   count--;
            }
        }
        if(count==2)    return true;
        return false;
    }

    private void makeRandomMove(char [][] board){
        Random rand=new Random();
        while(true){
            int x=rand.nextInt(3);
            int y=rand.nextInt(3);
            if(board[x][y]==' '){
                board[x][y]='O';
                printMove(x, y);
                return;
            }
        }
    }

    private void printMove(int x,int y){
        System.out.println("The (X,Y) coordinates are ("+x+","+y+")");
    }
}
