package application.models;
import java.util.HashMap;
import java.util.Map;
public class Futsal {
    private String name;
    private String location;
    private double price;
    private Object owner;
    private Map<String, Boolean> slots = new HashMap<>();
    public Futsal(String name, String location, double price, Object owner) {
        this.name = name;
        this.location = location;
        this.price = price;
        this.owner = owner;
        for(int hour = 9; hour < 23; hour++) {
            String time = String.format("%02d:00", hour);
            slots.put(time, true);
        }
    }
    public String getName() {
        return name;
    }
    public String getLocation() {
        return location;
    }
    public double getPrice() {
        return price;
    }
    public Object getOwner() {
        return owner;
    }
    public boolean getSlot(String time) {
        return slots.getOrDefault(time, false);
    }
    public void bookSlot(String time) {
        slots.put(time, false);
    }
    public String getPriceString() {
        return "Rs." + price + "/hour";
    }
}
