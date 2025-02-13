package com.labs.vector.service.admin.dto.request;

import com.labs.vector.service.admin.enums.VectorRoles;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MapUserRolesReqest {
    List<Integer> vectorRoleIDList;
}
