package br.edu.utfpr.pb.oficinaweb.service.impl;

import br.edu.utfpr.pb.oficinaweb.data.UsuarioData;
import br.edu.utfpr.pb.oficinaweb.entity.Usuario;
import br.edu.utfpr.pb.oficinaweb.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UsuarioServiceImpl extends CrudServiceImpl<Usuario, Long> implements UsuarioService, UserDetailsService, CommandLineRunner {

    @Autowired private UsuarioData usuarioData;

    @Override
    protected JpaRepository<Usuario, Long> getData() {
        return usuarioData;
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        Optional<Usuario> usuarioOptional = usuarioData.findByUsername(s);
        return usuarioOptional.orElseThrow(() -> new UsernameNotFoundException("username not found"));
    }

    @Override
    public void run(String... args) throws Exception {
        Usuario usuario = Usuario.builder()
                .id(1L)
                .ativo(true)
                .email("joao.p.merlin@gmail.com")
                .nome("Admin")
                .username("admin")
                .password(new BCryptPasswordEncoder().encode("admin"))
                .build();

        save(usuario);
    }
}
