import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DeleteData {
    Connection conn = null;

    public DeleteData(String user) {
        try {
            conn = DriverManager.getConnection(Server.dbURL, Server.user, Server.pass);
            String query = "DELETE FROM users WHERE username=?";

            PreparedStatement statement = conn.prepareStatement(query);
            statement.setString(1, user);

            int rowDeleted = statement.executeUpdate();
            if (rowDeleted > 0) {
                System.out.println("Delete Success");
            } else {
                System.out.println("Delete Failed");
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
