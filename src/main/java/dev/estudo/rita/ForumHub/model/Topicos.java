package dev.estudo.rita.ForumHub.model;
import dev.estudo.rita.ForumHub.dto.AtualizarTopicoDto;
import dev.estudo.rita.ForumHub.dto.TopicosDto;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Entity
@Table(name ="topicos")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Topicos {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String titulo;
    private String mensagem;

    private LocalDateTime dataCriacao = LocalDateTime.now();

   @Enumerated
    private StatusTopico status;

    @ManyToOne
    @JoinColumn(name = "autor_id")
    private Usuario autor;

    @ManyToOne
    @JoinColumn(name = "curso_id")
    private Curso curso;

    public Topicos(TopicosDto topicosDto, Usuario autor, Curso curso) {
        this.titulo = topicosDto.titulo();
        this.mensagem= topicosDto.mensagem();
        this.autor = autor;
        this.curso = curso;
        this.status = StatusTopico.ABERTO;

    }

    public void atualizarInformacoes(@Valid AtualizarTopicoDto atualizarDto) {

        if(atualizarDto.titulo() != null){
        this.titulo = atualizarDto.titulo();
        }
        if(atualizarDto.mensagem() != null){
        this.mensagem = atualizarDto.mensagem();
        }
        if(atualizarDto.status() != null) {
            this.status = atualizarDto.status();

        }
    }
}