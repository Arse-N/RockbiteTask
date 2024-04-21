package org.rockbite.rockbitetask.dto.item;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NonNull;
import org.hibernate.validator.constraints.Length;

@Getter
public class CreateItemDTO {

    @NotBlank
    @Length(min = 1, max = 100)
    private String name;

    private String description;

    @NotBlank
    private String icon;

    @NotNull
    @Min(value = 1)
    private Short maxCap;
}
