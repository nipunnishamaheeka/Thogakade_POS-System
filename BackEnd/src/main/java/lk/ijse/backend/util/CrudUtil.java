package lk.ijse.backend.util;

import lk.ijse.backend.dao.SuperDAO;

import java.sql.SQLException;
import java.util.List;

public interface CrudUtil <T> extends SuperDAO {
    boolean save(T entity) throws SQLException;
    T search(String id) throws SQLException;
    boolean update(T entity) throws SQLException;
    boolean delete(String id) throws SQLException;
    List<T> getAll() throws SQLException;
}
