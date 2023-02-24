package br.com.cwi.shop.repository;

import br.com.cwi.shop.entities.Post;
import br.com.cwi.shop.entities.Usuario;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PostRepository {

    @Autowired
    @PersistenceContext
    EntityManager entityManager;

    @Autowired
    UsuarioRepository usuarioRepository;

    @Autowired
    ComentarioRepository comentarioRepository;

    public List<Post> buscar(String filtro) {
        var sqlString = "SELECT p FROM Post p join fetch p.usuario left join fetch p.comentarios where p.texto like '%" + filtro + "%' order by p.criadoEm desc";
        return entityManager.createQuery(sqlString, Post.class).getResultList();
    }

    @Transactional
    public void add(Post post) {
        var sqlString = "INSERT INTO Post (texto, foto, visibilidade, criado_em, usuario_id) VALUES (:texto, :foto, :visibilidade, :criadoEm, :usuarioId)";
        entityManager.createNativeQuery(sqlString)
                .setParameter("texto", post.getTexto())
                .setParameter("foto", post.getFoto())
                .setParameter("visibilidade", post.getVisibilidade())
                .setParameter("criadoEm", post.getCriadoEm())
                .setParameter("usuarioId", post.getUsuario().getId())
                .executeUpdate();
    }
}