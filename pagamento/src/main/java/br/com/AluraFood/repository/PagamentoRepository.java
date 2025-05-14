package br.com.AluraFood.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.AluraFood.model.Pagamento;

public interface PagamentoRepository extends JpaRepository<Pagamento, Long> {

    
}
