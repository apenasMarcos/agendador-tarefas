package br.com.marcos.tarefas.model;


import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Entity
@Data
public class Tarefa {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private double custo;
    private LocalDate dataLimite;
    @Column(unique = true)
    private int ordem;

    public String getDataLimiteFormatada() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return dataLimite.format(formatter);
    }
}

