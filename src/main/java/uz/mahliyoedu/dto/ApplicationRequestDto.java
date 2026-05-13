package uz.mahliyoedu.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ApplicationRequestDto {

    // Bo'sh bo'lmasligi va 2-50 belgi orasida bo'lishi kerak
    @NotBlank(message = "Ism bo'sh bo'lmasligi kerak")
    @Size(min = 2, max = 50, message = "Ism 2-50 belgi orasida bo'lishi kerak")
    private String name;

    // Bo'sh bo'lmasligi va telefon formatiga mos bo'lishi kerak
    @NotBlank(message = "Telefon raqam bo'sh bo'lmasligi kerak")
    @Pattern(regexp = "^\\+998[0-9]{9}$", message = "Telefon raqam +998XXXXXXXXX formatida bo'lishi kerak")
    private String phone;
}
