package com.example.cadastro.repositories;

import com.example.cadastro.entities.Cadastro;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CadastroRepository extends JpaRepository<Cadastro, Long> {
}
