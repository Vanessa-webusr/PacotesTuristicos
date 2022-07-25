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

 @WebServlet(urlPatterns = { "/api/*" })
 public class apiController extends HttpServlet {

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
                case "/cadastroAgencia":
                    apresentaFormCadastroAgencia(request, response);
                    break;
                case "/edicaoAgencia":
                    apresentaFormEdicaoAgencia(request, response);
                    break;
                case "/insereAgencia":
                    insereAgencia(request, response);
                    break;
                case "/removeAgencia":
                    removeAgencia(request, response);
                    break;
                case "/atualizaAgencia":
                    atualizaAgencia(request, response);
                    break;
                case "/listaAgencia":
                    listaAgencia(request, response);
                    break;
                case "/cadastroCliente":
                    apresentaFormCadastroCliente(request, response);
                    break;
                case "/edicaoCliente":
                    apresentaFormEdicaoCliente(request, response);
                    break;
                case "/insereCliente":
                    insereCliente(request, response);
                    break;
                case "/removeCliente":
                    removeCliente(request, response);
                    break;
                case "/atualizaCliente":
                    atualizaCliente(request, response);
                    break;
                case "/listaCliente":
                    listaCliente(request, response);
                    break;
                case "/cadastroPacote":
                    apresentaFormCadastroPacote(request, response);
                    break;
                case "/edicaoPacote":
                    apresentaFormEdicaoPacote(request, response);
                    break;
                case "/inserePacote":
                    inserePacote(request, response);
                    break;
                case "/removePacote":
                    removePacote(request, response);
                    break;
                case "/atualizaPacote":
                    atualizaPacote(request, response);
                    break; 
                case "/listaPacote":
                    listaPacote(request, response);
                    break;
                default:
                    listaPacote(request, response);
            }
        } catch (RuntimeException | IOException | ServletException e){
            throw new ServletException(e);
        }
    }

    private void listaAgencia(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
        List<Agencia> listaAgencia = agenciaDao.getAll();
        request.setAttribute("listaAgencia", listaAgencia);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/views/listaAgencia.jsp");
        dispatcher.forward(request, response);
    }

    private Map<Long, String> getAgencia() {
        Map <Long,String> agencias = new HashMap<>();
        for (Agencia agencia: new AgenciaDAO().getAll()) {
            agencias.put(agencia.getId(), agencia.getCnpj());
        }
        return agencias;
    }

    private void apresentaFormCadastroAgencia(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setAttribute("agencias", getAgencia());
        RequestDispatcher dispatcher = request.getRequestDispatcher("/views/formularioAgencia.jsp");
        dispatcher.forward(request, response);
    }

    private void apresentaFormEdicaoAgencia(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Long id = Long.parseLong(request.getParameter("id"));
        Agencia agencia = agenciaDao.get(id);
        request.setAttribute("agencia", agencia);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/views/formularioAgencia.jsp");
        dispatcher.forward(request, response);
    }

    private void insereAgencia(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        
        String email = request.getParameter("email");
        String senha = request.getParameter("senha");
        String cnpj = request.getParameter("cnpj");
        String nome = request.getParameter("nome");
        String descricao = request.getParameter("descricao");
 
        Agencia agencia = new Agencia(email, senha, cnpj, nome, descricao);
        agenciaDao.insert(agencia);
        response.sendRedirect("listaAgencia");
    }

    private void atualizaAgencia(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");
        Long id = Long.parseLong(request.getParameter("id"));
        String email = request.getParameter("email");
        String senha = request.getParameter("senha");
        String cnpj = request.getParameter("cnpj");
        String nome = request.getParameter("nome");
        String descricao = request.getParameter("descricao");
        
        Agencia agencia = new Agencia(id, email, senha, cnpj, nome, descricao);
        agenciaDao.update(agencia);
        response.sendRedirect("listaAgencia");
    }

    private void removeAgencia(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        Long id = Long.parseLong(request.getParameter("id"));

        Agencia agencia = new Agencia(id);
        agenciaDao.delete(agencia);
        response.sendRedirect("listaAgencia");
    }

    private void listaCliente(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
        List<Cliente> listaCliente = clienteDao.getAll();
        request.setAttribute("listaCliente", listaCliente);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/views/listaCliente.jsp");
        dispatcher.forward(request, response);
    }

    private Map<Long, String> getCliente() {
        Map <Long,String> clientes = new HashMap<>();
        for (Cliente cliente: new ClienteDAO().getAll()) {
            clientes.put(cliente.getId(), cliente.getNome());
        }
        return clientes;
    }

    private void apresentaFormCadastroCliente(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setAttribute("clientes", getCliente());
        RequestDispatcher dispatcher = request.getRequestDispatcher("/views/formularioCliente.jsp");
        dispatcher.forward(request, response);
    }

    private void apresentaFormEdicaoCliente(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Long id = Long.parseLong(request.getParameter("id"));
        Cliente cliente = clienteDao.get(id);
        request.setAttribute("cliente", cliente);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/views/formularioCliente.jsp");
        dispatcher.forward(request, response);
    }

    private void insereCliente(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        
        String email = request.getParameter("email");
        String nome = request.getParameter("nome");
        String senha = request.getParameter("senha");
        String cpf = request.getParameter("cpf");
        String telefone = request.getParameter("telefone");
        String sexo = request.getParameter("sexo");
        String nascimento = request.getParameter("nascimento");
        int admin = Integer.parseInt(request.getParameter("admin"));
 
        Cliente cliente = new Cliente(email, nome, senha, cpf, telefone, sexo, nascimento, admin);
        clienteDao.insert(cliente);
        response.sendRedirect("listaCliente");
    }

    private void atualizaCliente(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");
        Long id = Long.parseLong(request.getParameter("id"));
        String email = request.getParameter("email");
        String nome = request.getParameter("nome");
        String senha = request.getParameter("senha");
        String cpf = request.getParameter("cpf");
        String telefone = request.getParameter("telefone");
        String sexo = request.getParameter("sexo");
        String nascimento = request.getParameter("nascimento");
        int admin = Integer.parseInt(request.getParameter("admin"));
 
        Cliente cliente = new Cliente(id, email, nome, senha, cpf, telefone, sexo, nascimento, admin);
        clienteDao.update(cliente);
        response.sendRedirect("listaCliente");
    }

    private void removeCliente(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        Long id = Long.parseLong(request.getParameter("id"));

        Cliente cliente= new Cliente(id);
        clienteDao.delete(cliente);
        response.sendRedirect("listaCliente");
    }

    private void listaPacote(HttpServletRequest request, HttpServletResponse response)
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

    private void apresentaFormCadastroPacote(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setAttribute("agencias", getAgencia());       
        RequestDispatcher dispatcher = request.getRequestDispatcher("/views/formularioPacote.jsp");
        dispatcher.forward(request, response);
    }

    private void apresentaFormEdicaoPacote(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Long id = Long.parseLong(request.getParameter("id"));
        Pacote pacote = pacoteDao.get(id); 
        request.setAttribute("agencias", getAgencia()); 
        request.setAttribute("pacote", pacote);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/views/formularioPacote.jsp");
        dispatcher.forward(request, response);
    }

    private void inserePacote(HttpServletRequest request, HttpServletResponse response)
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
        String[] listaLink = request.getParameterValues("imagem");
        Imagem[] listaImagem = {new Imagem()};

        Long agencia_id = agenciaDao.getByCnpj(cnpj).getId();
        

        Pacote pacote = new Pacote(cnpj, agencia_id, cidade, estado, pais, partida, duracao, valor, listaImagem, descricao);
        pacoteDao.insert(pacote, agenciaDao.getByCnpj(cnpj).getId());

        Long id = pacoteDao.getMaxId();
        for (String link: listaLink) {
            Imagem imagem = new Imagem(id, link);
            imagemDao.insert(imagem);
        }
        
        response.sendRedirect("listaPacote");
    }

    private void atualizaPacote(HttpServletRequest request, HttpServletResponse response)
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

        String[] listaLink = request.getParameterValues("imagem");
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
        response.sendRedirect("listaPacote");
    }

    private void removePacote(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        Long id = Long.parseLong(request.getParameter("id"));

        Pacote pacote= new Pacote(id);
        pacoteDao.delete(pacote);
        response.sendRedirect("listaPacote");
    }
}