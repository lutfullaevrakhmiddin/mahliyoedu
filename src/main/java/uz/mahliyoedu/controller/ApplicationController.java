package uz.mahliyoedu.controller;

import jakarta.validation.Valid;
import uz.mahliyoedu.dto.ApplicationRequestDto;
import uz.mahliyoedu.entity.Application;
import uz.mahliyoedu.service.ApplicationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
public class ApplicationController {

    private final ApplicationService applicationService;

    public ApplicationController(ApplicationService applicationService) {
        this.applicationService = applicationService;
    }

    // @Valid — validation tekshiriladi, xato bo'lsa 400 qaytaradi
    @PostMapping("/applications")
    public ResponseEntity<Application> create(@Valid @RequestBody ApplicationRequestDto dto) {
        Application saved = applicationService.save(dto);
        return ResponseEntity.status(201).body(saved);
    }

    @GetMapping("/admin/applications")
    public ResponseEntity<List<Application>> getAll() {
        return ResponseEntity.ok(applicationService.getAll());
    }

    @GetMapping("/admin/applications/unviewed")
    public ResponseEntity<List<Application>> getUnviewed() {
        return ResponseEntity.ok(applicationService.getUnviewed());
    }

    @PutMapping("/admin/applications/{id}/view")
    public ResponseEntity<Application> markAsViewed(@PathVariable Long id) {
        return ResponseEntity.ok(applicationService.markAsViewed(id));
    }

    @DeleteMapping("/admin/applications/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        applicationService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
