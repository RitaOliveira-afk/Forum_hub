package dev.estudo.rita.ForumHub.dto;
import dev.estudo.rita.ForumHub.model.StatusTopico;
import jakarta.validation.constraints.NotNull;

public record AtualizarTopicoDto(

        @NotNull
        Long id,
        String titulo,
        String mensagem,
        StatusTopico status) {
}
