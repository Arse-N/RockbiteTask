package org.rockbite.rockbitetask.service.impl;


import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.rockbite.rockbitetask.dto.item.AddItemToWarehouseDTO;
import org.rockbite.rockbitetask.dto.item.CreateItemDTO;
import org.rockbite.rockbitetask.dto.item.MoveItemToWarehouseDTO;
import org.rockbite.rockbitetask.exception.custom.ConflictException;
import org.rockbite.rockbitetask.exception.custom.NotFoundException;
import org.rockbite.rockbitetask.model.Item;
import org.rockbite.rockbitetask.model.Warehouse;
import org.rockbite.rockbitetask.model.WarehouseItem;
import org.rockbite.rockbitetask.repository.ItemRepository;
import org.rockbite.rockbitetask.repository.WarehouseItemRepository;
import org.rockbite.rockbitetask.service.ItemService;
import org.rockbite.rockbitetask.service.WarehouseService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class ItemServiceImpl implements ItemService {

    private final ItemRepository itemRepository;
    private final WarehouseItemRepository warehouseItemRepository;
    private final WarehouseService warehouseService;

    public Item createItem(CreateItemDTO createItemDTO) {
        Item item = mapTo(createItemDTO);
        item = itemRepository.saveAndFlush(item);
        log.info("item -->" + item.toString());
        return item;
    }

    private Item mapTo(CreateItemDTO createItemDTO) {
        return Item.builder()
                .name(createItemDTO.getName())
                .description(createItemDTO.getDescription())
                .icon(createItemDTO.getIcon())
                .maxCap((short) createItemDTO.getMaxCap())
                .build();
    }

    public void addItemToWarehouse(AddItemToWarehouseDTO dto) {
        Item item = getItemById(dto.getItemId());
        Warehouse warehouse = warehouseService.getWarehouseById(dto.getWarehouseId());
        WarehouseItem warehouseItem = getWarehouseItem(item, warehouse);
        if (warehouseItem == null) {
            addItem(item, warehouse);
        } else {
            addItemCount(warehouseItem, item.getMaxCap());
        }
    }

    @Transactional
    public void moveItemToWarehouse(MoveItemToWarehouseDTO dto) {

        if (dto.getToWarehouseId() != dto.getFromWarehouseId()) {
            Item item = getItemById(dto.getItemId());
            Warehouse from = warehouseService.getWarehouseById(dto.getFromWarehouseId());
            WarehouseItem warehouseItemFrom = getWarehouseItem(item, from);
            if (warehouseItemFrom != null) {
                Warehouse to = warehouseService.getWarehouseById(dto.getToWarehouseId());
                WarehouseItem warehouseItemTo = getWarehouseItem(item, to);
                if (warehouseItemTo == null) {
                    addItem(item, to);
                } else {
                    addItemCount(warehouseItemTo, item.getMaxCap());
                }
                removeItemCount(warehouseItemFrom);
            } else {
                throw new NotFoundException("Item with id " + dto.getItemId() + " not found in this warehouse.");
            }
        }
    }

    public void removeItemFromWarehouse(long itemId, long warehouseId) {
        Item item = getItemById(itemId);
        Warehouse warehouse = warehouseService.getWarehouseById(warehouseId);
        WarehouseItem warehouseItem = getWarehouseItem(item, warehouse);
        if (warehouseItem != null) {
            removeItemCount(warehouseItem);
        } else {
            throw new NotFoundException("Item with id " + itemId + " not found in this warehouse.");
        }
    }

    private void addItem(Item item, Warehouse warehouse) {
        WarehouseItem warehouseItem = WarehouseItem.builder()
                .item(item)
                .warehouse(warehouse)
                .count((short) 1)
                .build();
        warehouseItemRepository.save(warehouseItem);
    }

    private void addItemCount(WarehouseItem warehouseItem, short macCap) {
        if (warehouseItem.getCount() < macCap) {
            warehouseItem.setCount((short) (warehouseItem.getCount() + 1));
            warehouseItemRepository.save(warehouseItem);
        } else {
            throw new ConflictException("Oops, Item already reached max capacity in this warehouse!");
        }
    }

    private void removeItemCount(WarehouseItem warehouseItem) {
        if (warehouseItem.getCount() > 1) {
            warehouseItem.setCount((short) (warehouseItem.getCount() - 1));
            warehouseItemRepository.save(warehouseItem);
        } else {
            warehouseItemRepository.delete(warehouseItem);
        }
    }

    public Item getItemById(long itemId) {
        return itemRepository.findById(itemId)
                .orElseThrow(() -> new NotFoundException("Item with id " + itemId + " not found."));
    }

    @Override
    public List<Item> getAllItems() {
        return itemRepository.findAll();
    }

    private WarehouseItem getWarehouseItem(Item item, Warehouse warehouse) {
        return warehouseItemRepository.findWarehouseItemByItemAndWarehouse(item, warehouse).orElse(null);
    }
}
