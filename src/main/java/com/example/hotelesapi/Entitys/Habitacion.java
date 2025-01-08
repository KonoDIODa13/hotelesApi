package com.example.hotelesapi.Entitys;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

/*
CREATE TABLE Habitacion
(
    id       int NOT NULL AUTO_INCREMENT PRIMARY KEY,
    tamanno  int     DEFAULT 1,
    precio   double  DEFAULT 100.00,
    desayuno boolean DEFAULT FALSE,
    ocupada  boolean DEFAULT FALSE,
    hotel    int NOT NULL,
    FOREIGN KEY (hotel) REFERENCES Hotel(id)

) ENGINE=InnoDB  DEFAULT CHARSET=utf8mb4 AUTO_INCREMENT=1;
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name = "Habitacion")
public class Habitacion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "tamanno")
    private int tamanno;

    @Column(name = "precio")
    private double precio;

    @Column(name = "desayuno")
    private boolean desayuno;

    @Column(name = "ocupada")
    private boolean ocupada;

    @ManyToOne
    @JoinColumn(name = "hotel", referencedColumnName = "id")
    @JsonBackReference
    private Hotel hotel;

}
