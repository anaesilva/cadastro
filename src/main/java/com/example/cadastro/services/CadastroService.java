package com.example.cadastro.services;

import com.example.cadastro.entities.Cadastro;
import com.example.cadastro.repositories.CadastroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CadastroService {
    @Autowired
    private CadastroRepository cadastroRepository;

    public List<Cadastro> findAll() {
        List<Cadastro> result = cadastroRepository.findAll();
        return result;
    }
}
