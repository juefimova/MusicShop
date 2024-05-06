package DBConnection;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DBHandler {
    public Connection dbConnection;

    public Connection getDbConnection() {
        try {
            Class.forName("org.sqlite.JDBC");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }


        Properties props = new Properties();
        try (InputStream in = Files.newInputStream(Paths.get("src/main/resources/database.properties"))) {
            props.load(in);
            String url = props.getProperty("url");
            dbConnection = DriverManager.getConnection(url);
            System.out.println("Соединение с БД прошло успешно!");
        } catch (IOException | SQLException e) {
            e.printStackTrace();
        }
        return dbConnection;
    }
}
