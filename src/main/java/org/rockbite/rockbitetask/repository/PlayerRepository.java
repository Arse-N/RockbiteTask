package org.rockbite.rockbitetask.repository;

import org.rockbite.rockbitetask.model.Item;
import org.rockbite.rockbitetask.model.Player;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PlayerRepository extends JpaRepository<Player, Long> {

    Optional<Player> findById(long id);

    List<Player> findAll();

//    void update(long id, Player player);

}
