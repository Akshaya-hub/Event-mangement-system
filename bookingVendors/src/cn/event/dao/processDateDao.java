package cn.event.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class processDateDao {
    private Connection connection;

    public processDateDao(Connection connection) {
        this.connection = connection;
    }

    public boolean updateOrderDate(String orderId, String newDate) {
        boolean success = false;
        PreparedStatement statement = null;

        try {
            String sql = "UPDATE cart SET O_date = ? WHERE event_id = ?";
            statement = connection.prepareStatement(sql);
            statement.setString(1, newDate);
            statement.setString(2, orderId);

            int rowsAffected = statement.executeUpdate();
            if (rowsAffected > 0) {
                success = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (statement != null) {
                    statement.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return success;
    }
}

