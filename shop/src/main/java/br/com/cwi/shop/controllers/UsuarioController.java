package br.com.cwi.shop.controllers;

import br.com.cwi.shop.dtos.UsuarioDto;
import br.com.cwi.shop.entities.Usuario;
import br.com.cwi.shop.models.LoginModel;
import br.com.cwi.shop.repository.UsuarioRepository;
import jakarta.persistence.EntityManager;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UsuarioController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @GetMapping("usuario")
    public Iterable<Usuario> getUsuarios(@RequestParam String username) {
         return usuarioRepository.buscarUsuarios(username);
    }

    @PostMapping("login")
    public ResponseEntity<?> login(@RequestBody LoginModel loginModel) {
        var usuario = usuarioRepository.login(loginModel);
        if(usuario != null){
            var usuarioDto = new UsuarioDto();
            BeanUtils.copyProperties(usuario, usuarioDto);
            return new ResponseEntity<UsuarioDto>(usuarioDto, HttpStatus.OK);
        }
        return new ResponseEntity<String>("Não Autorizado", HttpStatus.UNAUTHORIZED);
    }
}