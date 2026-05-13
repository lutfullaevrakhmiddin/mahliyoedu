package uz.mahliyoedu.repository;

import uz.mahliyoedu.entity.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface AdminRepository extends JpaRepository<Admin, Long> {

    // Email bo'yicha admin topish — login uchun kerak
    Optional<Admin> findByEmail(String email);
}
