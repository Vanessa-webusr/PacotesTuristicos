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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import br.ufscar.dc.dsw.pacotesturisticos.domain.Pacote;
import br.ufscar.dc.dsw.pacotesturisticos.domain.Imagem;
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
public class ImagemRestController{
	
	@Autowired
	private IImagemService service;
	private boolean isJSONValid(String jsonInString) {
		try {
			return new ObjectMapper().readTree(jsonInString) != null;
		} catch (IOException e) {
			return false;
		}	
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
	
	@SuppressWarnings("unchecked")
	private void parse(Imagem imagem, JSONObject json) {
		Map<String, Object> map = (Map<String, Object>) json.get("imagem");
		
		Object id = json.get("id");
		if (id instanceof Integer) {
			imagem.setId(((Integer) id).longValue());
		}
		else {
			imagem.setId((Long) id);
		}
		
		imagem.setByteStream((byte[]) json.get("byteStream"));
		imagem.setTipo((String) json.get("tipo"));
		
		Pacote pacote = new Pacote();
		parse(pacote, json);
		imagem.setPacote(pacote);
		
	}
	
	/*
	 * @DeleteMapping(path = "/imagem/{id}") public ResponseEntity<Boolean>
	 * remove(@PathVariable("id") long id){ Imagem imagem = service.findById(id); if
	 * (imagem == null) { return ResponseEntity.notFound().build(); }
	 * 
	 * else { service.deleteById(id); return ResponseEntity.noContent().build(); }
	 * 
	 * 
	 * }
	 */
	
}