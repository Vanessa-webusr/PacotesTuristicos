package br.ufscar.dc.dsw.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.ufscar.dc.dsw.domain.Compra;
import br.ufscar.dc.dsw.domain.Imagem;
import br.ufscar.dc.dsw.domain.Pacote;


public class CompraDAO extends GenericDAO {

    public void insert(Compra compra) {

        String sql = "INSERT INTO Compra (pacote_id, pessoa_id, valor, ativo) VALUES (?, ?, ?, ?)";

        try {
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setLong(1, compra.getPacote().getId());
            statement.setLong(2, compra.getCliente());
            statement.setDouble(3, compra.getValor());
            statement.setInt(4, compra.getAtivo());

            statement.executeUpdate();

            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void delete(Compra compra) {
        String sql = "DELETE FROM Compra where id = ?";

        try {
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);

            statement.setLong(1, compra.getId());
            statement.executeUpdate();

            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Compra> getPorCliente(Long clienteId){
        List<Compra> listaCompra = new ArrayList<>();

        String sql = "SELECT * FROM Compra WHERE pessoa_id = ?";

        try{
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);

            statement.setLong(1, clienteId);
            ResultSet resultSet = statement.executeQuery();
            while(resultSet.next()){
                Long id = resultSet.getLong("id");
                Long pacoteId = resultSet.getLong("pacote_id");
                PacoteDAO pacoteDAO = new PacoteDAO();
                Pacote pacote = pacoteDAO.get(pacoteId);
                Float valor = resultSet.getFloat("valor");
                int ativo = resultSet.getInt("ativo");

                Compra compra = new Compra(id, pacote, clienteId, valor, ativo);
                listaCompra.add(compra);
            }
            resultSet.close();
            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return listaCompra;
    }

    public Compra get(Long id) {
    	String sql = "SELECT * FROM Compra WHERE id = ?";
    	Compra compra = null;
        try{
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);

            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            while(resultSet.next()){
                Long pacoteId = resultSet.getLong("pacote_id");
                PacoteDAO pacoteDAO = new PacoteDAO();
                Pacote pacote = pacoteDAO.get(pacoteId);
                Long pessoaId = resultSet.getLong("pessoa_id");
                Float valor = resultSet.getFloat("valor");
                int ativo = resultSet.getInt("ativo");

                compra = new Compra(id, pacote, pessoaId, valor, ativo);
            }    
            resultSet.close();
            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return compra;
    }

    public void updateAtivo(Long id, int ativo) {
        String sql = "UPDATE Compra SET ativo = ? WHERE id = ?";

        try {
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);

            statement.setInt(1, ativo);
            statement.setLong(2, id);
            statement.executeUpdate();

            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void deleteAllByPacote(Long pacoteId) {
        String sql = "DELETE FROM Compra WHERE pacote_id = ?";

        try {
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);

            statement.setLong(1, pacoteId);
            statement.executeUpdate();

            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void deleteAllByCliente(Long clienteId) {
        String sql = "DELETE FROM Compra WHERE pessoa_id = ?";

        try {
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);

            statement.setLong(1, clienteId);
            statement.executeUpdate();

            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
