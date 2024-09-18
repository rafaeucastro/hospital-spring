package med.voll.api.domain.usuario;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AutenticacaoService implements UserDetailsService {

    @Autowired
    UsuarioRepository usuarioRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
       return usuarioRepository.findByLogin(username);
    }

    public UserDetails createUser(String login, String senha) {
        var newUser = new Usuario();

        newUser.setLogin(login);
        newUser.setSenha(senha);

        return usuarioRepository.save(newUser);
    }

    public List<Usuario> getUsers(){
        return usuarioRepository.findAll();
    }
}
