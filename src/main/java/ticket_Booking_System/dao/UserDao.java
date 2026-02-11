package ticket_Booking_System.dao;
import ticket_Booking_System.entities.User;
import ticket_Booking_System.db.dbConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDao {

    public boolean isExist(String userId) {
        String que = "SELECT 1 from user WHERE userId = ?";
        try (Connection con = dbConnection.getConnection();
             PreparedStatement pre = con.prepareStatement(que)
        ) {
            pre.setString(1, String.valueOf(userId));
            ResultSet res = pre.executeQuery();
            return res.next();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return false;
    }

    public boolean saveUser(User user) {
        String save = "INSERT INTO user (name, password, userId) VALUES (?,?,?) ";
        try (
                Connection cod = dbConnection.getConnection();
                PreparedStatement ps = cod.prepareStatement(save)) {
            ps.setString(1, user.getName());
            ps.setString(2, user.getHashPassword());
            ps.setString(3, user.getUserId());
            ps.executeUpdate();
            return true;

        } catch (SQLException sq) {
            System.out.println(sq.getMessage());
        }
        return false;
    }

    public boolean validateLogin(String userId, String password) throws SQLException {
        String query = "SELECT 1 FROM user WHERE userId = ? and password = ?";
        try (Connection con = dbConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(query)) {
            ps.setString(1, userId);
          ps.setString(2, String.valueOf(password.hashCode()));
            ResultSet rs = ps.executeQuery();
           return rs.next();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return false;
    }



    }