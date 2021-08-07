import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ProcedureClass {
    public static void getLastName(int studentId) {

        String query = "{ call SelectStudentById(?) }";
        ResultSet rs;

        try (Connection conn = SqlJdbc.getConnection();
             CallableStatement stmt = conn.prepareCall(query)) {

            stmt.setInt(1, studentId);

            rs = stmt.executeQuery();
            while (rs.next()) {
                System.out.println(rs.getString("lastName"));
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }


    public static void main(String[] args) {
        getLastName(1001);
    }
}
