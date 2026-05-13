package uz.mahliyoedu.repository;

import uz.mahliyoedu.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {

    // Faqat aktiv kurslarni olish — saytda ko'rsatish uchun
    List<Course> findByActiveTrue();
}
