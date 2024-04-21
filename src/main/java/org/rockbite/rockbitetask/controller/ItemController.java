package org.rockbite.rockbitetask.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.rockbite.rockbitetask.dto.item.AddItemToWarehouseDTO;
import org.rockbite.rockbitetask.dto.item.CreateItemDTO;
import org.rockbite.rockbitetask.dto.item.MoveItemToWarehouseDTO;
import org.rockbite.rockbitetask.model.Item;
import org.rockbite.rockbitetask.service.ItemService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("item")
@RequiredArgsConstructor
public class ItemController {

    private final ItemService itemService;

    /**
     * "/item/" creates new item.
     *
     * @param dto additional information for item
     * @return {@link  ResponseEntity} HttpStatus created if item successfully creates, otherwise throws exception
     * @see <a href="https://docs.oracle.com/en/cloud/paas/content-cloud/rest-api-sites-management/op-requests-id-edit-form-get.html">@RequestBody</a>
     */
    @PostMapping()
    public ResponseEntity<Item> createItem(@Valid @RequestBody CreateItemDTO dto) {
        Item item = itemService.createItem(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(item);
    }

    /**
     * "/item/add" adds item to warehouse.
     *
     * @param dto additional information for item and warehouse
     * @return {@link  ResponseEntity} HttpStatus Ok if item successfully adds, otherwise throws exception
     * @see <a href="https://docs.oracle.com/en/cloud/paas/content-cloud/rest-api-sites-management/op-requests-id-edit-form-get.html">@RequestBody</a>
     */
    @PatchMapping("/add")
    public ResponseEntity<Void> addItemToWarehouse(@Valid @RequestBody AddItemToWarehouseDTO dto) {
        itemService.addItemToWarehouse(dto);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    /**
     * "/item/add" move item from one warehouse to another.
     *
     * @param dto additional information for item and warehouses.
     * @return {@link  ResponseEntity} HttpStatus Ok if item successfully moved, otherwise throws exception
     * @see <a href="https://docs.oracle.com/en/cloud/paas/content-cloud/rest-api-sites-management/op-requests-id-edit-form-get.html">@RequestBody</a>
     */
    @PatchMapping("/move")
    public ResponseEntity<Void> moveItemToWarehouse(@Valid @RequestBody MoveItemToWarehouseDTO dto) {
        itemService.moveItemToWarehouse(dto);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    /**
     * "/item/" removes item from warehouse.
     *
     * @param itemId  item id
     * @param warehouseId  warehouse id
     * @return {@link  ResponseEntity} HttpStatus Ok if item successfully removed, otherwise throws exception
     * @see <a href="https://docs.oracle.com/en/cloud/paas/content-cloud/rest-api-sites-management/op-requests-id-edit-form-get.html">@RequestBody</a>
     */
    @DeleteMapping()
    public ResponseEntity<Void> removeItemFromWarehouse(@RequestParam() long itemId, long warehouseId) {
        itemService.removeItemFromWarehouse(itemId, warehouseId);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    /**
     * "/item/{id}" returns item by id.
     *
     * @param id  item id
     * @return {@link  ResponseEntity} HttpStatus Ok if item successfully removed, otherwise throws not found exception
     * @see <a href="https://docs.oracle.com/en/cloud/paas/content-cloud/rest-api-sites-management/op-requests-id-edit-form-get.html">@RequestBody</a>
     */
    @GetMapping("/{id}")
    public ResponseEntity<Item> getItem(@Valid @PathVariable("id") long id){
        Item item = itemService.getItemById(id);
        return ResponseEntity.status(HttpStatus.OK).body(item);
    }

    /**
     * "/item/" returns all items.
     *
     * @return {@link  ResponseEntity} HttpStatus Ok
     * @see <a href="https://docs.oracle.com/en/cloud/paas/content-cloud/rest-api-sites-management/op-requests-id-edit-form-get.html">@RequestBody</a>
     */
    @GetMapping()
    public ResponseEntity<List<Item>> getAllItems(){
        List<Item> items = itemService.getAllItems();
        return ResponseEntity.status(HttpStatus.OK).body(items);
    }


}
