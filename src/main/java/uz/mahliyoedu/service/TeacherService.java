package uz.mahliyoedu.service;

import uz.mahliyoedu.entity.Teacher;
import uz.mahliyoedu.repository.TeacherRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class TeacherService {

    private final TeacherRepository teacherRepository;

    // Constructor injection
    public TeacherService(TeacherRepository teacherRepository) {
        this.teacherRepository = teacherRepository;
    }

    // Saytda ko'rsatish uchun faqat aktiv ustozlarni olish
    public List<Teacher> getAllActive() {
        return teacherRepository.findByActiveTrue();
    }

    // Admin uchun barcha ustozlarni olish
    public List<Teacher> getAll() {
        return teacherRepository.findAll();
    }

    // Yangi ustoz qo'shish — admin uchun
    public Teacher save(Teacher teacher) {
        return teacherRepository.save(teacher);
    }

    // Ustozni o'chirish — admin uchun
    public void delete(Long id) {
        teacherRepository.deleteById(id);
    }

    // Ustozni aktiv/passiv qilish
    public Teacher toggleActive(Long id) {
        Teacher teacher = teacherRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Teacher not found: " + id));
        teacher.setActive(!teacher.isActive());
        return teacherRepository.save(teacher);
    }
}
