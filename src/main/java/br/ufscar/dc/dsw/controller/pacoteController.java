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
 import br.ufscar.dc.dsw.util.Erro;

 import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
 import java.util.List;
 import java.util.Map;
 import javax.servlet.RequestDispatcher;
 import javax.servlet.ServletException;
 import javax.servlet.annotation.WebServlet;
 import javax.servlet.http.HttpServlet;
 import javax.servlet.http.HttpServletRequest;
 import javax.servlet.http.HttpServletResponse;

 @WebServlet(urlPatterns = { "/pacote/*" })
 public class pacoteController extends HttpServlet {

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

        try {
            switch(action){
                case "/cadastro":
                    autorizacao(request, response);
                    apresentaFormCadastro(request, response);
                    break;
                case "/edicao":
                    autorizacao(request, response);
                    apresentaFormEdicao(request, response);
                    break;
                case "/insere":
                    autorizacao(request, response);
                    insere(request, response);
                    break;
                case "/remove":
                    autorizacao(request, response);
                    remove(request, response);
                    break;
                case "/atualiza":
                    autorizacao(request, response);
                    atualiza(request, response);
                    break; 
                case "/lista":
                    lista(request, response);
                    break;
                default:
                    erro(request, response);
            }
        } catch (RuntimeException | IOException | ServletException e){
            throw new ServletException(e);
        }
    }

    private Map<Long, String> getAgencia() {
        Map <Long,String> agencias = new HashMap<>();
        for (Agencia agencia: new AgenciaDAO().getAll()) {
            agencias.put(agencia.getId(), agencia.getCnpj());
        }
        return agencias;
    }

    private void lista(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
        List<Pacote> listaPacote = pacoteDao.getAll();
        request.setAttribute("listaPacote", listaPacote);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/views/listaPacote.jsp");
        dispatcher.forward(request, response);
    }

    private Map<Long, String> getPacote() {
        Map <Long,String> pacotes = new HashMap<>();
        for (Pacote pacote: new PacoteDAO().getAll()) {
            pacotes.put(pacote.getId(), pacote.getCidade());
        }
        return pacotes;
    }

    private void apresentaFormCadastro(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setAttribute("agencias", getAgencia());       
        RequestDispatcher dispatcher = request.getRequestDispatcher("/views/formularioPacote.jsp");
        dispatcher.forward(request, response);
    }

    private void apresentaFormEdicao(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Long id = Long.parseLong(request.getParameter("id"));
        Pacote pacote = pacoteDao.get(id); 
        request.setAttribute("agencias", getAgencia()); 
        request.setAttribute("pacote", pacote);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/views/formularioPacote.jsp");
        dispatcher.forward(request, response);
    }

    private void insere(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        
        String cnpj = request.getParameter("cnpj");
        String cidade = request.getParameter("cidade");
        String estado = request.getParameter("estado");
        String pais = request.getParameter("pais");
        String partida = request.getParameter("partida");
        String duracao = request.getParameter("duracao");
        Float valor = Float.parseFloat(request.getParameter("valor"));
        String descricao = request.getParameter("descricao");
        String[] listaLink = request.getParameterValues("imagem[]");
        Imagem[] listaImagem = {new Imagem()};

        Long agencia_id = agenciaDao.getByCnpj(cnpj).getId();
        

        Pacote pacote = new Pacote(cnpj, agencia_id, cidade, estado, pais, partida, duracao, valor, listaImagem, descricao);
        pacoteDao.insert(pacote, agenciaDao.getByCnpj(cnpj).getId());

        Long id = pacoteDao.getMaxId();
        int length = listaLink.length;
        for (int i = 0; i < length; i++) {
            Imagem imagem = new Imagem(id, listaLink[i]);
            imagemDao.insert(imagem);
        }
        
        response.sendRedirect("lista");
    }

    private void atualiza(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");
        Long id = Long.parseLong(request.getParameter("id"));
        String cnpj = request.getParameter("cnpj");
        String cidade = request.getParameter("cidade");
        String estado = request.getParameter("estado");
        String pais = request.getParameter("pais");
        String partida = request.getParameter("partida");
        String duracao = request.getParameter("duracao");
        Float valor = Float.parseFloat(request.getParameter("valor"));
        String descricao = request.getParameter("descricao");

        String[] listaLink = request.getParameterValues("imagem[]");
        Imagem[] listaImagem = {new Imagem()};

        int i = 0;
        for(i = 0; i < 0; i++){
            listaImagem[i] = new Imagem(id, listaLink[i]);
            Long imagemId = imagemDao.idByPacoteLink(id, listaLink[i]);
            if(imagemId != -1){
                listaImagem[i].setId(imagemId);
                imagemDao.update(listaImagem[i]);
            } else {
                imagemDao.insert(listaImagem[i]);
            }
        }

        Long agencia_id = agenciaDao.getByCnpj(cnpj).getId();
 
        Pacote pacote = new Pacote(id, cnpj, agencia_id, cidade, estado, pais, partida, duracao, valor, listaImagem, descricao);
        pacoteDao.update(pacote);
        response.sendRedirect("lista");
    }

    private void remove(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        Long id = Long.parseLong(request.getParameter("id"));

        Pacote pacote= new Pacote(id);
        pacoteDao.delete(pacote);
        response.sendRedirect("lista");
    }

    private void erro(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("/views/authError.jsp");
        dispatcher.forward(request, response);
    }

    private void autorizacao(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
        Cliente usuario = (Cliente) request.getSession().getAttribute("usuarioLogado");
        if (usuario == null) {
            response.sendRedirect("../views/login.jsp");
            return;
        }
        if (usuario.getAdmin() == 0) {
            Erro erro = new Erro();
            erro.add("Você não tem permissão para acessar esta página.");
            request.setAttribute("mensagens", erro);
            RequestDispatcher rd = request.getRequestDispatcher("/views/error.jsp");
            rd.forward(request, response);
            return;
        }
    }
}