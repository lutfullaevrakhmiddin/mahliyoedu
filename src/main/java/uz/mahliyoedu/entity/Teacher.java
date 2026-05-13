package uz.mahliyoedu.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

// Bu klass bazadagi "teachers" jadvaliga mos
// Hozir faqat arxitektura, keyinchalik admin qo'sha oladi
@Entity
@Table(name = "teachers")
@Getter
@Setter
public class Teacher {

    // Primary key, avtomatik generatsiya
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Ustoz ismi
    @Column(nullable = false)
    private String name;

    // Ustoz o'qitadigan fan
    @Column(nullable = false)
    private String subject;

    // Ustoz haqida qisqacha ma'lumot
    @Column(length = 500)
    private String description;

    // Ustoz rasmi URL si
    private String photoUrl;

    // Ustoz saytda ko'rsatilsinmi
    @Column(nullable = false)
    private boolean active = true;
}
