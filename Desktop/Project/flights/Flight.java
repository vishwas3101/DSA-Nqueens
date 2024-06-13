package flights;
import java.util.ArrayList;
import java.util.List;
import seat.*;
import user.*;


public class Flight 
{
    private String flightNumber;
    private String departureCity;
    private String destinationCity;
    private int availableSeats;
    private List<Seat> seats;
    private double ticketPrice;
    private String departureTime;
    private String flightDate;

    public Flight(String flightNumber, String departureCity, String destinationCity, int totalSeats, double ticketPrice, String departureTime, String flightDate)
    {
        this.flightNumber = flightNumber;
        this.departureCity = departureCity;
        this.destinationCity = destinationCity;
      
        this.availableSeats = totalSeats;
        this.seats = new ArrayList<>();

        for (int i = 1; i <= totalSeats; i++) 
        {
            seats.add(new Seat("A" + i));
        }

        this.ticketPrice = ticketPrice;
        this.departureTime = departureTime;
        this.flightDate = flightDate;
    }

    public String getFlightNumber() 
    {
        return flightNumber;
    }

    public String getDepartureCity() 
    {
        return departureCity;
    }

    public String getDestinationCity() 
    {
        return destinationCity;
    }

    public int getAvailableSeats() 
    {
        return availableSeats;
    }

    public double getTicketPrice() 
    {
        return ticketPrice;
    }

    public String getDepartureTime() 
    {
        return departureTime;
    }

    public String getFlightDate() 
    {
        return flightDate;
    }

    public boolean bookSeats(int windowSeatsToBook, int nonWindowSeatsToBook, User user, boolean preferWindow) {
        if (windowSeatsToBook + nonWindowSeatsToBook <= availableSeats)
        {
            List<Seat> bookedSeats = new ArrayList<>();
            double totalTicketPrice = 0.0;

            for (int i = 0; i < windowSeatsToBook; i++)
            {
                Seat bookedSeat = seats.get(i);
                bookedSeats.add(bookedSeat);
                totalTicketPrice += ticketPrice;
            }

            for (int i = 0; i < nonWindowSeatsToBook; i++)
            {
                Seat bookedSeat = seats.get(windowSeatsToBook + i);
                bookedSeats.add(bookedSeat);
                totalTicketPrice += ticketPrice;
            }

            if (preferWindow)
            {
                totalTicketPrice += windowSeatsToBook * getWindowSeatExtraCharge();
            }

            availableSeats -= (windowSeatsToBook + nonWindowSeatsToBook);

            System.out.println(windowSeatsToBook + " window seat(s) and " + nonWindowSeatsToBook + " non-window seat(s) booked successfully for Flight " + flightNumber);
            System.out.println();
            System.out.println();
            System.out.println();
            System.out.println("**********************************************    Ticket Details    ***************************************************");
            System.out.println();
            System.out.println();
            System.out.println("PASSENGER NAME : " + user.getName());
            System.out.println("PASSENGER AGE : " + user.getAge());
            System.out.println("FLIGHT NUMBER : " + getFlightNumber());
            System.out.println("DESTINATION CITY : " + getDestinationCity());
            System.out.println("DEPARTURE CITY : " + getDepartureTime());
            System.out.println("DEPARTURE DATE : " + getFlightDate());
            System.out.println("DEPARTURE TIME : " + getDepartureTime());
            System.out.println("TOTAL TICKET PRICE : " + totalTicketPrice + " RUPEES");
            System.out.println();
            System.out.println();
            System.out.println();
            System.out.println();
            System.out.println();
            System.out.println("TRANSACTION(s) ID FOR THE BOOKED SEATS :");
            
            for (Seat bookedSeat : bookedSeats) 
            {
                System.out.println("Seat " + bookedSeat.getSeatNumber() + ": " + bookedSeat.getPNR());
            }
            System.out.println();
            System.out.println();
            System.out.println();
            return true;
        }


        else 
        {
            System.out.println("Sorry, there are not enough available seats on Flight " + flightNumber);
            return false;
        }


    }

    public double getWindowSeatExtraCharge()
    {

        return 100.0;
    }
}
