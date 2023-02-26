package br.com.cwi.shop.controllers;

import br.com.cwi.shop.dtos.ComentarioDto;
import br.com.cwi.shop.entities.Comentario;
import br.com.cwi.shop.entities.Post;
import br.com.cwi.shop.entities.Usuario;
import br.com.cwi.shop.helpers.StringHelper;
import br.com.cwi.shop.repository.ComentarioRepository;
import br.com.cwi.shop.repository.PostRepository;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RestController
public class ComentarioController extends BaseController {

    @Autowired
    private ComentarioRepository comentarioRepository;

    @Autowired
    private PostRepository postRepository;

    @PostMapping("comentario")
    public ResponseEntity criarComentario(HttpServletRequest request, @RequestBody ComentarioDto comentarioDto) {

        var postId = comentarioDto.getPostId();

        if(postId == 0 || postRepository.buscarPorId(postId) == null)
        {
            return badRequest("Post não encontrado.");
        }

        if(StringHelper.isNullOrEmpty(comentarioDto.getTexto())){
            return badRequest("Texto é obrigatório");
        }

        var comentario = new Comentario();

        comentario.setTexto(comentarioDto.getTexto());
        comentario.setCriadoEm(new Date());

        var usuarioLogadoId = obterUsuarioLogado(request).getId();
        var usuario = new Usuario(usuarioLogadoId);
        comentario.setUsuario(usuario);

        var post = new Post(postId);
        comentario.setPost(post);

        comentarioRepository.save(comentario);

        return ResponseEntity.ok().build();
    }

    @DeleteMapping("comentario/{id}")
    public ResponseEntity removerComentario(@RequestParam long id) {

        if(!comentarioRepository.existsById(id))
            return badRequest("Comentário não encontrado.");

        comentarioRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }
}