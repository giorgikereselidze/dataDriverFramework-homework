import org.testng.Assert;
import org.testng.annotations.Test;

import java.sql.*;

public class updateAndValidateFName {
    @Test
    public void updateFName() throws SQLException {

        Connection conn = SqlJdbc.getConnection();
        String sqlUpdate = "UPDATE students "
                + "SET firstName = ? "
                + "WHERE id = ?";

        PreparedStatement pstmt = conn.prepareStatement(sqlUpdate);
        String firstName = "Billy";
        int id = 1004;

        pstmt.setString(1, firstName);
        pstmt.setInt(2, id);

        int rowAffected = pstmt.executeUpdate();

        pstmt.close();

        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * FROM students WHERE id=(SELECT max(id) FROM students);");
        while (rs.next()) {
            String fName = rs.getString("firstName");

            Assert.assertEquals(fName, firstName);

        }
    }
}
