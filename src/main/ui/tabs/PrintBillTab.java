/*
This is the GUI for the Bill. After the user clicks the button "Pay" on TableTab, the user will be directed to this tab

On this tab,
    User check all the dishes and drinks ordered by a given table, just like a regular restaurant bill
    User can press pay and the table will be reset to empty ready for the next customer to occupy
*/

package ui.tabs;

import model.Beverages;
import model.Dishes;
import model.Restaurant;
import model.Table;
import ui.Tab;

import javax.swing.*;
import java.awt.*;
import java.text.DecimalFormat;

public class PrintBillTab extends Tab {
    private final Table table;
    private JFrame frame;
    private JPanel totalGUI;
    private static final DecimalFormat df = new DecimalFormat("0.00");

    // EFFECTS: Constructor
    public PrintBillTab(Restaurant restaurant, Table table) {
        super(restaurant);
        this.table = table;
        showWindow();
    }

    // EFFECTS: Constructs the GUI
    private void showWindow() {
        frame = new JFrame("L'art Du Chef");

        totalGUI = new JPanel();
        totalGUI.setLayout(new BoxLayout(totalGUI, BoxLayout.PAGE_AXIS));
        totalGUI.setBorder(BorderFactory.createLineBorder(Color.red));
        printBill();
        backButton();
        frame.getContentPane().add(totalGUI);
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    // EFFECTS: Opens a new window with a JScrollPane, with the bill displayed inside
    private void printBill() {
        JPanel bill = new JPanel();
        totalGUI.add(bill);

        JTextArea billTextArea = new JTextArea("", 6, 40);
        billTextArea.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
        bill.setVisible(true);

        billTextArea.setText(getRestaurant().getResName()
                + "\n Route du Mont St Michel, 50170 Le Mont-Saint-Michel, France"
                + "\n Table: " + table.getTableNumber() + 1
                + "\n -----------------------------------------------------------"
                + "\n Dishes:"
                + getOrderedFood()
                + "\n -----------------------------------------------------------"
                + "\n Beverages:"
                + getOrderedDrinks()
                + "\n -----------------------------------------------------------"
                + "\n Subtotal: " + df.format(getRestaurant().getTableList().get(table.getTableNumber()).subtotal())
                + "€"
                + "\n Tax(20%): " + df.format(getRestaurant().getTableList().get(table.getTableNumber()).tax()) + "€"
                + "\n Total: " + df.format(getRestaurant().getTableList().get(table.getTableNumber()).total()) + "€"
                + "\n" + "\n Thank you for visiting");

        JScrollPane billPane = new JScrollPane(billTextArea);
        bill.add(billPane);
    }

    // EFFECTS: Returns all the drinks' name and price ordered from a table
    private String getOrderedDrinks() {
        String output = "";
        for (Beverages b : getRestaurant().getTableList().get(table.getTableNumber()).getOrderDrinks()) {
            output += "\n" + b.getBeverageName() + "  ---------------  " + b.getBeveragePrice();
        }
        if (output.equals("")) {
            return "No drinks were ordered";
        } else {
            return output;
        }
    }

    // EFFECTS: Returns all the dishes' name and price ordered from a table
    private String getOrderedFood() {
        String output = "";
        for (Dishes d : getRestaurant().getTableList().get(table.getTableNumber()).getOrderFood()) {
            output += "\n" + d.getDishName() + "    ---------------    " + d.getDishPrice();
        }
        if (output.equals("")) {
            return "No dishes were ordered";
        } else {
            return output;
        }
    }

    // EFFECTS: Displays a pay button that checks out the table.
    private void backButton() {
        JPanel backPane = new JPanel();
        totalGUI.add(backPane);
        JButton back = new JButton("Pay");

        backPane.add(back);

        setLayout(new BoxLayout(totalGUI, BoxLayout.PAGE_AXIS));

        back.addActionListener(e -> {
            frame.setVisible(false);
            getRestaurant().checkOutTable(table.getTableNumber());
            getRestaurant().occupiedTables();
            new CheckOccupAndAddNewTableTab(getRestaurant());
        });
    }
}
