package lk.ijse.backend.bo.custom;

import lk.ijse.backend.bo.ItemBo;
import lk.ijse.backend.dao.ItemDao;
import lk.ijse.backend.dao.custom.ItemDaoImpl;
import lk.ijse.backend.dto.CustomerDto;
import lk.ijse.backend.dto.ItemDto;
import lk.ijse.backend.entity.Item;

import java.sql.SQLException;
import java.util.List;

public class ItemBoImpl implements ItemBo {
    ItemDao itemDao = new ItemDaoImpl();
    @Override
    public boolean addItem(ItemDto itemDto) throws SQLException {
        System.out.println("itemDto = " + itemDto);
        return itemDao.addItem(
                new Item(
                        itemDto.getId(),
                        itemDto.getName(),
                        itemDto.getQty(),
                        itemDto.getPrice()
                )
        );
    }

    @Override
    public ItemDto searchItem(String id) throws SQLException {
        ItemDto itemDto = itemDao.searchItem(id);

        if (itemDto != null) {
            return new ItemDto(
                    itemDto.getId(),
                    itemDto.getName(),
                    itemDto.getQty(),
                    itemDto.getPrice()
            );
        }
        return null;
    }

    @Override
    public boolean updateItem(ItemDto itemDto) throws SQLException {
        return itemDao.updateItem(
                new Item(
                        itemDto.getId(),
                        itemDto.getName(),
                        itemDto.getQty(),
                        itemDto.getPrice()
                )
        );
    }

    @Override
    public boolean deleteItem(String id) throws SQLException {
        return itemDao.deleteItem(id);
    }

    @Override
    public List<ItemDto> getAllItems() throws SQLException {
//        List<Item> itemList = itemDao.getAllItems();
//
//        List<ItemDto> itemDtoList = new ArrayList<>();
//
//        for (Item item : itemList) {
//            itemDtoList.add(
//                    new ItemDto(
//                            item.getId(),
//                            item.getName(),
//                            item.getQty(),
//                            item.getPrice()
//                    )
//            );
//        }
//
//        return itemDtoList;
//    }
        List<Item> itemList = itemDao.getAllItems();

        if (itemList != null) {
            return itemList.stream().map(items -> new ItemDto(items.getId(), items.getName(), items.getQty(), items.getPrice())).toList();
        } else {
            return null;
        }

    }
}

