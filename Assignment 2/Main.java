import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner console = new Scanner(System.in);

        Menu eatAtJoesMenu = new Menu(); // iterate over all menu items
        eatAtJoesMenu.addItem("Lobster Dinner", 2, 24.99, false);
        eatAtJoesMenu.addItem("Ice Cream Sundae", 3, 5.99, false);
        eatAtJoesMenu.addItem("Grilled Chicken", 2, 14.99, true);
        eatAtJoesMenu.addItem("Garden Salad", 1, 4.99, true);
        eatAtJoesMenu.addItem("Apple Pie", 3, 8.99, false);
        eatAtJoesMenu.addItem("French Fries", 1, 5.99, false);

        String itemName;
        int category;
        boolean heartHealthy;
        double price;

        System.out.println("Would you like to browse menu options?: Yes(1)/No(0)");
        int input = console.nextInt();
        while (input == 1) {
            int choice = optionList(console);

            if (choice == 1) {
                MenuIterator itr = eatAtJoesMenu.getAllItemsItr();
                System.out.println("All Menu Items:");
                while (itr.hasNext()) {
                    MenuItem item = itr.getItem();
                    System.out.println(item.getItemName() + " $" + item.getPrice());
                    itr.next();
                }
                System.out.println("View another option?: Yes(1)/No(0)");
                input = console.nextInt();

            } else if (choice == 2) {
                MenuIterator itrItem = eatAtJoesMenu.getItemItr(1);
                System.out.println("All Appetizers:");
                while (itrItem.hasNext()) {
                    MenuItem item = itrItem.getItem();
                    System.out.println(item.getItemName() + " $" + item.getPrice());
                    itrItem.next();
                }
                System.out.println("View another option?: Yes(1)/No(0)");
                input = console.nextInt();

            } else if (choice == 3) {
                MenuIterator itrItem = eatAtJoesMenu.getItemItr(2);
                System.out.println("All Main Dishes:");
                while (itrItem.hasNext()) {
                    MenuItem item = itrItem.getItem();
                    System.out.println(item.getItemName() + " $" + item.getPrice());
                    itrItem.next();
                }
                System.out.println("View another option?: Yes(1)/No(0)");
                input = console.nextInt();

            } else if (choice == 4) {
                MenuIterator itrItem = eatAtJoesMenu.getItemItr(3);
                System.out.println("All Desserts:");
                while (itrItem.hasNext()) {
                    MenuItem item = itrItem.getItem();
                    System.out.println(item.getItemName() + " $" + item.getPrice());
                    itrItem.next();
                }
                System.out.println("View another option?: Yes(1)/No(0)");
                input = console.nextInt();

            } else if (choice == 5) {
                MenuIterator itrHeartHealth = eatAtJoesMenu.getHeartHealthyItemsItr(true);
                System.out.println("All Heart Healthy Menu Items:");
                while (itrHeartHealth.hasNext()) {
                    MenuItem item = itrHeartHealth.getItem();
                    System.out.println(item.getItemName() + " $" + item.getPrice());
                    itrHeartHealth.next();
                }
                System.out.println("View another option?: Yes(1)/No(0)");
                input = console.nextInt();

            } else if (choice == 6) {
                System.out.println("Choose max price");
                Double maxPrice = console.nextDouble();
                MenuIterator itrPrice = eatAtJoesMenu.getPriceItr(maxPrice);
                System.out.println("Dishes under $" + maxPrice + ":");
                while (itrPrice.hasNext()) {
                    MenuItem item = itrPrice.getItem();
                    System.out.println(item.getItemName() + " $" + item.getPrice());
                    itrPrice.next();
                }
                System.out.println("View another option?: Yes(1)/No(0)");
                input = console.nextInt();

            } else if (choice == 7) {
                itemName = getName(console);
                category = getCategory(console);
                heartHealthy = getHealth(console);
                price = getPrice(console);
                eatAtJoesMenu.addItem(itemName, category, price, heartHealthy);
                System.out.println("View another option?: Yes(1)/No(0)");
                input = console.nextInt();

            } else if (choice == 8) {
                MenuIterator itr = eatAtJoesMenu.getAllItemsItr();
                while (itr.hasNext()) {
                    MenuItem item = itr.getItem();
                    System.out.println(item.getItemName() + " $" + item.getPrice());
                    if (deleteOption(console) == true) {
                        System.out.println("Deleted " + item.getItemName());
                        eatAtJoesMenu.deleteItem(itr);
                    } else {
                        System.out.println("Did not delete " + item.getItemName());
                        itr.next();
                    }
                }
                System.out.println("View another option?: Yes(1)/No(0)");
                input = console.nextInt();
            }
        }
        System.out.println("Goodbye.");
    }

    public static int optionList(Scanner console) {
        System.out.println("Please choose an option: ");
        System.out.println("1 - Display all menu items\n" + "2 - Display all appetizers\n"
                + "3 - Display all main dishs\n" + "4 - Display all desserts\n" +
                "5 - Display all heart healthy items\n" + "6 - Display all dishes under a specified price\n"
                + "7 - Add menu item\n" + "8 - Remove menu item\n");
        int choice = console.nextInt();
        while (choice < 1 || choice > 8) {
            System.out.println("Not valid option: ");
            System.out.println("1 - Display all menu items\n" + "2 - Display all appetizers\n"
                    + "3 - Display all main dishs\n" + "4 - Display all desserts\n" +
                    "5 - Display all heart healthy items\n" + "6 - Display all dishes under a specified price\n"
                    + "7 - Add menu item\n" + "8 - Remove menu item\n");
            choice = console.nextInt();
        }
        return choice;
    }

    private static String getName(Scanner console) {
        System.out.println("Enter item name");
        String name = console.next();
        return name;
    }

    private static int getCategory(Scanner console) {
        System.out.println("Enter a number to choose a category: 1-Appetizer, 2-Main Dish, 3-Dessert");
        int category = console.nextInt();
        while (category < 1 || category > 3) {
            System.out.println("Invalid choice!");
            System.out.println("Enter a number to choose a category: 1-Appetizer, 2-Main Dish, 3-Dessert");
            category = console.nextInt();
        }
        return category;
    }

    private static boolean getHealth(Scanner console) {
        System.out.println("Is this dish heart healthy?: Yes(1)/No(0)");
        int health = console.nextInt();
        if (health == 1) {
            return true;
        } else {
            return false;
        }
    }

    private static double getPrice(Scanner console) {
        System.out.println("What is the price of this item?: ");
        Double price = console.nextDouble();
        return price;
    }

    private static boolean deleteOption(Scanner console) {
        System.out.println("Would you like to delete this item?: Yes(1)/No(0)");
        int delete = console.nextInt();
        if (delete == 1) {
            return true;
        } else {
            return false;
        }
    }
}