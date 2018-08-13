/**
 * This class models the game 2048.
 * 
 * @author Robin Markwitz & Neosh Sheikh
 * @version 1.0
 */

import java.awt.*;
import java.awt.event.*;

public class Play2048 implements KeyListener
{
    private static int WINDOW_SIZE  = 700;
    private static int SQUARE_SIZE  = 100;
    private static int BORDER_WIDTH = (WINDOW_SIZE - GameState.getNoOfSquares() * SQUARE_SIZE) / 2;

    private boolean gameStarted = false;
    private boolean paused = false;

    private int highScore;
    private int current;
    private int currentScreen;
    private int flashingScreen;
    private Color   GRID_COLOR  = Color.BLACK;
    private static Color[] SQUARE_COLORS = new Color[12];
    private Color   BACK_COLOR  = new Color(210,210,210);

    private GameState gameState;
    private SimpleCanvas sc;

    // set up a game from the file file 
    public Play2048(String file) 
    {
        sc = new SimpleCanvas("2048", WINDOW_SIZE, WINDOW_SIZE, BACK_COLOR);
        sc.addKeyListener(this);
        startGame();
        setColor(255,255, 227); //default color
        try {
            gameState = new GameState(file);
        }
        catch (Exception e) {}
    }

    // set up a game using the probability p 
    public Play2048(double p) 
    {
        sc = new SimpleCanvas("2048", WINDOW_SIZE, WINDOW_SIZE, BACK_COLOR);
        sc.addKeyListener(this);
        startGame();
        setColor(255, 227, 227); //default color
        try {
            gameState = new GameState(p);
        }
        catch (Exception e) {}
    }

    // set up a game using the probability 0.3 
    public Play2048() 
    {
        sc = new SimpleCanvas("2048", WINDOW_SIZE, WINDOW_SIZE, BACK_COLOR);
        sc.addKeyListener(this);
        startGame();
        setColor(255, 227, 227); //default color
        try {
            gameState = new GameState(0.3);
        }
        catch (Exception e) {}
    }
    
    public static void main(String[] args) {
    	Play2048 play20481 = new Play2048();
    	play20481.startGame();
    	
    	
    	
    	
    }

    // set up the menu screen for the game
    private void startGame() {
        Font a = sc.getFont();
        sc.drawRectangle(BORDER_WIDTH, BORDER_WIDTH, WINDOW_SIZE - BORDER_WIDTH, WINDOW_SIZE - BORDER_WIDTH, BACK_COLOR);
        sc.drawRectangle(0, 0, BORDER_WIDTH, WINDOW_SIZE, GRID_COLOR);
        sc.drawRectangle(0, WINDOW_SIZE, WINDOW_SIZE, WINDOW_SIZE - BORDER_WIDTH, GRID_COLOR);
        sc.drawRectangle(WINDOW_SIZE - BORDER_WIDTH, 0, WINDOW_SIZE, WINDOW_SIZE, GRID_COLOR);
        sc.drawRectangle(0, 0, WINDOW_SIZE, BORDER_WIDTH, GRID_COLOR);
        sc.setFont(new Font("Comic Sans MS", 1, 72));
        for (int i = 255; i > 0; --i) {
            sc.drawRectangle(BORDER_WIDTH, BORDER_WIDTH, WINDOW_SIZE - BORDER_WIDTH, WINDOW_SIZE - BORDER_WIDTH,new Color((int)(i+255*Math.random()) % 255,(int)(i+255*Math.random()) % 255,(int)(i+255*Math.random()) % 255) );
            sc.drawString("2048", WINDOW_SIZE/2 - 80, WINDOW_SIZE/2 + 10, new Color((int)(i+255*Math.random()) % 255,(int)(i+255*Math.random()) % 255,(int)(i+255*Math.random()) % 255));
            sc.wait(2);
        }
        sc.setFont(a);
        flashingScreen++;
        menu(0);
    }

