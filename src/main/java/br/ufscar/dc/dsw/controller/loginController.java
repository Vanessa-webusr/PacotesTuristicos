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
            Cliente cliente = clienteDao.getByEmail(email);
            if(cliente == null){
                erro.add("Email não cadastrado");
            }else{
                if(cliente.getSenha().equals(senha)){
                    request.getSession().setAttribute("usuarioLogado", cliente);
                    response.sendRedirect("pacote/lista");
                    return;
                } else {
                    erro.add("Senha incorreta");
                }
                
            }
        }
        request.getSession().invalidate();
        request.setAttribute("mensagens", erro);
        RequestDispatcher dispatcher = request.getRequestDispatcher("views/login.jsp");
        dispatcher.forward(request, response);
    }
 }
