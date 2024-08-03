package lk.ijse.backend.bo;

import lk.ijse.backend.dto.ItemDto;

import java.sql.SQLException;
import java.util.List;

public interface ItemBo {
    boolean addItem (ItemDto itemDto) throws SQLException;
    ItemDto searchItem(int id) throws SQLException;

    List<ItemDto> getAllItems() throws SQLException;
}
