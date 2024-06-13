import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;
import flights.Flight;
import payment.Payment;

import user.*;



public class AirlineReservationSystem 
{
    public static void main(String[] args) 
    {
        Scanner scanner = new Scanner(System.in);

        // Create flights for different cities
        Flight flight1 = new Flight("F1234", "Delhi", "Mumbai", 100, 2500, "09:00 AM", "2023-11-10");
        Flight flight2 = new Flight("F5678", "Mumbai", "Chennai", 150, 2000, "10:30 AM", "2023-11-15");
        Flight flight3 = new Flight("F9012", "Delhi", "Mumbai", 120, 2750, "06:15 AM", "2023-11-12");
        Flight flight4 = new Flight("F123", "udaipur", "pune", 90, 1500, "09:00 AM", "2023-11-10");
        Flight flight5 = new Flight("F1232", "mysore", "jaipur", 108, 2500, "08:00 AM", "2023-11-08");
        Flight flight6 = new Flight("F1231", "hyderabad", "delhi", 170, 2900, "09:00 AM", "2023-11-11");
        Flight flight7 = new Flight("F1236", "jammu", "Mumbai", 190, 7500, "09:00 AM", "2023-11-09");
        Flight flight8 = new Flight("F1200", "Goa", "Mumbai", 163, 7500, "09:00 AM", "2023-11-09");
        Flight flight9 = new Flight("F1237", "kerela", "Mumbai", 169, 7500, "09:00 AM", "2023-11-09");
        Flight flight10 = new Flight("F1238", "Delhi", "Mumbai", 179, 1500, "11:00 AM", "2023-11-09");
        Flight flight11 = new Flight("F1239", "jammu", "lahore", 112, 7500, "09:00 AM", "2023-11-09");
        Flight flight12 = new Flight("F1230", "Delhi", "Mumbai", 146, 4500, "04:00 PM", "2023-11-03");

        boolean isRunning = true;
        User user = null;

        while (isRunning) 
        {
            System.out.println("***********************************************************************************************************************");
            System.out.println();
            System.out.println();
            System.out.println("*********************************    WELCOME TO THE AIRLINE RESERVATION SYSTUM    **************************************");
            System.out.println();
            System.out.println();
            System.out.println("***********************************************************************************************************************");
            
            System.out.println("");
            System.out.println("1. Login");
            System.out.println("2. Exit");
            System.out.print("Select an option: ");
            int option = scanner.nextInt();

            switch (option) 
            {
                case 1:
                    // Collect user details
                    System.out.print("Enter your name: ");
                    String userName = scanner.next();
                    System.out.print("Enter your email: ");
                    String userEmail = scanner.next();
                    System.out.print("Enter your age: ");
                    int userAge = scanner.nextInt();

                    user = new User(userName, userEmail, userAge);

                    // Ask for departure and destination cities
                    System.out.print("Enter departure city: ");
                    String departureCity = scanner.next();
                    System.out.print("Enter destination city: ");
                    String destinationCity = scanner.next();

                    // Ask for window seat preference
                    System.out.print("Do you prefer window seats? (yes/no): ");
                    String windowPreference = scanner.next();
                    boolean preferWindow = windowPreference.equalsIgnoreCase("yes");
                    System.out.println();
                    System.out.println();

                    // Display available flights
                    System.out.println("Available flights from " + departureCity + " to " + destinationCity + ":");
                    List<Flight> availableFlights = new ArrayList<>();
                    for (Flight flight : new Flight[]{flight1, flight2, flight3,flight4,flight5,flight6,flight7,flight8,flight9,flight10,flight11,flight12})
                    {
                        if (flight.getDepartureCity().equalsIgnoreCase(departureCity) && flight.getDestinationCity().equalsIgnoreCase(destinationCity)) 
                        {
                            availableFlights.add(flight);
                        }
                    }

                    if (availableFlights.isEmpty()) 
                    {
                        System.out.println("No available flights for the specified route.");
                    } else 
                    {
                        for (Flight flight : availableFlights) 
                        {
                                System.out.println("Flight " + flight.getFlightNumber() + " - " + flight.getDepartureCity() + " to " + flight.getDestinationCity() +
                                    " (" + flight.getAvailableSeats() + " seats available) - " + "Departure Time: " + flight.getDepartureTime() +
                                    " - Date: "+ flight.getFlightDate() + " - Price: " + flight.getTicketPrice() + " Rupees");
                        }
        
                                System.out.println();
                                System.out.println();
                                System.out.print("Enter the flight number you want to book : ");
                                String selectedFlightNumber = scanner.next();
                                Flight selectedFlight = null;
                                for (Flight flight : availableFlights)
                                {
                                    if (flight.getFlightNumber().equalsIgnoreCase(selectedFlightNumber))
                                    {
                                        selectedFlight = flight;
                                        break;
                                    }
                                }
        
                                if (selectedFlight != null) {
                                System.out.println("Available seats on Flight " + selectedFlight.getFlightNumber() + ": " + selectedFlight.getAvailableSeats());

                                System.out.println();
                                System.out.print("Enter the number of window seats you want to book: ");
                                int windowSeatsToBook = scanner.nextInt();

                                System.out.println();
                                System.out.print("Enter the number of non-window seats you want to book: ");
                                int nonWindowSeatsToBook = scanner.nextInt();

                                if (windowSeatsToBook < 0 || nonWindowSeatsToBook < 0) 
                                {
                                    System.out.println("Invalid number of seats. Booking failed.");
                                } 
                                
                                else 
                                {
                                    selectedFlight.bookSeats(windowSeatsToBook, nonWindowSeatsToBook, user, preferWindow);
                                    if (selectedFlight.getAvailableSeats() > 0) {
                                        
                                        System.out.println("Payment Process:");
                                        System.out.println("1. UPI Payment");
                                        System.out.println("2. Credit Card Payment");
                                        System.out.println("3. Debit Card Payment");
                                        System.out.print("Select a payment method (1/2/3): ");
                                        int paymentMethod = scanner.nextInt();
                                        if (paymentMethod == 1) {
                                            System.out.print("Enter UPI ID: ");
                                            String upiId = scanner.next();
                                            boolean paymentStatus = Payment.makeUPIPayment(upiId, selectedFlight.getTicketPrice());
                                            if (paymentStatus) 
                                            {
                                                System.out.println("Payment successful!");
                                                System.out.println("Ticket booked successfully.");
                                                isRunning = false;
                                            } 
                                            
                                            else 
                                            {
                                                System.out.println("Payment failed. Ticket booking canceled.");
                                            }
                                        } 
                                        
                                        else if (paymentMethod == 2 || paymentMethod == 3) 
                                        {
                                            System.out.print("Enter card number: ");
                                            String cardNumber = scanner.next();
                                            System.out.print("Enter card expiry date (MM/YY): ");
                                            String expiryDate = scanner.next();
                                            System.out.print("Enter CVV: ");
                                            String cvv = scanner.next();
                                            System.out.print("Enter OTP: ");
                                            String otp = scanner.next();

                                            boolean paymentStatus;
                                            if (paymentMethod == 2) {
                                                
                                                paymentStatus = Payment.makeCreditCardPayment(cardNumber, expiryDate, cvv, selectedFlight.getTicketPrice(), otp);
                                            } else {
                                                paymentStatus = Payment.makeDebitCardPayment(cardNumber, expiryDate, cvv, selectedFlight.getTicketPrice(), otp);
                                            }

                                            if (paymentStatus) 
                                            {
                                                System.out.println("Payment successful!");
                                                System.out.println("Ticket booked successfully.");
                                                System.out.println();
                                                System.out.println();
                                                System.out.println("----------------------------------------------        RESERVED       ---------------------------------------------------");
                
                                                isRunning = false;
                                            } 
                                            else 
                                            {
                                                System.out.println("Payment failed. Ticket booking canceled.");
                                            }
                                        }
                                    }
                                }
                            } else {
                                System.out.println("Invalid flight number. Booking failed.");
                            }
                        }

                        break;
                    case 2:
                        isRunning = false;
                        break;
                    default:
                        System.out.println("Invalid option. Please try again.");
                        break;
                }
                
            }

            // Close the scanner
            scanner.close();
        }
    }