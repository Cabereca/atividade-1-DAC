package com.projetoHoteis.dao;

import com.projetoHoteis.conexao.FabricaDeConexao;
import com.projetoHoteis.entities.Reserva;

import java.sql.*;
import java.time.Instant;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ReservaDao {
    private Connection connection;

    public ReservaDao () throws ClassNotFoundException {
        this.connection = new FabricaDeConexao().getConnection();
    }

    public void criarReserva(Reserva reserva) {
        String sql = "insert into reservas (id, idHospede, numeroQuarto, dataEntrada, dataSaida) values(?,?,?,?,?)";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, reserva.getId());
            stmt.setInt(2, reserva.getIdHospede());
            stmt.setInt(3, reserva.getNumeroQuarto());
            stmt.setDate(4, Date.valueOf(reserva.getdataEntrada()));
            stmt.setDate(5, Date.valueOf(reserva.getdataSaida()));
            stmt.execute();
            stmt.close();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public List<Reserva> ListarReservas() {
        List<Reserva> reservas = new ArrayList<Reserva>();
        try {
            PreparedStatement stmt = this.connection.prepareStatement("select * from reservas");
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Reserva reserva = new Reserva(
                  rs.getInt("id"),
                  rs.getInt("idhospede"),
                  rs.getInt("numeroquarto"),
                        rs.getDate("dataentrada").toLocalDate(),
                        rs.getDate("datasaida").toLocalDate()
                );
                reservas.add(reserva);
            }
            stmt.close();

        } catch (SQLException e) {
            System.out.println(e);
        }

        return reservas;
    }

    public Reserva BuscarById(Integer id) {
        Reserva reserva = null;
        String sql = "select * from reservas where id=?";

        try {
            PreparedStatement stmt = this.connection.prepareStatement(sql);
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            stmt.close();
            while(rs.next()) {
                reserva = new Reserva(
                        rs.getInt("id"),
                        rs.getInt("idhospede"),
                        rs.getInt("numeroquarto"),
                        rs.getDate("dataentrada").toLocalDate(),
                        rs.getDate("datasaida").toLocalDate()
                );
            }

        } catch (SQLException e) {
            System.out.println(e);
        }

        return reserva;
    }

    public void AtualizarReserva(Integer id, Integer numeroQuarto, LocalDate dataEntrada, LocalDate dataSaida) {
        String sql = "update reservas set numeroquarto=?, dataentrada=?, datasaida=? where id=?";
        try{
            PreparedStatement stmt = this.connection.prepareStatement(sql);
            stmt.setInt(1, numeroQuarto);
            stmt.setDate(2, Date.valueOf(dataEntrada));
            stmt.setDate(3, Date.valueOf(dataSaida));
            stmt.setInt(4, id);

            stmt.execute();
            stmt.close();

        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public void DeletarReserva(Integer id) {
        String sql = "delete from reservas where id=?";
        try{
            PreparedStatement stmt = this.connection.prepareStatement(sql);
            stmt.setInt(1,id);
            stmt.execute();
            stmt.close();

        } catch (SQLException e) {
            System.out.println(e);
        }
    }
}
