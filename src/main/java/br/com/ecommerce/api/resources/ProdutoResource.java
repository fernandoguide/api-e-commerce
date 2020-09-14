package br.com.ecommerce.api.resources;

import br.com.ecommerce.api.services.ClienteService;
import br.com.ecommerce.api.services.ProdutoService;
import br.com.ecommerce.api.domain.Produto;
import br.com.ecommerce.api.dto.ProdutoDTO;
import br.com.ecommerce.api.resources.utils.URL;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value="/produtos")
public class ProdutoResource {

	@Autowired
	private ProdutoService service;
	@Autowired
	private ClienteService clienteService;

	@ApiOperation(value="Busca de Produto por id")
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public ResponseEntity<Produto> find(@PathVariable Integer id) {
		Produto obj = service.find(id);
		return ResponseEntity.ok().body(obj);
	}

	@ApiOperation(value="Busca de Produto paginada")
	@RequestMapping(method=RequestMethod.GET)
	public ResponseEntity<Page<ProdutoDTO>> findPage(
			@RequestParam(value="nome", defaultValue="Memória") String nome,
			@RequestParam(value="categorias", defaultValue="1") String categorias,
			@RequestParam(value="page", defaultValue="0") Integer page, 
			@RequestParam(value="linesPerPage", defaultValue="24") Integer linesPerPage, 
			@RequestParam(value="orderBy", defaultValue="nome") String orderBy, 
			@RequestParam(value="direction", defaultValue="ASC") String direction) {
		String nomeDecoded = URL.decodeParam(nome);
		List<Integer> ids = URL.decodeIntList(categorias);
		Page<Produto> list = service.search(nomeDecoded, ids, page, linesPerPage, orderBy, direction);
		Page<ProdutoDTO> listDto = list.map(ProdutoDTO::new);
		return ResponseEntity.ok().body(listDto);
	}

}
