package com.example.cadastro.controller;

import com.example.cadastro.entities.Cadastro;
import com.example.cadastro.services.CadastroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/perfil")
public class CadastroController {
    @Autowired
    private CadastroService cadastroService;

    @GetMapping
    public List<Cadastro> findAll() {
        List<Cadastro> result = cadastroService.findAll();
        return result;
    }
}