    private void menu( int x ) { //current screen = 0
        Color a,b,c;
        switch(x)
        {
            case 0: a = new Color(200,200,255); b = BACK_COLOR; c = BACK_COLOR; break;
            case 1: a = BACK_COLOR; b = new Color(200,200,255); c = BACK_COLOR; break;
            case 2: a = BACK_COLOR; b = BACK_COLOR; c = new Color(200,200,255); break;
            default: a = BACK_COLOR; b = BACK_COLOR; c = BACK_COLOR; break;
        }
        Font aF = sc.getFont();
        sc.setFont(new Font("Comic Sans MS", 1, 14));
        sc.drawRectangle(BORDER_WIDTH, BORDER_WIDTH, WINDOW_SIZE - BORDER_WIDTH, WINDOW_SIZE - BORDER_WIDTH, BACK_COLOR);
        sc.drawRectangle(BORDER_WIDTH, WINDOW_SIZE/2-100, WINDOW_SIZE-BORDER_WIDTH, WINDOW_SIZE/2 -50, a);
        sc.drawString("PLAY", WINDOW_SIZE/2-50, WINDOW_SIZE/2-75, new Color(50,50,50));
        sc.drawRectangle(BORDER_WIDTH, WINDOW_SIZE/2, WINDOW_SIZE-BORDER_WIDTH, WINDOW_SIZE/2 +50, b);
        sc.drawString("OPTIONS", WINDOW_SIZE/2-50, WINDOW_SIZE/2 + 25, new Color(50,50,50));
        sc.drawRectangle(BORDER_WIDTH, WINDOW_SIZE/2+100, WINDOW_SIZE-BORDER_WIDTH, WINDOW_SIZE/2 +150, c);
        sc.drawString("QUIT",WINDOW_SIZE/2-50, WINDOW_SIZE/2+125, new Color(50,50,50));
        sc.setFont(aF);
    }

    private void menuE( int x ) {//current screen = 1
        Color a,b,c;
        Font aF = sc.getFont();
        sc.setFont(new Font("Comic Sans MS", 1, 14));
        switch(x)
        {
            case 0: a = new Color(200,200,255); b = BACK_COLOR; c = BACK_COLOR; break;
            case 1: a = BACK_COLOR; b = new Color(200,200,255); c = BACK_COLOR; break;
            case 2: a = BACK_COLOR; b = BACK_COLOR; c = new Color(200,200,255); break;
            default: a = BACK_COLOR; b = BACK_COLOR; c = BACK_COLOR; break;
        }
        sc.drawRectangle(BORDER_WIDTH, BORDER_WIDTH, WINDOW_SIZE - BORDER_WIDTH, WINDOW_SIZE - BORDER_WIDTH, BACK_COLOR);
        sc.drawRectangle(0, 0, BORDER_WIDTH, WINDOW_SIZE, GRID_COLOR);
        sc.drawRectangle(0, WINDOW_SIZE, WINDOW_SIZE, WINDOW_SIZE - BORDER_WIDTH, GRID_COLOR);
        sc.drawRectangle(WINDOW_SIZE - BORDER_WIDTH, 0, WINDOW_SIZE, WINDOW_SIZE, GRID_COLOR);
        sc.drawRectangle(BORDER_WIDTH, BORDER_WIDTH, WINDOW_SIZE - BORDER_WIDTH, WINDOW_SIZE - BORDER_WIDTH, BACK_COLOR);
        sc.drawRectangle(BORDER_WIDTH, WINDOW_SIZE/2-100, WINDOW_SIZE-BORDER_WIDTH, WINDOW_SIZE/2 -50, a);
        sc.drawString("COLORS", WINDOW_SIZE/2-50, WINDOW_SIZE/2-75, new Color(50,50,50));
        sc.drawRectangle(BORDER_WIDTH, WINDOW_SIZE/2, WINDOW_SIZE-BORDER_WIDTH, WINDOW_SIZE/2 +50, b);
        sc.drawString("BOARD SIZE", WINDOW_SIZE/2-50, WINDOW_SIZE/2 + 25, new Color(50,50,50));
        sc.drawRectangle(BORDER_WIDTH, WINDOW_SIZE/2+100, WINDOW_SIZE-BORDER_WIDTH, WINDOW_SIZE/2 +150, c);
        sc.drawString("BACK",WINDOW_SIZE/2-50, WINDOW_SIZE/2+125, new Color(50,50,50));
    }

