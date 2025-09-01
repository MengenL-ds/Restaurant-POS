/*
This class represents all the dishes and drinks offered by restaurant L'art Du Chef
*/

package model;

import model.exceptions.GetBeverageByIDException;
import model.exceptions.GetDishByIDException;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class LartduchefMenu {
    protected List<Dishes> lartduchefMenuDishes;
    protected List<Beverages> lartduchefMenuBeverages;

    // Constructor
    public LartduchefMenu() {
        Dishes tartare = new Dishes("Cumbrian Beef Tartare", "Meat", 1, 1, 14.99);
        Dishes scalop = new Dishes("Isle Of Skye Scallop", "Seafood", 1, 2, 16.99);
        Dishes lobster = new Dishes("Grilled Native Lobster", "Seafood", 1, 3, 21.99);
        Dishes vegetables = new Dishes("Stuffed Vegetables", "Vegetables", 1, 4, 12.99);
        Dishes cod = new Dishes("Cornish Cod", "Seafood", 2, 5, 26.99);
        Dishes salmon = new Dishes("Confit Salmon", "Seafood", 2, 6, 24.99);
        Dishes blueGrey = new Dishes("Cumbrian Blue Grey", "Meat", 2, 7, 30.99);
        Dishes pigeon = new Dishes("Pigeon Trilogy", "Meat", 2, 8, 38.99);
        Dishes parfait = new Dishes("Pigeon Trilogy", "Dessert", 3, 9, 10.99);
        Dishes cremeux = new Dishes("Chocolate Cremeux", "Dessert", 3, 10, 11.99);
        Dishes souffle = new Dishes("Chocolate Souffle", "Dessert", 3, 11, 9.99);
        Dishes nougat = new Dishes("Evanescent Nougat", "Dessert", 3, 12, 14.99);
        lartduchefMenuDishes = Stream.of(tartare, scalop, lobster, vegetables, cod, salmon, blueGrey, pigeon, parfait,
                cremeux, souffle, nougat).collect(Collectors.toList());
        Beverages jeanFoillard = new Beverages("Jean Foillard Morgon Côte du Py", 1, 89.99);
        Beverages domaineGuiberteu = new Beverages("Domaine Guiberteau Saumur Blanc", 2, 99.99);
        Beverages camilleGiroud = new Beverages("Camille Giroud Bourgogne Rouge", 3, 129.99);
        Beverages domaineLeroy = new Beverages("Domaine Leroy Closde Vougeot Grand Cru", 4, 1199.99);
        Beverages sangria = new Beverages("Sangria", 5, 12.99);
        Beverages dryMartini = new Beverages("Dry Martini", 6, 10.99);
        Beverages cocacola = new Beverages("Cocacola", 7, 11.99);
        lartduchefMenuBeverages = Stream.of(jeanFoillard, domaineGuiberteu, camilleGiroud, domaineLeroy, sangria,
                dryMartini, cocacola).collect(Collectors.toList());
    }

    // EFFECTS: Return a dish given the corresponding ID, otherwise throw GetDishByIDException.
    public Dishes getDishByID(int id) throws GetDishByIDException {
        for (Dishes lartduchefMenuDish : lartduchefMenuDishes) {
            if (lartduchefMenuDish.getDishID() == id) {
                return lartduchefMenuDish;
            }
        }
        throw new GetDishByIDException();
    }

    // EFFECTS: Return a beverage given the corresponding ID, otherwise throw GetBeverageByIDException.
    public Beverages getBeverageByID(int id) throws GetBeverageByIDException {
        for (Beverages lartduchefMenuBeverage : lartduchefMenuBeverages) {
            if (lartduchefMenuBeverage.getBeverageID() == id) {
                return lartduchefMenuBeverage;
            }
        }
        throw new GetBeverageByIDException();
    }
}
