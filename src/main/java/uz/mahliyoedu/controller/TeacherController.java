package uz.mahliyoedu.controller;

import uz.mahliyoedu.entity.Teacher;
import uz.mahliyoedu.service.TeacherService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
public class TeacherController {

    private final TeacherService teacherService;

    public TeacherController(TeacherService teacherService) {
        this.teacherService = teacherService;
    }

    // GET /api/teachers — saytda ko'rsatish uchun aktiv ustozlar
    // Bu endpoint hammaga ochiq
    @GetMapping("/teachers")
    public ResponseEntity<List<Teacher>> getAllActive() {
        return ResponseEntity.ok(teacherService.getAllActive());
    }

    // GET /api/admin/teachers — barcha ustozlar (admin uchun)
    @GetMapping("/admin/teachers")
    public ResponseEntity<List<Teacher>> getAll() {
        return ResponseEntity.ok(teacherService.getAll());
    }

    // POST /api/admin/teachers — yangi ustoz qo'shish (admin uchun)
    @PostMapping("/admin/teachers")
    public ResponseEntity<Teacher> save(@RequestBody Teacher teacher) {
        return ResponseEntity.status(201).body(teacherService.save(teacher));
    }

    // DELETE /api/admin/teachers/{id} — ustozni o'chirish (admin uchun)
    @DeleteMapping("/admin/teachers/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        teacherService.delete(id);
        return ResponseEntity.noContent().build();
    }

    // PUT /api/admin/teachers/{id}/toggle — aktiv/passiv qilish (admin uchun)
    @PutMapping("/admin/teachers/{id}/toggle")
    public ResponseEntity<Teacher> toggleActive(@PathVariable Long id) {
        return ResponseEntity.ok(teacherService.toggleActive(id));
    }
}
