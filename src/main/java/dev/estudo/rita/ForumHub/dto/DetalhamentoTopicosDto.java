package dev.estudo.rita.ForumHub.dto;
import dev.estudo.rita.ForumHub.model.Curso;
import dev.estudo.rita.ForumHub.model.StatusTopico;
import dev.estudo.rita.ForumHub.model.Topicos;
import dev.estudo.rita.ForumHub.model.Usuario;

import java.time.LocalDateTime;

public record DetalhamentoTopicosDto(
        Long id,
        String titulo,
        String mensagem,
        LocalDateTime dataCriacao,
        StatusTopico status,
        Usuario autor,
        Curso curso)
{

    public DetalhamentoTopicosDto(Topicos topico) {
        this(
                topico.getId(),
                topico.getTitulo(),
                topico.getMensagem(),
                topico.getDataCriacao(),
                topico.getStatus(),
                topico.getAutor(),
                topico.getCurso()
               );
 }

}
