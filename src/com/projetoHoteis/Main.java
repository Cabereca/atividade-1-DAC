package com.projetoHoteis;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import com.projetoHoteis.dao.DBDao;
import com.projetoHoteis.dao.HospedeDao;
import com.projetoHoteis.dao.ReservaDao;
import com.projetoHoteis.entities.Hospede;
import com.projetoHoteis.entities.Reserva;

public class Main {
    public static void main(String[] args) throws ClassNotFoundException {
        Scanner sc = new Scanner(System.in);

        System.out.println("-===Sistema de Hoteis===-");
        System.out.println("1 - Inserir Hospede");
        System.out.println("2 - Listar Hospede");
        System.out.println("3 - Criar Tabelas");
        System.out.println("4 - Criar Reserva");
        int ac = sc.nextInt();

        switch (ac) {
            case 1 -> {
                System.out.print("Insira o nome do Hospede:");
                String nome = sc.next();
                System.out.print("Insira o cpf do Hospede:");
                String cpf = sc.next();
                System.out.print("Insira o id do Hospede:");
                Integer id = sc.nextInt();
                Hospede hospede = new Hospede(nome, cpf, id);
                HospedeDao hospedeDao = new HospedeDao();
                hospedeDao.InsereHospede(hospede);
                break;
            }
            case 2 -> {
                HospedeDao hospedeDao = new HospedeDao();
                List<Hospede> list = hospedeDao.ListarTodosHospedes();
                System.out.println("ID  |  NOME |  CPF");
                System.out.println("-------------------");
                list.forEach((hospede ->
                        System.out.println(
                                hospede.getId() + "   |  " +
                                hospede.getNome() + "  |  " +
                                hospede.getCpf()
                        )
                ));

                break;
            }
            case 3 -> {
                System.out.println("1 - Tabela Hospedes");
                System.out.println("2 - Tabela Reservas");
                int tb = sc.nextInt();

                switch (tb) {
                    case 1 -> {
                        DBDao dbDao = new DBDao();
                        dbDao.criarTabelaHospedes();
                        break;
                    }
                    case 2 -> {
                        DBDao dbDao = new DBDao();
                        dbDao.criarTabelaReservas();
                        break;
                    }
                }
                break;
            }
            case 4 -> {
                System.out.print("Insira o id da Reserva:");
                int idReserva = sc.nextInt();
                System.out.print("Insira o id do Hospede:");
                int idHospede = sc.nextInt();
                System.out.print("Insira o numero do quarto:");
                int numeroQuarto = sc.nextInt();
                System.out.print("Insira a data de entrada:");
                String dataEntrada = sc.next();
                System.out.print("Insira a data de saída:");
                String dataSaida = sc.next();
                Reserva reserva = new Reserva(
                        idReserva,
                        idHospede,
                        numeroQuarto,
                        LocalDate.parse(dataEntrada),
                        LocalDate.parse(dataSaida)
                );
                ReservaDao reservaDao = new ReservaDao();
                reservaDao.criarReserva(reserva);
            }
            default -> {
                System.out.println("Não reconhecido");
            }
        }

    }
}