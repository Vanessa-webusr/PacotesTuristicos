package br.ufscar.dc.dsw.controller;

 import br.ufscar.dc.dsw.dao.AgenciaDAO;
import br.ufscar.dc.dsw.dao.ImagemDAO;
import br.ufscar.dc.dsw.dao.PacoteDAO;
import br.ufscar.dc.dsw.domain.Agencia;
 import br.ufscar.dc.dsw.domain.Login;
import br.ufscar.dc.dsw.domain.Pacote;
 import br.ufscar.dc.dsw.util.Erro;

 import java.io.IOException;
import java.util.HashMap;
 import java.util.List;
 import java.util.Map;
 import javax.servlet.RequestDispatcher;
 import javax.servlet.ServletException;
 import javax.servlet.annotation.WebServlet;
 import javax.servlet.http.HttpServlet;
 import javax.servlet.http.HttpServletRequest;
 import javax.servlet.http.HttpServletResponse;

 @WebServlet(urlPatterns = { "/agencia/*" })
 public class agenciaController extends HttpServlet {

    private static final long serialVersionUID = 1L;

    private AgenciaDAO agenciaDao;

    @Override
    public void init() {
        agenciaDao = new AgenciaDAO();
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

        if (usuario.getAgencia() != null || usuario.getCliente().getAdmin() == 0) {
            Erro erro = new Erro();
            erro.add("Você não tem permissão para acessar esta página.");
            request.setAttribute("mensagens", erro);
            request.setAttribute("usuario", usuario);
            request.setAttribute("linkVoltar", "../pacote/lista");
            RequestDispatcher rd = request.getRequestDispatcher("/views/error.jsp");
            rd.forward(request, response);
            return;
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
                    Erro erro = new Erro();
                    erro.add("Erro 404:");
                    erro.add("Página não encontrada.");
                    request.setAttribute("mensagens", erro);
                    request.setAttribute("linkVoltar", "../pacote/lista");
                    RequestDispatcher dispatcher = request.getRequestDispatcher("/views/error.jsp");
                    dispatcher.forward(request, response);
            }
        } catch (RuntimeException | IOException | ServletException e){
            Erro erro = new Erro();
            erro.add("Erro ao processar a requisição.");
            erro.add(e.getMessage());
            request.setAttribute("mensagens", erro);
            request.setAttribute("linkVoltar", "../agencia/lista");
            RequestDispatcher dispatcher = request.getRequestDispatcher("/views/error.jsp");
            dispatcher.forward(request, response);
            throw new ServletException(e);
        }
    }

    private void lista(HttpServletRequest request, HttpServletResponse response)
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

    private void apresentaFormCadastro(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setAttribute("agencias", getAgencia());
        RequestDispatcher dispatcher = request.getRequestDispatcher("/views/formularioAgencia.jsp");
        dispatcher.forward(request, response);
    }

    private void apresentaFormEdicao(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Long id = Long.parseLong(request.getParameter("id"));
        Agencia agencia = agenciaDao.get(id);
        request.setAttribute("agencia", agencia);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/views/formularioAgencia.jsp");
        dispatcher.forward(request, response);
    }

    private void insere(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        
        String email = request.getParameter("email");
        String senha = request.getParameter("senha");
        String cnpj = request.getParameter("cnpj");
        String nome = request.getParameter("nome");
        String descricao = request.getParameter("descricao");

        if(agenciaDao.verificaEmailDuplicado(email)){
            Erro erro = new Erro();
            erro.add("Erro ao adicionar agência:");
            erro.add("Email já cadastrado.");
            request.setAttribute("mensagens", erro);
            request.setAttribute("linkVoltar", "../agencia/cadastro");
            RequestDispatcher dispatcher = request.getRequestDispatcher("/views/error.jsp");
            dispatcher.forward(request, response);
            return;
        }

        if(agenciaDao.verificaCnpjDuplicado(cnpj)){
            Erro erro = new Erro();
            erro.add("Erro ao adicionar agência:");
            erro.add("CNPJ já cadastrado.");
            request.setAttribute("mensagens", erro);
            request.setAttribute("linkVoltar", "../agencia/cadastro");
            RequestDispatcher dispatcher = request.getRequestDispatcher("/views/error.jsp");
            dispatcher.forward(request, response);
            return;
        }
 
        Agencia agencia = new Agencia(email, senha, cnpj, nome, descricao);
        agenciaDao.insert(agencia);
        response.sendRedirect("lista");
    }

    private void atualiza(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");
        Long id = Long.parseLong(request.getParameter("id"));
        String email = request.getParameter("email");
        String senha = request.getParameter("senha");
        String cnpj = request.getParameter("cnpj");
        String nome = request.getParameter("nome");
        String descricao = request.getParameter("descricao");

        Agencia agenciaEmail = agenciaDao.getByEmail(email);
        Agencia agenciaCnpj = agenciaDao.getByCnpj(cnpj);
        if(agenciaEmail != null && !agenciaEmail.getId().equals(id)){
            Erro erro = new Erro();
            erro.add("Erro ao atualizar agência:");
            erro.add("Email já cadastrado.");
            request.setAttribute("mensagens", erro);
            request.setAttribute("linkVoltar", "../agencia/edicao?id=" + id);
            RequestDispatcher dispatcher = request.getRequestDispatcher("/views/error.jsp");
            dispatcher.forward(request, response);
            return;
        }

        if(agenciaCnpj != null && !agenciaCnpj.getId().equals(id)){
            Erro erro = new Erro();
            erro.add("Erro ao atualizar agência:");
            erro.add("CNPJ já cadastrado.");
            request.setAttribute("mensagens", erro);
            request.setAttribute("linkVoltar", "../agencia/edicao?id=" + id);
            RequestDispatcher dispatcher = request.getRequestDispatcher("/views/error.jsp");
            dispatcher.forward(request, response);
            return;
        }
        
        Agencia agencia = new Agencia(id, email, senha, cnpj, nome, descricao);
        agenciaDao.update(agencia);
        response.sendRedirect("lista");
    }

    private void remove(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        Long id = Long.parseLong(request.getParameter("id"));

        Agencia agencia = new Agencia(id);
        PacoteDAO pacoteDao = new PacoteDAO();
        ImagemDAO imagemDao = new ImagemDAO();
        List<Pacote> pacotes = pacoteDao.getPorAgencia(agencia.getId());
        while(pacotes.size() > 0){
            Pacote pacote = pacotes.remove(0);
            imagemDao.deleteAll(pacote.getId());
            pacoteDao.delete(pacote);
            pacotes = pacoteDao.getPorAgencia(agencia.getId());
        }
        agenciaDao.delete(agencia);
        response.sendRedirect("lista");
    }
}