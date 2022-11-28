import java.util.*;

public class Menu {
    MenuItem[] menuItems;
    int currentIndex = 0;

    public static final int APPETIZER = 1;
    public static final int MAIN_DISH = 2;
    public static final int DESSERT = 3;
    static final int MAX = 20;

    public Menu() {
        menuItems = new MenuItem[MAX];
    }

    public void addItem(String ItemName, int Category, double Price, boolean HeartHealthy) {
        MenuItem menuItem = new MenuItem(ItemName, Category, Price, HeartHealthy);
        if (currentIndex >= menuItems.length) {
            System.err.println("ERROR: Menu has reached the max amount of items");
        } else {
            menuItems[currentIndex] = menuItem;
            currentIndex++;
        }
    }

    public void deleteItem(MenuIterator itr) {
        AllItemsIterator itemItr = (AllItemsIterator) itr;
        int toDelete = itemItr.currentIndex;

        for (int i = toDelete; i < menuItems.length - 1; i++) {
            menuItems[i] = menuItems[i + 1];
        }

        if (menuItems.length == MAX) {
            menuItems[menuItems.length - 1] = null;
        }

        currentIndex--;
    }

    public MenuItem[] getMenuItems() {
        return menuItems;
    }

    private class AllItemsIterator implements MenuIterator {
        MenuItem[] menuItems;
        int currentIndex = 0;

        public AllItemsIterator(MenuItem[] menuItems) {
            this.menuItems = menuItems;
        }

        public boolean hasNext() {
            if (currentIndex >= menuItems.length || menuItems[currentIndex] == null) {
                return false;
            } else {
                return true;
            }
        }

        public void next() {
            if (currentIndex < 0) {
                throw new NoSuchElementException();
            } else
                currentIndex++;
        }

        public MenuItem getItem() {
            return menuItems[currentIndex];
        }
    }

    private class ItemIterator implements MenuIterator {
        MenuItem[] menuItems;
        int currentIndex = 0;
        int category;

        public ItemIterator(int category, MenuItem[] menuItems) {
            this.menuItems = menuItems;
            this.category = category;
        }

        public boolean hasNext() {
            if (currentIndex >= menuItems.length || menuItems[currentIndex] == null) {
                return false;
            } else {
                return checkCategory();
            }
        }

        public void next() {
            if (currentIndex < 0) {
                throw new NoSuchElementException();
            } else
                currentIndex++;
        }

        public boolean checkCategory() {
            MenuItem m = menuItems[currentIndex];
            if (m.getCategory() == category) {
                return true;
            } else {
                currentIndex++;
                return hasNext();
            }
        }

        public MenuItem getItem() {
            return menuItems[currentIndex];
        }
    }

    private class HeartHealthyIterator implements MenuIterator {
        private MenuItem[] menuItems;
        private int currentIndex = 0;
        private boolean HeartHealthy;

        public HeartHealthyIterator(boolean HeartHealthy, MenuItem[] menuItems) {
            this.menuItems = menuItems;
            this.HeartHealthy = HeartHealthy;
        }

        public boolean hasNext() {
            if (currentIndex >= menuItems.length || menuItems[currentIndex] == null) {
                return false;
            } else {
                return checkHealthy();
            }
        }

        public void next() {
            if (currentIndex < 0 || menuItems[currentIndex] == null) {
                throw new NoSuchElementException();
            } else
                currentIndex++;
        }

        public boolean checkHealthy() {
            MenuItem m = menuItems[currentIndex];
            if (m.getHeartHealthy() == HeartHealthy) {
                return true;
            } else {
                currentIndex++;
                return hasNext();
            }
        }

        public MenuItem getItem() {
            return menuItems[currentIndex];
        }

    }

    private class PriceIterator implements MenuIterator {
        private MenuItem[] menuItems;
        private int currentIndex = 0;
        private double price;

        public PriceIterator(double price, MenuItem[] menuItems) {
            this.menuItems = menuItems;
            this.price = price;
        }

        public boolean hasNext() {
            if (currentIndex > menuItems.length - 1 || menuItems[currentIndex] == null) {
                return false;
            } else {
                return checkPrice();
            }
        }

        public void next() {
            if (currentIndex < 0) {
                throw new NoSuchElementException();
            } else
                currentIndex++;
        }

        public boolean checkPrice() {
            MenuItem m = menuItems[currentIndex];
            if (m.getPrice() <= price) {
                return true;
            } else {
                currentIndex++;
                return hasNext();
            }
        }

        public MenuItem getItem() {
            return menuItems[currentIndex];
        }
    }

    public MenuIterator getAllItemsItr() {
        return new AllItemsIterator(menuItems);
    }

    public MenuIterator getHeartHealthyItemsItr(boolean HeartHealthy) {
        return new HeartHealthyIterator(HeartHealthy, menuItems);

    }

    public MenuIterator getItemItr(int category) {
        return new ItemIterator(category, menuItems);
    }

    public MenuIterator getPriceItr(Double price) {
        return new PriceIterator(price, menuItems);
    }
}
