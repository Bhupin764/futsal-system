package application.models;
public class Booking {
    private User user;
    private Futsal futsal;
    private String date;
    private String timeSlot;
    public Booking(User user, Futsal futsal, String date, String timeSlot) {
        this.user = user;
        this.futsal = futsal;
        this.date = date;
        this.timeSlot = timeSlot;
    }
    public User getUser() {
        return user;
    }
    public Futsal getFutsal() {
        return futsal;
    }
    public String getDate() {
        return date;
    }
    public String getTimeSlot() {
        return timeSlot;
    }
}
