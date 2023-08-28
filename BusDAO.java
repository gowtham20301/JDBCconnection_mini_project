package Jdbc;
import java.sql.*;
public class BusDAO {
    public static void getBusInfo() throws SQLException
    {
        String query="select * from bus";
        Connection con = Dbcon.getConnection();
        Statement st= con.createStatement();
         ResultSet rs=st.executeQuery(query);
        while(rs.next())
        {
            System.out.println("id : "+rs.getInt(1));
            if(rs.getInt(2)==0)
            {
                System.out.println("Ac : No");
            }
            else
            {
                System.out.println("Ac : Yes");
            }
            System.out.println("capacity : "+rs.getInt(3));
            System.out.println("----------------------------------------------------");
        }
        con.close();

    }

    public int getCapacity(int id) throws SQLException
    {
        String query="select capacity from bus where bus_id= "+id;
        Connection con=Dbcon.getConnection();
        Statement st = con.createStatement();
        ResultSet rs=st.executeQuery(query);
        rs.next();
        return rs.getInt(1);
    }
}