    private void menuCol( int x ) {//current screen = 2
        Color a,b,c,d,e,f,g;
        switch(x)
        {
            case 0: a = new Color(255,180,180); b = BACK_COLOR; c = BACK_COLOR; d = BACK_COLOR; e = BACK_COLOR; f = BACK_COLOR; g = BACK_COLOR; break;
            case 1: a = BACK_COLOR; b = new Color(180,180,255); c = BACK_COLOR; d = BACK_COLOR; e = BACK_COLOR; f = BACK_COLOR; g = BACK_COLOR; break;
            case 2: a = BACK_COLOR; b = BACK_COLOR; c = new Color(180,255,180); d = BACK_COLOR; e = BACK_COLOR; f = BACK_COLOR; g = BACK_COLOR; break;
            case 3: a = BACK_COLOR; b = BACK_COLOR; c = BACK_COLOR; d = new Color(255,255,180); e = BACK_COLOR; f = BACK_COLOR; g = BACK_COLOR; break;
            case 4: a = BACK_COLOR; b = BACK_COLOR; c = BACK_COLOR; d = BACK_COLOR; e = new Color(255,180,255); f = BACK_COLOR; g = BACK_COLOR; break;
            case 5: a = BACK_COLOR; b = BACK_COLOR; c = BACK_COLOR; d = BACK_COLOR; e = BACK_COLOR; f = new Color(180,255,255); g = BACK_COLOR; break;
            case 6: a = BACK_COLOR; b = BACK_COLOR; c = BACK_COLOR; d = BACK_COLOR; e = BACK_COLOR; f = BACK_COLOR; g = new Color(200,200,255); break;
            default: a = BACK_COLOR; b = BACK_COLOR; c = BACK_COLOR; d = BACK_COLOR; e = BACK_COLOR; f = BACK_COLOR; g = new Color(200,200,200); break;
        }
        sc.drawRectangle(BORDER_WIDTH, BORDER_WIDTH, WINDOW_SIZE - BORDER_WIDTH, WINDOW_SIZE - BORDER_WIDTH, BACK_COLOR);

        sc.drawRectangle(BORDER_WIDTH, WINDOW_SIZE/2-175, WINDOW_SIZE-BORDER_WIDTH, WINDOW_SIZE/2 -125, a);
        sc.drawString("RED", WINDOW_SIZE/2-50, WINDOW_SIZE/2-150, new Color(50,50,50));

        sc.drawRectangle(BORDER_WIDTH, WINDOW_SIZE/2-125, WINDOW_SIZE-BORDER_WIDTH, WINDOW_SIZE/2 -75, b);
        sc.drawString("BLUE", WINDOW_SIZE/2-50, WINDOW_SIZE/2 -100, new Color(50,50,50));

        sc.drawRectangle(BORDER_WIDTH, WINDOW_SIZE/2-75, WINDOW_SIZE-BORDER_WIDTH, WINDOW_SIZE/2-25, c);
        sc.drawString("GREEN", WINDOW_SIZE/2-50, WINDOW_SIZE/2-50, new Color(50,50,50));

        sc.drawRectangle(BORDER_WIDTH, WINDOW_SIZE/2-25, WINDOW_SIZE-BORDER_WIDTH, WINDOW_SIZE/2+25, d);
        sc.drawString("YELLOW", WINDOW_SIZE/2-50, WINDOW_SIZE/2, new Color(50,50,50));

        sc.drawRectangle(BORDER_WIDTH, WINDOW_SIZE/2+25,WINDOW_SIZE-BORDER_WIDTH, WINDOW_SIZE/2 +75, e);
        sc.drawString("PINK", WINDOW_SIZE/2-50, WINDOW_SIZE/2+50, new Color(50,50,50));

        sc.drawRectangle(BORDER_WIDTH, WINDOW_SIZE/2+75, WINDOW_SIZE-BORDER_WIDTH, WINDOW_SIZE/2 +125, f);
        sc.drawString("AQUA", WINDOW_SIZE/2-50, WINDOW_SIZE/2+100, new Color(50,50,50));

        sc.drawRectangle(BORDER_WIDTH, WINDOW_SIZE/2+125, WINDOW_SIZE-BORDER_WIDTH, WINDOW_SIZE/2 +175, g);
        sc.drawString("BACK",WINDOW_SIZE/2-50, WINDOW_SIZE/2+150, new Color(50,50,50));
    }

