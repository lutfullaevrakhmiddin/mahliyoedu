package uz.mahliyoedu.service;

import uz.mahliyoedu.dto.AdminLoginDto;
import uz.mahliyoedu.entity.Admin;
import uz.mahliyoedu.repository.AdminRepository;
import org.springframework.stereotype.Service;

@Service
public class AdminService {

    private final AdminRepository adminRepository;

    // Constructor injection
    public AdminService(AdminRepository adminRepository) {
        this.adminRepository = adminRepository;
    }

    // Login tekshirish — email va parol to'g'rimi
    public boolean login(AdminLoginDto dto) {
        Admin admin = adminRepository.findByEmail(dto.getEmail())
                .orElse(null);

        // Admin topilmasa yoki parol noto'g'ri bo'lsa false qaytaradi
        if (admin == null) {
            return false;
        }

        // Parolni tekshirish — hozircha oddiy taqqoslash
        // Keyinchalik BCrypt bilan shifrlash qo'shamiz
        return admin.getPassword().equals(dto.getPassword());
    }

    // Birinchi admin yaratish — faqat baza bo'sh bo'lganda
    public void createAdminIfNotExists(String email, String password) {
        if (adminRepository.count() == 0) {
            Admin admin = new Admin();
            admin.setEmail(email);
            admin.setPassword(password);
            adminRepository.save(admin);
        }
    }
}
