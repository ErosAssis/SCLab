package com.company.sclab.entity;

import io.jmix.core.MetadataTools;
import io.jmix.core.entity.annotation.JmixGeneratedValue;
import io.jmix.core.metamodel.annotation.DependsOnProperties;
import io.jmix.core.metamodel.annotation.InstanceName;
import io.jmix.core.metamodel.annotation.JmixEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.util.UUID;

@JmixEntity
@Table(name = "EMPRESTIMO", indexes = {
        @Index(name = "IDX_EMPRESTIMO_RESPONSAVEL_RETIRADA", columnList = "RESPONSAVEL_RETIRADA_ID"),
        @Index(name = "IDX_EMPRESTIMO_RESPONSAVEL_DEVOLUCAO", columnList = "RESPONSAVEL_DEVOLUCAO_ID")
    },
        uniqueConstraints = {
            @UniqueConstraint(name = "UNQ_EMPRESTIMO_ID_RFID", columnNames = {"ID_RFID"})
        }
)
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

    @JoinColumn(name = "RESPONSAVEL_RETIRADA_ID", nullable = false)
    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private User responsavelRetirada;

    @JoinColumn(name = "RESPONSAVEL_DEVOLUCAO_ID")
    @ManyToOne(fetch = FetchType.LAZY)
    private User responsavelDevolucao;

    @Column(name = "OBSERVACOES")
    private String observacoes;

    @NotNull
    @Column(name = "ID_RFID", nullable = false)
    private String idRFID;

    public void setIdRFID(String idRFID) {
        this.idRFID = idRFID;
    }

    public String getIdRFID() {
        return idRFID;
    }

    public User getResponsavelDevolucao() {
        return responsavelDevolucao;
    }

    public void setResponsavelDevolucao(User responsavelDevolucao) {
        this.responsavelDevolucao = responsavelDevolucao;
    }

    public User getResponsavelRetirada() {
        return responsavelRetirada;
    }

    public void setResponsavelRetirada(User responsavelRetirada) {
        this.responsavelRetirada = responsavelRetirada;
    }

    public String getObservacoes() {
        return observacoes;
    }

    public void setObservacoes(String observacoes) {
        this.observacoes = observacoes;
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

    @InstanceName
    @DependsOnProperties({"responsavelRetirada", "responsavelDevolucao"})
    public String getInstanceName(MetadataTools metadataTools) {
        return String.format("%s %s",
                metadataTools.format(responsavelRetirada),
                metadataTools.format(responsavelDevolucao));
    }
}