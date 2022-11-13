package com.ap.apmenu.controller;

import com.ap.apmenu.model.FoodModel;

import java.util.HashMap;
import java.util.Locale;
import java.util.Objects;

public class MenuController {

    private static class MenuItem {
        public int amount;
        public double price;
    }

    private final HashMap<String, MenuItem> orderMap;

    public MenuController() {
        orderMap = new HashMap<>();
    }

    public void setFood(FoodModel food, int amount) {
        // if food does not exist in order yet
        if (!orderMap.containsKey(food.getFoodID())) {
            MenuItem item = new MenuItem();
            item.amount = amount;
            item.price = food.getPrice();
            orderMap.put(food.getFoodID(), item);
        } else {
            Objects.requireNonNull(orderMap.get(food.getFoodID())).amount = amount;
        }
    }

    public void addFood(FoodModel food) {
        // if food does not exist in order yet
        if (!orderMap.containsKey(food.getFoodID())) {
            MenuItem item = new MenuItem();
            item.amount = 1;
            item.price = food.getPrice();
            orderMap.put(food.getFoodID(), item);
        } else {
            Objects.requireNonNull(orderMap.get(food.getFoodID())).amount += 1;
        }
    }

    /**
     * Remove food and return if food is deleted.
     * @param food food to be removed.
     * @return boolean indicating food is deleted by the order.
     */
    public boolean removeFood(FoodModel food) {
        if (orderMap.containsKey(food.getFoodID())) {
            MenuItem item = Objects.requireNonNull(orderMap.get(food.getFoodID()));
            item.amount -= 1;
            if (item.amount <= 0) {
                orderMap.remove(food.getFoodID());
                return true;
            }
        }
        return false;
    }

    public String getTotalPrice() {
        double totalPrice = 0.0;

        for (MenuItem item: orderMap.values()) {
            totalPrice += item.price * item.amount;
        }

        return String.format(Locale.ENGLISH, "%.2f", totalPrice);
    }


    public int getAmountOfFood(String foodID) {
        if (orderMap.containsKey(foodID))
            return Objects.requireNonNull(orderMap.get(foodID)).amount;
        else
            return 0;
    }
}
