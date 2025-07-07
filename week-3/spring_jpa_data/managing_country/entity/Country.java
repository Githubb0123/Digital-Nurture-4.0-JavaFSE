package com.example.country.entity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "country")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Country {
    @Id
    @Column(name = "co_code", nullable = false)
    private String coCode;

    @Column(name = "co_name", nullable = false)
    private String coName;
}