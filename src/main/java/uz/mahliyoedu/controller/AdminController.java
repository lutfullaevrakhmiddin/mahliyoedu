package uz.mahliyoedu.controller;

import uz.mahliyoedu.dto.AdminLoginDto;
import uz.mahliyoedu.service.AdminService;
import uz.mahliyoedu.service.JwtService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Map;

@RestController
@RequestMapping("/api/admin")
@CrossOrigin(origins = "*")
public class AdminController {

    private final AdminService adminService;
    private final JwtService jwtService;

    public AdminController(AdminService adminService, JwtService jwtService) {
        this.adminService = adminService;
        this.jwtService = jwtService;
    }

    // POST /api/admin/login — admin login
    // Muvaffaqiyatli bo'lsa JWT token qaytaradi
    @PostMapping("/login")
    public ResponseEntity<Map<String, Object>> login(@RequestBody AdminLoginDto dto) {
        boolean success = adminService.login(dto);

        if (success) {
            // Token yaratamiz — email ni token ichiga joylashtiramiz
            String token = jwtService.generateToken(dto.getEmail());
            return ResponseEntity.ok(Map.of(
                "success", true,
                "message", "Login muvaffaqiyatli",
                "token", token
            ));
        }

        // Login muvaffaqiyatsiz
        return ResponseEntity.status(401).body(Map.of(
            "success", false,
            "message", "Email yoki parol noto'g'ri"
        ));
    }
}
