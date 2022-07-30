package br.ufscar.dc.dsw.controller;

 import br.ufscar.dc.dsw.dao.AgenciaDAO;
 import br.ufscar.dc.dsw.dao.ClienteDAO;
 import br.ufscar.dc.dsw.dao.CompraDAO;
 import br.ufscar.dc.dsw.dao.PacoteDAO;
 import br.ufscar.dc.dsw.dao.ImagemDAO;
 import br.ufscar.dc.dsw.domain.Agencia;
 import br.ufscar.dc.dsw.domain.Cliente;
 import br.ufscar.dc.dsw.domain.Compra;
 import br.ufscar.dc.dsw.domain.Login;
 import br.ufscar.dc.dsw.domain.Pacote;
 import br.ufscar.dc.dsw.domain.Imagem;
 import br.ufscar.dc.dsw.domain.Login;
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

        Login usuario = (Login) request.getSession().getAttribute("usuarioLogado");
        request.setAttribute("usuario", usuario);

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
                case "/listaPorAgencia":
                    listaPorAgencia(request, response);
                    break;
                default:
                    Erro erro = new Erro();
                    erro.add("Erro 404:");
                    erro.add("Página não encontrada.");
                    RequestDispatcher dispatcher = request.getRequestDispatcher("/views/error.jsp");
                    dispatcher.forward(request, response);
            }
        } catch (RuntimeException | IOException | ServletException e){
            Erro erro = new Erro();
            erro.add("Erro ao processar a requisição.");
            erro.add(e.getMessage());
            request.setAttribute("mensagens", erro);
            RequestDispatcher dispatcher = request.getRequestDispatcher("/views/error.jsp");
            dispatcher.forward(request, response);
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
        List<Agencia> listaAgencia = agenciaDao.getAll();
        request.setAttribute("listaPacote", listaPacote);
        request.setAttribute("listaAgencia", listaAgencia);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/views/listaPacote.jsp");
        dispatcher.forward(request, response);
    }

    private void listaPorAgencia(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
        Login usuario = (Login) request.getSession().getAttribute("usuarioLogado");
        List<Pacote> listaPacote = pacoteDao.getPorAgencia(usuario.getAgencia().getId());   
        request.setAttribute("listaPacote", listaPacote);
        request.setAttribute("filtrado", true);
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
        List <String> linkImagens = imagemDao.getLinkImagens(id);
        request.setAttribute("agencias", getAgencia()); 
        request.setAttribute("pacote", pacote);
        request.setAttribute("imagens", linkImagens);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/views/formularioPacote.jsp");
        dispatcher.forward(request, response);
    }

    private void insere(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        
        Long agencia_id = Long.parseLong(request.getParameter("agencia_id"));
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

        

        Pacote pacote = new Pacote(cnpj, agencia_id, cidade, estado, pais, partida, duracao, valor, listaImagem, descricao);
        pacoteDao.insert(pacote, agenciaDao.getByCnpj(cnpj).getId());

        Long id = pacoteDao.getMaxId();
        int length = listaLink.length;
        for (int i = 0; i < length; i++) {
            if(listaLink[i] != "" && listaLink[i] != null && !listaLink[i].isEmpty()){
                Imagem imagem = new Imagem(id, listaLink[i]);
                imagemDao.insert(imagem);
            }
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

        imagemDao.deleteAll(id);
        int length = listaLink.length;
        for (int i = 0; i < length; i++) {
            if(listaLink[i] != "" && listaLink[i] != null && !listaLink[i].isEmpty()){
                Imagem imagem = new Imagem(id, listaLink[i]);
                imagemDao.insert(imagem);
            }
        }

        Long agencia_id = agenciaDao.getByCnpj(cnpj).getId();
 
        Pacote pacote = new Pacote(id, cnpj, agencia_id, cidade, estado, pais, partida, duracao, valor, listaImagem, descricao);
        pacoteDao.update(pacote);
        listaPorAgencia(request, response);
    }

    private void remove(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        Long id = Long.parseLong(request.getParameter("id"));

        Pacote pacote= new Pacote(id);
        pacoteDao.delete(pacote);
        listaPorAgencia(request, response);
    }

    private void autorizacao(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
        Login usuario = (Login) request.getSession().getAttribute("usuarioLogado");
        if (usuario == null) {
            response.sendRedirect("../views/login.jsp");
            return;
        }
        if (usuario.getAgencia() == null) {
            Erro erro = new Erro();
            erro.add("Você não tem permissão para acessar esta página.");
            request.setAttribute("mensagens", erro);
            request.setAttribute("usuario", usuario);
            RequestDispatcher rd = request.getRequestDispatcher("/views/error.jsp");
            rd.forward(request, response);
            return;
        }
    }
    
}