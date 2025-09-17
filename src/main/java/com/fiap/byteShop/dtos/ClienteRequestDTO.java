package com.fiap.byteShop.dtos;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(
        description = "DTO para criar ou atualizar um cliente.",
        example = "{\"nome\": \"João Silva\", \"email\": \"joao@exemplo.com\", \"documento\": \"12345678900\"}"
)
public class ClienteRequestDTO {
    @Schema(description = "Nome completo do cliente", example = "João Silva", required = true)
    private String nome;

    @Schema(description = "E-mail do cliente (deve ser único)", example = "joao@exemplo.com", required = true)
    private String email;

    @Schema(description = "Documento do cliente (CPF, RG, etc)", example = "12345678900", required = true)
    private String documento;

    public ClienteRequestDTO() {

    }

    public ClienteRequestDTO(String nome, String email, String documento) {
        this.nome = nome;
        this.email = email;
        this.documento = documento;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDocumento() {
        return documento;
    }

    public void setDocumento(String documento) {
        this.documento = documento;
    }
}