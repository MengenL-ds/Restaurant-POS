# Restaurant Point-of-Service (POS) System

## Overview
This is a custom-built **restaurant POS (Point-of-Service) system**, designed to manage tables, track orders, and generate bills in a restaurant environment.  

I started this project because my father needed a POS solution that existing providers couldn’t offer: **flexibility, customization, and simplicity**. By building this system, I combined **software engineering principles** with practical requirements from a real business context.  

This repository demonstrates my skills in:
- **Software design & abstraction** (domain analysis, cohesion, coupling, inheritance)
- **Full-stack development** (backend logic, data persistence, GUI)
- **Documentation & testing** (requirements, user stories, reflections)

---

## Tech Stack
- **Language:** Java (with Swing/JavaFX for GUI)  
- **Persistence:** File-based storage (saving/loading state)  
- **Build Tool:** Maven/Gradle *(or javac/java if plain Java)*  
- **Version Control:** Git + GitHub  

---

## Getting Started

### Prerequisites
- Java 11+ installed  
- Git installed  

### Installation & Run
Clone the repository:
```
git clone https://github.com/<your-username>/restaurant-pos-system.git
cd restaurant-pos-system
```

### Compile and Run
```
javac -d bin src/**/*.java
java -cp bin Main
```

## Features
- Add both dishes and beverages to a table
- See which tables are occupied vs. vacant
- Automatically calculate totals, gratuities, and taxes
- Print bills and clear tables automatically
- Save and load orders across program runs
- Simple, intuitive GUI for restaurant staff
- Event logging (orders, checkouts, etc.)

## Demo
Example Console Output
```
Wed Mar 30 18:32:13 PDT 2022
Drink: Jean Foillard Morgon Côte du Py has been added to table: 11
Wed Mar 30 18:32:23 PDT 2022
Dish: Isle Of Skye Scallop has been added to table: 12
Wed Mar 30 18:32:25 PDT 2022
Drink: Domaine Guiberteau Saumur Blanc has been added to table: 12
Wed Mar 30 18:32:33 PDT 2022
Table: 12 has checked out, all items cleared
Wed Mar 30 18:32:37 PDT 2022
Table: 11 has checked out, all items cleared
```

## Insights
During development, I identified several improvements for future iterations:
- Abstraction: Create an abstract Item class with Dish and Beverage subclasses to simplify duplicated methods.
- GUI Structure: Use an abstract Tab class to reduce repetition between different UI tabs.
- Cohesion: Move billing logic (subtotal, total, tax calculations) out of Table into a dedicated Billing class.
- Coupling: Reduce dependency on table index mapping by encapsulating it into a dedicated class or utility.

## Future Developments
- Role-based access (staff vs. manager)
- ntegration with payment APIs
- Multi-restaurant support
- Analytics dashboard (popular items, busiest tables, peak times)
- Cloud based storage for multi device access

## Testing
Unit tests written with JUnit, run them:
```
mvn test
```
