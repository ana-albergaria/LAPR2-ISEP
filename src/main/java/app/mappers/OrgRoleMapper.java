package app.mappers;

import app.domain.model.OrgRole;
import app.mappers.dto.OrgRoleDTO;

import java.util.ArrayList;
import java.util.List;

public class OrgRoleMapper {
    public OrgRoleDTO toDTO(OrgRole role) {
        return new OrgRoleDTO(role.getDescription());
    }

    public List<OrgRoleDTO> toDTO(List<OrgRole> roles) {
        List<OrgRoleDTO> rolesDTOS = new ArrayList<>();
        for (OrgRole role : roles) {
            rolesDTOS.add(this.toDTO(role));
        }
        return rolesDTOS;
    }
}
