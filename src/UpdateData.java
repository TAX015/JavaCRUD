import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UpdateData {
    Connection conn = null;

    public UpdateData(String name, String email, String username, String password) {
        try {
            conn = DriverManager.getConnection(Server.dbURL, Server.user, Server.pass);
            String query = "UPDATE users SET password=?, fullname=?, email=? WHERE username=?";

            PreparedStatement statement = conn.prepareStatement(query);
            statement.setString(1, password);
            statement.setString(2, name);
            statement.setString(3, email);
            statement.setString(4, username);

            int rowsUpdated = statement.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("Update Success");
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
