package cloud2.demo.HandleDB;


import java.sql.*;

public class HandleDB {

    public void GetFiles() {
        try {

            Connection conn = DriverManager.getConnection(
                    "",
                    "",
                    ""
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
