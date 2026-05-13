package uz.mahliyoedu.controller;

import uz.mahliyoedu.entity.Teacher;
import uz.mahliyoedu.service.TeacherService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
public class TeacherController {

    private final TeacherService teacherService;

    public TeacherController(TeacherService teacherService) {
        this.teacherService = teacherService;
    }

    // GET /api/teachers — saytda ko'rsatish uchun aktiv ustozlar (ochiq)
    @GetMapping("/teachers")
    public ResponseEntity<List<Teacher>> getAllActive() {
        return ResponseEntity.ok(teacherService.getAllActive());
    }

    // GET /api/admin/teachers — barcha ustozlar (admin uchun)
    @GetMapping("/admin/teachers")
    public ResponseEntity<List<Teacher>> getAll() {
        return ResponseEntity.ok(teacherService.getAll());
    }

    // POST /api/admin/teachers — yangi ustoz qo'shish
    // multipart/form-data — rasm va ma'lumotlar birgalikda
    @PostMapping("/admin/teachers")
    public ResponseEntity<Teacher> create(
            @RequestParam("name") String name,
            @RequestParam("subject") String subject,
            @RequestParam(value = "description", required = false) String description,
            @RequestParam(value = "photo", required = false) MultipartFile photo) {
        return ResponseEntity.status(201)
            .body(teacherService.create(name, subject, description, photo));
    }

    // PUT /api/admin/teachers/{id} — ustozni yangilash
    @PutMapping("/admin/teachers/{id}")
    public ResponseEntity<Teacher> update(
            @PathVariable Long id,
            @RequestParam("name") String name,
            @RequestParam("subject") String subject,
            @RequestParam(value = "description", required = false) String description,
            @RequestParam(value = "photo", required = false) MultipartFile photo) {
        return ResponseEntity.ok(
            teacherService.update(id, name, subject, description, photo));
    }

    // DELETE /api/admin/teachers/{id} — o'chirish
    @DeleteMapping("/admin/teachers/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        teacherService.delete(id);
        return ResponseEntity.noContent().build();
    }

    // PUT /api/admin/teachers/{id}/toggle — aktiv/passiv
    @PutMapping("/admin/teachers/{id}/toggle")
    public ResponseEntity<Teacher> toggleActive(@PathVariable Long id) {
        return ResponseEntity.ok(teacherService.toggleActive(id));
    }
}
