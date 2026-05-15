package uz.mahliyoedu.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDateTime;

// Bu klass bazadagi "applications" jadvaliga mos
@Entity
@Table(name = "applications")
@Getter
@Setter
public class Application {

    // Primary key, avtomatik generatsiya
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Foydalanuvchi ismi, bo'sh bo'lmasligi kerak
    @Column(nullable = false)
    private String name;

    // Telefon raqam, bo'sh bo'lmasligi kerak
    @Column(nullable = false)
    private String phone;

    // So'rov yuborilgan vaqt, avtomatik o'rnatiladi
    @Column(nullable = false)
    private LocalDateTime createdAt;

    // Qaysi curs uchun so'ro'v
    @Column(length = 100)
    private String course;

    // Admin ko'rdimi yoki yo'q, default false
    @Column(nullable = false)
    private boolean viewed = false;

    // Obyekt bazaga saqlanishidan oldin vaqtni avtomatik o'rnatadi
    @PrePersist
    public void prePersist() {
        this.createdAt = LocalDateTime.now();
    }
}
