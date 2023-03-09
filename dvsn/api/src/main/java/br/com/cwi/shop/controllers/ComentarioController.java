package br.com.cwi.shop.controllers;

import br.com.cwi.shop.dtos.ComentarioDto;
import br.com.cwi.shop.entities.Comentario;
import br.com.cwi.shop.entities.Post;
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

        try {
            var postId = comentarioDto.getPostId();

            if (postId == 0 || postRepository.buscarPorId(postId) == null) {
                return badRequest("Post não encontrado.");
            }

            if (StringHelper.isNullOrEmpty(comentarioDto.getTexto())) {
                return badRequest("Texto é obrigatório");
            }

            var usuario = usuarioRepository.buscarPorId(comentarioDto.getUsuarioId());

            if (usuario == null)
                return badRequest("Usuário não encontrado");

            var comentario = new Comentario();

            comentario.setTexto(comentarioDto.getTexto());
            comentario.setCriadoEm(new Date());

            comentario.setUsuario(usuario);

            var post = new Post(postId);
            comentario.setPost(post);

            comentarioRepository.save(comentario);

            return ResponseEntity.ok().build();
        }catch(Exception ex){
            return internalServerError(ex);
        }
    }

    @DeleteMapping("comentario/{id}")
    public ResponseEntity removerComentario(@PathVariable long id) {

        try {
            if (!comentarioRepository.existsById(id))
                return badRequest("Comentário não encontrado.");

            comentarioRepository.deleteById(id);
            return ResponseEntity.ok().build();
        }catch(Exception ex) {
            return internalServerError(ex);
        }
    }
}