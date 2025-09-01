/*
This is the GUI for the Restaurant menu. After the user enters L'art Du Chef from the initial tab. The user will be
taken to this tab.

On this tab,
    User can check which tables are occupied (Or tables where customers haven't paid)
    User can add a new table if not on the occupied list
    User can go back to the HomeTab
    User will be able to see which tables are occupied or unoccupied
    User can see the restaurant name on top of the frame
*/

package ui.tabs;

import model.Restaurant;
import model.Table;
import ui.ButtonNames;
import ui.Tab;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class CheckOccupAndAddNewTableTab extends Tab {
    private JPanel totalGUI;
    private JFrame frame;
    private JPanel descriptionPanel;
    private JButton addNewTableButton;
    private JButton checkOccupiedTablesButton;
    private JTextField textField;

    // EFFECTS: Constructor
    public CheckOccupAndAddNewTableTab(Restaurant restaurant) {
        super(restaurant);
        showWindow();
    }

    // EFFECTS: Constructs the GUI
    private void showWindow() {
        frame = new JFrame("L'art Du Chef");

        totalGUI = new JPanel();
        totalGUI.setLayout(new BoxLayout(totalGUI, BoxLayout.PAGE_AXIS));
        totalGUI.setBorder(BorderFactory.createLineBorder(Color.red));
        placeGreeting();
        addTextFieldsAndEnterButtons();
        description();
        descriptionForOccup();
        descriptionForNew();
        loadTextAreaData();
        backButton();
        frame.getContentPane().add(totalGUI);
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        frame.setResizable(false);
    }

    //EFFECTS: creates greeting at top of console
    private void placeGreeting() {
        JLabel greeting = new JLabel("L'art Du Chef");
        JPanel greetingPanel = new JPanel();
        greetingPanel.add(greeting);
        greetingPanel.setBorder(BorderFactory.createLineBorder(Color.black));
        totalGUI.add(greetingPanel);
    }

    // EFFECTS: Display a text field, and 2 buttons (add new table and check occupied table), given input on the text
    // field, add new table will add new table if it isn't occupied, otherwise show error. check occupied table will
    // check occupied table depending on value inputted in text field, otherwise add new table.
    private void addTextFieldsAndEnterButtons() {
        JPanel textFieldAndButtonPanel = new JPanel();
        textFieldAndButtonPanel.setBorder(BorderFactory.createLineBorder(Color.blue));
        totalGUI.add(textFieldAndButtonPanel);

        textField = new JTextField();
        textField.setPreferredSize(new Dimension(250, 20));
        textFieldAndButtonPanel.add(textField);

        checkOccupiedTablesButton = new JButton(ButtonNames.OCCUPIED.getName());
        textFieldAndButtonPanel.add(checkOccupiedTablesButton);

        addNewTableButton = new JButton(ButtonNames.ADDTABLE.getName());
        textFieldAndButtonPanel.add(addNewTableButton);

        addNewTableAction();
        checkOccupiedTableAction();
    }

    // EFFECTS: If not 0 < tableNum < 51 show error. Add new table if the table is not occupied otherwise display error.
    private void addNewTableAction() {
        addNewTableButton.addActionListener(e -> {
            int tableNumb = Integer.parseInt(textField.getText());
            if (tableNumb < 1 || 50 < tableNumb) {
                JOptionPane.showMessageDialog(totalGUI, "Please select a table between 1 and 50", "Error",
                        JOptionPane.ERROR_MESSAGE);
            } else if (!getRestaurant().occupiedTables.contains(tableNumb)) {
                frame.setVisible(false);
                new TableTab(getRestaurant(), new Table(tableNumb));
            }
        });
    }

    // EFFECTS: If not 0 < tableNum < 51 show error. Add new table if the table is not occupied otherwise check table.
    private void checkOccupiedTableAction() {
        checkOccupiedTablesButton.addActionListener(e -> {
            int tableNumb = Integer.parseInt(textField.getText());
            if (tableNumb < 1 || 50 < tableNumb) {
                JOptionPane.showMessageDialog(totalGUI, "Please select a table between 1 and 50", "Error",
                        JOptionPane.ERROR_MESSAGE);
            } else if (getRestaurant().occupiedTables.contains(tableNumb)) {
                frame.setVisible(false);
                new TableTab(getRestaurant(), getRestaurant().getTableList().get(tableNumb - 1));
            }
        });
    }

    // EFFECTS: Display the brief description of what is in each JScrollPane
    private void description() {
        descriptionPanel = new JPanel();
        descriptionPanel.setLayout(new GridLayout(1, 2));
        descriptionPanel.setBorder(BorderFactory.createLineBorder(Color.pink));
        totalGUI.add(descriptionPanel);
    }

    // EFFECTS: Display the brief description "Currently occupied tables"
    private void descriptionForOccup() {
        JPanel descriptionForOccupPanel = new JPanel();
        descriptionPanel.setBorder(BorderFactory.createLineBorder(Color.blue));
        descriptionPanel.add(descriptionForOccupPanel);

        JLabel occupiedTables = new JLabel("Currently occupied tables");
        descriptionForOccupPanel.add(occupiedTables);
    }

    // EFFECTS: Display the brief description "Currently available tables"
    private void descriptionForNew() {
        JPanel descriptionForNew = new JPanel();
        descriptionPanel.add(descriptionForNew);

        JLabel availTables = new JLabel("Currently available tables");
        descriptionForNew.add(availTables);
    }

    // EFFECTS: Display all the occupied and available tables to be seated on 2 JScrollPane
    private void loadTextAreaData() {
        JPanel jpanePanel = new JPanel();
        descriptionPanel.setBorder(BorderFactory.createLineBorder(Color.pink));
        totalGUI.add(jpanePanel);

        JTextArea avaiTablesText = new JTextArea("", 6, 40);
        JTextArea occupTablesText = new JTextArea("", 6, 40);

        JScrollPane occupTablesPane = new JScrollPane(occupTablesText);
        occupTablesPane.setLocation(0, 50);
        jpanePanel.add(occupTablesPane);

        JScrollPane avaiTablesPane = new JScrollPane(avaiTablesText);
        avaiTablesPane.setLocation(70, 50);
        jpanePanel.add(avaiTablesPane);

        avaiTablesText.setVisible(true);
        occupTablesText.setVisible(true);

        avaiTablesText.setText(getAllAvaiTables());
        avaiTablesPane.setViewportView(avaiTablesText);

        occupTablesText.setText(getAllOccup());
        occupTablesPane.setViewportView(occupTablesText);
    }

    // EFFECTS: Return all the table numbers occupied
    private String getAllOccup() {
        String output = "";
        getRestaurant().occupiedTables();
        for (Integer i : getRestaurant().occupiedTables) {
            output += Integer.toString(i) + ", ";
        }
        if (output.equals("")) {
            return "No occupied tables at this time";
        } else {
            return output;
        }
    }

    // EFFECTS: Return all the table numbers available to be seated
    private String getAllAvaiTables() {
        String output = "";
        getRestaurant().availableTables();
        for (Integer i : getRestaurant().getRemainingAvailableTables()) {
            output += Integer.toString(i) + ", ";
        }
        if (output.equals("")) {
            return "No available tables at this time";
        } else {
            return output;
        }
    }

    // EFFECTS: Constructs the home button and quit button
    private void backButton() {
        JPanel backButtonPanel = new JPanel();
        totalGUI.add(backButtonPanel);

        JButton back = new JButton(ButtonNames.BACK.getName());
        backButtonPanel.add(back);

        back.addActionListener(e -> {
            String buttonPressed = e.getActionCommand();
            if (buttonPressed.equals(ButtonNames.BACK.getName())) {
                frame.setVisible(false);
                try {
                    new HomeTab(getRestaurant());
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        });
    }
}