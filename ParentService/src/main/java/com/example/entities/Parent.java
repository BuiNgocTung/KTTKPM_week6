package com.example.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "parents")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Parent {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long parentId;
    @Column
    private String name;
    @Column
    private String email;
    @OneToOne
    private Student student;

    public Parent(long parentId, String name, String email) {
        this.parentId = parentId;
        this.name = name;
        this.email = email;
    }
}
