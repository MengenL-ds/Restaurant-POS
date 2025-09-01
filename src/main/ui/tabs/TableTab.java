/*
This is the GUI for the table tab. After the user checks or adds a new table, this tab will pop up with the
corresponding saved details (Food and drink items) previously ordered.

On this tab,
    Users would be able to add new dishes and drinks to the table.
    Users can print bill if the customer requests it
    User can go back to the CheckOccupAndAddNewTableTab
    User will be able to see what dishes and drinks have been order by the table so far
    User can see the number of the current table
 */

package ui.tabs;

import model.Restaurant;
import model.Table;
import model.exceptions.GetBeverageByIDException;
import model.exceptions.GetDishByIDException;
import ui.ButtonNames;
import ui.Tab;

import javax.swing.*;
import java.awt.*;

public class TableTab extends Tab {
    private JFrame frame;
    private final Table currtableNum;
    private JPanel totalGUI;
    private JPanel descriptionPanel;
    private JButton addDrink;
    private JTextField idTextField;
    private AbstractButton addDish;

    // EFFECTS: Constructor
    public TableTab(Restaurant restaurant, Table table) {
        super(restaurant);
        currtableNum = table;
        showWindow();
    }

    // EFFECTS: Constructs the GUI
    private void showWindow() {
        frame = new JFrame("L'art Du Chef");

        totalGUI = new JPanel();
        totalGUI.setLayout(new BoxLayout(totalGUI, BoxLayout.PAGE_AXIS));
        totalGUI.setBorder(BorderFactory.createLineBorder(Color.red));
        currTableNumber();
        addDrinkOrAddDishButtons();
        description();
        descriptionForDrinks();
        descriptionForDishes();
        jpanel();
        backAndBillButtons();
        frame.getContentPane().add(totalGUI);
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        frame.setResizable(false);
    }

    //EFFECTS: creates current table number at top of console
    private void currTableNumber() {
        JPanel currentTableNumberPanel = new JPanel();
        JLabel tableNumber = new JLabel("You are currently viewing table: "
                + (currtableNum.getTableNumber()));
        currentTableNumberPanel.add(tableNumber);
        totalGUI.add(currentTableNumberPanel);
    }

    // EFFECTS: Displays a textfield, and 2 buttons (add drink and add dish). Depending on the input value in the
    // textfield, new drink or dish with that corresponding ID will be added to the table
    private void addDrinkOrAddDishButtons() {
        JPanel addDrinkDishTextfieldPanel = new JPanel();
        addDrinkDishTextfieldPanel.setLayout(new BoxLayout(addDrinkDishTextfieldPanel, BoxLayout.LINE_AXIS));
        addDrinkDishTextfieldPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        totalGUI.add(addDrinkDishTextfieldPanel);

        idTextField = new JTextField();
        addDrinkDishTextfieldPanel.add(idTextField);

        addDrink = new JButton(ButtonNames.ADDDRINK.getName());
        addDrinkDishTextfieldPanel.add(addDrink);

        addDish = new JButton(ButtonNames.ADDDISH.getName());
        addDrinkDishTextfieldPanel.add(addDish);

        addDrinkAction();
        addDishAction();
    }

