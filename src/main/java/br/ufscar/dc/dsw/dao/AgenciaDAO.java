package br.ufscar.dc.dsw.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import br.ufscar.dc.dsw.domain.Agencia;

public class AgenciaDAO extends GenericDAO {

    public void insert(Agencia agencia) {

        String sql = "INSERT INTO Agencia (cnpj, email, senha, nome, descricao) VALUES (?, ?, ?, ?, ?)";

        try {
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1, agencia.getCnpj());
            statement.setString(2, agencia.getEmail());
            statement.setString(3, agencia.getSenha());
            statement.setString(4, agencia.getNome());
            statement.setString(5, agencia.getDescricao());
            statement.executeUpdate();

            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Agencia> getAll() {

        List<Agencia> listaAgencia = new ArrayList<>();

        String sql = "SELECT * FROM Agencia";

        try {
            Connection conn = this.getConnection();
            Statement statement = conn.createStatement();

            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                Long id = resultSet.getLong("id");
                String cnpj = resultSet.getString("cnpj");
                String email = resultSet.getString("email");
                String senha = resultSet.getString("senha");
                String nome = resultSet.getString("nome");
                String descricao = resultSet.getString("descricao");

                Agencia agencia = new Agencia(id, email, senha, cnpj, nome, descricao);
                listaAgencia.add(agencia);
            }

            resultSet.close();
            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return listaAgencia;
    }

    public void delete(Agencia agencia) {
        String sql = "DELETE FROM Agencia where id = ?";

        try {
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);

            statement.setLong(1, agencia.getId());
            statement.executeUpdate();

            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void update(Agencia agencia) {
        String sql = "UPDATE Agencia SET cnpj = ?, email = ?, senha = ?, nome = ?, descricao = ? WHERE id = ?";

        try {
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);

            statement.setString(1, agencia.getCnpj());
            statement.setString(2, agencia.getEmail());
            statement.setString(3, agencia.getSenha());
            statement.setString(4, agencia.getNome());
            statement.setString(5, agencia.getDescricao());
            
            statement.executeUpdate();

            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Agencia get(Long id) {
        Agencia agencia = null;

        String sql = "SELECT * from Agencia where id = ? ";

        try {
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);

            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                String cnpj = resultSet.getString("cnpj");
                String email = resultSet.getString("email");
                String senha = resultSet.getString("senha");
                String nome = resultSet.getString("nome");
                String descricao = resultSet.getString("descricao");

                agencia = new Agencia(id, email, senha, cnpj, nome, descricao);
            }

            resultSet.close();
            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return agencia;
    }
}
