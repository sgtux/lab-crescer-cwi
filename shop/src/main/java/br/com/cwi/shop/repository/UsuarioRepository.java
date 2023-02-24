package br.com.cwi.shop.repository;

import br.com.cwi.shop.dtos.UsuarioDto;
import br.com.cwi.shop.entities.Usuario;
import br.com.cwi.shop.helpers.StringHelper;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UsuarioRepository {

    @Autowired
    private EntityManager entityManager;

    public Usuario buscarPorId(long id){
        var query = entityManager.createQuery("select u from Usuario u where u.id = :id", Usuario.class);
        query.setParameter("id", id);
        var list = query.getResultList();
        return list.isEmpty() ? null : list.get(0);
    }

    public Usuario login(UsuarioDto usuario){
        var query = String.format("select u from Usuario u where u.email = '%s' and u.senha = '%s'", usuario.getEmail(), StringHelper.md5(usuario.getSenha()));
        var list = entityManager.createQuery(query, Usuario.class).getResultList();
        if(list.isEmpty())
            return null;
        else
            return list.get(0);
    }

}