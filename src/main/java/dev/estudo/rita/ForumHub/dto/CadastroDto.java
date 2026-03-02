package dev.estudo.rita.ForumHub.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record CadastroDto(

        @NotBlank
        String login,

        @NotBlank
        String senha,

        @NotBlank String nome,

        @NotBlank
        @Email
        String email) {
}
