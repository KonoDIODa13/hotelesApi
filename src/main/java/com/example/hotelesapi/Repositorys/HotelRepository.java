package com.example.hotelesapi.Repositorys;

import com.example.hotelesapi.Entities.Hotel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
// import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface HotelRepository extends JpaRepository<Hotel, Integer> {
 // query para buscar hoteles segun su localidad.
    @Query("SELECT h FROM Hotel h WHERE h.localidad= :localidad")
    public List<Hotel> getHotelsByLocalidad(String localidad);
 /*
    otra manera de hacerlo en la que se utiliza @Param
    @Query("SELECT h FROM Hotel h WHERE h.localidad = :localidad")
    List<Hotel> buscarHotelLocalidad(
            @Param("localidad") String localidad
 */
    @Query("SELECT h FROM Hotel h WHERE h.categoria =:categoria")
    public List<Hotel> getHotelsByCategoria(int categoria);

    @Query("SELECT h FROM Hotel h WHERE h.id =:id")
    public Optional<Hotel> getHotelById(int id);

}
