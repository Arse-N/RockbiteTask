package org.rockbite.rockbitetask.service;


import org.rockbite.rockbitetask.dto.item.AddItemToWarehouseDTO;
import org.rockbite.rockbitetask.dto.item.CreateItemDTO;
import org.rockbite.rockbitetask.dto.item.MoveItemToWarehouseDTO;
import org.rockbite.rockbitetask.model.Item;

import java.util.List;


public interface ItemService {

    Item createItem(CreateItemDTO createItemDTO);

    void addItemToWarehouse(AddItemToWarehouseDTO dto);

    void moveItemToWarehouse(MoveItemToWarehouseDTO dto);

    void removeItemFromWarehouse(long itemId, long warehouseId);

    Item getItemById(long itemId);

    List<Item> getAllItems();
}
