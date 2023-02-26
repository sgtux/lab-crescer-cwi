package br.com.cwi.shop.controllers;

import br.com.cwi.shop.dtos.UsuarioDto;
import br.com.cwi.shop.dtos.UsuarioLogadoDto;
import br.com.cwi.shop.helpers.Constantes;
import br.com.cwi.shop.helpers.StringHelper;
import br.com.cwi.shop.repository.UsuarioRepository;
import br.com.cwi.shop.helpers.CookieHelper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
public class UsuarioController extends BaseController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @GetMapping("usuario")
    public ResponseEntity obterDadosUsuarioLogado(HttpServletRequest request) {
        var usuarioLogado = obterUsuarioLogado(request);
        return new ResponseEntity(usuarioLogado, HttpStatus.OK);
    }

    @PostMapping("criarConta")
    public ResponseEntity criarConta(@RequestBody UsuarioDto usuarioDto) {

        if (!StringHelper.isEmail(usuarioDto.getEmail())) {
            return badRequest("Email inválido.");
        }

        if(StringHelper.isNullOrEmpty( usuarioDto.getNome())) {
            return badRequest("Nome inválido.");
        }

        if(StringHelper.isNullOrEmpty( usuarioDto.getSobrenome())) {
            return badRequest("Sobrenome inválido.");
        }

        if(usuarioRepository.buscarPorEmail(usuarioDto.getEmail()) != null) {
            return badRequest("Email já cadastrado.");
        }

        try {
            usuarioRepository.adicionar(usuarioDto);
        } catch (Exception ex) {
            return new ResponseEntity(ex, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return ResponseEntity.ok().build();
    }

    @PostMapping("token")
    public ResponseEntity<?> token(@RequestBody UsuarioDto usuarioDto, HttpServletResponse response) {

        var usuario = usuarioRepository.login(usuarioDto);

        if(usuario != null) {

            var usuarioLogadoDto = new UsuarioLogadoDto(usuario);

            try {
                String jsonData = StringHelper.toJson(usuarioLogadoDto);
                String cookieValue = StringHelper.toBase64(jsonData);
                CookieHelper.AddCookie(response, Constantes.AUTH_COOKIE_NAME, cookieValue, 60 * 60);
                return new ResponseEntity(usuarioLogadoDto, HttpStatus.OK);
            }catch (Exception ex) {
                System.out.println(ex);
                return new ResponseEntity("Erro Desconhecido", HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }
        return new ResponseEntity("Não Autorizado", HttpStatus.UNAUTHORIZED);
    }

    @GetMapping("logout")
    public ResponseEntity logout(HttpServletResponse response) {
        CookieHelper.AddCookie(response, Constantes.AUTH_COOKIE_NAME, "", 0);
        return new ResponseEntity(HttpStatus.OK);
    }
}