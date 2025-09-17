package com.fiap.byteShop.services;

import com.fiap.byteShop.dtos.ClienteRequestDTO;
import com.fiap.byteShop.dtos.ClienteResponseDTO;
import java.util.List;

public interface ClienteService {
    ClienteResponseDTO save(ClienteRequestDTO cliente);
    List<ClienteResponseDTO> getAll();
    ClienteResponseDTO findById(Long id);
    ClienteResponseDTO update(Long id, ClienteRequestDTO cliente);
    void delete(Long id);
}