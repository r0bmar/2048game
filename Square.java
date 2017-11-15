/**
 * This class models one square on the 2048 board.
 * A square can be empty, denoted by 0; 
 * or it can hold a tile with a number in {2,4,8,16,32,64,...}. 
 * 
 * @author Robin Markwitz & Neosh Sheikh
 * @version 1.0
 */

public class Square
{
    private int x; // the value of the tile sitting on the square, or 0 for empty

    // create a square with the value x, or empty if x is not legal 
    public Square(int x)
    {
        try {
            setSquare(x);
        }
        catch (Exception e) {}
    }

    // return the current value of the square 
    public int getSquare()
    {
        return x;
    }
    
    // return true iff the square is empty
    public boolean isEmpty()
    {
        return x == 0;
    }
    
    // put a tile of value x on the square, if x is legal; 
    // otherwise throw an Exception with a suitable error message 
    public void setSquare(int x) throws Exception
    {
        if (x==0 || ((Math.log(x)/Math.log(2))%1 == 0 && x>=2)) {
            this.x = x;
        }
        else throw new Exception("Please use a power of 2 greater than 1.");
    }
}
