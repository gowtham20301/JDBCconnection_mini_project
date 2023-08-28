package Jdbc;
import java.util.*;
import java.sql.*;
public class BusReservation {
    public static void main(String[] args) throws SQLException
    {
       // BusDAO busdao= new BusDAO();
        BusDAO.getBusInfo();
        Scanner sc= new Scanner(System.in);
       boolean loop= true;
        while(loop) {
            System.out.println("Enter your choice : \n 1: for booking \n 2: for Exit");
            int choice= sc.nextInt();
            switch (choice) {
                case 1: {
                    Passenger p= new Passenger();
                    if(p.isAvailable())
                    {
                        BookingDAO bookingdao= new BookingDAO();
                        bookingdao.addbookings(p);
                        System.out.println("Booking Confirmed");
                    }
                    else
                    {
                        System.out.println("Sorry,Booking is full \n try another bus or date");
                    }
                    break;
                }
                case 2:
                {
                    loop= false;
                    break;
                }
            }
        }
    }

}
