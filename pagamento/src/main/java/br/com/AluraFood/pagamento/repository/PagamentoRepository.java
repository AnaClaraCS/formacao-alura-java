package br.com.AluraFood.pagamento.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.AluraFood.pagamento.model.Pagamento;

public interface PagamentoRepository extends JpaRepository<Pagamento, Long> {

    
}
