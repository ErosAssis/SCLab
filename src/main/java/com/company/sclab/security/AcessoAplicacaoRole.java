package com.company.sclab.security;

import com.company.sclab.entity.Emprestimo;
import com.company.sclab.entity.Equipamento;
import com.company.sclab.entity.TipoEquipamento;
import io.jmix.security.model.EntityAttributePolicyAction;
import io.jmix.security.model.EntityPolicyAction;
import io.jmix.security.role.annotation.EntityAttributePolicy;
import io.jmix.security.role.annotation.EntityPolicy;
import io.jmix.security.role.annotation.ResourceRole;
import io.jmix.security.role.annotation.SpecificPolicy;
import io.jmix.securityflowui.role.annotation.MenuPolicy;
import io.jmix.securityflowui.role.annotation.ViewPolicy;

@ResourceRole(name = "AcessoAplicacao", code = AcessoAplicacaoRole.CODE)
public interface AcessoAplicacaoRole {
    String CODE = "acesso-aplicacao";

    @MenuPolicy(menuIds = {"Emprestimo.list", "Equipamento.list", "TipoEquipamento.list", "LeitorView"})
    @ViewPolicy(viewIds = {"Emprestimo.list", "Equipamento.list", "TipoEquipamento.list", "LeitorView", "Emprestimo.detail", "Equipamento.detail", "LoginView", "MainView", "TipoEquipamento.detail"})
    void screens();

    @EntityAttributePolicy(entityClass = Emprestimo.class, attributes = "*", action = EntityAttributePolicyAction.MODIFY)
    @EntityPolicy(entityClass = Emprestimo.class, actions = EntityPolicyAction.ALL)
    void emprestimo();

    @EntityAttributePolicy(entityClass = Equipamento.class, attributes = "*", action = EntityAttributePolicyAction.MODIFY)
    @EntityPolicy(entityClass = Equipamento.class, actions = EntityPolicyAction.ALL)
    void equipamento();

    @EntityAttributePolicy(entityClass = TipoEquipamento.class, attributes = "*", action = EntityAttributePolicyAction.MODIFY)
    @EntityPolicy(entityClass = TipoEquipamento.class, actions = EntityPolicyAction.ALL)
    void tipoEquipamento();

    @SpecificPolicy(resources = "ui.loginToUi")
    void specific();
}