package lk.ijse.backend.dao;

import lk.ijse.backend.entity.Item;

public interface ItemDao {
    boolean addItem(Item item) throws Exception;
}
