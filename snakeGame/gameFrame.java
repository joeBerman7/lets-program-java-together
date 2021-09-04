import javax.swing.JFrame;

public class gameFrame extends JFrame{

    gameFrame() {

        this.add(new gamePanel()); // creating our game panel
        this.setTitle("Snake"); // srt the game title
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // the game will close when we hit the x button
        this.setResizable(false); // the game window will not be resizeable
        this.pack(); // colobarate all the components of the game 
        this.setVisible(true); // the game window will be visable
        this.setLocationRelativeTo(null); // game location will be in the middle

    }
}