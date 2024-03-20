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
        Boolean isRunning = true;
        while (isRunning){
            Scanner sc = new Scanner(System.in);
            System.out.println("-===Sistema de Hoteis===-");
            System.out.println("1 - Operações hospedes");
            System.out.println("2 - Operações reservas");
            System.out.println("3 - Criar tabelas");
            System.out.println("99 - Sair");
            int ac = sc.nextInt();
            switch (ac) {
                case 1 -> {
                    System.out.println("-===Sistema de Hoteis===-");
                    System.out.println("1 - Inserir Hospede");
                    System.out.println("2 - Listar Hospedes");
                    System.out.println("3 - Atualizar Hospede");
                    System.out.println("4 - Deletar Hospede");
                    int hospedeAction = sc.nextInt();

                    switch (hospedeAction) {
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
                            }
                            case 3 -> {
                                System.out.print("Insira o id do Hospede:");
                                Integer id = sc.nextInt();
                                System.out.print("Insira o novo nome do Hospede:");
                                String nome = sc.next();
                                System.out.print("Insira o novo cpf do Hospede:");
                                String cpf = sc.next();

                                HospedeDao hospedeDao = new HospedeDao();
                                hospedeDao.AtualizarHospede(id, nome, cpf);
                            }
                            case 4 -> {
                                System.out.print("Insira o id do Hospede:");
                                Integer id = sc.nextInt();
                                HospedeDao hospedeDao = new HospedeDao();
                                hospedeDao.DeletarHospede(id);
                            }
                    }
                }

                case 2 -> {
                    System.out.println("-===Sistema de Hoteis===-");
                    System.out.println("1 - Criar Reserva");
                    System.out.println("2 - Listar Reservas");
                    System.out.println("3 - Atualizar Reserva");
                    System.out.println("4 - Deletar Reserva");
                    int reservaAction = sc.nextInt();

                    switch (reservaAction) {
                        case 1 -> {
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
                        case 2 -> {
                            ReservaDao reservaDao = new ReservaDao();
                            List<Reserva> reservas = reservaDao.ListarReservas();

                            reservas.forEach((reserva) -> System.out.println(
                                        reserva.getId() + " | " + reserva.getIdHospede() +
                                                " | " + reserva.getNumeroQuarto() + " | " +
                                                reserva.getdataEntrada() + " | " + reserva.getdataSaida()
                                    )
                            );
                        }

                        case 3 -> {
                            ReservaDao reservaDao = new ReservaDao();
                            System.out.println("Qual reserva deseja atualizar?");
                            int idReserva = sc.nextInt();
                            System.out.println("Qual o novo numero do quarto?");
                            int newNumQaurto = sc.nextInt();
                            System.out.println("Qual a nova data de entrada?");
                            String newDataEntrada = sc.next();
                            System.out.println("Qual a nova data de saída?");
                            String newDataSaida = sc.next();
                            reservaDao.AtualizarReserva(
                                    idReserva,
                                    newNumQaurto,
                                    LocalDate.parse(newDataEntrada),
                                    LocalDate.parse(newDataSaida)
                            );
                        }

                        case 4 -> {
                            System.out.println("Informe o id da reserva: ");
                            int idReserva = sc.nextInt();
                            ReservaDao reservaDao = new ReservaDao();
                            reservaDao.DeletarReserva(idReserva);
                        }
                    }
                }

                case 3 -> {
                    System.out.println("1 - Tabela Hospedes");
                    System.out.println("2 - Tabela Reservas");
                    int tb = sc.nextInt();

                    switch (tb) {
                        case 1 -> {
                            DBDao dbDao = new DBDao();
                            dbDao.criarTabelaHospedes();
                        }
                        case 2 -> {
                            DBDao dbDao = new DBDao();
                            dbDao.criarTabelaReservas();
                        }
                    }
                }

                case 99 -> isRunning = false;

                default -> System.out.println("Não reconhecido");
            }
        }
    }
}