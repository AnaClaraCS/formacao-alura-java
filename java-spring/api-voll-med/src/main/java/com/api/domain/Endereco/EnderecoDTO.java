package com.api.domain.Endereco;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record EnderecoDTO (

    @NotBlank
    String logradouro,

    Integer numero,

    String complemento,

    @NotBlank
    String bairro,

    @NotBlank
    String cidade,

    @NotBlank
    String uf,

    @NotBlank
    @Pattern(regexp = "\\d{8}")
    String cep
) {

}
