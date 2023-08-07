package Server.Shared;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Map;

public class Order implements Serializable {


    private int orderId;
    private Map<String, Integer> foodName;

    private int totalPrice;

    private String orderStatus;
    private LocalDateTime orderTime;


    public Order( Map<String, Integer> foodName, int totalPrice,String orderStatus) {

        this.foodName = foodName;
        this.totalPrice = totalPrice;
        this.orderStatus=orderStatus;
        this.orderTime = LocalDateTime.now();
    }
    public Order(int orderId, Map<String, Integer> foodName, int totalPrice,String orderStatus) {
        this.orderId=orderId;
        this.foodName = foodName;
        this.totalPrice = totalPrice;
        this.orderStatus=orderStatus;
        this.orderTime = LocalDateTime.now();
    }


    // Getter and Setter
    public LocalDateTime getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(LocalDateTime orderTime) {
        this.orderTime = orderTime;
    }


    public int getOrderId() {
        return orderId;
    }

    public void setTotalPrice(int totalPrice) {
        this.totalPrice = totalPrice;
    }

    public void setFoodName(Map<String, Integer> foodName) {
        this.foodName = foodName;
    }

    public int getTotalPrice() {
        return totalPrice;
    }

    public Map<String, Integer> getFoodName() {
        return foodName;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    @Override
    public String toString() {
        return "Order{" +
                "orderId=" + orderId +
                ", foodName=" + foodName +
                ", totalPrice=" + totalPrice +
                ", orderStatus='" + orderStatus + '\'' +
                ", orderTime=" + orderTime +
                '}';
    }
}
