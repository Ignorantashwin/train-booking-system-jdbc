
package ticket_Booking_System.entities;

import ticket_Booking_System.dao.TrainDao;

import java.time.LocalDate;

public class Train {
    private String trainName;
    private String trainNo;
    private String source;
    private String destination;
    private LocalDate travelDate;
    private int totalSeats;
    private int availableSeats;
    public Train(String trainName, String trainNo, String source, String destination, LocalDate travelDate, int totalSeats, int availableSeats){
        this.trainName = trainName;
        this.trainNo = trainNo;
        this.source = source;
        this.destination = destination;
        this.travelDate = travelDate;
        this.totalSeats = totalSeats;
        this.availableSeats = availableSeats;
    }
public String getTrainName() { return trainName ; }
    public String getTrainNo() {return trainNo ;}
public String getSource() { return source ; }
    public String getDestination() { return destination;}
    public LocalDate getTravelDate() { return travelDate;}
    public int getAvailableSeats() { return availableSeats;}

}
