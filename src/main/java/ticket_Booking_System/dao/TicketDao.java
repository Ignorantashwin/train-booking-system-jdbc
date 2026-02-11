package ticket_Booking_System.dao;

import ticket_Booking_System.db.dbConnection;
import ticket_Booking_System.entities.Ticket;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TicketDao {
    public List<Ticket> viewMyTicket(String userId){
        List<Ticket> tickets = new ArrayList<>();
        String getQ = "SELECT * FROM ticket WHERE userId = ?";
        try (
                Connection connection = dbConnection.getConnection();
                PreparedStatement ps1 = connection.prepareStatement(getQ)
                ){
            ps1.setString(1, userId);
            ResultSet rs = ps1.executeQuery();
            while(rs.next()){
                Ticket ticket = new Ticket(
                        rs.getInt("ticketId"),
                        userId,
                        rs.getString("trainNo"),
                        rs.getInt("seatsBooked"),
                        rs.getString("source"),
                       rs.getString("destination"),
                        rs.getTimestamp("bookingDate")
                );
                tickets.add(ticket);
            }
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return tickets;
    }


}
