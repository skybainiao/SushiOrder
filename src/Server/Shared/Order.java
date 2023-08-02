package Server.Shared;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Map;

public class Order implements Serializable {


    private Map<String, Integer> food;

    private int totalPrice;

    public Order(Map<String, Integer> food, int totalPrice) {
        this.food = food;
        this.totalPrice = totalPrice;
    }

    public void setTotalPrice(int totalPrice) {
        this.totalPrice = totalPrice;
    }

    public void setFood(Map<String, Integer> food) {
        this.food = food;
    }

    public int getTotalPrice() {
        return totalPrice;
    }

    public Map<String, Integer> getFood() {
        return food;
    }

    @Override
    public String toString() {
        return "Order{" +
                "food=" + food +
                ", totalPrice=" + totalPrice +
                '}';
    }
}