    // EFFECTS: When addDrink button is pressed, add drink to table
    private void addDrinkAction() {
        addDrink.addActionListener(e -> {
            String buttonPressed = e.getActionCommand();
            if (buttonPressed.equals("Add drink")) {
                // Read id of item from idTextField and adds the item to the drink list of that table
                int drinkID = Integer.parseInt(idTextField.getText());
                try {
                    getRestaurant().serveBeverageToTable(drinkID, currtableNum.getTableNumber());
                    frame.setVisible(false);
                    new TableTab(getRestaurant(), currtableNum);
                } catch (GetBeverageByIDException ex) {
                    JOptionPane.showMessageDialog(totalGUI, "This drink item is not available", "Error",
                            JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }

    // EFFECTS: When addDish button is pressed, add dish to table
    private void addDishAction() {
        addDish.addActionListener(e -> {
            String buttonPressed = e.getActionCommand();
            if (buttonPressed.equals("Add dish")) {
                // Read id of item from idTextField and adds the item to the dish list of that table
                int dishID = Integer.parseInt(idTextField.getText());
                try {
                    getRestaurant().serveDishToTable(dishID, currtableNum.getTableNumber());
                    frame.setVisible(false);
                    new TableTab(getRestaurant(), currtableNum);
                } catch (GetDishByIDException getDishByIDException) {
                    JOptionPane.showMessageDialog(totalGUI, "This food item is not available", "Error",
                            JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }

    // EFFECTS: Places the following 2 descriptions side by side
    private void description() {
        descriptionPanel = new JPanel();
        descriptionPanel.setLayout(new GridLayout(1, 2));
        descriptionPanel.setBorder(BorderFactory.createLineBorder(Color.pink));
        totalGUI.add(descriptionPanel);
    }

    // EFFECTS: Displays a description of the contents within the drinks JScrollPane
    private void descriptionForDrinks() {
        JPanel descriptionForDrinks = new JPanel();
        descriptionPanel.setBorder(BorderFactory.createLineBorder(Color.blue));
        descriptionPanel.add(descriptionForDrinks);

        JLabel drinksAdded = new JLabel("Drinks added:");
        descriptionForDrinks.add(drinksAdded);
    }

    // EFFECTS: Displays a description of the contents within the dishes JScrollPane
    private void descriptionForDishes() {
        JPanel descriptionForDishes = new JPanel();
        descriptionPanel.add(descriptionForDishes);

        JLabel dishesAdded = new JLabel("Dishes added:");
        descriptionForDishes.add(dishesAdded);
    }

    // EFFECTS: Displays 2 JScrollPanes, one for currently ordered drinks and one for currently ordered dishes
    private void jpanel() {
        JPanel jpanePanel = new JPanel();
        jpanePanel.setBorder(BorderFactory.createLineBorder(Color.black));
        totalGUI.add(jpanePanel);

        JTextArea currDrinksText = new JTextArea("", 6, 40);
        JTextArea currDishesText = new JTextArea("", 6, 40);

        JScrollPane currDrinksPane = new JScrollPane(currDrinksText);
        currDrinksPane.setLocation(0, 50);
        jpanePanel.add(currDrinksPane);

        JScrollPane currDishesPane = new JScrollPane(currDishesText);
        currDishesPane.setLocation(70, 50);
        jpanePanel.add(currDishesPane);

        currDrinksText.setVisible(true);
        currDishesText.setVisible(true);

        currDrinksText.setText(getAllCurrDrinks());
        currDrinksPane.setViewportView(currDrinksText);

        currDishesText.setText(getAllCurrDishes());
        currDishesPane.setViewportView(currDishesText);
    }

    // EFFECTS: Returns string with all the drinks' names
    private String getAllCurrDrinks() {
        StringBuilder output = new StringBuilder();
        for (String s : getRestaurant().getTableList().get(currtableNum.getTableNumber()).orderedDrinks()) {
            output.append("\n").append(s);
        }
        return output.toString();
    }

    // EFFECTS: Returns string with all the dishes' names
    private String getAllCurrDishes() {
        StringBuilder output = new StringBuilder();
        for (String s : getRestaurant().getTableList().get(currtableNum.getTableNumber()).orderedFoods()) {
            output.append("\n").append(s);
        }
        return output.toString();
    }

    // EFFECTS: Constructs the back button and print bill button
    private void backAndBillButtons() {
        JPanel backAndBillButtonsPanel = new JPanel();
        totalGUI.add(backAndBillButtonsPanel);

        JButton back = new JButton("Back");
        backAndBillButtonsPanel.add(back);
        JButton printBill = new JButton("Print bill");
        backAndBillButtonsPanel.add(printBill);

        back.addActionListener(e -> {
            frame.setVisible(false);
            new CheckOccupAndAddNewTableTab(getRestaurant());
        });

        printBill.addActionListener(e -> {
            frame.setVisible(false);
            new PrintBillTab(getRestaurant(), currtableNum);
        });
    }
}
