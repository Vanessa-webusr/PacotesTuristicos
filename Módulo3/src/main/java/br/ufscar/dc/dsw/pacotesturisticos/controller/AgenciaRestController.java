package br.ufscar.dc.dsw.pacotesturisticos.controller;

import java.io.IOException;
import java.util.List;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.CrossOrigin;
import com.fasterxml.jackson.databind.ObjectMapper;
import br.ufscar.dc.dsw.pacotesturisticos.domain.Agencia;
import br.ufscar.dc.dsw.pacotesturisticos.domain.Cliente;
import br.ufscar.dc.dsw.pacotesturisticos.domain.Compra;
import br.ufscar.dc.dsw.pacotesturisticos.domain.Imagem;
import br.ufscar.dc.dsw.pacotesturisticos.domain.Pacote;
import br.ufscar.dc.dsw.pacotesturisticos.service.spec.IAgenciaService;
import br.ufscar.dc.dsw.pacotesturisticos.service.spec.IClienteService;
import br.ufscar.dc.dsw.pacotesturisticos.service.spec.ICompraService;
import br.ufscar.dc.dsw.pacotesturisticos.service.spec.IImagemService;
import br.ufscar.dc.dsw.pacotesturisticos.service.spec.IPacoteService;

@CrossOrigin
@RestController

public class AgenciaRestController {
	@Autowired
	private IAgenciaService agenciaService;
	@Autowired
	private IClienteService clienteService;
	@Autowired
	private IPacoteService pacoteService;
	@Autowired
	private ICompraService compraService;
	@Autowired
	private IImagemService imagemService;
	
	private boolean isJSONValid(String jsonInString) {
		try {
			return new ObjectMapper().readTree(jsonInString) != null;
		} catch (IOException e) {
			return false;
		}
 }

	private void parse(Agencia agencia, JSONObject json) {
		
		Object id = json.get("id");
		if (id != null) {
			if (id instanceof Integer) {
				agencia.setId(((Integer) id).longValue());
			} else {
				agencia.setId((Long) id);
			}
 	}

		agencia.setEmail((String) json.get("email"));
		agencia.setSenha((String) json.get("senha"));
		agencia.setNome((String) json.get("nome"));
		agencia.setCnpj((String) json.get("cnpj"));
		agencia.setDescricao((String) json.get("descricao"));
		agencia.setPacotes((List<Pacote>) json.get("pacotes"));
 }

	@GetMapping(path = "/agencias")
	public ResponseEntity<List<Agencia>> lista() {
		List<Agencia> lista = agenciaService.findAll();
		if (lista.isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(lista);
 }

	@GetMapping(path = "/agencias/{id}")
	public ResponseEntity<Agencia> lista(@PathVariable("id") long id) {
		Agencia agencia = agenciaService.findById(id);
		if (agencia == null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(agencia);
 }

	@PostMapping(path = "/agencias")
	@ResponseBody
	public ResponseEntity<Agencia> cria(@RequestBody JSONObject json) {
		try {
			if (isJSONValid(json.toString())) {
				Agencia agencia = new Agencia();
				parse(agencia, json);
				Agencia agencia2 = agenciaService.findById(agencia.getId());
				if (agencia2 != null) {
					return ResponseEntity.status(HttpStatus.CONFLICT).build();
				}
				agencia2 = agenciaService.findByCnpj(agencia.getCnpj());
				if (agencia2 != null) {
					return ResponseEntity.status(HttpStatus.CONFLICT).build();
				}
				agencia2 = agenciaService.findByEmail(agencia.getEmail());
				Cliente cliente = clienteService.findByEmail(agencia.getEmail());
				if (agencia2 != null || cliente != null) {
					return ResponseEntity.status(HttpStatus.CONFLICT).build();
				}
				agenciaService.save(agencia);
				return ResponseEntity.ok(agencia);
			} else {
				return ResponseEntity.badRequest().body(null);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(null);
		}
 }

	@PutMapping(path = "/agencias/{id}")
	public ResponseEntity<Agencia> atualiza(@PathVariable("id") long id, @RequestBody JSONObject json) {
		try {
			if (isJSONValid(json.toString())) {
				Agencia agencia = agenciaService.findById(id);
				if (agencia == null) {
					return ResponseEntity.notFound().build();
				} else {
					parse(agencia, json);
					if (agencia.getId() != id) {
						return ResponseEntity.badRequest().build();
					}
					Agencia agencia2 = agenciaService.findByCnpj(agencia.getCnpj());
					if (agencia2 != null && agencia2.getId() != agencia.getId()) {
						return ResponseEntity.status(HttpStatus.CONFLICT).build();
					}
					agencia2 = agenciaService.findByEmail(agencia.getEmail());
					Cliente cliente = clienteService.findByEmail(agencia.getEmail());
					if (agencia2 != null && agencia2.getId() != agencia.getId() || cliente != null) {
						return ResponseEntity.status(HttpStatus.CONFLICT).build();
					}
					agenciaService.save(agencia);
					return ResponseEntity.ok(agencia);
				}
			} else {
				return ResponseEntity.badRequest().body(null);
			}
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(null);
		}
 }

	@DeleteMapping(path = "/agencias/{id}")
 public ResponseEntity<Boolean> remove(@PathVariable("id") long id) {

		Agencia agencia = agenciaService.findById(id);
		if (agencia == null) {
			return ResponseEntity.notFound().build();
		} else {
			List<Pacote> pacotes = pacoteService.findByAgencia(agencia);
			for (Pacote pacote : pacotes) {
				List<Compra> compras = compraService.findByPacote(pacote);
				for (Compra compra : compras) {
					compraService.deleteById(compra.getId());
				}
				List<Imagem> imagens = imagemService.findByPacote(pacote);
				for (Imagem imagem : imagens) {
					imagemService.deleteById(imagem.getId());
				}
				pacoteService.deleteById(pacote.getId());
			}
			agenciaService.deleteById(id);
			return ResponseEntity.noContent().build();
		}
	}
	
}

