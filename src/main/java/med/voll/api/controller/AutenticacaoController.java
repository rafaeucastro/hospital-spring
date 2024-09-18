package med.voll.api.controller;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import med.voll.api.domain.usuario.AutenticacaoService;
import med.voll.api.domain.usuario.DadosAutenticacao;
import med.voll.api.domain.usuario.Usuario;
import med.voll.api.infra.security.DadosTokenJWT;
import med.voll.api.infra.security.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("user")
@AllArgsConstructor()
public class AutenticacaoController {
    private TokenService tokenService;
    private AuthenticationManager authenticationManager;
    private AutenticacaoService autenticacaoService;

    @PostMapping("login")
    public ResponseEntity<DadosTokenJWT> efetuarLogin(@Valid @RequestBody DadosAutenticacao dadosAutenticacao){
        var authToken = new UsernamePasswordAuthenticationToken(dadosAutenticacao.login(), dadosAutenticacao.senha());
        var authentication = authenticationManager.authenticate(authToken);
        var tokenJWT = tokenService.gerarToken((Usuario) authentication.getPrincipal());
        return ResponseEntity.ok(new DadosTokenJWT(tokenJWT));
    }

    @PostMapping("new")
    public ResponseEntity<String> createUser(@Valid @RequestBody DadosAutenticacao dadosAutenticacao){
       final UserDetails user = this.autenticacaoService.createUser(dadosAutenticacao.login(), dadosAutenticacao.senha());

       return ResponseEntity.ok("Usuário criado com sucesso! Realize login");
    }

    @GetMapping("all")
    public ResponseEntity<List<Usuario>> listUsers(){
      return ResponseEntity.ok(this.autenticacaoService.getUsers());
    }

}
