package br.com.cwi.shop.repository;

import br.com.cwi.shop.entities.Usuario;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface UsuarioRepository extends CrudRepository<Usuario, Long> {

    @Query(value = "select * from usuario where username like ?1", nativeQuery = true)
    List<Usuario> buscarUsuarios(String username);

}
