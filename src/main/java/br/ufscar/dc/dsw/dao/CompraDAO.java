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

        String sql = "INSERT INTO Compra (pacote_id, pessoa_id, valor) VALUES (?, ?, ?)";

        try {
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setLong(1, compra.getPacote());
            statement.setLong(2, compra.getCliente());
            statement.setDouble(3, compra.getValor());

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
                Double valor = resultSet.getDouble("valor");

                Compra compra = new Compra(id, pacoteId, clienteId, valor);
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


}
