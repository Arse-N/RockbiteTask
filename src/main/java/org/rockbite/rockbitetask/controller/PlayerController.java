package org.rockbite.rockbitetask.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.rockbite.rockbitetask.dto.player.CreatePlayerDTO;
import org.rockbite.rockbitetask.model.Player;
import org.rockbite.rockbitetask.service.PlayerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("player")
@RequiredArgsConstructor
public class PlayerController {

    private final PlayerService playerService;

    /**
     * "/player/" creates new player, and default warehouse for player.
     *
     * @param dto additional information for player
     * @return {@link  ResponseEntity} HttpStatus created if player successfully creates, otherwise throws exception
     * @see <a href="https://docs.oracle.com/en/cloud/paas/content-cloud/rest-api-sites-management/op-requests-id-edit-form-get.html">@RequestBody</a>
     */
    @PostMapping()
    public ResponseEntity<Player> createPlayer(@Valid @RequestBody CreatePlayerDTO dto){
        Player player = playerService.createPlayer(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(player);
    }

    /**
     * "/player/{id}" returns the player by id.
     *
     * @param id player id;
     * @return {@link  ResponseEntity} HttpStatus OK if found warehouse by id, otherwise throws not found.
     * @see <a href="https://docs.oracle.com/en/cloud/paas/content-cloud/rest-api-sites-management/op-requests-id-edit-form-get.html">@RequestBody</a>
     */
    @GetMapping("/{id}")
    public ResponseEntity<Player> getPlayer(@Valid @PathVariable("id") long id){
        Player player = playerService.getPlayerById(id);
        return ResponseEntity.status(HttpStatus.OK).body(player);
    }



}
