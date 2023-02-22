package br.com.cwi.shop.repository;

import br.com.cwi.shop.entities.Usuario;
import br.com.cwi.shop.dtos.LoginDto;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UsuarioRepository {

    @Autowired
    private EntityManager entityManager;

    public List<Usuario> buscarUsuario(String username){
        var query = entityManager.createQuery("select u from Usuario u where u.username like :username", Usuario.class);
        query.setParameter("username", username);
        return query.getResultList();
    }

    public Usuario login(LoginDto loginModel){
        var query = String.format("select u from Usuario u where u.username = '%s' and u.senha = '%s'", loginModel.getUsername(), loginModel.getSenha());
        var list = entityManager.createQuery(query, Usuario.class).getResultList();
        if(list.isEmpty())
            return null;
        else
            return list.get(0);
    }

}
