package testdb;

import java.sql.*;
import java.util.Calendar;

public class App 
{
    public static void main( String[] args ) throws SQLException, ClassNotFoundException {
        java.sql.Date date = new java.sql.Date(Calendar.getInstance().getTime().getTime());
        insertInvoice(date,"bestellung 231",324, (byte) 1,connectDatabase());
        updateInvoice(1,date,"bestellung 1 neu",192, (byte) 1,connectDatabase());
        deleteInvoice(9,connectDatabase());
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
    public static void insertInvoice(Date date, String description, int value, Byte paid, Connection con) throws SQLException {

        PreparedStatement insert = con.prepareStatement
                ("insert into invoices  (date, description, value, paid) values(?,?,?,?)");
        insert.setDate(1, date);
        insert.setString(2, description);
        insert.setInt(3, value);
        insert.setByte(4, paid);

        insert.executeUpdate();
    }
    public static void updateInvoice(int id, Date date, String description, int value, Byte paid, Connection con) throws SQLException {
        PreparedStatement update = con.prepareStatement
                ("UPDATE invoices SET date = ?, description = ?, value = ?, paid = ? WHERE id = ?");
        update.setDate(1, date);
        update.setString(2, description);
        update.setInt(3, value);
        update.setByte(4, paid);
        update.setInt(5, id);

        update.executeUpdate();
    }
    public static void deleteInvoice(int id,Connection con) throws SQLException {
        PreparedStatement update = con.prepareStatement
                ("DELETE FROM invoices WHERE ID = ? ");
        update.setInt(1, id);
        update.executeUpdate();
    }
}