    private void menuSize( int x ) {//current screen = 3
        Color a,b,c,d;
        switch(x)
        {
            case 0: a = new Color(200,200,255); b = BACK_COLOR; c = BACK_COLOR;d = BACK_COLOR; break;
            case 1: a = BACK_COLOR; b = new Color(200,200,255); c = BACK_COLOR;d = BACK_COLOR; break;
            case 2: a = BACK_COLOR; b = BACK_COLOR; c = new Color(200,200,255);d = BACK_COLOR; break;
            case 3: a = BACK_COLOR; b = BACK_COLOR; c = BACK_COLOR;d = new Color(200,200,255); break;
            default: a = BACK_COLOR; b = BACK_COLOR; c = BACK_COLOR; d = BACK_COLOR; break;
        }
        sc.drawRectangle(BORDER_WIDTH, BORDER_WIDTH, WINDOW_SIZE - BORDER_WIDTH, WINDOW_SIZE - BORDER_WIDTH, BACK_COLOR);

        sc.drawRectangle(BORDER_WIDTH, WINDOW_SIZE/2-175, WINDOW_SIZE-BORDER_WIDTH, WINDOW_SIZE/2 -125, a);
        sc.drawString("4", WINDOW_SIZE/2-50, WINDOW_SIZE/2-150, new Color(50,50,50));
        sc.drawRectangle(BORDER_WIDTH, WINDOW_SIZE/2-75, WINDOW_SIZE-BORDER_WIDTH, WINDOW_SIZE/2 -25, b);
        sc.drawString("5", WINDOW_SIZE/2-50, WINDOW_SIZE/2-50, new Color(50,50,50));
        sc.drawRectangle(BORDER_WIDTH, WINDOW_SIZE/2+25, WINDOW_SIZE-BORDER_WIDTH, WINDOW_SIZE/2 +75, c);
        sc.drawString("6", WINDOW_SIZE/2-50, WINDOW_SIZE/2 + 50, new Color(50,50,50));
        sc.drawRectangle(BORDER_WIDTH, WINDOW_SIZE/2+125, WINDOW_SIZE-BORDER_WIDTH, WINDOW_SIZE/2 +175, d);
        sc.drawString("BACK",WINDOW_SIZE/2-50, WINDOW_SIZE/2+150, new Color(50,50,50));

    }

