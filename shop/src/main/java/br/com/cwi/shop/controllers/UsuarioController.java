package br.com.cwi.shop.controllers;

import br.com.cwi.shop.entities.Usuario;
import br.com.cwi.shop.repository.UsuarioRepository;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UsuarioController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private EntityManager entityManager;

    @GetMapping("usuario")
    public Iterable<Usuario> getUsuarios(@RequestParam String username) {
         return usuarioRepository.buscarUsuarios(username);
    }
}