package dev.estudo.rita.ForumHub.controller;
import dev.estudo.rita.ForumHub.dto.CadastroDto;
import dev.estudo.rita.ForumHub.dto.LoginDto;
import dev.estudo.rita.ForumHub.exception.dadosTokenJWT;
import dev.estudo.rita.ForumHub.model.Usuario;
import dev.estudo.rita.ForumHub.repository.UsuarioRepository;
import dev.estudo.rita.ForumHub.exception.security.TokenService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/auth")
public class AutenticacaoController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private AuthenticationManager manager;

    @Autowired
    private TokenService tokenService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/login")
    public ResponseEntity Login(@RequestBody @Valid LoginDto loginDto) {
        var token = new UsernamePasswordAuthenticationToken( loginDto.login(), loginDto.senha());
        var autenticacao = manager.authenticate(token);
        var tokenJWT = tokenService.gerarToken((Usuario) autenticacao.getPrincipal());

        return ResponseEntity.ok(new dadosTokenJWT(tokenJWT));
    }

@PostMapping
@Transactional
public ResponseEntity cadastrar(@RequestBody @Valid CadastroDto cadastro) {
        if(usuarioRepository.existsByLogin(cadastro.login())){
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body("Login em uso");
        }
    var usuario = new Usuario(
            null,
            cadastro.nome(),
            cadastro.email(),
            passwordEncoder.encode(cadastro.senha()),
            cadastro.login());

    usuarioRepository.save(usuario);
    return ResponseEntity.ok().build();
  }
}