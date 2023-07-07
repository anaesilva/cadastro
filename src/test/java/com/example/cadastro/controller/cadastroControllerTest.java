package com.example.cadastro.controller;


import com.example.cadastro.entities.Cadastro;
import com.example.cadastro.services.CadastroService;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;

@SpringBootTest
public class cadastroControllerTest {

    @Mock
    private CadastroService cadastroService;

    Cadastro user;

    @InjectMocks
    private CadastroController controller;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);

        user = new Cadastro(1L, "Ana", "12345678", "ana@example");
    }

    @Test
    void getAll(){
        List<Cadastro> mockCadastros = new ArrayList<>();
        mockCadastros.add(new Cadastro(1L, "joel", "1034106061", "joel@example.com"));
        mockCadastros.add(new Cadastro(2L, "carla", "92824106061", "carla@example.com"));

        Mockito.when(cadastroService.findAll()).thenReturn(mockCadastros);

        List<Cadastro> response = controller.findAll();

        Assertions.assertEquals(response, mockCadastros);
    }

    @Test
    void getById() {
        Mockito.when(cadastroService.findById(user.getId())).thenReturn(Optional.of(user));

        Optional<Cadastro> response = controller.findById(user.getId());

        assertEquals(response.get().getId(), user.getId());
    }

   @Test
   void save() {
        Mockito.when(cadastroService.save(user)).thenReturn(user);

        Cadastro response = controller.postCadastro(user);

        assertEquals(user, response);
   }

   @Test
    void putCadastro() {
       Long id = 1L;
       Cadastro cadastroAtualizado = new Cadastro(id, "Ana", "01111111111", "ana@example.com");

       Mockito.when(cadastroService.findById(id)).thenReturn(Optional.of(user));
       Mockito.when(cadastroService.save(user)).thenReturn(user);

       Cadastro result = controller.updateCadastro(id, cadastroAtualizado);

       assertEquals(user, result);

       assertEquals(cadastroAtualizado.getName(), user.getName());
       assertEquals(cadastroAtualizado.getCpf(), user.getCpf());
       assertEquals(cadastroAtualizado.getEmail(), user.getEmail());
   }

    @Test
    public void UpdateCadastroNotFound() {
        Long id = 1L;
        Cadastro cadastroAtualizado = new Cadastro(id, "Ana", "977654321", "anna@example.com");

        Mockito.when(cadastroService.findById(id)).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> controller.updateCadastro(id, cadastroAtualizado));
    }

    @Test
    void deleteCadastro() {
        controller.deleteCadastro(user.getId());
        verify(cadastroService).delete(user.getId());
    }
}
