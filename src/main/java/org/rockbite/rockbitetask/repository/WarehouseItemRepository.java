package org.rockbite.rockbitetask.repository;

import org.rockbite.rockbitetask.model.Item;
import org.rockbite.rockbitetask.model.Warehouse;
import org.rockbite.rockbitetask.model.WarehouseItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface WarehouseItemRepository extends JpaRepository<WarehouseItem, Long> {

    Optional<WarehouseItem> findWarehouseItemByItemAndWarehouse(Item item, Warehouse warehouse);

}
