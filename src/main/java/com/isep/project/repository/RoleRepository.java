package com.isep.project.repository;

import com.isep.project.entity.Role;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface RoleRepository extends JpaRepository<Role, Long>, JpaSpecificationExecutor<Role>
{

    /**
     * 根据用户id 查询角色列表
     *
     * @param userId 用户id
     * @return 角色列表
     */
    @Query(value = "SELECT t_role.* FROM t_role,t_user,t_user__role WHERE t_user.f_id = "
            + "t_user__role.f_user_id AND t_role.f_id = t_user__role.f_role_id AND t_user.f_id = "
            + ":userId"
            , nativeQuery = true)
    List<Role> selectByUserId(@Param("userId") Long userId);

}