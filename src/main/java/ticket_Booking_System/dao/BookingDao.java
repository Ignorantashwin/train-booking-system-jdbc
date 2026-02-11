package ticket_Booking_System.dao;

import ticket_Booking_System.db.dbConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class BookingDao {
   public boolean bookTicket(String trainNo, String userId, int seats, String source, String destination){
       Connection con = null;
       try {
            con = dbConnection.getConnection();
               con.setAutoCommit(false);

           String checkQ = "SELECT availableSeats FROM Train WHERE trainNo = ?";
           PreparedStatement pre = con.prepareStatement(checkQ);
           pre.setString(1, trainNo);
           ResultSet re = pre.executeQuery();

           if (!re.next() ){
               System.out.println("No Train Found 🚇😔");
               con.rollback();
               return false;
           }
           int availableSeats = re.getInt("availableSeats");
           if (availableSeats < seats){
               System.out.println("Not Enough Seats 😔");
               con.rollback();
               return false;
           }

               String updateQ = "UPDATE train SET availableSeats = availableSeats - ? WHERE trainNo = ? AND availableSeats >= ?";
               PreparedStatement up = con.prepareStatement(updateQ);
               up.setInt(1, seats);
               up.setString(2, trainNo);
               up.setInt(3, seats);
               int rowsAffected =  up.executeUpdate();
               if (rowsAffected==0){
                   System.out.println("Not Enough Seats 😬");
                   con.rollback();
                   return false;
               }


               String ticketQ = "INSERT INTO ticket( trainNo, userId, source, destination, seatsBooked ) VALUES (?, ?, ?, ?, ?)";
               PreparedStatement pro = con.prepareStatement(ticketQ);
               pro.setString(1, trainNo);
               pro.setString(2, userId);
               pro.setString(3,  source);
               pro.setString(4, destination);
               pro.setInt(5, seats);
               pro.executeUpdate();

               con.commit();
               System.out.println("Tickets Booked Successfully 🎟😎️");
               return true;

       } catch (Exception e) {
           try {
               if (con != null) con.rollback();
           } catch (SQLException x) {
               System.out.println(x.getMessage());
           }
           System.out.println("Booking Failed, try again 😔");
           System.out.println(e.getMessage());
           return false;
       }

   }
   public boolean cancelTicket(String userId, int ticketId){
       Connection con = null;
       try{
           con = dbConnection.getConnection();
         con.setAutoCommit(false);

           String selectQ = "SELECT trainNo, seatsBooked FROM ticket WHERE ticketId = ? AND userId = ?";
           PreparedStatement pst = con.prepareStatement(selectQ);
           pst.setInt(1,ticketId);
           pst.setString(2,userId);
           ResultSet rs = pst.executeQuery();
           if (!rs.next()){
               System.out.println("Ticket Not Found 😶‍🌫️");
               con.rollback();
               return false;
           }
           String trainNo = rs.getString("trainNo");
           int seatsBooked = rs.getInt("seatsBooked");

           String deleteQ = "UPDATE ticket SET status = 'CANCELLED' WHERE ticketId = ?";
           PreparedStatement deletePre = con.prepareStatement(deleteQ);
           deletePre.setInt(1, ticketId);
           deletePre.executeUpdate();

           String updateQ = "UPDATE train SET availableSeats = availableSeats + ? WHERE trainNo = ?";
           PreparedStatement updatePre = con.prepareStatement(updateQ);
           updatePre.setInt(1, seatsBooked);
           updatePre.setString(2, trainNo);
           updatePre.executeUpdate();

           con.commit();
           System.out.println("Ticket Cancelled Successfully 😊");
           return true;

       }catch(Exception e){
         try{
             if (con != null) con.rollback();
         }catch (Exception c){
             System.out.println(c.getMessage());
         }
           System.out.println("Cancellation Failed 🫥" + e.getMessage());
         return false;
       } finally {
           try{
               if (con!= null) con.setAutoCommit(true);
           } catch(SQLException m){
               System.out.println(m.getMessage());
           }
       }
   }
}
