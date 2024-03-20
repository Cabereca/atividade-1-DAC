package com.projetoHoteis.dao;

import com.projetoHoteis.conexao.FabricaDeConexao;
import com.projetoHoteis.entities.Hospede;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class HospedeDao {
    private Connection connection;

    public HospedeDao() throws ClassNotFoundException {
        this.connection = new FabricaDeConexao().getConnection();
    }

    public void InsereHospede(Hospede hospede) {
        String sql = "insert into hospedes (nome, cpf, id) values (?,?,?)";
        try {
            // prepared statement para inserção
            PreparedStatement stmt = connection.prepareStatement(sql);
            // seta os valores
            stmt.setString(1, hospede.getNome());
            stmt.setString(2, hospede.getCpf());
            stmt.setLong(3, hospede.getId());
            stmt.execute();
            stmt.close();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }
    public Hospede EncontrarPorId(Integer id) {
        String sql = "select * from hospedes where id=?";
        Hospede hospede = null;
        try {
            PreparedStatement stmt = this.connection.prepareStatement(sql);
            stmt.setLong(1, id);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                // criando o objeto Contato
                hospede = new Hospede(
                        rs.getString("nome"),
                        rs.getString("cpf"),
                        rs.getInt("id")
                );
            }

        } catch (SQLException e) {
            System.out.println(e);
        }
        return hospede;
    }

    public void AtualizarHospede(Integer id, String nome, String cpf) {
        String sql = "update hospedes set nome=?, cpf=? where id=?";
        try {
            PreparedStatement stmt = this.connection.prepareStatement(sql);
            stmt.setString(1, nome);
            stmt.setString(2, cpf);
            stmt.setLong(3, id);
            stmt.execute();
            System.out.println("Hospede Atualizado");
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public void DeletarHospede(Integer id) {
        String sql = "delete from hospedes where id=?";
        try {
            PreparedStatement stmt = this.connection.prepareStatement(sql);
            stmt.setLong(1, id);
            stmt.execute();
            System.out.println("Hospede Deletado");
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public List<Hospede> ListarTodosHospedes(){
        List<Hospede> hospedes = new ArrayList<Hospede>();
        try {
            PreparedStatement stmt = this.connection.prepareStatement("select * from hospedes");
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                // criando o objeto Contato
                Hospede funcionario = new Hospede(
                        rs.getString("nome"),
                        rs.getString("cpf"),
                        rs.getInt("id")
                );

                hospedes.add(funcionario);
            }
            rs.close();
            stmt.close();

        } catch (SQLException e) {
            System.out.println(e);
        }
        return hospedes;
    }
}
