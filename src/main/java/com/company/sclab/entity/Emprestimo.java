package com.company.sclab.entity;

import io.jmix.core.entity.annotation.JmixGeneratedValue;
import io.jmix.core.metamodel.annotation.JmixEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.util.UUID;

@JmixEntity
@Table(name = "EMPRESTIMO", indexes = {
        @Index(name = "IDX_EMPRESTIMO_ID_RFID", columnList = "ID_RFID_ID")
})
@Entity
public class Emprestimo {
    @JmixGeneratedValue
    @Column(name = "ID", nullable = false)
    @Id
    private UUID id;

    @Column(name = "VERSION", nullable = false)
    @Version
    private Integer version;

    @Column(name = "COMODATARIO", nullable = false)
    @NotNull
    private String comodatario;

    @Column(name = "DATA_RETIRADA", nullable = false)
    @NotNull
    private LocalDate dataRetirada;

    @Column(name = "DATA_DEVOLUCAO")
    private LocalDate dataDevolucao;

    @NotNull
    @Column(name = "RESPONSAVEL_RETIRADA", nullable = false)
    private String responsavelRetirada;

    @Column(name = "RESPONSAVEL_DEVOLUCAO")
    private String responsavelDevolucao;

    @Column(name = "OBSERVACOES")
    private String observacoes;

    @NotNull
    @JoinColumn(name = "ID_RFID_ID", nullable = false)
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private Equipamento idRFID;

    public void setIdRFID(Equipamento idRFID) {
        this.idRFID = idRFID;
    }

    public Equipamento getIdRFID() {
        return idRFID;
    }

    public String getObservacoes() {
        return observacoes;
    }

    public void setObservacoes(String observacoes) {
        this.observacoes = observacoes;
    }

    public String getResponsavelDevolucao() {
        return responsavelDevolucao;
    }

    public void setResponsavelDevolucao(String responsavelDevolucao) {
        this.responsavelDevolucao = responsavelDevolucao;
    }

    public String getResponsavelRetirada() {
        return responsavelRetirada;
    }

    public void setResponsavelRetirada(String responsavelRetirada) {
        this.responsavelRetirada = responsavelRetirada;
    }

    public LocalDate getDataDevolucao() {
        return dataDevolucao;
    }

    public void setDataDevolucao(LocalDate dataDevolucao) {
        this.dataDevolucao = dataDevolucao;
    }

    public LocalDate getDataRetirada() {
        return dataRetirada;
    }

    public void setDataRetirada(LocalDate dataRetirada) {
        this.dataRetirada = dataRetirada;
    }

    public String getComodatario() {
        return comodatario;
    }

    public void setComodatario(String comodatario) {
        this.comodatario = comodatario;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

}