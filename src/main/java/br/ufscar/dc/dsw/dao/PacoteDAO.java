package br.ufscar.dc.dsw.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import br.ufscar.dc.dsw.domain.Pacote;

public class PacoteDAO extends GenericDAO{

    public void insert(Pacote pacote, Long agencia_id) {
        String sql = "INSERT INTO Pacote (cnpj, agencia_id, cidade, estado, pais, data_partida, duracao_dias, valor, descricao)";
        sql += "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try {
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1, pacote.getCnpj());
            statement.setLong(2, agencia_id);
            statement.setString(3, pacote.getCidade());
            statement.setString(4, pacote.getEstado());
            statement.setString(5, pacote.getPais());
            statement.setString(6, pacote.getPartida());
            statement.setString(7, pacote.getDuracao());
            statement.setFloat(8, pacote.getValor());
            statement.setString(9, pacote.getDescricao());
            statement.executeUpdate();

            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Pacote> getAll() {
        
        List<Pacote> listaPacote = new ArrayList<>();

        String sql = "SELECT * FROM Pacote";

        try {
            Connection conn = this.getConnection();
            Statement statement = conn.createStatement();

            ResultSet resultSet = statement.executeQuery(sql);
            while(resultSet.next()) {
                Long id = resultSet.getLong("id");
                String cnpj = resultSet.getString("cnpj");
                Long agencia_id = resultSet.getLong("agencia_id");
                String cidade = resultSet.getString("cidade");
                String estado = resultSet.getString("estado");
                String pais = resultSet.getString("pais");
                String partida = resultSet.getString("data_partida");
                String duracao = resultSet.getString("duracao_dias");
                Float valor = resultSet.getFloat("valor");
                String descricao = resultSet.getString("descricao");
                
                ArrayList<String> listaFoto = new ArrayList<>();

                listaFoto = getFoto(id);
                Pacote pacote = new Pacote(id, cnpj, cidade, estado, pais, partida, duracao, valor, listaFoto, descricao);
                listaPacote.add(pacote);
            }
            resultSet.close();
            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return listaPacote;
    }

    public ArrayList<String> getFoto(Long pacoteId) {
        ArrayList<String> listaFoto = new ArrayList<>();

        String sql = "SELECT imagem FROM Foto WHERE pacote_id = ?";

        try {
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);

            statement.setLong(1, pacoteId);
            ResultSet resultSet = statement.executeQuery();
            while(resultSet.next()){
                String imagem = resultSet.getString("imagem");
                listaFoto.add(imagem);
            }

            resultSet.close();
            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return listaFoto;
    }

    public List<Pacote> getPorAgencia(Long agenciaId){
        List<Pacote> listaPacote = new ArrayList<>();

        String sql = "SELECT * FROM Pacote WHERE agencia_id = ?";

        try{
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);

            statement.setLong(1, agenciaId);
            ResultSet resultSet = statement.executeQuery();
            while(resultSet.next()){
                Long id = resultSet.getLong("id");
                String cnpj = resultSet.getString("cnpj");
                Long agencia_id = resultSet.getLong("agencia_id");
                String cidade = resultSet.getString("cidade");
                String estado = resultSet.getString("estado");
                String pais = resultSet.getString("pais");
                String partida = resultSet.getString("data_partida");
                String duracao = resultSet.getString("duracao_dias");
                Float valor = resultSet.getFloat("valor");
                String descricao = resultSet.getString("descricao");
                
                ArrayList<String> listaFoto = new ArrayList<>();

                listaFoto = getFoto(id);
                Pacote pacote = new Pacote(id, cnpj, cidade, estado, pais, partida, duracao, valor, listaFoto, descricao);
                listaPacote.add(pacote);
            }
            resultSet.close();
            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return listaPacote;
    }

    public void delete(Pacote pacote) {
        String sql = "DELETE FROM Pacote where id = ?";

        try {
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);

            statement.setLong(1, pacote.getId());
            statement.executeUpdate();

            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void update(Pacote pacote) {
        String sql = "UPDATE Pacote SET cnpj = ?, agencia_id = ?, cidade = ?, estado= ?, pais = ?, data_partida = ?, duracao_dias = ?, valor = ?, descricao = ?";
        sql += "WHERE id = ?";

        try {
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);

            statement.setString(1, pacote.getCnpj());
            statement.setLong(2, 0); //EDITAR ***************************************************************************************************
            statement.setString(3, pacote.getCidade());
            statement.setString(4, pacote.getEstado());
            statement.setString(5, pacote.getPais());
            statement.setString(6, pacote.getPartida());
            statement.setString(7, pacote.getDuracao());
            statement.setDouble(8, pacote.getValor());
            statement.setString(9, pacote.getDescricao());
            statement.executeUpdate();

            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Pacote get(Long id) {
        Pacote pacote = null;

        String sql = "SELECT * from Pacote where id = ? ";

        try {
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);

            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                String cnpj = resultSet.getString("cnpj");
                Long agencia_id = resultSet.getLong("agencia_id");
                String cidade = resultSet.getString("cidade");
                String estado = resultSet.getString("estado");
                String pais = resultSet.getString("pais");
                String partida = resultSet.getString("data_partida");
                String duracao = resultSet.getString("duracao_dias");
                Float valor = resultSet.getFloat("valor");
                String descricao = resultSet.getString("descricao");

                ArrayList<String> listaFoto = new ArrayList<>();

                listaFoto = getFoto(id);

                pacote = new Pacote(id, cnpj, cidade, estado, pais, partida, duracao, valor, listaFoto, descricao);
            }

            resultSet.close();
            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return pacote;
    }
}
