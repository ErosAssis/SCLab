package com.company.sclab.entity;

import io.jmix.core.entity.annotation.JmixGeneratedValue;
import io.jmix.core.metamodel.annotation.JmixEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.util.UUID;

@JmixEntity
@Table(name = "RFID_LEITOR")
@Entity
public class RfidLeitor {
    @JmixGeneratedValue
    @Column(name = "ID", nullable = false)
    @Id
    private UUID id;

    @Column(name = "VERSION", nullable = false)
    @Version
    private Integer version;

    @NotNull
    @Column(name = "TAG_CODIGO", nullable = false)
    private String tagCodigo;

    @Column(name = "LIDO_EM")
    private LocalDate lidoEm;

    @Column(name = "PORTA_UTILIZADA")
    private String portaUtilizada;

    public String getPortaUtilizada() {
        return portaUtilizada;
    }

    public void setPortaUtilizada(String portaUtilizada) {
        this.portaUtilizada = portaUtilizada;
    }

    public LocalDate getLidoEm() {
        return lidoEm;
    }

    public void setLidoEm(LocalDate lidoEm) {
        this.lidoEm = lidoEm;
    }

    public String getTagCodigo() {
        return tagCodigo;
    }

    public void setTagCodigo(String tagCodigo) {
        this.tagCodigo = tagCodigo;
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