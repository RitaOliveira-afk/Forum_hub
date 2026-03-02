package dev.estudo.rita.ForumHub.controller;
import dev.estudo.rita.ForumHub.dto.AtualizarTopicoDto;
import dev.estudo.rita.ForumHub.dto.ListaTopicosDto;
import dev.estudo.rita.ForumHub.dto.TopicosDto;
import dev.estudo.rita.ForumHub.dto.DetalhamentoTopicosDto;
import dev.estudo.rita.ForumHub.model.Topicos;
import dev.estudo.rita.ForumHub.repository.CursoRepository;
import dev.estudo.rita.ForumHub.repository.TopicosRepository;
import dev.estudo.rita.ForumHub.repository.UsuarioRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;


@RestController
@RequestMapping("/topicos")
public class TopicosController {

    @Autowired
    private TopicosRepository topicosRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private CursoRepository cursoRepository;

    @PostMapping
    @Transactional
    public ResponseEntity <DetalhamentoTopicosDto> cadastrar(@RequestBody @Valid TopicosDto topicosDto,
                                                             UriComponentsBuilder uriBuilder) {
        var autor = usuarioRepository
                .getReferenceById(topicosDto.autorId());

        var curso = cursoRepository
                .getReferenceById(topicosDto.cursoId());

        var topicos = new Topicos(topicosDto, autor, curso);
        topicosRepository.save(topicos);

        var uri = uriBuilder.path("/topicos/{id}").buildAndExpand(topicos.getId()).toUri();
        return ResponseEntity.created(uri).body(new DetalhamentoTopicosDto(topicos));
    }

    @GetMapping
    public ResponseEntity <Page<ListaTopicosDto>> listar(Pageable paginacao) {
        var page= topicosRepository.findAll(paginacao).map(ListaTopicosDto::new);
        return ResponseEntity.ok(page);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DetalhamentoTopicosDto> detalhar(@PathVariable Long id) {
        var topicos = topicosRepository.getReferenceById(id);
        return ResponseEntity.ok(new DetalhamentoTopicosDto(topicos));
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity <DetalhamentoTopicosDto> atualizar(@PathVariable Long id,
                                                             @RequestBody @Valid AtualizarTopicoDto atualizarDto){
        var topicos = topicosRepository.getReferenceById(id);
        topicos.atualizarInformacoes(atualizarDto);
        return ResponseEntity.ok(new DetalhamentoTopicosDto(topicos));

    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity <String> deletar(@PathVariable Long id){
        topicosRepository.deleteById(id);
        return ResponseEntity.noContent().build();

    }
}

