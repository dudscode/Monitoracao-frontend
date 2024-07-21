package com.monitoracao.monitoramentoerros.entities;

import jakarta.persistence.*;

@Entity
public class Application {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "application_id")
    private Long Id;
    private String name;
    private int errorCount;
    private int acessCount;

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getErrorCount() {
        return errorCount;
    }

    public void setErrorCount(int errorCount) {
        this.errorCount = errorCount;
    }

    public int getAcessCount() {
        return acessCount;
    }

    public void setAcessCount(int acessCount) {
        this.acessCount = acessCount;
    }
}
