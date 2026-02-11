package ticket_Booking_System.service;
import ticket_Booking_System.dao.UserDao;
import ticket_Booking_System.entities.User;

import java.sql.SQLException;

public class UserBookingService {
private UserDao userDao = new UserDao();

  public boolean signUp(User user) {
   if (userDao.isExist(user.getUserId())){
       System.out.println("userId already exist !");
       return false;
   }
   return userDao.saveUser(user);
  }
  public boolean login(String userId, String password){
      try {
          if (userDao.validateLogin(userId, password)){
            System.out.println("Login Successful");
            return true;
          } else {
            System.out.println("Invalid credentials");
          }
      } catch (SQLException e) {
          throw new RuntimeException(e);
      }
    return false;
  }

}
