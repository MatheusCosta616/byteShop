package com.fiap.byteShop.repositories;

import com.fiap.byteShop.models.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Long> {
	boolean existsByClienteId(Long clienteId);
	List<Pedido> findByClienteId(Long clienteId);
	List<Pedido> findByStatus(String status);
	List<Pedido> findByClienteIdAndStatus(Long clienteId, String status);
}