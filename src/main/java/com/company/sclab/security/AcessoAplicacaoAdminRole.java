package com.company.sclab.security;

import com.company.sclab.entity.Emprestimo;
import com.company.sclab.entity.Equipamento;
import com.company.sclab.entity.TipoEquipamento;
import com.company.sclab.entity.User;
import io.jmix.security.model.EntityAttributePolicyAction;
import io.jmix.security.model.EntityPolicyAction;
import io.jmix.security.role.annotation.EntityAttributePolicy;
import io.jmix.security.role.annotation.EntityPolicy;
import io.jmix.security.role.annotation.ResourceRole;
import io.jmix.securityflowui.role.annotation.MenuPolicy;
import io.jmix.securityflowui.role.annotation.ViewPolicy;

@ResourceRole(name = "AcessoAplicacaoAdmin", code = AcessoAplicacaoAdminRole.CODE)
public interface AcessoAplicacaoAdminRole {
    String CODE = "acesso-aplicacao-admin";

    @MenuPolicy(menuIds = {"User.list", "Emprestimo.list", "Equipamento.list", "TipoEquipamento.list", "LeitorView", "sec_ResourceRoleModel.list"})
    @ViewPolicy(viewIds = {"User.list", "Emprestimo.list", "Equipamento.list", "TipoEquipamento.list", "LeitorView", "sec_ResourceRoleModel.list"})
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

    @EntityAttributePolicy(entityClass = User.class, attributes = "*", action = EntityAttributePolicyAction.MODIFY)
    @EntityPolicy(entityClass = User.class, actions = EntityPolicyAction.ALL)
    void user();
}