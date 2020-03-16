package dao;

import model.User;

import java.sql.*;
import java.util.UUID;

public class UserDao {

    private String jdbcURL;
    private String jdbcUsername;
    private String jdbcPassword;
    private Connection jdbcConnection;

    public UserDao(String jdbcURL, String jdbcUsername, String jdbcPassword) {
        this.jdbcURL = jdbcURL;
        this.jdbcUsername = jdbcUsername;
        this.jdbcPassword = jdbcPassword;
    }

    protected void connect() throws SQLException {
        if (jdbcConnection == null || jdbcConnection.isClosed()) {
            try {
                Class.forName("org.h2.Driver");
            } catch (ClassNotFoundException e) {
                throw new SQLException(e);
            }
            jdbcConnection = DriverManager.getConnection(
                    jdbcURL, jdbcUsername, jdbcPassword);
        }
    }

    protected void disconnect() throws SQLException {
        if (jdbcConnection != null && !jdbcConnection.isClosed()) {
            jdbcConnection.close();
        }
    }

    public boolean login(String name, String password) {
        try {
            connect();
            // 3. Create a statement
            Statement st = jdbcConnection.createStatement();

            String query = "select ID from USER U where U.LOGIN ='" + name + "' and password='" + password + "'";

            // 4. Create a ResultSet
            ResultSet rs = st.executeQuery(query);

            if (rs.next()) {
                // 5. Close all connections
                rs.close();
                st.close();
                disconnect();
                return true;
            }

            // 5. Close all connections
            rs.close();
            st.close();
            disconnect();
            return false;
        } catch (SQLException e) {
            System.out.println(e.toString());
        } catch (Exception e) {
            System.out.println(e.toString());
        }
        return false;
    }

    public User getUser(String username) {
        User user = new User();
        try {
            connect();
            // 3. Create a statement
            Statement st = jdbcConnection.createStatement();

            String query = "select ID, LOGIN, PASSWORD, FIO, ROLE from USER U where U.LOGIN ='" + username + "'";

            // 4. Create a ResultSet
            ResultSet rs = st.executeQuery(query);
            if (rs.next()) {
                user.setId((UUID) rs.getObject("ID"));
                user.setLogin(rs.getString("LOGIN"));
                user.setPassword(rs.getString("PASSWORD"));
                user.setFio(rs.getString("FIO"));
                user.setRole(rs.getString("ROLE"));
            }
        } catch (SQLException e) {
            System.out.println(e.toString());
        } catch (Exception e) {
            System.out.println(e.toString());
        } finally {
            try {
                disconnect();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return user;
    }


}
