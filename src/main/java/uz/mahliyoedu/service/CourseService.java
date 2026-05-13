package uz.mahliyoedu.service;

import uz.mahliyoedu.entity.Course;
import uz.mahliyoedu.repository.CourseRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.util.List;

@Service
public class CourseService {

    private final CourseRepository courseRepository;
    private final ImageKitService imageKitService;

    public CourseService(CourseRepository courseRepository,
                         ImageKitService imageKitService) {
        this.courseRepository = courseRepository;
        this.imageKitService = imageKitService;
    }

    // Saytda ko'rsatish uchun faqat aktiv kurslar
    public List<Course> getAllActive() {
        return courseRepository.findByActiveTrue();
    }

    // Admin uchun barcha kurslar
    public List<Course> getAll() {
        return courseRepository.findAll();
    }

    // Yangi kurs qo'shish — rasm bilan
    public Course create(String name, MultipartFile image) {
        Course course = new Course();
        course.setName(name);

        // Rasm yuklash
        if (image != null && !image.isEmpty()) {
            String imageUrl = imageKitService.uploadImage(
                image,
                "course_" + System.currentTimeMillis()
            );
            course.setImageUrl(imageUrl);
        }

        return courseRepository.save(course);
    }

    // Kursni yangilash
    public Course update(Long id, String name, MultipartFile image) {
        Course course = courseRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Kurs topilmadi: " + id));

        course.setName(name);

        // Yangi rasm yuklangan bo'lsa
        if (image != null && !image.isEmpty()) {
            String imageUrl = imageKitService.uploadImage(
                image,
                "course_" + System.currentTimeMillis()
            );
            course.setImageUrl(imageUrl);
        }

        return courseRepository.save(course);
    }

    // Kursni o'chirish
    public void delete(Long id) {
        courseRepository.deleteById(id);
    }

    // Kursni aktiv/passiv qilish
    public Course toggleActive(Long id) {
        Course course = courseRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Kurs topilmadi: " + id));
        course.setActive(!course.isActive());
        return courseRepository.save(course);
    }
}
