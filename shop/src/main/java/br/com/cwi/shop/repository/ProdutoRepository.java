package br.com.cwi.shop.repository;

import br.com.cwi.shop.entities.Produto;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ProdutoRepository {

    @Autowired
    EntityManager entityManager;

    public List<Produto> getAll() {
        return new ArrayList<>();
    }

    public List<Produto> buscarPorCategoria(String categoria) {
        var jpql = String.format("SELECT * FROM produto where categoria = '%s'", categoria);
        var query = entityManager.createNativeQuery(jpql, Produto.class);
        return query.getResultList();
    }
}