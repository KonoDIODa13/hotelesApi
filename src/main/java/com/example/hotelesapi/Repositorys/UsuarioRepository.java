package com.example.hotelesapi.Repositorys;

import com.example.hotelesapi.Entities.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {
}
