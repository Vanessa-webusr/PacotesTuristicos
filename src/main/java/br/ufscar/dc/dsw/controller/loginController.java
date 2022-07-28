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

 @WebServlet(urlPatterns = { "/auth/*" })
 public class loginController extends HttpServlet {

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
                case "/login":
                    login(request, response);
                    break;
                case "/logout":
                    logout(request, response);
                    break;
                default:
                    erro(request, response);
            }
        } catch (RuntimeException | IOException | ServletException e){
            throw new ServletException(e);
        }  
    }

    private void login(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Erro erro = new Erro();
        String email = request.getParameter("email");
        String senha = request.getParameter("senha");
        if(email == null || email.isEmpty()){
            erro.add("Email não pode ser vazio");
        }
        if(senha == null || senha.isEmpty()){
            erro.add("Senha não pode ser vazia");
        }
        if(!erro.isExisteErros()){
            Login login = null;
            if(request.getParameter("tipo").equals("Cliente")){
                Cliente usuario = clienteDao.getByEmail(email);
                if(usuario != null && usuario.getSenha().equals(senha)){
                    login = new Login(null, usuario, "Cliente");
                } else {
                    erro.add("Email ou senha inválidos");
                }
            } else {
                Agencia usuario = agenciaDao.getByEmail(email);
                if(usuario != null && usuario.getSenha().equals(senha)){
                    login = new Login(usuario, null, "Agencia");
                } else {
                    erro.add("Email ou senha inválidos");
                }
            }
            if(!erro.isExisteErros()){
                request.getSession().setAttribute("usuarioLogado", login);
                response.sendRedirect("../pacote/lista");
                return;
                
            }
        }
        request.getSession().invalidate();
        request.setAttribute("mensagens", erro);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/views/login.jsp");
        dispatcher.forward(request, response);
    }

    private void logout(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getSession().invalidate();
        response.sendRedirect("../pacote/lista");
    }

    private void erro(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
      RequestDispatcher dispatcher = request.getRequestDispatcher("/views/authError.jsp");
      dispatcher.forward(request, response);
    }
 }