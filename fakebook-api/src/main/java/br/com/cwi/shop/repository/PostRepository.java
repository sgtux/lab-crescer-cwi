package br.com.cwi.shop.repository;

import br.com.cwi.shop.entities.Post;
import br.com.cwi.shop.entities.Usuario;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PostRepository {

    @Autowired
    EntityManager entityManager;

    @Autowired
    UsuarioRepository usuarioRepository;

    @Autowired
    ComentarioRepository comentarioRepository;

    public List<Post> buscar(String filtro) {
        var sqlString = "SELECT p FROM Post p join fetch p.usuario left join fetch p.comentarios where p.texto like '%" + filtro + "%'";
        return entityManager.createQuery(sqlString, Post.class).getResultList();
    }
}