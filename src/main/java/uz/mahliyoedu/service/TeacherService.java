package uz.mahliyoedu.service;

import uz.mahliyoedu.entity.Teacher;
import uz.mahliyoedu.repository.TeacherRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.util.List;

@Service
public class TeacherService {

    private final TeacherRepository teacherRepository;
    private final ImageKitService imageKitService;

    public TeacherService(TeacherRepository teacherRepository,
                          ImageKitService imageKitService) {
        this.teacherRepository = teacherRepository;
        this.imageKitService = imageKitService;
    }

    public List<Teacher> getAllActive() {
        return teacherRepository.findByActiveTrue();
    }

    public List<Teacher> getAll() {
        return teacherRepository.findAll();
    }

    public Teacher create(String name, String subject,
                          String description, MultipartFile photo) {
        Teacher teacher = new Teacher();
        teacher.setName(name);
        teacher.setSubject(subject);
        teacher.setDescription(description);
        if (photo != null && !photo.isEmpty()) {
            String photoUrl = imageKitService.uploadImage(
                photo, "teacher_" + System.currentTimeMillis());
            teacher.setPhotoUrl(photoUrl);
        }
        return teacherRepository.save(teacher);
    }

    public Teacher update(Long id, String name, String subject,
                          String description, MultipartFile photo) {
        Teacher teacher = teacherRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Ustoz topilmadi: " + id));
        teacher.setName(name);
        teacher.setSubject(subject);
        teacher.setDescription(description);
        if (photo != null && !photo.isEmpty()) {
            String photoUrl = imageKitService.uploadImage(
                photo, "teacher_" + System.currentTimeMillis());
            teacher.setPhotoUrl(photoUrl);
        }
        return teacherRepository.save(teacher);
    }

    public void delete(Long id) {
        teacherRepository.deleteById(id);
    }

    public Teacher toggleActive(Long id) {
        Teacher teacher = teacherRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Ustoz topilmadi: " + id));
        teacher.setActive(!teacher.isActive());
        return teacherRepository.save(teacher);
    }
}
