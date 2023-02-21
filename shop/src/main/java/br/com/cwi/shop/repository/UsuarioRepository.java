package br.com.cwi.shop.repository;

import br.com.cwi.shop.entities.Usuario;
import br.com.cwi.shop.models.LoginModel;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UsuarioRepository {

    @Autowired
    private EntityManager entityManager;

    public List<Usuario> buscarUsuarios(String username){
        var query = entityManager.createQuery("select u from Usuario u where u.username like :username", Usuario.class);
        query.setParameter("username", username);
        return query.getResultList();
    }

    public Usuario login(LoginModel loginModel){
        var query = entityManager.createQuery("select u from Usuario u where u.username = :username and u.senha = :senha", Usuario.class);
        query.setParameter("username", loginModel.getUsername());
        query.setParameter("senha", loginModel.getSenha());
        var list = query.getResultList();
        if(list.isEmpty())
            return null;
        else
            return list.get(0);
    }

}
