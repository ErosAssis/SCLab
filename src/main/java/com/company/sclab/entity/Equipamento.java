package com.company.sclab.entity;

import io.jmix.core.MetadataTools;
import io.jmix.core.entity.annotation.JmixGeneratedValue;
import io.jmix.core.metamodel.annotation.DependsOnProperties;
import io.jmix.core.metamodel.annotation.InstanceName;
import io.jmix.core.metamodel.annotation.JmixEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.util.UUID;

@JmixEntity
@Table(name = "EQUIPAMENTO", indexes = {
        @Index(name = "IDX_EQUIPAMENTO_TIPO_EQUIP", columnList = "TIPO_EQUIP_ID")
    }
)
@Entity
public class Equipamento {
    @JmixGeneratedValue
    @Column(name = "ID", nullable = false)
    @Id
    private UUID id;

    @Column(name = "VERSION", nullable = false)
    @Version
    private Integer version;

    @Column(name = "ID_RFID", nullable = false)
    @NotNull
    private String idRFID;

    @Column(name = "DESCRICAO")
    private String descricao;

    @Column(name = "EQUIP_ATIVO")
    private Boolean equipAtivo;

    @Column(name = "PATRIMONIO")
    private String patrimonio;

    @Column(name = "OBSERVACOES")
    private String observacoes;

    @JoinColumn(name = "TIPO_EQUIP_ID", nullable = false)
    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private TipoEquipamento tipoEquip;

    public TipoEquipamento getTipoEquip() {
        return tipoEquip;
    }

    public void setTipoEquip(TipoEquipamento tipoEquip) {
        this.tipoEquip = tipoEquip;
    }

    public String getObservacoes() {
        return observacoes;
    }

    public void setObservacoes(String observacoes) {
        this.observacoes = observacoes;
    }

    public String getPatrimonio() {
        return patrimonio;
    }

    public void setPatrimonio(String patrimonio) {
        this.patrimonio = patrimonio;
    }

    public Boolean getEquipAtivo() {
        return equipAtivo;
    }

    public void setEquipAtivo(Boolean equipAtivo) {
        this.equipAtivo = equipAtivo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getIdRFID() {
        return idRFID;
    }

    public void setIdRFID(String idRFID) {
        this.idRFID = idRFID;
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
    @DependsOnProperties({"tipoEquip"})
    public String getInstanceName(MetadataTools metadataTools) {
        return metadataTools.format(tipoEquip);
    }
}