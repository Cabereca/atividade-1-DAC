package com.projetoHoteis.dao;

import com.projetoHoteis.conexao.FabricaDeConexao;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class DBDao {
    private Connection connection;

    public DBDao() throws ClassNotFoundException {
        this.connection = new FabricaDeConexao().getConnection();
    }

    public void criarTabelaHospedes() {
        String sql = "CREATE TABLE IF NOT EXISTS hospedes(\n" +
                "        nome varchar,\n" +
                "        cpf varchar,\n" +
                "        id int primary key\n" +
                ");";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.execute();
            stmt.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void criarTabelaReservas() {
        String sql = "CREATE TABLE IF NOT EXISTS reserva(\n" +
                "        id int PRIMARY KEY,\n" +
                "        idReserva int,\n" +
                "        foreign key (idReserva) references hospedes(id)\n" +
                "        );";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.execute();
            stmt.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}

