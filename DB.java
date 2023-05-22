package JavaProject;

import java.sql.Connection;
import java.sql.DriverManager;


public class DB {
    Connection con=null;
    java.sql.PreparedStatement pst;
    public static Connection dbconnect()
    {
        try{
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn=DriverManager.getConnection("jdbc:mysql://172.22.37.41/applicationform","Project","Pass@123");
            return conn;

        }
        catch (Exception e2)
        {
            System.out.println(e2);
            return null;
        }
    }

}