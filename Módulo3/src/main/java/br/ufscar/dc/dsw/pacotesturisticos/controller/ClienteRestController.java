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
import br.ufscar.dc.dsw.pacotesturisticos.service.spec.IAgenciaService;
import br.ufscar.dc.dsw.pacotesturisticos.service.spec.IClienteService;
import br.ufscar.dc.dsw.pacotesturisticos.service.spec.ICompraService;

@CrossOrigin
@RestController

public class ClienteRestController {
	@Autowired
	private IClienteService clienteService;
	@Autowired
	private IAgenciaService agenciaService;
	@Autowired
	private ICompraService compraService;
	
	private boolean isJSONValid(String jsonInString) {
		try {
			return new ObjectMapper().readTree(jsonInString) != null;
		} catch (IOException e) {
			return false;
		}
 }

	private void parse(Cliente cliente, JSONObject json) {
		
		Object id = json.get("id");
		if (id != null) {
			if (id instanceof Integer) {
				cliente.setId(((Integer) id).longValue());
			} else {
				cliente.setId((Long) id);
			}
 	}

		cliente.setEmail((String) json.get("email"));
		cliente.setSenha((String) json.get("senha"));
		cliente.setNome((String) json.get("nome"));
		cliente.setCpf((String) json.get("cpf"));
		cliente.setTelefone((String) json.get("telefone"));
		String s = (String) json.get("sexo");
		char sexo = s.charAt(0);
		cliente.setSexo(sexo);
		cliente.setDataNascimento((String) json.get("dataNascimento"));
		cliente.setTipo((String) json.get("tipo"));
 }

	@GetMapping(path = "/clientes")
	public ResponseEntity<List<Cliente>> lista() {
		List<Cliente> lista = clienteService.findAll();
		if (lista.isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(lista);
 }

	@GetMapping(path = "/clientes/{id}")
	public ResponseEntity<Cliente> lista(@PathVariable("id") long id) {
		Cliente cliente = clienteService.findById(id);
		if (cliente == null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(cliente);
 }

	@PostMapping(path = "/clientes")
	@ResponseBody
	public ResponseEntity<Cliente> cria(@RequestBody JSONObject json) {
		try {
			if (isJSONValid(json.toString())) {
				Cliente cliente = new Cliente();
				parse(cliente, json);
				Cliente cliente2 = null;
				if(cliente.getId() != null){
					cliente2 = clienteService.findById(cliente.getId());
					if (cliente2 != null) {
						return ResponseEntity.status(HttpStatus.CONFLICT).build();
					}
				}
				cliente2 = clienteService.findByEmail(cliente.getEmail());
				Agencia agencia = agenciaService.findByEmail(cliente.getEmail());
				if (cliente2 != null || agencia != null) {
					return ResponseEntity.status(HttpStatus.CONFLICT).build();
				}
				cliente2 = clienteService.findByCpf(cliente.getCpf());
				if (cliente2 != null) {
					return ResponseEntity.status(HttpStatus.CONFLICT).build();
				}
				clienteService.save(cliente);
				return ResponseEntity.ok(cliente);
			} else {
				return ResponseEntity.badRequest().body(null);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(null);
		}
 }

	@PutMapping(path = "/clientes/{id}")
	public ResponseEntity<Cliente> atualiza(@PathVariable("id") long id, @RequestBody JSONObject json) {
		try {
			if (isJSONValid(json.toString())) {
				Cliente cliente = clienteService.findById(id);
				if (cliente == null) {
					return ResponseEntity.notFound().build();
				} else {
					parse(cliente, json);
					Cliente cliente2 = clienteService.findByEmail(cliente.getEmail());
					Agencia agencia = agenciaService.findByEmail(cliente.getEmail());
					if (cliente2 != null && cliente2.getId() != cliente.getId() || agencia != null) {
						return ResponseEntity.status(HttpStatus.CONFLICT).build();
					}
					cliente2 = clienteService.findByCpf(cliente.getCpf());
					if (cliente2 != null && cliente2.getId() != cliente.getId()) {
						return ResponseEntity.status(HttpStatus.CONFLICT).build();
					}
					clienteService.save(cliente);
					return ResponseEntity.ok(cliente);
				}
			} else {
				return ResponseEntity.badRequest().body(null);
			}
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(null);
		}
 }

	@DeleteMapping(path = "/clientes/{id}")
 public ResponseEntity<Boolean> remove(@PathVariable("id") long id) {
		Cliente cliente = clienteService.findById(id);
		if (cliente == null) {
			return ResponseEntity.notFound().build();
		} else {
			for(Compra compra : compraService.findByCliente(clienteService.findById(id))){
				compraService.deleteById(compra.getId());
			}
			clienteService.deleteById(id);
			return ResponseEntity.noContent().build();
		}
	}
	
}

