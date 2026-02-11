package ticket_Booking_System;

import ticket_Booking_System.entities.User;
import ticket_Booking_System.service.UserBookingService;
import ticket_Booking_System.service.menuService;

import java.util.Scanner;


public class App {
 public static void main(String[] args) {
     Scanner sc = new Scanner(System.in);
     UserBookingService ubs = new UserBookingService();
     String userId = null;
     boolean wheel = true;
     while(wheel){
         System.out.println("1. Signup ");
         System.out.println("2. Login");
         System.out.print("please enter (1 or 2) : ");
         int choice = sc.nextInt();


     sc.nextLine();
     if (choice == 1) {
         System.out.println("Enter your name : ");
         String name = sc.nextLine();
         System.out.println("Enter your password : ");
         String password = sc.nextLine();
         System.out.println("Enter your userId : ");
         userId = sc.nextLine();
         User user = new User(name, password, userId);
         boolean registered = ubs.signUp(user);

         if (registered) {
             System.out.println("✅ user registered successfully");
         } else {
             System.out.println("❌ user already exists");
         }

     } else {
         boolean loggedIn = false;
         while (!loggedIn) {
             System.out.println("Enter your userId :");
             userId = sc.nextLine();
             System.out.println("Enter password :");
             String password = sc.nextLine();
             loggedIn = ubs.login(userId, password);
             if (!loggedIn) {
                 System.out.println("❌ Invalid credentials, try again\n");
             }
         }
     }
         System.out.println(" Welcome to Train Booking System 🚇");
         menuService menuService = new menuService();
         menuService.showMenu(sc, userId);
   }



   }
     }
