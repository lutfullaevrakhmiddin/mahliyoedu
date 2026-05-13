package uz.mahliyoedu;

import uz.mahliyoedu.service.AdminService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class MahliyoeduApplication {

    public static void main(String[] args) {
        SpringApplication.run(MahliyoeduApplication.class, args);
    }

    // Dastur ishga tushganda birinchi admin avtomatik yaratiladi
    // Agar admin allaqachon mavjud bo'lsa — hech narsa qilmaydi
    @Bean
    public CommandLineRunner init(AdminService adminService) {
        return args -> {
            adminService.createAdminIfNotExists(
                "admin@mahliyoedu.uz",  // Admin email
                "mahliyo2024"           // Admin parol — keyinchalik o'zgartiring
            );
        };
    }
}