    private void menuInGame( int x ) {//pause is activated
        Color a,b,c;
        Font aF = sc.getFont();
        sc.setFont(new Font("Comic Sans MS", 1, 14));
        switch(x)
        {
            case 0: a = new Color(200,200,255); b = BACK_COLOR; c = BACK_COLOR; break;
            case 1: a = BACK_COLOR; b = new Color(200,200,255); c = BACK_COLOR; break;
            case 2: a = BACK_COLOR; b = BACK_COLOR; c = new Color(200,200,255); break;
            default: a = BACK_COLOR; b = BACK_COLOR; c = BACK_COLOR; break;
        }
        sc.drawRectangle(BORDER_WIDTH, BORDER_WIDTH, WINDOW_SIZE - BORDER_WIDTH, WINDOW_SIZE - BORDER_WIDTH, BACK_COLOR);
        sc.drawRectangle(BORDER_WIDTH, WINDOW_SIZE/2-100,WINDOW_SIZE-BORDER_WIDTH, WINDOW_SIZE/2 -50, a);
        sc.drawString("RESUME", WINDOW_SIZE/2-50, WINDOW_SIZE/2-75, new Color(50,50,50));
        sc.drawRectangle(BORDER_WIDTH, WINDOW_SIZE/2, WINDOW_SIZE-BORDER_WIDTH, WINDOW_SIZE/2 +50, b);
        sc.drawString("RESTART", WINDOW_SIZE/2-50, WINDOW_SIZE/2 + 25, new Color(50,50,50));
        sc.drawRectangle(BORDER_WIDTH, WINDOW_SIZE/2+100, WINDOW_SIZE-BORDER_WIDTH, WINDOW_SIZE/2 +150, c);
        sc.drawString("QUIT",WINDOW_SIZE/2-50, WINDOW_SIZE/2+125, new Color(50,50,50));
        sc.setFont(aF);
    }
    // get the computer to play a game of 2048 
    public void play2048()
    {
        int lP=0,rP=0,uP=0,dP=0;
        while (!gameState.gameOver()) { 
            lP=0;rP=0;uP=0;dP=0;
            for(int i = 0; i<gameState.getNoOfSquares(); ++i)
                for(int j = 0; j<gameState.getNoOfSquares(); ++j)
                {
                    int x = gameState.getBoard()[i][j].getSquare();
                    if(i>0 && x != 0)if( gameState.getBoard()[i-1][j].getSquare() == 0 || gameState.getBoard()[i-1][j].getSquare() == x)++uP;
                    if(i+1<gameState.getNoOfSquares() && x != 0)if( gameState.getBoard()[i+1][j].getSquare() == 0 || gameState.getBoard()[i+1][j].getSquare() == x)++dP;
                    if(j>0 && x != 0)if( gameState.getBoard()[i][j-1].getSquare() == 0 || gameState.getBoard()[i][j-1].getSquare() == x)++lP;
                    if(j+1<gameState.getNoOfSquares() && x != 0)if( gameState.getBoard()[i][j+1].getSquare() == 0 || gameState.getBoard()[i][j+1].getSquare() == x)++rP;
                }
            if(lP!=0 && lP >= rP && lP >=uP){gameState.left();drawBoard();continue;}
            else if(rP!=0 &&rP >=uP){gameState.right();drawBoard();continue;}
            else if(uP>0){gameState.up();drawBoard();continue;}
            else if(dP>0 &&(lP == 0 && rP ==0 && uP ==0)){gameState.down();drawBoard();continue;}

        }
    }

