package com.example.cadastro.controller;

import com.example.cadastro.entities.Cadastro;
import com.example.cadastro.services.CadastroService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

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

    @GetMapping(value = "/{id}")
    public Optional<Cadastro> findById(@PathVariable Long id) {
        Optional<Cadastro> optionalCadastro = cadastroService.findById(id);
        return optionalCadastro;
    }

    @PostMapping
    public Cadastro postCadastro(@RequestBody Cadastro data) {
        return cadastroService.save(data);
    }

    @PutMapping("/{id}")
    public Cadastro updateCadastro(@PathVariable Long id, @RequestBody Cadastro cadastroAtualizado) {
        Optional<Cadastro> optionalCadastro = cadastroService.findById(id);

        if (optionalCadastro.isPresent()) {
            Cadastro cadastroExistente = optionalCadastro.get();
            cadastroExistente.setName(cadastroAtualizado.getName());
            cadastroExistente.setCpf(cadastroAtualizado.getCpf());
            cadastroExistente.setEmail(cadastroAtualizado.getEmail());

            return cadastroService.save(cadastroExistente);
        } else {
            throw new EntityNotFoundException();
        }
    }

    @DeleteMapping("/{id}")
    public void deleteCadastro(@PathVariable Long id) {
        cadastroService.delete(id);
    }

}

