package org.rockbite.rockbitetask.service;

import org.rockbite.rockbitetask.model.Player;
import org.rockbite.rockbitetask.model.Warehouse;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;


public interface WarehouseService {

    Warehouse getDefaultWarehouse(Player player);

    Warehouse createWarehouse(String name, Player player);

    Warehouse getWarehouseById(long id);

    List<Warehouse> getWarehousesByPlayerId(Player player);

}
