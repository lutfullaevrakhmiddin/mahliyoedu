package uz.mahliyoedu.dto;

import lombok.Getter;
import lombok.Setter;

// Admin login qilganda faqat email va parol kerak
@Getter
@Setter
public class AdminLoginDto {

    // Admin emaili
    private String email;

    // Admin paroli
    private String password;
}
