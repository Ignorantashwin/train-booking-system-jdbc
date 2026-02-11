package ticket_Booking_System.entities;


import java.sql.Timestamp;


public class Ticket {
    private int ticketId;
private String userId;
private String trainNo;
private int seatsBooked;
private String source;
private String destination;
private Timestamp bookingDate;

public Ticket(int ticketId, String userId, String trainNo, int seatsBooked, String source, String destination, Timestamp bookingDate){
this.ticketId = ticketId;
this.userId = userId;
this.trainNo= trainNo;
this.seatsBooked= seatsBooked;
this.source = source;
this.destination = destination;
this.bookingDate = bookingDate;
}
  public int getTicketId(){ return  ticketId;}
public String getUserId() { return userId;}
    public String getTrainNo() { return  trainNo;}
    public int getSeatsBooked() { return seatsBooked;}
    public String getSource(){ return source;}
    public String getDestination(){ return destination;}
}