    // draw each Square from the board[][] array within GameState individually
    private void drawSquare(int x, int y) {

        Font f = sc.getFont();
        sc.setFont(new Font("Arial", 1, 28));
        if ((gameState.getBoard()[x][y].getSquare() < 1024) && (gameState.getBoard()[x][y].getSquare() != 0)){
            int n = (int) (Math.log(gameState.getBoard()[x][y].getSquare())/(Math.log(2)));
            sc.drawRectangle(SQUARE_SIZE*y + BORDER_WIDTH + 2, SQUARE_SIZE*x + BORDER_WIDTH + 2, SQUARE_SIZE*(y+1) + BORDER_WIDTH - 2, SQUARE_SIZE*(x+1) + BORDER_WIDTH - 2, SQUARE_COLORS[n-1]);
            sc.drawString(gameState.getBoard()[x][y].getSquare() + "", SQUARE_SIZE*y + BORDER_WIDTH + 25, SQUARE_SIZE*x + BORDER_WIDTH + 50, GRID_COLOR);
        }
        else if ((gameState.getBoard()[x][y].getSquare() >= 1024) && (gameState.getBoard()[x][y].getSquare() != 0)) {
            int n = (int) (Math.log(gameState.getBoard()[x][y].getSquare())/(Math.log(2)));
            sc.drawRectangle(SQUARE_SIZE*y + BORDER_WIDTH + 2, SQUARE_SIZE*x + BORDER_WIDTH + 2, SQUARE_SIZE*(y+1) + BORDER_WIDTH - 2, SQUARE_SIZE*(x+1) + BORDER_WIDTH - 2, SQUARE_COLORS[n-1]);
            sc.drawString(gameState.getBoard()[x][y].getSquare() + "", SQUARE_SIZE*y + BORDER_WIDTH +19, SQUARE_SIZE*x + BORDER_WIDTH + 50, GRID_COLOR);
        }
        else {
            sc.drawRectangle(SQUARE_SIZE*y + BORDER_WIDTH + 2, SQUARE_SIZE*x + BORDER_WIDTH + 2, SQUARE_SIZE*(y+1) + BORDER_WIDTH - 2, SQUARE_SIZE*(x+1) + BORDER_WIDTH - 2, new Color(225,225,225));
        }
        sc.setFont(f);
    }

    // set a new color scheme for the game
    public static void setColor(int r, int g, int bl) {
        int a = 0, b = 0, c = 0, d = 0, e = 0, f = 0;
        for (int i = 0; i < SQUARE_COLORS.length; i++) {
            if (r == 255) {
                a = r-0*i;
                d = -32*(i-8)+r;
            }
            else if (r == 227) {
                a = r-21*i;
                d = -21*7+r;
            }
            if (g == 255){
                b = g-0*i;
                e = -32*(i-8)+g;
            }
            else if (g == 227) {
                b = g-21*i;
                e = -21*7+g;
            }
            if (bl == 255) {
                c = bl-0*i;
                f = -32*(i-8)+bl;
            }
            else if (bl == 227){
                c = bl-21*i;
                f = -21*7+bl;
            }
            if (i < 8) {
                SQUARE_COLORS[i] = new Color(a, b, c);
            }
            else {
                SQUARE_COLORS[i] = new Color(d, e, f);
            }
        }

    }
    // handle the key typed event from the SQUARE field.
    public void keyTyped(KeyEvent e) {

    }

