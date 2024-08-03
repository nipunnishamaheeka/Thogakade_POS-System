package lk.ijse.backend.db;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class DbConnection {
    private static DbConnection instance;
    private DataSource dataSource;
    private Connection connection;

    private DbConnection() {
        try {
            var initialContext = new InitialContext();
            dataSource = (DataSource) initialContext.lookup("java:comp/env/jdbc/thogakadepos");
            this.connection = dataSource.getConnection(); // Optionally, you might want to handle connection pooling here
        } catch (SQLException | NamingException e) {
            e.printStackTrace();
        }
    }

    public static DbConnection getInstance() {
        if (instance == null) {
            instance = new DbConnection();
        }
        return instance;
    }

    public Connection getConnection() {
        return connection;
    }
}
