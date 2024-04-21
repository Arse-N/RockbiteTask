package org.rockbite.rockbitetask.dto.player;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.Getter;
import org.hibernate.validator.constraints.Length;

@Getter
public class CreatePlayerDTO {

    @NotBlank()
    @Length(min = 1, max = 100)
    private String name;

    @NotBlank()
    @Length(min = 1, max = 100)
    private String lastName;

}
