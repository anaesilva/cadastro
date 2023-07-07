package com.example.cadastro.services;

import com.example.cadastro.entities.Cadastro;
import com.example.cadastro.repositories.CadastroRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CadastroServiceTest {

    @InjectMocks
    CadastroService service;

    @Mock
    CadastroRepository repository;

    Cadastro user;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);

        user = new Cadastro(1L, "Ana", "12345678", "ana@example");
    }

    @Test
    void getAllCadastro() {
        List<Cadastro> mockCadastros = new ArrayList<>();
        mockCadastros.add(new Cadastro(1L, "carlos", "02824106061", "carlos@example.com"));
        mockCadastros.add(new Cadastro(2L, "maria", "92824106061", "maria@example.com"));

      when(repository.findAll()).thenReturn(mockCadastros);

      List<Cadastro> result = service.findAll();

        assertEquals(mockCadastros, result);
    }

    @Test
    void getAllEmpty() {
        List<Cadastro> mockCadastros = new ArrayList<>();

        when(repository.findAll()).thenReturn(mockCadastros);

        List<Cadastro> result = service.findAll();

        assertEquals(0, result.size());
    }

    @Test
    void getByIdCadastro() {
       when(repository.findById(user.getId())).thenReturn(Optional.of(user));

      Optional<Cadastro> result = service.findById(user.getId());

      assertEquals(result.get().getId(), 1L);
    }

    @Test
    void saveCadastro() {
        when(repository.save(user)).thenReturn(user);

        Cadastro result = service.save(user);

        assertEquals(result, user);
    }

    @Test
    void deleteCadastro() {
        service.delete(user.getId());
        verify(repository).deleteById(user.getId());
    }

}
