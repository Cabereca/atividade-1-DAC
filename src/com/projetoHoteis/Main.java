package com.projetoHoteis;

import java.util.List;
import java.util.Scanner;

import com.projetoHoteis.dao.DBDao;
import com.projetoHoteis.dao.HospedeDao;
import com.projetoHoteis.entities.Hospede;

public class Main {
    public static void main(String[] args) throws ClassNotFoundException {
        while (true){
            Scanner sc = new Scanner(System.in);

            System.out.println("-===Sistema de Hoteis===-");
            System.out.println("1 - Inserir Hospede");
            System.out.println("2 - Listar Hospede");
            System.out.println("3 - Atualizar Hospede");
            System.out.println("4 - Deletar Hospede");
            System.out.println("5 - Criar Tabelas");
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
                case 5 -> {
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
                default -> System.out.println("Não reconhecido");
            }
        }
    }
}