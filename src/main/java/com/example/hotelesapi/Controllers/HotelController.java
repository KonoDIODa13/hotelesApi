package com.example.hotelesapi.Controllers;

import com.example.hotelesapi.Entitys.Hotel;
import com.example.hotelesapi.Services.HotelService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api/hotel")
public class HotelController {
    private final HotelService hotelService;

    public HotelController(HotelService hotelService) {
        this.hotelService = hotelService;
    }

    @GetMapping("/")
    public List<Hotel> getAllHoteles() {
        try {
            return hotelService.findAllAgricultores();
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Error al obtener todos los agricultores", e);
        }

    }
}
