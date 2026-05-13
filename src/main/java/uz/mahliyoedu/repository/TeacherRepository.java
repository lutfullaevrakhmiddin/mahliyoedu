package uz.mahliyoedu.repository;

import uz.mahliyoedu.entity.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface TeacherRepository extends JpaRepository<Teacher, Long> {

    // Faqat aktiv ustozlarni olish — saytda ko'rsatish uchun
    List<Teacher> findByActiveTrue();
}
