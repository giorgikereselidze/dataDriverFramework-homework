import org.testng.Assert;
import org.testng.annotations.Test;

import java.sql.*;

public class InsertRow {

    public static int insertStudents(int id,String firstName,String lastName,
                                      String phone) {
        // for insert a new candidate
        ResultSet rs = null;
        int studentId = 0;


        String sql = "INSERT INTO students(id,firstName,lastName,phone) "
                + "VALUES(?,?,?,?)";

        try (Connection conn = SqlJdbc.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);)
        {

            conn.setAutoCommit(false);
            // set parameters for statement
            pstmt.setInt(1,id);
            pstmt.setString(2, firstName);
            pstmt.setString(3, lastName);
            pstmt.setString(4, phone);


            int rowAffected = pstmt.executeUpdate();
            if(rowAffected == 1)
            {
                // get student id
                rs = pstmt.getGeneratedKeys();
                if(rs.next())
                    studentId = rs.getInt(1);
            }

//            int actualLastId = studentId;
//            System.out.println(actualLastId);
//            AssertionMode assertionMode = AssertionMode.SOFT;
//            Assert.assertEquals(actualLastId,id);
            conn.commit();


        } catch (SQLException ex) {
            System.out.println(ex.getMessage());

        } finally {
            try {
                if(rs != null)  rs.close();
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }

        return studentId;
    }

    public static void main (String [] args) {
        // insert a new students
        int id  = insertStudents(1004,"john","doe","577777777");
    }

    @Test
    public void validationTest() throws SQLException {
        Connection conn = SqlJdbc.getConnection();
        Statement stmt = conn.createStatement();

//        String sql = "SELECT * FROM students WHERE id=(SELECT max(id) FROM students)";
        ResultSet rs=  stmt.executeQuery("SELECT * FROM students WHERE id=(SELECT max(id) FROM students);");

        while (rs.next()){
            int id = rs.getInt("id");
            String fName = rs.getString("firstName");
            String lName = rs.getString("lastName");
            String phone = rs.getString("phone");

            Assert.assertEquals(id,1004);
            Assert.assertEquals(fName,"john");
            Assert.assertEquals(lName,"doe");
            Assert.assertEquals(phone,"577777777");

        }

    }


    }





