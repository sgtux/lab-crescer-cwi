package br.com.cwi.shop.repository;

import br.com.cwi.shop.entities.Comentario;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ComentarioRepository {

    @Autowired
    EntityManager entityManager;

    public List<Comentario> obterPorPostId(long postId) {
        var query = entityManager.createQuery("select c from Comentario c where c.post_id = :postId", Comentario.class);
        query.setParameter("postId", postId);
        return query.getResultList();
    }
}
