package Jdbc;
import java.sql.*;
public class Jdbcpractice {
    public static void main(String[] args) throws SQLException {
        //Jdbcpractice.readData();
        //Jdbcpractice.updateData();
        // Jdbcpractice.insertRecord1();
        //Jdbcpractice.DeleteRecord();
        //Jdbcpractice.update();
        //Jdbcpractice.sp1();
        //Jdbcpractice.sp2();
        // Jdbcpractice.sp3();
        //Jdbcpractice.cm();
        Jdbcpractice.batchProcess();
      /*Types of statement
                1:Normal Statement
                2:PreparedStatement
                3:CallableStatement(stored procedure)*/
    }

    public static void updateData() throws SQLException {
        String url = "jdbc:mysql://localhost:3306/logic";
        String userName = "root";
        String password = "vbnm";
        String query = "insert into employee values(2,'gowtham',2000000)";
        Connection con = DriverManager.getConnection(url, userName, password);
        Statement st = con.createStatement();
        int rows = st.executeUpdate(query);
        System.out.println("no of rows affected :" + rows);
        con.close();

    }

    public static void readData() throws SQLException {
        String url = "jdbc:mysql://localhost:3306/logic";
        String userName = "root";
        String password = "vbnm";
        String query = "Select *from employee";
        Connection con = DriverManager.getConnection(url, userName, password);
        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery(query);// result set has the result it pointing the top we said to him to move to first record

        rs.next(); //which means it moves to the first record of the pointer
        System.out.println("emp_id " + rs.getInt(1));
        System.out.println("ename " + rs.getString(2));
        System.out.println("salary " + rs.getInt(3));
        con.close();

    }

    public static void insertRecord1() throws SQLException {
        String url = "jdbc:mysql://localhost:3306/logic";
        String userName = "root";
        String password = "vbnm";
        Connection con = DriverManager.getConnection(url, userName, password);
        String query = "insert into employee values(?,?,?)";
        int id = 5;
        String name = "sangesh";
        int salary = 800000;
        // Statement st= con.createStatement();
        PreparedStatement pst = con.prepareStatement(query);
        pst.setInt(1, id);
        pst.setString(2, name);
        pst.setInt(3, salary);
        int rs = pst.executeUpdate();
        System.out.println("No of rows affected : " + rs);
    /*    while(rs.next()) {
            System.out.println("id no : " + rs.getInt(1));
            System.out.println("Name : " + rs.getString(2));
            System.out.println("Salary : "+ rs.getInt(3));
    }*/
        con.close();
    }

    public static void DeleteRecord() throws SQLException {
        String url = "jdbc:mysql://localhost:3306/logic";
        String userName = "root";
        String password = "vbnm";
        int id = 2;
        String query = "delete from employee where emp_id=" + id;
        Connection con = DriverManager.getConnection(url, userName, password);
        Statement st = con.createStatement();
        int rows = st.executeUpdate(query);
        System.out.println("Deleted rows affected : " + rows);
        con.close();
    }

    public static void update() throws SQLException {
        String url = "jdbc:mysql://localhost:3306/logic";
        String userName = "root";
        String password = "vbnm";
        String query = "update employee set salary=1500000 where emp_id=1";
        Connection con = DriverManager.getConnection(url, userName, password);
        Statement st = con.createStatement();
        int rows = st.executeUpdate(query);
        System.out.println("No of rows affected in updated : " + rows);
        con.close();
    }

    public static void sp() throws SQLException {
        String url = "jdbc:mysql://localhost:3306/logic";
        String userName = "root";
        String password = "vbnm";
        String query = "{call getEmp}";

        Connection con = DriverManager.getConnection(url, userName, password);
        CallableStatement cst = con.prepareCall(query);
        ResultSet rs = cst.executeQuery();
        while (rs.next()) {
            System.out.println("ID : " + rs.getInt(1));
            System.out.println("Name : " + rs.getString(2));
            System.out.println("Salary : " + rs.getInt(3));
            System.out.println("----------------------------------------");
        }
    }

    public static void sp1() throws SQLException {  // Prepared call is used to
        String url = "jdbc:mysql://localhost:3306/logic";
        String userName = "root";
        String password = "vbnm";
        String query = "{call getemp(?)}";
        Connection con = DriverManager.getConnection(url, userName, password);
        CallableStatement cst = con.prepareCall(query);
        cst.setInt(1, 1);
        ResultSet rs = cst.executeQuery();
        while (rs.next()) {
            System.out.println("Id : " + rs.getInt(1));
            System.out.println("Name  : " + rs.getString(2));
            System.out.println("Salary : " + rs.getInt(3));
        }
    }

    public static void sp3() throws SQLException {
        String url = "jdbc:mysql://localhost:3306/logic";
        String userName = "root";
        String password = "vbnm";
        //String query="{call getIdName(?,?)}";
        int id = 1;
        Connection con = DriverManager.getConnection(url, userName, password);
        CallableStatement cst = con.prepareCall("call getIdName(?,?)");
        cst.setInt(1, id);
        cst.registerOutParameter(2, Types.VARCHAR);
        cst.executeUpdate();
        System.out.println(" Get the String " + cst.getString(2));

    }

    // Commit and Auto commit------------------
    public static void cm() throws SQLException {
        String url = "jdbc:mysql://localhost:3306/logic";
        String userName = "root";
        String password = "vbnm";
        String query = "update employee set salary=10640000 where emp_id=1";
        String query1 = "update employee set salary=2057000 where emp_id=2";
        String query2= "update employee set salary=10640000 where emp_id=3";
        String query3 = "update employee set salary=2057000 where emp_id=4";

        Connection con = DriverManager.getConnection(url, userName, password);// first establish the connection
        con.setAutoCommit(false); //defaultly autocommit is true so we change into false
        Statement st = con.createStatement();
        int rows = st.executeUpdate(query);
        System.out.println("No of rows affected in query " + rows);
        int rows1 = st.executeUpdate(query1);
        System.out.println(rows + rows1);
        if (rows > 0 && rows1 > 0)
        {
            con.commit();
        }

        con.close();
    }
    // batchProcessing
    public static void batchProcess() throws SQLException
    {
        String url = "jdbc:mysql://localhost:3306/logic";
        String userName = "root";
        String password = "vbnm";
        String query = "update employee set salary=106400000 where emp_id=1";
        String query1 = "update employee set salary=2050000 where emp_id=2";
        String query2= "update employee set salary=1064000 where emp_id=3";
        String query3 = "update employee set salary=205000 where emp_id=4";

        Connection con = DriverManager.getConnection(url, userName, password);// first establish the connection
        con.setAutoCommit(false);
        Statement st = con.createStatement();
        st.addBatch(query);
        st.addBatch(query1);
        st.addBatch(query2);
        st.addBatch(query3);

        int[] arr=st.executeBatch();
        for(int i:arr)
        {
            System.out.println("rows affected :" +i);
        }
        for(int i:arr)
        {
            if(i>0)
            {continue;}
            else
            {
                //Roll Back----------------------------------------------------

                con.rollback();

                // which means dont add any bending changes in previous correct
                // change only fully is corrected
                //then don't do any changes if any one is wrong or affected
            }
        }
        con.commit();
        con.close();
    }
}
