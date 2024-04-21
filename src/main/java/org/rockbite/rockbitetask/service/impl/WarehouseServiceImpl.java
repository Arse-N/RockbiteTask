package org.rockbite.rockbitetask.service.impl;

import lombok.RequiredArgsConstructor;
import org.rockbite.rockbitetask.exception.custom.NotFoundException;
import org.rockbite.rockbitetask.model.Player;
import org.rockbite.rockbitetask.model.Warehouse;
import org.rockbite.rockbitetask.repository.WarehouseRepository;
import org.rockbite.rockbitetask.service.WarehouseService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class WarehouseServiceImpl implements WarehouseService {

    private final WarehouseRepository warehouseRepository;

    public Warehouse getDefaultWarehouse(Player player) {
        return Warehouse.builder()
                .name("default")
                .description("bla bla bla")
                .player(player)
                .build();
    }

    public Warehouse createWarehouse(String name, Player player){
        Warehouse warehouse = Warehouse.builder()
                .name(name)
                .description("bla bla")
                .player(player)
                .build();
        warehouseRepository.saveAndFlush(warehouse);
        return warehouse;
    }

    public Warehouse getWarehouseById(long id){
        return warehouseRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("warehouse with id " + id + " not found."));
    }

    @Override
    public List<Warehouse> getWarehousesByPlayerId(Player player) {
        return warehouseRepository.findByPlayer(player);
    }

}
