/**
 * This class models the 2048 game board.
 * 
 * @author Robin Markwitz & Neosh Sheikh
 * @version 1.0
 */

public class GameState
{
    private static int noOfSquares = 4; // the extent of the board in both directions
    private Square[][] board; // the current state of the board 
    private int score;        // the current score

    // initialise the board and the score directly from the arguments 
    // intended principally for testing 
    public GameState(Square[][] board, int score) 
    {
        this.board = board;
        this.score = score;
    }
    // initialise the board from the file file 
    // you may assume that the file has four lines, each with four integers separated by a space 
    // throw an exception if an empty board is generated 
    public GameState(String file) throws Exception 
    {
        board = new Square[noOfSquares][noOfSquares];
        score = 0;
        FileIO f = new FileIO(file);
        int check = 0;
        for(int i = 0; i < noOfSquares; ++i)
        {
            int start = 0;
            int end = f.lines.get(i).indexOf(' ', start);
            for(int j = 0; j < noOfSquares;++j)
            {
                int temp = Integer.valueOf(f.lines.get(i).substring(start, end));
                board[i][j] = new Square(temp);
                start = end+1;
                if(f.lines.get(i).indexOf( ' ' , start) != -1) end = f.lines.get(i).indexOf(" ", start);
                else end = f.lines.get(i).length();
                if(check == 0) if(temp != 0)  check++;
            }
        }
        if(check == 0) throw new Exception("Please use nonzero values within the grid.");
    }

    // initialise the board randomly; each square should be set to 2 with probability p, and to empty otherwise
    // throw an exception if an empty board is generated 
    public GameState(double p) throws Exception 
    {
        board = new Square[noOfSquares][noOfSquares];
        score = 0;
        int check = 0;
        for(int i = 0; i < noOfSquares; ++i)
        {
            for(int j = 0; j < noOfSquares; ++j)
            {
                if( p > Math.random())
                {
                    board[i][j] = new Square(2);
                    if(check == 0)++check;
                }
                else board[i][j] = new Square(0);
            }
        }
        if(check == 0) throw new Exception("Please use nonzero values within the grid.");
    }

    // return the current state of the board
    public Square[][] getBoard() 
    {
        return board;
    }

    // return the current score
    public int getScore()
    {
        return score;
    }
    
    public static void setNoOfSquares(int x)
    {
        noOfSquares = x;
    }
    
    public static int getNoOfSquares()
    {
        return noOfSquares;
    }

    // make a forward move in row r; return true iff something moved
    public boolean forward(Square[] row)
    {
        int j = 0;
        boolean check = false;
        for(int i = 0; i <noOfSquares;++i)
        {
            if( row[i].getSquare() != 0 )
            {
                Square x = row[j];
                row[j++] = row[i];
                row[i] = x;
                if( check == false) if(row[i].getSquare() != row[j-1].getSquare()) check = true;
            }
        }
        for(int i = 0; i <noOfSquares-1;++i)
        {
            if((row[i].getSquare() ==  row[i+1].getSquare()) && row[i].getSquare() != 0)
            {
                try {
                    row[i].setSquare(row[i].getSquare()*2);
                    row[i+1].setSquare(0);
                    if( check == false)check = true;
                }
                catch(Exception e){}
            }
        } 
        j = 0;
        for(int i = 0; i <noOfSquares;++i)
        {
            if( row[i].getSquare() != 0 )
            {
                Square x = row[j];
                row[j++] = row[i];
                row[i] = x;
            }
        } 
        return check;
    }

    // make a Left move
    public void left()
    {
        boolean check = false;
        for (int i = 0; i < noOfSquares; i++) {
            if (check == false) {
                check = forward(board[i]);
            }
            else forward(board[i]);
        }
        if (check) {
            score++;
            outerloop:
            for (int k = noOfSquares - 1; k >= 0 ; k--) {
                for (int j = noOfSquares - 1; j >= 0; j--) {
                    if (board[k][j].getSquare() == 0){
                        try {
                            board[k][j].setSquare(2);
                        }
                        catch (Exception E) {}
                        break outerloop;
                    }
                }
            }
        }
        else score--;
    }

    // make a Right move
    public void right()
    {
        this.clockwise();
        this.clockwise();
        this.left();
        this.anticlockwise();
        this.anticlockwise();
    }
    // make an Up move
    public void up()
    {
        this.anticlockwise();
        this.left();
        this.clockwise();
    }

    // make a Down move
    public void down()
    {
        this.clockwise();
        this.left();
        this.anticlockwise();
    }

    // rotate the board clockwise 90 degrees
    public void clockwise()
    {
        for(int i = 0; i < noOfSquares - 2;++i)
        {
            for(int j = i; j <noOfSquares - 1 -i;++j)
            {
                try{
                    Square x = board[i][j];
                    board[i][j] = board[noOfSquares - 1-j][i];
                    board[noOfSquares - 1 -j][i] = board[noOfSquares - 1 -i][noOfSquares - 1 -j];
                    board[noOfSquares - 1 -i][noOfSquares -1 -j] = board[j][noOfSquares -1 -i];
                    board[j][noOfSquares -1 -i] = x;
                }
                catch(Exception e){}
            }
        }
    }

    // rotate the board anti-clockwise 90 degrees
    public void anticlockwise()
    {
        for(int i = 0; i < noOfSquares - 2;++i)
        {
            for(int j = i; j <noOfSquares - 1 -i;++j)
            {
                try{
                    Square x = board[noOfSquares - 1 -j][i];
                    board[noOfSquares - 1 -j][i] = board[i][j];
                    board[i][j] = board[j][noOfSquares - 1 -i];
                    board[j][noOfSquares - 1 -i] = board[noOfSquares - 1 -i][noOfSquares - 1 -j];
                    board[noOfSquares -1 -i][noOfSquares -1 -j] = x;
                }
                catch(Exception e){}
            }
        }
    }

    // return true iff the game is over, i.e. no legal moves are possible
    public boolean gameOver()
    {
        for(int i = 0; i<noOfSquares; ++i)
            for(int j = 0; j<noOfSquares; ++j)
            {
                int x = board[i][j].getSquare();
                if(i>0)if( board[i-1][j].getSquare() == 0 || board[i-1][j].getSquare() == x) return false;
                if(i+1<noOfSquares)if( board[i+1][j].getSquare() == 0 || board[i+1][j].getSquare() == x) return false;
                if(j>0)if( board[i][j-1].getSquare() == 0 || board[i][j-1].getSquare() == x) return false;
                if(j+1<noOfSquares)if( board[i][j+1].getSquare() == 0 || board[i][j+1].getSquare() == x) return false;
            }
        return true;
    }
}
