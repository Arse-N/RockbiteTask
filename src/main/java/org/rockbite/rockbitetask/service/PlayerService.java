package org.rockbite.rockbitetask.service;

import org.rockbite.rockbitetask.dto.player.CreatePlayerDTO;
import org.rockbite.rockbitetask.dto.warehouse.CreateWarehouseDTO;
import org.rockbite.rockbitetask.model.Player;


public interface PlayerService {

    Player createPlayer(CreatePlayerDTO createPlayerDTO);

    void createWarehouse(CreateWarehouseDTO createWarehouseDTO);

    Player getPlayerById(Long id);

}
