import java.text.NumberFormat;
import javax.swing.JOptionPane;

// Mortgage GUI Calculator project

public class MortgageCalculatorGUI {

    public static void main(String[] args) {

        // declaring constant and variables 

        final byte mounthsInYyears = 12; // mounts in years
        final byte ofPresentage = 100; // total presentage

        String input=null, inputs=null, inputsi=null;
        int myPrincipal = 0;
        float annualInterstRate=0, actuallRate=0, periodInMounths=0, period=0;
        boolean err=true;

        // running the program 

        do{
            try{
                input = JOptionPane.showInputDialog("principal (1K shekels - 1M shekels): ");
                myPrincipal = Integer.parseInt(input);
                err=false;

                while (myPrincipal<1_000 || myPrincipal>1_000_000) { // create while loop becuse we check our condition until it works
                    JOptionPane.showMessageDialog(null,"Error, your principal should be a number between 1K to 1M! "); // printing error line
                    myPrincipal = Integer.parseInt(JOptionPane.showInputDialog("principal (1K shekels - 1M shekels): ")); // adding again the input line
                    if (myPrincipal>1000 && myPrincipal<1000000) // declare our condition
                        break; // if the condition will work we will break out of the loop
                }

            }catch(NumberFormatException e){
                e.printStackTrace();
            }
        }while(err);
        
        do{
            try{
                err = true;
                inputs = JOptionPane.showInputDialog("Annual Interst Rate: ");
                annualInterstRate = Float.parseFloat(inputs);
                err=false;
                while (annualInterstRate<0 || annualInterstRate>30) { // create while loop becuse we check our condition until it works
                    JOptionPane.showMessageDialog(null,"Error,enter a number between 0 to 30!"); // printing error line
                    annualInterstRate = Float.parseFloat(JOptionPane.showInputDialog("Annual Interst Rate: "));
                    if (myPrincipal>0 && myPrincipal<=30) // declare our condition
                        break; // if the condition will work we will break out of the loop
                } 
                actuallRate = annualInterstRate/(ofPresentage*mounthsInYyears); // creting new calculated variable  

            }catch(NumberFormatException e){
                e.printStackTrace();
            }
        }while(err);

        do{
            try{
                err = true;
                inputsi = JOptionPane.showInputDialog("Period (Years):");
                period = Float.parseFloat(inputsi);
                err=false;
                while (period<0 || period>30) { // create while loop becuse we check our condition until it works
                    JOptionPane.showMessageDialog(null,"Error,enter a number between 0 to 30!"); // printing error line
                    period = Float.parseFloat(JOptionPane.showInputDialog("Period (Years):"));
                    if (period>0 && period<=30) // declare our condition
                        break; // if the condition will work we will break out of the loop
                }
                periodInMounths = period*mounthsInYyears; // creting new calculated variable

            }catch(NumberFormatException e){
                e.printStackTrace();
            }
        }while(err);
        
        // calculations
        float parenthesisValue = 1 + actuallRate;
        double parenthesisValuePower = Math.pow(parenthesisValue,  periodInMounths);
        double Mortgage = myPrincipal * ((actuallRate * parenthesisValuePower)/(parenthesisValuePower -1 ));
               
        // formating statements
        NumberFormat currency = NumberFormat.getCurrencyInstance();
        String result = currency.format(Mortgage);
        
        JOptionPane.showMessageDialog(null, "The Mortgage is " + result);  

    }
}

////////////////////////////// End of the project ///////////////////////////////////