    // handle the key pressed event from the SQUARE field.
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        if (!gameState.gameOver() && gameStarted && !paused) {
            if (key == KeyEvent.VK_LEFT) {
                gameState.left();
                drawBoard();
            }
            if (key == KeyEvent.VK_RIGHT) {
                gameState.right();
                drawBoard();
            }
            if (key == KeyEvent.VK_UP) {
                gameState.up();
                drawBoard();
            }
            if (key == KeyEvent.VK_DOWN) {
                gameState.down();
                drawBoard();
            }
            if (key == KeyEvent.VK_ESCAPE) {
                paused = true;
                menuInGame(0);
            }
        }
        else if (gameStarted == false && currentScreen == 0) //MAIN MENU
        {
        		if(flashingScreen == 0) {
        			return;
        		}
            if (key == KeyEvent.VK_UP) {
                if(current == 0) current = 2;
                else --current;
                menu(current);
            }
            if (key == KeyEvent.VK_DOWN) {
                if(current == 2) current = 0;
                else ++current;
                menu(current);
            }
            if (key == KeyEvent.VK_ENTER && current == 0) {
                gameStarted = true;
                drawBoard();
            }
            if (key == KeyEvent.VK_ENTER && current == 1) {
                currentScreen = 1;
                current = 0;
                menuE(0);
            }
            if(key == KeyEvent.VK_ENTER && current == 2) {
            		System.exit(0);
            }
        }
        else if (gameStarted == false && currentScreen == 1) //EXTRA MENU
        {
            if (key == KeyEvent.VK_UP) {
                if(current == 0) current = 2;
                else --current;
                menuE(current);
            }
            if (key == KeyEvent.VK_DOWN) {
                if(current == 2) current = 0;
                else ++current;
                menuE(current);
            }
            if (key == KeyEvent.VK_ENTER)
            {
                switch(current)
                {
                    case 0: currentScreen = 2; current = 0; menuCol(0); break;
                    case 1: currentScreen = 3; current = 0; menuSize(0);break;
                    default: break;
                }
            }
            if (key == KeyEvent.VK_ESCAPE ||(key == KeyEvent.VK_ENTER && current == 2)) {
                currentScreen = 0;
                current = 0;
                menu(0);
            }
        }
        else if (gameStarted == false && currentScreen == 2) //COLOR MENU
        {
            if (key == KeyEvent.VK_UP) {
                if(current == 0) current = 6;
                else --current;
                menuCol(current);
            }
            if (key == KeyEvent.VK_DOWN) {
                if(current == 6) current = 0;
                else ++current;
                menuCol(current);
            }
            if (key == KeyEvent.VK_ENTER)
            {
                switch(current)
                {
                    case 0: setColor(255, 227, 227); currentScreen = 1; current = 0; menuE(0);break;
                    case 1: setColor(227, 227, 255); currentScreen = 1; current = 0; menuE(0);break;
                    case 2: setColor(227, 255, 227); currentScreen = 1; current = 0; menuE(0);break;
                    case 3: setColor(255, 255, 227); currentScreen = 1; current = 0; menuE(0);break;
                    case 4: setColor(255, 227, 255); currentScreen = 1; current = 0; menuE(0);break;
                    case 5: setColor(227, 255, 255); currentScreen = 1; current = 0; menuE(0);break;
                    case 6: currentScreen = 1; current = 0; menuE(0);break;
                    default: break;
                }
            }
            if (key == KeyEvent.VK_ESCAPE) {
                currentScreen = 1;
                current = 0;
                menuE(0);
            }
        }

