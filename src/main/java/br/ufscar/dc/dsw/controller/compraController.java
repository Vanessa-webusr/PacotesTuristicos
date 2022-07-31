package br.ufscar.dc.dsw.controller;

import br.ufscar.dc.dsw.dao.AgenciaDAO;
import br.ufscar.dc.dsw.dao.ClienteDAO;
import br.ufscar.dc.dsw.dao.CompraDAO;
import br.ufscar.dc.dsw.dao.PacoteDAO;
import br.ufscar.dc.dsw.dao.ImagemDAO;
import br.ufscar.dc.dsw.domain.Agencia;
import br.ufscar.dc.dsw.domain.Cliente;
import br.ufscar.dc.dsw.domain.Compra;
import br.ufscar.dc.dsw.domain.Pacote;
import br.ufscar.dc.dsw.domain.Imagem;
import br.ufscar.dc.dsw.domain.Login;
import br.ufscar.dc.dsw.util.Erro;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
 
 @WebServlet(urlPatterns = { "/compra/*" })
 public class compraController extends HttpServlet {
	 
	    private static final long serialVersionUID = 1L;

	    private AgenciaDAO agenciaDao;
	    private ClienteDAO clienteDao;
	    private CompraDAO compraDao;
	    private PacoteDAO pacoteDao;
	    private ImagemDAO imagemDao;

	    @Override
	    public void init() {
	        agenciaDao = new AgenciaDAO();
	        clienteDao = new ClienteDAO();
	        compraDao = new CompraDAO();
	        pacoteDao = new PacoteDAO();
	        imagemDao = new ImagemDAO();
	    }

	    @Override
	    protected void doGet(HttpServletRequest request, HttpServletResponse response)
	            throws ServletException, IOException {
	        doPost(request, response);
	    }

	    @Override
	    protected void doPost(HttpServletRequest request, HttpServletResponse response)
	            throws ServletException, IOException {
	        
	        String action = request.getPathInfo();
	        if (action == null) {
	            action = "";
	        }
	        
	        Login usuario = (Login) request.getSession().getAttribute("usuarioLogado");
	        if (usuario == null) {
	            Erro erro = new Erro("É necessário estar logado para acessar esta página.");
	            request.setAttribute("mensagens", erro);
	            response.sendRedirect("../views/login.jsp");
	            return;
	        }
	        request.setAttribute("usuario", usuario);

	        try {
	            switch(action){
	                case "/apresentacao":
	                    apresentaInformacao(request, response);
	                    break;
	                case "/insere":
	                	insereCompra(request, response);
	                	break;
					case "/lista":
						listaPorUsuario(request, response);
						break;	
					case "/cancelar":
						cancelarCompra(request, response);
						break;			
	                default:
	                    erro(request, response);
	            }
	        } catch (RuntimeException | IOException | ServletException e){
				Erro erro = new Erro();
            erro.add("Erro ao processar a requisição.");
            erro.add(e.getMessage());
            request.setAttribute("mensagens", erro);
            request.setAttribute("linkVoltar", "../pacote/lista");
            RequestDispatcher dispatcher = request.getRequestDispatcher("/views/error.jsp");
            dispatcher.forward(request, response);
            throw new ServletException(e);
	        }
	    }
	 
	    private void apresentaInformacao(HttpServletRequest request, HttpServletResponse response)
	    		throws ServletException, IOException {
	    	Long pacoteId = Long.parseLong(request.getParameter("id"));
	        Pacote pacote = pacoteDao.get(pacoteId);
	        //List <String> linkImagens = imagemDao.getLinkImagens(pacoteId);
	        request.setAttribute("pacote", pacote);
	        //request.setAttribute("imagens", linkImagens);
	    	RequestDispatcher dispatcher = request.getRequestDispatcher("/views/compra.jsp");
	    	dispatcher.forward(request, response);
	    	
	    }
	    

	    private void erro(HttpServletRequest request, HttpServletResponse response)
	      throws ServletException, IOException {
			Erro erro = new Erro("Erro ao acessar a página.");
			request.setAttribute("mensagens", erro);
			request.setAttribute("linkVoltar", "../pacote/lista");
	        RequestDispatcher dispatcher = request.getRequestDispatcher("/views/authError.jsp");
	        dispatcher.forward(request, response);
	    }
	    
	    private void insereCompra(HttpServletRequest request, HttpServletResponse response)
	            throws ServletException, IOException {
	    	request.setCharacterEncoding("UTF-8");
	    	Long pacote_id = Long.parseLong(request.getParameter("idPacote"));
			Pacote pacote = pacoteDao.get(pacote_id);
	    	Long pessoa_id = Long.parseLong(request.getParameter("idCliente"));
	    	Float valor = Float.parseFloat(request.getParameter("valor"));
	    	Compra compra = new Compra(pacote, pessoa_id, valor, 1);
	    	compraDao.insert(compra);

			response.sendRedirect("../pacote/lista");
	    }

		private void listaPorUsuario(HttpServletRequest request, HttpServletResponse response)
      			throws ServletException, IOException {
        	Login usuario = (Login) request.getSession().getAttribute("usuarioLogado");
        	List<Compra> listaCompra = compraDao.getPorCliente(usuario.getCliente().getId());   
        	request.setAttribute("listaCompra", listaCompra);
        	request.setAttribute("usuario", usuario);
        	RequestDispatcher dispatcher = request.getRequestDispatcher("/views/listaCompra.jsp");
        	dispatcher.forward(request, response);
    	}

		private void cancelarCompra(HttpServletRequest request, HttpServletResponse response)
				throws ServletException, IOException {
			Long id = Long.parseLong(request.getParameter("id"));
			Pacote pacote = compraDao.get(id).getPacote();
			// check "partida" date against system date
			LocalDate dataPartida = LocalDate.parse(pacote.getPartida());
			if (dataPartida.isBefore(LocalDate.now())) {
				Erro erro = new Erro("Não é possível cancelar um pacote que já ocorreu.");
				request.setAttribute("mensagens", erro);
				listaPorUsuario(request, response);
				return;
			}
			dataPartida = dataPartida.minusDays(5);
			if (dataPartida.isBefore(LocalDate.now())) {
				Erro erro = new Erro("Só é possível cancelar um pacote com no mínimo 5 dias de antecedência.");
				request.setAttribute("mensagens", erro);
				listaPorUsuario(request, response);
				return;
			}
			compraDao.updateAtivo(id, 0);
			listaPorUsuario(request, response);
		}
 	}