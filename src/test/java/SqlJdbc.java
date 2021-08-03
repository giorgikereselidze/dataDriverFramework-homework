import org.testng.annotations.Test;


import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;


public class SqlJdbc {

    public static Connection getConnection() throws SQLException {
        Connection conn = null;

        try (FileInputStream f = new FileInputStream("src/main/resources/db.properties")) {

            // load the properties file
            Properties pros = new Properties();
            pros.load(f);

            // assign db parameters
            String url = pros.getProperty("url");
            String user = pros.getProperty("user");
            String password = pros.getProperty("password");

            // create a connection to the database
            conn = DriverManager.getConnection(url, user, password);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return conn;
    }

    @Test

    public void db() throws SQLException {

        Connection conn = SqlJdbc.getConnection();
        Statement stmt = conn.createStatement();

        ResultSet rs=  stmt.executeQuery("select * from students");

        while (rs.next()){
            String fName = rs.getString("firstName");
            String lName = rs.getString("lastName");
            String phone = rs.getString("phone");

            open("https://demoqa.com/automation-practice-form");
            $("#firstName").setValue(fName);
            $("#lastName").setValue(lName);
            $("#userNumber").setValue(phone);
        }




    }
}

