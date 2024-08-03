package lk.ijse.backend.bo;

import lk.ijse.backend.dto.ItemDto;

import java.sql.SQLException;

public interface ItemBo {
    boolean addItem (ItemDto itemDto) throws Exception;
}
