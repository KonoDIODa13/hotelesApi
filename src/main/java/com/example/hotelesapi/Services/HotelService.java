package com.example.hotelesapi.Services;

import com.example.hotelesapi.Entitys.Hotel;
import com.example.hotelesapi.Repositorys.HotelRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HotelService {
    private final HotelRepository hotelRepository;

    public HotelService(HotelRepository hotelRepository) {
        this.hotelRepository = hotelRepository;
    }

    public List<Hotel> findAllAgricultores() {
        return hotelRepository.findAll();
    }

}

