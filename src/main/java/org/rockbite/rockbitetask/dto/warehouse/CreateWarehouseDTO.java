package org.rockbite.rockbitetask.dto.warehouse;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import org.hibernate.validator.constraints.Length;

@Getter
public class CreateWarehouseDTO {

    @NotBlank()
    @Length(min = 1, max = 100)
    private String name;

    @NotNull
    @Min(value = 1)
    private Long playerId;

}
