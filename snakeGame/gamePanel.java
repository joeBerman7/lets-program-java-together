import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.Random;

import javax.swing.JPanel;

public class gamePanel extends JPanel implements ActionListener{

    static final int SCREEN_WIDTH = 600; 
    static final int SCREEN_HEIGHT = 600; 
    static final int UNIT_SIZE = 25; 
    static final int GAME_UNITS = (SCREEN_WIDTH*SCREEN_HEIGHT)/UNIT_SIZE;
    static final int DELAY = 75;
    // all the cordinates of our snake body parts
    final int x[] = new int[GAME_UNITS]; 
    final int y[] = new int[GAME_UNITS];
    // initialize the snake body length 
    int bodyParts = 6;
    int appelsEaten;
    int appelsX;
    int appelsY;
    // set the direction
    char direction = 'R'; // R = right, L = left, D = down, U = up
    boolean running = false; // for the begining of the game
    Timer timer;
    Random random;

    gamePanel(){
        random = new Random();
        this.setPreferredSize(new Dimension(SCREEN_WIDTH,SCREEN_HEIGHT));
        this.setBackground(new Color(45,68,112));
        this.setFocusable(true);
        this.addKeyListener(new MyKeyAdapter());
        startGame();
    }


    // starting a game
    public void startGame() { 
        newApple(); // create new apple
        running = true; // changing our running value from false to true to runing
        timer = new Timer(DELAY,this); // creating our timer
        timer.start(); // start our timer
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        draw(g);
    }

    public void draw(Graphics g) {

        if(running) {
            /* help us visualize the game cunstructing
            for(int i=0;i<SCREEN_HEIGHT/UNIT_SIZE;i++) {
                g.drawLine(i*UNIT_SIZE, 0, i*UNIT_SIZE, SCREEN_HEIGHT);
                g.drawLine(0, i*UNIT_SIZE, SCREEN_WIDTH, i*UNIT_SIZE);
            }
            */
            // draw the apple

            


            g.setColor(Color.red);
            g.fillOval(appelsX, appelsY, UNIT_SIZE, UNIT_SIZE);

            // draw the snake
            for(int i=0; i< bodyParts; i++) {
                if(i ==0) {
                    g.setColor(Color.green);
                    g.fillRect(x[i], y[i], UNIT_SIZE, UNIT_SIZE);  
                }

                else {
                    g.setColor(new Color(45,180,0));
                    g.fillRect(x[i], y[i], UNIT_SIZE, UNIT_SIZE); 
                }
            }
            // draw the score
            g.setColor(Color.red); // set the color
            g.setFont(new Font("Ink Free",Font.BOLD,40)); // set the font
            FontMetrics metrics = getFontMetrics(g.getFont()); // creating our location object
            g.drawString("Score: " +appelsEaten, (SCREEN_WIDTH - metrics.stringWidth("Score: "+appelsEaten))/2, g.getFont().getSize()); // center the messege in the metrics (in the center)

        }
        else {
            gameOver(g);
        }
    }

    // creating the apple randomlly cordinates in the game
    public void newApple() {
        appelsX = random.nextInt((int)(SCREEN_WIDTH/UNIT_SIZE))*UNIT_SIZE;
        appelsY = random.nextInt((int)(SCREEN_HEIGHT/UNIT_SIZE))*UNIT_SIZE;
    }

    // moves to snake
    public void move() {
        //increasing snake length
        for(int i = bodyParts; i>0; i--) {
          x[i] = x[i-1]; 
          y[i] = y[i-1];  
        }

        // chenging directions
        switch(direction) {
        case 'U':
            y[0] = y[0] - UNIT_SIZE;
            break;
        case 'D':
            y[0] = y[0] + UNIT_SIZE;
            break;
        case 'L':
            x[0] = x[0] - UNIT_SIZE;
            break; 
        case 'R':
            x[0] = x[0] + UNIT_SIZE;
            break;           
        }
    }

    // check if our snake eat the apple
    public void cheackApple(){
        if((x[0] == appelsX) && (y[0] == appelsY)) {
            bodyParts++; // snake get bigger
            appelsEaten++; // for the score
            newApple(); // generate our new apples
        }
    }

    public void checkCollisions(){
        //checks if head colides with body
        for(int i = bodyParts; i>0; i--) {
            if((x[0] == x[i]) && (y[0] == y[i])) {
                running = false;
            }
        }
        // check if head touches left border
        if(x[0]<0) {
            running = false;
        }
        // check if head touches right border
        if(x[0]> SCREEN_WIDTH) {
            running = false;
        }
        //check if head touch top broder
        if(y[0]<0) {
            running = false;
        }
        //check if head touch bottom border
        if(y[0]>SCREEN_HEIGHT) {
            running = false;
        }
        // stope the timer when our snake stop
        if(!running) {
            timer.stop();
        }
    }

    public void gameOver(Graphics g) {

        // score
        g.setColor(Color.red); // set the color
        g.setFont(new Font("Ink Free",Font.BOLD,40)); // set the font
        FontMetrics metrics1 = getFontMetrics(g.getFont()); // creating our location object
        g.drawString("Score: " +appelsEaten, (SCREEN_WIDTH - metrics1.stringWidth("Score: "+appelsEaten))/2, g.getFont().getSize());

        // game over text
        g.setColor(Color.red); // set the color
        g.setFont(new Font("Ink Free",Font.BOLD,75)); // set the font
        FontMetrics metrics2 = getFontMetrics(g.getFont()); // creating our location object
        g.drawString("Game Over", (SCREEN_WIDTH - metrics2.stringWidth("Game Over"))/2, SCREEN_HEIGHT/2); // center the messege in the metrics (in the center)
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        
        if(running) {
            move();
            cheackApple();
            checkCollisions();
        }
        repaint();
    }

    public class MyKeyAdapter extends KeyAdapter {

        @Override
        public void keyPressed(KeyEvent e) {
            switch(e.getKeyCode()) {
            case KeyEvent.VK_LEFT:
                if (direction != 'R') {
                    direction = 'L';
                }
                break;
            case KeyEvent.VK_RIGHT:
                if (direction != 'L') {
                    direction = 'R';

                }
                break;
            case KeyEvent.VK_UP:
                if (direction != 'D') {
                    direction = 'U';
                }   
                break;
            case KeyEvent.VK_DOWN: 
               if (direction != 'U') {
                   direction = 'D';
               }
               break;
            }
        
            
        }
    }

}