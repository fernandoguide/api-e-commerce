package br.com.ecommerce.api.repositories;

import br.com.ecommerce.api.domain.Pagamento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PagamentoRepository  extends JpaRepository<Pagamento, Integer> {

}
