package uz.mahliyoedu.service;

import uz.mahliyoedu.dto.AdminLoginDto;
import uz.mahliyoedu.entity.Admin;
import uz.mahliyoedu.repository.AdminRepository;
import org.springframework.stereotype.Service;

@Service
public class AdminService {

    private final AdminRepository adminRepository;

    public AdminService(AdminRepository adminRepository) {
        this.adminRepository = adminRepository;
    }

    public boolean login(AdminLoginDto dto) {
        Admin admin = adminRepository.findByEmail(dto.getEmail()).orElse(null);
        if (admin == null) return false;
        return admin.getPassword().equals(dto.getPassword());
    }

    public void createAdminIfNotExists(String email, String password) {
        if (adminRepository.count() == 0) {
            Admin admin = new Admin();
            admin.setEmail(email);
            admin.setPassword(password);
            adminRepository.save(admin);
        }
    }
}
