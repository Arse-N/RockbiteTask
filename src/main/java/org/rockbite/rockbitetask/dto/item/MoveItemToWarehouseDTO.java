package org.rockbite.rockbitetask.dto.item;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class MoveItemToWarehouseDTO {

    @NotNull
    @Min(value = 1)
    private Long itemId;

    @NotNull
    @Min(value = 1)
    private Long fromWarehouseId;

    @NotNull
    @Min(value = 1)
    private Long toWarehouseId;
}
