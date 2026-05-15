package uz.mahliyoedu.dto;

import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ApplicationRequestDto {

    @NotBlank
    @Size(min = 2, max = 50)
    private String name;

    @NotBlank
    @Pattern(regexp = "^\\+998[0-9]{9}$")
    private String phone;

    // Ixtiyoriy — qaysi kurs uchun
    @Size(max = 100)
    private String course;
}
