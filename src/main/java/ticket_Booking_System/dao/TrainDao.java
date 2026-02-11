package ticket_Booking_System.dao;

import com.mysql.cj.jdbc.JdbcConnection;
import ticket_Booking_System.db.dbConnection;
import ticket_Booking_System.entities.Train;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TrainDao {
  public List<Train> searchTrain (String source, String destination){
List<Train> trains = new ArrayList<>();
String sTrain = "SELECT * FROM Train WHERE source = ? AND destination = ?";
  try ( Connection connection = dbConnection.getConnection();
    PreparedStatement pre = connection.prepareStatement(sTrain) )
{
   pre.setString(1,source);
   pre.setString(2, destination);
    ResultSet res = pre.executeQuery();
    while(res.next()){
        Train train = new Train(
      res.getString("trainName"),
      res.getString("trainNo"),
      res.getString("source"),
      res.getString("destination"),
      res.getDate("travelDate").toLocalDate(),
      res.getInt("totalSeats"),
      res.getInt("availableSeats") );
        trains.add(train);
    }

} catch(SQLException e) {
      System.out.println(e.getMessage());
  }
  return trains;
  }
}
