/*
This is the GUI for the Home tab. After the user runs the POS, this will be the first tab to appear on screen

On this tab,
    User can check which tables are occupied (Or tables where customers haven't paid)
    User can add a new table if not on the occupied list
    User can go back to the HomeTab
    User will be able to see which tables are occupied or unoccupied
    User can see the restaurant name on top of the frame
*/

package ui.tabs;

import model.Event;
import model.EventLog;
import model.Restaurant;
import model.exceptions.LogException;
import ui.ButtonNames;
import ui.ConsolePrinter;
import ui.LogPrinter;
import ui.Tab;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class HomeTab extends Tab {
    private static final String GREETING = "Home";
    private JFrame frame;
    private JPanel totalGUI;
    private JButton exitProgram;
    private JButton lartduchefButton;
    private JButton loadButton;
    private JButton saveButton;
    private LogPrinter logPrinter;

    // EFFECTS: Constructor
    public HomeTab(Restaurant restaurant) throws IOException {
        super(restaurant);
        showWindow();
    }

    // EFFECTS: Constructs the GUI
    public void showWindow() {
        frame = new JFrame("Home");

        totalGUI = new JPanel();
        totalGUI.setLayout(new BoxLayout(totalGUI, BoxLayout.PAGE_AXIS));
        totalGUI.setBorder(BorderFactory.createLineBorder(Color.red));
        placeGreeting();
        loadImageApp();
        homeButtons();
        frame.getContentPane().add(totalGUI);
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        frame.setResizable(false);
    }

    //EFFECTS: creates greeting at top of console
    private void placeGreeting() {
        JPanel greetingPanel = new JPanel();
        totalGUI.add(greetingPanel);
        JLabel greeting = new JLabel(GREETING);
        Font font = new Font("Courier", Font.BOLD, 20);
        greeting.setFont(font);
        greetingPanel.add(greeting);
    }

    // EFFECTS: Loads and displays the logo image
    private void loadImageApp() {
        ImageIcon imageIcon = new ImageIcon("Logo.jpeg");
        JPanel imagePanel = new JPanel();
        JLabel imageLabel = new JLabel();
        Image newImage = imageIcon.getImage().getScaledInstance(300, 300, Image.SCALE_SMOOTH);
        imageIcon = new ImageIcon(newImage);
        imageLabel.setIcon(imageIcon);
        imagePanel.add(imageLabel);
        totalGUI.add(imagePanel);
    }

    // EFFECTS: Constructs the home, save, load and quit buttons
    private void homeButtons() {
        JPanel homeButtonPanel = new JPanel();
        totalGUI.add(homeButtonPanel);

        lartduchefButton = new JButton(ButtonNames.RESTAURANT.getName());
        homeButtonPanel.add(lartduchefButton);

        saveButton = new JButton(ButtonNames.SAVE.getName());
        homeButtonPanel.add(saveButton);

        loadButton = new JButton(ButtonNames.LOAD.getName());
        homeButtonPanel.add(loadButton);

        exitProgram = new JButton(ButtonNames.QUIT.getName());
        homeButtonPanel.add(exitProgram);

        enterRestaurantAction();
        enterLoadAction();
        enterSaveAction();
        exitProgramAction();

    }

    // EFFECTS: When loadButton pressed, load from Restaurant.json
    private void enterLoadAction() {
        loadButton.addActionListener(e -> {
            String buttonPressed = e.getActionCommand();
            if (buttonPressed.equals(ButtonNames.LOAD.getName())) {
                loadRestaurant();
                JOptionPane.showMessageDialog(totalGUI, "Progress loaded", "Success",
                        JOptionPane.YES_OPTION);
            }
        });
    }

    // EFFECTS: When saveButton pressed, save progress to Restaurant.json
    private void enterSaveAction() {
        saveButton.addActionListener(e -> {
            String buttonPressed = e.getActionCommand();
            if (buttonPressed.equals(ButtonNames.SAVE.getName())) {
                saveRestaurant();
                JOptionPane.showMessageDialog(totalGUI, "Progress saved", "Success",
                        JOptionPane.YES_OPTION);
            }
        });
    }

    // EFFECTS: When L'art Du Chef button pressed, display L'art Du Chef main window
    private void enterRestaurantAction() {
        lartduchefButton.addActionListener(e -> {
            String buttonPressed = e.getActionCommand();
            if (buttonPressed.equals(ButtonNames.RESTAURANT.getName())) {
                frame.setVisible(false);
                new CheckOccupAndAddNewTableTab(getRestaurant());
            }
        });
    }

    // EFFECTS: When quit button pressed, stop program
    private void exitProgramAction() {
        exitProgram.addActionListener(e -> {
            String buttonPressed = e.getActionCommand();
            if (buttonPressed.equals(ButtonNames.QUIT.getName())) {
                frame.setVisible(false);

                logPrinter = new ConsolePrinter();
                try {
                    logPrinter.printLog(EventLog.getInstance());
                } catch (LogException ex) {
                    ex.printStackTrace();
                }
                System.exit(0);
            }
        });
    }
}
