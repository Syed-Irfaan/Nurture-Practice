import java.util.*;

interface Observer {
    void update(String stockName, double price);
}

interface Stock {
    void register(Observer observer);
    void deregister(Observer observer);
    void notifyObservers();
}

class StockMarket implements Stock {
    private List<Observer> observers = new ArrayList<>();
    private String stockName;
    private double stockPrice;

    public void setStock(String stockName, double stockPrice) {
        this.stockName = stockName;
        this.stockPrice = stockPrice;
        notifyObservers();
    }

    public void register(Observer observer) {
        observers.add(observer);
    }

    public void deregister(Observer observer) {
        observers.remove(observer);
    }

    public void notifyObservers() {
        for (Observer observer : observers) {
            observer.update(stockName, stockPrice);
        }
    }
}

class MobileApp implements Observer {
    private String user;

    public MobileApp(String user) {
        this.user = user;
    }

    public void update(String stockName, double price) {
        System.out.println("MobileApp (" + user + ") received update: " + stockName + " is now ₹" + price);
    }
}

class WebApp implements Observer {
    private String user;

    public WebApp(String user) {
        this.user = user;
    }

    public void update(String stockName, double price) {
        System.out.println("WebApp (" + user + ") received update: " + stockName + " is now ₹" + price);
    }
}

public class ObserverPatternExample {
    public static void main(String[] args) {
        StockMarket market = new StockMarket();

        Observer mobileUser = new MobileApp("Alice");
        Observer webUser = new WebApp("Bob");

        market.register(mobileUser);
        market.register(webUser);

        market.setStock("TCS", 3450.75);
        market.setStock("INFY", 1510.50);

        market.deregister(webUser);
        market.setStock("WIPRO", 420.20);
    }
}
