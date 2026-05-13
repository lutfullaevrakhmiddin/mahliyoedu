package uz.mahliyoedu.repository;

import uz.mahliyoedu.entity.Application;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ApplicationRepository extends JpaRepository<Application, Long> {

    // Ko'rilmagan so'rovlarni olish
    List<Application> findByViewedFalse();

    // Ko'rilgan so'rovlarni olish
    List<Application> findByViewedTrue();

    // Telefon raqam bo'yicha qidirish
    List<Application> findByPhone(String phone);
}
