package br.ufscar.dc.dsw.pacotesturisticos.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.math.BigDecimal;

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
import br.ufscar.dc.dsw.pacotesturisticos.domain.AbstractEntity;
import br.ufscar.dc.dsw.pacotesturisticos.domain.Agencia;
import br.ufscar.dc.dsw.pacotesturisticos.domain.Compra;
import br.ufscar.dc.dsw.pacotesturisticos.service.spec.IPacoteService;
import br.ufscar.dc.dsw.pacotesturisticos.service.spec.IImagemService;
import br.ufscar.dc.dsw.pacotesturisticos.service.spec.IAgenciaService;
import br.ufscar.dc.dsw.pacotesturisticos.service.spec.ICompraService;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.bind.annotation.CrossOrigin;
import com.fasterxml.jackson.databind.ObjectMapper;

import br.ufscar.dc.dsw.pacotesturisticos.security.UsuarioDetails;

@CrossOrigin
@RestController
public class PacoteRestController{
	
	@Autowired
	private IPacoteService pacoteService;
	@Autowired
	private IImagemService imagemService;
	@Autowired
	private IAgenciaService agenciaService;
	@Autowired
	private ICompraService compraService;
	
	private boolean isJSONValid(String jsonInString) {
		try {
			return new ObjectMapper().readTree(jsonInString) != null;
		}
		catch (IOException e) {
			return false;
		}
	}
	
	private void parse(Agencia agencia, JSONObject json) {
		Map<String, Object> map = (Map<String, Object>) json.get("agencia");
		
		Object id = map.get("id");
		if (id instanceof Integer) {
			agencia.setId(((Integer) id).longValue());
		}
		else {
			agencia.setId((Long) id);
		}
		
		agencia.setEmail((String) map.get("email"));
		agencia.setSenha((String) map.get("senha"));
		agencia.setNome((String) map.get("nome"));
		agencia.setCnpj((String) map.get("cnpj"));
		agencia.setDescricao((String) map.get("descricao"));
		agencia.setPacotes((List<Pacote>) map.get("pacotes"));

	}
	
	private void parse(List<Imagem> imagens, JSONObject json) {
		Map<String, Object> map = (Map<String, Object>) json.get("imagem");
		
		Object id = map.get("id");
		if (id instanceof Integer) {
			((AbstractEntity<Long>) imagens).setId(((Integer) id).longValue());
		}
		else {
			((AbstractEntity<Long>) imagens).setId((Long) id);
		}
		
		((Imagem) imagens).setByteStream((byte[]) json.get("byteStream"));
		((Imagem) imagens).setTipo((String) json.get("tipo"));

	}
	@SuppressWarnings("unchecked")
	private void parse(Pacote pacote, JSONObject json) {
		
		Object id = json.get("id");
		if (id != null) {
			if (id instanceof Integer) {
				pacote.setId(((Integer) id).longValue());
			}
			else {
				pacote.setId((Long) id);
			}
		}
				
		pacote.setCidade((String) json.get("cidade"));
		pacote.setEstado((String) json.get("estado"));
		pacote.setPais((String) json.get("pais"));
		pacote.setPartida((String) json.get("partida"));
		pacote.setDuracao((int) json.get("duracao"));
		pacote.setPreco((BigDecimal) json.get("preco"));
		pacote.setDescricao((String) json.get("descricao"));
		pacote.setImagens((List<Imagem>) json.get("imagens"));
		
		Agencia agencia = new Agencia();
		parse(agencia, json);
		pacote.setAgencia(agencia);
		
		List<Imagem> imagens = (List<Imagem>) new Imagem();
		parse(imagens, json);
		pacote.setImagens(imagens);
	}
	
	//Mostrar todos os pacotes
	@GetMapping(path = "/pacotes")
	public ResponseEntity<List<Pacote>> lista() {
		List<Pacote> lista = pacoteService.findAll();
		if (lista.isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		//adicionar imagens de cada pacote a lista
		return ResponseEntity.ok(lista);
	}

	@GetMapping(path = "/pacotes/{id}")
	public ResponseEntity<Pacote> detalhes(@PathVariable("id") Long id) {
		Pacote pacote = pacoteService.findById(id);
		if (pacote == null) {
			return ResponseEntity.notFound().build();	
		}
		return ResponseEntity.ok(pacote);
	}
	
	//Mostrar todos os pacotes de um cliente
	//@GetMapping(path = "/pacotes/clientes/{id}")
	//Vai ser feito no de compra

	
	//Cadastrar novo pacote em uma agencia
	@PostMapping(path = "/pacotes/agencias/{id}")
	@ResponseBody
	public ResponseEntity<Pacote> cadastra(@PathVariable("id") Long id, @RequestBody JSONObject json) {
		Agencia agencia = agenciaService.findById(id);
		if (agencia == null) {
			return ResponseEntity.notFound().build();
		}
		if (isJSONValid(json.toString())) {
			Pacote pacote = new Pacote();
			parse(pacote, json);
			pacote.setAgencia(agencia);
			pacoteService.save(pacote);
			return ResponseEntity.ok(pacote);
		}
		return ResponseEntity.badRequest().build();
	}
	
	//Mostrar todos os pacotes de uma agencia
	@GetMapping(path = "/pacotes/agencias/{id}")
	public ResponseEntity<List<Pacote>> listaPorAgencia(@PathVariable("id") long id){
		
		Agencia agencia = agenciaService.findById(id);
		List<Pacote> lista = pacoteService.findByAgencia(agencia);
		
		if (lista.isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(lista);
	}
	
	//Mostrar os pacotes de um destino pela cidade
	@GetMapping(path = "/pacotes/destinos/{cidade}")
	public ResponseEntity<List<Pacote>> listaPorCidade(@PathVariable("cidade") String cidade){
		List<Pacote> pacotes = pacoteService.findByCidade(cidade);
		
		if (pacotes.isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(pacotes);
	}
}