package dev.estudo.rita.ForumHub.dto;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import dev.estudo.rita.ForumHub.model.Curso;
import dev.estudo.rita.ForumHub.model.StatusTopico;
import dev.estudo.rita.ForumHub.model.Topicos;
import dev.estudo.rita.ForumHub.model.Usuario;

import java.time.LocalDateTime;

@JsonPropertyOrder({"status","autor","curso", "titulo", "mensagem", "dataCriacao" })
public record ListaTopicosDto(
        String titulo,
        String mensagem,
        LocalDateTime dataCriacao,
        StatusTopico status,
        Usuario autor,
        Curso curso)
    {

    public ListaTopicosDto(Topicos topico) {
        this(
                topico.getTitulo(),
                topico.getMensagem(),
                topico.getDataCriacao(),
                topico.getStatus(),
                topico.getAutor(),
                topico.getCurso()
        );
    }
}