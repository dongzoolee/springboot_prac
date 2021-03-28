package cloud2.demo.HandleDB;

import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.*;

@RestController
public class HandleDB {

    @Value("${spring.datasource.url}")
    private String URL;

    @Value("${spring.datasource.username}")
    private String Username;

    @Value("${spring.datasource.password}")
    private String Password;

    @Autowired
    public void GetFiles() {
        try {
            Connection conn = DriverManager.getConnection(
                    URL,
                    Username,
                    Password
            );

            Statement sql = conn.createStatement();
            ResultSet resultSet = sql.executeQuery(
                    "SELECT * FROM comment;"
            );

            while (resultSet.next()) {
                System.out.println(resultSet.getString(3));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
