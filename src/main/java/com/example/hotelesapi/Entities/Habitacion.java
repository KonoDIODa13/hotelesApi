package com.example.hotelesapi.Entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.SortComparator;

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


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getTamanno() {
        return tamanno;
    }

    public void setTamanno(int tamanno) {
        this.tamanno = tamanno;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public boolean isDesayuno() {
        return desayuno;
    }

    public void setDesayuno(boolean desayuno) {
        this.desayuno = desayuno;
    }

    public boolean isOcupada() {
        return ocupada;
    }

    public void setOcupada(boolean ocupada) {
        this.ocupada = ocupada;
    }

    public Hotel getHotel() {
        return hotel;
    }

    public void setHotel(Hotel hotel) {
        this.hotel = hotel;
    }
}
