package nl.han.jeremydalm.datasource;

import nl.han.jeremydalm.datasource.util.DatabaseProperties;
import nl.han.jeremydalm.service.dto.LoginRequestDTO;

import javax.inject.Inject;
import java.sql.*;

public class LoginDAO {
    private DatabaseProperties properties;

    @Inject
    public void setProperties(DatabaseProperties properties) {
        this.properties = properties;
    }

    public LoginDAO() {
    }

    public LoginRequestDTO findUser() {
        LoginRequestDTO request = new LoginRequestDTO();
        try {
            Connection connection = DriverManager.getConnection(properties.getConnectionString());
            PreparedStatement statement = connection.prepareStatement("SELECT * from users");
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                request.setUser(resultSet.getString("Username"));
                request.setPassword(resultSet.getString("Password"));
            }
            statement.close();
            connection.close();
        } catch (SQLException e) {
            throw new InvalidLoginException();
        }
        return request;
    }

    public void verifyLogin(String user, String password) {
        LoginRequestDTO info = findUser();
        if (!info.getUser().equals(user) || !info.getPassword().equals(password))
            throw new InvalidLoginException();
    }
}
