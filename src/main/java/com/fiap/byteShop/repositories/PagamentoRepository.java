package com.fiap.byteShop.repositories;

import com.fiap.byteShop.models.Pagamento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PagamentoRepository extends JpaRepository<Pagamento, Long> {
	Pagamento findByPedidoId(Long pedidoId);
}
