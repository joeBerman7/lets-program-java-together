import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;
//import javax.swing.border.Border;

public class ticTacToe implements ActionListener {

    Random randon = new Random(); // for randomly who start to play
    JFrame frame = new JFrame(); // creating our frame 
    JPanel titlePanel = new JPanel(); // creating our title panel
    JPanel buttonPanel = new JPanel(); // creating our buttons panel
    JLabel textField = new JLabel(); // // creating our text label
    JButton[] buttons = new JButton[9]; // we will have 9 buttons so we create an array
    boolean player1Turn; // desiside who is the player that gonna start, true for the starter

    // our constructor
    ticTacToe() { 

        // frame settings
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // close our frame on X button there
        frame.setSize(800,800); // size of our frame
        frame.getContentPane().setBackground(new Color(148,93,233)); // background color of our frame
        frame.setLayout(new BorderLayout()); // our border layout
        frame.setVisible(true); // we can see our frame

        // text settings
        textField.setBackground(new Color(52,71,159)); // background text field
        textField.setForeground(Color.RED); // set the text color
        textField.setFont(new Font("Ink Free",Font.BOLD,75)); // set text font
        textField.setHorizontalAlignment(JLabel.CENTER); // set text location
        textField.setText("Tic-Tac-Toe");
        textField.setOpaque(true);

        // panel settings 
        titlePanel.setLayout(new BorderLayout());
        titlePanel.setBounds(0, 0, 800, 100);

        buttonPanel.setLayout(new GridLayout(3,3));
        buttonPanel.setBackground(new Color(150,150,150));

        // creating our 9 buttons
        for(int i=0; i<9; i++) {
            buttons[i] = new JButton();
            buttonPanel.add(buttons[i]);
            buttons[i].setFont(new Font("MV Boli",Font.BOLD,120));
            buttons[i].setFocusable(false);
            buttons[i].addActionListener(this);
        }

        // combining all togther 
        titlePanel.add(textField);
        frame.add(titlePanel,BorderLayout.NORTH);
        frame.add(buttonPanel);

        firstTurn();

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        
        // running the game it self
        for(int i=0; i<9; i++) {
            if(e.getSource()==buttons[i]) {
                if(player1Turn) {
                    if(buttons[i].getText()=="") {
                        buttons[i].setForeground(Color.RED);
                        buttons[i].setText("X");
                        player1Turn = false;
                        textField.setText("O turn");
                        check();
                    }
                }
                else {
                    if(buttons[i].getText()=="") {
                        buttons[i].setForeground(Color.BLUE);
                        buttons[i].setText("O");
                        player1Turn = true;
                        textField.setText("X turn");
                        check();
                    }
                }
            }
        }
        
    }

    // firstTurn method who begins to play 
    public void firstTurn() {

        // wait a little before present who is turn
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // randomly decide who begins 
        if(randon.nextInt(2)==0) {
            player1Turn = true;
            textField.setText("X turn");
        }
        else {
            player1Turn = false;
            textField.setText("O turn");
        }
    }

    public void check() {
        //this method will examine all the wining combination they got

        //check if X win
        if(
            (buttons[0].getText()=="X") &&
            (buttons[1].getText()=="X") &&
            (buttons[2].getText()=="X") 
        ){
            xWins(0, 1, 2);
        }

        if(
            (buttons[3].getText()=="X") &&
            (buttons[4].getText()=="X") &&
            (buttons[5].getText()=="X") 
        ){
            xWins(3, 4, 5);
        }

        if(
            (buttons[6].getText()=="X") &&
            (buttons[7].getText()=="X") &&
            (buttons[8].getText()=="X") 
        ){
            xWins(6, 7, 8);
        }

        if(
            (buttons[0].getText()=="X") &&
            (buttons[3].getText()=="X") &&
            (buttons[6].getText()=="X") 
        ){
            xWins(0, 3, 6);
        }

        if(
            (buttons[1].getText()=="X") &&
            (buttons[4].getText()=="X") &&
            (buttons[7].getText()=="X") 
        ){
            xWins(1, 4, 7);
        }

        if(
            (buttons[2].getText()=="X") &&
            (buttons[5].getText()=="X") &&
            (buttons[8].getText()=="X") 
        ){
            xWins(2, 5, 8);
        }

        if(
            (buttons[0].getText()=="X") &&
            (buttons[4].getText()=="X") &&
            (buttons[8].getText()=="X") 
        ){
            xWins(0, 4, 8);
        }

        if(
            (buttons[2].getText()=="X") &&
            (buttons[4].getText()=="X") &&
            (buttons[6].getText()=="X") 
        ){
            xWins(2, 4, 6);
        }

        // check if O win
        if(
            (buttons[0].getText()=="O") &&
            (buttons[1].getText()=="O") &&
            (buttons[2].getText()=="O") 
        ){
            oWins(0, 1, 2);
        }

        if(
            (buttons[3].getText()=="O") &&
            (buttons[4].getText()=="O") &&
            (buttons[5].getText()=="O") 
        ){
            oWins(3, 4, 5);
        }

        if(
            (buttons[6].getText()=="O") &&
            (buttons[7].getText()=="O") &&
            (buttons[8].getText()=="O") 
        ){
            oWins(6, 7, 8);
        }

        if(
            (buttons[0].getText()=="O") &&
            (buttons[3].getText()=="O") &&
            (buttons[6].getText()=="O") 
        ){
            oWins(0, 3, 6);
        }

        if(
            (buttons[1].getText()=="O") &&
            (buttons[4].getText()=="O") &&
            (buttons[7].getText()=="O") 
        ){
            oWins(1, 4, 7);
        }

        if(
            (buttons[2].getText()=="O") &&
            (buttons[5].getText()=="O") &&
            (buttons[8].getText()=="O") 
        ){
            oWins(2, 5, 8);
        }

        if(
            (buttons[0].getText()=="O") &&
            (buttons[4].getText()=="O") &&
            (buttons[8].getText()=="O") 
        ){
            oWins(0, 4, 8);
        }

        if(
            (buttons[2].getText()=="O") &&
            (buttons[4].getText()=="O") &&
            (buttons[6].getText()=="O") 
        ){
            oWins(2, 4, 6);
        }

    }

    public void xWins(int a, int b, int c) {
        // select wining buttons
        buttons[a].setBackground(Color.GREEN);
        buttons[b].setBackground(Color.GREEN);
        buttons[c].setBackground(Color.GREEN);

        //diasable game to continue
        for(int i=0; i<9; i++) {
            buttons[i].setEnabled(false);
        }

        textField.setText("X wins!!! :)"); // wining text :)
    }

    public void oWins(int a, int b, int c) {
        // select wining buttons
        buttons[a].setBackground(Color.GREEN);
        buttons[b].setBackground(Color.GREEN);
        buttons[c].setBackground(Color.GREEN);

        //diasable game to continue
        for(int i=0; i<9; i++) {
            buttons[i].setEnabled(false);
        }

        textField.setText("O wins!!! :)"); // wining text :)
    }
        
}