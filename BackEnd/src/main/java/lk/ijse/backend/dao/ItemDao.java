package lk.ijse.backend.dao;

import lk.ijse.backend.dto.ItemDto;
import lk.ijse.backend.entity.Item;

import java.sql.SQLException;
import java.util.List;

public interface ItemDao {
    boolean addItem(Item item) throws SQLException;
    ItemDto searchItem(int id) throws SQLException;
    List<Item> getAllItems() throws SQLException;
}
