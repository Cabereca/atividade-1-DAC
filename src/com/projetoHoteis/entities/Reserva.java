package com.projetoHoteis.entities;

import java.time.LocalDate;
import java.util.Date;

public class Reserva {
    private Integer id;
    private Integer idHospede;
    private Integer numeroQuarto;
    private LocalDate dataEntrada;
    private LocalDate dataSaida;

    public Reserva(Integer id, Integer idHospede, Integer numeroQuarto, LocalDate dataEntrada, LocalDate dataSaida) {
        this.id = id;
        this.idHospede = idHospede;
        this.numeroQuarto = numeroQuarto;
        this.dataEntrada = dataEntrada;
        this.dataSaida = dataSaida;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getIdHospede() {
        return idHospede;
    }

    public void setIdHospede(Integer idHospede) {
        this.idHospede = idHospede;
    }

    public Integer getNumeroQuarto() {
        return numeroQuarto;
    }

    public void setNumeroQuarto(Integer numeroQuarto) {
        this.numeroQuarto = numeroQuarto;
    }

    public LocalDate getdataEntrada() {
        return dataEntrada;
    }

    public void setdataEntrada(LocalDate dataEntrada) {
        this.dataEntrada = dataEntrada;
    }

    public LocalDate getdataSaida() {
        return dataSaida;
    }

    public void setdataSaida(LocalDate dataSaida) {
        this.dataSaida = dataSaida;
    }
}
