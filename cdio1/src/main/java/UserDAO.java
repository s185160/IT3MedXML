import entitites.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDAO {

    private Connection conn;

    public UserDAO() {
        ConnectionProvider.connectToLocal("130.225.170.222");
    }

    public User authenticateUser(int cpr, String password) {
        User user = new User();
        try {
            String sql = "SELECT * FROM users WHERE cpr=" + cpr + " and password='" + password + "';";
            PreparedStatement ps = conn.
                    prepareStatement(sql);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                user.setCpr(rs.getInt("cpr"));
                user.setName(rs.getString("password"));
                user.setName(rs.getString("name"));
                user.setLastname(rs.getString("lastname"));
                user.setDiverse(rs.getString("diverse"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }


}
