package Jdbc;
import java.sql.SQLException;
import java.util.Date;
import java.sql.*;
public class BookingDAO {
    public int getBookinginfo(int busno,Date date) throws SQLException
    {
        String query= "select count(passenger_name) from booking " +
                "where bus_no=? " +
                "and travel_date=?";
        Connection con= Dbcon.getConnection();
        PreparedStatement pst= con.prepareStatement(query);
        pst.setInt(1,busno);
        java.sql.Date sqldate= new java.sql.Date(date.getTime());
        pst.setDate(2,sqldate);
        ResultSet rs=pst.executeQuery();
        rs.next();
        int bookingcount = rs.getInt(1);
        return bookingcount;
    }
    public void addbookings(Passenger p) throws SQLException
    {
        String query = "insert into booking values(?,?,?)";
        Connection con= Dbcon.getConnection();
        java.sql.Date sqldate= new java.sql.Date(p.date.getTime());
        PreparedStatement pst= con.prepareStatement(query);
        pst.setInt(1,p.pbusNo);
        pst.setString(2,p.name);
        pst.setDate(3,sqldate);
        pst.executeUpdate();
    }
}