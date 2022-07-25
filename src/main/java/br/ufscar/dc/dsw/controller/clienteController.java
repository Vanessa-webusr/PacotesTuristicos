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

 @WebServlet(urlPatterns = { "/cliente/*" })
 public class clienteController extends HttpServlet {

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
                    apresentaFormCadastro(request, response);
                    break;
                case "/edicao":
                    apresentaFormEdicao(request, response);
                    break;
                case "/insere":
                    insere(request, response);
                    break;
                case "/remove":
                    remove(request, response);
                    break;
                case "/atualiza":
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

    private void lista(HttpServletRequest request, HttpServletResponse response)
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

    private void apresentaFormCadastro(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setAttribute("clientes", getCliente());
        RequestDispatcher dispatcher = request.getRequestDispatcher("/views/formularioCliente.jsp");
        dispatcher.forward(request, response);
    }

    private void apresentaFormEdicao(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Long id = Long.parseLong(request.getParameter("id"));
        Cliente cliente = clienteDao.get(id);
        request.setAttribute("cliente", cliente);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/views/formularioCliente.jsp");
        dispatcher.forward(request, response);
    }

    private void insere(HttpServletRequest request, HttpServletResponse response)
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

    private void atualiza(HttpServletRequest request, HttpServletResponse response)
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

    private void remove(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        Long id = Long.parseLong(request.getParameter("id"));

        Cliente cliente= new Cliente(id);
        clienteDao.delete(cliente);
        response.sendRedirect("listaCliente");
    }

    private void erro(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("/views/authError.jsp");
        dispatcher.forward(request, response);
    }

}