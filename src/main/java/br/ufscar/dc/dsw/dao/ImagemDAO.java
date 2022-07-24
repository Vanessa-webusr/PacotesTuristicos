package br.ufscar.dc.dsw.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import br.ufscar.dc.dsw.domain.Imagem;
import br.ufscar.dc.dsw.domain.Pacote;

public class ImagemDAO extends GenericDAO{

    public void insert(Imagem imagem) {
        String sql = "INSERT INTO Pacote (pacote_id, imagem)";
        sql += "VALUES (?, ?)";

        try {
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setLong(1, imagem.getPacoteId());
            statement.setString(2, imagem.getLink());
            statement.executeUpdate();

            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

        public Imagem[] getPorPacote(Long pacote_id){

        String sql = "SELECT * FROM Foto WHERE pacote_id = ?";
        Imagem[] listaImagem = {new Imagem()};
        try{
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);

            statement.setLong(1, pacote_id);
            ResultSet resultSet = statement.executeQuery();
            int i = 0;
            while(resultSet.next()){
                Long id = resultSet.getLong("id");
                String link = resultSet.getString("link");

                Imagem imagem = new Imagem(id, pacote_id, link);
                listaImagem[i] = imagem;
                i += 1;
            }
            resultSet.close();
            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return listaImagem;
    }

    public void delete(Imagem imagem) {
        String sql = "DELETE FROM Foto where id = ?";

        try {
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);

            statement.setLong(1, imagem.getId());
            statement.executeUpdate();

            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void update(Imagem imagem) {
        String sql = "UPDATE Pacote SET pacote_id = ?, imagem = ?";
        sql += "WHERE id = ?";

        try {
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);

            statement.setLong(1, imagem.getPacoteId());
            statement.setString(2, imagem.getLink()); 
            statement.executeUpdate();

            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Imagem get(Long id) {

        String sql = "SELECT * from Foto where id = ? ";

        Imagem imagem = null;
        try {
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);

            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                Long pacote_id = resultSet.getLong("pacote_id");
                String link = resultSet.getString("imagem");

                imagem = new Imagem(id, pacote_id, link);
            }

            resultSet.close();
            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return imagem;
    }
}
