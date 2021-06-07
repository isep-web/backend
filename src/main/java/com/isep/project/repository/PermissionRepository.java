package com.isep.project.repository;

import com.isep.project.entity.Permission;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 * @author Xuan MIAO
 */
public interface PermissionRepository extends JpaRepository<Permission, Long>,
        JpaSpecificationExecutor<Permission>
{


    /**
     * 根据角色列表查询权限列表
     *
     * @param ids 角色id列表
     * @return 权限列表
     */
    @Query(value = "SELECT DISTINCT t_permission.* FROM t_permission,t_role,t_role__permission "
            + "WHERE t_role.f_id = t_role__permission.f_role_id AND t_permission.f_id = "
            + "t_role__permission.f_permission_id AND t_role.f_id IN (:ids)", nativeQuery = true)
    List<Permission> selectByRoleIdList(@Param("ids") List<Long> ids);
}