package lk.ijse.backend.bo.custom;

import lk.ijse.backend.bo.ItemBo;
import lk.ijse.backend.dao.ItemDao;
import lk.ijse.backend.dao.custom.ItemDaoImpl;
import lk.ijse.backend.dto.ItemDto;
import lk.ijse.backend.entity.Item;

import java.sql.SQLException;

public class ItemBoImpl implements ItemBo {
    ItemDao itemDao = new ItemDaoImpl();
    @Override
    public boolean addItem(ItemDto itemDto) throws Exception {
        return itemDao.addItem(
                new Item(
                        itemDto.getId(),
                        itemDto.getName(),
                        itemDto.getQty(),
                        itemDto.getPrice()
                )
        );
    }
}
