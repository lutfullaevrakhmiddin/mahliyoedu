package uz.mahliyoedu.controller;

import uz.mahliyoedu.entity.Course;
import uz.mahliyoedu.service.CourseService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
public class CourseController {

    private final CourseService courseService;

    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    // GET /api/courses — saytda ko'rsatish uchun aktiv kurslar (ochiq)
    @GetMapping("/courses")
    public ResponseEntity<List<Course>> getAllActive() {
        return ResponseEntity.ok(courseService.getAllActive());
    }

    // GET /api/admin/courses — barcha kurslar (admin uchun)
    @GetMapping("/admin/courses")
    public ResponseEntity<List<Course>> getAll() {
        return ResponseEntity.ok(courseService.getAll());
    }

    // POST /api/admin/courses — yangi kurs qo'shish
    // multipart/form-data — rasm va nom birgalikda yuboriladi
    @PostMapping("/admin/courses")
    public ResponseEntity<Course> create(
            @RequestParam("name") String name,
            @RequestParam(value = "image", required = false) MultipartFile image) {
        return ResponseEntity.status(201).body(courseService.create(name, image));
    }

    // PUT /api/admin/courses/{id} — kursni yangilash
    @PutMapping("/admin/courses/{id}")
    public ResponseEntity<Course> update(
            @PathVariable Long id,
            @RequestParam("name") String name,
            @RequestParam(value = "image", required = false) MultipartFile image) {
        return ResponseEntity.ok(courseService.update(id, name, image));
    }

    // DELETE /api/admin/courses/{id} — o'chirish
    @DeleteMapping("/admin/courses/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        courseService.delete(id);
        return ResponseEntity.noContent().build();
    }

    // PUT /api/admin/courses/{id}/toggle — aktiv/passiv
    @PutMapping("/admin/courses/{id}/toggle")
    public ResponseEntity<Course> toggleActive(@PathVariable Long id) {
        return ResponseEntity.ok(courseService.toggleActive(id));
    }
}
