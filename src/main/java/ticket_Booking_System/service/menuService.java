package ticket_Booking_System.service;
import ticket_Booking_System.dao.BookingDao;
import ticket_Booking_System.dao.TicketDao;
import ticket_Booking_System.dao.TrainDao;
import ticket_Booking_System.entities.Ticket;
import ticket_Booking_System.entities.Train;

import java.util.List;
import java.util.Scanner;
public class menuService {
    private List<Train> lastSearchedTrain;

    public void showMenu(Scanner sc, String userId){
    boolean running = true;
    while(running){
        System.out.println("1. Search Train");
        System.out.println("2. Book My Ticket");
        System.out.println("3. Cancel My Ticket");
        System.out.println("4. View My Tickets");
        System.out.println("5. Logout");
        System.out.println("Select Services (enter 1/2/3/4/5) : ");
        int choice2 = sc.nextInt();
        sc.nextLine();
        switch (choice2){
            case 1:
                System.out.println("🔍 Search Train");
                System.out.print("Enter start Journey station :  ");
                String source = sc.nextLine();
                System.out.print("Enter Destination station :  ");
                String destination = sc.nextLine();
                TrainDao trainDao = new TrainDao();
                lastSearchedTrain = trainDao.searchTrain(source, destination);
                if (lastSearchedTrain.isEmpty()) {
                    System.out.println("❌ No trains found");
                } else {
                    for (Train t : lastSearchedTrain) {
                        System.out.println(
                                t.getTrainNo() + " | " +

                                        t.getTrainName() + " | " +
                                        t.getSource() + " → " +
                                        t.getDestination() + " | Seats: " +
                                        t.getAvailableSeats()
                        );
                    }

                }
                break;
            case 2:
                System.out.println("🎟️ Book Ticket");
                if (lastSearchedTrain== null || lastSearchedTrain.isEmpty()){
                    System.out.println("Please search train first 😊");
                    sc.nextLine();
                    break;
                }
                System.out.print("Enter train number : ");
                String trainNo = sc.nextLine();

                Train selectedTrain = null;
                for (Train t : lastSearchedTrain){
                   if (t.getTrainNo().equalsIgnoreCase(trainNo)){
                       selectedTrain = t;
                       break;
                   }
                }
                if (selectedTrain == null){
                    System.out.println("Invalid Train Number 😬");
                    break;
                }
                System.out.print("Enter seats to book : ");
                int seats = sc.nextInt();
                sc.nextLine();

                BookingDao bookingDao = new BookingDao();
                bookingDao.bookTicket( trainNo, userId, seats, selectedTrain.getSource(), selectedTrain.getDestination() );
                break;

            case 3:
                System.out.println("Cancel My Ticket 🎟️");
                System.out.print("Enter Ticket id to cancel : ");
                int ticketId = sc.nextInt();
                sc.nextLine();
                BookingDao bookingDao2 = new BookingDao();
                bookingDao2.cancelTicket(userId, ticketId);
                break;


            case 4:
                System.out.println("📋 View My Ticket");
                TicketDao ticketDao = new TicketDao();
                List<Ticket> tickets = ticketDao.viewMyTicket(userId);
                if (tickets.isEmpty()){
                    System.out.println("No Tickets Found..");
                }
                else{
                    for (Ticket t : tickets){
                        System.out.println("Train : "   + " | "+ t.getTrainNo() +  " Ticket id : " + t.getTicketId() + " | " + t.getSource() + " -> " + t.getDestination() + " | Seats : " + t.getSeatsBooked());
                    }
                }
                break;

            case 5:
                System.out.println("👋 Logged Out");
                running = false;
                break;
            default:
                System.out.println("❌ Invalid choice");
        }
    }
}
}
