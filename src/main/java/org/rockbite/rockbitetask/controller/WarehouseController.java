package org.rockbite.rockbitetask.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.rockbite.rockbitetask.dto.warehouse.CreateWarehouseDTO;
import org.rockbite.rockbitetask.service.PlayerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("warehouse")
@RequiredArgsConstructor
public class WarehouseController {
    private final PlayerService playerService;

    /**
     * "/warehouse" creates new warehouse for player.
     *
     * @param dto additional information for warehouse
     * @return {@link  ResponseEntity} HttpStatus created if warehouse successfully creates otherwise throws exception
     * @see <a href="https://docs.oracle.com/en/cloud/paas/content-cloud/rest-api-sites-management/op-requests-id-edit-form-get.html">@RequestBody</a>
     */
    @PostMapping()
    public ResponseEntity<Void> createWarehouse(@Valid @RequestBody CreateWarehouseDTO dto){
        playerService.createWarehouse(dto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

}
