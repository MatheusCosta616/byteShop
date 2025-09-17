package com.fiap.byteShop.services.impl;

import com.fiap.byteShop.dtos.ClienteRequestDTO;
import com.fiap.byteShop.dtos.ClienteResponseDTO;
import com.fiap.byteShop.models.Cliente;
import com.fiap.byteShop.repositories.ClienteRepository;
import com.fiap.byteShop.repositories.PedidoRepository;
import com.fiap.byteShop.services.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ClienteServiceImpl implements ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private PedidoRepository pedidoRepository;

    @Override
    public ClienteResponseDTO save(ClienteRequestDTO dto) {
        if (dto.getNome() == null || dto.getNome().isBlank()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Nome obrigatório");
        }
        if (dto.getEmail() == null || dto.getEmail().isBlank()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Email obrigatório");
        }
        if (dto.getDocumento() == null || dto.getDocumento().isBlank()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Documento obrigatório");
        }
        if (clienteRepository.existsByEmail(dto.getEmail())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "E-mail já cadastrado");
        }
        Cliente cliente = new Cliente();
        cliente.setNome(dto.getNome());
        cliente.setEmail(dto.getEmail());
        cliente.setDocumento(dto.getDocumento());
        cliente = clienteRepository.save(cliente);
        return toResponseDTO(cliente);
    }

    @Override
    public List<ClienteResponseDTO> getAll() {
        List<Cliente> clientes = clienteRepository.findAll();
        return clientes.stream()
                .map(this::toResponseDTO)
                .collect(Collectors.toList());
    }

    @Override
    public ClienteResponseDTO findById(Long id) {
        Cliente cliente = clienteRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cliente não encontrado"));
        return toResponseDTO(cliente);
    }

    @Override
    public ClienteResponseDTO update(Long id, ClienteRequestDTO dto) {
        Cliente cliente = clienteRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cliente não encontrado"));

        if (dto.getNome() == null || dto.getNome().isBlank()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Nome obrigatório");
        }
        if (dto.getEmail() == null || dto.getEmail().isBlank()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Email obrigatório");
        }
        if (dto.getDocumento() == null || dto.getDocumento().isBlank()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Documento obrigatório");
        }
        Optional<Cliente> outroCliente = clienteRepository.findByEmail(dto.getEmail());
        if (outroCliente.isPresent() && !outroCliente.get().getId().equals(id)) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "E-mail já cadastrado");
        }
        cliente.setNome(dto.getNome());
        cliente.setEmail(dto.getEmail());
        cliente.setDocumento(dto.getDocumento());
        cliente = clienteRepository.save(cliente);
        return toResponseDTO(cliente);
    }

    @Override
    public void delete(Long id) {
        Cliente cliente = clienteRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cliente não encontrado"));
        if (pedidoRepository.existsByClienteId(cliente.getId())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Não é possível excluir cliente com pedidos vinculados");
        }
        clienteRepository.delete(cliente);
    }

    private ClienteResponseDTO toResponseDTO(Cliente cliente) {
        return new ClienteResponseDTO(
                cliente.getId(),
                cliente.getNome(),
                cliente.getEmail(),
                cliente.getDocumento()
        );
    }
}