package com.example.cadastro.services;

import com.example.cadastro.entities.Cadastro;
import com.example.cadastro.repositories.CadastroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Optional;

@Service
public class CadastroService {
    @Autowired
    private CadastroRepository cadastroRepository;

    public List<Cadastro> findAll() {
        List<Cadastro> result = cadastroRepository.findAll();
        return result;
    }


    public Optional<Cadastro> findById(@PathVariable Long id) {
        Optional<Cadastro> optionalCadastro = cadastroRepository.findById(id);
         return optionalCadastro;
    }


    public Cadastro save(Cadastro cadastro) {
        return cadastroRepository.save(cadastro);
    }


    public void delete(@PathVariable Long id) {
        cadastroRepository.deleteById(id);
    }

}
