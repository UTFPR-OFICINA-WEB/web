package br.edu.utfpr.pb.oficinaweb.data;

import br.edu.utfpr.pb.oficinaweb.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsuarioData extends JpaRepository<Usuario, Long> {

    Optional<Usuario> findByUsername(String username);
}
