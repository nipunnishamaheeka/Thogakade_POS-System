package lk.ijse.backend.util;

import lk.ijse.backend.db.DbConnection;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class SQLUtil {
    public static <T> T execute(String sql, Object... params) throws SQLException {
        PreparedStatement statement = DbConnection.getInstance().getConnection().prepareStatement(sql);

        for (int i = 0; i < params.length; i++) {
            statement.setObject((i + 1), params[i]);
        }
        if (sql.startsWith("SELECT") || sql.startsWith("select")) {
            return (T) statement.executeQuery();
        } else {
            return (T) (Boolean) (statement.executeUpdate() > 0);
        }
    }
}
