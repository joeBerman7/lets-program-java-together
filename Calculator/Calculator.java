import javax.swing.*;
import java.awt.*;
import java.awt.event.*;


public class Calculator implements ActionListener{

    JFrame frame; // creating our frame
    JTextField textField ; // creating our text field
    JButton[] numberButtons = new JButton[10]; // creating array of nummber of buttons we will need
    JButton[] functionButtons = new JButton[9]; // creating array of function buttons that we will use
    JButton addButton, subButton, mulButton, divButton, decButton, equButton, delButton, clrButton, negButton; // named our function numbers
    JPanel panel; // creating our panel

    Font myFont = new Font("Ink Free",Font.BOLD,30); // set font to all of our buttons

    double num1=0, num2=0, result=0; // we declare our variables. for this calculator we will use only two numbers 
    char operator; // char that will hold or aretmetic operator

    Calculator() { // our constructor

        frame = new JFrame("Calculator"); // finishing build our frame in the constructor
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // we can close our program on the X button
        frame.setSize(420,530); // size of our frame
        frame.setResizable(false); // we couldn'r resize the frame
        frame.setLayout(null); 

        textField = new JTextField(); // finish creating our textfield
        textField.setBounds(50, 25, 300, 50); // create bounds to our text fileds
        textField.setFont(myFont); // declare our text field font
        textField.setEditable(false); // close our write option in the blank there
    
        // creating our operation buttons
        addButton = new JButton("+");
        subButton = new JButton("-");
        mulButton = new JButton("*");
        divButton = new JButton("/");
        decButton = new JButton(".");
        equButton = new JButton("=");
        delButton = new JButton("Del");
        clrButton = new JButton("Clr");
        negButton = new JButton("(-)");

        functionButtons[0] = addButton;
        functionButtons[1] = subButton;
        functionButtons[2] = mulButton;
        functionButtons[3] = divButton;
        functionButtons[4] = decButton;
        functionButtons[5] = equButton;
        functionButtons[6] = delButton;
        functionButtons[7] = clrButton;
        functionButtons[8] = negButton;

        //this is the loop for our function buttons
        for(int i=0; i<9; i++) {
            functionButtons[i].addActionListener(this);
            functionButtons[i].setFont(myFont);
            functionButtons[i].setFocusable(false);
        }

        //this is the loop for our number buttons
        for(int i=0; i<10; i++) {
            numberButtons[i] = new JButton(String.valueOf(i)); // finishing declare our number buttons
            numberButtons[i].addActionListener(this);
            numberButtons[i].setFont(myFont);
            numberButtons[i].setFocusable(false);
        }

        // this specific two buttons will look differently from others, and need to add them sepertly
        negButton.setBounds(50, 430, 100, 50);
        delButton.setBounds(150, 430, 100, 50);
        clrButton.setBounds(250, 430, 100, 50);

        // creating our buttons panels

        panel = new JPanel();
        panel.setBounds(50, 100, 300, 300);
        panel.setLayout(new GridLayout(4,4,10,10));
        //panel.setBackground(Color.GRAY); //if we want a backgroung for our layout for key numbers

        //first raw of calculator buttons
        panel.add(numberButtons[1]);
        panel.add(numberButtons[2]);
        panel.add(numberButtons[3]);
        panel.add(addButton);
        //second raw of calculator buttons
        panel.add(numberButtons[4]);
        panel.add(numberButtons[5]);
        panel.add(numberButtons[6]);
        panel.add(subButton);
        //third raw of calculator buttons
        panel.add(numberButtons[7]);
        panel.add(numberButtons[8]);
        panel.add(numberButtons[9]);
        panel.add(mulButton);
        panel.add(decButton);
        panel.add(numberButtons[0]);
        panel.add(equButton);
        panel.add(divButton);
        
        
        frame.add(panel);
        frame.add(delButton);
        frame.add(clrButton);
        frame.add(negButton);
        frame.add(textField);
        frame.setVisible(true); // our frame visible
        
    }

    public static void main(String[] args) {
        
        Calculator calc = new Calculator();

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        
        // creating a loop that every time one of our numbers will be hitten we will see it on the calculator screen
        for(int i=0; i<10; i++) {
            if(e.getSource() == numberButtons[i]) {
                textField.setText(textField.getText().concat(String.valueOf(i)));
            }
        }
        if(e.getSource()==decButton) {
            textField.setText(textField.getText().concat("."));
        }
        if(e.getSource()==addButton) {
            num1 = Double.parseDouble(textField.getText());
            operator = '+';
            textField.setText("+");
        }
        if(e.getSource()==subButton) {
            num1 = Double.parseDouble(textField.getText());
            operator = '-';
            textField.setText("");
        }
        if(e.getSource()==mulButton) {
            num1 = Double.parseDouble(textField.getText());
            operator = '*';
            textField.setText("");
        }
        if(e.getSource()==divButton) {
            num1 = Double.parseDouble(textField.getText());
            operator = '/';
            textField.setText("");
        }
        if(e.getSource()==equButton) {
            num2 = Double.parseDouble(textField.getText());

            switch(operator) {
                case '+':
                    result = num1+num2;
                    break;
                case '-':
                    result = num1-num2;
                    break;  
                case '*':
                    result = num1*num2;
                    break;  
                case '/':
                    result = num1/num2;
                    break;        
            }
            textField.setText(String.valueOf(result));
            num1=result;
        }
        if(e.getSource()==clrButton) {
            textField.setText("");
        }
        if(e.getSource()==delButton) {
            String string = textField.getText();
            textField.setText("");
            for(int i=0; i<string.length()-1; i++) {
                textField.setText(textField.getText()+string.charAt(i)); // delete our last char each time
            }
        }
        if(e.getSource()==negButton) {
            double temp = Double.parseDouble(textField.getText());
            temp*=-1;
            textField.setText(String.valueOf(temp));
        }

    }        
    
}