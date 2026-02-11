package ticket_Booking_System.entities;

import java.util.List;

public class User {
    private String name;
    private String hashPassword;
    private List<Ticket> ticketsBooked;
    private String userId;

   public User(String name, String password, String userId){
       this.name = name;
       this.hashPassword = String.valueOf(password.hashCode());
       this.userId = userId;

   }
    public String getName() {
        return name;
    }

    public String getHashPassword() {
        return hashPassword;
    }


    public String getUserId() {
        return userId;
    }

}


