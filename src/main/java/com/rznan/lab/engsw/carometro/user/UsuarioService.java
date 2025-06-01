package com.rznan.lab.engsw.carometro.user;

import com.rznan.lab.engsw.carometro.exception.EmailExistsException;
import com.rznan.lab.engsw.carometro.exception.ServiceExc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;


@Service
public class UsuarioService implements UserDetailsService {

    @Autowired
    private UsuarioRepository repository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    public void saveUser(Usuario user) throws Exception {

        if(repository.findByEmail(user.getEmail()) != null) {
            throw new EmailExistsException("Este email já está cadastrado: " + user.getEmail());
        }

        user.setSenha(passwordEncoder.encode(user.getSenha()));
        user.setRole("ADMIN");

        repository.save(user);
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Usuario usuario = repository.findByEmail(email);
        if (usuario == null) {
            throw new UsernameNotFoundException("Usuário não encontrado");
        }

        return org.springframework.security.core.userdetails.User.builder()
                .username(usuario.getEmail())
                .password(usuario.getSenha())
                .roles("ADMIN") // ou usuario.getRole()
                .build();
    }
}