        else if (gameStarted == false && currentScreen == 3) //Board-Size
        {
            if (key == KeyEvent.VK_UP) {
                if(current == 0) current = 3;
                else --current;
                menuSize(current);
            }
            if (key == KeyEvent.VK_DOWN) {
                if(current == 3) current = 0;
                else ++current;
                menuSize(current);
            }
            if (key == KeyEvent.VK_ENTER)
            {
                switch(current)
                {
                    case 0: GameState.setNoOfSquares(4); BORDER_WIDTH = (WINDOW_SIZE - GameState.getNoOfSquares() * SQUARE_SIZE) / 2; currentScreen = 1; current = 0; createNewBoard(); menuE(0); break;
                    case 1: GameState.setNoOfSquares(5); BORDER_WIDTH = (WINDOW_SIZE - GameState.getNoOfSquares() * SQUARE_SIZE) / 2; currentScreen = 1; current = 0; createNewBoard(); menuE(0); break;
                    case 2: GameState.setNoOfSquares(6); BORDER_WIDTH = (WINDOW_SIZE - GameState.getNoOfSquares() * SQUARE_SIZE) / 2; currentScreen = 1; current = 0; createNewBoard(); menuE(0); break;
                    case 3: currentScreen = 1; current = 0; menuE(0);break;
                    default: break;
                }
            }
            if (key == KeyEvent.VK_ESCAPE) {
                currentScreen = 1;
                current = 0;
                menuE(0);
            }
        }
        else if (paused) //InGameMenu
        {
            if (key == KeyEvent.VK_UP) {
                if(current == 0) current = 2;
                else --current;
                menuInGame(current);
            }
            if (key == KeyEvent.VK_DOWN) {
                if(current == 2) current = 0;
                else ++current;
                menuInGame(current);
            }
            if (key == KeyEvent.VK_ESCAPE || (key == KeyEvent.VK_ENTER && current == 0)) {
                paused = false;
                current = 0;
                drawBoard();
            }
            if (key == KeyEvent.VK_ENTER && current == 1) {
                paused = false;
                createNewBoard();
                current = 0;
            }
            if (key == KeyEvent.VK_ENTER && current == 2) {
                paused = false;
                gameStarted = false;
                current = 0;
                currentScreen = 0;
                menu(0);
                try {
                    gameState = new GameState(0.3);
                }
                catch (Exception r) {}
                current = 0;
            }
        }
        else if (gameState.gameOver() == true) {
            if (key == KeyEvent.VK_N) {
                createNewBoard();
            }
            else if (key == KeyEvent.VK_ESCAPE) {
                menu(0);
                gameStarted = false;
                try {
                    gameState = new GameState(0.3);
                }
                catch (Exception r) {} 
            }
        }
    }

    // handle the key-released event from the SQUARE field.
    public void keyReleased(KeyEvent e) {

    }

    // hraw the board onto the canvas.
    private void drawBoard() {
        if (gameState.gameOver() == false) {
            if (gameState.getScore() > highScore) highScore = gameState.getScore();
            sc.drawRectangle(BORDER_WIDTH, BORDER_WIDTH, WINDOW_SIZE - BORDER_WIDTH, WINDOW_SIZE - BORDER_WIDTH, BACK_COLOR);
            sc.drawRectangle(0, 0, BORDER_WIDTH, WINDOW_SIZE, GRID_COLOR);
            sc.drawRectangle(0, WINDOW_SIZE, WINDOW_SIZE, WINDOW_SIZE - BORDER_WIDTH, GRID_COLOR);
            sc.drawRectangle(WINDOW_SIZE - BORDER_WIDTH, 0, WINDOW_SIZE, WINDOW_SIZE, GRID_COLOR);
            sc.drawRectangle(0, 0, WINDOW_SIZE, BORDER_WIDTH, GRID_COLOR);
            sc.drawString("Score = " + gameState.getScore(), BORDER_WIDTH, BORDER_WIDTH - 5, BACK_COLOR);
            sc.drawString("High Score = " + highScore, WINDOW_SIZE - BORDER_WIDTH - 80, BORDER_WIDTH - 5, BACK_COLOR);
            for (int i = 0; i < GameState.getNoOfSquares(); i++) {
                for (int j = 0; j < GameState.getNoOfSquares(); j++) {
                    drawSquare(i,j);
                }
            }
        }
        else {
            sc.drawRectangle(BORDER_WIDTH, BORDER_WIDTH, WINDOW_SIZE - BORDER_WIDTH, WINDOW_SIZE - BORDER_WIDTH, BACK_COLOR);
            sc.drawRectangle(0, 0, BORDER_WIDTH, WINDOW_SIZE, GRID_COLOR);
            sc.drawRectangle(0, WINDOW_SIZE, WINDOW_SIZE, WINDOW_SIZE - BORDER_WIDTH, GRID_COLOR);
            sc.drawRectangle(WINDOW_SIZE - BORDER_WIDTH, 0, WINDOW_SIZE, WINDOW_SIZE, GRID_COLOR);
            sc.drawRectangle(0, 0, WINDOW_SIZE, BORDER_WIDTH, GRID_COLOR);
            sc.setFont(new Font("Comic Sans MS", 1 ,36));
            sc.drawString("Game Over", WINDOW_SIZE/2 - 90, WINDOW_SIZE/2 - 10 , GRID_COLOR);
            sc.setFont(new Font("Comic Sans MS", 2 ,18));
            sc.drawString("Score = " + gameState.getScore(), WINDOW_SIZE/2 - 90, WINDOW_SIZE/2 + 25, GRID_COLOR);
            sc.drawString("Press N to retry.", WINDOW_SIZE/2 - 95, WINDOW_SIZE/2 + 95, Color.blue);
            sc.setFont(new Font("Comic Sans MS", 2 ,14));
        }
    }

    private void createNewBoard()
    {
        try {
            gameState = new GameState(0.3);
            drawBoard();
        }
        catch (Exception r) {}
    }
}

