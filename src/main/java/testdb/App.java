package testdb;

import java.sql.*;
import java.util.Calendar;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) throws SQLException, ClassNotFoundException {
        java.sql.Date date = new java.sql.Date(Calendar.getInstance().getTime().getTime());
        insertInvoice(date,"bestellung 4",12, (byte) 1,connectDatabase());
    showInvoices(connectDatabase());
    }
    public static Connection connectDatabase() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection con=DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/jdbctest","root","");
        return con;
    }
    public static void showInvoices(Connection con)
    {
        try{
            Statement stmt=con.createStatement();
            ResultSet rs=stmt.executeQuery("select * from Invoices");
            while(rs.next())
                System.out.println(rs.getInt(1)+"  "+rs.getString(2) + " "+rs.getString(3)+ " "+rs.getInt(4));
            con.close();
        }catch(Exception e){ System.out.println(e);}
    }
    public static void insertInvoice(Date date, String description, double value, Byte paid, Connection con) throws SQLException {
        Statement stmt=con.createStatement();
        String sql = "insert into invoices"
                   + "(date,description,value,paid)"
                    + "values (date,description,value,paid)";
        stmt.executeQuery(sql);
    }
    public static void updateInvoice(int id, Date date, String description, double value, int paid)
    {

    }
    public static void deleteInvoice(int id)
    {

    }
}