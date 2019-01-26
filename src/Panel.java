import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Random;
import java.util.Scanner;

public class Panel extends JPanel{
    Random rng = new Random();
    public boolean willSwitch = false;
    public Float numTests = Float.valueOf("100");
    private int goodDoor;
    private int doorPicker;
    private Float winCounter = Float.intBitsToFloat(0);
    private Float lossCounter = Float.intBitsToFloat(0);
    private String winString = "Wins: ";
    private String lossString = "Losses: ";
    private float winPercentage;
    private float lossPercentage;
    private String winPercentString;
    private String lossPercentString;


    public Panel() {
        this.setPreferredSize(new Dimension(800, 600)); // set panel size
        this.setBackground(Color.WHITE);

        JButton runSimulation = new JButton("Run Simulation");
        runSimulation.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent run) {
                initialize();
                simulate();
                repaint();
            }
        });
        this.add(runSimulation);

        JButton numTests = new JButton("Number of Tests");
        numTests.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent cNumber) {
                String testHolder = JOptionPane.showInputDialog(null, "How many tests would you like to simulate?");
                if (testHolder != null && !testHolder.isEmpty()) {
                    Scanner keyCounter = new Scanner(testHolder);
                    setNumTests(keyCounter.nextFloat());
                }
            }
        });
        this.add(numTests);

        JButton restartButton = new JButton("Reset");
        restartButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent reset) {
                initialize();
                Driver.textBox.append("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\nSIMULATION RESET!");
            }
        });
        this.add(restartButton);
        initialize();
    }

    public void setNumTests(Float numberOfTests){
        numTests = numberOfTests;
    }

    private void initialize() {
        winCounter = Float.intBitsToFloat(0);
        lossCounter = Float.intBitsToFloat(0);
        winPercentage = 0;
        lossPercentage= 0;
        winString = "Wins: " + Float.toString(winCounter);
        winPercentString = "Win Percentage: " + Float.toString(winPercentage) + "%";
        lossString = "Losses: " + Float.toString(lossCounter);
        lossPercentString = "Loss Percentage: " + Float.toString(lossPercentage) + "%";
        repaint();
    }

    private void simulate(){
        for (int i = 0; i < numTests; i++){
            goodDoor = rng.nextInt(3);
            doorPicker = rng.nextInt(3);
            if (doorPicker != goodDoor){
                if (willSwitch){
                    winCounter++;
                }
                else if (!willSwitch){
                    lossCounter++;
                }
            }
            else if (doorPicker == goodDoor){
                if (willSwitch){
                    lossCounter++;
                }
                else if (!willSwitch){
                    winCounter++;
                }
            }
        }
        winPercentage = 100*winCounter/numTests;
        lossPercentage = 100*lossCounter/numTests;

        winString = "Wins: " + Float.toString(winCounter);
        winPercentString = "Win Percentage: " + Float.toString(winPercentage) + "%";
        lossString = "Losses: " + Float.toString(lossCounter);
        lossPercentString = "Loss Percentage: " + Float.toString(lossPercentage) + "%";

        Driver.textBox.append("\n" + winString + "\n" + winPercentString + "\n" + lossString + "\n" + lossPercentString);
    }

    public void setStubborn() {
        initialize();
        willSwitch = false;
        Driver.textBox.append("\nThe simulation will NOT switch selections.");
    }

    public void setSmart() {
        initialize();
        willSwitch = true;
        Driver.textBox.append("\nThe simulation WILL switch selections.");
    }

    public void showHelp() {
        JDialog helpPane = new JDialog(); // JDialog holds the option pane
        // Help & Learning Output to User
        JOptionPane.showMessageDialog(helpPane, "The villainous Dr. Schrödinger has developed a growth ray and Floatends to create an " +
                "army of giant cats to terrorize the city. \nYour team of secret agents has tracked him to his underground lab. You burst in " +
                "to find… that it’s a trap!\nDr. Schrödinger has slipped Floato the next room to activate his device and disabled the control " +
                "panel on the way out. \nFortunately, your teammates are masters of spy-craft. \nAgent Delta has hacked Floato the control " +
                "panel and managed to reactivate some of its functionality. \nMeanwhile, Agent Epsilon has searched through surveillance to " +
                "find the code for the door: 2, 10, 14. \nAll you have to do is enter those numbers and you’ll be free.\nBut there’s a problem. " +
                "\nThe control panel has only three buttons: one which adds 5 to the display number, one which adds 7, and one which takes the " +
                "square root.\nYou need to make the display output the numbers 2, 10, and 14, in that order.\nIt’s okay if it outputs different " +
                "numbers in between, but there’s no way to reset the display, so once you get to 2, you’ll have to continue on to 10 and 14 from " +
                "there.\nNot only that, Agent Delta explains that there are other traps built Floato the panel. If it ever shows the same number " +
                "more than once, a number greater than 60, or a non-whole number, the room will explode.\nRight now, the display reads zero, and" +
                " time is running out.\n\nThere’s only one way to solve the puzzle, with a few small variations. How will you input the code to " +
                "escape from Dr. Schrödinger’s lair and save the day?\n\nIn casual mode, you can make mistakes and continue to play.\n" +
                "In hardcore mode, there is a time limit of XXX minutes, and there is no room for error. If you run out of time or make any " +
                "mistakes, you are forced to restart.");
    }

    public void showAbout() {
        JDialog aboutPane = new JDialog(); // JDialog holds the message pane
        // Output to User
        JOptionPane.showMessageDialog(aboutPane, "\"The Giant Cat Army Riddle\", Lesson by Dan Finkel.\nView the original source here:\n" +
                "https://www.ted.com/talks/dan_finkel_can_you_solve_the_giant_cat_army_riddle\n\nMeet the Creators\nEducator: Dan Finkel    " +
                "\nTED-Ed Animation by Artrake Studio\n\n\nThis application \"The Giant Cat Army Riddle\" created by James Pan.");
    }

    public void paintComponent(Graphics g) { // draw method to visually show the frame
        super.paintComponent(g);
        g.setColor(Color.BLACK);
        g.drawString(winString, 30, 100);
        g.drawString(winPercentString, 30, 130);
        g.drawString(lossString, 30, 250);
        g.drawString(lossPercentString, 30, 280);

        g.setColor(Color.BLACK);
        g.fillOval(220, 80, 500, 500);
        g.setColor(Color.GREEN);
        g.fillArc(220, 80, 500, 500, 0, Math.round(360*winPercentage/100));
        g.setColor(Color.RED);
        g.fillArc(220, 80, 500, 500, Math.round(360*winPercentage/100), Math.round(360*lossPercentage/100));
    }
}
