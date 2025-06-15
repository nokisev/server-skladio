package ru.nokisev.skladio.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "users")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = true)
    private String username;
    @Column(nullable = true)
    private String password;
    private String email;
    private boolean isAdmin = false;
    @Column(nullable = true)
    @OneToMany
    private List<Order> orders;
}
