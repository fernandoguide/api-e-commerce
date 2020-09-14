package br.com.ecommerce.api.services;

import br.com.ecommerce.api.domain.Cidade;
import br.com.ecommerce.api.domain.Cliente;
import br.com.ecommerce.api.domain.Endereco;
import br.com.ecommerce.api.domain.enums.Perfil;
import br.com.ecommerce.api.domain.enums.TipoCliente;
import br.com.ecommerce.api.dto.ClienteDTO;
import br.com.ecommerce.api.dto.ClienteNewDTO;
import br.com.ecommerce.api.dto.ClienteUpdateDTO;
import br.com.ecommerce.api.repositories.ClienteRepository;
import br.com.ecommerce.api.repositories.EnderecoRepository;
import br.com.ecommerce.api.security.UserSS;
import br.com.ecommerce.api.services.excepitions.AuthorizationException;
import br.com.ecommerce.api.services.excepitions.DataIntegrityExecpition;
import br.com.ecommerce.api.services.excepitions.ObjectNotFoundExecpition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.awt.image.BufferedImage;
import java.net.URI;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class ClienteService {

    public final static String ACESSO_NEGADO = "Acesso negado";

    @Autowired
    private ClienteRepository repo;

    @Autowired
    private EnderecoRepository enderecoRepository;

    @Autowired
    private BCryptPasswordEncoder pe;

    public Cliente find(Integer id) {

        UserSS user = UserService.authenticated();
        if (user == null || !user.hasRole(Perfil.ADMIN) && !id.equals(user.getId())) {
            throw new AuthorizationException(ACESSO_NEGADO);
        }
        Optional<Cliente> obj = repo.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundExecpition(
                "Objeto nao encontrado! Id: " + id + " Tipo: " + Cliente.class.getName()));
    }

    @Transactional
    public Cliente insert(Cliente obj) {
        obj.setId(null);
        obj = repo.save(obj);
        enderecoRepository.saveAll(obj.getEnderecos());
        return obj;
    }

    public Cliente fromDTO(ClienteUpdateDTO objDto, Integer id) {
        Cliente cliente = find(id);
        return validateClient(objDto, cliente);

    }

    private Cliente validateClient(ClienteUpdateDTO objDto, Cliente cliente) {
        Cliente cli = new Cliente(
                cliente.getId(),
                objDto.getNome() != null ? objDto.getNome() : cliente.getNome(),
                objDto.getEmail() != null ? objDto.getEmail() : cliente.getEmail(),
                objDto.getCpfOuCnpj() != null ? objDto.getCpfOuCnpj() : cliente.getCpfOuCnpj(),
                objDto.getTipo() != null ? TipoCliente.toEnum(objDto.getTipo()) : cliente.getTipo(),
                objDto.getSenha() != null ? pe.encode(objDto.getSenha()) : cliente.getSenha()
        );

        Cidade cid = new Cidade(
                objDto.getCidadeId() != null ? objDto.getCidadeId() :
                        cliente.getEnderecos().get(0).getCidade().getId(),
                null,
                null
        );
        Endereco end = new Endereco(
                null,
                objDto.getLogradouro() != null ? objDto.getLogradouro() :
                        cliente.getEnderecos().get(0).getLogradouro(),

                objDto.getNumero() != null ? objDto.getNumero() :
                        cliente.getEnderecos().get(0).getNumero(),

                objDto.getComplemento() != null ? objDto.getComplemento() :
                        cliente.getEnderecos().get(0).getComplemento(),

                objDto.getBairro() != null ? objDto.getBairro() :
                        cliente.getEnderecos().get(0).getBairro(),

                objDto.getCep() != null ? objDto.getCep() :
                        cliente.getEnderecos().get(0).getCep(),
                cliente,
                cid
        );
        cli.getEnderecos().add(end);
        if (objDto.getTelefone1() != null) {
            cli.getTelefones().add(objDto.getTelefone1());
        } else {
            cli.getTelefones().add(cliente.getTelefones().iterator().next());
        }
        if (objDto.getTelefone2() != null) {
            cli.getTelefones().add(objDto.getTelefone2());
        }
        if (objDto.getTelefone3() != null) {
            cli.getTelefones().add(objDto.getTelefone3());
        }
        return cli;
    }

    public Cliente fromNewDTO(ClienteNewDTO objDto) {
        Cliente cli = new Cliente(null, objDto.getNome(), objDto.getEmail(), objDto.getCpfOuCnpj(),
                TipoCliente.toEnum(objDto.getTipo()), pe.encode(objDto.getSenha()));
        return getCliente(objDto, cli);
    }

    private Cliente getCliente(ClienteNewDTO objDto, Cliente cli) {
        Cidade cid = new Cidade(objDto.getCidadeId(), null, null);
        Endereco end = new Endereco(null, objDto.getLogradouro(), objDto.getNumero(), objDto.getComplemento(),
                objDto.getBairro(), objDto.getCep(), cli, cid);

        cli.getEnderecos().add(end);
        cli.getTelefones().add(objDto.getTelefone1());
        if (objDto.getTelefone2() != null) {
            cli.getTelefones().add(objDto.getTelefone2());
        }
        if (objDto.getTelefone3() != null) {
            cli.getTelefones().add(objDto.getTelefone3());
        }
        return cli;
    }

    public Cliente update(Cliente obj) {
        Cliente newObj = find(obj.getId());
        updateData(newObj, obj);
        return repo.save(obj);
    }

    public void delete(Integer id) {
        find(id);
        try {
            repo.deleteById(id);
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityExecpition("Não é possível excluir porque há pedidos relacionados");
        }
    }

    public List<Cliente> findAll() {
        return repo.findAll();
    }

    public Cliente findByEmail(String email) {
        UserSS user = UserService.authenticated();
        if (user == null || !user.hasRole(Perfil.ADMIN) && !email.equals(user.getUsername())) {
            throw new AuthorizationException(ACESSO_NEGADO);
        }

        Cliente obj = repo.findByEmail(email);
        if (obj == null) {
            throw new ObjectNotFoundExecpition(
                    "Objeto não encontrado! Id: " + user.getId() + ", Tipo: " + Cliente.class.getName());
        }
        return obj;
    }

    public Page<Cliente> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {
        PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
        return repo.findAll(pageRequest);
    }


    private void updateData(Cliente newObj, Cliente obj) {
        newObj.setNome(obj.getNome());
        newObj.setEmail(obj.getEmail());
    }

}
