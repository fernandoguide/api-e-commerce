package br.com.ecommerce.api.services;

import br.com.ecommerce.api.services.excepitions.AuthorizationException;
import br.com.ecommerce.api.services.excepitions.ObjectNotFoundExecpition;
import br.com.ecommerce.api.utils.DateUtils;
import br.com.ecommerce.api.domain.Cliente;
import br.com.ecommerce.api.domain.ItemPedido;
import br.com.ecommerce.api.domain.PagamentoComBoleto;
import br.com.ecommerce.api.domain.Pedido;
import br.com.ecommerce.api.domain.enums.EstadoPagamento;
import br.com.ecommerce.api.repositories.ItemPedidoRepository;
import br.com.ecommerce.api.repositories.PagamentoRepository;
import br.com.ecommerce.api.repositories.PedidoRepository;
import br.com.ecommerce.api.security.UserSS;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Optional;


@Service
public class PedidoService {

    @Autowired
    private PedidoRepository repo;

    @Autowired
    private BoletoService boletoService;

    @Autowired
    private PagamentoRepository pagamentoRepository;

    @Autowired
    private ItemPedidoRepository itemPedidoRepository;

    @Autowired
    private ProdutoService produtoService;

    @Autowired
    private ClienteService clienteService;


    public Pedido find(Integer id) {

        Optional<Pedido> obj = repo.findById(id);

        return obj.orElseThrow(() -> new ObjectNotFoundExecpition(
                "Objeto nao encontrado! Id: " + id
                        + " Tipo: " + Pedido.class.getName()));
    }

    @Transactional
    public Pedido insert(Pedido obj) throws Exception {
        obj.setId(null);
        obj.setInstante(new Date(System.currentTimeMillis()));
        obj.setCliente(clienteService.find(obj.getCliente().getId()));
        obj.getPagamento().setEstado(EstadoPagamento.PENDENTE);
        obj.getPagamento().setPedido(obj);
        if (obj.getPagamento() instanceof PagamentoComBoleto) {
            PagamentoComBoleto pagto = (PagamentoComBoleto) obj.getPagamento();
            boletoService.preencherPagamentoComBoleto(pagto);
        }
        obj = repo.save(obj);
        pagamentoRepository.save(obj.getPagamento());
        for (ItemPedido ip : obj.getItens()) {
            ip.setDesconto(0.0);
            ip.setProduto(produtoService.find(ip.getProduto().getId()));
            ip.setPreco(ip.getProduto().getPreco());
            ip.setPedido(obj);
        }
        itemPedidoRepository.saveAll(obj.getItens());

        return obj;
    }

    public Page<Pedido> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {
        UserSS user = UserService.authenticated();
        if (user == null) {
            throw new AuthorizationException("Acesso negado");
        }
        PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
        Cliente cliente = clienteService.find(user.getId());
        return repo.findByCliente(cliente, pageRequest);
    }

    public List<Pedido> findAll() {
        return repo.findAll();
    }

}
