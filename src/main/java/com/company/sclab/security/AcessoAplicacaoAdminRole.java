package com.company.sclab.security;

import io.jmix.security.model.EntityAttributePolicyAction;
import io.jmix.security.model.EntityPolicyAction;
import io.jmix.security.role.annotation.EntityAttributePolicy;
import io.jmix.security.role.annotation.EntityPolicy;
import io.jmix.security.role.annotation.ResourceRole;
import io.jmix.security.role.annotation.SpecificPolicy;
import io.jmix.securityflowui.role.annotation.MenuPolicy;
import io.jmix.securityflowui.role.annotation.ViewPolicy;

@ResourceRole(name = "AcessoAplicacaoAdmin", code = AcessoAplicacaoAdminRole.CODE)
public interface AcessoAplicacaoAdminRole {
    String CODE = "acesso-aplicacao-admin";

    @EntityPolicy(entityName = "*", actions = {EntityPolicyAction.ALL})
    @EntityAttributePolicy(entityName = "*", attributes = "*", action = EntityAttributePolicyAction.MODIFY)
    @ViewPolicy(viewIds = "*")
    @MenuPolicy(menuIds = {"User.list", "Emprestimo.list", "Equipamento.list", "TipoEquipamento.list", "LeitorView", "sec_ResourceRoleModel.list", "sec_RowLevelRoleModel.list"})
    @SpecificPolicy(resources = "*")
    void fullAccess();
}