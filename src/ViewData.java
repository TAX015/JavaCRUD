import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.sql.*;

public class ViewData {
    public DefaultTableModel tableModel;

    public ViewData(JTable table) {
        Connection conn = null;

        try {
            conn = DriverManager.getConnection(Server.dbURL, Server.user, Server.pass);
            if (conn != null) {
                System.out.println("Connection Success");
            } else {
                System.out.println("Connection Failed");
            }

            Statement statement = (Statement) conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);
            String query = "SELECT * FROM users";
            ResultSet resultSet = statement.executeQuery(query);
            resultSet.beforeFirst();

            String[] columnName = {"ID", "Username", "Password", "Name", "Email"};
            tableModel = new DefaultTableModel(columnName, 0);

            while (resultSet.next()) {
                String id = resultSet.getString("user_id");
                String username = resultSet.getString("username");
                String password = resultSet.getString("password");
                String name = resultSet.getString("fullname");
                String email = resultSet.getString("email");

                String[] data = {id, username, password, name, email};
                tableModel.addRow(data);
            }

            table.setModel(tableModel);
        } catch (SQLException e) {
            System.out.println("An error occurred. Maybe user/password is invalid");
            e.printStackTrace();
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                    System.out.println("Connection Terminated");
                } catch (Exception e) {

                }
            }
        }
    }
}
