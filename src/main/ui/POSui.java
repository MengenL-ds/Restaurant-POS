package ui;

import model.LartduchefMenu;
import model.Restaurant;
import ui.tabs.HomeTab;

import javax.swing.*;
import java.io.IOException;

public class POSui extends JFrame {
    private final Restaurant restaurant;

    public static void main(String[] args) throws IOException {
        new LartduchefMenu();
        new POSui();
    }

    // EFFECTS: Initialize restaurant and GUI
    private POSui() throws IOException {
        super("POS System");

        restaurant = new Restaurant("L'art Du Chef");
        new HomeTab(restaurant);
    }
}
