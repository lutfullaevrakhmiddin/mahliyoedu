package uz.mahliyoedu.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

// Bu klass bazadagi "admins" jadvaliga mos
@Entity
@Table(name = "admins")
@Getter
@Setter
public class Admin {

    // Primary key, avtomatik generatsiya
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Admin emaili, unikal bo'lishi kerak
    @Column(nullable = false, unique = true)
    private String email;

    // Admin paroli, shifrlangan holda saqlanadi
    @Column(nullable = false)
    private String password;
}
