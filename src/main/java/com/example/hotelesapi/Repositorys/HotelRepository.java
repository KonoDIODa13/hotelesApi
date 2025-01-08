package com.example.hotelesapi.Repositorys;

import com.example.hotelesapi.Entitys.Hotel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HotelRepository extends JpaRepository<Hotel, Integer> {


}
