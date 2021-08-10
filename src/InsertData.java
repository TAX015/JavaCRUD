import java.sql.*;

public class InsertData {
    Connection conn = null;

    public InsertData(String name, String email, String username, String password) {
        try {
            conn = DriverManager.getConnection(Server.dbURL, Server.user, Server.pass);
            String query = "INSERT INTO users (username, password, fullname, email) VALUES (?, ?, ?, ?)";

            PreparedStatement statement = conn.prepareStatement(query);
            statement.setString(1, username);
            statement.setString(2, password);
            statement.setString(3, name);
            statement.setString(4, email);

            int rowsInserted = statement.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("Insert Success");
            } else {
                System.out.println("Insert Failed");
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
