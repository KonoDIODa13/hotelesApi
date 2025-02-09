package com.example.hotelesapi.Repositorys;

import com.example.hotelesapi.Entities.Hotel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface HotelRepository extends JpaRepository<Hotel, Integer> {


}
