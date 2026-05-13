package uz.mahliyoedu.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

// Bu klass bazadagi "courses" jadvaliga mos
@Entity
@Table(name = "courses")
@Getter
@Setter
public class Course {

    // Primary key, avtomatik generatsiya
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Kurs nomi — bo'sh bo'lmasligi kerak
    @Column(nullable = false)
    private String name;

    // Kurs rasmi URL si — ImageKit dan keladi
    private String imageUrl;

    // Kurs faolmi yoki yo'q
    @Column(nullable = false)
    private boolean active = true;
}
