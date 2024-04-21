package org.rockbite.rockbitetask.dto.item;

import jakarta.validation.constraints.*;
import lombok.Builder;
import lombok.Getter;

@Getter
public class AddItemToWarehouseDTO {

    @NotNull
    @Min(value = 1)
    private Long itemId;

    @NotNull
    @Min(value = 1)
    private Long warehouseId;
}
