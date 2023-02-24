package br.com.cwi.shop.controllers;

import br.com.cwi.shop.dtos.PostDto;
import br.com.cwi.shop.repository.PostRepository;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class PostController extends BaseController {

    @Autowired
    public PostRepository repository;

    @GetMapping("/post")
    public List<PostDto> buscarPosts(@RequestParam(required = false) String filtro, HttpServletRequest request) {

        var list = new ArrayList<PostDto>();

        var idUsuarioLogado = obterUsuarioLogado(request).getId();

        for(var post : repository.buscar(filtro)){
            var postDto = new PostDto(post);
            postDto.setOwner(postDto.getUsuario().getId() == idUsuarioLogado);

            for(var comentario : postDto.getComentarios())
                comentario.setOwner(comentario.getUsuarioId() == idUsuarioLogado);

            list.add(postDto);
        }

        return list;
    }
}