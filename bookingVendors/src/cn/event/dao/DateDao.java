package cn.event.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Date;

public class DateDao {
    private String jdbcURL = "jdbc:mysql://localhost:3306/eventmanagement";
    private String jdbcUsername = "root";
    private String jdbcPassword = "#Akshaya@19";
    private static final String UPDATE_DATE_SQL = "UPDATE cart SET O_date = ? WHERE id = 1";

    public DateDao() {
    }

    protected Connection getConnection() {
        Connection connection = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return connection;
    }

    public void updateDate(Date newDate) {
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(UPDATE_DATE_SQL)) {
            statement.setDate(1, new java.sql.Date(newDate.getTime()));
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

