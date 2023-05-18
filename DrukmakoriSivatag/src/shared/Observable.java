package shared;

import java.util.ArrayList;
import java.util.List;


public class Observable {
    private List<Observer> observers;

    public Observable() {
        observers = new ArrayList<Observer>();
    }

    public void notifyObservers() {
        for(Observer observer : observers) {
            observer.update();
        }
    }

    public void addObserver(Observer observer) {
        observers.add(observer);
    }
}
