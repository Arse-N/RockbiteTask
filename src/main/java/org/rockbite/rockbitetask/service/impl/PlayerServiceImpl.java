package org.rockbite.rockbitetask.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.rockbite.rockbitetask.dto.player.CreatePlayerDTO;
import org.rockbite.rockbitetask.dto.warehouse.CreateWarehouseDTO;
import org.rockbite.rockbitetask.exception.custom.NotFoundException;
import org.rockbite.rockbitetask.model.Player;
import org.rockbite.rockbitetask.model.Warehouse;
import org.rockbite.rockbitetask.repository.PlayerRepository;
import org.rockbite.rockbitetask.service.PlayerService;
import org.rockbite.rockbitetask.service.WarehouseService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
@Slf4j
@RequiredArgsConstructor
public class PlayerServiceImpl implements PlayerService {

    private final PlayerRepository playerRepository;

    private final WarehouseService warehouseService;

    public Player createPlayer(CreatePlayerDTO createPlayerDTO) {
        Player player = mapTo(createPlayerDTO);
        playerRepository.save(player);
        Warehouse warehouse = warehouseService.getDefaultWarehouse(player);
        player.getWarehouses().add(warehouse);
        playerRepository.save(player);
        log.info("player -->" + player.toString());
        log.info("warhouse -->" + warehouse.toString());
        return getPlayerById(player.getId());
    }

    public void createWarehouse(CreateWarehouseDTO createWarehouseDTO) {
        Player player = getPlayerById(createWarehouseDTO.getPlayerId());
        Warehouse newWarehouse = warehouseService.createWarehouse(createWarehouseDTO.getName(), player);
        player.getWarehouses().add(newWarehouse);
        log.info("new warehouse -->" + newWarehouse.toString());
        playerRepository.save(player);
    }

    private Player mapTo(CreatePlayerDTO createPlayerDTO) {
        return Player.builder()
                .name(createPlayerDTO.getName())
                .lastName(createPlayerDTO.getLastName())
                .warehouses(new ArrayList<>())
                .build();
    }

    public Player getPlayerById(Long id) {
        Player player = playerRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Player with id " + id + " not found."));
        player.setWarehouses(warehouseService.getWarehousesByPlayerId(player));
        return player;
    }

}
