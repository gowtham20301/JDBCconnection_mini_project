package Jdbc;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.sql.SQLException;
public class Passenger {
    String name;
    int pbusNo;
    Date date;
    Passenger()
    {
        Scanner sc= new Scanner(System.in);
        System.out.println("Enter your name : ");
        name= sc.next();
        System.out.println("Enter busNo :");
        pbusNo=sc.nextInt();
        System.out.println("Enter Date :");
        String d;
        d= sc.next();
        SimpleDateFormat sim= new SimpleDateFormat("dd-MM-yyyy");
        try {
            date = sim.parse(d);
        }
        catch(ParseException e)
        {
          e.printStackTrace();
        }

    }
    public boolean isAvailable() throws SQLException
    {
            BusDAO busdao= new BusDAO();
            int cap=busdao.getCapacity(pbusNo);
            BookingDAO bookingdao= new BookingDAO();
            int booked=bookingdao.getBookinginfo(pbusNo,date);

        return cap>booked?true:false;
    }

}