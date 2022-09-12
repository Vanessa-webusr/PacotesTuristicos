package br.ufscar.dc.dsw.pacotesturisticos.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.math.BigDecimal;
import java.time.LocalDate;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.json.simple.JSONObject;
import org.hibernate.annotations.Parameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import br.ufscar.dc.dsw.pacotesturisticos.domain.Pacote;
import br.ufscar.dc.dsw.pacotesturisticos.domain.Imagem;
import br.ufscar.dc.dsw.pacotesturisticos.domain.Agencia;
import br.ufscar.dc.dsw.pacotesturisticos.domain.Cliente;
import br.ufscar.dc.dsw.pacotesturisticos.domain.Compra;
import br.ufscar.dc.dsw.pacotesturisticos.service.spec.IPacoteService;
import br.ufscar.dc.dsw.pacotesturisticos.service.spec.IImagemService;
import br.ufscar.dc.dsw.pacotesturisticos.service.spec.IAgenciaService;
import br.ufscar.dc.dsw.pacotesturisticos.service.spec.IClienteService;
import br.ufscar.dc.dsw.pacotesturisticos.service.spec.ICompraService;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.bind.annotation.CrossOrigin;
import com.fasterxml.jackson.databind.ObjectMapper;

import br.ufscar.dc.dsw.pacotesturisticos.security.UsuarioDetails;

@CrossOrigin
@RestController
public class CompraRestController{
	
	@Autowired
	private ICompraService compraService;
	
	@Autowired
	private IClienteService clienteService;

	@Autowired
	private IAgenciaService agenciaService;
	
	private boolean isJSONValid(String jsonInString) {
		try {
			return new ObjectMapper().readTree(jsonInString) != null;
		} catch (IOException e) {
			return false;
		}
	}
	
	@SuppressWarnings("unchecked")
	private void parse(Cliente cliente, JSONObject json) {
		Map<String, Object> map = (Map<String, Object>) json.get("cliente");
		
		Object id = map.get("id");
		if (id instanceof Integer) {
			cliente.setId(((Integer) id).longValue());
		} else {
			cliente.setId((Long) id);
		}
		 		
		cliente.setEmail((String) map.get("email"));
		cliente.setSenha((String) map.get("senha"));
		cliente.setNome((String) map.get("nome"));
		cliente.setCpf((String) map.get("cpf"));
		cliente.setTelefone((String) map.get("telefone"));
		String s = (String) map.get("sexo");
		char sexo = s.charAt(0);
		cliente.setSexo(sexo);
		cliente.setDataNascimento((String) map.get("dataNascimento"));
		cliente.setTipo((String) map.get("tipo"));
		cliente.setCompras((List<Compra>) map.get("compras"));

	}
	
	private void parse(Pacote pacote, JSONObject json) {
Map<String, Object> map = (Map<String, Object>) json.get("pacote");
		
		Object id = map.get("id");
		if (id instanceof Integer) {
			pacote.setId(((Integer) id).longValue());
		}
		else {
			pacote.setId((Long) id);
		}
		
		pacote.setCidade((String) map.get("cidade"));
		pacote.setEstado((String) map.get("estado"));
		pacote.setPais((String) map.get("pais"));
		pacote.setPartida((String) map.get("partida"));
		pacote.setDuracao((int) map.get("duracao"));
		pacote.setPreco((BigDecimal) map.get("preco"));
		pacote.setDescricao((String) map.get("descricao"));
		pacote.setImagens((List<Imagem>) map.get("imagens"));
		pacote.setAgencia((Agencia) map.get("agencia"));

	}
	
	private void parse(Compra compra, JSONObject json) {
		Object id = json.get("id");
		if(id != null) {
			if(id instanceof Integer) {
				compra.setId(((Integer) id).longValue());
			}
			else {
				compra.setId((Long) id);
			}
		}
		
		compra.setValor((BigDecimal) json.get("valor"));
		compra.setAtivo((boolean) json.get("ativo"));
		
		Cliente cliente = new Cliente();
		parse(cliente, json);
		compra.setCliente(cliente);
		
		Pacote pacote = new Pacote();
		parse(pacote, json);
		compra.setPacote(pacote);
		
	}
	
	@PostMapping(path = "/compras")
	@ResponseBody
	public ResponseEntity<Compra> cria(@RequestBody JSONObject json) {
		try {
			if (isJSONValid(json.toString())) {
				Compra compra = new Compra();
				parse(compra, json);
				LocalDate dataPartida = LocalDate.parse(compra.getPacote().getPartida());
				if(dataPartida.isBefore(LocalDate.now())){
					return ResponseEntity.badRequest().build();
				}
				compra.setAtivo(true);
				compraService.save(compra);
				return ResponseEntity.ok(compra);
			} else {
				return ResponseEntity.badRequest().body(null);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(null);
		}
	}

	@PutMapping(path = "/compras/{id}")
	public ResponseEntity<Compra> update(@PathVariable("id") long id) {
		Compra compra = compraService.findById(id);
		if (compra == null) {
			return ResponseEntity.notFound().build();
		} else {
			LocalDate dataPartida = LocalDate.parse(compra.getPacote().getPartida());
			if(dataPartida.minusDays(5).isBefore(LocalDate.now())){
				return ResponseEntity.badRequest().build();
			}
			compra.setAtivo(false);
			compraService.save(compra);
			return ResponseEntity.ok(compra);
		}
	}
	
	@GetMapping(path = "/compras/clientes/{id}")
	public ResponseEntity<List<Compra>> listaPorCliente(@PathVariable("id") long id) {
		
		Cliente cliente = clienteService.findById(id);
		List<Compra> lista = compraService.findByCliente(cliente);
		
		if (lista == null) {
			return ResponseEntity.notFound().build();
		} else {
			return ResponseEntity.ok(lista);
		}
	}

	@GetMapping(path = "/compras")
	public ResponseEntity<List<Compra>> lista() {
		List<Compra> lista = compraService.findAll();
		if (lista == null) {
			return ResponseEntity.notFound().build();
		} else {
			return ResponseEntity.ok(lista);
		}
	}

	@GetMapping(path = "/compras/{id}")
	public ResponseEntity<Compra> busca(@PathVariable("id") long id) {
		Compra compra = compraService.findById(id);
		if (compra == null) {
			return ResponseEntity.notFound().build();
		} else {
			return ResponseEntity.ok(compra);
		}
	}
	
	@DeleteMapping(path = "/compras/{id}")
	public ResponseEntity<Compra> delete(@PathVariable("id") long id) {
		Compra compra = compraService.findById(id);
		if (compra == null) {
			return ResponseEntity.notFound().build();
		} else {
			compraService.deleteById(compra.getId());
			return ResponseEntity.ok(compra);
		}
	}
	
}