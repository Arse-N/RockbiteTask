package org.rockbite.rockbitetask.repository;

import org.rockbite.rockbitetask.model.Item;
import org.rockbite.rockbitetask.model.Player;
import org.rockbite.rockbitetask.model.Warehouse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Repository
public interface WarehouseRepository extends JpaRepository<Warehouse, Long> {

    Optional<Warehouse> findById(Long id);

    List<Warehouse> findByPlayer(Player player);
}
