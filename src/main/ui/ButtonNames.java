/*
This class represents all the button names used on Tabs
*/

package ui;

public enum ButtonNames {
    RESTAURANT("L'art Du Chef"),
    OCCUPIED("Check occupied tables"),
    LOAD("Load"),
    SAVE("Save"),
    ADDTABLE("Add a new table"),
    QUIT("Exit program"),
    BACK("back"),
    ADDDISH("Add dish"),
    ADDDRINK("Add drink");


    private final String name;

    // EFFECTS: Constructor
    ButtonNames(String name) {
        this.name = name;
    }

    // EFFECTS: returns name of this button
    public String getName() {
        return name;
    }
}
