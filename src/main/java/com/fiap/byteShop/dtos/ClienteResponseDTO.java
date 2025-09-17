package com.fiap.byteShop.dtos;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(
        description = "DTO de resposta com os dados do cliente cadastrado.",
        example = "{\"id\": 10, \"nome\": \"João Silva\", \"email\": \"joao@exemplo.com\", \"documento\": \"12345678900\"}"
)
public class ClienteResponseDTO {
    @Schema(description = "ID do cliente", example = "10")
    private Long id;

    @Schema(description = "Nome completo do cliente", example = "João Silva")
    private String nome;

    @Schema(description = "E-mail do cliente", example = "joao@exemplo.com")
    private String email;

    @Schema(description = "Documento do cliente (CPF, RG, etc)", example = "12345678900")
    private String documento;

    public ClienteResponseDTO() {
    }

    public ClienteResponseDTO(Long id, String nome, String email, String documento) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.documento = documento;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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